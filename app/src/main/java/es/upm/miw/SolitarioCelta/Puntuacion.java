package es.upm.miw.SolitarioCelta;

import android.os.Parcel;
import android.os.Parcelable;

public class Puntuacion implements Parcelable {

    private int id;
    private String jugador;
    private String fechaHora;
    private int piezasTablero;

    public Puntuacion(Parcel in) {
        id = in.readInt();
        jugador = in.readString();
        fechaHora = in.readString();
        piezasTablero = in.readInt();
    }

    public Puntuacion(int id, String jugador, String fechaHora, int piezasTablero) {
        this.id = id;
        this.jugador = jugador;
        this.fechaHora = fechaHora;
        this.piezasTablero = piezasTablero;
    }

    public static final Creator<Puntuacion> CREATOR = new Creator<Puntuacion>() {
        @Override
        public Puntuacion createFromParcel(Parcel in) {
            return new Puntuacion(in);
        }

        @Override
        public Puntuacion[] newArray(int size) {
            return new Puntuacion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(jugador);
        dest.writeString(fechaHora);
        dest.writeInt(piezasTablero);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getPiezasTablero() {
        return piezasTablero;
    }

    public void setPiezasTablero(int piezasTablero) {
        this.piezasTablero = piezasTablero;
    }

    @Override
    public String toString() {
        return "Puntuacion{" +
                "id=" + id +
                ", jugador='" + jugador + '\'' +
                ", fechaHora='" + fechaHora + '\'' +
                ", piezasTablero=" + piezasTablero +
                '}';
    }
}
