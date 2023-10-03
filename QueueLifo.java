//Queue in LIFO
public class QueueLifo {

	public WeightedNode4A deb;
	public int size;
	
	//Constructor
	public QueueLifo() {
		this.deb = new WeightedNode4A(0,null, (float) 0);
		this.size = 0;
	}
	
	//Add a new node at the queue of my LinkedList
	public void add(int source)
	{
		WeightedNode4A n = new WeightedNode4A(source,this.deb, (float) 0);
		WeightedNode4A tmp = this.deb;
		
		while (tmp != null)
		{
			tmp = tmp.getNext();
		}
		tmp = n;
		
	}
	//Print my LinkedList
	public String toString()
	{
	        if (this.deb == null) {return "Empty List";}
	        else {return this.deb.toString();}
	}
	
	// Remove the first element of the queue
	public WeightedNode4A remove()
	{
		WeightedNode4A elementToRemove = this.deb.getNext();
		
		if (elementToRemove.getNext() != null)
		{
			this.deb = elementToRemove.getNext();
			elementToRemove.setNext(null);
			
		}else
		{
			this.deb.setNext(null);
		}
		return elementToRemove;
	}
	
	public int size()
	{
		WeightedNode4A tmp = this.deb;
		int acc = 0;
		while (tmp.getNext() != null)
		{
			tmp = tmp.getNext();
			acc ++;
		}
		return acc;
			
	}


}

