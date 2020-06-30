package objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "project")
public class ProjectDTO extends ResourceSupport {

    @Id
    @Column(name = "projectId")
    private String code;

    @JsonIgnore
    @Column(name = "implementorId")
    private int implementorId;

    @JsonIgnore
    @Column(name = "clientId")
    private int clientId;

    @Column(name = "buildAddress")
    private String buildAddress;

    @Column(name = "buildCity")
    private String buildCity;

    @Column(name = "workedHours")
    private float workedHours;

    @Column(name = "description")
    private String description;

    @Column(name = "particularities")
    private String particularities;

    @Column(name = "lastModified")
    private Date lastModified;

    public ProjectDTO() {}

    public ProjectDTO(int id) {
        this.implementorId = id;
        this.code = "20RV1";
    }

    public String getBuildAddress() {
        return buildAddress;
    }

    public void setBuildAddress(String buildAddress) {
        this.buildAddress = buildAddress;
    }

    public String getBuildCity() {
        return buildCity;
    }

    public void setBuildCity(String buildCity) {
        this.buildCity = buildCity;
    }

    public float getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(float workedHours) {
        this.workedHours = workedHours;
    }

    public String getParticularities() {
        return particularities;
    }

    public void setParticularities(String particularities) {
        this.particularities = particularities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getImplementorId() {
        return implementorId;
    }

    public void setImplementorId(int implementorId) {
        this.implementorId = implementorId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
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
