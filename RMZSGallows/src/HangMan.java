import java.util.ArrayList;
import java.util.List;

public class HangMan {

    private final List<String> hangman = new ArrayList<>();

    public HangMan () {
        hangman.add("  +---+");
        hangman.add("  |   |");
        hangman.add("      |");
        hangman.add("      |");
        hangman.add("      |");
        hangman.add("      |");
        hangman.add("=========");
        hangman.add("");
    }


    public void makeMistake(int mistakes) {
        switch (mistakes) {
            case 1:
                hangman.set(2, "  |   O");
                break;
            case 2:
                hangman.set(3, "  |   |");
                break;
            case 3:
                hangman.set(3, "  |   |\\");
                break;
            case 4:
                hangman.set(3, "  |  /|\\");
                break;
            case 5:
                hangman.set(4, "  |   |");
                break;
            case 6:
                hangman.set(5, "  |  / \\");
                break;
        }
    }

    public void printResult () {
        for (String line : hangman) {
            System.out.println(line);
        }
    }


}
