package victoriano.jaime.modules;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//Esta Clase deberia solo listar y seleccionar archivos
public class TextManager {
//    TODO Debe ser el path completo
    private static String actualTextFilenameSearch = "";
    private static File searchDirectory;
    private static DefaultListModel<String> selectedFilenameListModel = new DefaultListModel<>();
    private static DefaultComboBoxModel<String> allFilenamesModel = new DefaultComboBoxModel<>();


    public static TextManager build(String directoryPath) {
        Path path = Paths.get(directoryPath);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File directory = new File(directoryPath);
        return new TextManager(directory);
    }
    public TextManager(File searchDirectory) {
        this.searchDirectory = searchDirectory;
    }

    public void setDirectory(File directory) {
        searchDirectory = directory;
//        TODO REFRESH
    }

////    Se debe llamar para cada vez que toque la lista de archivos, para tenerlos a tiempo real y evitar problemas
////    Recupera SOLO los archivos .TXT
//    public List<File> getFileList() {
//        List<File> fileList = new ArrayList<>();
//
//        DefaultComboBoxModel<String> textFilesModel = getTextFilenameModel();
//        for (int i = 0; i < textFilesModel.getSize(); i++) {
//            String filename = textFilesModel.getElementAt(i);
//            fileList.add(getFile(filename));
//        }
//
//
//        return fileList;
//    }

    public void setActualTextFilenameSearch(String search) {
        this.actualTextFilenameSearch = search;
    }
    
    public DefaultComboBoxModel<String> getTextFilenameModel() {
//        Primero veficia si es que termian con la extension de los archivos de texto, y luego verifica el nombre del archivo (sin extension) para ver si coincide con la busqueda
        FilenameFilter filter = (directory, name) -> name.endsWith(ExtensionsType.TEXT.toString()) && name.substring(0, name.length()-4).contains(actualTextFilenameSearch);
        File[] textFiles = searchDirectory.listFiles(filter);

        DefaultComboBoxModel<String> dcmTextFiles = new DefaultComboBoxModel<>();
        for (File file : textFiles) {
            dcmTextFiles.addElement(file.getName().substring(0, file.getName().length()-4));
        }
        
        return dcmTextFiles;
    }

    public File getFile(String filename) {
        return new File(searchDirectory + "/" + normalizeTextFilename(filename));
    }

    public List<File> getSelectedFiles() {
        List<File> selectedFileList = new ArrayList<>();
        for (Object o : selectedFilenameListModel.toArray()) {
            String filename = (String) o;
            selectedFileList.add(getFile(filename));
        }
        return selectedFileList;
    }

    public DefaultListModel<String> getSelectedFilenamesModel() {
        return selectedFilenameListModel;
    }

    public void selectFile(String filename) {
        selectedFilenameListModel.addElement(filename);
    }

    public void clearSelectedFiles() {
        selectedFilenameListModel = new DefaultListModel<>();
    }

    public File createFile(String filename) throws IOException {
        File newFile = new File(searchDirectory.getAbsolutePath() + '/' + normalizeTextFilename(filename));
        newFile.createNewFile();
        allFilenamesModel.addElement(newFile.getName());

        return newFile;
    }

    public String generateText() {
        List<File> selectedFileList = getSelectedFiles();
        StringBuilder sb = new StringBuilder();

        for (File file : selectedFileList) {
            sb.append(read(file) + '\n');
        }

        return sb.toString();
    }

    public String read(File file) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }


        return text;
    }

    private static String normalizeTextFilename(String filename) {
        return filename.endsWith(ExtensionsType.TEXT.toString()) ? filename : filename + ExtensionsType.TEXT;
    }
    private static String normalizePresetFilename(String filename) {
        return filename.endsWith(ExtensionsType.PRESET.toString()) ? filename : filename + ExtensionsType.PRESET;
    }
}
