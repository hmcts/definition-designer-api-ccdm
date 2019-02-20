package uk.gov.hmcts.ccd.definition.designer.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.ccd.definition.designer.repository.entity.JurisdictionEntity;

import java.util.List;
import java.util.Optional;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class VersionedDefinitionRepositoryDecoratorTest {

    public static final String REFERENCE = "Reference";
    private VersionedDefinitionRepositoryDecorator<JurisdictionEntity, Integer> versionedJurisdictionRepository;

    @Mock
    private JurisdictionRepository jurisdictionRepository;

    private JurisdictionEntity jurisdiction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        versionedJurisdictionRepository = new VersionedDefinitionRepositoryDecorator<>(jurisdictionRepository);
        jurisdiction = new JurisdictionEntity();
        jurisdiction.setReference(REFERENCE);
        given(jurisdictionRepository.findLastVersion(REFERENCE)).willReturn(Optional.of(98));
    }

    @Test
    void saveIterable() {
        List<JurisdictionEntity> list = asList(jurisdiction);
        given(jurisdictionRepository.saveAll(list)).willReturn(list);

        final List<JurisdictionEntity> entityList = versionedJurisdictionRepository.saveAll(list);
        assertThat(entityList, hasSize(1));
        assertThat(entityList.get(0).getVersion(), is(99));
        verify(jurisdictionRepository).saveAll(list);
    }
}
