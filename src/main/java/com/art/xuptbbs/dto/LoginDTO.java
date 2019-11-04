package com.art.xuptbbs.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {

    @NotEmpty
    private String entrance;

    @NotEmpty
    private String password;

//    @NotEmpty
//    @Length(max = 4)
//    private String code;


    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
}
