import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSet {
    public static void main(String[] args) {
        Deck deck = new Deck(); // Create a Deck of 81 cards.
        GraphicsLobby graphics = new GraphicsLobby();

        try {
            Socket socket = new Socket("cooper-VirtualBox", 5122);

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String message;

            while (true) {
                Scanner scanner = new Scanner(System.in);
                message = scanner.next();
                output.writeBytes(message);
            }
        } catch (IOException e) {
            System.out.println("Connection not established!");
        }


        // TODO: Network interface, see how many players are in the game.
        int numPlayers = 0;
        String playerName = new String(); // TODO: Write code where this is set.
        List<Player> playersInGame = new ArrayList<Player>();

        for (int i = 0; i < numPlayers; i++) {
            playersInGame.add(new Player(playerName));
        }
    }

    public static boolean noSetsOnBoard(List<Card> cardsInPlay) {
        //If there are no sets return true
        // TODO: OPTIONAL: Improve this to run like O(N^2) instead of O(N^3)
        boolean containsNoSet = true;
        List<Card> cardSet;
        for (int i = cardsInPlay.size() - 1; i > -1; i--) {
            for (int j = cardsInPlay.size() - 1; j > -1; j--) {
                for (int k = cardsInPlay.size() - 1; k > -1; k--) {
                    //all cards should be different
                    if (i != k && i != j && k != j) {
                        cardSet = new ArrayList<Card>();
                        cardSet.add(cardsInPlay.get(i));
                        cardSet.add(cardsInPlay.get(j));
                        cardSet.add(cardsInPlay.get(k));
                        if (Player.confirmCards(cardSet)) {
                            containsNoSet = false;
                        }
                    }
                }
            }
        }
        return containsNoSet;
    }

    public static boolean extraCards(List<Card> cardsInPlay) {
        if (cardsInPlay.size() > 12) {
            return true;
        } else {
            return false;
        }
    }
}