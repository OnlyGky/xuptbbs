package com.art.xuptbbs.interceptor;


import com.art.xuptbbs.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class AuthInterceptor implements HandlerInterceptor {

//    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
//
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//
//        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Methods","POST,GET");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Max-Age", "3600");
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null && cookies.length > 0)
//        for (Cookie cookie: cookies){
//            if ("token".equals(cookie.getName())){
//                String userId = JwtUtil.getAppUID( cookie.getValue() );
//                String token = stringRedisTemplate.opsForValue().get(userId);
//                if (token != null && token.equals(cookie.getValue()))
//                    return true;
//                else
//                    return false;
//            }
//        }
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        return false;
//    }
}