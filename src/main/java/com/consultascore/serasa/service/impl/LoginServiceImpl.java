package com.consultascore.serasa.service.impl;

import com.consultascore.serasa.dto.LoginDTO;
import com.consultascore.serasa.entity.RegularUser;
import com.consultascore.serasa.repository.IUserRepository;
import com.consultascore.serasa.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public Optional<Long> authentication(LoginDTO loginDto) {

        Optional<RegularUser> user = userRepository.findByLoginEqualsAndPasswordEquals(loginDto.getLogin(), loginDto.getPassword());

        return user.map(RegularUser::getId);
    }

    public RegularUser authenticateUserFromId(String id) {

        Optional<RegularUser> user = userRepository.findById(Long.valueOf(id));

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("User Not Found With Id: " + id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
