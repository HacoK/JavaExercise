import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Animation {
	int x=2;
	int y=1;
	public static void main(String[] args){
		Animation gui=new Animation();
		gui.go();
	}
	public void go(){
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyDrawPanelP drawP=new MyDrawPanelP();
		frame.getContentPane().add(drawP);
		frame.setSize(500, 300);
		frame.setVisible(true);
		for(int i=0;i<124;i++,x++,y++){
			x++;
			drawP.repaint();
			try{
				Thread.sleep(50);
			}catch(Exception ex){}
		}
	}
	class MyDrawPanelP extends JPanel{
		public void paintComponent(Graphics p){
			p.setColor(Color.white);
			p.fillRect(0, 0, 500, 250);
			
			p.setColor(Color.BLUE);
			p.fillRect(x, y, 500-x*2, 250-y*2);
		}
	}
}


