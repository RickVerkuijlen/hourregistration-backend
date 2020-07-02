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

    public ImplementorDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
