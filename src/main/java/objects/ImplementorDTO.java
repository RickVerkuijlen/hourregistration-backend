package objects;

import javax.persistence.*;

@Entity
@Table(name = "implementor")
public class ImplementorDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "implementorId")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "initial")
    private String initial;

    public ImplementorDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
