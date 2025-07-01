package com.nicky;

public class LoginUserImpl implements LoginUserPort{

    private final UserRepositoryPort userRepositoryPort;

    public LoginUserImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public String loginUser(UserDomain user) {
        return userRepositoryPort.login(user);
    }
}
