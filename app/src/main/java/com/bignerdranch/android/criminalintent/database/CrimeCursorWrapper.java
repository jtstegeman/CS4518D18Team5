package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.Crime;

import java.util.Date;
import java.util.UUID;

import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = "0";
        String title = "Unknown Crime";
        String suspect = "Unknown Suspect";
        long date = 0;
        int isSolved = 0;
        int face = 0;
        try {
            uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
            title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
            date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
            isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
            suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));
            face = getInt(getColumnIndex(CrimeTable.Cols.FACE));
        } catch (Exception e){}

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setFaceDetect(face != 0);
        crime.setSuspect(suspect);

        return crime;
    }
}
