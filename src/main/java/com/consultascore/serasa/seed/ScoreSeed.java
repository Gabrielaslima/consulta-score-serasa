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
        scoreInsuficiente.setScoreDescricao("Inaceitável");
        scoreInsuficiente.setInicial(201);
        scoreInsuficiente.setValorFinal(500);

        scoreRepository.save(scoreInaceitável);

        Score scoreAceitável = new Score();
        scoreInsuficiente.setScoreDescricao("Aceitável");
        scoreInsuficiente.setInicial(501);
        scoreInsuficiente.setValorFinal(700);

        scoreRepository.save(scoreAceitável);

        Score scoreRecomendável = new Score();
        scoreInsuficiente.setScoreDescricao("Recomendável");
        scoreInsuficiente.setInicial(701);
        scoreInsuficiente.setValorFinal(1000);

        scoreRepository.save(scoreRecomendável);

    }
}
