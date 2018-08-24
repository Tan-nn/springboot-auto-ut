package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tan on 02/04/2017.
 */
public class TestCaseJavaFileGenerator {
    private static final String MAIN_PATH ="src/test/java/com/example";


    /**
     * Execution method
     *
     * @param args param
     * @throws IOException not expected
     */
    public static void main(String [] args) throws IOException {
        writeJavaExecuteTestCaseFile( MAIN_PATH + "/template/test-case-template.txt", MAIN_PATH + "/MoonParkUnitTests.java");
    }


    /**
     * Build TestCase body method
     *
     * @return test case content
     */
    private static StringBuilder generateTestCase() {
        StringBuilder result = new StringBuilder();
        File[] dirList = new File(MAIN_PATH + "/Test-Data/").listFiles();

        for (File file : dirList) {

            String testCase = "   /**\n";
            testCase += "    * Just demo please read more description in test case file "+file.getPath() +"\n";
            testCase += "    * @throws Exception exception not expected\n";
            testCase += "    */";
            testCase += "\n";
            testCase +=       "    @Test";
            testCase += "\n";
            testCase += String.format("    public void %s() throws Exception {", file.getName().replace(".json", ""));
            testCase += "\n";
            testCase += String.format("        String inputFilePath = \"%s\";", file.getPath());
            testCase += "\n";
            testCase += "\n";
            testCase += "        UtilTest.executeTestCase(inputFilePath);";
            testCase += "\n    } \n\n";

            result.append(testCase);
        }
        return result;
    }

    /**
     *
     * Create Test case file base on template
     *
     * @param templateFilePath path of template file
     * @param ouputFilePath path to write
     * @throws IOException not expected
     */
    private static void writeJavaExecuteTestCaseFile(String templateFilePath, String ouputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(templateFilePath));
        List<String> result = new ArrayList<>();

        lines.forEach(p -> {
            if (p.contains("%{test_case}%")) {
                p = p.replace("%{test_case}%", generateTestCase());
            }
            result.add(p);
        });
        Files.write(Paths.get(ouputFilePath), result);
    }


}
