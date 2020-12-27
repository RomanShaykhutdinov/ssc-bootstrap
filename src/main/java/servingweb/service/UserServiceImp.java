package servingweb.service;

import servingweb.model.Role;
import servingweb.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servingweb.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User userChanges) {
        userChanges.setPassword(bCryptPasswordEncoder.encode(userChanges.getPassword()));
        userChanges.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(userChanges);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public User getUserByUsername(String firstname) {
        return userRepository.getUserByUsername(firstname);
    }

    @Transactional
    @Override
    public void dropPassword(User user) { //принудительно сбрасывает пароль при изменении юзера
        user.setPassword(null);
    }
}


