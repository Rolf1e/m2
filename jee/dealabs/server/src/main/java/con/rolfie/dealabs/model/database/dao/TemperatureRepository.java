package con.rolfie.dealabs.model.database.dao;

import con.rolfie.dealabs.model.database.entity.DealDo;
import con.rolfie.dealabs.model.database.entity.TemperatureDo;
import con.rolfie.dealabs.model.database.entity.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureDo, Long> {

    Optional<TemperatureDo> findByDealAndUser(final DealDo deal, final UserDo user);

}
