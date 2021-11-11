package con.rolfie.dealabs.model.database.dao;

import con.rolfie.dealabs.model.database.entity.TemperatureDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureDo, Long> {
}
