package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler implements Runnable {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    static ArrayList<ConnectionHandler> connections = new ArrayList<>();

    ConnectionHandler(Socket socket) throws Exception{
        this.socket = socket;
        this.dataInputStream =  new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

        connections.add(this);

        broadcastData( "User has joined the chat");
    }

    public void run() {

        try {
            String input;
            while ((input = dataInputStream.readUTF()) != null){
                if(input.equals("/quit")){
                    removeUser();
                }else {
                    broadcastData(input);
                }
            }
        }catch (Exception e){
            System.out.println("Exception in the ServerDataHandler. e : " + e);
            e.printStackTrace();
        }

    }

    private void broadcastData(String input) throws Exception {

        for(ConnectionHandler connectionHandler : connections){
            try {
                connectionHandler.dataOutputStream.writeUTF(input);
                connectionHandler.dataOutputStream.flush();
            }catch (Exception e){
                System.out.println("Exception while broadcasting the data. e : " + e);
                e.printStackTrace();
                connectionHandler.close();
            }
        }

    }

    private void removeUser() throws Exception {
        connections.remove(this);
        broadcastData("User has Left the chat");
    }

    public void close() {

        System.out.println("Client Close Method Started");

        try {
            if(dataInputStream != null){
                dataInputStream.close();
            }
            if(dataOutputStream != null){
                dataOutputStream.close();
            }
            if(!socket.isClosed()){
                socket.close();
            }
        }catch (Exception e){
            System.out.println("Exception while closing the socket. e : " + e);
            e.printStackTrace();
        }

        System.out.println("Client Close Method Completed");

    }

}

