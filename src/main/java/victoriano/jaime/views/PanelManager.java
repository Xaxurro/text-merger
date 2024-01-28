package victoriano.jaime.views;

import victoriano.jaime.TextData;
import victoriano.jaime.views.modules.Searcher.SearcherPanelV01;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

public class PanelManager {
    TextData data;

    public PanelManager(TextData data) {
        this.data = data;
    }

    public JPanel initMainPanel() {
        JPanel pnlMain = new JPanel();

        pnlMain.add(SearcherPanelV01.build());
        pnlMain.add(textSelector(data));
        pnlMain.add(textEditor(data));

        return pnlMain;
    }

    private JPanel textEditor(TextData data) {
        Map<String, String> textMap = data.getTextMap();

        JTextArea txaTextEditor = new JTextArea(5, 30);

        JPanel pnlTextEditor = new JPanel();
        pnlTextEditor.add(txaTextEditor);
        return pnlTextEditor;
    }

    private JPanel textSelector(TextData data) {
        Set<String> textNameSet = data.getTextMap().keySet();

        JList lstTextNames = new JList();
        lstTextNames.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lstTextNames.setLayoutOrientation(JList.VERTICAL);
        lstTextNames.setVisibleRowCount(-1);

        DefaultListModel listModel = new DefaultListModel();
        listModel.addAll(textNameSet);
        lstTextNames.setModel(listModel);

        JPanel pnlTextSelector = new JPanel();
        pnlTextSelector.add(lstTextNames);
        return pnlTextSelector;
    }
}
