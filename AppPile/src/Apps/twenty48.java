package Apps;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
public class twenty48 extends JFrame implements ActionListener
{
	JButton up,down,left,right,refresh;
	JLabel c[][];
	int c1[][],m;
	
	twenty48()
	{
		super("2048");
		m=0;
		do
		{
			m=Integer.parseInt(JOptionPane.showInputDialog("Enter the size of board (between 3 and 6)"));
		}while(m<3 || m>6);
		
		
		setSize(m*30+500,m*30+300);
		setLayout(null);
		getContentPane().setBackground(new Color(220,255,190));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		c=new JLabel[m][m];
		c1=new int[m][m];
		int x=50,y=100,size=50,i,j;
		for(i=0;i<m;i++)
		{
			for(j=0;j<m;j++)
			{
				c[i][j]=new JLabel("",SwingConstants.CENTER);
				c[i][j].setBounds(x,y,size,size);
				c[i][j].setOpaque(true);
				c[i][j].setBackground(new Color(220,255,255));
				c[i][j].setBorder(new BevelBorder(BevelBorder.LOWERED));
				c[i][j].setFont(new Font("Calibri", Font.PLAIN, 20));
				add(c[i][j]);
				y+=size;
			}
			x+=size;
			if(j==m)
			y=100;
		}
		for(i=0;i<m;i++)
	    {
	        for(j=0;j<m;j++)
	        {
	        c1[i][j]=0;
	        }
	    }
		
		
		JLabel l=new JLabel("MAKE A 2048");
		l.setForeground(Color.RED);
		l.setBounds(100,20,200,50);
		l.setFont(new Font("Forte",Font.ITALIC,24));
		add(l);
		
		up=new JButton("UP");
		up.addActionListener(this);
		up.setBounds(m*30+310,100,75,50);
		up.setBackground(new Color(110,100,255));
		up.setForeground(Color.WHITE);
		up.setFont(new Font("Calibri", Font.BOLD, 16));
		add(up);
		
		down=new JButton("DOWN");
		down.addActionListener(this);
		down.setBounds(m*30+310,220,85,50);
		down.setBackground(new Color(110,100,255));
		down.setForeground(Color.WHITE);
		down.setFont(new Font("Calibri", Font.BOLD, 16));
		add(down);
		
		left=new JButton("LEFT");
		left.addActionListener(this);
		left.setBounds(m*30+260,160,75,50);
		left.setBackground(new Color(110,100,255));
		left.setForeground(Color.WHITE);
		left.setFont(new Font("Calibri", Font.BOLD, 16));
		add(left);
		
		right=new JButton("RIGHT");
		right.addActionListener(this);
		right.setBounds(m*30+350,160,75,50);
		right.setBackground(new Color(110,100,255));
		right.setForeground(Color.WHITE);
		right.setFont(new Font("Calibri", Font.BOLD, 16));
		add(right);
		
		refresh=new JButton("NEW GAME");
		refresh.setBounds(m*30+280,275,150,50);
		refresh.addActionListener(this);
		refresh.setBackground(new Color(255,130,200));
		refresh.setForeground(Color.WHITE);
		refresh.setFont(new Font("Calibri", Font.BOLD, 16));
		add(refresh);
		
		
		this.getRandom();
		setVisible(true);
	}
	
	public static void main(String ar[])
	{
		new twenty48();
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		JButton e=(JButton)ev.getSource();
		if(e.equals(refresh))
		{
			this.dispose();
			new twenty48();
		}
		else if(e.equals(up))
			moveUp();
		else if(e.equals(down))
			moveDown();
		else if(e.equals(left))
			moveLeft();
		else if(e.equals(right))
			moveRight();
	}
	
