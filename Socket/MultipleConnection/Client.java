package MultipleConnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Client Name");
        String input = scanner.nextLine();

        Map data = new HashMap<>();
        data.put("FROM", "Client" + input);
        data.put("TIME", System.currentTimeMillis());

        Socket socket = new Socket("localhost",1234);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(data.toString());
        dataOutputStream.flush();

        dataOutputStream.close();
        socket.close();

    }

}
