package pl.kszucki.crmznsurance.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import pl.kszucki.crmznsurance.Entity.User;




//Not necessary @Repository because it is included in SimpleJpaRepository class


//
public interface UserRepository extends JpaRepository<User,Long> {
}
