package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientListener implements Runnable{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    ClientListener(Socket socket) throws Exception{
        this.socket = socket;
        this.dataInputStream =  new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void run() {

        try {
            readData();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void readData() {

        System.out.println("Started the client read");

        try {
            while (socket.isConnected()){
                String data = dataInputStream.readUTF();
                System.out.println(data);
            }
        }catch (Exception e){
            System.out.println("Exception during the client read operation. e : " + e);
            e.printStackTrace();
        }finally {
            close();
        }

        System.out.println("Completed the client read");

    }

    private void close() {

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
            System.out.println("Exception while closing the client. e : " + e);
            e.printStackTrace();
        }

        System.out.println("Client Close Method Completed");

    }

}

