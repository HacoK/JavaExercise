import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleChatServer {

    ArrayList clientOutputStreams;

    public static void main(String[] args) {
        new SimpleChatServer().go();
    }

    public void go(){
        clientOutputStreams = new ArrayList();
        try {
            //建立一个服务器端的Socket，监听5000端口
            ServerSocket serverSocket = new ServerSocket(5000);

            while(true){
                Socket clientSocket = serverSocket.accept();

                //当有客户端连接时，获取输出流，维护一个包含所有输出流的列表
                //每个输出流代表一个客户端
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                //创建一个新线程，在新线程中向所有的客户端发送消息
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket sock;

        /**
         * 构造方法:
         * 初始化Socket和输入流
         */
        public ClientHandler(Socket clientSocket){
            sock = clientSocket;
            try {
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
         * 读取消息，并发送给所有的客户端
         */
        @Override
        public void run() {
            String message;
            try {
                while((message = reader.readLine()) != null){
                    System.out.println("read" + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 将消息发送给所有的客户端
     */
    public void tellEveryone(String message){
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()){
            PrintWriter writer = (PrintWriter) it.next();
            writer.println(message);
            writer.flush();
        }
    }

}