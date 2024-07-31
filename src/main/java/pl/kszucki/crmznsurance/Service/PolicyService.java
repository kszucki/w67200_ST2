package pl.kszucki.crmznsurance.Service;

import pl.kszucki.crmznsurance.Entity.InsPolicy;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PolicyService {
    List<InsPolicy> getAllPolicies();

    InsPolicy savePolicy(InsPolicy insPolicy);

    List<InsPolicy> getAllByUserId(Long Id);

    InsPolicy getPolicyById(Long id);

    InsPolicy updatePolicy(InsPolicy policy);

    void deletePolicy(Long id);

    List<InsPolicy> search(String query);

    List<InsPolicy> getPolicyBetweenDate(Date startDate, Date endDate);

    List<InsPolicy> searchPoliciesByDate(Date startDate, Date endDate);

    public List<InsPolicy> searchByDateRange(String startDate, String endDate);

    public List<InsPolicy> searchByDateRange2(String Date1, String Date2);

}
