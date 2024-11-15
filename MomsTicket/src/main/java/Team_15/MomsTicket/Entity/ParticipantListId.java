package Team_15.MomsTicket.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ParticipantListId implements java.io.Serializable {
    private static final long serialVersionUID = 8625574425690214356L;
    @NotNull
    @Column(name = "userID", nullable = false)
    private Integer userID;

    @NotNull
    @Column(name = "recruitmentID", nullable = false)
    private Integer recruitmentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipantListId entity = (ParticipantListId) o;
        return Objects.equals(this.recruitmentID, entity.recruitmentID) &&
                Objects.equals(this.userID, entity.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recruitmentID, userID);
    }

}