import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import victoriano.jaime.modules.TextManagerReader;
import victoriano.jaime.modules.TextManagerFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class TextManagerFilesTest {

    final File TEST_DIR_PATH = new File("src/test/resources/group 1");
    @Test
    void test1_selectionSpecialCharacters() {
        TextManagerFiles fm = new TextManagerFiles(TEST_DIR_PATH);
        fm.selectFile("test3");
        Assertions.assertEquals("test\n\n3\n", fm.generateText());
        fm.selectFile("test1");
        Assertions.assertEquals("test\n\n3\ntest1\n", fm.generateText());
        fm.selectFile("test4");
        Assertions.assertEquals("test\n\n3\ntest1\n\ntest4\n", fm.generateText());
    }

    @Test
    void test2_selection() {
        TextManagerFiles fm = new TextManagerFiles(TEST_DIR_PATH);
        fm.selectFile("test1");
        fm.selectFile("test2");
        fm.selectFile("test1");
        Assertions.assertEquals("test1\ntest 2\ntest1\n", fm.generateText());

        fm.clearSelectedFiles();
        Assertions.assertEquals("", fm.generateText());
    }

    @Test
    void test3_writing() {
        TextManagerFiles fm = new TextManagerFiles(TEST_DIR_PATH);

        File testFile5 = null;
        File testFile6 = null;
        try {
            testFile5 = fm.createFile("test5");
            testFile6 = fm.createFile("test6.txt");

            Assertions.assertNotNull(testFile5);
            Assertions.assertNotNull(testFile6);

            FileWriter fw5 = new FileWriter(testFile5);
            fw5.write("Este archivo hay que eliminarlo");
            fw5.close();
            FileWriter fw6 = new FileWriter(testFile6);
            fw6.write("\tEste tambien.");
            fw6.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals("Este archivo hay que eliminarlo", TextManagerReader.read(testFile5));
        Assertions.assertEquals("\tEste tambien.",                 TextManagerReader.read(testFile6));

        Assertions.assertEquals(true, testFile5.delete());
        Assertions.assertEquals(true, testFile6.delete());
    }
}