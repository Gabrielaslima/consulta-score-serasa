package com.consultascore.serasa.entity;

import com.consultascore.serasa.dto.PessoaDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;

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

    private String name;

    private String phoneNumber;

    private long age;

    private String city;

    private String state;
    private long score;

    private String neighborhood;

    private String patio;
    private String cep;

    public Pessoa(PessoaDTO pessoaDto) {
        this.name = pessoaDto.getName();
        this.phoneNumber = pessoaDto.getPhoneNumber();
        this.age = pessoaDto.getAge();
        this.score = pessoaDto.getScore();
        this.cep = pessoaDto.getCep();
    }

}
