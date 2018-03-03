package com.example.me.sqlitesample;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2015/12/28.
 */
public final class MatchDbContract {
    public MatchDbContract(){}
    public static final String DB_NAME="2";


    public static abstract class StudentsTable implements BaseColumns{
        public static final String TABLE_NAME="students";
        public static final String COLUMN_NAME_S_Rid="S_Rid";
        public static final String COLUMN_NAME_Name="Name";
        public static final String COLUMN_NAME_Student_No="Student_No";
        public static final String COLUMN_NAME_R_date ="R_date";
    }
    public static abstract class ClubsTable implements BaseColumns{
        public static final String TABLE_NAME="clubs";
        public static final String COLUMN_NAME_C_Rid="C_Rid";
        public static final String COLUMN_NAME_Name="Name";
        public static final String COLUMN_NAME_R_date ="R_date";
    }
    public static abstract class Stu_ClubTable implements BaseColumns{
        public static final String TABLE_NAME="stu_club";
        public static final String COLUMN_NAME_Rid="Rid";
        public static final String COLUMN_NAME_S_Rid="S_Rid";
        public static final String COLUMN_NAME_C_Rid ="C_Rid";
        public static final String COLUMN_NAME_R_date ="R_date";
    }


}
