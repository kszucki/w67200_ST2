package pl.kszucki.crmznsurance.Service;


import pl.kszucki.crmznsurance.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Long id);
    User updateUser(User user);

    void deleteUser(Long id);

    Optional<User> getUserByIdOptional(Long id);

    List<User> search(String query);
}


