package es.upm.miw.SolitarioCelta;

import android.provider.BaseColumns;

public final class PuntuacionContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private PuntuacionContract() {}

    /* Inner class that defines the table contents */
    public static class PuntuacionEntry implements BaseColumns {
        public static final String TABLE_NAME = "puntuaciones";
        public static final String COL_NAME_ID = BaseColumns._ID;
        public static final String COL_NAME_JUGADOR = "jugador";
        public static final String COL_NAME_FECHAHORA = "fechahora";
        public static final String COL_NAME_PIEZASTABLERO = "piezastablero";
    }
}
