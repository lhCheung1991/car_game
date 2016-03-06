
public class WayPoints {

	private static final int[][] points = 
		{
		{1350, 12186},
		{990,  11682},
		{990,  11466},
		{1350, 11070},
		{1638, 10890},
		{1962, 10458},
		{1638, 10170},
		{774,  10206},
		{846,  9882},
		{1026, 9738},
		{1710, 9738},
		{1584, 9378},
		{1584, 8946},
		{1584, 8334},
		{918,  8226},
		{702,  7974},
		{882,  7686},
		{882,  6606},
		{1386, 6498},
		{1602, 7038},
		{2034, 7002},
		{1926, 6174},
		{1278, 5958},
		{918,  6030},
		{738,  5706},
		{900,  5562},
		{900,  4986},
		{1674, 4770},
		{2034, 4770},
		{1962, 4518},
		{1746, 4122},
		{1350, 3978},
		{954,  4410},
		{558,  4122},
		{702,  3150},
		{1170, 2934},
		{1530, 3150},
		{1134, 3726},
		{1386, 3870},
		{1998, 3762},
		{1998, 3150},
		{1890, 2790},
		{1566, 2214},
		{738,  2142},
		{918,  1818},
		{1350, 1746},
		{1350, 1530},
		{1296, 0}
		};
	
	private int idx;
	private boolean isFinished;

	public WayPoints() {
		super();
		this.idx = 0;
		this.isFinished = false;
	}
	
	public void chargeToNext(){
		idx++;
		if (idx + 1 >= points.length)
			this.isFinished = true;
	}
	
	public boolean isFinished() {
		return isFinished;
	}

	public int getCurrentX(){
		return points[idx][0];
	}
	
	public int getCurrentY(){
		return points[idx][1];
	}
}
