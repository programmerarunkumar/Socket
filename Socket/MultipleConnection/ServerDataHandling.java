package MultipleConnection;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ServerDataHandling implements Runnable{

    private Socket socket;

    ServerDataHandling(Socket socket){
        this.socket = socket;
    }

    public void run() {

        try {
            Long startTime = System.currentTimeMillis();
            System.out.println("ThreadName : " + Thread.currentThread().getName() + " Start : " + startTime);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println("Data : " + dataInputStream.readUTF());

            Long endTime = System.currentTimeMillis();
            System.out.println("ThreadName : " + Thread.currentThread().getName() + " End : " + endTime);
            System.out.println("ThreadName : " + Thread.currentThread().getName() + " TotalTime : " + (endTime-startTime));
        }catch (Exception e){
            System.out.println("Exception : " + e);
        }

    }

}
