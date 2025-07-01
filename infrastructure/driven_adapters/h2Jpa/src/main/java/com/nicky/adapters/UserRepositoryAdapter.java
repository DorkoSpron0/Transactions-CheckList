package com.nicky.adapters;

import com.nicky.DBO.UserDBO;
import com.nicky.JwtService;
import com.nicky.UserDomain;
import com.nicky.UserRepositoryPort;
import com.nicky.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserRepositoryAdapter(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        return userRepository.save(UserDBO.fromDomain(userDomain)).toDomain();
    }


    @Override
    @Transactional
    public Optional<UserDomain> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userDBO -> Optional.of(userDBO.toDomain()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public String login(UserDomain userDomain) {
        UserDBO userFounded = userRepository.findByEmail(userDomain.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!passwordEncoder.matches(userDomain.getPassword(), userFounded.getPassword())){
            throw new BadCredentialsException("Password doesn't match");
        }

        return jwtService.createToken(userFounded.getEmail());
    }

}
