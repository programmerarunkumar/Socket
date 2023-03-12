package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerDataHandler implements Runnable {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    static ArrayList<ServerDataHandler> userList = new ArrayList<>();

    ServerDataHandler(Socket socket) throws Exception{
        this.socket = socket;
        this.dataInputStream =  new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

        userList.add(this);

        broadcastData( "User has joined the chat");
    }

    @Override
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

        for(ServerDataHandler serverDataHandler : userList){
            try {
                serverDataHandler.dataOutputStream.writeUTF(input);
                serverDataHandler.dataOutputStream.flush();
            }catch (Exception e){
                System.out.println("Exception while broadcasting the data. e : " + e);
                e.printStackTrace();
            }
        }

    }

    private void removeUser() throws Exception {
        userList.remove(this);
        broadcastData("User has Left the chat");
    }

    public void close() {

        try {
            if(!socket.isClosed()){
                socket.close();
            }
            if(dataInputStream != null){
                dataInputStream.close();
            }
            if(dataOutputStream != null){
                dataOutputStream.close();
            }
        }catch (Exception e){
            System.out.println("Exception while closing the socket. e : " + e);
            e.printStackTrace();
        }

    }

}
