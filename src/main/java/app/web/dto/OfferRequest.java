package app.web.dto;
import app.condition.model.ConditionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OfferRequest {
    @Size(min = 2,max = 50,message = "Description must be between 2 and 50 characters.")
    private String description;

    @Positive
    private BigDecimal price;

    @NotNull
    private ConditionType condition;
}
