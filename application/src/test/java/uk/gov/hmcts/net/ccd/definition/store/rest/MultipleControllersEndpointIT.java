package uk.gov.hmcts.net.ccd.definition.store.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.gov.hmcts.ccd.definition.store.repository.model.UserRole;
import uk.gov.hmcts.net.ccd.definition.store.BaseTest;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static uk.gov.hmcts.ccd.definition.store.repository.SecurityClassification.PRIVATE;
import static uk.gov.hmcts.ccd.definition.store.repository.SecurityClassification.PUBLIC;
import static uk.gov.hmcts.ccd.definition.store.repository.SecurityClassification.RESTRICTED;

public class MultipleControllersEndpointIT extends BaseTest {
    private static final String ROLES_URL = "/api/user-roles/%s";
    private static final String JURISDICTIONS_URL = "/api/data/jurisdictions";

    // To be @Nested - UserRoleController
    @Test
    public void shouldReturnUserRolesForDefinedRoles() throws Exception {
        final String URL = String.format(ROLES_URL, "CaseWorker1,CaseWorker2,CaseWorker3,Nayab,Fatih,Andrzej,Mario");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        final List<UserRole> userRoles = mapper.readValue(result.getResponse().getContentAsString(),
                                                          TypeFactory.defaultInstance().constructType(new TypeReference<List<UserRole>>() {
                                                          }));
        assertAll(
            () -> assertThat(userRoles, hasSize(3)),
            () -> assertThat(userRoles.get(0).getSecurityClassification(), is(PUBLIC)),
            () -> assertThat(userRoles.get(1).getSecurityClassification(), is(PRIVATE)),
            () -> assertThat(userRoles.get(2).getSecurityClassification(), is(RESTRICTED))
        );
    }

    @Test
    public void shouldSaveUserRole() throws Exception {
        final String URL = ROLES_URL.substring(0, ROLES_URL.length() - 4);
        MvcResult result = mockMvc
            .perform(MockMvcRequestBuilders
                .put(URL)
                .contentType("application/json")
                .accept("application/json")
                .content("{\"role\":\"to-delete-1\",\"security_classification\":\"PUBLIC\"}"))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();
        final UserRole userRole = mapper.readValue(result.getResponse().getContentAsString(),
            TypeFactory.defaultInstance().constructType(new TypeReference<UserRole>() {
            }));
        assertAll(
            () -> assertThat(userRole.getSecurityClassification(), is(PUBLIC)),
            () -> assertThat(userRole.getRole(), is("to-delete-1")),
            () -> assertThat(userRole.getId(), is(notNullValue()))
        );
    }

    @Test
    public void shouldReturnNoUserRolesWhenUndefinedRolesQueried() throws Exception {
        final String URL = String.format(ROLES_URL, "Nayab,Fatih,Andrzej,Mario");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        final List<UserRole> userRoles = mapper.readValue(result.getResponse().getContentAsString(),
                                                          TypeFactory.defaultInstance().constructType(new TypeReference<List<UserRole>>() {
                                                          }));
        assertAll(
            () -> assertThat(userRoles, hasSize(0))
        );
    }
}
