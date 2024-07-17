public class Main {

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();

        // game logic
        while (true) {
            Game game = new Game();
            game.run(dictionary);
        }

    }
}