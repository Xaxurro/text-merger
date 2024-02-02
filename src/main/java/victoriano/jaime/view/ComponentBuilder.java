package victoriano.jaime.view;

import victoriano.jaime.modules.GBConstrains;
import victoriano.jaime.modules.TextManagerFiles;
import victoriano.jaime.modules.TextManagerReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class ComponentBuilder {
    public static JTextField txtFileSearcher () {
        JTextField txtFileSearcher = new JTextField(15);

        return txtFileSearcher;
    }

    public static JComboBox cbbFilesAvailible (TextManagerFiles files) {
        JComboBox cbbFilesAvailible = new JComboBox();
        DefaultComboBoxModel<String> dcmFilesAvailible = new DefaultComboBoxModel<>();
        dcmFilesAvailible.addAll(files.getFilenames());
        cbbFilesAvailible.setModel(dcmFilesAvailible);
        cbbFilesAvailible.setSelectedIndex(0);
        return cbbFilesAvailible;
    }

    public static JTextArea txaFileText () {
        JTextArea txaFileText = new JTextArea("", 10, 20);

        txaFileText.setLineWrap(true);
        txaFileText.setTabSize(4);

        return txaFileText;
    }

    public static JScrollPane scrollPane(JTextArea txaFileText) {
        JScrollPane scpFileText = new JScrollPane(txaFileText);
        scpFileText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scpFileText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scpFileText;
    }

    public static JList lstSelectedFiles(TextManagerFiles files) {
        JList lstSelectedFiles = new JList<>();
        DefaultListModel dlmSelectedFiles = new DefaultListModel<>();
        dlmSelectedFiles.addAll(files.getSelectedFiles());
        lstSelectedFiles.setModel(dlmSelectedFiles);
        lstSelectedFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstSelectedFiles.setVisibleRowCount(10);
//        lstSelectedFiles.setDragEnabled(true);

        return lstSelectedFiles;
    }

    public static JScrollPane scrollPane(JList lstSelectedFiles) {
        JScrollPane scpFileText = new JScrollPane(lstSelectedFiles);
        scpFileText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scpFileText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scpFileText.setPreferredSize(new Dimension(25, 100));
        return scpFileText;
    }
}
