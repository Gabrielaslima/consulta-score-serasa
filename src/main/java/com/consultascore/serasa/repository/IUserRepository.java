package com.consultascore.serasa.repository;

import com.consultascore.serasa.entity.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<RegularUser, Long> {
}
