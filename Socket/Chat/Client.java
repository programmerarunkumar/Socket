package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String clientUserName;

    Client(Socket socket, String clientUserName) throws Exception {
        this.socket = socket;
        this.dataInputStream =  new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.clientUserName = clientUserName;


    }

    public void readData() throws Exception {

        System.out.println("Started the read");

        ClientDataHandling clientDataHandling = new ClientDataHandling(socket);
        Thread thread = new Thread(clientDataHandling);
        thread.start();

        System.out.println("Completed the read");

    }

    public void writeData() throws Exception{

        System.out.println("Started the write");

        Scanner scanner = new Scanner(System.in);
        while (socket.isConnected()){
            String data = scanner.nextLine();
            dataOutputStream.writeUTF(clientUserName + " : " + data);
            dataOutputStream.flush();
        }

        System.out.println("Completed the write");

    }

}

