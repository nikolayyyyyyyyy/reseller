package app.condition.model;
import app.offer.model.Offer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conditions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private ConditionType conditionType;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "condition")
    private List<Offer> offers;
}
