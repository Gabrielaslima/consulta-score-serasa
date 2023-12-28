package com.consultascore.serasa.service;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PessoaService {

    ResponseEntity<String> cadastroPessoa(PessoaDTO pessoaDto);

    ResponseEntity<Page<Pessoa>> listarPessoas(String name, Integer age, String cep, Pageable pageable);

    ResponseEntity<String> getScoreDescricaoById(Long id);

    ResponseEntity<String> updatePessoa(Long id, PessoaDTO pessoaDTO);

}
