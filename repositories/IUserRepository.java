package repositories;

import model.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> getUserByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void removeUser(User user);
}
