package com.example.danggeunbunny.repository.user;

import com.example.danggeunbunny.model.user.UserRequest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class UserRepository implements JpaRepository<UserRequest, Long> {


    @Override
    public List<UserRequest> findAll() {
        return null;
    }

    @Override
    public List<UserRequest> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserRequest> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserRequest> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserRequest entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserRequest> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserRequest> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserRequest> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserRequest> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserRequest> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserRequest> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserRequest> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserRequest getOne(Long aLong) {
        return null;
    }

    @Override
    public UserRequest getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserRequest> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserRequest> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserRequest> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserRequest> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserRequest> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserRequest> boolean exists(Example<S> example) {
        return false;
    }
}
