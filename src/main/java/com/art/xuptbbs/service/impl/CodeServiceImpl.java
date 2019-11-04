package com.art.xuptbbs.service.impl;


import com.art.xuptbbs.service.CodeService;
import com.art.xuptbbs.util.CodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
public class CodeServiceImpl implements CodeService {

    private static Logger LOGGER = LoggerFactory.getLogger(CodeServiceImpl.class);
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Cacheable(cacheNames = "code")
    public void getCheckPhoto(HttpServletRequest request, HttpServletResponse response) {
       final String photoKey ;
        LOGGER.info("cheng"+request+response);
        try {
            LOGGER.info("start "+stringRedisTemplate);
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            LOGGER.info("two "+response);
            CodeUtil codeUtil = new CodeUtil();
            LOGGER.info("third "+codeUtil);
            String code = codeUtil.getRandcode(request,response);
            LOGGER.info("1 "+stringRedisTemplate +code);
            photoKey = ""+code.hashCode();
            stringRedisTemplate.opsForValue().set(photoKey,code,300, TimeUnit.SECONDS);
            LOGGER.info("失败 "+code);

            }catch (Exception e){
            e.printStackTrace();
        }
        return;
}
}
