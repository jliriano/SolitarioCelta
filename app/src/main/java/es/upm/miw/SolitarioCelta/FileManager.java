package es.upm.miw.SolitarioCelta;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

public class FileManager {

    private static final String FILE_NAME = "savedGame";
    private File file;

    public FileManager(Context context) {
        file = new File(context.getFilesDir(),FILE_NAME);
        try {
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGame(String tablero) {
        try {
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(tablero.getBytes());
            fos.write('\n');
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSavedGame() {
        String savedGame = null;
        try {
            BufferedReader fis = new BufferedReader(new FileReader(file));
            savedGame = fis.readLine();
            fis.close();
        } catch (FileNotFoundException e) {
            return savedGame;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedGame;
    }

    public boolean hasSavedGame() {
        String savedGame = getSavedGame();
        return (savedGame!=null);
    }

}
