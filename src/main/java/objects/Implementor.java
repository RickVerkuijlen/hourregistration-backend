package objects;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "implementor")
@Data
public class Implementor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "implementorId")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "initial")
    private String initial;
}
