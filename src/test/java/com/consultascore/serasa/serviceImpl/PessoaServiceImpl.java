package com.consultascore.serasa.serviceImpl;

import com.consultascore.serasa.dto.PessoaDTO;
import com.consultascore.serasa.entity.Pessoa;
import com.consultascore.serasa.repository.IPessoaRepository;
import com.consultascore.serasa.repository.IScoreRepository;
import com.consultascore.serasa.service.PessoaService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PessoaServiceImpl {

    @Autowired
    private PessoaService pessoaService;

    @Mock
    private IPessoaRepository pessoaRepository;

    @Mock
    private IScoreRepository scoreRepository;

    @Test
    public void testCadastroPessoa() {

        PessoaDTO pessoaDTO = new PessoaDTO();
        Pessoa pessoa = new Pessoa();

        pessoaDTO.setScore(1000);
        pessoaDTO.setPhoneNumber("16981513857");
        pessoaDTO.setCep("14811430");
        pessoaDTO.setName("Teste");

        Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(pessoa);
        ResponseEntity<String> response = pessoaService.cadastroPessoa(pessoaDTO);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    public void testGetDescricaoById() {

        Pessoa pessoa = new Pessoa();
        pessoa.setScore(1000);
        pessoa.setId(1L);

        Optional<Pessoa> optionalPessoa = Optional.of(pessoa);

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(optionalPessoa);
        ResponseEntity<String> response = pessoaService.getScoreDescricaoById(1L);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
