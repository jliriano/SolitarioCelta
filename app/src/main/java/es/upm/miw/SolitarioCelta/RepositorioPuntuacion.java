package es.upm.miw.SolitarioCelta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static es.upm.miw.SolitarioCelta.PuntuacionContract.PuntuacionEntry;

public class RepositorioPuntuacion extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PuntuacionEntry.TABLE_NAME + " (" +
                    PuntuacionEntry.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PuntuacionEntry.COL_NAME_JUGADOR + " TEXT, " +
                    PuntuacionEntry.COL_NAME_FECHAHORA + " TEXT, " +
                    PuntuacionEntry.COL_NAME_PIEZASTABLERO + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PuntuacionEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = PuntuacionEntry.TABLE_NAME+".db";

    public RepositorioPuntuacion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, PuntuacionEntry.TABLE_NAME);
    }

    public List<Puntuacion> getAll(@Nullable String orderBy) {
        if(!(orderBy == null))
            orderBy = (orderBy.equals("leaderboard")) ?
                    PuntuacionEntry.COL_NAME_PIEZASTABLERO+" ASC"+", "+PuntuacionEntry.COL_NAME_FECHAHORA+" ASC" :
                    null;

        List<Puntuacion> puntuaciones = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PuntuacionEntry.TABLE_NAME,
                null, null, null,
                null, null, orderBy);
        while(cursor.moveToNext()) {
            Puntuacion puntuacion = new Puntuacion(
                    (int) cursor.getLong(cursor.getColumnIndexOrThrow(PuntuacionEntry.COL_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PuntuacionEntry.COL_NAME_JUGADOR)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PuntuacionEntry.COL_NAME_FECHAHORA)),
                    (int)cursor.getLong(cursor.getColumnIndexOrThrow(PuntuacionEntry.COL_NAME_PIEZASTABLERO)));
            puntuaciones.add(puntuacion);
        }
        cursor.close();
        return puntuaciones;
    }

    public void addPuntuacion(String jugador, String fechahora, int piezasTablero) {
        SQLiteDatabase writeDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PuntuacionEntry.COL_NAME_JUGADOR, jugador);
        values.put(PuntuacionEntry.COL_NAME_FECHAHORA, fechahora);
        values.put(PuntuacionEntry.COL_NAME_PIEZASTABLERO, piezasTablero);
        writeDb.insert(PuntuacionEntry.TABLE_NAME, null, values);
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

}
