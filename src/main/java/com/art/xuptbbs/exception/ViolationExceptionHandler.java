package com.art.xuptbbs.exception;

import com.art.xuptbbs.controller.CodeController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ViolationExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody

    public Map<String, Object> allExceptionHandler(Exception e) {
        Map<String, Object> map = new HashMap<>(2);
        if (e instanceof BindException) {
            BindException ex = (BindException) e;
            BindingResult bindingResult = ex.getBindingResult();
            StringBuilder errMsg = new StringBuilder(bindingResult.getFieldErrors().size() * 16);
            errMsg.append("Invalid request:");
            for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
                if (i > 0) {
                    errMsg.append(",");
                }
                FieldError error = bindingResult.getFieldErrors().get(i);
                errMsg.append(error.getField() + ":" + error.getDefaultMessage());
            }
            map.put("errcode", 500);

            map.put("errmsg", errMsg.toString());
        } else {
            map.put("errcode", 500);
            map.put("errmsg", e.getMessage());
        }
        return map;
    }

}
