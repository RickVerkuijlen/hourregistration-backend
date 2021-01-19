package objects;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "folder")
@Data
public class Folder {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
