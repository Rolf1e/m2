package con.rolfie.dealabs.model.database.dao;

import con.rolfie.dealabs.model.database.entity.TemperatureDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureDo, Long> {
}
