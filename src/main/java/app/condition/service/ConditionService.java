package app.condition.service;

import app.condition.model.Condition;
import app.condition.model.ConditionType;
import app.condition.repository.ConditionRepository;
import app.exception.DomainException;
import app.web.dto.ConditionRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ConditionService {

    private final ConditionRepository repository;

    public ConditionService(ConditionRepository repository) {
        this.repository = repository;
    }

    public void createCondition(ConditionRequest conditionRequest){
        if(this.repository.findByConditionType(conditionRequest.getConditionType()) != null){

            throw new DomainException("Condition %s already exist".formatted(conditionRequest.getConditionType()));
        }

        Condition condition = Condition.builder()
                .conditionType(conditionRequest.getConditionType())
                .description(conditionRequest.getDescription())
                .build();

        this.repository.save(condition);
    }

    public Set<Condition> getAllConditions(){
        if(this.repository.findAll().isEmpty()){
            return new HashSet<>();
        }
        return new HashSet<>(this.repository.findAll());
    }

    public Condition getConditionByConditionType(ConditionType conditionType){
        return this.repository.findByConditionType(conditionType);
    }
}
