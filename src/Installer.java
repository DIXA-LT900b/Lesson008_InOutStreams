import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Installer {
    public static final Path INSTALL_PATH = Path.of("C:\\GameZz");
    private static StringBuilder installLog;
    List<String> pathList;
    String[] paths = {
            "src",
            "res",
            "savegames",
            "temp",
            "src\\main",
            "src\\test",
            "src\\main\\Main.java",
            "src\\main\\Utils.java",
            "res\\drawables",
            "res\\vectors",
            "res\\icons",
            "temp\\temp.txt"
    };

    Installer(){
        installLog = new StringBuilder("");
        pathList = Arrays.stream(paths).toList();
    }
    public void run() {

        for (String path : pathList){
            createSource(INSTALL_PATH.resolve(path));
        }

       writeLogToFile(String.valueOf(INSTALL_PATH.resolve("temp\\temp.txt")));
    }

    private void createSource(Path path) {

        if (String.valueOf(path).contains(".")) {
            createFile(path);
            System.out.println(path + " файл");
        } else {
            createDirectory(path);
            System.out.println(path + " каталог");
        }
    }

    private void createDirectory(Path path) {

        String result = null;
        try {
            Files.createDirectories(path);
            result = "каталог успешно создан или уже существует.\n";
        } catch (IOException e) {
            e.printStackTrace();
            result = "невозможно создать каталог.\n";
        } finally {
            installLog.append(INSTALL_PATH.resolve(path)).append(" ").append(result);
        }
    }

    private void createFile(Path path) {

        File file = new File(String.valueOf(path));
        String result = null;
        try {
            file.createNewFile();
            result = "файл успешно создан или уже существует.\n";
        } catch (IOException e) {
            e.printStackTrace();
            result = "невозможно создать файл.\n";
        } finally {
            installLog.append(INSTALL_PATH.resolve(path)).append(" ").append(result);
        }
    }

    private void writeLogToFile(String path){

        File logFile = new File(path);

        try {
            FileWriter output = new FileWriter(logFile);
            output.write(String.valueOf(installLog));
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
