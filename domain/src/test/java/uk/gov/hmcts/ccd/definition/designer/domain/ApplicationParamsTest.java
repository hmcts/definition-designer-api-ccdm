package uk.gov.hmcts.ccd.definition.designer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

class ApplicationParamsTest {

    private static final String IDAM_HOST_URL = "idamHostUrl";
    private ApplicationParams classUnderTest = new ApplicationParams();

    @BeforeEach
    void setUp() {
        final Field idamHostField = ReflectionUtils.findField(ApplicationParams.class, "idamHost");
        assertNotNull(idamHostField);
        ReflectionUtils.makeAccessible(idamHostField);
        ReflectionUtils.setField(idamHostField, classUnderTest, IDAM_HOST_URL);
    }

    @DisplayName("Gets IDAM User Profile URL")
    @Test
    void idamUserProfileURL() {
        assertThat(classUnderTest.idamUserProfileURL(), is(IDAM_HOST_URL + "/details"));
    }

}
