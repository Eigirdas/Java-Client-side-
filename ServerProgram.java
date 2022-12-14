import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerProgram
{
    private final static int port = 8888;

    private static final Bank bank = new Bank();

    public static void main(String[] args)
    {
        RunServer();



    }

    private static void RunServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for incoming connections...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, bank)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
