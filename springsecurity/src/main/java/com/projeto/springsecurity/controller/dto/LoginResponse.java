package com.projeto.springsecurity.controller.dto;

public record LoginResponse(String acessToken, Long expireIn) {

}
