package com.consultascore.serasa.entity;

import com.consultascore.serasa.dto.ScoreDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity(name = "SCORE")
@Table(name = "SCORE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scoreDescricao;

    private long inicial;

    private long valorFinal;

    public Score(ScoreDTO scoreDto) {
        this.scoreDescricao = scoreDto.getScoreDescricao();
        this.inicial = scoreDto.getInicial();
        this.valorFinal = scoreDto.getValorFinal();
    }
}
