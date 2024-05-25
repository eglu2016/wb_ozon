package helpers;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.fail;

public class WriteReadFile {

    public static String getAbsolutePath(String folderName, String fileName) {
        Path dir = Paths.get("src", "test", "resources", folderName, fileName);
        return dir.toFile().getAbsolutePath();
    }

    public static File createFile(String name) {
        File myFile = new File(name);
        try {
            if (myFile.createNewFile())
                System.out.println("--> THE FILE HAS BEEN CREATED SUCCESSFULLY - " + name);
            else
                System.out.println("--> THE FILE ALREADY EXISTS - " + name);
        }
        catch (Exception ex) {
            fail(ex.getMessage());
        }
        return myFile;
    }

    public static void appendStrToFile(String name, String text) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(name, true));
            out.write(text);
            out.close();
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        System.out.println("--> SUCCESSFULLY APPENDED IN FILE: " + name + ", LINE: " + text);
    }

    public static List<String> readFromFile(File myFile) {
        List<String> dataFromFile = new ArrayList<>();
        Scanner myReader = null;
        try {
            myReader = new Scanner(myFile);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        while (myReader.hasNextLine()) {
            dataFromFile.add(myReader.nextLine());
        }
        return dataFromFile;
    }
}
