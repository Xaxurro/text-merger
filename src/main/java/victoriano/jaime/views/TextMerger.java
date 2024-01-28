package victoriano.jaime.views;

import victoriano.jaime.TextData;

import javax.swing.*;
import java.util.Map;

public class TextMerger extends JFrame {
    TextData data;
    public TextMerger(TextData data) {
        this.data = data;

        setTitle("Text Merger");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 540);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }

        PanelManager pm = new PanelManager(data);

        getContentPane().add(pm.initMainPanel());
        setVisible(true);
    }


}
