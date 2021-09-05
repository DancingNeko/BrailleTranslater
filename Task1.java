
public class Task1 {
	public int[][] mask;
	
	public Task1() {
		mask = new int[5][];
		mask[0] = new int[]{0,1,0,0,0};
		mask[1] = new int[]{1,1,1,0,0};
		mask[2] = new int[]{0,1,0,1,0};
		mask[2] = new int[]{0,0,1,1,1};
		mask[2] = new int[]{0,0,0,0,0};
	}
	
	public int[] getBound(int x, int y)
	{
		int xMax = -100;
		int xMin = Integer.MAX_VALUE;
		int yMax = -100;
		int yMin = Integer.MAX_VALUE;
		//write you code here:
		
		int i=0;
        int j=0;
        int n[][]= new int[i][j];
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
                if(n[i][j]==0)
                {
                j++;
                continue;
            }
        else
        {
            int ci1=i;
            int cj1=j;
             while ( n[ci1][cj1] != 0)
             {
                 --ci1;
                 if( n[ci1][cj1] != 0  )
                 continue;
                 else
                 {
                    cj1--;
                    while( cj1!=-1 || n[ci1][cj1]!=0 || ci1!=-1 )
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
                
             while ( n[ci2][cj2] != 0)
             {
                 ++ci2;
                 if( n[ci2][cj2] != 0)
                 continue;
                 else
                 {
                    cj2--;
                    while( cj2!=-1 || n[ci2][cj2]!=0)
                    j--;
                    if( cj2==-1 )
                    cj2++;
                     xmax2=ci2;
                     ymin2=cj2;
                    }
                }
                int ci3=i;
                int cj3=j;
                while ( n[ci3][cj3] != 0)
             {
                 ++cj3;
                 if( n[ci3][cj3] != 0)
                 continue;
                 else
                 {
                    ci3--;
                    while( ci3!=-1 || n[ci3][cj3]!=0)
                    ci3--;
                    if( ci3==-1 )
                    ci3++;
                     xmin3=ci3;
                     ymax3=cj3;
                    }
                }
                int ci4=i;
                int cj4=j;
                while ( n[ci4][cj4] != 0)
             {
                 ++cj4;
                 if( n[ci4][cj4] != 0)
                 continue;
                 else
                 {
                    ci4++;
                    while(  n[ci4][cj4]!=0)
                    ci4++;
                    if( ci4==-1 )
                    ci4++;
                     xmax4=ci4;
                     ymax4=cj4;
                    }
                }
                int ci5=i;
                int cj5=j;
                while ( n[ci5][cj5] != 0)
             {
                 --cj5;
                 if( n[ci5][cj5] != 0)
                 continue;
                 else
                 {
                    ci5--;
                    while( ci5!=-1 || n[ci5][cj5]!=0 ||cj5!=-1)
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
                while ( n[ci6][cj6] != 0)
             {
                 --cj6;
                 if( n[ci6][cj6] != 0)
                 continue;
                 else
                 {
                    ci6++;
                    while( n[ci6][cj6]!=0 || cj5!=-1)
                    if( cj6==-1 )
                    cj6++;
                     xmax6=ci6;
                     ymin6=cj6;
                    }
                }
                int ci7=i;
                int cj7=j;
                while ( n[ci7][cj7] != 0)
             {
                 ++ci7;
                 if( n[ci7][cj7] != 0)
                 continue;
                 else
                 {
                    cj7++;
                    while( n[ci7][cj7]!=0)
                    if( ci7==-1 )
                    ci7++;
                     xmax7=ci7;
                     ymax7=cj7;
                    }
                }
                int ci8=i;
            int cj8=j;
             while ( n[ci8][cj8] != 0)
             {
                 --ci8;
                 if( n[ci8][cj8] != 0)
                 continue;
                 else
                 {
                    cj8++;
                    while( n[ci8][cj8]!=0 || ci8!=-1)
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
	}//task 1
	
	public static void main(String[] args) {
		Task1 test = new Task1();
		System.out.print(test.getBound(1, 1));
		System.out.print(test.getBound(3, 3));
		//output should be 0,0,2,2 and 2,2,3,4
	}

}
