package com.consultascore.serasa.seed;

import com.consultascore.serasa.entity.RegularUser;
import com.consultascore.serasa.enums.RoleEnum;
import com.consultascore.serasa.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeed implements CommandLineRunner {

    @Autowired
    private IUserRepository repository;

    /**
     * metodo responsavel por inserir usuario ADMIN e USER no banco de dados ao rodar o projeto
     * */
    @Override
    public void run(String... args) throws Exception {

        if (repository.count() == 0) {
            RegularUser userAdmin = new RegularUser();
            userAdmin.setLogin("testeAdmin");
            userAdmin.setName("admin");
            userAdmin.setRole(RoleEnum.ADMIN);
            userAdmin.setPassword("admin");

            repository.save(userAdmin);

            RegularUser regularUser = new RegularUser();
            regularUser.setLogin("testeUser");
            regularUser.setName("user");
            regularUser.setRole(RoleEnum.USER);
            regularUser.setPassword("user");

            repository.save(regularUser);
        }

    }
}
