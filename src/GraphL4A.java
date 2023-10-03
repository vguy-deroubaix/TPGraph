
import java.util.ArrayList;
import java.util.Scanner;

public class GraphL4A extends Graph {

	private int n;
	private int type; //0 if undirected, 1 if directed
	private int weighted; // 0 if unweighted, 1 otherwise
	private WeightedNode4A[] adjlistW; //one of adjlistW and adjlist is null, depending on the type of the graph
	private Node4A[] adjlist;
	private int[] d;
	private int[] f;
	private ArrayList<Edge> TabEdges;

	public GraphL4A(Scanner sc){
	    String[] firstline = sc.nextLine().split(" ");
	    this.n = Integer.parseInt(firstline[0]);
	    System.out.println(this.n);
	    if (firstline[1].equals("undirected"))
	    	this.type = 0;
	    else
	    	this.type=1;
	    System.out.println("Type= "+this.type);
	    if (firstline[2].equals("unweighted"))
	    	this.weighted = 0;
	    else
	    	this.weighted=1;
	    System.out.println("Weighted= "+this.weighted);
	    if (this.weighted==0) {
	    	this.adjlist=new Node4A[this.n];
	    	for (int i=0;i<this.n;i++)
	    		adjlist[i]=null;
	        adjlistW=null;
	    }
	    else {
	    	this.adjlistW=new WeightedNode4A[this.n];
	    	for (int i=0;i<this.n;i++)
	    		adjlistW[i]=null;
	        adjlist=null;
	    }


	  	for(int k=0;k<this.n;k++){
	  		String[] line = sc.nextLine().split(" : ");
	  		int i = Integer.parseInt(line[0]); //the vertex "source"
	  		if (weighted==0) {
	  			String[] successors= line[1].split(", ");
	  			System.out.println(i+ " "+ successors.length);
	  			for (int h=0;h<successors.length;h++) {
	  				Node4A node=new Node4A(Integer.parseInt(successors[h])-1,null);
			        node.setNext(adjlist[i-1]);
			        adjlist[i-1]=node;
	  			}
	  		}
	  		else {
	  			line = line[1].split(" // ");
	  			if ((line.length==2)&&(line[1].charAt(0)!=' ')){// if there really are some successors, then we must have something different from " " after "// "
	  				  	String[] successors= line[0].split(", ");
	  			  	    String[] theirweights= line[1].split(", ");
	  				  	for (int h=0;h<successors.length;h++) {
	  				  	  	WeightedNode4A nodeW = new WeightedNode4A(Integer.parseInt(successors[h])-1,null,Float.parseFloat(theirweights[h]));
	  				  	  	nodeW.setNext(adjlistW[i-1]);
	  				  	  	adjlistW[i-1]=nodeW;
	  				  	}

	  			}
	  		}
	  	}
	  	//I Will use it in my DFSnum method
	  	this.d = new int[this.n];
	  	for (int i = 0; i < this.n; i++)
	  	{
	  		d[i] = 0;
	  	}
	  	this.f = new int[this.n];
	  	for (int i = 0; i < this.n; i++)
	  	{
	  		f[i] = 0;
	  	}

	  	this.TabEdges = new ArrayList<Edge>();
	  }

	//Surcharge de constructeur
	public GraphL4A(int n, int type, int weighted, Node4A[] adjlist)
	{
		this.n = n;
		this.type = type;
		this.weighted = weighted;
		this.adjlistW = null;
		this.adjlist = adjlist;
	}

	public GraphL4A(int n, int type, int weighted, WeightedNode4A[] adjlistw)
	{
		this.n = n;
		this.type = type;
		this.weighted = weighted;
		this.adjlistW = adjlistw;
		this.adjlist = null;
	}




	//method to be applied only when type=0 and weighted=0
	public int[] degree(){
		int[] tmp=new int[this.n];
	    for(int i=0;i<this.n;i++)
	    	tmp[i]=0;
	    	for(int i=0;i<this.n;i++) {
	    		Node4A p=adjlist[i];
	    		while (p!=null) {
	    			tmp[i]=tmp[i]+1;
	    			p=p.getNext();
	    		}
	    	}
	    	return(tmp);
	  }

