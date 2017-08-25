package com.heroku.mapper;

import com.heroku.entity.U_UserEntity;
import com.heroku.entity.U_User_RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface U_UserMapper {

    List<U_UserEntity> getAll();

    U_UserEntity getOne(int id);

    void insert(U_UserEntity o);

    void update(U_UserEntity o);

    void delete(int id);
}
