package com.example.me.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Administrator on 2015/12/28.
 */
public class StudentClubDbHelper extends SQLiteOpenHelper {

   SQLiteDatabase mDb;


    private static final String CREATE_STUDENTSTBL = "CREATE TABLE " + StudentClubDbContract.StudentsTable.TABLE_NAME + " (" +
            StudentClubDbContract.StudentsTable._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            StudentClubDbContract.StudentsTable.COLUMN_NAME_Name+" TEXT , "+
            StudentClubDbContract.StudentsTable.COLUMN_NAME_Student_No+" TEXT , "+
            StudentClubDbContract.StudentsTable.COLUMN_NAME_R_date+" TEXT"+
            " )";

    private static final String CREATE_CLUBSTBL = "CREATE TABLE " + StudentClubDbContract.ClubsTable.TABLE_NAME + " (" +
            StudentClubDbContract.ClubsTable._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            StudentClubDbContract.ClubsTable.COLUMN_NAME_Name+" TEXT , "+
            StudentClubDbContract.ClubsTable.COLUMN_NAME_R_date+" TEXT"+
            " )";

    private static final String CREATE_STU_CLUBTBL = "CREATE TABLE " + StudentClubDbContract.Stu_ClubTable.TABLE_NAME + " (" +
            StudentClubDbContract.Stu_ClubTable._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            StudentClubDbContract.Stu_ClubTable.COLUMN_NAME_C_Rid+" TEXT , "+
            StudentClubDbContract.Stu_ClubTable.COLUMN_NAME_S_Rid+" TEXT , "+
            StudentClubDbContract.Stu_ClubTable.COLUMN_NAME_R_date+" TEXT"+
            "FOREIGN KEY ("+StudentClubDbContract.Stu_ClubTable.COLUMN_NAME_C_Rid+") REFERENCES"+StudentClubDbContract.ClubsTable.TABLE_NAME+" ("+StudentClubDbContract.ClubsTable._ID+")" +
            "FOREIGN KEY ("+StudentClubDbContract.Stu_ClubTable.COLUMN_NAME_S_Rid+") REFERENCES"+StudentClubDbContract.StudentsTable.TABLE_NAME+" ("+StudentClubDbContract.StudentsTable._ID+")" +
            " )";

    private static final String SQL_DELETE_STUDENTSTBL =
            "DROP TABLE IF EXISTS " + StudentClubDbContract.StudentsTable.TABLE_NAME;
    private static final String SQL_DELETE_CLUBSTBL =
            "DROP TABLE IF EXISTS " + StudentClubDbContract.ClubsTable.TABLE_NAME;
    private static final String SQL_DELETE_STU_CLUBTBL =
            "DROP TABLE IF EXISTS " + StudentClubDbContract.Stu_ClubTable.TABLE_NAME;

    StudentClubDbHelper(Context c, int version) {
        super(c, StudentClubDbContract.DB_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.mDb = db;

        db.execSQL(CREATE_STUDENTSTBL);
        db.execSQL(CREATE_CLUBSTBL);
        db.execSQL(CREATE_STU_CLUBTBL);
    }

    public void insert(String table,ContentValues values) {
        SQLiteDatabase db=getWritableDatabase();
        db.insert(table, null, values);
        close();
    }

    public Cursor query(String table,String[] projection,String selection,String[] selectionArgs,String groupby,String having,String sortorder,String limit) {

        SQLiteDatabase db =getReadableDatabase();
        Cursor cs = db.query(table, projection, selection, selectionArgs, groupby, having, sortorder, limit);

        return cs;
    }

    public Cursor query(String table) {

        SQLiteDatabase db =getReadableDatabase();
        String sql="select * from " + table;
        Cursor cs=db.rawQuery(sql,new String[0]);
        return cs;
    }

    public void delete(String table,String selection,String[] selectionArgs) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(table, selection,selectionArgs);
        close();
    }

    public void update(String table,ContentValues values,String selection,String[] selectionArgs) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(table, values,selection, selectionArgs);
        close();
    }

    public void close() {
        if (mDb != null)
            mDb.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_STUDENTSTBL);
        db.execSQL(SQL_DELETE_CLUBSTBL);
        db.execSQL(SQL_DELETE_STU_CLUBTBL);
        onCreate(db);
    }
}

