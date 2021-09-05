
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
		
		
		
		
		
		
		
		return new int[]{xMin,yMin,xMax,yMax};
	}//task 1
	
	public static void main(String[] args) {
		Task1 test = new Task1();
		System.out.print(test.getBound(1, 1));
		System.out.print(test.getBound(3, 3));
		//output should be 0,0,2,2 and 2,2,3,4
	}

}
