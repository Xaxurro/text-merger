package victoriano.jaime.view;

import victoriano.jaime.modules.TextManager;

import javax.swing.*;
import java.awt.*;

public final class ComponentBuilder {
    public static JTextField txtFileSearcher () {
        JTextField txtFileSearcher = new JTextField(15);

        return txtFileSearcher;
    }

    public static JList<String> lstFilesAvailible(TextManager files) {
        JList<String> lstFilesAvailible = new JList<String>();
        lstFilesAvailible.setModel(files.getTextFilenameModel());
        lstFilesAvailible.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstFilesAvailible.setVisibleRowCount(10);
        return lstFilesAvailible;
    }

    public static JTextArea txaFileText () {
        JTextArea txaFileText = new JTextArea("", 10, 20);

        txaFileText.setLineWrap(true);
        txaFileText.setWrapStyleWord(true);
        txaFileText.setTabSize(4);

        return txaFileText;
    }

    public static JScrollPane scrollPane(JTextArea txaFileText) {
        JScrollPane scpFileText = new JScrollPane(txaFileText);
        scpFileText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        return scpFileText;
    }

    public static JList<String> lstSelectedFiles(TextManager files) {
        JList<String> lstSelectedFiles = new JList<String>();
        lstSelectedFiles.setModel(files.getSelectedFilenamesModel());
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
