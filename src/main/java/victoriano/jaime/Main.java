package victoriano.jaime;

import victoriano.jaime.modules.TextManagerFiles;
import victoriano.jaime.view.TextMergerFrame;

public class Main {
    public static void main(String[] args) {
//        TODO HACER QUE ESTE PATH SE MANDE POR ARGUMENTO
        final String PATH = "/home/xaxurro/Progra/.text-merger";
        TextMergerFrame textMergerFrame = new TextMergerFrame(PATH);
    }
}
