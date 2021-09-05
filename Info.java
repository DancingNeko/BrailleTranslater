import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info extends JPanel{
	JLabel formula;
	JLabel greyScale;
	JLabel concentration;
	public Info()
	{
		this.setSize(200,75);
		this.setLayout(new BorderLayout());
		formula = new JLabel("Formula: ");
		this.add(formula,BorderLayout.NORTH);
		greyScale = new JLabel("Greyscale = ");
		concentration = new JLabel("Concentration = ");
		this.add(greyScale, BorderLayout.CENTER);
		this.add(concentration, BorderLayout.SOUTH);
	}
	public void setScale(int scale)
	{
		greyScale.setText("Greyscale = " + scale);
	}
	public void setConcentration(int value)
	{
		concentration.setText("Concentration = " + value);
	}
	public boolean setFormula(String k, String b)
	{
		if(b.equals(""))
			b = "0";
		if(k.equals(""))
			k = "0";
		if(isInteger(k) && isInteger(b))
			formula.setForeground(Color.BLACK);
		else
		{
			formula.setForeground(Color.RED);
			formula.setText("y = " + k + "x" + " + " + b);
			return false;
		}
		formula.setText("y = " + k + "x" + " + " + b);
		return true;
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
