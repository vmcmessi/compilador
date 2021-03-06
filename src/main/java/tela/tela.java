/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import core.Analisador;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author victo
 */
public class tela extends javax.swing.JFrame {

    private File openedFile;
    private static tela instance;

    /**
     * Creates new form tela
     */
    public tela() {
        initComponents();
        jEditor.setBorder(new NumberedBorder());
        initShortcuts();

        if (instance == null) instance = this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jNew = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSave = new javax.swing.JButton();
        jCopy = new javax.swing.JButton();
        jPaste = new javax.swing.JButton();
        jCut = new javax.swing.JButton();
        jCompile = new javax.swing.JButton();
        jAbout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jMensagens = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 620));

        jPanel2.setMinimumSize(new java.awt.Dimension(850, 550));
        jPanel2.setPreferredSize(new java.awt.Dimension(900, 620));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(255, 51, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(145, 549));
        jPanel1.setPreferredSize(new java.awt.Dimension(145, 500));

        jNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_New_132768.png"))); // NOI18N
        jNew.setText("new [ctrl-n]");
        jNew.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/add-new-document.png"))); // NOI18N
        jNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewActionPerformed();
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_Folder_132707.png"))); // NOI18N
        jButton2.setText("open [ctrl-o]");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed();
            }
        });

        jSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_Save_132607.png"))); // NOI18N
        jSave.setText("save [ctrl-s]");
        jSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveActionPerformed();
            }
        });

        jCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_Copy_132650.png"))); // NOI18N
        jCopy.setText("copy [ctrl-c]");
        jCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCopyActionPerformed();
            }
        });

        jPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_Paste_132732.png"))); // NOI18N
        jPaste.setText("paste [ctrl-v]");
        jPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasteActionPerformed();
            }
        });

        jCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_Cut_132727.png"))); // NOI18N
        jCut.setText("cut [ctrl-x]");
        jCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCutActionPerformed();
            }
        });

        jCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_Go_132632.png"))); // NOI18N
        jCompile.setText("compile [F9]");
        jCompile.setMargin(new java.awt.Insets(1, 14, 1, 14));
        jCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCompileActionPerformed();
            }
        });

        jAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tela/if_Person_132730.png"))); // NOI18N
        jAbout.setText("about [F1]");
        jAbout.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jAbout.setMaximumSize(new java.awt.Dimension(111, 23));
        jAbout.setMinimumSize(new java.awt.Dimension(111, 23));
        jAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAboutActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPaste, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addComponent(jCut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCompile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jNew, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPaste, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jCut, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jCompile, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(700, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 400));
        jScrollPane1.setRequestFocusEnabled(false);

        jEditor.setColumns(20);
        jEditor.setRows(5);
        jScrollPane1.setViewportView(jEditor);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(700, 105));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(750, 105));

        jMensagens.setEditable(false);
        jMensagens.setColumns(20);
        jMensagens.setRows(5);
        jScrollPane2.setViewportView(jMensagens);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setMinimumSize(new java.awt.Dimension(500, 25));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 25));

        jStatus.setMinimumSize(new java.awt.Dimension(500, 25));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAboutActionPerformed() {//GEN-FIRST:event_jAboutActionPerformed
        jMensagens.setText("Christian Passold, Richard Curbani Alfarth e Victor Matheus Cunha");
    }//GEN-LAST:event_jAboutActionPerformed

    private void jCompileActionPerformed() {//GEN-FIRST:event_jCompileActionPerformed
        if(openedFile == null){
            jSaveActionPerformed();
        }else{
            jSaveActionPerformed();
            new Analisador(jMensagens).analisar();
        }
    }//GEN-LAST:event_jCompileActionPerformed

    private void jCutActionPerformed() {//GEN-FIRST:event_jCutActionPerformed
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String text = jEditor.getSelectedText();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
        jEditor.replaceRange("", jEditor.getSelectionStart(), jEditor.getSelectionEnd());
    }//GEN-LAST:event_jCutActionPerformed

    private void jPasteActionPerformed() {//GEN-FIRST:event_jPasteActionPerformed
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipdata = clipboard.getContents(clipboard);
        try {
            if (clipdata.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String s = (String) (clipdata.getTransferData(DataFlavor.stringFlavor));
                jEditor.replaceSelection(s);
            }
        } catch (Exception e) {
            jStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_jPasteActionPerformed

    private void jCopyActionPerformed() {//GEN-FIRST:event_jCopyActionPerformed
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String text = jEditor.getSelectedText();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }//GEN-LAST:event_jCopyActionPerformed

    private void jSaveActionPerformed() {//GEN-FIRST:event_jSaveActionPerformed
        try {
            String text = jEditor.getText();

            jMensagens.setText(null);
            if (openedFile == null || !openedFile.exists()) {
                this.setOpenedFile(TelaUtil.chooserSave(this));
            }

            TelaUtil.writeToFile(openedFile, text);
        } catch (IOException e) {
            Logger.getLogger(tela.class.getName()).log(Level.SEVERE, "Erro ao salvar", e);
        }

    }//GEN-LAST:event_jSaveActionPerformed

    private void jButton2ActionPerformed() {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setCurrentDirectory(new File("."));
        int returnValue = jfc.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            if (selectedFile == null) return;

            this.setOpenedFile(selectedFile);

            BufferedReader readFile = null;
            try {
                jEditor.setText(null);
                jMensagens.setText(null);

                readFile = new BufferedReader(new FileReader(selectedFile));

                String line;
                while ((line = readFile.readLine()) != null) {
                    jEditor.append(line);
                    jEditor.append("\n");
                }

            } catch (IOException ex) {
                Logger.getLogger(tela.class.getName()).log(Level.SEVERE, null, ex);
                jStatus.setText(ex.getMessage());
            } finally {
                if (readFile != null) {
                    try {
                        readFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jNewActionPerformed() {//GEN-FIRST:event_jNewActionPerformed
        jEditor.setText(null);
        jStatus.setText(null);
        jMensagens.setText(null);
        this.setOpenedFile(null);
    }//GEN-LAST:event_jNewActionPerformed

    private void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;

        if (openedFile != null) {
            jStatus.setText(String.valueOf(openedFile.toPath()));
        } else {
            jStatus.setText(null);
        }
    }

    public static String getCodigo() {
        return instance.jEditor.getText() == null ? "" : instance.jEditor.getText();
    }

    public static String getFilenameOpened () {

        if(instance.openedFile == null){
            throw new NullPointerException("Nenhum arquivo aberto");
        }

        return instance.openedFile.getPath();
    }

    private void initShortcuts() {
        KeyStroke keySave = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK);
        KeyStroke keyNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK);
        KeyStroke keyOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
        KeyStroke keyCopy = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK);
        KeyStroke keyPaste = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK);
        KeyStroke keyCut = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK);
        KeyStroke keyCompile = KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0);
        KeyStroke keyAbout = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);

        TelaUtil.createShortcut("new", jNew, keyNew, this::jNewActionPerformed);
        TelaUtil.createShortcut("save", jSave, keySave, this::jSaveActionPerformed);
        TelaUtil.createShortcut("open", jButton2, keyOpen, this::jButton2ActionPerformed);
        TelaUtil.createShortcut("copy", jCopy, keyCopy, this::jCopyActionPerformed);
        TelaUtil.createShortcut("paste", jPaste, keyPaste, this::jPasteActionPerformed);
        TelaUtil.createShortcut("cut", jCut, keyCut, this::jCutActionPerformed);
        TelaUtil.createShortcut("compile", jCompile, keyCompile, this::jCompileActionPerformed);
        TelaUtil.createShortcut("about", jAbout, keyAbout, this::jAboutActionPerformed);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAbout;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jCompile;
    private javax.swing.JButton jCopy;
    private javax.swing.JButton jCut;
    private javax.swing.JTextArea jEditor;
    private javax.swing.JTextArea jMensagens;
    private javax.swing.JButton jNew;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jPaste;
    private javax.swing.JButton jSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jStatus;
    // End of variables declaration//GEN-END:variables
}
