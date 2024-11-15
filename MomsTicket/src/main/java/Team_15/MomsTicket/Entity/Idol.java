package Team_15.MomsTicket.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Idol {
    @Id
    @Column(name = "idolID", nullable = false)
    private Integer idol_id;

    @Size(max = 50)
    @NotNull
    @Column(name = "idolName", nullable = false, length = 50)
    private String idolName;

}