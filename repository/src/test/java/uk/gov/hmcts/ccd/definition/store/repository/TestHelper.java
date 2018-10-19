package uk.gov.hmcts.ccd.definition.store.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.ccd.definition.store.repository.entity.*;

@Component
public class TestHelper {

    private final VersionedDefinitionRepositoryDecorator<JurisdictionEntity, Integer> versionedJurisdictionRepository;
    private final VersionedDefinitionRepositoryDecorator<FieldTypeEntity, Integer> versionedTypeRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public TestHelper(JurisdictionRepository jurisdictionRepository,
                      FieldTypeRepository fieldTypeRepository,
                      UserRoleRepository userRoleRepository) {
        versionedJurisdictionRepository = new VersionedDefinitionRepositoryDecorator<>(jurisdictionRepository);
        versionedTypeRepository = new VersionedDefinitionRepositoryDecorator<>(fieldTypeRepository);
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

    public FieldTypeEntity createType(JurisdictionEntity jurisdiction) {
        final FieldTypeEntity fieldType = new FieldTypeEntity();
        fieldType.setReference("SomeType");
        fieldType.setMinimum("2");
        fieldType.setMaximum("4");
        fieldType.setJurisdiction(jurisdiction);
        return versionedTypeRepository.save(fieldType);
    }

    public CaseFieldEntity buildCaseField(final String reference,
                                          final FieldTypeEntity fieldType,
                                          final String label,
                                          final Boolean hidden) {
        final CaseFieldEntity caseFieldEntity = new CaseFieldEntity();
        caseFieldEntity.setReference(reference);
        caseFieldEntity.setFieldType(fieldType);
        caseFieldEntity.setLabel(label);
        caseFieldEntity.setHidden(hidden);
        caseFieldEntity.setSecurityClassification(SecurityClassification.PUBLIC);
        return caseFieldEntity;
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

    public EventCaseFieldEntity createEventCaseField(CaseFieldEntity caseField, DisplayContext displayContext, String showCondition, Boolean ssco) {
        EventCaseFieldEntity ecf = new EventCaseFieldEntity();
        ecf.setCaseField(caseField);
        ecf.setDisplayContext(displayContext);
        ecf.setShowCondition(showCondition);
        ecf.setShowSummaryChangeOption(ssco);
        return ecf;
    }

}
