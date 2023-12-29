package com.consultascore.serasa.controller;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.entity.Pessoa;
import com.consultascore.serasa.entity.RegularUser;
import com.consultascore.serasa.enums.RoleEnum;
import com.consultascore.serasa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/serasa/score")
public class SerasaController {

    @Autowired
    private PessoaService pessoaService;

    /**
     * */
    @PostMapping(value = "/pessoa", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> postPessoa(SecurityContextHolder context, @RequestBody PessoaDTO body) {

        RegularUser user = (RegularUser)context.getContext().getAuthentication().getPrincipal();
        if (user.getRole() != RoleEnum.ADMIN) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não autorizado a realizar esta operação");
        }

        return pessoaService.cadastroPessoa(body);
    }

    @GetMapping(value="/pessoa-pageable", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Page<Pessoa>> listarPessoas(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String cep,
            Pageable pageable) {
        return pessoaService.listarPessoas(name, age, cep, pageable);
    }

    @PutMapping(value = "/pessoa/{idPessoa}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> updatePessoa(SecurityContextHolder context, @PathVariable Long idPessoa, @RequestBody PessoaDTO body) {

        RegularUser user = (RegularUser)context.getContext().getAuthentication().getPrincipal();
        if (user.getRole() != RoleEnum.ADMIN) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não autorizado a realizar esta operação");
        }

        return pessoaService.updatePessoa(idPessoa, body);
    }

    @GetMapping(value = "/score-descricao/{idPessoa}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> getPessoaId(@PathVariable Long idPessoa) {
        ResponseEntity<String> response = pessoaService.getScoreDescricaoById(idPessoa);
        return response;
    }

    @DeleteMapping(value = "/pessoa/{idPessoa}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> deletePessoa(SecurityContextHolder context, @PathVariable Long idPessoa) {

        RegularUser user = (RegularUser)context.getContext().getAuthentication().getPrincipal();
        if (user.getRole() != RoleEnum.ADMIN) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não autorizado a realizar esta operação");
        }

        return pessoaService.deleteById(idPessoa);
    }

}
