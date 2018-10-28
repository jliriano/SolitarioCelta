package es.upm.miw.SolitarioCelta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment {

    private MainActivity main;
    private LeaderboardActivity leaderboard;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    final Activity activity = getActivity();
		final String dialogTitle = getString(getArguments().getInt("TITLE"));
		final String dialogMessage = getString(getArguments().getInt("MESSAGE"));
		final String yesBtnLabel = getString(R.string.txtDialogoFinalAfirmativo);
		final String noBtnLabel = getString(R.string.txtDialogoFinalNegativo);

		if(activity.getClass().equals(MainActivity.class)) {
		    main = (MainActivity) activity;
        } else if(activity.getClass().equals(LeaderboardActivity.class)) {
		    leaderboard = (LeaderboardActivity) activity;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle(dialogTitle)
                .setMessage(dialogMessage)
                .setPositiveButton(
                        yesBtnLabel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dialogTitle.equals(getString(R.string.txtDialogoFinalTitulo))
                                        || dialogTitle.equals(getString(R.string.txtDialogoReiniciarTitulo))) {
                                    main.resetGame();
                                } else if(dialogTitle.equals(getString(R.string.txtDialogoGuardarJuegoTitulo))) {
                                    main.saveGame();
                                } else if(dialogTitle.equals(getString(R.string.txtDialogoRecuperarJuegoTitulo))) {
                                    main.recoverGame();
                                } else if(dialogTitle.equals(getString(R.string.borrarLeaderboard))) {
                                    DataGateway.getPuntuacionDb().deleteAll();
                                    leaderboard.finish();
                                }
                            }
                        }
                )
                .setNegativeButton(
                        noBtnLabel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dialogTitle.equals(getString(R.string.txtDialogoFinalTitulo))) {
                                    main.finish();
                                } else if(dialogTitle.equals(getString(R.string.txtDialogoReiniciarTitulo)) ||
                                        dialogTitle.equals(getString(R.string.txtDialogoGuardarJuegoTitulo)) ||
                                        dialogTitle.equals(getString(R.string.txtDialogoRecuperarJuegoTitulo)) ||
                                        dialogTitle.equals(getString(R.string.borrarLeaderboard))) {
                                    dismiss();
                                }
                            }
                        }
                );

		return builder.create();
	}
}
