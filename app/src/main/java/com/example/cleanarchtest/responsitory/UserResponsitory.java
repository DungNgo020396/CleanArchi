package com.example.cleanarchtest.responsitory;

import android.database.Observable;

import java.util.List;

public interface UserResponsitory {
    Observable<List<User>> users();
    Observable<User> user(final int userId);

}
