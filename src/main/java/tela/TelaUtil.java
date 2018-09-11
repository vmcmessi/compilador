package tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class TelaUtil {

    static void createShortcut(String actionName, JButton button, KeyStroke keyStroke, final Runnable runnable) {

        Action performSave = new AbstractAction(actionName) {
            public void actionPerformed(ActionEvent e) {
                runnable.run();
            }
        };

        button.getActionMap().put("perform" + actionName, performSave);
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "perform" + actionName);
    }


    static void writeToFile(File selectFile, String text){
        try {

            Logger.getLogger(tela.class.getName()).log(Level.SEVERE, String.valueOf(selectFile.canWrite()));
            BufferedWriter bfWrite = new BufferedWriter(new FileWriter(selectFile));
            bfWrite.write(text);
            bfWrite.close();

        } catch (IOException ex) {
            Logger.getLogger(tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
