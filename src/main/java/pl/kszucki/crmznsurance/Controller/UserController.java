package pl.kszucki.crmznsurance.Controller;



import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kszucki.crmznsurance.Entity.InsPolicy;
import pl.kszucki.crmznsurance.Entity.User;
import pl.kszucki.crmznsurance.Service.PolicyService;
import pl.kszucki.crmznsurance.Service.UserService;

import java.util.List;


@NoArgsConstructor
@Controller
public class UserController{


    @Autowired
    private PolicyService policyService;
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService, PolicyService policyService){
        super();
        this.userService = userService;
        this.policyService = policyService;
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/new")
    public String createUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }
    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user){
        User existingUser = userService.getUserById(id);
        existingUser.setIdUsers(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPesel(user.getPesel());
        existingUser.setAddress(user.getAddress());
        existingUser.setEmail(user.getEmail());

        userService.updateUser(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> search(@RequestParam String query) {
        List<User> policies = userService.search(query);
        return ResponseEntity.ok().body(policies);
    }


}



