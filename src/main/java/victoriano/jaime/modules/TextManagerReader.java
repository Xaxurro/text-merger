package victoriano.jaime.modules;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//Esta clase debe leer archivos que le pase el FileManager
public class TextManagerReader {

    public static String read(File file) {
        String text = "";

        try {
            List<String> lines = Files.readAllLines(Path.of(file.getPath()));
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < lines.size(); i++) {
                sb.append(lines.get(i));

                if (i < lines.size() - 1) {
                    sb.append('\n');
                }
            }

            text = sb.toString();
        } catch (IOException e) {}


        return text;
    }
}
