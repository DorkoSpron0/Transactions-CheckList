package com.nicky;

public class UserService implements RegisterUserPort, LoginUserPort{

    private final RegisterUserPort registerUserPort;
    private final LoginUserPort loginUserPort;

    public UserService(RegisterUserPort registerUserPort, LoginUserPort loginUserPort) {
        this.registerUserPort = registerUserPort;
        this.loginUserPort = loginUserPort;
    }

    @Override
    public String loginUser(UserDomain user) {
        return loginUserPort.loginUser(user);
    }

    @Override
    public UserDomain registerUser(UserDomain user) {
        return registerUserPort.registerUser(user);
    }
}
