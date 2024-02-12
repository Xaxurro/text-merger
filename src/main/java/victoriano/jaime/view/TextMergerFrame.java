package victoriano.jaime.view;

import victoriano.jaime.modules.GBConstrains;
import victoriano.jaime.modules.TextManager;

import javax.swing.*;
import java.awt.*;

public class TextMergerFrame extends JFrame{
    JFrame f;
    TextManager files;

//    |File Name:       |txtFileSearcher                                                           |btnSelectDirectory|
//    |-----------------|--------------------------------------------------------------------------|------------------|
//    |Select File      |Text                                                                      |Selected Files    |
//    |-----------------|--------------------------------------------------------------------------|------------------|
//    |lstFilesAvailible|scpFileText                                                               |scpSelectedFiles  |
//    |-----------------|--------------------------------------------------------------------------|------------------|
//    |btnCreateFile    |btnSelectFile  |btnUnselectFile    |btnGenerateText    |btnSavePreset     |btnLoadPreset     |
    public TextMergerFrame(String directory) {

        files = TextManager.build(directory);

        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());

//        1RA LINEA
        JTextField txtFileSearcher = ComponentBuilder.txtFileSearcher();
        pnl.add(new JLabel("File Name:"), new GBConstrains(0, 0).align(GBConstrains.EAST).setInsets(20, 0, 20, 5));
        pnl.add(txtFileSearcher, new GBConstrains(1, 0, 4, 1).expandHorizontally());


//        2DA LINEA
        JList<String> lstFilesAvailible = ComponentBuilder.lstFilesAvailible(files);
        JScrollPane scpFilesAvailible = ComponentBuilder.scrollPane(lstFilesAvailible);
        pnl.add(new JLabel("Selected File"), new GBConstrains(0, 1).setInsets(20, 0, 20, 0));
        pnl.add(scpFilesAvailible, new GBConstrains(0, 2).expandBothAxis().setInsets(0, 0 ,20, 0));

        JTextArea txaFileText = ComponentBuilder.txaFileText();
        JScrollPane scpFileText = ComponentBuilder.scrollPane(txaFileText);
        pnl.add(new JLabel("Text"), new GBConstrains(1, 1, 4, 1));
        pnl.add(scpFileText, new GBConstrains(1, 2, 4, 1).setInsets(0, 0 ,20, 0));

        JList<String> lstSelectedFiles = ComponentBuilder.lstSelectedFiles(files);
        JScrollPane scpSelectedFiles = ComponentBuilder.scrollPane(lstSelectedFiles);
        pnl.add(new JLabel("Selected Files"), new GBConstrains(5, 1));
        pnl.add(scpSelectedFiles, new GBConstrains(5, 2).expandBothAxis().setInsets(0, 0 ,20, 0));

        ComponentController cc = new ComponentController(files, txtFileSearcher, lstFilesAvailible, txaFileText, lstSelectedFiles);


//        1RA LINEA
        JButton btnSelectDirectory = cc.btnSelectDirectory();
        pnl.add(btnSelectDirectory, new GBConstrains(5, 0));


//        3RA LINEA
        JButton btnCreateFile = cc.btnCreateFile();
        pnl.add(btnCreateFile, new GBConstrains(0, 3).setInsets(40, 0 ,0, 0));

        JButton btnSelectFile = cc.btnSelectFile();
        pnl.add(btnSelectFile, new GBConstrains(1, 3).setInsets(40, 0 ,0, 0));

        JButton btnUnselectFile = new JButton("Remove File from text");
        pnl.add(btnUnselectFile, new GBConstrains(2, 3).setInsets(40, 0 ,0, 0));

        JButton btnGenerateText = cc.btnGenerateText();
        pnl.add(btnGenerateText, new GBConstrains(3, 3).setInsets(40, 0 ,0, 0));

        JButton btnSavePreset = new JButton("Save Preset");
        pnl.add(btnSavePreset, new GBConstrains(4, 3).setInsets(40, 0 ,0, 0));

        JButton btnLoadPreset = new JButton("Load Preset");
        pnl.add(btnLoadPreset, new GBConstrains(5, 3).setInsets(40, 0 ,0, 0));


        cc.txaFileText();
        cc.lstFilesAvailible();
        cc.txtFileSearcher();
        cc.lstSelectedFiles();


        add(pnl);
        setSize(800, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
