import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDictionary {

    private final List<String> listDictionary = new ArrayList<>();

    public ReadDictionary () {

        try {
            Scanner scanner = new Scanner(Paths.get("resources","RUS.txt").toFile());

            do {
                String word = scanner.nextLine();
                listDictionary.add(word);
            } while (scanner.hasNext());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List <String> getDictionary () {
        return  listDictionary;
    }
}
