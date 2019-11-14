package softuni.heroes.services.models.auth;

import softuni.heroes.data.models.Hero;

public class UserLoginServiceModel {
    private String username;
    private String password;
    private String  heroName;

    public UserLoginServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
