
import java.util.Scanner;

public class GraphM4A extends Graph{

  private int n;
  private int type; //0 if undirected, 1 if directed
  private int weighted; // 0 if unweighted, 1 otherwise
  private float[][] adjmat;


  public GraphM4A(Scanner sc){
    String[] firstline = sc.nextLine().split(" ");
    this.n = Integer.parseInt(firstline[0]);
    System.out.println("Number of vertices "+this.n);
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

    this.adjmat = new float[this.n][this.n];
    for (int i=0;i<this.n; i++)
    	for(int j=0; j<this.n; j++)
    		adjmat[i][j]=0; // replace 0 with something else if the weights can be 0

  	for(int k=0;k<this.n;k++){
  		String[] line = sc.nextLine().split(" : ");
  		int i = Integer.parseInt(line[0]); //the vertex "source"
  		if (weighted==0) {
  			String[] successors= line[1].split(", ");
  			System.out.println(i+ " "+ successors.length);
  			for (int h=0;h<successors.length;h++)
  					this.adjmat[i-1][Integer.parseInt(successors[h])-1]=1;
  		}
  		else {
  			line = line[1].split(" // ");
  			if ((line.length==2)&&(line[1].charAt(0)!=' ')){// if there really are some successors, then we must have something different from " " after "// "
  				  	String[] successors= line[0].split(", ");
  			  	    String[] theirweights= line[1].split(", ");
  				  	for (int h=0;h<successors.length;h++)
  				  	  		this.adjmat[i-1][Integer.parseInt(successors[h])-1]=Float.parseFloat(theirweights[h]);
  			}
  		}
  	}
  }

  public GraphM4A(int n, int type, int weighted, float[][] adjmat){
    this.n = n;
    this.type = type;
    this.weighted = weighted;
    this.adjmat = adjmat;
  }



//method to be applied only when type=0
  public int[] degree(){
	int[] tmp=new int[this.n];
    for(int i=0;i<this.n;i++)
    	tmp[i]=0;
    for(int i=0;i<this.n;i++)
      for(int j=0;j<this.n;j++)
        if(this.adjmat[i][j] != 0)
          tmp[i] = tmp[i] + 1 ;
    return tmp;

  }

//method to be applied only when type=1
  public TwoArrays4A degrees(){
	  int[] tmp1=new int[this.n]; //indegrees
	  int[] tmp2=new int[this.n]; //outdegrees
	  for(int i=0;i<this.n;i++) {
	    tmp1[i]=0;
	    tmp2[i]=0;
	  }
    for(int i=0;i<this.n;i++)
      for(int j=0;j<this.n;j++)
        if(this.adjmat[i][j]!=0){
          tmp2[i]= tmp2[i] +1;
          tmp1[j]=tmp1[j]+1;
        }
    return(new TwoArrays4A(tmp1,tmp2));

  }

  public int getType() {
  	return this.type;
  }

  public Graph transposed(boolean ListMat){ // Matrix if true, List if false
    if(ListMat){
      return transMatMat();
    } else {
      return transMatList();
    }
  }

  private GraphM4A transMatMat(){
    float[][] transposedMat = new float[this.adjmat.length][this.adjmat.length];
    for(int i=0;i<this.adjmat[1].length;i++){
      for(int j=0;j<this.adjmat[1].length;j++){
        transposedMat[j][i] = this.adjmat[i][j];
        transposedMat[i][j] = this.adjmat[j][i];
      }
    }
    return new GraphM4A(transposedMat[1].length,this.type,this.weighted,transposedMat);
  }

  private GraphL4A transMatList(){
    if(this.weighted == 0){
      GraphL4A adjlist = new GraphL4A(this.adjmat.length,this.type,this.weighted,new Node4A[this.adjmat.length]);
      for(int i=0;i<this.adjmat.length;i++)
        adjlist.getAdjlist()[i] = null;
      for(int i=0;i<this.adjmat.length;i++){
        for(int j=0;i<this.adjmat.length;j++){
          if(this.adjmat[i][j] == 1){
            adjlist.add(j,i);
          }
        }
      }
      return adjlist;
    } else {
      GraphL4A adjlistW = new GraphL4A(this.adjmat.length,this.type,this.weighted,new WeightedNode4A[this.adjmat.length]);
      for(int i=0;i<this.adjmat.length;i++) 
        adjlistW.getAdjlistW()[i] = null;
      for(int i=0;i<this.adjmat.length;i++){
        for(int j=0;j<this.adjmat.length;j++){
          if(this.adjmat[i][j] != 0.0){
            adjlistW.addW(j,i,this.adjmat[i][j]);
          }
        }
      }
      return  adjlistW;
    }
  }

  public void printGraph() {
    System.out.println("-----------------------------------------------");
      for(int i=0;i<this.adjmat.length;i++) {
        for(int j=0;j<this.adjmat.length;j++) {
          System.out.print(" "+this.adjmat[i][j]);
        }
        System.out.println();
      }
      System.out.println("-----------------------------------------------");
  }


}
