package com.example.cleanarchtest.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.cleanarchtest.entities.UserEntity;
import com.example.cleanarchtest.entities.mapper.UserEntityJsonMapper;
import com.example.cleanarchtest.exception.NetworkConnectionException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;

public class RestApiImpl extends RestApi {
    private final Context context;
    private final UserEntityJsonMapper userEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context              {@link android.content.Context}.
     * @param userEntityJsonMapper {@link UserEntityJsonMapper}.
     */
    public RestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        if (context == null || userEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseUserEntities = getUserEntitiesFromApi();
                    if (responseUserEntities != null) {
                        emitter.onNext(userEntityJsonMapper.transformUserEntityCollection(
                                responseUserEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<UserEntity> userEntityById(final int userId) {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseUserDetails = getUserDetailsFromApi(userId);
                    if (responseUserDetails != null) {
                        emitter.onNext(userEntityJsonMapper.transformUserEntity(responseUserDetails));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    private String getUserEntitiesFromApi() throws IOException {
        return ApiConnection.createGET(API_URL_GET_USER_LIST).requestSyncCall();
    }

    private String getUserDetailsFromApi(int userId) throws IOException {
        String apiUrl = API_URL_GET_USER_DETAILS + userId + ".json";
        return ApiConnection.createGET(apiUrl).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}