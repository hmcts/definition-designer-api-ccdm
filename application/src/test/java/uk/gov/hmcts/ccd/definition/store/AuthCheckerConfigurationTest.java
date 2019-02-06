package uk.gov.hmcts.ccd.definition.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class AuthCheckerConfigurationTest {

    @Mock
    private HttpServletRequest request;

    private AuthCheckerConfiguration configuration;

    @Before
    public void setup() {
        configuration = new AuthCheckerConfiguration();
    }

    @Test
    public void shouldReturnEmptyCollectionWhenUserIdExtractorIsInvoked() {
        assertThat(configuration.userIdExtractor().apply(request), is(Optional.empty()));
    }

    @Test
    public void shouldReturnServiceCollectionWhenAuthorizedServicesExtractorIsInvoked() {
        final List<String> authorisedServices = Arrays.asList("s1", "s2", "s3");
        ReflectionTestUtils.setField(configuration, "authorisedServices", authorisedServices);
        final Collection<String> result = configuration.authorizedServicesExtractor().apply(request);
        assertThat(result, hasSize(3));
        assertThat(result, hasItems("s1", "s2", "s3"));
    }
}
