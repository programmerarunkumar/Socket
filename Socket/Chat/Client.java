package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String clientUserName;

    Client(Socket socket, String clientUserName) throws Exception {
        this.socket = socket;
        this.dataInputStream =  new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.clientUserName = clientUserName;
    }

    public void readData() throws Exception {

        ClientDataHandling clientDataHandling = new ClientDataHandling(socket);
        Thread thread = new Thread(clientDataHandling);
        thread.start();

    }

    public void writeData() {

        System.out.println("Started the client write");

        try {
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String data = scanner.nextLine();
                if(data.equals("/quit")){
                    dataOutputStream.writeUTF(data);
                    dataOutputStream.flush();
                    close();
                }else {
                    dataOutputStream.writeUTF(clientUserName + " : " + data);
                    dataOutputStream.flush();
                }
            }
        }catch (Exception e){
            System.out.println("Exception during the client write operation. e : " + e);
            e.printStackTrace();
        }finally {
            close();
        }

        System.out.println("Completed the client write");

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

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the UserName");
        String clientUserName = scanner.nextLine();

        Socket socket = new Socket("localhost",1234);

        Client client = new Client(socket, clientUserName);
        client.readData();
        client.writeData();

    }

}

