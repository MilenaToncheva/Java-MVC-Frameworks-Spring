package softuni.heroes.services.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.heroes.data.models.User;
import softuni.heroes.data.repositories.UsersRepository;
import softuni.heroes.services.models.auth.UserRegisterServiceModel;
import softuni.heroes.services.services.AuthValidationService;

@Service
public class AuthValidationImpl implements AuthValidationService {
    private final UsersRepository usersRepository;
@Autowired
    public AuthValidationImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean isValid(UserRegisterServiceModel userRegisterServiceModel) {
        return this.isEmailValid(userRegisterServiceModel.getEmail()) &&
                this.arePasswordsValid(userRegisterServiceModel.getPassword(), userRegisterServiceModel.getConfirmPassword()) &&
                this.isUsernameFree(userRegisterServiceModel.getUsername());

    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }



    private boolean isEmailValid(String email) {
        //todo
        return true;
    }

    private boolean isUsernameFree(String username) {
        //User user=this.usersRepository.findByUsername(username);
        //return user == null;
return !usersRepository.existsByUsername(username);
    }
}
