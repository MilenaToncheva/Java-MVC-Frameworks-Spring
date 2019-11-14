package softuni.heroes.data.models;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="heroes")
public class Hero  extends BaseEntity{
private String name;
private Gender gender;
private Integer level;
private Integer stamina;
private Integer strength;
private Integer attack;
private Integer defence;
private List<Item>items;
    private User user;

    public Hero() {
    }
@Column(name="name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Enumerated(EnumType.STRING)
    @Column(name="gender",nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    @Column(name="level",nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    @Column(name="stamina",nullable = false)
    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }
    @Column(name="strength",nullable = false)
    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }
    @Column(name="attack",nullable = false)
    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }
    @Column(name="defence",nullable = false)
    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }
@ManyToMany()
@JoinTable(name = "heroes_items",
joinColumns=@JoinColumn(name = "hero_id",referencedColumnName = "id"),
inverseJoinColumns = @JoinColumn(name = "item_id",referencedColumnName = "id"))
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @OneToOne()
@JoinColumn(name="user_id",referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}



