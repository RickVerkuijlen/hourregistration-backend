package objects;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@Data
public class Project extends ResourceSupport {

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
}
