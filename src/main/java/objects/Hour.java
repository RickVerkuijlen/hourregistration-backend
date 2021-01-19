package objects;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "hours")
@Data
public class Hour {

    @Id
    @Column(name = "hourId")
    private int id;

    @Column(name = "projectId")
    private String projectId;

    @Column(name = "userId")
    private int userId;

    @Column(name = "date")
    private Date date;

    @Column(name = "workedHours")
    private float workedHours;
}
