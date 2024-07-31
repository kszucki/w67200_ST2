package pl.kszucki.crmznsurance.Controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kszucki.crmznsurance.Entity.InsPolicy;
import pl.kszucki.crmznsurance.Entity.User;
import pl.kszucki.crmznsurance.Service.PolicyService;
import pl.kszucki.crmznsurance.Service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Controller
public class PolicyController {


    private PolicyService policyService;

    private UserService userService;

    @Autowired
    public PolicyController(PolicyService policyService, UserService userService) {
        super();
        this.policyService = policyService;
        this.userService = userService;
    }

    //More complex, but I think worse version of method below
    /*@GetMapping("/policies")
    public String listPolicies(Model model) {
        List<InsPolicy> policies = policyService.getAllPolicies();
        Map<Long, User> users = new HashMap<>();
        for (InsPolicy policy : policies) {
            users.put(policy.getUser().getIdUsers(), policy.getUser());
        }
        model.addAttribute("policies", policies);
        model.addAttribute("users", users);
        return "policies";
    }*/
    @GetMapping("/policies")
    public String listPolicies(Model model){
            // If no date range is provided, show all policies
            model.addAttribute("policies", policyService.getAllPolicies());
            model.addAttribute("users", userService.getAllUsers());
            return "policies";
    }

    @GetMapping("/policies/searchByDateRange")
    public ResponseEntity<List<InsPolicy>> searchByDateRange(
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate) {

        List<InsPolicy> policies = policyService.searchByDateRange(startDate, endDate); // You might want to filter policies by dates

        return ResponseEntity.ok().body(policies);
    }

    @GetMapping("/policies/searchByDateRange2")
    public ResponseEntity<List<InsPolicy>> searchByDateRange2(
            @RequestParam("Date1") @DateTimeFormat(pattern = "dd-MM-yyyy") String Date1,
            @RequestParam("Date2") @DateTimeFormat(pattern = "dd-MM-yyyy") String Date2) {

        List<InsPolicy> policies = policyService.searchByDateRange2(Date1, Date2); // You might want to filter policies by dates

        return ResponseEntity.ok().body(policies);
    }

/*
    @GetMapping("/policies_filtered")
    public String searchPolicies(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model
    ) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate2 = dateFormat.parse(startDate);
        Date endDate2 = dateFormat.parse(endDate);
        System.out.println(startDate2 +""+ endDate2);

        List<InsPolicy> policies = policyService.searchPoliciesByDate(startDate2, endDate2);

        model.addAttribute("policies", policies);
        return "policies";
    }

*/

//Edit policy
    @GetMapping("/policies/edit/{id}")
    public String editPolicyForm(@PathVariable Long id, Model model){
        model.addAttribute("InsPolicy", policyService.getPolicyById(id));
        return "edit_policy";
    }
    @PostMapping("/policies/{id}")
    public String updatePolicy(@PathVariable Long id, @ModelAttribute("policy") InsPolicy policy){
    InsPolicy existingPolicy = policyService.getPolicyById(id);

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

    return "redirect:/policies";
}


    @GetMapping("/policies/delete/{id}")
    public String deletePolicy(@PathVariable Long id){
        policyService.deletePolicy(id);
        return "redirect:/policies";
    }

   @GetMapping("/policies/search")
   public ResponseEntity<List<InsPolicy>> search(@RequestParam String query) {
     List<InsPolicy> policies = policyService.search(query);
       return ResponseEntity.ok().body(policies);
    }


     //   @GetMapping("/policies/between")
       // public String searchBetween(Model model, @RequestParam Date startDate, @RequestParam Date endDate){
        ///model.addAttribute("policies", policyService.getPolicyBetweenDate(startDate, endDate));
        //model.addAttribute("users", userService.getAllUsers());
        //return "policies_filltered";
        //}

}

