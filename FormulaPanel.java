import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormulaPanel extends JPanel{
	
	String k = "";
	String b = "";
	JLabel formula;
	
	
	FormulaPanel(JTextField getX, JTextField getY)
	{
		JPanel kInput = new JPanel(new BorderLayout());
		JPanel bInput = new JPanel(new BorderLayout());
		this.setVisible(true);
		this.setSize(200,60);
		this.setLayout(new BorderLayout());
		formula = new JLabel("y = kx + b", JLabel.CENTER);
		JLabel x = new JLabel("k = ", JLabel.CENTER);		
		JLabel y = new JLabel("b = ", JLabel.CENTER);
		this.add(formula, BorderLayout.NORTH);
		kInput.add(x,BorderLayout.WEST);
		kInput.add(getX,BorderLayout.CENTER);
		bInput.add(y,BorderLayout.WEST);
		bInput.add(getY,BorderLayout.CENTER);
		this.add(kInput,BorderLayout.CENTER);
		this.add(bInput,BorderLayout.SOUTH);
		getX.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				k = getX.getText();
				b = getY.getText();
				if(b.equals(""))
					b = "0";
				if(k.equals(""))
					k = "0";
				if(isInteger(k) && isInteger(b))
					formula.setForeground(Color.BLACK);
				else
				{
					formula.setForeground(Color.RED);
				}
				formula.setText("y = " + k + "x" + " + " + b);
			}
			public void removeUpdate(DocumentEvent e) {
				k = getX.getText();
				b = getY.getText();
				if(b.equals(""))
					b = "0";
				if(k.equals(""))
					k = "0";
				if(isInteger(k) && isInteger(b))
					formula.setForeground(Color.BLACK);
				else
				{
					formula.setForeground(Color.RED);
				}
				formula.setText("y = " + k + "x" + " + " + b);
			}
			public void changedUpdate(DocumentEvent e) {
				k = getX.getText();
				b = getY.getText();
				if(b.equals(""))
					b = "0";
				if(k.equals(""))
					k = "0";
				if(isInteger(k) && isInteger(b))
					formula.setForeground(Color.BLACK);
				else
				{
					formula.setForeground(Color.RED);
				}
				formula.setText("y = " + k + "x" + " + " + b);
			}
		});
		getY.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				k = getX.getText();
				b = getY.getText();
				if(b.equals(""))
					b = "0";
				if(k.equals(""))
					k = "0";
				if(isInteger(k) && isInteger(b))
					formula.setForeground(Color.BLACK);
				else
				{
					formula.setForeground(Color.RED);
				}
				formula.setText("y = " + k + "x" + " + " + b);
			}
			public void removeUpdate(DocumentEvent e) {
				k = getX.getText();
				b = getY.getText();
				if(b.equals(""))
					b = "0";
				if(k.equals(""))
					k = "0";
				if(isInteger(k) && isInteger(b))
					formula.setForeground(Color.BLACK);
				else
				{
					formula.setForeground(Color.RED);
				}
				formula.setText("y = " + k + "x" + " + " + b);
			}
			public void changedUpdate(DocumentEvent e) {
				k = getX.getText();
				b = getY.getText();
				if(b.equals(""))
					b = "0";
				if(k.equals(""))
					k = "0";
				if(isInteger(k) && isInteger(b))
					formula.setForeground(Color.BLACK);
				else
				{
					formula.setForeground(Color.RED);
				}
				formula.setText("y = " + k + "x" + " + " + b);
			}
		});
	}
	
	
	
	
	public boolean isValid()
	{
		if(k == null || b == null)
			return false;
		if(isInteger(k) && isInteger(b))
			return true;
		else
			return false;
	}
	public String getK()
	{
		return k;
	}
	public String getB()
	{
		return b;
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
}
