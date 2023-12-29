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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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
    public void listarPessoas() {
        Pessoa pessoa = new Pessoa();
        pessoa.setScore(1000);
        pessoa.setPhoneNumber("16981513857");
        pessoa.setCep("14811430");
        pessoa.setName("Teste");
        List<Pessoa> lista = new ArrayList<>();
        lista.add(pessoa);
        Page<Pessoa> page = new PageImpl<Pessoa>(lista);

        Pageable pageable= PageRequest.of(0, 5);
        Mockito.when(pessoaRepository.findByNameContainingIgnoreCaseAndAgeEqualsAndCepEquals("teste", 20, "11111111", pageable)).thenReturn(page);

        ResponseEntity<Page<Pessoa>> response = pessoaService.listarPessoas("teste", 20, "11111111", pageable);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteById() {
       ResponseEntity<String> response = pessoaService.deleteById(1L);
       Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
