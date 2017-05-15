package com.course.example.zooresolver;

import android.net.Uri;

public final class Animal {

    public static final String AUTHORITY = "com.course.animal";
    public static final String PATH_MULTIPLE = "animals";
    public static final Uri CONTENT_URI = Uri.parse("content://" + Animal.AUTHORITY + "/" + Animal.PATH_MULTIPLE);

    public static final String DEFAULT_SORT_ORDER = "updated DESC";

    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";
   
}
