package Apps;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;
public class minibombs extends JFrame implements ActionListener
{
	int m,posi=0,posj=0,c1[][],i,j;
	JButton up,down,left,right,refresh;
	JLabel c[][];
	minibombs()
	{
		super("MINIBOMBS");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		do
		{
			m=Integer.parseInt(JOptionPane.showInputDialog("Enter the size of board (between 10 to 20)"));
		}while(m<10 || m>20);
		setSize(m*30+400,m*30+150);
		
		c=new JLabel[m][m];
		c1=new int[m][m];
		int x=50,y=100,size=30;
		for(i=0;i<m;i++)
		{
			for(j=0;j<m;j++)
			{
				c[i][j]=new JLabel();
				c[i][j].setBounds(x,y,size,size);
				c[i][j].setOpaque(true);
				c[i][j].setBackground(Color.YELLOW);
				c[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));
				add(c[i][j]);
				y+=size;
			}
			x+=size;
			if(j==m)
			y=100;
		}
		JLabel ins=new JLabel("YOU ARE THE BLACK BLOCK. YOU CAN MOVE UP, DOWN, LEFT OR RIGHT.\n YOUR GOAL IS TO REACH THE WHITE BLOCK.");
		ins.setBounds(20,20,650,50);
		add(ins);
		c[0][0].setBackground(Color.darkGray);
		c[m-1][m-1].setBackground(Color.white);
		
		for(i=0;i<m;i++)
	    {
	        for(j=0;j<m;j++)
	        {
	        c1[i][j]=0;
	        }
	    }
		for(i=0;i<m;i++)
	    {
	        for(j=0;j<m/2;j++)
	        {
	        c1[i][new Random().nextInt(m)]=1;
	        }
	    }
		if(c1[0][0]==1)
	        c1[0][0]=0;
	    if(c1[m-1][m-1]==1)
	        c1[m-1][m-1]=0;
	    if(c1[0][1]==1 && c1[1][0]==1 && c1[1][1]==1)
	        c1[1][0]=0;
			
		up=new JButton("UP");
		up.addActionListener(this);
		up.setBounds(m*30+210,100,75,50);
		add(up);
		down=new JButton("DOWN");
		down.addActionListener(this);
		down.setBounds(m*30+210,220,75,50);
		add(down);
		left=new JButton("LEFT");
		left.addActionListener(this);
		left.setBounds(m*30+170,160,75,50);
		add(left);
		right=new JButton("RIGHT");
		right.addActionListener(this);
		right.setBounds(m*30+245,160,75,50);
		add(right);
		refresh=new JButton("NEW GAME");
		refresh.setBounds(m*30+200,275,100,50);
		refresh.addActionListener(this);
		add(refresh);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		int p1=posj,p2=posi;
		JButton b=(JButton)e.getSource();
		if(b.equals(left))
		{
			if(posi==0)
				JOptionPane.showMessageDialog(null, "INVALID MOVE");
			else
			{
				c[posi][posj].setBackground(Color.GREEN);
				posi--;
			}
		}
		else if(b.equals(right))
		{
			if(posi==(m-1))
				JOptionPane.showMessageDialog(null, "INVALID MOVE");
			else
			{
				c[posi][posj].setBackground(Color.GREEN);
				posi++;
			}
		}
		else if(b.equals(up))
		{
			if(posj==0)
				JOptionPane.showMessageDialog(null, "INVALID MOVE");
			else
			{
				c[posi][posj].setBackground(Color.GREEN);
				posj--;
			}
		}
		else if(b.equals(down))
		{
			if(posj==m-1)
				JOptionPane.showMessageDialog(null, "INVALID MOVE");
			else
			{
				c[posi][posj].setBackground(Color.GREEN);
				posj++;
			}
		}
		else if(b.equals(refresh))
		{
			dispose();
			new minibombs();
		}
		
		for(i=posi-1;i<=posi+1;i++)
	    {
	        if(i==-1 || i==m)
	            continue;
	        for(j=posj-1;j<=posj+1;j++)
	        {
	            if(j==-1 || j==m)
	            continue;
	            if(c1[i][j]!=1)
	                c[i][j].setBackground(Color.GREEN);
	        }
	    }
		
		if(posi==m-1 && posj==m-1)
		{
			JOptionPane.showMessageDialog(null,"!!! YOU WON THE GAME :) !!!" );
			for(i=0;i<m;i++)
			{
				for(j=0;j<m;j++)
					if(c1[i][j]==1)
						c[i][j].setBackground(Color.ORANGE);
			}
		}
		else if(c1[posi][posj]==1)
		{
			JOptionPane.showMessageDialog(null,"!!! YOU LOST THE GAME :( !!!" );
			for(i=0;i<m;i++)
			{
				for(j=0;j<m;j++)
					if(c1[i][j]==1)
						c[i][j].setBackground(Color.ORANGE);
			}
		}
		
		int count=0;  //to check if all the cells surrounding the current position are bombs
	    for(i=posi-1;i<=posi+1;i++)
	    {
	        if(i==-1 || i==m)
	            continue;
	        for(j=posj-1;j<=posj+1;j++)
	        {
	            if(j==-1 || j==m)
	            continue;
	            if(c1[i][j]!='*' && i!=p1 && j!=p2)
	                {
	                    count++;
	                    break;
	                }
	        }
	        if(count==1)
	            break;
	    }
	    if(count!=1)
	    {
	        if(posi==(m-1) || posi==0)
	            c1[posi][posj+1]='#';
	        else if(posj==(m-1) || posj==0)
	            c1[posi+1][posj]='#';

	    }
		c[posi][posj].setBackground(Color.darkGray);
	}
	public static void main(String a[])
	{
		new minibombs();
	}
}
