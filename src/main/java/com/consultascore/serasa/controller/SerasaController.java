package com.consultascore.serasa.controller;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.entity.Pessoa;
import com.consultascore.serasa.service.PessoaService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@OpenAPIDefinition(info = @Info(title = "Cadastro e consulta de Score API", version = "0.0.1-SNAPSHOT", description = "API de cadastro e consulta de score de pessoa"))
@RequestMapping("/api/v1/serasa")
public class SerasaController {

    @Autowired
    private PessoaService pessoaService;

    /**
     * */
    @PostMapping(value = "/pessoa")
    public ResponseEntity<String> postPessoa(@RequestBody PessoaDTO body) {
        ResponseEntity<String> response = pessoaService.cadastroPessoa(body);
        return response;
    }

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarPessoas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer idade,
            @RequestParam(required = false) String cep,
            Pageable pageable) {
        return pessoaService.listarPessoas(nome, idade, cep, pageable);
    }

}
