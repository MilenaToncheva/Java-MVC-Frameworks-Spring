package softuni.heroes.services.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.heroes.data.models.User;
import softuni.heroes.data.repositories.UsersRepository;
import softuni.heroes.services.models.auth.UserLoginServiceModel;
import softuni.heroes.services.models.auth.UserRegisterServiceModel;
import softuni.heroes.services.services.AuthService;
import softuni.heroes.services.services.AuthValidationService;
import softuni.heroes.services.services.HashingService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService authValidationService;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;

    @Autowired
    public AuthServiceImpl(AuthValidationService authValidationService, UsersRepository usersRepository, ModelMapper modelMapper, HashingService hashingService) {
        this.authValidationService = authValidationService;
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        //validate email,matching password, the user already exists
        //create or throw
        if (!authValidationService.isValid(userRegisterServiceModel)) {
            // do something
            return;
        }

        User user = this.modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(this.hashingService.hash(userRegisterServiceModel.getPassword()));
        this.usersRepository.save(user);

    }

    @Override
    public UserLoginServiceModel login(UserLoginServiceModel userLoginServiceModel) throws Exception {
        String passwordHashed = this.hashingService.hash(userLoginServiceModel.getPassword());
        Optional<User> userOptional = this.usersRepository.findByUsernameAndPassword(userLoginServiceModel.getUsername(), passwordHashed);

        if (!userOptional.isPresent()) {
            throw new Exception("Invalid user");
        }
        User user = userOptional.get();

        UserLoginServiceModel userLoginServiceModel1 = this.modelMapper.map(userOptional.get(), UserLoginServiceModel.class);
        userLoginServiceModel.setPassword(null);//in order not ot keep the password in the session
        if (user.getHero() == null) {
            userLoginServiceModel.setHeroName(null);
        } else {
            userLoginServiceModel.setHeroName(user.getHero().getName());
        }

        return userLoginServiceModel;
    }
}