	public void moveUp()
	{
		for(int j=0;j<m;j++)
		{
			for(int i=0;i<m-1;i++)
			{
				while(c1[i][j]==0)
					this.shiftUp(i, j);
				for(int k=i;k>0;k--)
				{
					if(c1[k-1][j]==0)
						c1[k-1][j]=c1[k][j];
				}
				if(c1[i][j]==c1[i+1][j])
				{
					c1[i][j]*=2;
					this.shiftUp(i+1,j);
				}
			}
		}
		this.setVal();
		if(!this.gameOver())
			this.getRandom();
	}
	public void shiftUp(int r,int c)
	{
		for(int i=r;i<m-1;i++)
		{
			c1[i][c]=c1[i+1][c];
		}
		c1[m-1][c]=0;
		this.setVal();
	}
	
	public void moveDown()
	{
		for(int j=0;j<m;j++)
		{
			for(int i=m-1;i>0;i--)
			{
				while(c1[i][j]==0)
					this.shiftDown(i, j);
				if(c1[i][j]==c1[i-1][j])
				{
					c1[i][j]*=2;
					this.shiftDown(i-1,j);
				}
			}
		}
		this.setVal();
		if(!this.gameOver())
			this.getRandom();
	}
	public void shiftDown(int r,int c)
	{
		for(int i=r;i>0;i--)
		{
			c1[i][c]=c1[i-1][c];
		}
		c1[0][c]=0;
		this.setVal();
	}
	
	public void moveLeft()
	{
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m-1;j++)
			{
				while(c1[i][j]==0)
					this.shiftLeft(i, j);
				if(c1[i][j]==c1[i][j+1])
				{
					c1[i][j]*=2;
					this.shiftLeft(i,j+1);
				}
			}
		}
		this.setVal();
		if(!this.gameOver())
			this.getRandom();
	}
	public void shiftLeft(int r,int c)
	{
		for(int i=c;i<m-1;i++)
		{
			c1[r][i]=c1[r][i+1];
		}
		c1[r][m-1]=0;
		this.setVal();
	}
	
	public void moveRight()
	{
		for(int i=0;i<m;i++)
		{
			for(int j=m-1;j>0;j--)
			{
			while(c1[i][j]==0)
					this.shiftLeft(i,j);
				if(c1[i][j]==c1[i][j-1])
				{
					c1[i][j]*=2;
					this.shiftRight(i,j-1);
				}
			}
		}
		this.setVal();
		if(!this.gameOver())
			this.getRandom();
	}
	public void shiftRight(int r,int c)
	{
		for(int i=c;i>0;i--)
		{
			c1[r][i]=c1[r][i-1];
		}
		c1[r][0]=0;
		this.setVal();
	}
	
	public void getRandom()
	{
		int i,j;
		do
		{
			i=new Random().nextInt(m);
			j=new Random().nextInt(m);
			if(c1[i][j]==0)
			{
				c1[i][j]=(new Random().nextInt(2)+1)*2;
				c[i][j].setText(Integer.toString(c1[i][j]));
				break;
			}
		}while(true);
	}
	
	public void setVal()
	{
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				if(c1[i][j]==0)
					c[i][j].setText("");
				else
					c[i][j].setText(Integer.toString(c1[i][j]));
			}
		}
	}
	
	public boolean gameOver()
	{
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
				if(c1[i][j]==0)
					return false;
		}
		for(int i=0;i<m;i+=2)
		{
			for(int j=0;j<m;j+=2)
			{
				System.out.println(i+" "+j);
				if(i>0 && i<m-1 && j>0 && j<m-1)
				{
					int n=c1[i][j];
					if(n==c1[i-1][j] || n==c1[i][j-1] || n== c1[i+1][j] || n==c1[i][j+1])
						return false;
				}
				else if(i==0 && j<m-1)
				{
					if(c1[i][j]==c1[i+1][j] || c1[i][j]==c1[i][j+1])
						return false;
				}
				else if(i==m-1 && j<m-1)
				{
					if(c1[i][j]==c1[i-1][j] || c1[i][j]==c1[i][j+1])
						return false;
				}
				else if(j==0 && i<m-1)
				{
					if(c1[i][j]==c1[i+1][j] || c1[i][j]==c1[i][j+1])
						return false;
				}
				else if(j==m-1 && i<m-1)
				{
					if(c1[i][j]==c1[i+1][j] || c1[i][j]==c1[i][j-1])
						return false;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "GAME OVER");
		return true;
	}
}
