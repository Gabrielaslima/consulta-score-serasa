package com.consultascore.serasa.repository;

import com.consultascore.serasa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {
}
