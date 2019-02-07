package uk.gov.hmcts.ccd.definition.designer.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.ccd.definition.designer.repository.entity.DefinitionEntity;
import uk.gov.hmcts.ccd.definition.designer.repository.entity.DefinitionStatus;
import uk.gov.hmcts.ccd.definition.designer.repository.entity.JurisdictionEntity;
import uk.gov.hmcts.ccd.definition.designer.repository.entity.UserRoleEntity;

import java.io.IOException;

@Component
public class TestHelper {

    private final VersionedDefinitionRepositoryDecorator<JurisdictionEntity, Integer> versionedJurisdictionRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public TestHelper(JurisdictionRepository jurisdictionRepository,
                      UserRoleRepository userRoleRepository) {
        versionedJurisdictionRepository = new VersionedDefinitionRepositoryDecorator<>(jurisdictionRepository);
        this.userRoleRepository = userRoleRepository;
    }

    public JurisdictionEntity createJurisdiction() {
        return createJurisdiction("jurisdiction", "name", "desc");
    }

    public JurisdictionEntity createJurisdiction(String reference, String name, String desc) {
        final JurisdictionEntity jurisdiction = new JurisdictionEntity();
        jurisdiction.setReference(reference);
        jurisdiction.setName(name);
        jurisdiction.setDescription(desc);
        return versionedJurisdictionRepository.save(jurisdiction);
    }

    public UserRoleEntity createUserRole(final String reference,
                                         final String name,
                                         final SecurityClassification sc) {
        final UserRoleEntity entity = new UserRoleEntity();
        entity.setReference(reference);
        entity.setName(name);
        entity.setSecurityClassification(sc);
        return userRoleRepository.save(entity);
    }

    public DefinitionEntity buildDefinition(final JurisdictionEntity jurisdiction,
                                            final String description,
                                            final DefinitionStatus status) throws IOException {
        final DefinitionEntity definitionEntity = new DefinitionEntity();
        definitionEntity.setJurisdiction(jurisdiction);
        definitionEntity.setDescription(description);
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode data = mapper.readTree("{\"FieldX\": \"ValueX\", \"FieldZ\": []}");
        definitionEntity.setData(data);
        definitionEntity.setAuthor("lrmgc2gp7g@example.com");
        definitionEntity.setDeleted(false);
        definitionEntity.setStatus(status);
        return definitionEntity;
    }

    public DefinitionEntity buildDefinition(final JurisdictionEntity jurisdiction,
                                            final String description) throws IOException {
        final DefinitionEntity definitionEntity = new DefinitionEntity();
        definitionEntity.setJurisdiction(jurisdiction);
        definitionEntity.setDescription(description);
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode data = mapper.readTree("{\"Field1\": \"Value1\", \"Field2\": []}");
        definitionEntity.setData(data);
        definitionEntity.setAuthor("ccd@hmcts");
        definitionEntity.setDeleted(false);
        return definitionEntity;
    }
}
