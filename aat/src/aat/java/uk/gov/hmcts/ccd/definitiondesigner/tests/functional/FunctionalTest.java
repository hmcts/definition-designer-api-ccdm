package uk.gov.hmcts.ccd.definitiondesigner.tests.functional;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.ccd.definitiondesigner.tests.AATHelper;
import uk.gov.hmcts.ccd.definitiondesigner.tests.BaseTest;

import java.util.function.Supplier;

public class FunctionalTest extends BaseTest {

    private Supplier<RequestSpecification> asUser = asAutoTestCaseworker();

    protected FunctionalTest(AATHelper aat) {
        super(aat);
    }

    @Test
    @DisplayName("TO DO")
    void todo() {
        asUser.get()
            .given()
            .when();
    }
}
