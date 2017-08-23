package com.heroku.mapper;

import com.heroku.entity.UserMasterEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMasterMapper {

    List<UserMasterEntity> getAll();

    UserMasterEntity getOne(String id);

/*    void insert(User_masterEntity user);

    void update(User_masterEntity user);

    void delete(Long id);*/
}
