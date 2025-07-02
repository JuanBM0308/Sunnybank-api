package com.juanba.sunnybank.domain.response.user;

public record CodePasswordResponse(
        String codePassword
) {
    public CodePasswordResponse(String codePassword) {
        this.codePassword = codePassword;
    }
}
