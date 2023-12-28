package com.consultascore.serasa.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ScoreDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String scoreDescricao;

    private long inicial;

    private long valorFinal;

}
