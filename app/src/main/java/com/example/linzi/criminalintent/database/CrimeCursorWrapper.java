package com.example.linzi.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by linzi on 2016/3/18.
 */
public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor){
        super(cursor);
    }
}
