package ro.teamnet.zthbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.teamnet.zthbo.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
