package com.example.linzi.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linzi.criminalintent.database.CrimeBaseHelper;
import com.example.linzi.criminalintent.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.linzi.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.DATE;
import static com.example.linzi.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.SOLVED;
import static com.example.linzi.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.TITLE;
import static com.example.linzi.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.UUID;

/**
 * Created by linzi on 2016/2/27.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

    }

    public void addCrime(Crime c){
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME,null,values);
    }

    public List<Crime> getCrimes(){
        return new ArrayList<>();
    }
    public Crime getCrime(UUID id){
        return null;
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME,values,UUID + "= ?" ,new String[]{uuidString});
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(UUID,crime.getId().toString());
        values.put(TITLE,crime.getTitle());
        values.put(DATE,crime.getDate().getTime());
        values.put(SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }

    private Cursor queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return cursor;
    }
}
