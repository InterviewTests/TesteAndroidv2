package com.santander.vicolmoraes.santander.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataUsuarioVO {

    @JsonProperty("userAccount")
    private UsuarioVO userAccount;

    public UsuarioVO getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UsuarioVO userAccount) {
        this.userAccount = userAccount;
    }

}
