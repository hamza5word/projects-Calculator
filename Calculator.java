import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// SIMPLE CALCULATOR GRAPHICAL INTERFACE

public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private JPanel display;
	private JPanel keypad;
	private String expression = "";

	public Calculator() {
		// SETTING UP COMPONENTS
		setContainer();
		setDisplay();
		setKeypad();
		// ADDING CONPONENTS
		this.container.add(this.display, BorderLayout.NORTH);
		this.container.add(this.keypad, BorderLayout.CENTER);
		setContentPane(this.container);
	}
	
	private void setContainer() {
		this.container = new JPanel();
		container.setLayout(new BorderLayout());
	}
	
	private void setDisplay() {
		this.display = new JPanel();
		display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
		
		JTextField display1 = new JTextField();
		display1.setMargin(new Insets(12, 12, 12, 12));
		display1.setFont(new Font("Calibri", Font.BOLD, 40));
		display1.setHorizontalAlignment(JTextField.CENTER);
		display1.setBackground(Color.WHITE);
		display1.setEditable(false);
		
		JTextField display2 = new JTextField();
		display2.setFont(new Font("Calibri", Font.BOLD, 30));
		display2.setHorizontalAlignment(JTextField.RIGHT);
		display2.setEditable(false);
		display2.setText(" = RESULT");
		
		display.add(display1);
		display.add(display2);
	}
	
	private void setKeypad() {
		this.keypad = new JPanel();
		keypad.setLayout(new GridLayout(8, 4));
		
		keypad.add(new JButton("C"));
		keypad.add(new JButton("B"));
		keypad.add(new JButton("("));
		keypad.add(new JButton(")"));
		
		keypad.add(new JButton("exp"));
		keypad.add(new JButton("log"));
		keypad.add(new JButton("rac"));
		keypad.add(new JButton("^"));
		
		keypad.add(new JButton("cos"));
		keypad.add(new JButton("sin"));
		keypad.add(new JButton("tan"));
		keypad.add(new JButton("%"));
		
		keypad.add(new JButton("!F"));
		keypad.add(new JButton("abs"));
		keypad.add(new JButton("flr"));
		keypad.add(new JButton("+"));
		
		keypad.add(new JButton("7"));
		keypad.add(new JButton("8"));
		keypad.add(new JButton("9"));
		keypad.add(new JButton("-"));
		
		keypad.add(new JButton("4"));
		keypad.add(new JButton("5"));
		keypad.add(new JButton("6"));
		keypad.add(new JButton("x"));
		
		keypad.add(new JButton("1"));
		keypad.add(new JButton("2"));
		keypad.add(new JButton("3"));
		keypad.add(new JButton("/"));
		
		keypad.add(new JButton("_"));
		keypad.add(new JButton("0"));
		keypad.add(new JButton("."));
		keypad.add(new JButton("="));
		
		colorKeypad();
		setEvents();
	}
	
	private void colorKeypad() {
		for(int i=0; i<keypad.getComponentCount(); i++) {
			JButton current = (JButton)keypad.getComponent(i);
			if(current.getText() == "C") {
				current.setBackground(new Color(183, 34, 38));
				current.setForeground(Color.WHITE);
			}
			else if(current.getText() == "exp" || current.getText() == "log" || current.getText() == "rac"
					|| current.getText() == "cos" || current.getText() == "sin" || current.getText() == "tan"
					|| current.getText() == "!F" || current.getText() == "flr" || current.getText() == "abs") {
				current.setBackground(new Color(9, 62, 117));
				current.setForeground(Color.WHITE);
			}
			else if(current.getText() == "=") {
				current.setBackground(new Color(8, 162, 27));
				current.setForeground(Color.WHITE);
			}
			else current.setBackground(Color.WHITE);
		}
		((JTextField)display.getComponent(1)).setBackground(Color.BLACK);
		((JTextField)display.getComponent(1)).setForeground(Color.WHITE);
	}
	
	private void setExpression() {
		JTextField entry = (JTextField) display.getComponent(0);
		JTextField entry2 = (JTextField) display.getComponent(1);
		entry.setText(expression);
		if(expression.equals("+5800+")) {
			entry.setText("BY HMZ ^^");
			return;
		}
		if(expression != "") {
			Calculate c = new Calculate(expression);
			if(c.verify_exp()) {
				c.calculate();
				entry2.setText(" = "+c.getResult());
			} else {
				entry2.setText("Syntax Error");
			}
		}
		else entry2.setText(" = RESULT");
	}
	
	private void setEvents() {
		for(int i=0; i<keypad.getComponentCount(); i++) {
			JButton b = (JButton)keypad.getComponent(i);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(b.getText() == "C") expression = "";
					else if(b.getText() == "=") expression = ((JTextField) display.getComponent(1)).getText().substring(3);
					else if(b.getText() == "B") 
						if(expression.length() > 0) expression = expression.substring(0, expression.length()-1);
						else;
					else if(b.getText() == "!F") expression += "!";
					else if(b.getText() == "exp") expression += "exp(";
					else if(b.getText() == "log") expression += "log(";
					else if(b.getText() == "rac") expression += "rac(";
					else if(b.getText() == "cos") expression += "cos(";
					else if(b.getText() == "sin") expression += "sin(";
					else if(b.getText() == "tan") expression += "tan(";
					else if(b.getText() == "flr") expression += "flr(";
					else if(b.getText() == "abs") expression += "abs(";
					else if(b.getText() == "_") expression += " ";
					else  expression += b.getText();
					setExpression();
				}
			});
		}
	}
	
	
	public void open() {
		this.setTitle("Calculator");
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Calculator().open();
	}

}
