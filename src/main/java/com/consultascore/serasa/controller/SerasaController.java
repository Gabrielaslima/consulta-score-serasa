package com.consultascore.serasa.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/serasa")
@Api("API referente ao cadastro de Score de pessoas")
public class SerasaController {

    @Autowired
    private PessoaService pessoaService;


    @PostMapping(value = "/pessoa")
    public ResponseEntity<String> postPessoa(@RequestBody PessoaDTO body) {
        ResponseEntity<String> response = pessoaService.cadastroPessoa(body);
        return response;
    }

}
