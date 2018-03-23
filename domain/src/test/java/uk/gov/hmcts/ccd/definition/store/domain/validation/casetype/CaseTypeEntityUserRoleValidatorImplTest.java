package uk.gov.hmcts.ccd.definition.store.domain.validation.casetype;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import uk.gov.hmcts.ccd.definition.store.domain.validation.ValidationResult;
import uk.gov.hmcts.ccd.definition.store.repository.entity.CaseTypeEntity;
import uk.gov.hmcts.ccd.definition.store.repository.entity.CaseTypeUserRoleEntity;
import uk.gov.hmcts.ccd.definition.store.repository.entity.UserRoleEntity;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class CaseTypeEntityUserRoleValidatorImplTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private CaseTypeEntityUserRoleValidatorImpl validator;

    private CaseTypeEntity caseType;

    private CaseTypeUserRoleEntity caseTypeUserRole;

    @Mock
    private UserRoleEntity userRole;

    @Before
    public void setup() {

        validator = new CaseTypeEntityUserRoleValidatorImpl();

        caseTypeUserRole = new CaseTypeUserRoleEntity();

        caseType = new CaseTypeEntity();
        caseType.setReference("case type");
        caseType.addCaseTypeUserRole(caseTypeUserRole);
    }

    @Test
    public void shouldHaveValidationError_whenUserNotFound() {

        final ValidationResult result = validator.validate(caseType);

        assertThat(result.getValidationErrors().size(), is(1));
        assertThat(result.getValidationErrors().get(0), instanceOf(CaseTypeEntityInvalidUserRoleValidationError.class));
        assertThat(result.getValidationErrors().get(0).getDefaultMessage(), is(
            "Invalid UserRole is not defined for case type 'case type'"));
    }

    @Test
    public void shouldHaveNoValidationError_whenUserFound() {

        caseTypeUserRole.setUserRole(userRole);

        final ValidationResult result = validator.validate(caseType);

        assertThat(result.getValidationErrors(), empty());
        assertThat(caseType.getCaseTypeUserRoleEntities().get(0).getUserRole(), is(userRole));
    }
}
