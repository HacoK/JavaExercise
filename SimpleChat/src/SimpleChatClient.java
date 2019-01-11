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
        //����GUI
        JFrame frame = new JFrame("Ludicrously Simple Char Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 30);       //��Ϣ��ʾ����
        incoming.setLineWrap(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        outgoing = new JTextField(20);      //���ַ��ʹ���
        sendButton = new JButton("Send");   //���Ͱ�ť
        sendButton.addActionListener(this);
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);

        //��ʼ����������
        setUpNetworking();

        //����һ�����̣߳������߳��ж�ȡ���������͵���Ϣ
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }

    /**
     * ��ʼ���������ӣ�
     * ����Socket����ȡ��������׼���������
     */
    private void setUpNetworking(){
        try {
            /**
             * ����Socket��
             * IP��ַ�Ǳ������͵�ַ������������һ̨�����ϲ��Ը����˺ͷ�����
             * �˿ں�ѡȡΪ5000��ע�⣬�˿ں�ֻ����1024~65535֮��ѡȡ
             */
            sock = new Socket("127.0.0.1", 5000);

            //��ȡ�����������ڶ�ȡ������������
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);

            //׼������������������������������
            writer = new PrintWriter(sock.getOutputStream());

            System.out.println("networking established");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ������Ӧ����
     * ����������ʱ���������������Ϣ
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
                 * ��whileѭ���ж�ȡ��������������Ϣ
                 * ����Ϊnull������Ϣ��ʾ������̨�Լ����촰��
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