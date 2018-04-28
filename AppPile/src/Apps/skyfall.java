package Apps;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
public class skyfall implements ActionListener,KeyListener 
{
	JFrame f;
	JButton play,ins,hm,pp;
	String s=this.getClass().getClassLoader().getResource("").getPath();
	
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		if(b==pp)
		{
			if((pp.getIcon()).equals(new ImageIcon(s+"Apps/obj/pause.jpg")))
			{
				//new objects().pause();
				pp.setIcon(null);
				Icon i=new ImageIcon(s+"Apps/obj/playb.jpg");
				pp.setIcon(i);
			}
			else
			{
				//new objects().play();
				pp.setIcon(new ImageIcon(s+"Apps/obj/pause.jpg"));
			}
			return;
		}
		f.dispose();
		if(b==play)
		new skyfall(3);
		else if(b==ins)
		new skyfall(2);
		else if(b==hm)
		new skyfall(1);
	}
	public void keyPressed(KeyEvent e)
	{
		
	}
	public void keyTyped(KeyEvent e)
	{
		
	}
	public void keyReleased(KeyEvent e)
	{
		
	}
	public static void main(String a[])
	{
		new skyfall(1);
	}
	skyfall(int n)
	{
		f=new JFrame("Save Your Head");
		switch(n)
		{
		case 1:home();
			break;
		case 2:instruct();
			break;
		case 3:game();
			break;
		}
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(75, 75, 800, 600);
		f.setVisible(true);
	
	}
	public void home()
	{
		JLabel l=new JLabel(new ImageIcon(s+"Apps/obj/home.jpg"));
		play=new JButton(new ImageIcon(s+"Apps/obj/play.jpg"));
		ins=new JButton(new ImageIcon(s+"Apps/obj/ins.jpg"));
		play.setBounds(40,400,210,100);
		ins.setBounds(300,400,500,120);
		play.setBorder(null);
		play.setBackground(Color.WHITE);
		ins.setBorder(null);
		ins.setBackground(Color.WHITE);
		play.addActionListener(this);
		ins.addActionListener(this);
		l.setBounds(100, 50, 550, 350);
		l.setBorder(new LineBorder(Color.BLACK));
		f.add(l);
		f.add(play);
		f.add(ins);
		f.getContentPane().setBackground(Color.WHITE);
	}
	public void instruct()
	{
		f.getContentPane().setBackground(Color.CYAN);
		play=new JButton(new ImageIcon(s+"Apps/obj/play.jpg"));
		JLabel l=new JLabel(new ImageIcon(s+"Apps/obj/ins.jpg"));
		play.setBounds(560,470,210,80);
		play.addActionListener(this);
		play.setBorder(null);
		play.setBackground(Color.WHITE);
		l.setBounds(20, 20, 480, 100);
		f.add(l);
		f.add(play);
	}
	public void game()
	{
		f.getContentPane().setBackground(Color.BLACK);
		hm=new JButton("<- Go To Home");
		pp=new JButton(new ImageIcon(s+"Apps/obj/pause.jpg"));
		hm.setBounds(0, 0, 150, 50);
		pp.setBounds(735,0,50,50);
		hm.addActionListener(this);
		pp.addActionListener(this);
		f.add(hm);
		f.add(pp);
	}
}
class objects extends Thread
{
	objects obj[]=new objects[10];
	objects player=new objects();
	public void pause()
	{
		for(int i=0;i<10;i++)
			obj[i].suspend();
		player.suspend();
	}
	public void play()
	{
		for(int i=0;i<10;i++)
			obj[i].resume();
		player.resume();
	}
}
