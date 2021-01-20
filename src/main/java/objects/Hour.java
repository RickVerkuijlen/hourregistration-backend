package objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "hours")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
