package com.itheima.test;

import com.itheima.utils.MailUtils;
import org.junit.Test;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestEmail {

    @Test
    public void test(){
        try {
            MailUtils.sendMail("paul@store.com","哈哈哈!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送失败!!!");
        }
    }

    @Test
    public void testDate() throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sf.format(new Date());
        Date parse = sf.parse(format);
        System.out.println("1:"+new Date());
        System.out.println("2:"+parse);

    }
}
