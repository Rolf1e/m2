package con.rolfie.dealabs.model.database.dao;

import con.rolfie.dealabs.model.database.entity.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDo, Long> {
}
