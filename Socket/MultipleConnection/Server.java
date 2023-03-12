package MultipleConnection;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://www.herongyang.com/JDK/Socket-ReverseEchoServer-Multi-Connection-Socket-Server.html

public class Server {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        ServerSocket serverSocket = new ServerSocket(1234);

        System.out.println("Going to start sleep");
        Thread.sleep(100000);
        System.out.println("Completed the sleep");

        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            ServerDataHandling data = new ServerDataHandling(socket);
            executorService.execute(data);
        }

        serverSocket.close();

    }

}
