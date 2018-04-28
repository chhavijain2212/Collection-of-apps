package Apps;
import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*;  
public class CharCount extends JFrame implements ActionListener
{  
    JLabel lb1,lb2,lb3;  
    JTextArea ta;  
    JButton b,pad,text;  
    CharCount(){  
        super("Char Word Count");  
        lb1=new JLabel("Characters: ");  
        lb1.setBounds(50,225,100,20);  
        lb2=new JLabel("Words: ");  
        lb2.setBounds(50,250,100,20); 
        lb3=new JLabel("Text");
        lb3.setBounds(10,25,50,20);
          
        ta=new JTextArea();  
        JScrollPane sp=new JScrollPane(ta);
        sp.setBounds(50,25,300,200); 
        sp.setBackground(Color.WHITE);
          
        b=new JButton("Count");  
        b.setBounds(50,280, 80,30);//x,y,w,h  
        b.addActionListener(this);  
      
        pad=new JButton("Pad Color");  
        pad.setBounds(140,280, 110,30);//x,y,w,h  
        pad.addActionListener(this);  
  
        text=new JButton("Text Color");  
        text.setBounds(260,280,110,30);//x,y,w,h  
        text.addActionListener(this);  
  
        add(lb1);
        add(lb2);
        add(lb3);
        add(sp);
        add(b);
        add(pad);
        add(text);  
          
        setSize(400,400);  
        setLayout(null);//using no layout manager  
        setVisible(true);  
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);  
    }  
    public void actionPerformed(ActionEvent e){  
        if(e.getSource()==b)
        {  
        	String text=ta.getText();  
        	lb1.setText("Characters: "+text.length());  
        	String words[]=text.split("\\s");  
        	lb2.setText("Words: "+words.length);  
        }
        else if(e.getSource()==pad)
        {  
            Color c=JColorChooser.showDialog(this,"Choose Color",Color.BLACK);  
            ta.setBackground(c);  
        }
        else if(e.getSource()==text){  
            Color c=JColorChooser.showDialog(this,"Choose Color",Color.BLACK);  
            ta.setForeground(c);  
        }  
    }  
    public static void main(String[] args) 
	{  
		new CharCount();  
	}
}  