package uk.gov.hmcts.ccd.definition.designer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.gov.hmcts.ccd.definition.designer.domain.ApplicationParams;
import uk.gov.hmcts.ccd.definition.designer.excel.UserRoleSetup;
import uk.gov.hmcts.ccd.definition.designer.repository.SecurityUtils;
import uk.gov.hmcts.ccd.definition.designer.rest.model.IdamProperties;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
    DefinitionDesignerApiApplication.class,
    TestConfiguration.class
})
@TestPropertySource(locations = "classpath:test.properties")
public abstract class BaseTest {

    @Inject
    protected DataSource db;

    @Inject
    protected WebApplicationContext wac;

    @Inject
    private ApplicationParams applicationParams;

    protected MockMvc mockMvc;
    protected JdbcTemplate jdbcTemplate;
    protected Map<String, Integer> userRoleIds;

    protected static final ObjectMapper mapper = new ObjectMapper();

    @Rule  // The @Rule 'wireMockRule' must be public
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort());

    @BeforeClass
    public static void init() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        jdbcTemplate = new JdbcTemplate(db);
        final Integer port = wireMockRule.port();
        final SecurityUtils securityUtils = mock(SecurityUtils.class);
        when(securityUtils.authorizationHeaders()).thenReturn(new HttpHeaders());

        final IdamProperties idamProperties = new IdamProperties();
        idamProperties.setId("445");
        idamProperties.setEmail("user@hmcts.net");

        userRoleIds = new UserRoleSetup(jdbcTemplate).addUserRoleTestData();
    }
}
