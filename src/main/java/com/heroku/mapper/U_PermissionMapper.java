package com.heroku.mapper;

import com.heroku.entity.U_PermissionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface U_PermissionMapper {

    List<U_PermissionEntity> getAll();

    U_PermissionEntity getOne(int id);

    void insert(U_PermissionEntity o);

    void update(U_PermissionEntity o);

    void delete(int id);
}
