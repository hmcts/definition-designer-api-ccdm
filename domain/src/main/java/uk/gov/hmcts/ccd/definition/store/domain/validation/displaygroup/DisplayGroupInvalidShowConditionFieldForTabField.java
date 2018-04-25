package uk.gov.hmcts.ccd.definition.store.domain.validation.displaygroup;

import uk.gov.hmcts.ccd.definition.store.domain.validation.ValidationError;
import uk.gov.hmcts.ccd.definition.store.domain.validation.ValidationErrorMessageCreator;
import uk.gov.hmcts.ccd.definition.store.repository.entity.DisplayGroupCaseFieldEntity;

public class DisplayGroupInvalidShowConditionFieldForTabField extends ValidationError {

    private String showConditionField;
    private DisplayGroupCaseFieldEntity displayGroupCaseField;

    public DisplayGroupInvalidShowConditionFieldForTabField(String showConditionField, DisplayGroupCaseFieldEntity displayGroupCaseField) {
        super(
                String.format(
                        "Invalid show condition '%s' for display group case field '%s': unknown field '%s'",
                        displayGroupCaseField.getShowCondition(),
                        displayGroupCaseField.getCaseField().getReference(),
                        showConditionField
                )
        );

        this.showConditionField = showConditionField;
        this.displayGroupCaseField = displayGroupCaseField;
    }

    public DisplayGroupInvalidShowConditionFieldForTabField(DisplayGroupCaseFieldEntity displayGroupCaseField) {
        super(
                String.format(
                        "Unable to parse show condition '%s' for display group '%s'",
                        displayGroupCaseField.getShowCondition(),
                        displayGroupCaseField.getCaseField().getReference()
                )
        );

        this.displayGroupCaseField = displayGroupCaseField;
    }

    @Override
    public String createMessage(ValidationErrorMessageCreator validationErrorMessageCreator) {
        return validationErrorMessageCreator.createErrorMessage(this);
    }

    public String getShowConditionField() {
        return showConditionField;
    }

    public DisplayGroupCaseFieldEntity getDisplayGroupCaseField() {
        return displayGroupCaseField;
    }
}
