package Chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private static ArrayList<ConnectionHandler> userList = new ArrayList<>();

    Server() throws Exception {
        this.serverSocket = new ServerSocket(1234);;
    }

    public void startServer() {

        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Client has connected");

                ConnectionHandler connectionHandler = new ConnectionHandler(socket);
                Thread thread = new Thread(connectionHandler);
                thread.start();

                userList.add(connectionHandler);
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

            for(ConnectionHandler connectionHandler : userList){
                connectionHandler.close();
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

