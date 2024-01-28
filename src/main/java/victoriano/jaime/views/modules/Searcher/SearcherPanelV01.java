package victoriano.jaime.views.modules.Searcher;

import javax.swing.*;
import java.awt.*;

public final class SearcherPanelV01 implements SearcherModule {
    public static JPanel build() {
        JLabel lblTextSearcher;
        JTextField txtTextSearcher;
        JCheckBox chxRegex;

        lblTextSearcher = new JLabel("Text Name:");
        txtTextSearcher = new JTextField(20);
        chxRegex = new JCheckBox("Regex");

        JPanel pnlTextSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlTextSearch.add(lblTextSearcher);
        pnlTextSearch.add(txtTextSearcher);
        pnlTextSearch.add(chxRegex);
        pnlTextSearch.add(new JSeparator(SwingConstants.HORIZONTAL));

        return pnlTextSearch;
    }
}
