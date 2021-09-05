
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Runner{
	BufferedImage img;
	JFrame window;
	FormulaPanel test;
	MainPanel panel;
	String path = null;
	int x = 200;
	int y = 0;
	
	Runner()
	{
		window = new JFrame("Point Detector");
		window.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-115, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-115,230,230);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		JTextField getK = new JTextField();
		JTextField getB = new JTextField();
		JLabel mousePosition = new JLabel("(0,0)");
		mousePosition.setBounds(0, 175, 200, 10);
		window.add(mousePosition);
		mousePosition.setBackground(new Color(240,240,240));
		test = new FormulaPanel(getK, getB);
		window.add(test);
		JSlider threshold = new JSlider(JSlider.HORIZONTAL,0,200,75);
		threshold.setMajorTickSpacing(50);
		threshold.setBounds(0, 75, 200, 50);
		threshold.setPaintTicks(true);
		threshold.setOpaque(true);
		threshold.setBackground(new Color(240,240,240));
		threshold.setPaintLabels(true);
		window.add(threshold);
		test.setVisible(true);
		Info info = new Info();
		window.add(info);
		info.setBounds(0,125,200,50);
		JFileChooser jfc = new JFileChooser();
		jfc.setVisible(true);
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
			    "Image files", ImageIO.getReaderFileSuffixes());
		jfc.setFileFilter(imageFilter);
		window.add(jfc);
	    jfc.showDialog(null,"Please Select the Image");
		File sourceImage = jfc.getSelectedFile();
		path = sourceImage.getParent();
		window.remove(jfc);
		try {
			img = ImageIO.read(sourceImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(img.getHeight() >= 300)
		window.setSize(img.getWidth() + 200,img.getHeight());
		else
			window.setSize(img.getWidth() + 200,300);
		panel = new MainPanel(img);
		panel.setVisible(true);
		panel.setBounds(200, 0, img.getWidth(), img.getHeight());
		window.add(panel);
		window.repaint();
		JButton calculate = new JButton("Calculate");
		calculate.setBounds(0, 225, 200, 30);
		window.add(calculate);
		if(img.getWidth() <= Toolkit.getDefaultToolkit().getScreenSize().getWidth() && img.getHeight() <= Toolkit.getDefaultToolkit().getScreenSize().getHeight())
		window.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-window.getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-window.getHeight()/2,window.getWidth(),window.getHeight());
		else
			window.setBounds(0, 0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 38)
				{
					y+=img.getHeight()/20;
					panel.setBounds(x, y, img.getWidth(), img.getHeight());
				}
				if(e.getKeyCode() == 39)
				{
					x-=img.getHeight()/20;
					panel.setBounds(x, y, img.getWidth(), img.getHeight());
				}
				if(e.getKeyCode() == 40)
				{
					y-=img.getHeight()/20;
					panel.setBounds(x, y, img.getWidth(), img.getHeight());
				}
				if(e.getKeyCode() == 37)
				{
					x+=img.getHeight()/20;
					panel.setBounds(x, y, img.getWidth(), img.getHeight());
				}
				window.repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) {}	
		});
		getK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.toFront();
				window.requestFocus();
			}	
		});
		
		getB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.toFront();
				window.requestFocus();
			}	
		});
		threshold.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				panel.update(threshold.getValue());
				window.toFront();
				window.requestFocus();
			}
		});
		window.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {}
			public void mouseMoved(MouseEvent e) {
				mousePosition.setText("(" + (e.getX() - x - 8) + ","+(e.getY() - y - 31)+")");
			}
		});

		JButton choose = new JButton("choose image");
		window.add(choose);
		choose.setBounds(0, 195, 200, 30);
		choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(path);
				jfc.setVisible(true);
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
					    "Image files", ImageIO.getReaderFileSuffixes());
				jfc.setFileFilter(imageFilter);
				window.add(jfc);
			    jfc.showDialog(null,"Please Select the Image");
				File sourceImage = jfc.getSelectedFile();
				path = sourceImage.getParent();
				window.remove(jfc);
				try {
					img = ImageIO.read(sourceImage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(img.getHeight() >= 300)
				window.setSize(img.getWidth() + 220,img.getHeight());
				else
					window.setSize(img.getWidth() + 220,300);
				window.remove(panel);
				panel = new MainPanel(img);
				panel.setVisible(true);
				panel.setBounds(200, 0, img.getWidth(), img.getHeight());
				window.add(panel);
				if(img.getWidth() <= Toolkit.getDefaultToolkit().getScreenSize().getWidth() && img.getHeight() <= Toolkit.getDefaultToolkit().getScreenSize().getHeight())
					window.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-window.getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-window.getHeight()/2,window.getWidth(),window.getHeight());
					else
						window.setBounds(0, 0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
				window.repaint();
			}
		});
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.calculateMessage();
			}
		});
	}
	public int getConcentration(int greyScale)
	{
		if(test.isValid())
		{
			int k = Integer.parseInt(test.getK());
			int b = Integer.parseInt(test.getB());
			return k * greyScale + b;
		}
		else
			return 0;
	}
	public static void main(String args[])
	{
		Runner test = new Runner();
	}
	
}
