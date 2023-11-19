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

    public List<String> listAppointments(String clausulaWhere, String[] argsWhere) {
        List<String> appointmentsList = new ArrayList<>();
        Cursor cursor = queryAppointment(clausulaWhere, argsWhere);

        if (cursor != null) {
            try {
                int dateIndex = cursor.getColumnIndexOrThrow(AppointmentDBSchema.AppointmentTbl.Cols.DATE);
                int timeIndex = cursor.getColumnIndexOrThrow(AppointmentDBSchema.AppointmentTbl.Cols.TIME);
                int descriptionIndex = cursor.getColumnIndexOrThrow(AppointmentDBSchema.AppointmentTbl.Cols.DESCRIPTION);

                while (cursor.moveToNext()) {
                    String date = cursor.getString(dateIndex);
                    String time = cursor.getString(timeIndex);
                    String description = cursor.getString(descriptionIndex);

                    if (time != null && description != null && !description.isEmpty()) {
                        appointmentsList.add(date + " - " + time + " - " + description);
                    }
                }
            } finally {
                cursor.close();
            }
        }

        return appointmentsList;
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
