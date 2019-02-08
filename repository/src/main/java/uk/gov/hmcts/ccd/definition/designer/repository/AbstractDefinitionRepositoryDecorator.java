package uk.gov.hmcts.ccd.definition.designer.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

abstract class AbstractDefinitionRepositoryDecorator<T, I extends Serializable, R extends DefinitionRepository<T, I>>
    implements DefinitionRepository<T, I> {

    protected R repository;

    protected AbstractDefinitionRepositoryDecorator(R repository) {
        this.repository = repository;
    }

    @Override
    public <S extends T> S save(S s) {
        return repository.save(s);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> iterable) {
        return repository.save(iterable);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<T> findAll(Iterable<I> iterable) {
        return repository.findAll(iterable);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return repository.count(example);
    }

    @Override
    public void delete(I id) {
        repository.delete(id);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        repository.delete(iterable);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public T findOne(I id) {
        return repository.findOne(id);
    }

    @Override
    public <S extends T> S findOne(Example<S> example) {
        return repository.findOne(example);
    }

    @Override
    public boolean exists(I id) {
        return repository.exists(id);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return repository.exists(example);
    }

    @Override
    public void flush() {
        repository.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return repository.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {
        repository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    @Override
    public T getOne(I id) {
        return repository.getOne(id);
    }
}
