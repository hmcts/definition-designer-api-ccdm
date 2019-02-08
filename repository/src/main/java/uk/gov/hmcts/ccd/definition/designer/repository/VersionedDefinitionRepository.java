package uk.gov.hmcts.ccd.definition.designer.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface VersionedDefinitionRepository<T, I extends Serializable> extends DefinitionRepository<T, I> {

    Optional<Integer> findLastVersion(String reference);

    Optional<T> findFirstByReferenceOrderByVersionDesc(String reference);

}
