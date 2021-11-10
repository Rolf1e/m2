package con.rolfie.dealabs.model.database.dao;

import con.rolfie.dealabs.model.database.entity.DealDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<DealDo, Long> {
}
