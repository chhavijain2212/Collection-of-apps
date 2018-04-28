package Apps;
import java.sql.*;
import java.awt.*;  
import java.awt.event.*;    
import javax.swing.*; 
import javax.swing.border.*; 
public class deardiary extends JFrame implements ActionListener
{
	JButton nentry,edit,view,back1,back2,save,about,submit,nuser,register,logout,publish; 
	JTextField text,title,email,fname,lname,dname,u,date;
	JLabel l,l1,l2;
	JComboBox box;
	JPasswordField pa1,pa2,p;
	static String user,pass;
	deardiary()
	{
		super("DEAR DIARY");
		setSize(600,600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String a[])
	{
		new deardiary().login();
	}
	void home()
	{
		nentry=new JButton("NEW ENTRY");
		nentry.addActionListener(this);
		nentry.setBounds(350, 100, 200, 100);
		add(nentry);
		view=new JButton("VIEW PREVIOUS ENTRIES");
		view.addActionListener(this);
		view.setBounds(350, 250, 200, 100);
		add(view);
		about=new JButton("ABOUT");
		about.addActionListener(this);
		about.setBounds(350, 400, 200, 100);
		add(about);
		logout=new JButton("LOG OUT");
		logout.setBounds(480,20,100,50);
		logout.addActionListener(this);
		add(logout);
		getContentPane().setBackground(new Color(120,190,180));
		l=new JLabel("WELCOME "+user);
		l.setBounds(50,200,250,100);
		l.setOpaque(true);
		l.setBackground(Color.white);
		l.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		add(l);
		
		setVisible(true);
	}
	void login()
	{
		l=new JLabel("WELCOME TO DEARDIARY");
		l.setBounds(200,30,200,100);
		add(l);
		l1=new JLabel("USER ID");
		l2=new JLabel("PASSWORD");
		u=new JTextField();
		p=new JPasswordField();
		l1.setBounds(130,200,100,50);
		l2.setBounds(130,300,100,50);
		add(l1);
		add(l2);
		u.setBounds(250, 200, 200, 50);
		p.setBounds(250, 300, 200, 50);
		add(u);
		add(p);
		submit=new JButton("SIGN IN");
		nuser=new JButton("SIGN UP");
		submit.setBounds(150,400,100,50);
		submit.addActionListener(this);
		nuser.setBounds(300,400,100,50);
		nuser.addActionListener(this);
		add(submit);
		add(nuser);
		about=new JButton("ABOUT");
		about.addActionListener(this);
		about.setBounds(220, 500, 100, 50);
		add(about);
		getContentPane().setBackground(new Color(180,120,180));
		
		setVisible(true);
	}
	void editentry(String para,String t,String d) 
	{
		if(para.equals("data	"))
		{
			back1=new JButton("GO BACK");
			back1.addActionListener(this);
			back1.setBounds(300,20,150,50);
			add(back1);
		}
		else
		{
			back2=new JButton("GO BACK");
			back2.addActionListener(this);
			back2.setBounds(300,20,150,50);
			add(back2);
		}
		save=new JButton("SAVE");
		save.addActionListener(this);
		save.setBounds(25,20,100,50);
		add(save);
		logout=new JButton("LOG OUT");
		logout.setBounds(470,20,100,50);
		logout.addActionListener(this);
		add(logout);
		text=new JTextField(para);
		JScrollPane s=new JScrollPane(text);
		s.setBounds(50,200,430,350);
		add(s);
		title=new JTextField(t);
		title.setBounds(50,100,300,50);
		add(title);
		date=new JTextField(d);
		date.setBounds(400,100,100,50);
		add(date);
		getContentPane().setBackground(new Color(153,250,255));
		
		setVisible(true);
	}
	void viewentry() throws Exception
	{
		back1=new JButton("GO BACK");
		back1.setBounds(450,475,100,50);
		back1.addActionListener(this);
		add(back1);
		setVisible(true);
		logout=new JButton("LOG OUT");
		logout.setBounds(500,20,100,50);
		logout.addActionListener(this);
		add(logout);
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost/diary?" + "user=root&password=12345");
		Statement st=c.createStatement();
		ResultSet r=st.executeQuery("select title,date from blog_post where authorid='"+user+"'");
		
		l=new JLabel("YOUR PREVIOUS ENTRIES");
		l.setBounds(50,50,100,50);
		add(l);
		box=new JComboBox();
		while(r.next())
		{
			box.addItem(r.getString("date")+"("+r.getString("title")+")");
		}
		box.setBounds(200,50,200,50);
		add(box);
		box.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					String b=box.getSelectedItem().toString();
					int bb=b.indexOf("(");
					b=b.substring(bb+1,b.length()-1);
				ResultSet r=st.executeQuery("select title,data,date from blog_post where authorid='"+user+"' and title='"+b+"'");
				if(r.next()){
					setVisible(false);
				l=new JLabel(r.getString("data"));
				l1=new JLabel(r.getString("title"));
				l2=new JLabel(r.getString("date"));
				JScrollPane s=new JScrollPane(l);
				s.setBounds(100,200,300,200);
				l1.setBounds(75,125,150,50);
				l2.setBounds(250,125,100,50);
				add(s);
				add(l1);
				add(l2);
				
				edit=new JButton("EDIT");
				edit.setBounds(275,475,100,50);
				add(edit);
				edit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						dispose();
						String s=l.getText();
						String s1=l1.getText();
						String s2=l2.getText();
						new deardiary().editentry(s,s1,s2);
					}
				});}
				setVisible(true);
			}
			catch(SQLException exc)
			{
				System.out.println(exc);
				JOptionPane.showMessageDialog(null, "ERROR OCCURED WHILE CONNECTING TO DATABASE");
			}
			}
		});
		
		getContentPane().setBackground(new Color(255,200,200));
		setVisible(true);
	}
	void newuser()
	{
		logout=new JButton("GO BACK");
		logout.setBounds(20,20,100,40);
		logout.addActionListener(this);
		add(logout);
		register=new JButton("REGISTER");
		register.setBounds(200,500,100,40);
		register.addActionListener(this);
		add(register);
		JLabel e=new JLabel("EMAIL");
		e.setBounds(100,50,100,50);
		add(e);
		email=new JTextField();
		email.setBounds(200,50,200,50);
		add(email);
		JLabel f=new JLabel("FIRST NAME");
		f.setBounds(100,120,100,50);
		add(f);
		fname=new JTextField();
		fname.setBounds(200,120,200,50);
		add(fname);
		JLabel l=new JLabel("LAST NAME");
		l.setBounds(100,190,100,50);
		add(l);
		lname=new JTextField();
		lname.setBounds(200,190,200,50);
		add(lname);
		JLabel d=new JLabel("DISPLAY NAME");
		d.setBounds(100,260,100,50);
		add(d);
		dname=new JTextField();
		dname.setBounds(200,260,200,50);
		add(dname);
		JLabel p1=new JLabel("PASSWORD");
		p1.setBounds(100,330,100,50);
		add(p1);
		pa1=new JPasswordField();
		pa1.setBounds(200, 330, 200, 50);
		add(pa1);
		JLabel p2=new JLabel("CONFIRM PASSWORD");
		p2.setBounds(100,400,100,50);
		add(p2);
		pa2=new JPasswordField();
		pa2.setBounds(200, 400, 200, 50);
		add(pa2);
		
		getContentPane().setBackground(new Color(153,255,200));
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/diary?" + "user=root&password=12345");
			Statement st=c.createStatement();
		
			JButton b=(JButton)e.getSource();
			if(b.equals(about))
			{
				JOptionPane.showMessageDialog(null, "\t\t\tWELCOME TO DEARDIARY\nTHIS IS AN APPLICATION WHERE YOU CAN MAINTAIN PRIVATE AND PUBLIC PUBLISHINGS\n\t\tTHANK YOU FOR USING DEARDIARY");
			}
			else if(b.equals(submit))
			{

				pass=p.getText();
				user=u.getText();
				ResultSet r=st.executeQuery("select display_name,password from blog_author where display_name='"+user+"'");
				if(!r.next())
					JOptionPane.showMessageDialog(null, "!!! WRONG USER NAME !!!");
				else if((r.getString("password")).equals(pass))
				{
					dispose();
					new deardiary().home();
				}
				else
					JOptionPane.showMessageDialog(null, "!!! WRONG PASSWORD !!!");
			}
			else if(b.equals(nuser))
			{
				dispose();
				new deardiary().newuser();
			}
			else if(b.equals(register))
			{
				pass=new String(pa1.getPassword());
				user=dname.getText();
				ResultSet r=st.executeQuery("select display_name from blog_author where display_name='"+user+"'");
				if(!r.next())
				{
					if(!((new String(pa1.getPassword())).equals((new String(pa2.getPassword())))))
						JOptionPane.showMessageDialog(null, "PASSWORD DOESN'T MATCH");
					else if(email.getText().equals(""))
						JOptionPane.showMessageDialog(null, "PLEASE GIVE EMAIL");
					else if(fname.getText().equals("") && lname.getText().equals(""))
						JOptionPane.showMessageDialog(null, "PLEASE GIVE FIRST OR LAST NAME");
					else
					{
						dispose();
						if(st.execute("insert into blog_author(first_name,last_name,display_name,email,password) values('"+fname.getText()+"','"+lname.getText()+"','"+user+"','"+email.getText()+"','"+pass+"')"))
							JOptionPane.showMessageDialog(null, "YOU ARE REGISTERED\nWELCOME TO DEARDIARY");
					}
					new deardiary().home();
				}
				else
					JOptionPane.showMessageDialog(null, "DISPLAY NAME ALREADY PRESENT");
			}
			else if(b.equals(logout))
			{
				user="";
				pass="";
				dispose();
				new deardiary().login();
			}
			else if(b.equals(nentry))
			{
				dispose();
				new deardiary().editentry("data","title","date");
			}
			else if(b.equals(view))
			{
				dispose();
				new deardiary().viewentry();
			}
			else if(b.equals(back1))
			{
				dispose();
				new deardiary().home();
			}
			else if(b.equals(back2))
			{
				JOptionPane.showMessageDialog(null, "IF YOU HAVE NOT SAVED, THE DATA WILL BE LOST");
				dispose();
				new deardiary().viewentry();
			}
			else if(b.equals(save))
			{
				String para=text.getText(), t=title.getText(), d=date.getText();
				if(para.equals(""))
					JOptionPane.showMessageDialog(null, "NO DATA IN THE ENTRY");
				else
				{
					boolean a=st.execute("select title from blog_post where authorid='"+user+"' and title='"+t+"' ")?st.execute("delete from blog_post where authorid='"+user+"' and title='"+t+"'"):false;
					if(st.execute("insert into blog_post(authorid,title,date,data) values('"+user+"','"+t+"','"+d+"','"+para+"')"))
						JOptionPane.showMessageDialog(null, "DATA SAVED");
				}
			}
		}
		catch(SQLException exc)
		{
			System.out.println(exc);
			JOptionPane.showMessageDialog(null, "ERROR OCCURED WHILE CONNECTING TO DATABASE");
		}
		catch(ClassNotFoundException exc)
		{
			System.out.println(exc);
			JOptionPane.showMessageDialog(null, "ERROR OCCURED WHILE CONNECTING TO DATABASE");
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null,exc);
		}
	}
}
