import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadDictionary {

    private final List<String> listDictionary = new ArrayList<>();

    public ReadDictionary () {

        try {
            FileReader reader = new FileReader("./resources/RUS.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String word = bufferedReader.readLine();
            listDictionary.add(word);

            while (word!=null){
                word = bufferedReader.readLine();
                listDictionary.add(word);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List <String> getDictionary () {
        return  listDictionary;
    }
}
