package pl.kszucki.crmznsurance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kszucki.crmznsurance.Entity.InsPolicy;
import pl.kszucki.crmznsurance.Entity.User;
import pl.kszucki.crmznsurance.Service.PolicyService;
import pl.kszucki.crmznsurance.Service.UserService;

import java.util.Optional;


//Controller class to make connected requests for both Policy and User
@Controller
public class UsersPolicyController {
    @Autowired
    private PolicyService policyService;
    @Autowired
    private UserService userService;

    @Autowired
    public UsersPolicyController(UserService userService, PolicyService policyService){
        super();
        this.userService = userService;
        this.policyService = policyService;
    }
    @GetMapping("/users/{id}/policies")
    public String getPolicies(@PathVariable Long id, Model model){
        model.addAttribute("InsPolicy", policyService.getAllByUserId(id));
        model.addAttribute("user", userService.getUserById(id));
        return "user_policies";
    }

    @GetMapping("/users/{id}/policy")
    public String createPolicyForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("InsPolicy", new InsPolicy());
        return "create_policy";
    }

    @PostMapping("users/{userId}/policy")
    public String addPolicyToUser(@PathVariable Long userId, @ModelAttribute("InsPolicy") InsPolicy policy) {
        Optional<User> userOptional = userService.getUserByIdOptional(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            policy.setUser(user);
            user.getPolicies().add(policy);
            userService.saveUser(user);
            return "redirect:/policies";
        } else {
            throw new RuntimeException("User with id " + userId + " not found");
        }
    }

    //edit user's policy
    @GetMapping("/users/{userId}/policy/{policyId}")
    public String editPolicyForm(@PathVariable Long userId, @PathVariable Long policyId, Model model){
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("InsPolicy", policyService.getPolicyById(policyId));
        return "edit_user_policy";
    }

    @PostMapping("/users/{userId}/policy/{policyId}")
    public String updatePolicy(@PathVariable Long userId, @PathVariable Long policyId, @ModelAttribute("InsPolicy") InsPolicy policy){
        Optional<User> userOptional = userService.getUserByIdOptional(userId);
        if(userOptional.isPresent()) {
            InsPolicy existingPolicy = policyService.getPolicyById(policyId);

            User existingUser = userService.getUserById(existingPolicy.getUser().getIdUsers());
            System.out.println("User id: " + existingUser.getIdUsers());

            existingPolicy.setCoOwnFirstName(policy.getCoOwnFirstName());
            existingPolicy.setCoOwnLastName(policy.getCoOwnLastName());
            existingPolicy.setPolicyNumber(policy.getPolicyNumber());
            existingPolicy.setUser(existingUser);
            existingPolicy.setInsuranceComp(policy.getInsuranceComp());
            existingPolicy.setInstallmentDate(policy.getInstallmentDate());
            existingPolicy.setStartDate(policy.getStartDate());
            existingPolicy.setEndDate(policy.getEndDate());
            existingPolicy.setVehicleType(policy.getVehicleType());
            existingPolicy.setDiscount(policy.getDiscount());
            existingPolicy.setRegNumber(policy.getRegNumber());



            userService.updateUser(existingUser);
            policyService.updatePolicy(existingPolicy);
            return "redirect:/users/{userId}/policies";
        } else {
            throw new RuntimeException("User with id " + userId + " not found");
        }
    }


}
