package objects;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hours")
public class HourDTO {

    @Id
    @Column(name = "hourId")
    private int id;

    @Column(name = "projectId")
    private String projectId;

    @Column(name = "date")
    private Date date;

    @Column(name = "workedHours")
    private float workedHours;

    public HourDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(float workedHours) {
        this.workedHours = workedHours;
    }
}
