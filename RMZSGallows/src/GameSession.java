import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameSession extends Thread {

    private GameStatus gameStatus = GameStatus.WAIT;
    private final ReadDictionary readDictionary = new ReadDictionary();
    private String hiddenWord = "";
    private String guessWord = "";
    private List<String> guessChars = new ArrayList<String>();
    private List<String> guessFail = new ArrayList<String>();
    private int countLose = 0;
    private final int countCanLose = 10;

    @Override
    public void run() {
        // logic of game here

        List<String> listDictionary = readDictionary.getDictionary();
        System.out.println("Добро пожаловать в игру виселица!");


        while (true) {

            if (gameStatus == GameStatus.LOAD) {
                Random r = new Random();
                hiddenWord = listDictionary.get(r.nextInt(0,listDictionary.size()-1));
                guessWord = "";
                guessFail.removeAll(guessFail);
                guessChars.removeAll(guessChars);

                gameStatus = GameStatus.RUN;
                System.out.println("\nСлово загадано");
            }
            else if (gameStatus == GameStatus.RUN) {
                System.out.print("Введите букву: ");
                Scanner scannerLetter = new Scanner(System.in);
                String inLetter = scannerLetter.nextLine();

                if (hiddenWord.contains(inLetter)) {

                    if (!guessChars.contains(inLetter)) guessChars.add(inLetter);
                    guessWord = "";

                    for (String letter : hiddenWord.split("")) {
                        if (guessChars.contains(letter)) {
                            guessWord = guessWord.concat(letter);
                        } else {
                            guessWord = guessWord.concat("*");
                        }
                    }


                    System.out.println("Буква в слове есть!");
                    if (guessWord.equals(hiddenWord)) {
                        gameStatus = GameStatus.WIN;
                    }
                } else {
                    countLose++;
                    guessFail.add(inLetter);
                    System.out.println("Буквы в слове нету!");
                    System.out.println("Счет неправильных ответов: " + countLose);
                    if (countCanLose == countLose) {
                        gameStatus = GameStatus.LOSE;
                    }
                }

                System.out.println("------------------------");
                System.out.println("Угаданное: "  + guessWord);
                System.out.println("Неправильные буквы: ");
                guessFail.forEach(System.out::print);
                System.out.println("\n------------------------");

            } else if (gameStatus == GameStatus.WAIT) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Начать игру? (да/нет)");
                String answ = scanner.nextLine();
                if (answ.equalsIgnoreCase("да")) {
                    gameStatus = GameStatus.LOAD;
                } else if (answ.equalsIgnoreCase("нет")){
                    gameStatus = GameStatus.EXIT;
                } else {
                    System.out.println("Неизвестная команда");
                }
            }else if (gameStatus == GameStatus.LOSE) {
                EndGame ();
            } else if (gameStatus == GameStatus.WIN) {
                EndGame ();
            } else if (gameStatus == GameStatus.EXIT) {
                break;
            }

        }

    }

    private void EndGame () {
        System.out.println("------------------------");
        System.out.print("Игра завершина! Вы ");
        if (gameStatus == GameStatus.LOSE) System.out.println("проиграли");
        if (gameStatus == GameStatus.WIN) System.out.println("победили");
        System.out.println("Попыток использовано: " + countLose);
        System.out.println("Загаданное слово: " + hiddenWord);

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
        countLose = 0;
    }

    enum GameStatus {
        LOAD, RUN, LOSE, WIN, EXIT, WAIT
    }
}
