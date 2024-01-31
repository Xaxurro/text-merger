package victoriano.jaime.modules;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Esta clase debe leer archivos que le pase el FileManager
public class TextManagerWriter {

    public static void write(String filePath, String content) throws IOException {
        write(TextManagerFiles.getFile(filePath), content);
    }

    public static void write(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file, false);

        fw.write(content);

        fw.close();
    }
}
