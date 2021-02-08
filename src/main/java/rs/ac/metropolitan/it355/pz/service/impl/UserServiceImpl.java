package rs.ac.metropolitan.it355.pz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355.pz.model.User;
import rs.ac.metropolitan.it355.pz.repository.UserRepository;
import rs.ac.metropolitan.it355.pz.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int userID) {
        Optional<User> userResult = userRepository.findById(userID);
        User user = null;
        if (userResult.isPresent()) {
            user = userResult.get();
        } else {
            throw new RuntimeException("There is no user with id: " + userID);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUsernameAlreadyInUse(String username) {
        System.out.println(userRepository.findByUsername(username) + " - Username is available");
        return userRepository.findByUsername(username) != null;
    }
}
