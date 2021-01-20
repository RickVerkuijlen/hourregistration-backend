package objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "implementor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
