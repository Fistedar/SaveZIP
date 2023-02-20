import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(24, 4, 5, 5.4);
        GameProgress save2 = new GameProgress(52, 1, 14, 1.4);
        GameProgress save3 = new GameProgress(91, 9, 1, 14.4);
        saveGame("C://Games//savegames//save1.dat", save1);
        saveGame("C://Games//savegames//save2.dat", save2);
        saveGame("C://Games//savegames//save3.dat", save3);
        List<String> saves = new ArrayList<>();
        saves.add("C://Games//savegames//save1.dat");
        saves.add("C://Games//savegames//save2.dat");
        saves.add("C://Games//savegames//save3.dat");
        zipFiles("C://Games//savegames//zip.zip", saves);
        delete(saves);

    }

    public static void saveGame(String path, GameProgress save) {
        try (FileOutputStream file = new FileOutputStream(path);
             ObjectOutputStream obj = new ObjectOutputStream(file)) {
            obj.writeObject(save);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String pathZip, List<String> saves) {

        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(pathZip))) {
            for (String save : saves) {
                FileInputStream file = new FileInputStream(save);
                File nameSave = new File(save);
                ZipEntry entry = new ZipEntry(nameSave.getName());
                zip.putNextEntry(entry);
                byte[] buffer = new byte[file.available()];
                file.read(buffer);
                zip.write(buffer);
                zip.closeEntry();
                file.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public static void delete(List<String> save) {
        for (String s : save) {
            File file = new File(s);
            if (file.delete()) {
            } else {
                System.out.println("Ошибка при удалении");
            }
        }
    }
}
