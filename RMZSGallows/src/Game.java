import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private GameStatus gameStatus = GameStatus.WAIT;
    private HangMan hangMan = new HangMan();
    private String maskedWord = "";
    private String guessWord = "";
    private List<String> guessChars = new ArrayList<String>();
    private List<String> guessFail = new ArrayList<String>();
    private int countLose = 0;

    public void run(Dictionary dictionary) {

        while (true) {

            if (gameStatus == GameStatus.LOAD) {
                maskedWord = dictionary.getMaskedWord();
                gameStatus = GameStatus.RUN;
                System.out.println("\nИгра началась. Разгадайте слово. ");
            }
            else if (gameStatus == GameStatus.RUN) {
                ifContainsLoseOrWin();
                infoRunningGame ();

            } else if (gameStatus == GameStatus.WAIT) {
                userMenu();
            } else if (gameStatus == GameStatus.LOSE) {
                infoEndGame();
                break;
            } else if (gameStatus == GameStatus.WIN) {
                infoEndGame();
                break;
            } else if (gameStatus == GameStatus.EXIT) {
                System.exit(0);
            }

        }

    }

    private void ifContainsLoseOrWin() {
        String inLetter = getInputGuess();
        if (maskedWord.contains(inLetter)) {
            winGuess(inLetter);
        } else {
            failGuess(inLetter);
        }
    }

    private void infoEndGame() {
        System.out.println("------------------------");
        System.out.print("Игра завершина! Вы ");
        if (gameStatus == GameStatus.LOSE) System.out.println("проиграли");
        if (gameStatus == GameStatus.WIN) System.out.println("победили");
        System.out.println("Попыток использовано: " + countLose);
        System.out.println("Загаданное слово: " + maskedWord);
        hangMan.printResult();
    }

    private void infoRunningGame () {
        System.out.println("Угадано: "  + guessWord);
        System.out.println("Неправильные буквы: ");
        guessFail.forEach(System.out::print);
        System.out.println("\n------------------------");
    }

    private String getInputGuess() {
        System.out.print("Введите букву: ");
        Scanner scannerLetter = new Scanner(System.in);
        return scannerLetter.nextLine();
    }

    private void winGuess (String inLetter) {
        if (!guessChars.contains(inLetter)) guessChars.add(inLetter);
        guessWord = "";

        for (String letter : maskedWord.split("")) {
            if (guessChars.contains(letter)) {
                guessWord = guessWord.concat(letter);
            } else {
                guessWord = guessWord.concat("*");
            }
        }

        System.out.println("Буква в слове есть!");
        if (guessWord.equals(maskedWord)) {
            gameStatus = GameStatus.WIN;
        }
    }

    private void failGuess (String inLetter) {
        int countCanLose = 6;

        countLose++;
        hangMan.makeMistake(countLose);
        guessFail.add(inLetter);
        System.out.println("Буквы в слове нету!");
        System.out.println("Счет неправильных ответов: " + countLose);
        hangMan.printResult();

        if (countCanLose == countLose) {
            gameStatus = GameStatus.LOSE;
        }
    }

    private void userMenu () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Начать новую игру? (да/нет)");
        String answ = scanner.nextLine();
        if (answ.equalsIgnoreCase("да")) {
            gameStatus = GameStatus.LOAD;
        } else if (answ.equalsIgnoreCase("нет")){
            gameStatus = GameStatus.EXIT;
        } else {
            System.out.println("Неизвестная команда");
        }
    }

    enum GameStatus {
        LOAD, RUN, LOSE, WIN, EXIT, WAIT
    }
}
