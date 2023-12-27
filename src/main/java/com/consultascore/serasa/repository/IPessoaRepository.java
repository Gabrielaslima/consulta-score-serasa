package com.consultascore.serasa.repository;

import com.consultascore.serasa.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {
    Page<Pessoa> findByNomeContainingIgnoreCaseAndIdadeEqualsAndCepEquals(String nome, Integer idade, String cep, Pageable pageable);
}
