package edu.miu.cs.cs425.eregistrar.service;

import edu.miu.cs.cs425.eregistrar.model.User;

import java.util.List;

public interface UserService {
    public abstract List<User> getAllUsers();
    public abstract User saveUser(User user);
    public User login(String username, String password);
    public abstract User getUserById(Integer userId);
}
