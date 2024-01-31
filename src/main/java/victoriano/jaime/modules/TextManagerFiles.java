package victoriano.jaime.modules;

import java.io.*;
import java.util.*;

//Esta Clase deberia solo listar y seleccionar archivos
public class TextManagerFiles {
    private static Set<String> searchDirectoryPathList = new HashSet<>();
    private static List<File> selectedFileList = new ArrayList<>();

    public TextManagerFiles(String... directoriesPath) {
        setDirectories(directoriesPath);
    }

    public void setDirectories(String... directoriesPath) {
        searchDirectoryPathList = Set.of(directoriesPath);
        getFiles();
    }

//    Se debe llamar para cada vez que toque la lista de archivos, para tenerlos a tiempo real y evitar problemas
    public static List<File> getFiles() {
        List<File> fileList = new ArrayList<>();
        FilenameFilter filter = (directory, name) -> name.endsWith(ExtensionsType.TEXT.toString());

        for (String directoryPath : searchDirectoryPathList) {
            // TODO Decidir que hacer con los archivos / directorios que no existan
            File directory = new File(directoryPath);
            File[] files = directory.listFiles(filter);

            fileList.addAll(List.of(files));
        }

        return fileList;
    }

    public static List<File> getSelectedFiles() {
        return selectedFileList;
    }

    public void selectFile(String filename) {
        selectedFileList.add(getFile(filename));
    }

    public void clearSelectedFiles() {
        selectedFileList = new ArrayList<>();
    }

    public File createFile(String filePath) throws IOException {
        File newFile = new File(normalizeFilename(filePath));
        newFile.createNewFile();

        return newFile;
    }

    protected static File getFile(String filename) {
        String normalizedName = normalizeFilename(filename);
        Optional<File> optFile = getFiles().stream().filter(file -> normalizedName.endsWith(file.getName())).findFirst();

        if (!optFile.isPresent()) {
            throw new NullPointerException("No existe un archivo dentro de las carpetas seleccionadas con el nombre: " + normalizedName);
        }

        return optFile.get();
    }

    private static String normalizeFilename(String filename) {
        return filename.endsWith(ExtensionsType.TEXT.toString()) ? filename : filename + ExtensionsType.TEXT;
    }
}
