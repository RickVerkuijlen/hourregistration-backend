package objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Project {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String code;
    private String address;
    private double timeTotal;
    private Implementor implementor;
    private Time startTime;
    private Client contactPerson;

    public Project() {

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

    public double getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(double timeTotal) {
        this.timeTotal = timeTotal;
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

    public Client getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Client contactPerson) {
        this.contactPerson = contactPerson;
    }
}
