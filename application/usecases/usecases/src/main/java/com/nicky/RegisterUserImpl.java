package com.nicky;

public class RegisterUserImpl implements RegisterUserPort{

    private final UserRepositoryPort userRepository;

    public RegisterUserImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDomain registerUser(UserDomain user) {
        return userRepository.save(user);
    }
}
