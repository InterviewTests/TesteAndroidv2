package com.example.santander.model;

import com.squareup.moshi.Json;

public class loginVO {

    @Json(name = "userAccount")
    private userAccountVO userAccountVO;

    @Json(name = "error")
    private errorVO errorVO;

    public loginVO(com.example.santander.model.userAccountVO userAccountVO, com.example.santander.model.errorVO errorVO) {
        this.userAccountVO = userAccountVO;
        this.errorVO = errorVO;
    }

    public userAccountVO getUserAccount() {
        return userAccountVO;
    }

    public errorVO getErrorVO() {
        return errorVO;
    }
}
