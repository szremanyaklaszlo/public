package com.training.sportsbetting.service.player.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.User;
import com.training.sportsbetting.service.player.UserRepository;
import com.training.sportsbetting.service.player.exception.UserNotFoundException;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(loginName).or(() -> userRepository.findByEmail(loginName));
        if (!user.isPresent()) {
            throw new UserNotFoundException("User has not been found.");
        }
        return user.map(DefaultUserDetails::new).get();
    }

}
