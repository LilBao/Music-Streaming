package com.rhymthwave.Request.DTO;

public record ChangePasswordDTO (String email, String password, String newpass, String confirmpass) {

}
