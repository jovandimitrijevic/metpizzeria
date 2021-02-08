package rs.ac.metropolitan.it355.pz.service;

import rs.ac.metropolitan.it355.pz.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> getAllUsers();

    User getUser(int userID);

    User getUserByUsername(String username);

    boolean isUsernameAlreadyInUse(String username);

}
