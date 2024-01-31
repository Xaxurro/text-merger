package victoriano.jaime.modules;

import java.io.File;
import java.util.List;

public class TextManager {
    public static String generateText() {
        List<File> selectedFileList = TextManagerFiles.getSelectedFiles();
        StringBuilder sb = new StringBuilder();

        for (File file : selectedFileList) {
            sb.append(TextManagerReader.read(file) + '\n');
        }

        return sb.toString();
    }
}
