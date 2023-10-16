//Queue in FIFO
public class QueueFifo {

	public WeightedNode4A deb;
	public int size;
	
	//Constructor
	public QueueFifo() {
		this.deb = new WeightedNode4A(0,null, (float) 0);
		this.size = 0;
	}
	
	//Add a new node at the head of my LinkedList
	public void add(int source, float weight)
	{
		WeightedNode4A n = new WeightedNode4A(source,null, weight);
		this.deb.setNext(n);
		
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
	
	


}

