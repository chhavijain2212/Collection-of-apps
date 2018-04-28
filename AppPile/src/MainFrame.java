import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Apps.*;
public class MainFrame extends JFrame implements ActionListener
{
	JButton calculator,puzzle,count,source,ip,mini,diary;
	MainFrame()
	{
		super("Java App Pile");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.CYAN);

		String s=MainFrame.class.getResource("").getPath();
		calculator=new JButton(new ImageIcon(MainFrame.class.getResource("/Apps/pic/calculator.jpg")));
		puzzle=new JButton(new ImageIcon(s+"Apps/pic/picpuzzle.jpg"));
		count=new JButton(new ImageIcon(s+"Apps/pic/wct.jpg"));
		source=new JButton(new ImageIcon(s+"Apps/pic/scg.jpg"));
		ip=new JButton(new ImageIcon(s+"Apps/pic/ip.jpg"));
		mini=new JButton(new ImageIcon(s+"Apps/pic/mini.jpg"));
		diary=new JButton(new ImageIcon(s+"Apps/pic/diary.jpg"));
		JLabel l1=new JLabel("JAVA APP PILE"),l2=new JLabel("WHAT DO YOU WANT TO DO??");
		l1.setBounds(210,10,200,50);
		l2.setBounds(180,50,250,50);
		l1.setForeground(Color.RED);
		l1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		l2.setFont(new Font("Forte",Font.PLAIN,18));
		l2.setForeground(Color.BLUE);
		add(l1);
		add(l2);
		add(calculator);
		add(puzzle);
		add(count);
		add(source);
		add(ip);
		add(mini);
		add(diary);
		calculator.setBounds(50,100,200,200);
		calculator.addActionListener(this);
		puzzle.setBounds(300,100,200,200);
		puzzle.addActionListener(this);
		ip.setBounds(550,100,200,200);
		ip.addActionListener(this);
		count.setBounds(50,350,200,200);
		count.addActionListener(this);
		source.setBounds(300,350,200,200);
		source.addActionListener(this);
		mini.setBounds(550,350,200,200);
		mini.addActionListener(this);
		diary.setBounds(280,600,200,150);
		diary.addActionListener(this);
		setSize(850,800);
		setVisible(true);
	}
	public static void main(String ar[])
	{
		new MainFrame();
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		if(b.equals(calculator))
			Calculator.main(new String[]{});
		else if(b.equals(puzzle))
			picpuzzle.main(new String[]{});
		else if(b.equals(count))
			CharCount.main(new String[]{});
		else if(b.equals(source))
			SourceCode.main(new String[]{});
		else if(b.equals(ip))
			FindIP.main(new String[]{});
		else if(b.equals(mini))
			minibombs.main(new String[]{});
		else
			deardiary.main(new String[]{});
	}
}
