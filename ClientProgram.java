import java.util.Random;
import java.util.Scanner;

public class ClientProgram {




    public static void main(String[] args) {

        try {
            Random random = new Random();
            Scanner in = new Scanner(System.in);
            int id = random.nextInt(1000);

            try (Client client = new Client(id)) {
                System.out.println("Logged in successfully.");
                System.out.println("---------------YOUR ID---------------");
                System.out.println(id);

                while (true) {
                    int[] accountNumbers = client.getAccountNumbers();
                    System.out.println("---------------PLAYERS ONLINE---------------");
                    for (int account : accountNumbers)
                        System.out.printf("Player " + account + "\n");
                    System.out.println("---------------BALL HOLDER---------------");
                    System.out.println(client.getBallHolder());
                    System.out.println("-----------------------------");
                    //System.out.println(client.getBallHolder());
                    if (id == client.getBallHolder()){
                        System.out.println("You now have a ball ");
                    }else {
                        System.out.println("You don't have a ball so you cannot pass it");
                    }

                    System.out.println("-----------------------------");

                    System.out.println("Choose between the following 2 options:\n" +
                            "[1] Pass ball\n" +
                            "[2] Display players\n" +
                            "(Press 1 or 2)");
                    int choice = Integer.parseInt(in.nextLine());

                    if (choice == 1){
                        System.out.println("Enter the account number to pass to or -1 to print the account list:");
                        int toAccount = Integer.parseInt(in.nextLine());
                        if (toAccount < 0)
                            continue;

                        client.transfer(id, toAccount);

                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
