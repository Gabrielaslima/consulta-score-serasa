package com.consultascore.serasa.service.impl;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.dto.EnderecoDTO;
import com.consultascore.serasa.entity.Pessoa;
import com.consultascore.serasa.repository.IPessoaRepository;
import com.consultascore.serasa.repository.IScoreRepository;
import com.consultascore.serasa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private IPessoaRepository pessoaRepository;

    @Autowired
    private IScoreRepository scoreRepository;

    /**
     * metodo responsavel por realizar o cadastro de pessoa
     * */
    @Override
    public ResponseEntity<String> cadastroPessoa(PessoaDTO pessoaDTO) {
        //tenta cadastrar pessoa e caso sucesso retorna status 201 CREATED e caso der erro retorna status 500 ERROR
        try {

            EnderecoDTO endereco = consultaEndereco(pessoaDTO.getCep());

            Pessoa newPessoa = new Pessoa(pessoaDTO);
            newPessoa.setCity(endereco.getLocalidade());
            newPessoa.setPatio(endereco.getLogradouro());
            newPessoa.setNeighborhood(endereco.getBairro());
            newPessoa.setState(endereco.getUf());

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
    public ResponseEntity<Page<Pessoa>> listarPessoas(String name, Integer age, String cep, Pageable pageable) {
        //tenta filtrar pessoas por nome, idade e cep e caso der sucesso retorna status 200 OK e caso der erro retorna status 500 ERROR
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findByNameContainingIgnoreCaseAndAgeEqualsAndCepEquals(name, age, cep, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> getScoreDescricaoById(Long id) {

        try {
            Optional<Pessoa> pessoa = pessoaRepository.findById(id);

            return ResponseEntity.status(HttpStatus.OK).body("O score da pessoa é: " + scoreRepository.findScoreDescricao(pessoa.get().getScore()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar score da pessoa");
        }

    }

    @Override
    public ResponseEntity<String> updatePessoa(Long id, PessoaDTO pessoaDTO) {

        try {
            Optional<Pessoa> existingPessoa = pessoaRepository.findById(id);

            EnderecoDTO novoEndereco = consultaEndereco(pessoaDTO.getCep());

            if (existingPessoa.isPresent()) {
                existingPessoa.get().setName(pessoaDTO.getName());
                existingPessoa.get().setAge(pessoaDTO.getAge());
                existingPessoa.get().setState(novoEndereco.getUf());
                existingPessoa.get().setNeighborhood(novoEndereco.getBairro());
                existingPessoa.get().setPatio(novoEndereco.getLogradouro());
                existingPessoa.get().setCity(novoEndereco.getLocalidade());
                existingPessoa.get().setCep(pessoaDTO.getCep());
                existingPessoa.get().setPhoneNumber(pessoaDTO.getPhoneNumber());
                existingPessoa.get().setScore(pessoaDTO.getScore());

                pessoaRepository.save(existingPessoa.get());

                return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa atualizada com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pessoa inexistente na base de dados");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar pessoa");
        }

    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        try {
            pessoaRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar pessoa.");
        }
    }

    private EnderecoDTO consultaEndereco(String cep) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://viacep.com.br/ws/" + cep + "/json/")).GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), EnderecoDTO.class);

    }

}
