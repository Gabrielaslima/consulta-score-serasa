package com.consultascore.serasa.seed;


import com.consultascore.serasa.entity.Score;
import com.consultascore.serasa.repository.IScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ScoreSeed implements CommandLineRunner {

    @Autowired
    private IScoreRepository scoreRepository;

    /**
     * metodo responsavel por inserir dados na tabela de Score ao rodar o projeto
     * */
    @Override
    public void run(String... args) throws Exception {

        Score scoreInsuficiente = new Score();
        scoreInsuficiente.setScoreDescricao("Insuficiente");
        scoreInsuficiente.setInicial(0L);
        scoreInsuficiente.setValorFinal(200);

        scoreRepository.save(scoreInsuficiente);

        Score scoreInaceitável = new Score();
        scoreInaceitável.setScoreDescricao("Inaceitável");
        scoreInaceitável.setInicial(201);
        scoreInaceitável.setValorFinal(500);

        scoreRepository.save(scoreInaceitável);

        Score scoreAceitável = new Score();
        scoreAceitável.setScoreDescricao("Aceitável");
        scoreAceitável.setInicial(501);
        scoreAceitável.setValorFinal(700);

        scoreRepository.save(scoreAceitável);

        Score scoreRecomendável = new Score();
        scoreRecomendável.setScoreDescricao("Recomendável");
        scoreRecomendável.setInicial(701);
        scoreRecomendável.setValorFinal(1000);

        scoreRepository.save(scoreRecomendável);

    }
}
