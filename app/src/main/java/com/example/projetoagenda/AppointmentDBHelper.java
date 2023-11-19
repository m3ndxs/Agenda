package com.example.projetoagenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class AppointmentDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME_DB = "appointmentDB";

    public AppointmentDBHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + AppointmentDBSchema.AppointmentTbl.NAME +
                "(" + "_id integer PRIMARY KEY autoincrement,"
                + AppointmentDBSchema.AppointmentTbl.Cols.DATE + ","
                + AppointmentDBSchema.AppointmentTbl.Cols.TIME + ","
                + AppointmentDBSchema.AppointmentTbl.Cols.DESCRIPTION + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + AppointmentDBSchema.AppointmentTbl.NAME);
        onCreate(db);
    }
}
