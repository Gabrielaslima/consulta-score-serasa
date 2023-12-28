package com.consultascore.serasa.service;

import com.consultascore.serasa.dto.LoginDTO;
import com.consultascore.serasa.entity.RegularUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface LoginService extends UserDetailsService {

    Optional<Long> authentication(LoginDTO loginDto);

    RegularUser authenticateUserFromId(String id);

}