	//method to be applied only when type=0 and weighted=1
		public int[] degreeW(){
			int[] tmp=new int[this.n];
		    for(int i=0;i<this.n;i++)
		    	tmp[i]=0;
		    	for(int i=0;i<this.n;i++) {
		    		WeightedNode4A p=adjlistW[i];
		    		while (p!=null) {
		    			tmp[i]=tmp[i]+1;
		    			p=p.getNext();
		    		}
		    	}
		    	return(tmp);
		  }


	//method to be applied only when type=1 and weighted=0
	  public TwoArrays4A degrees(){
		  int[] tmp1=new int[this.n]; //indegrees
		  int[] tmp2=new int[this.n]; //outdegrees
		  for(int i=0;i<this.n;i++) {
		    tmp1[i]=0;
		    tmp2[i]=0;
		  }
    	for(int i=0;i<this.n;i++) {
    		Node4A p=adjlist[i];
    		while (p!=null) {
    			tmp2[i]=tmp2[i]+1;
    			tmp1[p.getVal()]=tmp1[p.getVal()]+1;
    			p=p.getNext();
    		}
    	}
	    return(new TwoArrays4A(tmp1,tmp2));
	  }

	//method to be applied only when type=1 and weighted=1
	  public TwoArrays4A degreesW(){
		  int[] tmp1=new int[this.n]; //indegrees
		  int[] tmp2=new int[this.n]; //outdegrees
		  for(int i=0;i<this.n;i++) {
		    tmp1[i]=0;
		    tmp2[i]=0;
		  }
    	for(int i=0;i<this.n;i++) {
    		WeightedNode4A p=adjlistW[i];
    		while (p!=null) {
    			tmp2[i]=tmp2[i]+1;
    			tmp1[p.getVal()]=tmp1[p.getVal()]+1;
    			p=p.getNext();
    		}
    	}
	    return(new TwoArrays4A(tmp1,tmp2));
	  }



	  public int getType() {
			return this.type;
		}
	  public int getWeighted() {
			return this.weighted;
		}

	  public int getN()
	  {
		  return this.n;
	  }

	  public Node4A[] getAdjlist()
	  {
		  return this.adjlist;
	  }

	  public WeightedNode4A[] getAdjlistW()
	  {
		  return this.adjlistW;
	  }

	  public int[] getD()
	  {
		  return this.d;
	  }

	  public int[] getF()
	  {
		  return this.f;
	  }

	  public ArrayList<Edge> getTabEdge()
	  {
		  return this.TabEdges;
	  }
	  /*----------------------------------------------------------------------------------------------------------------------------*/

		//To add an unweighted node in a adjlist
		//Complexity O(1)
	  public void add(int i, int j)
      {
		  this.adjlist[i] = new Node4A(j, this.adjlist[i]);
      }

		//To add a weighted node in a adjlistW
		//O(1)
	  public void addW(int i, int j,Float weight)
	  {
		  this.adjlistW[i] = new WeightedNode4A(j, this.adjlistW[i],weight);
	  }


	  /*-------------------------------------------------------FONCTIONS POUR TP2----------------------------------------------------*/

	  //Function that transpose My adjList without weight into a List
		//Complexity Θ(n + m)
	  public GraphL4A transposeListIntoList()
	  {
		  	//Object that I will use as a pointer to browse all the target of my source
		  	Node4A p1 = new Node4A(0,null);

		  	//Initialise my Matrix
		    Node4A[] transposeList = new Node4A[this.n];

		    //Start the browse of my AdjacencyList
		    for (int i=0; i < n; i++)
		    {
		    	if (this.adjlist[i] != null)
		    	{
		    		p1 = this.adjlist[i];

		    		int j = 0;
		    		while (p1 != null)
		    		{
		    			//Stock the val of the target
		    			j = p1.getVal();

		    				//Add the object in my TransposedList
		    				Node4A p2 = new Node4A(j,transposeList[i]);
		    				transposeList[i] = p2;
		    			//next Target
		    			p1 = p1.getNext();
		    		}
		    	}
		    }
		    GraphL4A TransposeGraphL = new GraphL4A(this.n, this.type, this.weighted, transposeList );

		    return TransposeGraphL;

	  }



