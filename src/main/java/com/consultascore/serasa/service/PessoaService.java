package com.consultascore.serasa.service;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PessoaService {

    ResponseEntity<String> cadastroPessoa(PessoaDTO pessoaDto);

    ResponseEntity<Page<Pessoa>> listarPessoas(String nome, Integer idade, String cep, Pageable pageable);

}
