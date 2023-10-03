
import java.util.Scanner;

public class GraphL4A extends Graph{

	private int n;
	private int type; //0 if undirected, 1 if directed
	private int weighted; // 0 if unweighted, 1 otherwise
	private WeightedNode4A[] adjlistW; //one of adjlistW and adjlist is null, depending on the type of the graph
	private Node4A[] adjlist;

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

	  }

		public GraphL4A(){}

	  public GraphL4A(int n,int type,int weighted,Node4A[] adjlist){
			this.n = n;
			this.type = type;
			this.weighted = weighted;
			this.adjlist = adjlist;
		}

		public GraphL4A(int n,int type,int weighted,WeightedNode4A[] adjlistW){
			this.n = n;
			this.type = type;
			this.weighted = weighted;
			this.adjlistW = adjlistW;
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

		public WeightedNode4A[] getAdjlistW(){
			return this.adjlistW;
		}

		public Node4A[] getAdjlist(){
			return this.adjlist;
		}

		public void add(int i, int j)
      {
          if (this.adjlist[i].getNext() != null)
          	{
              this.adjlist[i].setNext(new Node4A(j,this.adjlist[i].getNext()));
            } else {
							this.adjlist[i].setNext(new Node4A(j,null));
						}
      }

			public void addW(int i, int j,Float weight)
      {
          if (this.adjlistW[i].getNext() != null)
            {
              this.adjlistW[i].setNext(new WeightedNode4A(j,this.adjlistW[i].getNext(),weight));
            } else {
							this.adjlistW[i].setNext(new WeightedNode4A(j,null,weight));
						}
      }

			public void printGraphList(){
				if(this.weighted == 0){
					for(int i=0;i<this.adjlist.length;i++){
						if(this.adjlist[i].getNext() == null)
							System.out.print((i+1) + "---> ");
						while(this.adjlist[i].getNext() != null){
							System.out.print((this.adjlist[i].getVal()+1)+ "---> ");
						}
					} System.out.println();
				} else {
					WeightedNode4A tps;
					for(int i=0;i<this.adjlistW.length;i++){
						if(this.adjlistW[i].getNext() == null){
							System.out.print((i+1) + "---> empty list");
						}
						tps = this.adjlistW[i];
						while(tps.getNext() != null){
							System.out.print((tps.getVal()+1)+ "---> ");
							tps = tps.getNext();
							if(tps.getNext() == null)
								System.out.print(tps.getVal()+1 + "---> null");
						}
						System.out.println();
					}
				}

			}

			public void printGraphMat(){

			}

}
