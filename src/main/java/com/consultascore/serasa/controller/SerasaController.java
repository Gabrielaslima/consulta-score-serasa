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

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@OpenAPIDefinition(info = @Info(title = "Cadastro e consulta de Score API", version = "0.0.1-SNAPSHOT", description = "API de cadastro e consulta de score de pessoa"))
@RequestMapping("/api/v1/serasa")
public class SerasaController {

    @Autowired
    private PessoaService pessoaService;

    /**
     * */
    @PostMapping(value = "/pessoa")
    public ResponseEntity<String> postPessoa(@RequestBody PessoaDTO body) throws URISyntaxException, IOException, InterruptedException {
        return pessoaService.cadastroPessoa(body);
    }

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarPessoas(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String cep,
            Pageable pageable) {
        return pessoaService.listarPessoas(name, age, cep, pageable);
    }

    @PutMapping(value = "/pessoa/{id}")
    public ResponseEntity<String> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO body) {
        return pessoaService.updatePessoa(id, body);
    }

    @GetMapping(value = "/score-descricao/{idPessoa}")
    public ResponseEntity<String> getPessoaId(@PathVariable Long id) {
        ResponseEntity<String> response = pessoaService.getScoreDescricaoById(id);
        return response;
    }

    @DeleteMapping(value = "/pessoa/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id) {
        ResponseEntity<String> response = pessoaService.deleteById(id);
        return response;
    }

}
