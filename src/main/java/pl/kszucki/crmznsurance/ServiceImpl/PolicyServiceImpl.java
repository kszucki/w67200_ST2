package pl.kszucki.crmznsurance.ServiceImpl;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import pl.kszucki.crmznsurance.Entity.InsPolicy;
import pl.kszucki.crmznsurance.Repository.PolicyRepository;
import pl.kszucki.crmznsurance.Repository.UserRepository;
import pl.kszucki.crmznsurance.Service.PolicyService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor
@Service
public class PolicyServiceImpl implements PolicyService {

    private PolicyRepository policyRepository;

    private UserRepository userRepository;





    @Autowired
    public PolicyServiceImpl(PolicyRepository policyRepository, UserRepository userRepository){
        super();
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<InsPolicy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public InsPolicy savePolicy(InsPolicy insPolicy) {
        return policyRepository.save(insPolicy);
    }

    @Override
    public List<InsPolicy> getAllByUserId(Long Id) {
        return policyRepository.findAllByUserIdUsers(Id);
    }

    @Override
    public InsPolicy getPolicyById(Long id) {
        Optional<InsPolicy> policyOptional = policyRepository.findById(id);
        if(policyOptional.isPresent()) {
            return policyOptional.get();
        } else {
            throw new RuntimeException("Policy with id " + id + " not found");
        }
    }


    @Override
    public InsPolicy updatePolicy(InsPolicy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }

    @Override
    public List<InsPolicy> search(String query) {
        List<InsPolicy> policies = policyRepository.findAll();
        List<InsPolicy> matchingPolicies = new ArrayList<>();

        query = query.toLowerCase(); // convert query to lowercase

        for (InsPolicy policy : policies) {
            // Convert all string fields to lowercase before comparing with query
            if (policy.getInsuranceComp().toLowerCase().contains(query) ||
                    policy.getPolicyNumber().toLowerCase().contains(query) ||
                    policy.getVehicleType().toLowerCase().contains(query) ||
                    policy.getRegNumber().toLowerCase().contains(query) ||
                    policy.getUser().getFirstName().toLowerCase().contains(query) ||
                    policy.getUser().getLastName().toLowerCase().contains(query) ||
                    policy.getUser().getPesel().toLowerCase().contains(query) ||
                    policy.getCoOwnFirstName().toLowerCase().contains(query) ||
                    policy.getCoOwnLastName().toLowerCase().contains(query)) {

                matchingPolicies.add(policy);
            }
        }

        return matchingPolicies;
    }




    @Override
    public List<InsPolicy> getPolicyBetweenDate(Date startDate, Date endDate){
        return policyRepository.findByEndDateBetween(startDate, endDate);
    }

    @Override
    public List<InsPolicy> searchPoliciesByDate(Date startDate, Date endDate) {
        return policyRepository.findByStartDateBetween(startDate, endDate);
    }

    public List<InsPolicy> searchByDateRange(String startDate, String endDate) {
        List<InsPolicy> policies = policyRepository.findAll();
        List<InsPolicy> matchingPolicies = new ArrayList<>();

        // Convert the start and end date strings from dd-MM-yyyy to yyyy-MM-dd format
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        for (InsPolicy policy : policies) {
            Timestamp policyEndDateTimestamp = (Timestamp) policy.getEndDate();
            LocalDate policyEndDate = policyEndDateTimestamp.toLocalDateTime().toLocalDate();

            if (!policyEndDate.isBefore(start) && !policyEndDate.isAfter(end)) {
                matchingPolicies.add(policy);
            }
        }

        return matchingPolicies;
    }

    @Override
    public List<InsPolicy> searchByDateRange2(String Date1, String Date2) {
        List<InsPolicy> policies = policyRepository.findAll();
        List<InsPolicy> matchingPolicies = new ArrayList<>();

        // Convert the start and end date strings from dd-MM-yyyy to yyyy-MM-dd format
        LocalDate start = LocalDate.parse(Date1);
        LocalDate end = LocalDate.parse(Date2);

        for (InsPolicy policy : policies) {
            Timestamp policyInstallmentDateTimestamp = (Timestamp) policy.getInstallmentDate();
            LocalDate policyInstallmentDate = policyInstallmentDateTimestamp.toLocalDateTime().toLocalDate();

            if (!policyInstallmentDate.isBefore(start) && !policyInstallmentDate.isAfter(end)) {
                matchingPolicies.add(policy);
            }
        }

        return matchingPolicies;
    }


}
