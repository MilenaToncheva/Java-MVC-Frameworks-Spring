package softuni.carshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.carshop.data.models.Car;
import softuni.carshop.data.repository.CarRepository;
import softuni.carshop.service.models.CarServiceModel;
import softuni.carshop.service.services.CarService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(ModelMapper modelMapper, CarRepository carRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }

    @Override
    public CarServiceModel save(CarServiceModel carServiceModel) {
        this.carRepository.save(this.modelMapper.map(carServiceModel,Car.class));

         return carServiceModel;
    }

    @Override
    public List<CarServiceModel> getAllCars() {
        return Arrays.stream(this.modelMapper.map(this.carRepository.findAll(), CarServiceModel[].class)).collect(Collectors.toList());
    }
}
