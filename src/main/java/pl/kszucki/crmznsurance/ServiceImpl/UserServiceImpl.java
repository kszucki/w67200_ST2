package pl.kszucki.crmznsurance.ServiceImpl;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kszucki.crmznsurance.Entity.User;
import pl.kszucki.crmznsurance.Repository.UserRepository;
import pl.kszucki.crmznsurance.Service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User with id " + id + " not found");
        }
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserByIdOptional(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> search(String query) {
        List<User> users = userRepository.findAll();
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getFirstName().toLowerCase().contains(query.toLowerCase()) ||
                    user.getLastName().toLowerCase().contains(query.toLowerCase()) ||
                    user.getPesel().toLowerCase().contains(query.toLowerCase()) ||
                    user.getAddress().toLowerCase().contains(query.toLowerCase()) ||
                    user.getEmail().toLowerCase().contains(query.toLowerCase())) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }


}
