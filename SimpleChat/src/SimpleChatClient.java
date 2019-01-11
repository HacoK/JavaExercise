import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class SimpleChatClient implements ActionListener {

    JTextArea incoming;
    JTextField outgoing;
    JButton sendButton;

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    public static void main(String[] args) {
        new SimpleChatClient().go();
    }

    public void go(){
        //创建GUI
        JFrame frame = new JFrame("Ludicrously Simple Char Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 30);       //消息显示窗口
        incoming.setLineWrap(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        outgoing = new JTextField(20);      //文字发送窗口
        sendButton = new JButton("Send");   //发送按钮
        sendButton.addActionListener(this);
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);

        //初始化网络连接
        setUpNetworking();

        //启动一个新线程，在新线程中读取服务器发送的信息
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }

    /**
     * 初始化网络连接：
     * 建立Socket，获取输入流，准备好输出流
     */
    private void setUpNetworking(){
        try {
            /**
             * 建立Socket：
             * IP地址是本机回送地址，这样可以在一台电脑上测试各户端和服务器
             * 端口号选取为5000，注意，端口号只能在1024~65535之间选取
             */
            sock = new Socket("127.0.0.1", 5000);

            //获取输入流，用于读取服务器的数据
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);

            //准备好输出流，用于向服务器传送数据
            writer = new PrintWriter(sock.getOutputStream());

            System.out.println("networking established");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按键响应函数
     * 当按键按下时，向服务器发送消息
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        writer.println(outgoing.getText());
        writer.flush();
        outgoing.setText("");
        outgoing.requestFocus();
    }

    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String message;
            try {
                /**
                 * 在while循环中读取服务器传来的信息
                 * 若不为null，则将信息显示到控制台以及聊天窗口
                 */
                while((message = reader.readLine()) != null){
                    System.out.println("read" + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}