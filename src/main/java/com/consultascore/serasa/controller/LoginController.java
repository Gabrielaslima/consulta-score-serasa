package com.consultascore.serasa.controller;

import com.consultascore.serasa.dto.LoginDTO;
import com.consultascore.serasa.service.LoginService;
import com.consultascore.serasa.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@OpenAPIDefinition(info = @Info(title = "Cadastro e consulta de score de pessoas API", version = "0.0.1-SNAPSHOT", description = "API de cadastro e consulta de score de pessoas"))
@RequestMapping("/api/v1/serasa/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO login) {

        Optional<Long> auth = loginService.authentication(login);

        if (auth.isPresent()) {

            String token = jwtTokenUtil.generateToken(auth.get());
            return ResponseEntity.status(HttpStatus.OK).header("token", token).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
