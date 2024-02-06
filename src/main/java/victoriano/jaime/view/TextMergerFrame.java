package victoriano.jaime.view;

import victoriano.jaime.modules.GBConstrains;
import victoriano.jaime.modules.TextManager;

import javax.swing.*;
import java.awt.*;

public class TextMergerFrame extends JFrame{
    JFrame f;
    TextManager files;

    public TextMergerFrame(String directory) {

        files = TextManager.build(directory);

        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());

        JTextField txtFileSearcher = ComponentBuilder.txtFileSearcher();
        pnl.add(new JLabel("File Name:"), new GBConstrains(0, 0, 1, 1));
        pnl.add(txtFileSearcher, new GBConstrains(1, 0, 3, 1));

        JButton btnSelectDirectory = ComponentController.btnSelectDirectory(files);
        pnl.add(btnSelectDirectory, new GBConstrains(4, 0, 1, 1));


        JComboBox cbbFilesAvailible = ComponentBuilder.cbbFilesAvailible(files);
        pnl.add(new JLabel("Selected File"), new GBConstrains(0, 1, 1, 1));
        pnl.add(cbbFilesAvailible, new GBConstrains(0, 2, 1, 1));

        JTextArea txaFileText = ComponentBuilder.txaFileText();
        JScrollPane scpFileText = ComponentBuilder.scrollPane(txaFileText);
        pnl.add(new JLabel("Text"), new GBConstrains(1, 1, 1, 1));
        pnl.add(scpFileText, new GBConstrains(1, 2, 3, 1));

        JList lstSelectedFiles = ComponentBuilder.lstSelectedFiles(files);
        JScrollPane scpSelectedFiles = ComponentBuilder.scrollPane(lstSelectedFiles);
        pnl.add(new JLabel("Selected Files"), new GBConstrains(4, 1, 1, 1));
        pnl.add(scpSelectedFiles, new GBConstrains(4, 2, 1, 1));


        JButton btnCreateFile = ComponentController.btnCreateFile(cbbFilesAvailible, files);
        pnl.add(btnCreateFile, new GBConstrains(0, 3, 1, 1));

        JButton btnSelectFile = ComponentController.btnSelectFile(cbbFilesAvailible, lstSelectedFiles, files);
        pnl.add(btnSelectFile, new GBConstrains(1, 3, 1, 1));

        JButton btnUnselectFile = new JButton("Remove File from text");
        pnl.add(btnUnselectFile, new GBConstrains(2, 3, 1, 1));

        JButton btnGenerateText = ComponentController.btnGenerateText(files);
        pnl.add(btnGenerateText, new GBConstrains(3, 3, 1, 1));

        JButton btnSavePreset = new JButton("Save Preset");
        pnl.add(btnSavePreset, new GBConstrains(4, 3, 1, 1));

        JButton btnLoadPreset = new JButton("Load Preset");
        pnl.add(btnLoadPreset, new GBConstrains(5, 3, 1, 1));


        ComponentController.txaFileText(txaFileText, cbbFilesAvailible, files);
        ComponentController.cbbFilesAvailible(cbbFilesAvailible, txaFileText, files);


        add(pnl);
        setSize(800, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
