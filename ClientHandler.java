import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private Bank bank;

    public ClientHandler(Socket socket, Bank bank) {
        this.socket = socket;
        this.bank = bank;
    }

    /*public int generateId(){
        int id = bank.getListOfPlayers().size()+1;
        return id;
    }*/

    @Override
    public void run() {
        int playerId = 0;
        try (
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            try {
                playerId = Integer.parseInt(scanner.nextLine());
                Player newPlayer = bank.addPlayer(playerId);
                System.out.println("New connection; customer ID " + playerId);
                if (bank.getListOfPlayers().size() == 1)
                    newPlayer.setBall(true);
                writer.println("SUCCESS");

                while (true) {
                    String line = scanner.nextLine();
                    String[] substrings = line.split(" ");
                    switch (substrings[0].toLowerCase()) {
                        case "players":
                            ArrayList<Player> listOfPlayers = bank.getListOfPlayers();
                            writer.println(listOfPlayers.size());
                            for (Player playerNumber : listOfPlayers)
                                writer.println(playerNumber.getPlayerId());
                            break;

                        case "hasball":
                            writer.println(bank.printBallHolder());
                            break;

                        case "transfer":
                            int fromPlayer = Integer.parseInt(substrings[1]);
                            int toPlayer = Integer.parseInt(substrings[2]);
                            bank.transfer(fromPlayer, toPlayer);
                            System.out.println("Ball was passed from " + fromPlayer + " to " + toPlayer);
                            writer.println("SUCCESS");
                            break;

                        default:
                            throw new Exception("Unknown command: " + substrings[0]);
                    }
                }


            } catch (Exception e) {
                writer.println("ERROR " + e.getMessage());
                System.out.println(bank.getListOfPlayers());
                bank.removePlayer(playerId);
                socket.close();

            }
        } catch (Exception e) {
        } finally {
            System.out.println("Customer " + playerId + " disconnected.");
            System.out.println(bank.getListOfPlayers());

        }
    }
}
