package softuni.heroes.services.services;

import softuni.heroes.services.models.auth.UserLoginServiceModel;
import softuni.heroes.services.models.auth.UserRegisterServiceModel;

public interface AuthService {
    void register(UserRegisterServiceModel userRegisterServiceModel);

    UserLoginServiceModel login(UserLoginServiceModel userLoginServiceModel) throws Exception;
}
