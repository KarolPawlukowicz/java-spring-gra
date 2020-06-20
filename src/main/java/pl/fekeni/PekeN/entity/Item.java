package pl.fekeni.PekeN.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotNull
    @Column(name = "item_name")
    private String itemName;

    @NotNull
    @Column(name = "item_required_level")
    private int requiredLevel;

    @NotNull
    @Column(name = "item_attack_demage")
    private int itemAttackDemage;

    @NotNull
    @Column(name = "item_armor")
    private int itemArmor;

    @NotNull
    @Column(name = "item_type")
    private int itemType;


    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public void setItemAttackDemage(int itemAttackDemage) {
        this.itemAttackDemage = itemAttackDemage;
    }

    public void setItemArmor(int itemArmor) {
        this.itemArmor = itemArmor;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public int getItemAttackDemage() {
        return itemAttackDemage;
    }

    public int getItemArmor() {
        return itemArmor;
    }

    public int getItemType() {
        return itemType;
    }
}
