package app.condition.init;

import app.condition.model.ConditionType;
import app.condition.service.ConditionService;
import app.web.dto.ConditionRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedCondition implements CommandLineRunner {
    private final ConditionService conditionService;

    public SeedCondition(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.conditionService.getAllConditions().isEmpty()){

            this.conditionService.createCondition(ConditionRequest
                    .builder()
                    .conditionType(ConditionType.ACCEPTABLE)
                    .description("The item is fairly worn but continues to function properly")
                    .build());

            this.conditionService.createCondition(ConditionRequest
                    .builder()
                    .conditionType(ConditionType.EXCELLENT)
                    .description("In perfect condition")
                    .build());

            this.conditionService.createCondition(ConditionRequest
                    .builder()
                    .conditionType(ConditionType.GOOD)
                    .description("Some signs of wear and tear or minor defects")
                    .build());
        }
    }
}
