package Apps;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.*;
public class picpuzzle extends JFrame implements ActionListener
{
	JButton b[][]=new JButton[3][3], star,reset;
	int posi,posj,n;
	boolean resetcheck;
	Icon img[]=new ImageIcon[9];
	Integer ar[];
	picpuzzle(int n1)
	{
		super("Picture Puzzle");
		n=n1;
		String s=this.getClass().getClassLoader().getResource("").getPath();
		for(int i=0;i<9;i++)
		{
			img[i]=new ImageIcon(s.substring(0,s.length()-4)+"src/Apps/pic/"+(int)(i+n)+".jpg");
		}
		ar = new Integer[]{0,1,2,3,4,5,6,7,8};
		Collections.shuffle(Arrays.asList(ar));
		int k=0;
		int x=20,y=100;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(k==8)
				{	
					b[i][j]=new JButton();
					posi=i;
					posj=j;
				}
				else
					b[i][j]=new JButton(img[ar[k]]);
				b[i][j].addActionListener(this);
				k++;
				b[i][j].setBounds(x,y,100,100);
				x=x+100;
				add(b[i][j]);
			}
			x=20;
			y=y+100;
		}
		star=b[posi][posj];
		Icon sam=new ImageIcon(s.substring(0,s.length()-4)+"src/Apps/pic/main"+n+".jpg");
		JLabel sample=new JLabel(sam);
		sample.setBounds(390,150,180,180);
		sample.setBorder(new LineBorder(Color.GREEN));
		JLabel l=new JLabel("SAMPLE");
		l.setBounds(450,340,70,30);
		l.setForeground(Color.GREEN);
		reset=new JButton("RESET");
		reset.setBounds(430, 400, 100, 40);
		reset.addActionListener(this);
		add(reset);
		add(l);
		add(sample);
		
		JLabel i1=new JLabel("INSTRUCTIONS"),i2=new JLabel("TO SHIFT A PUZZLE PIECE TO BLANK SPACE, CLICK ON IT."),i3=new JLabel("REMEMBER, PIECES ADJACENT TO THE VACANT SPACE CAN ONLY BE SHIFTED, NOT DIAGONALLY");
		i1.setBounds(20,20,100,30);
		i1.setForeground(Color.BLUE);
		add(i1);
		i2.setBounds(20,40,500,30);
		i2.setForeground(Color.MAGENTA);
		add(i2);
		i3.setBounds(20,60,600,30);
		i3.setForeground(Color.MAGENTA);
		add(i3);
		
		setLayout(null);
		setSize(630,500);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		resetcheck=false;
	}
	public static void main(String a[])
	{
		new picpuzzle(level());
	}
	static int level()
	{
		int n=4;
		while(n==4)
		{
			n=Integer.parseInt(JOptionPane.showInputDialog("LEVEL 1,2 OR 3?"));
			switch(n)
			{
			case 1:n=1;
				break;
			case 2:n=21;
				break;
			case 3:n=11;
				break;
			default:n=4;
			}
		}
		return n;
	}
	boolean won()
	{
		int k=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(k!=ar[8] && !(b[i][j].getIcon()).equals(img[k]))
					return false;
				k++;
			}
		}
		return true;
	}
	boolean adjacent(JButton b1)
	{
		int a[]=position(b1);
		if((a[0]==posi-1 && a[1]==posj) || (a[0]==posi+1 && a[1]==posj) || (a[0]==posi && a[1]==posj-1) || (a[0]==posi && a[1]==posj+1))
			return true;
		return false;
	}
	int[] position(JButton b1)
	{
		int a[]=new int[2];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(b[i][j].equals(b1))
				{
					a[0]=i;
					a[1]=j;
					break;
				}
			}
		}
		return a;
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b1=(JButton)e.getSource();
		if(b1==reset)
		{
			resetcheck=true;
			dispose();
			new picpuzzle(n);
		}
		else
		{
			Icon i=new ImageIcon();
			i=b1.getIcon();
			if(i!=null)
			{
				int a[]=position(star);
				posi=a[0];
				posj=a[1];
				if(adjacent(b1))
				{
					b1.setIcon(null);
					star.setIcon(i);
					star=b1;
				}
			}
			if(won())
			{
				JOptionPane.showMessageDialog(this,"\t!!! CONGRATULATIONS !!!\n      !!! YOU WON !!!");
				dispose();
				main(new String[]{});
			}
		}
	}
}
