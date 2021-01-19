package objects;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "salutation")
    private String salutation;

    @Column(name = "password")
    private String password;
}
