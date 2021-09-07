import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
			//task 2 by hamza
			for(Details dot:dots) {
				g.fillOval(dot.xMin,dot.yMin , 5, 5);
			}
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
		int xMax = x;
		int xMin = x;
		int yMax = y;
		int yMin = y;
		//write you code here:
		/*boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;
		System.out.print(Arrays.toString(mask[0]));
		while(up || down || left || right) {
			up = false;
			down = false;
			left = false;
			right = false;
			for(int i = xMin; i <= xMax; i++) {
				if(mask[i][yMin] == 1)
					up = true;
			}
			for(int i = xMin; i <= xMax; i++) {
				if(mask[i][yMax] == 1)
					down = true;
			}
			for(int i = yMin; i <= yMax; i++) {
				if(mask[xMin][i] == 1)
					left = true;
			}		
			for(int i = yMin; i <= yMax; i++) {
				if(mask[xMax][i] == 1)
					right = true;
			}
			if(up && xMin > 0)
				xMin --;
			if(down && xMax < mask.length-1)
				xMax ++;
			if(left && yMin > 0)
				yMin --;
			if(right && yMax < mask[0].length-1)
				yMax ++;
				
		}
		
		return new int[]{xMin,yMin,xMax,yMax};*/
		return new int[] {x,y,x,y};
	}
	
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
					for(int k = dot.xMin - 20; k <= dot.xMax + 30; k++)
						for(int l = dot.yMin - 20; l <= dot.yMax + 20; l++)
						{
							if(k >= mask.length || l >= mask[0].length || k < 0 || l < 0)
								continue;
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
		String message = "";
		ArrayList<Details>dots = this.findDots();
		ArrayList<Integer> xChart = new ArrayList<Integer>();
		ArrayList<Integer> yChart = new ArrayList<Integer>();
		for(Details dot:dots) {
			int x = (dot.xMax + dot.xMin)/2;
			int y = (dot.yMax + dot.yMin)/2;
			if(xChart.indexOf(x) == -1) {
				xChart.add(x);
			}
			if(yChart.indexOf(y) == -1) {
				yChart.add(y);
			}
		}
		Collections.sort(xChart);
		Collections.sort(yChart);
		for(int i = 0; i < xChart.size(); i++) {
			int close = closest(xChart.get(i),xChart);
			if(Math.abs(close - xChart.get(i)) < 10)
			{
				xChart.remove(i);
				i--;
			}
		}
		for(int i = 0; i < yChart.size(); i++) {
			int close = closest(yChart.get(i),yChart);
			if(Math.abs(close - yChart.get(i)) < 10) {
				yChart.remove(i);
				i--;
				}
		}
		int[][]input = new int[xChart.size()][yChart.size()];
		for(int[]arry:input) {
			Arrays.fill(arry, 0);
		}
		for(Details dot:dots) {
			int x = (dot.xMax + dot.xMin)/2;
			int y = (dot.yMax + dot.yMin)/2;
			int closeX = closest(x,xChart);
			int closeY = closest(y,yChart);
			int xIndex = xChart.indexOf(closeX);
			int yIndex = yChart.indexOf(closeY);
			if(xIndex == -1 || yIndex == -1)
				return "";
			input[xIndex][yIndex] = 1;
		}
			for(int j = 0 ; j < input[0].length; j++)
			{
				for(int i = 0; i < input.length; i++) {
					System.out.print(input[i][j]);
				}
				System.out.print("\n");
			}
		this.translate(input);
		return message;
	}
	
	public int closest(int of, ArrayList<Integer> in) {
	    int min = Integer.MAX_VALUE;
	    int closest = of;

	    for (int v : in) {
	        final int diff = Math.abs(v - of);

	        if (diff < min && diff != 0) {
	            min = diff;
	            closest = v;
	        }
	    }

	    return closest;
	}
	
	
	public String translate(int[][] input) {
		String message = "";
		int[][] letters = {{1,0,0,0,0,0},{1,0,1,0,0,0},{1,1,0,0,0,0},{1,1,0,1,0,0},{1,0,0,1,0,0},{1,1,1,0,0,0},{1,1,1,1,0,0},{1,0,1,1,1,0,0},{0,1,1,0,0,0},{0,1,1,1,0,0},{1,0,0,0,1,0},{1,0,1,0,1,0},{1,1,0,0,1,0},{1,1,0,1,1,0},{1,0,0,1,1,0},{1,1,1,0,1,0},{1,1,1,1,1,0},{1,0,1,1,1,0},{0,1,1,0,1,0},{0,1,1,1,1,0},{1,0,0,0,1,1},{1,0,1,0,1,1},{0,1,1,1,0,1},{1,1,0,0,1,1},{1,1,0,1,1,1},{1,0,0,1,1,1}};
		for(int i = 0; i < input.length-2; i += 3)
			for(int j = 0; j < input[0].length-1; j += 2) {
				int[] letter = {input[i][j],input[i][j+1],input[i+1][j],input[i+1][j+1],input[i+2][j],input[i+2][j+1]};
				for(int k = 0; k < letters.length; k++) {
					if(Arrays.equals(letters[k], letter)) {
						message += Character.toString((char)(97+k));
						break;
					}
					if(Arrays.equals(new int[]{0,0,1,1,0,1}, letter)) {
						message += ".";
						break;
					}
				}
			}
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
