
public class Edge {

	private int x;
	private int y;
	private String type;
	
	public Edge(int x, int y, String type) {
		this.x  = x;
		this.y = y;
		this.type = type;
	}

	public int getX()
	{
		return this.x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int gety()
	{
		return this.y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
}
