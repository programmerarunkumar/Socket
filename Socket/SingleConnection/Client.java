package SingleConnection;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 1234);

        Map data = new HashMap<>();
        data.put("FROM", "ClientSocket");
        data.put("TIME", System.currentTimeMillis());

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(data.toString());
        dataOutputStream.flush();
        dataOutputStream.close();

        socket.close();

    }

}
