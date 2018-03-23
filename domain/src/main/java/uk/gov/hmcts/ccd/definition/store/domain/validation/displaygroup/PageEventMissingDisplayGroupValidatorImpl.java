package uk.gov.hmcts.ccd.definition.store.domain.validation.displaygroup;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.ccd.definition.store.domain.validation.ValidationResult;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupEntity;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupType;

@Component
public class PageEventMissingDisplayGroupValidatorImpl implements DisplayGroupValidator {

    @Override
    public ValidationResult validate(DisplayGroupEntity displayGroupEntity) {
        ValidationResult validationResult = new ValidationResult();
        if (displayGroupEntity.getType() == DisplayGroupType.PAGE && displayGroupEntity.getEvent() == null) {
            validationResult.addError(new EventEntityMissingForPageTypeDisplayGroupError(displayGroupEntity));
        }

        return validationResult;
    }
}
