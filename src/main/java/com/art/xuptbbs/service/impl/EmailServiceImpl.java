package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "email")
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender mailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("2585581673@qq.com")
    private String form;

    @Override
    public BaseResponse getEmailCode(String email) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);

        String message = "您的注册验证码为：" + checkCode + "有效时间为五分钟";
        SimpleMailMessage sendto = new SimpleMailMessage();
        sendto.setFrom(form);
        sendto.setTo(email);
        sendto.setSubject("西邮技术论坛");
        sendto.setText(message);
        mailSender.send(sendto);
        String key = email;
        if (stringRedisTemplate.hasKey(key)) {
            stringRedisTemplate.delete(key);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, checkCode, 300, TimeUnit.SECONDS);
        }else {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(key, checkCode, 300, TimeUnit.SECONDS);
        }
        return BaseResponse.out(CodeEnum.SUCCESS);
    }
}
