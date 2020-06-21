package pl.fekeni.PekeN.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_user_id")
    private Long id;

    @NotNull(message="First name is compulsory")
    @Column(name = "first_name")
    private String name;

    @NotNull(message="Last name is compulsory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message="Email is compulsory")
    @Email(message = "Email is invalid")
    @Column(name = "email")
    private String email;

    @NotNull(message="Password is compulsory")
    @Length(min=5, message="Password should be at least 5 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "challange")
    private Long challange;

    @NotNull(message="Nick name is compulsory")
    @Length(min=5, message="Nick name should be at least 5 characters")
    @Column(name = "nick_name")
    private String nickName;

    @NotNull
    @Column(name = "lvl")
    private int lvl;

    @NotNull
    @Column(name = "xp")
    private int xp;

    @NotNull
    @Column(name = "money")
    private int money;

    @NotNull
    @Column(name = "strength")
    private int strength;

    @NotNull
    @Column(name = "dexterity")
    private int dexterity;

    @NotNull
    @Column(name = "intelligence")
    private int intelligence;

    @NotNull
    @Column(name = "health")
    private int health;



    @NotNull
    @Column(name = "health_points")
  //  @Formula("100 + health * 20")
    private int healthPoints;

    @NotNull
    @Column(name = "current_health")
    private int currentHealth;


    @NotNull
    @Column(name = "armor")
   // @Formula("20 + dexterity * 2 + intelligence")
    private int armor;

    @NotNull
    @Column(name = "DMG")
 //   @Formula("5 + strength * 2 + dexterity")
    private int DMG;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;

  /*  @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_item", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }*/

    public void setChallange(Long challange) {
        this.challange = challange;
    }

    public Long getChallange() {
        return challange;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setDMG(int DMG) {
        this.DMG = DMG;
    }

    public int getXp() {
        return xp;
    }

    public int getMoney() {
        return money;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getHealth() {
        return health;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getArmor() {
        return armor;
    }

    public int getDMG() {
        return DMG;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    public void incrementStat(String stat){
        if(stat.equals("strength")) {
            this.strength++;
        } else if(stat.equals("dexterity")) {
            this.dexterity++;
        } else if(stat.equals("intelligence")) {
            this.intelligence++;
        } else if(stat.equals("health")) {
            this.health++;
        } else {
            System.out.println("nie dodano");
        }
    }

    public void decreaseGold(int howMuch){
        this.money-=howMuch;
    }

    public void increaseGold(int howMuch){
        this.money+=howMuch;
    }

    public void increaseExp(int howMuch){
        System.out.println("exp");

        this.xp+=howMuch;
        if(this.xp >= 10){
            System.out.println("lvl up");
            this.lvl++;
            this.xp = this.xp%10;
        }
    }

    public void decreaseHP(int howMuch){
        this.currentHealth-=howMuch;
    }

    public void increaseHP(int howMuch){
        this.currentHealth+=howMuch;
    }


}