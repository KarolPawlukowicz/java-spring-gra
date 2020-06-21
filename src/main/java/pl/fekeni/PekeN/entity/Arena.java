package pl.fekeni.PekeN.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "arena")
public class Arena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arena_id")
    private Long id;

    @Column(name = "user_id_to")
    @NotNull
    private Long userIdTo;

    @Column(name = "user_id_from")
    @NotNull
    private Long userIdFrom;

    public Long getUserIdTo() {
        return userIdTo;
    }

    public Long getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdTo(Long userIdTo) {
        this.userIdTo = userIdTo;
    }

    public void setUserIdFrom(Long userIdFrom) {
        this.userIdFrom = userIdFrom;
    }
}
