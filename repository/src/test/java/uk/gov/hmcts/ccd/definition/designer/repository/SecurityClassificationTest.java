package uk.gov.hmcts.ccd.definition.designer.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static uk.gov.hmcts.ccd.definition.designer.repository.SecurityClassification.PRIVATE;
import static uk.gov.hmcts.ccd.definition.designer.repository.SecurityClassification.PUBLIC;
import static uk.gov.hmcts.ccd.definition.designer.repository.SecurityClassification.RESTRICTED;

class SecurityClassificationTest {

    @Test
    void leastRestricted() {
        assertFalse(PUBLIC.isMoreRestrictiveThan(PRIVATE));
        assertFalse(PUBLIC.isMoreRestrictiveThan(RESTRICTED));
    }


    @Test
    void privateSecurity() {
        assertTrue(PRIVATE.isMoreRestrictiveThan(PUBLIC));
        assertFalse(PRIVATE.isMoreRestrictiveThan(RESTRICTED));
    }

    @Test
    void mostRestricted() {
        assertTrue(RESTRICTED.isMoreRestrictiveThan(PUBLIC));
        assertTrue(RESTRICTED.isMoreRestrictiveThan(PRIVATE));
    }
}
