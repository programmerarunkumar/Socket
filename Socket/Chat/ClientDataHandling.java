package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientDataHandling implements Runnable{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    ClientDataHandling(Socket socket) throws Exception{
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

    private void readData() throws Exception {

        while (socket.isConnected()){
            String data = dataInputStream.readUTF();
            System.out.println(data);
        }
        close();

    }

    private void close() throws Exception{

        System.out.println("Close Method Called");

        if(!socket.isClosed()){
            socket.close();
        }
        if(dataInputStream != null){
            dataInputStream.close();
        }
        if(dataOutputStream != null){
            dataOutputStream.close();
        }

    }

}

