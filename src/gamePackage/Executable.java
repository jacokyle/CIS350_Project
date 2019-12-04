package gamePackage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

/***********************************************************************
 * The executable class contains the main method which allows the
 * application to be executed.
 *
 * @author (Jeremiah Cerriteno, Kyle Jacobson, Austin Jarema)
 * @version (10 / 25 / 19)
 ***********************************************************************/
public class Executable {

    /******************************************************************
     * Main method that runs the primaryGUI class.
     ******************************************************************/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new PrimaryGUI();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
    }
}
