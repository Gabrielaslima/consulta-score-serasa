package com.consultascore.serasa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class EnderecoDTO {

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

}
