package objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDate;

@Entity
public class Project extends ResourceSupport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String code;
    private String address;
    @JsonIgnore
    private Implementor implementor;
    private Time startTime;
    @JsonIgnore
    private Client client;
    private String description;
    private LocalDate lastEdit;

    public Project() {
        this.implementor = new Implementor();
        this.implementor.setId(1);
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

    public Implementor getImplementor() {
        return implementor;
    }

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }
}
