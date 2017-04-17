package com.testapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by stream on 17.04.17.
 */

public class Advertisement extends RealmObject {
    @PrimaryKey
    public String title;
    public Double price;
    public String content;
    public long createdAt;
}