	  //Function that transpose my weighted adjList into a transposed weighted adjList
		//Complexity Θ(n + m)
	  public GraphL4A transposeListWIntoList()
	  {
		//Object that I will use as a pointer to browse all the target of my source
		  WeightedNode4A p1 = new WeightedNode4A(0,null, (float) 0);

		//Initialise my List
		  WeightedNode4A[] transposeListW = new WeightedNode4A[this.n];

		    //Start the browse of my AdjacencyList
		    for (int i=0; i < n ; i++)
		    {
		    	if (this.adjlistW[i] != null)
		    	{
		    		p1 = this.adjlistW[i];

		    		int j = 0;
		    		while (p1 != null)
		    		{
		    			    //Stock the val of the target
		    				j = p1.getVal();

		    				//Add the object in my weightedTransposedList
		    				WeightedNode4A p2 = new WeightedNode4A(i,transposeListW[j], p1.getWeight());
		    				transposeListW[j] = p2;

		    				//next Target
		    				p1 = p1.getNext();
		    		}
		    	}
		    }
		    GraphL4A TransposeGraphLW = new GraphL4A(this.n, this.type, this.weighted, transposeListW );
		    return TransposeGraphLW;

	  }

	  //Function that transpose my weighted list into a matrix of weight
		//Complexity Θ(n + m)
	  public GraphM4A transposeListWIntoMatrix()
	  {
		  //Initialise my Matrix
		  float[][] transposeMatrix = new float[this.n][this.n];
		  //Object that I will use as a pointer to browse all the target of my source
		  WeightedNode4A p1 = new WeightedNode4A(0,null, (float) 0);

		  //Start the browse of my AdjacencyList
		    for (int i=0; i < n ; i++)
		    {
		    	if (this.adjlistW[i] != null)
		    	{
		    		p1 = this.adjlistW[i];

		    		int j = 0;
		    		while (p1 != null)
		    		{
		    				j = p1.getVal();
		    				//Add the  weight of the arc in my transposedMatrix
		    				transposeMatrix[j][i] = p1.getWeight();

		    				//next Target
		    				p1 = p1.getNext();
		    		}
		    	}
		    }
		    GraphM4A TransposeGraphMW = new GraphM4A(this.n, this.type, this.weighted, transposeMatrix );
		    return TransposeGraphMW;


	  }

	  //Function that transpose my adjList without weight into a matrix of 1/0
		//Θ(n + m)
	  public GraphM4A transposeListIntoMatrix()
	  {
		  float[][] transposeMatrix = new float[this.n][this.n];
		  Node4A p1 = new Node4A(0,null);
		  	//Start the browse of my AdjacencyList
		    for (int i=0; i < n ; i++)
		    {
		    	if (this.adjlist[i] != null)
		    	{
		    		p1 = this.adjlist[i];

		    		int j = 0;
		    		while (p1 != null)
		    		{

		    				j = p1.getVal();
		    				//Add the  reverse arc in my transposedMatrix
		    				transposeMatrix[j][i] = 1;
		    				//next Target
		    				p1 = p1.getNext();
		    		}
		    	}
		    }
		    GraphM4A TransposeGraphMW = new GraphM4A(this.n, this.type, this.weighted, transposeMatrix );
		    return TransposeGraphMW;


	  }


