package SingleConnection;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

//https://cs.gmu.edu/~setia/cs571/slides/lec6b.pdf

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(1234);
        Socket clientSocket = serverSocket.accept();

        DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
        String input = dataInputStream.readUTF();
        System.out.println(input);

        dataInputStream.close();
        clientSocket.close();
        serverSocket.close();

    }

}
