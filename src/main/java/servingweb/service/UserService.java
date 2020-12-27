package servingweb.service;

import servingweb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User userChanges);

    User getUserById(Long id);

    void deleteUser(User user);

    User getUserByUsername(String firstname);

    void dropPassword(User user);

}


