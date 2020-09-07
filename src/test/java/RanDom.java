import com.tian.blog.bean.User;
import com.tian.blog.dto.UserDTO;
import com.tian.blog.util.OSSFile;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    @Test
    public void test3(){
        List<String> list = Arrays.asList("1", "2", "3");
        System.out.println(list.get(1));

    }

    @Test
    public void test4(){
        User user = new User();
        user.setUsername("123");
        user.setPassword("123456");
        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(user,userDTO);
        System.out.println(user);
        System.out.println(userDTO);
    }

}
