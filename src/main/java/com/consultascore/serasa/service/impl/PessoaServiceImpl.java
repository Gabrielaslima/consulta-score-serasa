package com.consultascore.serasa.service.impl;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.entity.Pessoa;
import com.consultascore.serasa.repository.IPessoaRepository;
import com.consultascore.serasa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private IPessoaRepository pessoaRepository;

    /**
     * metodo responsavel por realizar o cadastro de pessoa
     * */
    @Override
    public ResponseEntity<String> cadastroPessoa(PessoaDTO pessoaDto) {
        //tenta cadastrar pessoa e caso sucesso retorna status 201 CREATED e caso der erro retorna status 500 ERROR
        try {
            Pessoa newPessoa = new Pessoa(pessoaDto);
            pessoaRepository.save(newPessoa);

            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar pessoa.");
        }


    }

    /**
     * metodo responsavel por realizar a filtragem de pessoas
     * */
    @Override
    public ResponseEntity<Page<Pessoa>> listarPessoas(String nome, Integer idade, String cep, Pageable pageable) {
        //tenta filtrar pessoas por nome, idade e cep e caso der sucesso retorna status 200 OK e caso der erro retorna status 500 ERROR
        try {

            return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findByNomeContainingIgnoreCaseAndIdadeEqualsAndCepEquals(nome, idade, cep, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
