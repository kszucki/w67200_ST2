package pl.kszucki.crmznsurance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kszucki.crmznsurance.Entity.InsPolicy;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;


public interface PolicyRepository extends JpaRepository<InsPolicy, Long> {

    List<InsPolicy> findAllByUserIdUsers(Long id);
    List<InsPolicy> findByEndDateBetween(Date startDate, Date endDate);

    List<InsPolicy> findByStartDateBetween(Date startDate, Date endDate);

}
