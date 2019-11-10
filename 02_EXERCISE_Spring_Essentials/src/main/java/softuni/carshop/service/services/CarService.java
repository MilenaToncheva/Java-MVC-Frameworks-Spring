package softuni.carshop.service.services;

import softuni.carshop.service.models.CarServiceModel;

import java.util.List;

public interface CarService {
    CarServiceModel save(CarServiceModel carServiceModel);
    List<CarServiceModel> getAllCars();
}
