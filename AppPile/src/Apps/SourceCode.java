package Apps;
import java.awt.*;  
import java.awt.event.*;  
import java.io.InputStream;  
import java.net.*;  
import javax.swing.*;
public class SourceCode extends JFrame implements ActionListener{  
    JTextField tf;  
    JTextArea ta;  
    JButton b;  
    JLabel l;  
    SourceCode(){  
        super("Source Code Finder");  
        l=new JLabel("Enter Website URL:");  
        l.setBounds(20,20,200,20);  
          
        tf=new JTextField("http://www.google.com");  
        tf.setBounds(20,50,250,30);  
          
        b=new JButton("Get Source Code");  
        b.setBounds(20, 100,150,30);  
        b.addActionListener(this);
          
        add(l);
        add(tf); 
        add(b);
        setSize(300,200);  
        setLayout(null);  
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);  
    }  
    public void actionPerformed(ActionEvent e)
    {
    	ta=new JTextArea();  
        JScrollPane sp=new JScrollPane(ta);
        sp.setBounds(50,150,600,500);
        String s=tf.getText();  
        if(s==null){}  
        else{  
            try{  
            URL u=new URL(s);  
            URLConnection uc=u.openConnection();  
          
            InputStream is=uc.getInputStream();  
            int i;  
            String sb="";  
            while((i=is.read())!=-1)
            {  
                sb+=((char)i); 
                if((char)i=='>')
                	sb+='\n';
            }    
            ta.setText(sb); 
            add(sp);//text area for source code to be added if source code is successfully found
            setSize(750,700);
            }
            catch(Exception ex){JOptionPane.showMessageDialog(this,"Exception: "+ex);}  
        }  
    }  
    public static void main(String[] args) {  
        new SourceCode();  
    }  
}  