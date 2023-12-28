package com.consultascore.serasa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String telefone;

    private long idade;

    private long score;

    private String cep;
}
