package Apps;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator extends JFrame
{
	boolean setClear=true;
	double number, memValue;
	char op;
	String digit[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "." }, operator[] = {"/", "sqrt", "*", "%", "-", "1/X", "+", "=" }, memory[] = {"MC", "MR", "MS", "M+" },special[] = {"Backspc", "C", "CE" };

	JButton digitButton[]=new JButton[12], operatorButton[]=new JButton[8], memoryButton[]=new JButton[4], specialButton[]=new JButton[3];
	
	JLabel displayLabel=new JLabel("0",JLabel.RIGHT);
	JLabel memLabel=new JLabel(" ",JLabel.RIGHT);
	final int HEIGHT=50, WIDTH=50, H_SPACE=10,V_SPACE=10,TOPX=30, TOPY=30;
	
	Calculator()
	{
		super("Calculator");
		setSize(450,450);
		setBackground(Color.WHITE);
		setLayout(null);

		displayLabel.setOpaque(true);
		displayLabel.setBackground(Color.CYAN);
		displayLabel.setForeground(Color.BLACK);
		displayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		displayLabel.setBounds(110,40,300,40);
		add(displayLabel);
		
		memLabel.setBounds(TOPX,  TOPY+HEIGHT+ V_SPACE,WIDTH+20, HEIGHT);
		memLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(memLabel);
		
		int y=TOPY+2*(HEIGHT+V_SPACE);//after the displaylabel, memlabel and special buttons
		for(int i=0; i<memoryButton.length; i++)
		{
		memoryButton[i]=new JButton(memory[i]);
		memoryButton[i].setBounds(TOPX,y,WIDTH+30,HEIGHT);
		memoryButton[i].setForeground(Color.RED);
		memoryButton[i].addActionListener(new MemoryButton(this));
		add(memoryButton[i]);
		y+=HEIGHT+V_SPACE;
		}
		
		int x=TOPX+WIDTH+H_SPACE+20;
		for(int i=0;i<specialButton.length;i++)
		{
		specialButton[i]=new JButton(special[i]);
		specialButton[i].setBounds(x,TOPY+HEIGHT+V_SPACE,WIDTH+40,HEIGHT);
		specialButton[i].setForeground(Color.MAGENTA);
		specialButton[i].addActionListener(new SpecialButton(this));
		add(specialButton[i]);
		x+=2*WIDTH+H_SPACE;
		}
		
		
		
		
		int digitX=TOPX+WIDTH+30+H_SPACE;
		int digitY=TOPY+2*(HEIGHT+V_SPACE);
		x=digitX;  y=digitY;
		for(int i=0;i<digitButton.length;i++)
		{
		digitButton[i]=new JButton(digit[i]);
		digitButton[i].setBounds(x,y,WIDTH,HEIGHT);
		digitButton[i].setForeground(Color.BLUE);
		digitButton[i].addActionListener(new DigitButton(this));
		add(digitButton[i]);
		x+=WIDTH+H_SPACE;
		if((i+1)%3==0){x=digitX; y+=HEIGHT+V_SPACE;}
		}

		int opsX=digitX+2*WIDTH+H_SPACE+H_SPACE;
		int opsY=digitY;
		x=opsX;  
		y=opsY;
		for(int i=0;i<operatorButton.length;i++)
		{
		x+=WIDTH+H_SPACE;
		operatorButton[i]=new JButton(operator[i]);
		operatorButton[i].setBounds(x,y,WIDTH+5,HEIGHT);
		operatorButton[i].setForeground(Color.RED);
		operatorButton[i].addActionListener(new OperatorButton(this));
		add(operatorButton[i]);
		if((i+1)%2==0)
		{
			x=opsX;
			y+=HEIGHT+V_SPACE;
		}
		}

		addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent ev)
		{System.exit(0);}
		});
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public String getFormattedText(double temp)
	{
		String resText=""+temp;
		if(resText.lastIndexOf(".0")>0)
			resText=resText.substring(0,resText.length()-2);
		return resText;
	}
	public static void main(String[] args)
	{
		new Calculator();
	}
}

class DigitButton implements ActionListener
{
	Calculator cl;
	DigitButton(Calculator c)
	{
		cl=c;
	}
	boolean isInString(String s, char ch)
	{
		for(int i=0; i<s.length();i++) 
		{
			if(s.charAt(i)==ch) 
				return true;
		}
		return false;
	}

