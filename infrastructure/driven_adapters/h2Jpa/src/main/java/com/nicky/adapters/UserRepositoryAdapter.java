package com.nicky.adapters;

import com.nicky.DBO.UserDBO;
import com.nicky.UserDomain;
import com.nicky.UserRepositoryPort;
import com.nicky.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        return userRepository.save(UserDBO.fromDomain(userDomain)).toDomain();
    }
}
