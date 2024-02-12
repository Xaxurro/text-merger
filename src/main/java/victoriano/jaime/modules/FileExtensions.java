package victoriano.jaime.modules;

public enum FileExtensions {
    TEXT,
    DIRECTORY,

//    El archivo que debe llevar presets
    PRESET;

    @Override
    public String toString() {
        switch (this) {
            case TEXT:
                return ".txt";
            case PRESET:
                return ".preset";
            default:
                return "";
        }
    }
}
