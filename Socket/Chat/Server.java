package Chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private static ArrayList<ServerHandler> userList = new ArrayList<>();

    Server() throws Exception {
        this.serverSocket = new ServerSocket(1234);;
    }

    public void startServer() {

        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Client has connected");

                ServerHandler serverHandler = new ServerHandler(socket);
                Thread thread = new Thread(serverHandler);
                thread.start();

                userList.add(serverHandler);
            }
        }catch (Exception e){
            System.out.println("Exception while handling the connection. e : " + e);
            e.printStackTrace();
        }finally {
            closeServerSocket();
        }

    }

    private void closeServerSocket(){

        System.out.println("Server Close Method Started");

        try {
            if(serverSocket != null){
                serverSocket.close();
            }

            for(ServerHandler serverHandler : userList){
                serverHandler.close();
            }
        }catch (Exception e){
            System.out.println("Exception while closing the server socket. e : " + e);
            e.printStackTrace();
        }

        System.out.println("Server Close Method Completed");

    }

    public static void main(String[] args) throws Exception{

        Server server = new Server();
        server.startServer();

    }

}

