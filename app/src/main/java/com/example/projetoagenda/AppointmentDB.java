package com.example.projetoagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDB {
    private final SQLiteDatabase mDatabase;

    public AppointmentDB(Context context) {
        mDatabase = new AppointmentDBHelper(context).getWritableDatabase();
    }

    private static ContentValues getValuesContent(Appointment ap) {
        ContentValues values = new ContentValues();

        values.put(AppointmentDBSchema.AppointmentTbl.Cols.DATE, ap.getDate());
        values.put(AppointmentDBSchema.AppointmentTbl.Cols.TIME, ap.getTime());
        values.put(AppointmentDBSchema.AppointmentTbl.Cols.DESCRIPTION, ap.getDescription());
        return values;
    }

    public void addAppointment(Appointment a) {
        ContentValues values = getValuesContent(a);
        mDatabase.insert(AppointmentDBSchema.AppointmentTbl.NAME, null, values);
    }

    String listAppointment(String clauseWhere, String[] argsWhere) {
        Cursor cursor = queryAppointment(clauseWhere, argsWhere);
        StringBuilder stringBuilder = new StringBuilder();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentDBSchema.AppointmentTbl.Cols.DATE));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentDBSchema.AppointmentTbl.Cols.TIME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentDBSchema.AppointmentTbl.Cols.DESCRIPTION));

                if (time != null && !description.isEmpty()) {
                    stringBuilder.append(date).append(" - ").append(time).append(" - ").append(description).append("\n");
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        return stringBuilder.toString();
    }
    public void deleteAppointments() {
        mDatabase.delete(AppointmentDBSchema.AppointmentTbl.NAME, null, null);
    }

    public Cursor queryAppointment(String clauseWhere, String[] argsWhere) {
        return mDatabase.query(
                AppointmentDBSchema.AppointmentTbl.NAME,
                null,
                clauseWhere,
                argsWhere,
                null,
                null,
                null
        );
    }
}
