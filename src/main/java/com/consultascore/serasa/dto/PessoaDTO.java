package com.consultascore.serasa.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String phoneNumber;

    private long age;

    private long score;

    private String cep;
}
