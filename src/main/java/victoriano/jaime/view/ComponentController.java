package victoriano.jaime.view;

import victoriano.jaime.modules.TextManagerFiles;
import victoriano.jaime.modules.TextManagerReader;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class ComponentController {

    public static JButton btnSelectDirectory (TextManagerFiles files) {
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

    public static void cbbFilesAvailible(JComboBox cbbFilesAvailible, JTextArea txaFileText, TextManagerFiles files) {
        cbbFilesAvailible.addActionListener(e -> {
            String text = TextManagerReader.read(files.getFile((String) cbbFilesAvailible.getSelectedItem()));
            txaFileText.setText(text);
        });
    }

    public static void txaFileText(JTextArea txaFileText, JComboBox cbbFilesAvailible, TextManagerFiles files) {
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

    public static JButton btnCreateFile(JComboBox cbbFilesAvailible, TextManagerFiles files) {
        JButton btnCreateFile = new JButton("Create a File");
        btnCreateFile.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(btnCreateFile, "File Name:");
            try {
                files.createFile(filename);

                cbbFilesAvailible.setSelectedItem(filename);
            } catch (IOException ex) {
//                TODO DIALOGO DE ERROR
                throw new RuntimeException(ex);
            }
        });
        return btnCreateFile;
    }

    public static JButton btnSelectFile(JComboBox cbbFilesAvailible, JList lstSelectedFiles, TextManagerFiles files) {
        JButton btnCreateFile = new JButton("Add File to Text");
        btnCreateFile.addActionListener(e -> {
            files.selectFile((String) cbbFilesAvailible.getSelectedItem());
            DefaultListModel<String> model = (DefaultListModel<String>) lstSelectedFiles.getModel();
            model.removeAllElements();
            model.addAll(files.getSelectedFilenames());
        });
        return btnCreateFile;
    }
}
