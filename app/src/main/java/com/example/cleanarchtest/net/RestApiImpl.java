package com.example.cleanarchtest.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.cleanarchtest.entities.UserEntity;
import com.example.cleanarchtest.entities.mapper.UserEntityJsonMapper;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RestApiImpl extends RestApi {
    private Context context;
    private UserEntityJsonMapper userEntityJsonMapper;
    public RestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        this.context=context;
        this.userEntityJsonMapper=userEntityJsonMapper;

    }
    // check network:
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    @Override
    Observable<List<UserEntity>> userEntityList() {

        return Observable.create(new ObservableOnSubscribe<List<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserEntity>> emitter) throws Exception {
                if(isThereInternetConnection()){
                    String responseUserDetails = getUserDetailsFromApi(userId);

                }
            }
        });

    }

    @Override
    Observable<UserEntity> userEntityById(int userId) {
        return null;
    }
    private String getUserEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_USER_LIST).requestSyncCall();
    }
}
