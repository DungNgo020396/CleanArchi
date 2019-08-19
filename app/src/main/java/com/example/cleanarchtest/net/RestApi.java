package com.example.cleanarchtest.net;


import com.example.cleanarchtest.entities.UserEntity;
import java.util.List;
import io.reactivex.Observable;
public abstract class RestApi {
    String API_BASE_URL = "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/";
    String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";
    String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";
     abstract Observable<List<UserEntity>> userEntityList();
     abstract Observable<UserEntity> userEntityById(final int userId);
}
