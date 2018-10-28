package es.upm.miw.SolitarioCelta;

public class DataGateway {

    private static RepositorioPuntuacion puntuacionDb;

    public static RepositorioPuntuacion getPuntuacionDb() {
        return puntuacionDb;
    }

    public static void setPuntuacionDb(RepositorioPuntuacion db) {
        puntuacionDb = db;
    }
}
