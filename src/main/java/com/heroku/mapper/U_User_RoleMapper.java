package com.heroku.mapper;

import com.heroku.entity.U_RoleEntity;
import com.heroku.entity.U_User_RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface U_User_RoleMapper {

    List<U_User_RoleEntity> getAll();

    U_User_RoleEntity getOne(int id);

    void insert(U_User_RoleEntity o);

    void update(U_User_RoleEntity o);

    void delete(int id);
}
