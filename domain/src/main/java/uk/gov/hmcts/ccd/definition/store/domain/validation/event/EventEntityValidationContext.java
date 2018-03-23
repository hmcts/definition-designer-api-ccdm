package uk.gov.hmcts.ccd.definition.store.domain.validation.event;

import uk.gov.hmcts.ccd.definition.store.domain.validation.ValidationContext;
import uk.gov.hmcts.ccd.definition.store.repository.SecurityClassification;
import uk.gov.hmcts.ccd.definition.store.repository.entity.CaseTypeEntity;

public class EventEntityValidationContext implements ValidationContext {

    private final SecurityClassification parentSecurityClassification;

    private final String caseName;

    private final String caseReference;

    public EventEntityValidationContext(CaseTypeEntity parentCaseType) {
        this.caseName = parentCaseType.getName();
        this.caseReference = parentCaseType.getReference();
        this.parentSecurityClassification = parentCaseType.getSecurityClassification();
    }

    public SecurityClassification getParentSecurityClassification() {
        return this.parentSecurityClassification;
    }

    public String getCaseName() {
        return this.caseName;
    }

    public String getCaseReference() {
        return this.caseReference;
    }

}
