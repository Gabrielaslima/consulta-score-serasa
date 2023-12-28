package com.consultascore.serasa.repository;

import com.consultascore.serasa.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IScoreRepository extends JpaRepository<Score, Long> {

    @Query(value = "SELECT s.scoreDescricao FROM com.consultascore.serasa.entity.Score s WHERE s.inicial <= :scorePessoa AND :scorePessoa <= s.valorFinal")
    public String findScoreDescricao(@Param("scorePessoa") Long scorePessoa);

}
