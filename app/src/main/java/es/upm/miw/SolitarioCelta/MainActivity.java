package es.upm.miw.SolitarioCelta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

	JuegoCelta mJuego;
    private final String CLAVE_TABLERO = "TABLERO_SOLITARIO_CELTA";
    private final String ALERT_TAG = "ALERT DIALOG";
    private static final String TITLE_TAG = "TITLE";
    private static final String MESSAGE_TAG = "MESSAGE";
    private static final String FINISH_MODE = "FINISH";
    private static final String RESET_MODE = "RESET";
    private static final String SAVEGAME_MODE = "SAVEGAME";
    private static final String RESTOREGAME_MODE = "RESTOREGAME";
    private AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
    private int gameFinishTitle;
    private int gameFinishMessage;
    private int gameResetTitle;
    private int gameResetMessage;
    private int gameSaveTitle;
    private int gameSaveMessage;
    private int gameRestoreTitle;
    private int gameRestoreMessage;
    private FileManager fileManager;
    private RepositorioPuntuacion puntuacionDb;

	private final int[][] ids = {
		{       0,        0, R.id.p02, R.id.p03, R.id.p04,        0,        0},
        {       0,        0, R.id.p12, R.id.p13, R.id.p14,        0,        0},
        {R.id.p20, R.id.p21, R.id.p22, R.id.p23, R.id.p24, R.id.p25, R.id.p26},
        {R.id.p30, R.id.p31, R.id.p32, R.id.p33, R.id.p34, R.id.p35, R.id.p36},
        {R.id.p40, R.id.p41, R.id.p42, R.id.p43, R.id.p44, R.id.p45, R.id.p46},
        {       0,        0, R.id.p52, R.id.p53, R.id.p54,        0,        0},
        {       0,        0, R.id.p62, R.id.p63, R.id.p64,        0,        0}
	};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameFinishTitle = R.string.txtDialogoFinalTitulo;
        gameFinishMessage = R.string.txtDialogoFinalPregunta;
        gameResetTitle = R.string.txtDialogoReiniciarTitulo;
        gameResetMessage = R.string.txtDialogoReiniciarPregunta;
        gameSaveTitle = R.string.txtDialogoGuardarJuegoTitulo;
        gameSaveMessage = R.string.txtDialogoGuardarJuegoMensaje;
        gameRestoreTitle = R.string.txtDialogoRecuperarJuegoTitulo;
        gameRestoreMessage = R.string.txtDialogoRecuperarJuegoMensaje;
        fileManager = new FileManager(getApplicationContext());

        puntuacionDb = new RepositorioPuntuacion(getApplicationContext());

        DataGateway.setPuntuacionDb(puntuacionDb);

        mJuego = new JuegoCelta();
        mostrarTablero();

    }

    /**
     * Se ejecuta al pulsar una posición
     *
     * @param v Vista de la posición pulsada
     */
    public void posicionPulsada(View v) {
        String resourceName = getResources().getResourceEntryName(v.getId());
        int i = resourceName.charAt(1) - '0';
        int j = resourceName.charAt(2) - '0';

        mJuego.jugar(i, j);

        mostrarTablero();
        if (mJuego.juegoTerminado()) {
            savePuntuacion();
            setupAlert(FINISH_MODE);
            alertDialogFragment.show(getFragmentManager(),ALERT_TAG);
        }
    }

    public void savePuntuacion() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        puntuacionDb.addPuntuacion(
                sharedPrefs.getString(getString(R.string.prefPlayerNameKey), "Dalí"),
                fechaHora.format(cal.getTime()),
                mJuego.getPiezasTablero());
    }

    public void reiniciarPartida() {
        setupAlert(RESET_MODE);
        alertDialogFragment.show(getFragmentManager(), ALERT_TAG);
    }

    public void guardarPartida() {
        setupAlert(SAVEGAME_MODE);
        alertDialogFragment.show(getFragmentManager(), ALERT_TAG);
    }

    public void recuperarPartida() {
        if(fileManager.hasSavedGame()) {
            setupAlert(RESTOREGAME_MODE);
            alertDialogFragment.show(getFragmentManager(), ALERT_TAG);
        } else {
            Toast.makeText(this, "No tienes partidas guardadas.", Toast.LENGTH_LONG).show();
        }
    }

    public void saveGame() {
        String tablero = mJuego.serializaTablero();
        fileManager.saveGame(tablero);
        Toast.makeText(this, "El juego ha sido guardado!", Toast.LENGTH_LONG).show();
    }

    public void resetGame() {
        mJuego.reiniciar();
        mostrarTablero();
        Toast.makeText(this, "Tablero reiniciado", Toast.LENGTH_LONG).show();
    }

    public void recoverGame() {
        mJuego.deserializaTablero(fileManager.getSavedGame());
        mostrarTablero();
        Toast.makeText(this, "Partida guardada ha sido recuperada!", Toast.LENGTH_LONG).show();
    }

    public void setupAlert(String mode) {
        Bundle alertDialogArgs;
        switch(mode) {
            case RESET_MODE:
                alertDialogArgs = new Bundle();
                alertDialogFragment = new AlertDialogFragment();
                alertDialogArgs.putInt(TITLE_TAG, gameResetTitle);
                alertDialogArgs.putInt(MESSAGE_TAG, gameResetMessage);
                alertDialogFragment.setArguments(alertDialogArgs);
                break;
            case FINISH_MODE:
                alertDialogArgs = new Bundle();
                alertDialogFragment = new AlertDialogFragment();
                alertDialogArgs.putInt(TITLE_TAG, gameFinishTitle);
                alertDialogArgs.putInt(MESSAGE_TAG, gameFinishMessage);
                alertDialogFragment.setArguments(alertDialogArgs);
                break;
            case SAVEGAME_MODE:
                alertDialogArgs = new Bundle();
                alertDialogFragment = new AlertDialogFragment();
                alertDialogArgs.putInt(TITLE_TAG, gameSaveTitle);
                alertDialogArgs.putInt(MESSAGE_TAG, gameSaveMessage);
                alertDialogFragment.setArguments(alertDialogArgs);
                break;
            case RESTOREGAME_MODE:
                alertDialogArgs = new Bundle();
                alertDialogFragment = new AlertDialogFragment();
                alertDialogArgs.putInt(TITLE_TAG, gameRestoreTitle);
                alertDialogArgs.putInt(MESSAGE_TAG, gameRestoreMessage);
                alertDialogFragment.setArguments(alertDialogArgs);
                break;
            default:
                break;
        }
    }

    /**
     * Visualiza el tablero
     */
    public void mostrarTablero() {
        RadioButton button;

        for (int i = 0; i < JuegoCelta.TAMANIO; i++)
            for (int j = 0; j < JuegoCelta.TAMANIO; j++)
                if (ids[i][j] != 0) {
                    button = findViewById(ids[i][j]);
                    button.setChecked(mJuego.obtenerFicha(i, j) == 1);
                }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(CLAVE_TABLERO, mJuego.serializaTablero());
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String grid = savedInstanceState.getString(CLAVE_TABLERO);
        mJuego.deserializaTablero(grid);
        mostrarTablero();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reiniciarPartida:
                reiniciarPartida();
                return true;
            case R.id.guardarPartida:
                guardarPartida();
                return true;
            case R.id.recuperarPartida:
                recuperarPartida();
                return true;
            case R.id.menuAbout:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.launchLeaderboard:
                Intent leaderboardIntent = new Intent(this, LeaderboardActivity.class);
                startActivity(leaderboardIntent);
                return true;
            case R.id.preferences:
                startActivity(new Intent(this, SCeltaPreferences.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
