package Chat;

import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the UserName");
        String clientUserName = scanner.nextLine();

        Socket socket = new Socket("localhost",1234);
        Client client = new Client(socket, clientUserName);
        client.readData();
        client.writeData();

    }

}
