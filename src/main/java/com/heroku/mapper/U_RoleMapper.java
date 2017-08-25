package com.heroku.mapper;

import com.heroku.entity.U_RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface U_RoleMapper {

    List<U_RoleEntity> getAll();

    U_RoleEntity getOne(int id);

    void insert(U_RoleEntity o);

    void update(U_RoleEntity o);

    void delete(int id);
}
