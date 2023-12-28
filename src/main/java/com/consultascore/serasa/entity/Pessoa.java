package com.consultascore.serasa.entity;

import com.consultascore.serasa.dto.PessoaDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "PESSOA")
@Table(name = "PESSOA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private long idade;

    private String cidade;

    private String estado;

    private long score;

    private String bairro;

    private String logradouro;

    private String cep;

    public Pessoa(PessoaDTO pessoaDto) {
        this.nome = pessoaDto.getNome();
        this.telefone = pessoaDto.getTelefone();
        this.idade = pessoaDto.getIdade();
        this.score = pessoaDto.getScore();
        this.cep = pessoaDto.getCep();
    }

}
