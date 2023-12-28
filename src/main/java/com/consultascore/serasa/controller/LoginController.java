package com.consultascore.serasa.controller;

import com.consultascore.serasa.dto.LoginDTO;
import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.service.LoginService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@OpenAPIDefinition(info = @Info(title = "Login de pessoa na API", version = "0.0.1-SNAPSHOT", description = "API de login de pessoa"))
@RequestMapping("/api/v1/serasa/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO login) {

        Optional<Long> auth = loginService.authentication(login);

        if (auth.isPresent()) {
            return ;
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
