package com.consultascore.serasa.repository;

import com.consultascore.serasa.entity.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<RegularUser, Long> {

    Optional<RegularUser> findByLoginEqualsAndPasswordEquals(String login, String password);

}
