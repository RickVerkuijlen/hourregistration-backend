package objects;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
public class ProjectDTO extends ResourceSupport {

    @Id
    @Column(name = "projectId")
    private String code;

    @Column(name = "implementorId")
    private int implementorId;

    @Column(name = "clientId")
    private int clientId;

    @Column(name = "buildAddress")
    private String buildAddress;

    @Column(name = "buildCity")
    private String buildCity;

    @Column(name = "buildZipcode")
    private String buildZipcode;

    @Column(name = "workedHours")
    private float workedHours;

    @Column(name = "description")
    private String description;

    @Column(name = "particularities")
    private String particularities;

    @Column(name = "finances")
    private String finances;

    @Column(name = "finances_extra")
    private String finances_extra;

    @Column(name = "lastModified",
            updatable = false, insertable = false)
    private LocalDateTime lastModified;

    public ProjectDTO() {}

    public ProjectDTO(int id) {
        this.implementorId = id;
        this.code = "20RV1";
    }

    public String getBuildZipcode() {
        return buildZipcode;
    }

    public String getFinances() {
        return finances;
    }

    public void setFinances(String finances) {
        this.finances = finances;
    }

    public String getFinances_extra() {
        return finances_extra;
    }

    public void setFinances_extra(String finances_extra) {
        this.finances_extra = finances_extra;
    }

    public void setBuildZipcode(String buildZipcode) {
        this.buildZipcode = buildZipcode;
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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
