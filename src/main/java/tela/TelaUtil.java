package tela;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    static void writeToFile(File selectFile, String text) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(selectFile);
            fileOutputStream.write(text.getBytes());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static File chooserSave(Component component) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Salvar");

        if(chooser.showSaveDialog(component) == JFileChooser.APPROVE_OPTION){
            return chooser.getSelectedFile();
        }

        return null;
    }
}
