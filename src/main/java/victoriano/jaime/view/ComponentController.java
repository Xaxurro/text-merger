package victoriano.jaime.view;

import victoriano.jaime.modules.TextManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class ComponentController {
    TextManager files;
    JTextField txtFileSearcher;
    JList<String> lstFilesAvailible;
    JTextArea txaFileText;
    JList<String> lstSelectedFiles;

    public ComponentController(TextManager files, JTextField txtFileSearcher, JList<String> lstFilesAvailible, JTextArea txaFileText, JList<String> lstSelectedFiles) {
        this.files = files;
        this.txtFileSearcher = txtFileSearcher;
        this.lstFilesAvailible = lstFilesAvailible;
        this.txaFileText = txaFileText;
        this.lstSelectedFiles = lstSelectedFiles;
    }

    public void txtFileSearcher() {
        txtFileSearcher.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String search = txtFileSearcher.getText();
                files.setTextFilenameSearch(search);
                refresh();
            }
        });
    }

    public JButton btnSelectDirectory () {
        JButton btnSelectDirectory = new JButton("Select Directory");
        btnSelectDirectory.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(btnSelectDirectory);

            if (result == JFileChooser.APPROVE_OPTION) {
                File newDirectory = fileChooser.getSelectedFile();
                files.setDirectory(newDirectory);
                refresh();
            }
        });

        return btnSelectDirectory;
    }

    public void lstFilesAvailible() {
        lstFilesAvailible.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lstFilesAvailible.getSelectedValue() == null) {
                    return;
                }
                String text = files.read(files.getFile(lstFilesAvailible.getSelectedValue()));
                txaFileText.setText(text);
            }
        });
        refresh();
    }

    public void txaFileText() {
        txaFileText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                File file = files.getFile(lstFilesAvailible.getSelectedValue());
                try {
                    FileWriter fw = new FileWriter(file);
                    fw.write(txaFileText.getText());
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

//        TODO hacer que el txaFileText pueda resizearse
        txaFileText.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            }
        });
    }

    public void lstSelectedFiles() {
        lstSelectedFiles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String filename = lstSelectedFiles.getSelectedValue();
                clearSearch();
                lstFilesAvailible.setSelectedValue(filename, true);
            }
        });
    }

    public JButton btnCreateFile() {
        JButton btnCreateFile = new JButton("Create a File");
        btnCreateFile.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(btnCreateFile, "File Name:");
            try {
                files.createFile(filename);
                refresh();
                lstFilesAvailible.setSelectedValue(filename, true);
            } catch (IOException ex) {
//                TODO DIALOGO DE ERROR
                throw new RuntimeException(ex);
            }
        });
        return btnCreateFile;
    }

    public JButton btnSelectFile() {
        JButton btnCreateFile = new JButton("Add File to Text");
        btnCreateFile.addActionListener(e -> {
            files.selectFile(lstFilesAvailible.getSelectedValue());
        });
        return btnCreateFile;
    }

    public JButton btnGenerateText() {
        JButton btnGenerateText = new JButton("Generate Text");
//      Copy to Clipboard
        btnGenerateText.addActionListener(e -> {
            String generatedText = files.generateText();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(generatedText);
            clipboard.setContents(selection, selection);
        });
        return btnGenerateText;
    }

    public void refresh() {
        lstFilesAvailible.setModel(files.getTextFilenameModel());
        lstFilesAvailible.setSelectedIndex(0);
    }

    public void clearSearch() {
        files.setTextFilenameSearch("");
        txtFileSearcher.setText("");
        refresh();
//        lstSelectedFiles.setse; TODO unselect list
    }
}
