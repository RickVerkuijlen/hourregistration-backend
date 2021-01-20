package objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "folder")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Folder {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
