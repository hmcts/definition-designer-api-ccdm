package uk.gov.hmcts.ccd.definition.designer.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;


class SecurityUtilsTest {

    @Mock
    private AuthTokenGenerator authTokenGenerator;

    private SecurityUtils classUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        classUnderTest = new SecurityUtils(authTokenGenerator);
    }

    @Test
    void authorizationHeaders() {
        final String s = randomAlphanumeric(32);
        given(authTokenGenerator.generate()).willReturn(s);
        final List<String> headers = classUnderTest.authorizationHeaders().get("ServiceAuthorization");
        assertThat(headers, hasSize(1));
        assertThat(headers.get(0), is(s));
    }
}
