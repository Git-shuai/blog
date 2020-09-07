package com.tian.blog.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author tian
 * @date 2020/7/3
 */
@Service
public class OSSFile {

    @Value("${alibaba.oss.endpoint}")
    private String endpoint;

    @Value("${alibaba.accessKeyId}")
    private String accessKeyId;

    @Value("${alibaba.secret}")
    private String accessKeySecret;

    @Value("${alibaba.oss.bucketName}")
    private String bucketName;


    /**
     * 简单上传
     * @param inputStream
     * @param filename
     * @return
     */
    public String oss(InputStream inputStream, String filename) {

        if (StringUtils.isEmpty(filename)) {
            return "文件名为空";
        }
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);



        filename = UUID.randomUUID().toString() + "." + filename;

        ossClient.putObject(bucketName, "community/" + filename, inputStream);

        String url = getUrl("community/"+filename);
        // 关闭OSSClient。
        ossClient.shutdown();

        return url;
    }


    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);

        ossClient.shutdown();

        if (url != null) {
            return url.toString();
        }
        return null;
    }

    /**
     * 表单上传
     * @param fileName  文件路径
     * @return
     * @throws Exception
     */
    public String PostObject(String fileName) {

        String objectName="community"+UUID.randomUUID().toString()+fileName;
        String urlStr = endpoint.replace("http://", "http://"+bucketName+"."); // 提交表单的URL为bucket域名

        LinkedHashMap<String, String> textMap = new LinkedHashMap<String, String>();
        // key
        textMap.put("key", objectName);
        // Content-Disposition
        textMap.put("Content-Disposition", "attachment;filename="+fileName);
        // OSSAccessKeyId
        textMap.put("OSSAccessKeyId", accessKeyId);
        // policy
        String policy = "{\"expiration\": \"2120-01-01T12:00:00.000Z\",\"conditions\": [[\"content-length-range\", 0, 104857600]]}";
        String encodePolicy = java.util.Base64.getEncoder().encodeToString(policy.getBytes());
        textMap.put("policy", encodePolicy);
        // Signature
        String signaturecom = com.aliyun.oss.common.auth.ServiceSignature.create().computeSignature(accessKeySecret, encodePolicy);
        textMap.put("Signature", signaturecom);

        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("file", fileName);

        formUpload(urlStr, textMap, fileMap);

        String url = getUrl(objectName);
        return url;

    }

    private static String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap) {
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "9431149156168";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator iter = textMap.entrySet().iterator();
                int i = 0;
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    if (i == 0) {
                        strBuf.append("--").append(BOUNDARY).append(
                                "\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                                + inputName + "\"\r\n\r\n");
                        strBuf.append(inputValue);
                    } else {
                        strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                                "\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                                + inputName + "\"\r\n\r\n");

                        strBuf.append(inputValue);
                    }

                    i++;
                }
                out.write(strBuf.toString().getBytes());
            }

            // file
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    String contentType = new MimetypesFileTypeMap().getContentType(file);
                    if (contentType == null || contentType.equals("")) {
                        contentType = "application/octet-stream";
                    }

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(
                            "\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type: " + contentType + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
                StringBuffer strBuf = new StringBuffer();
                out.write(strBuf.toString().getBytes());
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.err.println("发送POST请求出错: " + urlStr);
            return "上传失败";
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }
}
