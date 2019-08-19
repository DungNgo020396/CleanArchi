package com.example.cleanarchtest.responsitory;

import android.database.Observable;

import java.util.List;

public class UserDataResponsitory implements  UserResponsitory {
    @Override
    public Observable<List<User>> users() {
        return null;
    }

    @Override
    public Observable<User> user(int userId) {
        return null;
    }
    //
}
