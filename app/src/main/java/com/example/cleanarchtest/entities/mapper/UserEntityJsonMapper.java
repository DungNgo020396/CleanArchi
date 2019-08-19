package com.example.cleanarchtest.entities.mapper;

import com.example.cleanarchtest.entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class UserEntityJsonMapper {
    // chuyển đổi từ chuỗi json sang  {@link UserEntity}.
    private Gson gson = null;

    public UserEntityJsonMapper() {
        this.gson = new Gson();
    }
    public UserEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
        final Type userEntityType = new TypeToken<UserEntity>() {}.getType();
        return this.gson.fromJson(userJsonResponse,userEntityType);
    }
    public List<UserEntity> transformUserEntityCollection(String userJsonRespone){
        final Type userEntityType = new TypeToken<List<UserEntity>>()  {}.getType();
        return this.gson.fromJson(userJsonRespone,userEntityType);
    }
    

}
