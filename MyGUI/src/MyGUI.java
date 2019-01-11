import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyGUI {
	JFrame frame;
	JLabel label;
	boolean msg=false;
	int red1=(int)(Math.random()*256);
	int green1=(int)(Math.random()*256);
	int blue1=(int)(Math.random()*256);
	
	int red2=(int)(Math.random()*256);
	int green2=(int)(Math.random()*256);
	int blue2=(int)(Math.random()*256);
	public static void main(String[] args){
		MyGUI gui=new MyGUI();
		gui.go();
	}
	public void go(){
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label=new JLabel("I'm a label");
		class MyDrawPanel extends JPanel{
			public void paintComponent(Graphics g){
			    Graphics2D g2d=(Graphics2D)g;
			    if(msg)
			    {
			    red1=(int)(Math.random()*256);
				green1=(int)(Math.random()*256);
				blue1=(int)(Math.random()*256);
				red2=(int)(Math.random()*256);
			    green2=(int)(Math.random()*256);
				blue2=(int)(Math.random()*256);
				msg=false;
				}
				Color startColor=new Color(red1,green1,blue1);
				Color endColor=new Color(red2,green2,blue2);
				GradientPaint gradient=new GradientPaint(90,160,startColor,180,180, endColor);
				g2d.setPaint(gradient);
				
				g.fillOval(90, 160, 100, 100);
			}
		}
		MyDrawPanel drawPanel=new MyDrawPanel(); 
		
		class ColorListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				msg=true;
				frame.repaint();
			}
		}
		JButton colorButton=new JButton("Change Circle");
		colorButton.addActionListener(new ColorListener());
		
		class LabelListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				label.setText("    Ouch!    ");
			}
		}
		JButton labelButton=new JButton("Change Label");
		labelButton.addActionListener(new LabelListener());

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
		frame.getContentPane().add(BorderLayout.WEST, label);
		frame.getContentPane().add(BorderLayout.EAST, labelButton);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
		

	};
	
}
