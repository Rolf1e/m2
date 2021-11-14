package con.rolfie.dealabs.model.database.dao;

import con.rolfie.dealabs.model.database.entity.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDo, Long> {

    Optional<UserDo> findByFirstName(final String firstName);
}
