package Apps;
import javax.swing.*;  
import java.awt.event.*;  
import java.net.*;  
public class FindIP extends JFrame implements ActionListener{  
    JLabel l1;  
    JTextField tf;  
    JButton b1,b2;  
FindIP(){  
    super("IP Finder Tool");  
    l1=new JLabel("Enter Website URL:");  
    l1.setBounds(50,70,150,20);;  
    tf=new JTextField();  
    tf.setBounds(150,100,200,20); 
    
    b1=new JButton("Find IP Address of Site");  
    b1.setBounds(50,150,200,30);  
    b1.addActionListener(this); 
    
    b2=new JButton("Find Your IP Address");
    b2.setBounds(50,200,200,30);
    b2.addActionListener(this);
    add(l1);
    add(tf);  
    add(b1);
    add(b2);
    setSize(400,300);  
    setLayout(null);  
    setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    setVisible(true);  
}  
public void actionPerformed(ActionEvent e)
{  
	if(e.getSource()==b1)
	{
		String url=tf.getText();  
		try {  
			InetAddress ia=InetAddress.getByName(url);  
			String ip=ia.getHostAddress();  
			JOptionPane.showMessageDialog(this,"IP of "+url+" is: "+ip);  
		} 
		catch (UnknownHostException e1) 
		{  
        JOptionPane.showMessageDialog(this,e1.toString());  
		}  
	}
	else
	{
		try
		{
			String ip=InetAddress.getLocalHost().getHostAddress();
			JOptionPane.showMessageDialog(this,"Your IP is: "+ip);
		}
		catch (UnknownHostException e1) 
		{  
        JOptionPane.showMessageDialog(this,e1.toString());  
		}  
	}
}  
public static void main(String[] args) {  
    new FindIP();  
}  
}  