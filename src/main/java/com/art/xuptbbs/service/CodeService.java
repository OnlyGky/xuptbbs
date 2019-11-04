package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CodeService {
    void getCheckPhoto(HttpServletRequest request, HttpServletResponse response);
}
