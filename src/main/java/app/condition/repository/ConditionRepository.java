package app.condition.repository;

import app.condition.model.Condition;
import app.condition.model.ConditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, UUID> {

    public Condition findByConditionType(ConditionType conditionType);
}
