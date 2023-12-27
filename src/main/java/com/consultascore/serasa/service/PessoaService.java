package com.consultascore.serasa.service;

import com.consultascore.serasa.dto.PessoaDTO;
import org.springframework.http.ResponseEntity;

public interface PessoaService {

    ResponseEntity<String> cadastroPessoa(PessoaDTO pessoaDto);

}
