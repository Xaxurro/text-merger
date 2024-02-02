package victoriano.jaime.modules;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//Esta Clase deberia solo listar y seleccionar archivos
public class TextManagerFiles {
//    TODO Debe ser el path completo
    private static File searchDirectory;
    private static List<File> selectedFileList = new ArrayList<>();
    private static DefaultListModel<String> fileDefaultListModel = new DefaultListModel<>();
    private static DefaultComboBoxModel<String> fileDefaultComboBoxModel = new DefaultComboBoxModel<>();


    public static TextManagerFiles build(String directoryPath) {
        Path path = Paths.get(directoryPath);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File directory = new File(directoryPath);
        return new TextManagerFiles(directory);
    }
    public TextManagerFiles(File searchDirectory) {
        this.searchDirectory = searchDirectory;
    }

    public void setDirectory(File directory) {
        searchDirectory = directory;
        getFiles();
    }

//    Se debe llamar para cada vez que toque la lista de archivos, para tenerlos a tiempo real y evitar problemas
//    Recupera SOLO los archivos .TXT
    public List<File> getFiles() {
        List<File> fileList = new ArrayList<>();
        FilenameFilter filter = (directory, name) -> name.endsWith(ExtensionsType.TEXT.toString());

        // TODO Decidir que hacer con los archivos / directorios que no existan
        File[] files = searchDirectory.listFiles(filter);

        fileList.addAll(List.of(files));

        return fileList;
    }

    public List<String> getFilenames() {
        List<String> filenameList = new ArrayList<>();
        for (File file : getFiles()) {
            filenameList.add(file.getName().substring(0, file.getName().length()-4));
        }
        return filenameList;
    }

    public List<File> getSelectedFiles() {
        return selectedFileList;
    }

    public List<String> getSelectedFilenames() {
        List<String> filenameList = new ArrayList<>();
        for (File file : selectedFileList) {
            filenameList.add(file.getName().substring(0, file.getName().length()-4));
        }
        return filenameList;
    }

    public void selectFile(String filename) {
        selectedFileList.add(getFile(filename));
    }

    public void clearSelectedFiles() {
        selectedFileList = new ArrayList<>();
    }

    public File createFile(String filename) throws IOException {
        File newFile = new File(searchDirectory.getAbsolutePath() + '/' + normalizeFilename(filename));
        newFile.createNewFile();

        return newFile;
    }

    public File getFile(String filename) {
        String normalizedName = normalizeFilename(filename);
        Optional<File> optFile = getFiles().stream().filter(file -> normalizedName.endsWith(file.getName())).findFirst();

        if (!optFile.isPresent()) {
            throw new NullPointerException("No existe un archivo dentro de las carpetas seleccionadas con el nombre: " + normalizedName);
        }

        return optFile.get();
    }

    public String generateText() {
        List<File> selectedFileList = getSelectedFiles();
        StringBuilder sb = new StringBuilder();

        for (File file : selectedFileList) {
            sb.append(TextManagerReader.read(file) + '\n');
        }

        return sb.toString();
    }

    private static String normalizeFilename(String filename) {
        return filename.endsWith(ExtensionsType.TEXT.toString()) ? filename : filename + ExtensionsType.TEXT;
    }
}
