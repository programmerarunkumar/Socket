package Chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private static ArrayList<ServerDataHandler> userList = new ArrayList<>();

    Server() throws Exception {
        this.serverSocket = new ServerSocket(1234);;
    }

    public void startServer() throws Exception{

        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Client has connected");

                ServerDataHandler serverDataHandler = new ServerDataHandler(socket);
                Thread thread = new Thread(serverDataHandler);
                thread.start();

                userList.add(serverDataHandler);
            }
        }catch (Exception e){
            closeServerSocket();
            e.printStackTrace();
        }

    }

    private void closeServerSocket(){
        try {
            if(serverSocket != null){
                serverSocket.close();
            }

            for(ServerDataHandler serverDataHandler : userList){
                serverDataHandler.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

