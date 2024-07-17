import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dictionary {

    private final List<String> listDictionary = new ArrayList<>();

    public Dictionary() {

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

    public String getMaskedWord () {
        Random r = new Random();
        return listDictionary.get(r.nextInt(0,listDictionary.size()-1));
    }
}
