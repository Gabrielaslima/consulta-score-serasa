package com.consultascore.serasa.service.impl;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.entity.Pessoa;
import com.consultascore.serasa.repository.IPessoaRepository;
import com.consultascore.serasa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private IPessoaRepository pessoaRepository;

    @Override
    public ResponseEntity<String> cadastroPessoa(PessoaDTO pessoaDto) {

        try {
            Pessoa newPessoa = new Pessoa(pessoaDto);
            pessoaRepository.save(newPessoa);

            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar pessoa.");
        }

    }

}
