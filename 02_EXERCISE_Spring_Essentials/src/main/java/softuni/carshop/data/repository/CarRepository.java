package softuni.carshop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carshop.data.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
}
