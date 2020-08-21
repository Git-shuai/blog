package com.tian.blog.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tian
 * @date 2020/8/18
 */
@Component
public class DateUtil {

    /**
     * 时间
     * @param date
     * @return
     */
    public String dateToFormat(Date date){
        String strDateFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(date);
    }

    /**
     * 判断时间是否在给定的时间段内，不判断日期
     * @param date  new Date()
     * @param time  09:00:00-12:00:00,13:00:00-17:00:00
     * @return
     */
    public boolean judgeDate(Date date, String time) {
        if ("".equals(time) || "null".equals(time) || null == time) {
            return false;
        }
        boolean flag = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            date = dateFormat.parse(dateFormat.format(date));
            System.out.println("data:"+date);
            String[] split1 = time.split(",");
            dateFormat = new SimpleDateFormat("HH:mm");
            for (String s : split1) {
                String[] split2 = s.split("-");
                Date startDate = dateFormat.parse(split2[0]);
                Date endDate = dateFormat.parse(split2[1]);
                if (date.before(endDate) && date.after(startDate)) {
                    flag = true;
                    break;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
