package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;

public interface EmailService {

    BaseResponse getEmailCode(String email);
}
