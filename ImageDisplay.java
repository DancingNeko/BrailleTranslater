import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImageDisplay extends JPanel{
	BufferedImage img;
	ImageDisplay(BufferedImage src)
	{
		img = src;
		this.setSize(img.getWidth(), img.getHeight());
		this.setFocusable(false);
		this.setVisible(true);
	}
	@Override
	public void paint(Graphics window)
	{
		window.drawImage(img,0,0,this);
	}
}
