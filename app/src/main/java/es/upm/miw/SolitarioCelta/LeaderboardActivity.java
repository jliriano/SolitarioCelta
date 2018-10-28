package es.upm.miw.SolitarioCelta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        showLeaderbaord(DataGateway.getPuntuacionDb().getAll("leaderboard"));
    }

    private void showLeaderbaord(List<Puntuacion> puntuaciones) {
        LeaderboardListAdapter adapter = new LeaderboardListAdapter(this, R.layout.leaderboard_item_view, puntuaciones);

        ListView leaderboardList = findViewById(R.id.leaderbaordListView);
        leaderboardList.setAdapter(adapter);
    }

    public void borrarTodo(View v) {
        Button b = (Button) v;

        if(b.getText().equals(getString(R.string.resetLeaderboard))){
            Bundle alertDialogArgs = new Bundle();
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            alertDialogArgs.putInt("TITLE", R.string.borrarLeaderboard);
            alertDialogArgs.putInt("MESSAGE", R.string.borrarLeaderboardMsg);
            alertDialogFragment.setArguments(alertDialogArgs);
            alertDialogFragment.show(getFragmentManager(),"ALERT DIALOG");
        }
    }

}
