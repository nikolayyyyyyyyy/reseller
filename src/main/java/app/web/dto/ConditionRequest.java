package app.web.dto;

import app.condition.model.ConditionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ConditionRequest {
    private ConditionType conditionType;
    private String description;
}
