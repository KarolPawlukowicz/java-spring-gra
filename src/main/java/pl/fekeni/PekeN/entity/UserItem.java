package pl.fekeni.PekeN.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_item")
public class UserItem {

    @Id
    @Column(name = "auth_user_id")
    @NotNull
    private Long authUserId;

    @Column(name = "item_id")
    @NotNull
    private Long itemId;


    public void setAuthUserId(Long authUserId) {
        this.authUserId = authUserId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getAuthUserId() {
        return authUserId;
    }

    public Long getItemId() {
        return itemId;
    }
}
