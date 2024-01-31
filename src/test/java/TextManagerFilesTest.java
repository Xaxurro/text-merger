import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import victoriano.jaime.modules.TextManagerReader;
import victoriano.jaime.modules.TextManagerWriter;
import victoriano.jaime.modules.TextManagerFiles;
import victoriano.jaime.modules.TextManager;

import java.io.File;
import java.io.IOException;

class TextManagerFilesTest {

    final String TEST_DIR_PATH = "src/test/resources/group 1";
    @Test
    void test1_selectionSpecialCharacters() {
        TextManagerFiles fm = new TextManagerFiles(TEST_DIR_PATH);
        fm.selectFile("test3");
        Assertions.assertEquals("test\n\n3\n", TextManager.generateText());
        fm.selectFile("test1");
        Assertions.assertEquals("test\n\n3\ntest1\n", TextManager.generateText());
        fm.selectFile("test4");
        Assertions.assertEquals("test\n\n3\ntest1\n\ntest4\n", TextManager.generateText());
    }

    @Test
    void test2_selection() {
        TextManagerFiles fm = new TextManagerFiles(TEST_DIR_PATH);
        fm.selectFile("test1");
        fm.selectFile("test2");
        fm.selectFile("test1");
        Assertions.assertEquals("test1\ntest 2\ntest1\n", TextManager.generateText());

        fm.clearSelectedFiles();
        Assertions.assertEquals("", TextManager.generateText());
    }

    @Test
    void test3_writing() {
        TextManagerFiles fm = new TextManagerFiles(TEST_DIR_PATH);

        File testFile5 = null;
        File testFile6 = null;
        try {
            testFile5 = fm.createFile(TEST_DIR_PATH, "test5");
            testFile6 = fm.createFile(TEST_DIR_PATH, "test6.txt");

            Assertions.assertNotNull(testFile5);
            Assertions.assertNotNull(testFile6);

            TextManagerWriter.write(testFile5,                   "Este archivo hay que eliminarlo");
            TextManagerWriter.write(testFile6.getAbsolutePath(), "\tEste tambien.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals("Este archivo hay que eliminarlo", TextManagerReader.read(testFile5));
        Assertions.assertEquals("\tEste tambien.",                 TextManagerReader.read(testFile6));

        Assertions.assertEquals(true, testFile5.delete());
        Assertions.assertEquals(true, testFile6.delete());
    }
}