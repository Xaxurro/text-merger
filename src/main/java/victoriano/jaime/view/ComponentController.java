package victoriano.jaime.view;

import victoriano.jaime.modules.TextManager;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class ComponentController {

    public static JButton btnSelectDirectory (TextManager files) {
        JButton btnSelectDirectory = new JButton("Select Directory");
        btnSelectDirectory.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(btnSelectDirectory);

            if (result == JFileChooser.APPROVE_OPTION) {
                File newDirectory = fileChooser.getSelectedFile();
                files.setDirectory(newDirectory);
//                TODO REFRESH
            }
        });

        return btnSelectDirectory;
    }

    public static void cbbFilesAvailible(JComboBox cbbFilesAvailible, JTextArea txaFileText, TextManager files) {
        cbbFilesAvailible.addActionListener(e -> {
            String text = files.read(files.getFile((String) cbbFilesAvailible.getSelectedItem()));
            txaFileText.setText(text);
        });
        cbbFilesAvailible.setSelectedIndex(0);
    }

    public static void txaFileText(JTextArea txaFileText, JComboBox cbbFilesAvailible, TextManager files) {
        txaFileText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                File file = files.getFile((String) cbbFilesAvailible.getSelectedItem());
                try {
                    FileWriter fw = new FileWriter(file);
                    fw.write(txaFileText.getText());
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static JButton btnCreateFile(JComboBox cbbFilesAvailible, TextManager files) {
        JButton btnCreateFile = new JButton("Create a File");
        btnCreateFile.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(btnCreateFile, "File Name:");
            try {
                files.createFile(filename);
                cbbFilesAvailible.setModel(files.getTextFilenameModel());
                cbbFilesAvailible.setSelectedItem(filename);
            } catch (IOException ex) {
//                TODO DIALOGO DE ERROR
                throw new RuntimeException(ex);
            }
        });
        return btnCreateFile;
    }

    public static JButton btnSelectFile(JComboBox cbbFilesAvailible, JList lstSelectedFiles, TextManager files) {
        JButton btnCreateFile = new JButton("Add File to Text");
        btnCreateFile.addActionListener(e -> {
            files.selectFile((String) cbbFilesAvailible.getSelectedItem());
        });
        return btnCreateFile;
    }

    public static JButton btnGenerateText(TextManager files) {
        JButton btnGenerateText = new JButton("Generate Text");
        btnGenerateText.addActionListener(e -> {
            String generatedText = files.generateText();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(generatedText);
            clipboard.setContents(selection, selection);
        });
        return btnGenerateText;
    }
}
