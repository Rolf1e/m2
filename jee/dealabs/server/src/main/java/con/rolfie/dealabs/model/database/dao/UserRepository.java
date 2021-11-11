package con.rolfie.dealabs.model.database.dao;

import con.rolfie.dealabs.model.database.entity.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDo, Long> {
}
