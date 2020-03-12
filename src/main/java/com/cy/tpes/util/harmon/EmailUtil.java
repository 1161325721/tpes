package com.cy.tpes.util.harmon;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送工具类
 */
public class EmailUtil {

    private static final String NGINX_IP_HEADER = "X-Real-IP";
    private static final String NGINX_X_FORWARDED_FOR = "X-Forwarded-For";

    public static String getRemoteIp(HttpServletRequest request) {
        String ips = request.getHeader(NGINX_X_FORWARDED_FOR);
        String[] ipArray = StringUtils.split(ips, ",");
        String loginIp = "";
        if (ArrayUtils.isNotEmpty(ipArray)) {
            loginIp = StringUtils.trim(ipArray[0]);
        } else {
            String ip = request.getHeader(NGINX_IP_HEADER);
            if (StringUtils.isEmpty(ip)) {
                ip = request.getRemoteAddr();
            }
            loginIp = ip;
        }
        return loginIp;
    }

    public static boolean sendEmail(String emailAccount, String content) {
        boolean flag = false;
        try {
            final Properties props = System.getProperties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.163.com");
            props.put("mail.user", "shmdmw@163.com");
            props.put("mail.password", "2004115123");
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.starttls.enable", "true");
            // 设置邮箱认证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session session = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);
            // 设置发件人
            String username = props.getProperty("mail.user");

            InternetAddress fromAddress = new InternetAddress(username);
            message.setFrom(fromAddress);
            // 设置收件人
            InternetAddress[] toAddArr = {new InternetAddress(emailAccount),new InternetAddress(username)};

//            InternetAddress toAddress = new InternetAddress(emailAccount);
            message.setRecipients(MimeMessage.RecipientType.TO, toAddArr);
            // 设置邮件标题
            message.setSubject("团检后台登录信息提示 "+new Date().toLocaleString());
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            flag = true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
