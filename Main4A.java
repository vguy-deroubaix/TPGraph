import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Main4A {

  public static void main(String args[]){
	  /*----------------------------------MAIN POUR TP1-----------------------------------------------------------------------------*/
	  try{
      File file= new File("/home/axel/eclipse-workspace/TP2/src/ExempleTP1.txt");
      
    //If we choose the representation by adjacency matrix
      Scanner sc = new Scanner(file);      
      GraphM4A graphM = new GraphM4A(sc); 
      if (graphM.getType()==0) { //undirected
    	  int[] degree = graphM.degree();
      System.out.println("(Matrix) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");  
      Tools4A.printArray(degree);
      System.out.println("let's go");
      graphM.BFS(1);
      }
      else { //directed
    	  TwoArrays4A pair=graphM.degrees();
    	  int[] indegree =pair.in(); //the result of graphM.degrees() is a pair of arrays, indegree and outdegree
          int[] outdegree =pair.out();
          System.out.println("(Matrix)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(indegree);
          System.out.println("(Matrix)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(outdegree);
          graphM.BFS(1);
          
    	  }	  
      
   // If we choose the representation by adjacency lists
      sc = new Scanner(file);
      GraphL4A graphL = new GraphL4A(sc); 
      if (graphL.getType()==0&&graphL.getWeighted()==0) { //undirected and unweighted
    	  int[] degree = graphL.degree();
    	  System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");  
          Tools4A.printArray(degree);
      }
      if (graphL.getType()==0&&graphL.getWeighted()==1) { //undirected and weighted
    	  int[] degree = graphL.degreeW();
    	  System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");  
          Tools4A.printArray(degree);
      }
      if (graphL.getType()==1&&graphL.getWeighted()==0){ //directed and unweighted
    	  TwoArrays4A pair=graphL.degrees();
    	  int[] indegree = pair.in(); 
          int[] outdegree = pair.out();
          System.out.println("(List) Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(indegree);
          System.out.println("(List) Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(outdegree);
    	  }	  
      if (graphL.getType()==1 && graphL.getWeighted()==1){ //directed and unweighted
    	  TwoArrays4A pair=graphL.degreesW();
    	  int[] indegree = pair.in(); 
          int[] outdegree = pair.out();
          System.out.println("(List)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(indegree);
          System.out.println("(List)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(outdegree);
    	  }	  
      sc.close();
      /*--------------------------------------------------------------------------------------------------------------------------------------*/
      
      
      
      /*-------------------------------------------MAIN POUR TP2---------------------------------------------------------------------*/
      /*-------------------------------------------------EXERCICE_1----------------------------------------------------------------------------*/
      /*
      Scanner scanner = new Scanner(System.in);
      
      System.out.println("\n"+"Enter false for an AdjList or true for a AdjMatrix in input  :");
      String str = scanner.nextLine();
      boolean inputGraph = Boolean.parseBoolean(str);
      
      
      Scanner scanner2 = new Scanner(System.in);
      
      System.out.println("\n"+"Enter false for an AdjList or true for a AdjMatrix in output :");
      String str2 = scanner2.nextLine();
      boolean outputGraph = Boolean.parseBoolean(str2);
      
      
      if(inputGraph)
      {
    	 //Start the method transpose with a Matrix in input
    	 graphM.transposedGraphM(outputGraph).printGraph();  
      }else
      {
    	 graphL.transposedGraphL(outputGraph).printGraph();
      }
      scanner.close();
      scanner2.close();
      */
      /*-------------------------------------------------------------------------EXERCICE_2----------------------------------------------------------------------*/
      //Use this with an adjacency List graphL
      /*
      System.out.println();
      //Start the DFSNumb Method
      graphL.DFSNumb();
      System.out.println("--------------------------------------------------------Tab_D--------------------------------------------------------------");
      graphL.printTab(graphL.getD());
      System.out.println();
      System.out.println();
      System.out.println("--------------------------------------------------------Tab_F--------------------------------------------------------------");
      graphL.printTab(graphL.getF());
      System.out.println();
      System.out.println();

      System.out.println("--------------------------------------------------------Tab_Edges--------------------------------------------------------------");

      //Fill my table of edges with all the edges of the graph
      //I'm using an Edge class 
      graphL.fillTabEdges();
      graphL.displayTabEdges();
      System.out.println();
      System.out.println();
      
      //Browse my ArrayList to determine if the graph contain a cycle and return a boolean
      graphL.isCyclic();
     */
     
     GraphL4A GraphLTranspose = (GraphL4A) graphL.transposedGraphL(false);
     
     graphL.component(GraphLTranspose);
     
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
