package com.art.xuptbbs.dto;

import javax.validation.constraints.NotEmpty;


public class PasswordDTO {

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String againPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAgainPassword() {
        return againPassword;
    }

    public void setAgainPassword(String againPassword) {
        this.againPassword = againPassword;
    }
}
