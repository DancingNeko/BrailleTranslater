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
				g.drawRect(dot.xMin, dot.yMin, dot.xMax-dot.xMin, dot.yMax-dot.yMin);
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
	
	
	public int[] getBound(int i, int j)
	{
		int xMax = -100;
		int xMin = Integer.MAX_VALUE;
		int yMax = -100;
		int yMin = Integer.MAX_VALUE;
		//write you code here:

         int X= Integer.MAX_VALUE;
         int Y= Integer.MAX_VALUE;

         int xmax2=0;
         int xmax4=0;
         int xmax6=0;
         int xmax7=0;
         int xmin1=0;
         int xmin3=0;
         int xmin5=0;
         int xmin8=0;
         int ymin1=0;
         int ymin2=0;
         int ymin5=0;
         int ymin6=0;
         int ymax3=0;
         int ymax4=0;
         int ymax7=0;
         int ymax8=0;
         for(i=0;i<=X;i++  )
         {
             for(j=0;j<=Y;  )
             {
                 if(mask[i][j]==0)
                 {
                 j++;
                 continue;
             }
         else
         {
             int ci1=i;
             int cj1=j;
              while ( mask[ci1][cj1] != 0)
              {
                  --ci1;
                  if( mask[ci1][cj1] != 0  )
                  continue;
                  else
                  {
                     cj1--;
                     while( cj1!=-1 || mask[ci1][cj1]!=0 || ci1!=-1 )
                     cj1--;
                     if( cj1==-1 )
                     cj1++;
                     if( ci1==-1 )
                     ci1++;
                      xmin1=ci1;
                      ymin1=cj1;
                     }
                 }
                     int ci2=i;
                     int cj2=j;

              while ( mask[ci2][cj2] != 0)
              {
                  ++ci2;
                  if( mask[ci2][cj2] != 0)
                  continue;
                  else
                  {
                     cj2--;
                     while( cj2!=-1 || mask[ci2][cj2]!=0)
                     j--;
                     if( cj2==-1 )
                     cj2++;
                      xmax2=ci2;
                      ymin2=cj2;
                     }
                 }
                 int ci3=i;
                 int cj3=j;
                 while ( mask[ci3][cj3] != 0)
              {
                  ++cj3;
                  if( mask[ci3][cj3] != 0)
                  continue;
                  else
                  {
                     ci3--;
                     while( ci3!=-1 || mask[ci3][cj3]!=0)
                     ci3--;
                     if( ci3==-1 )
                     ci3++;
                      xmin3=ci3;
                      ymax3=cj3;
                     }
                 }
                 int ci4=i;
                 int cj4=j;
                 while ( mask[ci4][cj4] != 0)
              {
                  ++cj4;
                  if( mask[ci4][cj4] != 0)
                  continue;
                  else
                  {
                     ci4++;
                     while(  mask[ci4][cj4]!=0)
                     ci4++;
                     if( ci4==-1 )
                     ci4++;
                      xmax4=ci4;
                      ymax4=cj4;
                     }
                 }
                 int ci5=i;
                 int cj5=j;
                 while ( mask[ci5][cj5] != 0)
              {
                  --cj5;
                  if( mask[ci5][cj5] != 0)
                  continue;
                  else
                  {
                     ci5--;
                     while( ci5!=-1 || mask[ci5][cj5]!=0 ||cj5!=-1)
                     ci5--;
                     if( ci5==-1 )
                     ci5++;
                     if( cj5==-1 )
                     cj5++;
                      xmin5=ci5;
                      ymin5=cj5;
                     }
                 }
                 int ci6=i;
                 int cj6=j;
                 while ( mask[ci6][cj6] != 0)
              {
                  --cj6;
                  if( mask[ci6][cj6] != 0)
                  continue;
                  else
                  {
                     ci6++;
                     while( mask[ci6][cj6]!=0 || cj5!=-1)
                     if( cj6==-1 )
                     cj6++;
                      xmax6=ci6;
                      ymin6=cj6;
                     }
                 }
                 int ci7=i;
                 int cj7=j;
                 while ( mask[ci7][cj7] != 0)
              {
                  ++ci7;
                  if( mask[ci7][cj7] != 0)
                  continue;
                  else
                  {
                     cj7++;
                     while( mask[ci7][cj7]!=0)
                     if( ci7==-1 )
                     ci7++;
                      xmax7=ci7;
                      ymax7=cj7;
                     }
                 }
                 int ci8=i;
             int cj8=j;
              while ( mask[ci8][cj8] != 0)
              {
                  --ci8;
                  if( mask[ci8][cj8] != 0)
                  continue;
                  else
                  {
                     cj8++;
                     while( mask[ci8][cj8]!=0 || ci8!=-1)
                     cj8--;
                     if( ci8==-1 )
                     ci8++;
                      xmin8=ci8;
                      ymax8=cj8;
                     }
                 }

         }
             }
         }
         xMax= Math.max(Math.max(xmax2, xmax4), Math.max(xmax6, xmax7));
         xMin= Math.min(Math.max(xmin1, xmin3), Math.min(xmin5, xmin8));
         yMin= Math.min(Math.min(ymin6, ymin5), Math.min(ymin2, ymin1));
         yMax= Math.max(Math.max(ymax8, ymax7), Math.max(ymax3, ymax4));
		
		
		
		
		return new int[]{xMin,yMin,xMax,yMax};
	}//task 1 by aditya
	
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
		
		return message;
	}
	
	
	public String translate(boolean[][] input) {
		return ""; //task 3
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