	public void actionPerformed(ActionEvent e)
	{
		String tempText=((JButton)e.getSource()).getLabel();

		if(tempText.equals("."))
		{
			if(cl.setClear) 
			{
				cl.displayLabel.setText("0.");
				cl.setClear=false;
			}
			else if(!isInString(cl.displayLabel.getText(),'.'))
				cl.displayLabel.setText(cl.displayLabel.getText()+".");
			return;
		}

		int index=0;
		try
		{
			index=Integer.parseInt(tempText);
		}
		catch(NumberFormatException e1)
		{return;}

		if (index==0 && cl.displayLabel.getText().equals("0")) 
			return;

		if(cl.setClear)
        {
			cl.displayLabel.setText(""+index);
			cl.setClear=false;
		}
		else
			cl.displayLabel.setText(cl.displayLabel.getText()+index);
	}
}

class OperatorButton implements ActionListener
{
	Calculator cl;
	OperatorButton(Calculator c)
	{
		cl=c;
	}
	public void actionPerformed(ActionEvent ev)
	{
		String opText=((JButton)ev.getSource()).getLabel();

		cl.setClear=true;
		double temp=Double.parseDouble(cl.displayLabel.getText());

		if(opText.equals("1/x"))
		{
			try
			{
				double tempd=1/(double)temp;
				cl.displayLabel.setText(cl.getFormattedText(tempd));
			}
			catch(ArithmeticException e1)
            {cl.displayLabel.setText("Divide by 0.");}
			return;
		}
		if(opText.equals("sqrt"))
		{
			try
			{
				double tempd=Math.sqrt(temp);
				cl.displayLabel.setText(cl.getFormattedText(tempd));
			}
        	catch(ArithmeticException excp)
	        {cl.displayLabel.setText("Divide by 0.");}
			return;
		}
		if(!opText.equals("="))
		{
			cl.number=temp;
			cl.op=opText.charAt(0);
			return;
		}
		switch(cl.op)
		{
			case '+':
				temp+=cl.number;
				break;
			case '-':
				temp=cl.number-temp;break;
			case '*':
				temp*=cl.number;break;
			case '%':
				try
				{temp=cl.number%temp;}
				catch(ArithmeticException e1)
				{cl.displayLabel.setText("Divide by 0."); return;}
				break;
			case '/':
				try
				{temp=cl.number/temp;}
				catch(ArithmeticException e1)
                {cl.displayLabel.setText("Divide by 0."); return;}
				break;
		}

		cl.displayLabel.setText(cl.getFormattedText(temp));
		cl.number=temp;
}
}

class MemoryButton implements ActionListener
{
	Calculator cl;
	MemoryButton(Calculator c)
	{
		cl=c;
	}
	public void actionPerformed(ActionEvent e)
	{
			char memop=((JButton)e.getSource()).getLabel().charAt(1);

			cl.setClear=true;
			double temp=Double.parseDouble(cl.displayLabel.getText());

			switch(memop)
			{
			case 'C': 
					cl.memLabel.setText(" ");
					cl.memValue=0.0;
					break;
			case 'R': 
					cl.displayLabel.setText(cl.getFormattedText(cl.memValue));
					break;
			case 'S':
					cl.memValue=0.0;
			case '+': 
					cl.memValue+=Double.parseDouble(cl.displayLabel.getText());
					if(cl.displayLabel.getText().equals("0") || cl.displayLabel.getText().equals("0.0")  )
						cl.memLabel.setText(" ");
					else 
						cl.memLabel.setText("M");	
					break;
			}
	}
}

class SpecialButton implements ActionListener
{
	Calculator cl;
	SpecialButton(Calculator c)
	{
		cl=c;
	}
	String backSpace(String s)
	{
		String Res="";
		for(int i=0; i<s.length()-1; i++) 
			Res+=s.charAt(i);
		return Res;
	}

	public void actionPerformed(ActionEvent e)
	{
		String opText=((JButton)e.getSource()).getLabel();
		if(opText.equals("Backspc"))
		{
			String s=backSpace(cl.displayLabel.getText());
			if(s.equals("")) 
				cl.displayLabel.setText("0");
			else 
				cl.displayLabel.setText(s);
		}
		else if(opText.equals("C")) 
		{
			cl.number=0.0; cl.op=' '; 
			cl.memValue=0.0;
			cl.memLabel.setText(" ");
		}
		else
		{
			cl.displayLabel.setText("0");
			cl.setClear=true;
		}
	}
}