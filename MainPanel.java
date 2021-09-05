import java.awt.image.BufferedImage;

import javax.swing.JLayeredPane;

public class MainPanel extends JLayeredPane{
	BufferedImage img;
	ImageAnalyse analyse;
	MainPanel(BufferedImage src)
	{
		this.setSize(src.getWidth(),src.getHeight());
		img = src;
		ImageDisplay display = new ImageDisplay(img);
		add(display,1,0);
		analyse = new ImageAnalyse(img);
		add(analyse,2,0);
		analyse.setBounds(0, 0, img.getWidth(), img.getHeight());
	}
	public void update(int threshold)
	{
		analyse.update(threshold);
	}
	
	public String calculateMessage() {
		return analyse.analyseAndTranslate();
	}
}
