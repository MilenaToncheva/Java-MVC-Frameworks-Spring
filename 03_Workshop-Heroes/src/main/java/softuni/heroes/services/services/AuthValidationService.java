package softuni.heroes.services.services;

import softuni.heroes.services.models.auth.UserRegisterServiceModel;

public interface AuthValidationService {
    boolean isValid(UserRegisterServiceModel userRegisterServiceModel);
}
