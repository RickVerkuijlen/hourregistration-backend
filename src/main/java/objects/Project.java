package objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "project")
public class Project extends ResourceSupport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @Column(name = "implementorId")
    private int implementorId;

   // private Time startTime;

    @JsonIgnore
    @Column(name = "clientId")
    private int clientId;

    @Column(name = "description")
    private String description;

    @Column(name = "lastModified")
    private Date lastModified;

    public Project() {}

    public Project(int id) {
        this.implementorId = id;
        this.code = "20RV1";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImplementor() {
        return implementorId;
    }

    public void setImplementor(int implementorId) {
        this.implementorId = implementorId;
    }

//    public Time getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Time startTime) {
//        this.startTime = startTime;
//    }

    public int getClient() {
        return clientId;
    }

    public void setClient(int clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
