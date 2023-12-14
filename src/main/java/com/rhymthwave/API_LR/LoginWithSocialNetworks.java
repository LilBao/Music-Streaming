package com.rhymthwave.API_LR;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.Implement.CustemerOAuth2User;
import com.rhymthwave.Service_LR.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginWithSocialNetworks {

    private  final ILoginService loginSuccess;
    @GetMapping("/api/v1/auth/success")
    public ResponseEntity<?> loginSuccess(  OAuth2AuthenticationToken token){
        System.out.println(Optional.ofNullable(token.getPrincipal().getAttribute("email")));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",loginSuccess.checkLoginWithSocial(token)));
    }

    @GetMapping("/api/v1/auth/fail")
    public ResponseEntity<?> loginFail(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Login fail"));
    }

    @GetMapping("/api/v1/auth/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "Successfully"));
    }

    @GetMapping("/api/v1/auth/logout/success")
    public ResponseEntity<?> logoutsuscc(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(false, "logout Successfully"));
    }
}
