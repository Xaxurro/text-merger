package victoriano.jaime;

import victoriano.jaime.views.TextMerger;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> textMap = new HashMap<>();
        textMap.put("Test 1", "Esto es un test");
        textMap.put("Test 2", "Esto es otro test");
        TextData textData = new TextData();
        textData.setTextMap(textMap);


        SwingUtilities.invokeLater(() -> new TextMerger(textData));
    }
}
