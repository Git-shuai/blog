import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author tian
 * @date 2020/8/18
 */
public class RanDom {
    @Test
    public void test(){
        Random random = new Random();
        int code = random.nextInt();
        System.out.println(((Math.random()+1)*9)*100000);
        System.out.println();
    }

    @Test
    public void test1(){
        String sms="【ABC博客】验证码为：333333，您正在登录，若非本人操作，请勿泄露。";
        String substring = sms.substring(12, 18);
        System.out.println(substring);
    }

    @Test
    public void test2(){
        Date date1 = new Date(1595065367000L);
        String strDateFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date1));
    }

}
