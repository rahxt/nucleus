package com.crossedproductions.nucleus.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.crossedproductions.nucleus.model.Drugs;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "dbfile.sqlite";
    public static final String DBLOCATION =  "/data/user/0/com.crossedproductions.nucleus/databases/";

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void openDatabase() {

        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

/*
    //s
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

*/

    public List<Drugs> getListDrugs() {
        Drugs drugs = null;
        List<Drugs> drugsList = new ArrayList<>();
        openDatabase();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Drugs", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            drugs = new Drugs(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
            drugsList.add(drugs);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return drugsList;
    }
}
