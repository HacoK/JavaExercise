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
            //����һ���������˵�Socket������5000�˿�
            ServerSocket serverSocket = new ServerSocket(5000);

            while(true){
                Socket clientSocket = serverSocket.accept();

                //���пͻ�������ʱ����ȡ�������ά��һ������������������б�
                //ÿ�����������һ���ͻ���
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                //����һ�����̣߳������߳��������еĿͻ��˷�����Ϣ
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
         * ���췽��:
         * ��ʼ��Socket��������
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
         * ��ȡ��Ϣ�������͸����еĿͻ���
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
     * ����Ϣ���͸����еĿͻ���
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