	  //Display my adjList with a pretty print
		//Complexity Θ(n + m)
	  public void printGraph()
	  {
		 if(this.weighted == 1)
		 {

			 for(int i=0; i < this.n  ; i++)
			 {
				 System.out.print((i+1)+" = ");
				 if (this.adjlistW[i] != null)
				 {
					 WeightedNode4A p2 = new WeightedNode4A (this.adjlistW[i].getVal(), this.adjlistW[i].getNext(), (float)0);
					 while(p2 != null)
					 {
						 System.out.print(p2.getVal()+1+ "--->"+ " ");
						 p2 = p2.getNext();
					 }
					 System.out.println("Null");
		  	   	 }else
		  	   	 {
		  	   		 System.out.println("Empty List");
		  	   	 }
			 }
		 }else
		 {
			 for(int i=0; i < this.n  ; i++)
			  {
				  System.out.print((i+1)+" = ");
				  if (this.adjlist[i] != null)
				  {
					  Node4A p2 = new Node4A (this.adjlist[i].getVal(), this.adjlist[i].getNext());
					  while(p2 != null)
					  {
						  System.out.print(p2.getVal()+1+ "--->"+ " ");
						  p2 = p2.getNext();
					  }
			  		  System.out.println("Null");
			  	   }else
			  	   {
			  		   System.out.println("Empty List");
			  	   }
			  }
		 }
	  }

	  //Start the right method in fonction of the right input and output Graph
	  //For the output False: TransposedList, True: TransposedMatrix
		//Complexity Θ(n + m)
	  public Graph transposedGraphL(boolean outputGraph)
	  {
	      if(this.weighted == 1 && outputGraph == false)
	      {
	    	  GraphL4A TransposeGraph= this.transposeListWIntoList();
	    	  System.out.println();
	          System.out.println("--------------------------------------------------------TransposeWeightGraph--------------------------------------------------------------");
	    	  return TransposeGraph;

	      }
	      if(this.weighted == 1 && outputGraph == true)
	      {
	    	  System.out.println();
	    	  GraphM4A TransposeGraphM= this.transposeListWIntoMatrix();
	    	  System.out.println("--------------------------------------------------------TransposeWeightMatrix--------------------------------------------------------------");
	    	  return TransposeGraphM;
	      }
	      if(this.weighted == 0 &&  outputGraph == true)
	      {
	    	  System.out.println();
	    	  GraphM4A TransposeGraphM= this.transposeListIntoMatrix();
	    	  System.out.println("--------------------------------------------------------TransposeMatrix--------------------------------------------------------------");

	    	  return TransposeGraphM;

	      }

	      // if this.weighted == 0 && outputGraph == false)
	       GraphL4A TransposeGraph= this.transposeListIntoList();
	       System.out.println();
	       System.out.println("--------------------------------------------------------TransposeList--------------------------------------------------------------");
	       return TransposeGraph;



	  }
	  /*--------------------------------------------DFSNUMBERING METHOD--------------------------------------------------------------------------------*/

	  //Start the recursive method
		//Complexity Θ(m)
	  public int DFSNumbRecW(int s, int nb)
	  {
		  nb ++;

		  //Mark my source as visited
		  this.d[s] = nb;
		  WeightedNode4A ptr = new WeightedNode4A(0,this.adjlistW[s], (float)0);

		  //Browse all the target of s
		  while(ptr != null)
		  {
			  //Check if my vertex is not visited
			  if(this.d[ptr.getVal()] == 0)
			  {
				  //Start the recursive on the target/neighbourg of s
				  nb = DFSNumbRecW(ptr.getVal(), nb);
			  }
			  //Next target
			  ptr = ptr.getNext();
		  }
		  nb++;
		  //Mark the end of my source
		  this.f[s] = nb;

		  return nb;
	  }

	  //Same method as DFNumbRecW
		//Complexity Θ(m)
	  public int DFSNumbRec(int s, int nb)
	  {
		  nb ++;

		  this.d[s] = nb;
		  Node4A ptr = new Node4A(0,this.adjlist[s]);

		  while(ptr != null)
		  {
			  if(this.d[ptr.getVal()] == 0)
			  {
				  nb = DFSNumbRec(ptr.getVal(), nb);
			  }
			  ptr = ptr.getNext();
		  }
		  nb++;
		  this.f[s] = nb;

		  return nb;
	  }

