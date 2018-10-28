package es.upm.miw.SolitarioCelta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class LeaderboardListAdapter extends ArrayAdapter<Puntuacion> {

    private Context mContext;
    private int rowLayout;
    private List<Puntuacion> puntuaciones;

    public LeaderboardListAdapter(@NonNull Context context, int rowLayout, List<Puntuacion> puntuaciones) {
        super(context, rowLayout, puntuaciones);
        mContext = context;
        this.rowLayout = rowLayout;
        this.puntuaciones = puntuaciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout leaderboardRow = (LinearLayout) convertView;

        if(leaderboardRow==null) {
            LayoutInflater inflador = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            leaderboardRow = (LinearLayout) inflador.inflate(rowLayout, null, true);
        }

        Puntuacion currentPuntuacion = puntuaciones.get(position);
        TextView rank = leaderboardRow.findViewById(R.id.leadbItemRank);
        TextView jugador = leaderboardRow.findViewById(R.id.leadbItemPlayer);
        TextView fechahora = leaderboardRow.findViewById(R.id.leadbItemFechahora);
        TextView fichasFinal = leaderboardRow.findViewById(R.id.leadbItemFichasFinales);

        String ranking = "#"+(position+1);
        rank.setText(ranking);
        jugador.setText(currentPuntuacion.getJugador());
        fechahora.setText(currentPuntuacion.getFechaHora());
        fichasFinal.setText(String.valueOf(currentPuntuacion.getPiezasTablero()));

        return leaderboardRow;
    }

}
