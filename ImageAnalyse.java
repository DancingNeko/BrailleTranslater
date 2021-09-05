import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;

public class ImageAnalyse extends JComponent{
	BufferedImage img;
	boolean displayAnalyse = true;
	boolean displayData = false;
	int threshold = 75;
	int greyScale[][];
	int mask[][];
	int blank;
	int countPixels;
	int xMin = 0;
	int xMax = 0;
	int yMin = 0;
	int yMax = 0;
	ImageAnalyse(BufferedImage src)
	{
		 img = src;
		 greyScale = new int[img.getWidth()][img.getHeight()];
		 mask = new int[img.getWidth()][img.getHeight()];
		 for(int i = 0 ; i < img.getWidth(); i++)
			 for(int j = 0; j < img.getHeight(); j++)
			 {
				 int p = img.getRGB(i, j);
				 int a = (p>>24) & 0xff;
				 int r = (p>>16) & 0xff;
				 int g = (p>>8) & 0xff;
				 int b = p & 0xff;
				 greyScale[i][j] = (int)(0.299*(double)r+0.587*(double)g+0.114*(double)b);
			 }
		 blank = getEnvironmentGreyscale();
		 for(int i = 0 ; i < img.getWidth(); i++)
			 for(int j = 0; j < img.getHeight(); j++)
			 {
				 mask[i][j] = 0;
			 }
		 for(int i = 0 ; i < img.getWidth(); i++)
			 for(int j = 0; j < img.getHeight(); j++)
			 {
				 if(Math.abs(greyScale[i][j] - blank) > threshold)
					 mask[i][j] = 1;
			 }
	}
	public void update(int thres)
	{
		threshold = thres;
		for(int i = 0 ; i < img.getWidth(); i++)
			 for(int j = 0; j < img.getHeight(); j++)
			 {
				 if(Math.abs(greyScale[i][j] - blank) > threshold)
					 mask[i][j] = 1;
				 else mask[i][j] = 0;
			 }
		repaint();
	}
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		if(displayAnalyse)
		{
			ArrayList<Details>dots = findDots();
			draw rectangle here
		}
	}
	
	public boolean checkEdgePixels(int x, int y)
	{
		if(x == 0 || y == 0 || x == mask.length - 1 || y == mask[0].length - 1)
			return false;
		int data = mask[x][y];
		if(mask[x-1][y] != data || mask[x+1][y] != data || mask[x][y-1] != data || mask[x][y+1] != data)
			return true;
		return false;
	}
	
	
	public int[] getBound(int x, int y)
	{
		int xMax = -100;
		int xMin = Integer.MAX_VALUE;
		int yMax = -100;
		int yMin = Integer.MAX_VALUE;
		return new int[]{xMin,yMin,xMax,yMax};
	}task 1
	
	public boolean inCircle(int x1, int y1, int x2, int y2)
	{
		for(int i = x1; i <= x2; i++)
			for(int j = y1; j <= y2; j++)
				if(mask[i][j] == 1)
					return true;
		return false;
	}
	
	public ArrayList<Details> findDots()
	{
		ArrayList<Details> info = new ArrayList<Details>();
		int[][] maskCopy = new int[mask.length][mask[0].length];
		for(int i = 0; i < mask.length; i++)
			for(int j = 0 ; j < mask[0].length; j++)
			{
				maskCopy[i][j] = mask[i][j];
			}
		for(int i = 0; i < mask.length; i++)
			for(int j = 0 ; j < mask[0].length; j++)
			{
				if(maskCopy[i][j] == 1)
				{
					Details dot = new Details(getBound(i, j));
					for(int k = dot.xMin; k <= dot.xMax; k++)
						for(int l = dot.yMin; l <= dot.yMax; l++)
						{
							maskCopy[k][l] = 0;
						}
					info.add(dot);
				}
			}
		
		return info;
	}
	
	public int getEnvironmentGreyscale()
	{
		int scaleCount[] = new int [256];
		for(int i = 0; i <= 255; i++)
			scaleCount[i] = 0;
		for(int[] row:greyScale)
		{
			for(int item : row)
			{
				scaleCount[item]++;
			}
		}
		int maxIndex = 0;
		int max = 0;
		for(int i = 0; i <= 255; i++)
		{
			if(scaleCount[i] > max)
			{
				max = scaleCount[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public String analyseAndTranslate() {
		String message;
		ArrayList<Details>dots = this.findDots();
		
		return message;
	}
}

class Details
{
	public int xMin;
	public int yMin;
	public int xMax;
	public int yMax;
	public Details(int[]info)
	{
		xMin = info[0];
		xMax = info[2];
		yMin = info[1];
		yMax = info[3];
	}
}
