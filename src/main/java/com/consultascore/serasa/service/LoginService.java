package com.consultascore.serasa.service;

import com.consultascore.serasa.dto.LoginDTO;

import java.util.Optional;

public interface LoginService {

    Optional<Long> authentication(LoginDTO loginDto);

}
