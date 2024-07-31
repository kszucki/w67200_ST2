package pl.kszucki.crmznsurance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kszucki.crmznsurance.Entity.Attachment;

@Repository
public interface AttachemntRepository extends JpaRepository<Attachment, String> {
}