	  //Main method that call the recursive function
		//Complexity Θ(m)
	  public void DFSNumb()
	    {
	    	int nb = 0;

	    	//Check if the graph is weighted or not
	    	if (this.weighted == 1)
	    	{
	    		for(int i= 0; i < this.n; i++)
	    		{
	    			//Check if the source is visited
	    			if(this.d[i] == 0)
	    			{
	    				 nb = DFSNumbRecW(i, nb);
	    			}
	    		}
	    	}else
	    	{
	    		for(int i= 0; i < this.n; i++)
	    		{
	    			if(this.d[i] == 0)
	    			{
	    				nb = DFSNumbRec(i, nb);
	    			}
	    		}
	    	}
	    }


	    /*-------------------------------------------- DETERMINE THE TYPE OF ALL MY EDGE WITH TAB D AND TAB F-------------------------------------*/


	    public void printTab(int[] tab)
	    {
	    	for(int i = 0; i< tab.length; i++)
	    	{
	    		System.out.print(tab[i]+" ");
	    	}
	    }

	    //Function that determine the type of the edge between s and t
			//Complexity Θ(1)
	    public String edgeType(int s, int t)
	    {
	    	if (this.d[t] < this.d[s] && this.d[s] < this.f[s] && this.f[s] < this.f[t] )
	    	{
	    		return "Back";
	    	}
	    	if (this.f[t] < this.d[s])
	    	{
	    		return "Cross";
	    	}
	    	return "Forward";

	    }

	    //Function that create a list of object edge with the source, the target and the type of the edge
			//Complexity Θ(n + m)
	    public void fillTabEdges()
	    {
	    	//Browse a weighted adjList
	    	if(this.weighted == 1)
	    	{
	    		//Creation of a temporary pointer to browse my neighbours of i
	    		WeightedNode4A p1 = new WeightedNode4A(0,null, (float)0);
	    		for (int i=0; i < n ; i++)
	    		{
	    			if (this.adjlistW[i] != null)
	    			{
	    				p1 = this.adjlistW[i];

	    				int j = 0;
	    				while (p1 != null)
	    				{
		    				j = p1.getVal();

		    				//Add my edge in my list of edges
		    				Edge tmp = new Edge(i,j,edgeType(i,j));
		    				TabEdges.add(tmp);

		    				p1 = p1.getNext();
	    				}
	    			}
	    		}


	    	}else
	    	{
	    		//Browse a unweightedList
	    		//Creation of a temporary pointer to browse my neighbours of i
	    		Node4A p1 = new Node4A(0,null);
	    		for (int i=0; i < n ; i++)
	    		{
	    			if (this.adjlist[i] != null)
	    			{
	    				p1 = this.adjlist[i];

	    				int j = 0;
	    				while (p1 != null)
	    				{
		    				j = p1.getVal();

		    				//Add my edge in my list of edges
		    				Edge tmp = new Edge(i,j,edgeType(i,j));
		    				TabEdges.add(tmp);

		    				p1 = p1.getNext();
	    				}
	    			}
	    		}
	    	}
	    }

	    //Function that print my table of edges
	    public void displayTabEdges()
	    {
	    	for(int i = 0; i < this.TabEdges.size(); i++)
	    	{
	    		System.out.println("arc:"+ " "+ "(" + (this.TabEdges.get(i).getX()+1)+ ", " + (this.TabEdges.get(i).getY()+1) + ")" +" "+ this.TabEdges.get(i).getType() );
	    	}
	    }



	    /*----------------------------------------------------------------------EXERCICE 3--------------------------------------------------------------------------*/

	    /*
				Function that return a boolean if
				Complexity : Θ(n)
			*/
	    public boolean isCyclic()
	    {
	    	for(int i = 0; i < this.TabEdges.size(); i++)
	    	{
	    		if(this.TabEdges.get(i).getType() == "Back")
	    		{
	    			System.out.println("Your graph contain at least one cycle");
	    			return true;
	    		}

	    	}
	    	System.out.println("Your graph doesn't contain cycle");
	    	return false;
	    }



}
