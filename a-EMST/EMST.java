import java.util.*;
import java.lang.*;
import java.io.*;

class Point{
  //Attributes
  private int x;
  private int y;

  //Constructor
  public Point(int x, int y){ this.x=x; this.y=y; };

  //Methods
  public int getX(){ return x; }
  public int getY(){ return y; }

  public String toString(){
    return "("+x+","+y+")";
  }

  public boolean equals(Point p){
    return this.x == p.x && this.y == p.y;
  }

  public double distanceFrom(Point p){
    return Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y));
  }
};

class Edge implements Comparable<Edge>{
  //Attributes
  private Point p1;
  private Point p2;
  private double weight;
 
  //Constructor
  public Edge(Point p1, Point p2){
    this.p1=p1; this.p2=p2;
    this.weight = p1.distanceFrom(p2);
  }

  //Methods
  public Point getP1() { return p1; }
  public Point getP2() { return p2; }
  public double getWeight(){
    return weight;
  }

  public int compareTo(Edge e){
    if(this.weight>e.weight) return 1;
    else if(this.weight<e.weight) return -1;
    return 0;
  }

  public String toString(){
    return p1.toString()+p2.toString()+", w(e):"+weight;
  }
};

public class EMST{
  public static void main(String[] args){
    if(args.length!=2){
      System.out.println("Invalid command line arguments: expected...");
      return;
    }


    //Input
    float maxWeight;
    Set<Point> D = new HashSet<>();
    
    //Get maxWeight from argument
    try{ 
      maxWeight = Float.parseFloat(args[1]);
    }catch(NumberFormatException e){
      maxWeight = 0;
      System.out.println("Invalid command ..");
      return;
    }

    //Get points in file from argument
    try{
      String path = args[0];
      BufferedReader br = new BufferedReader(new FileReader(path));

      String line;
      while((line=br.readLine())!=null){
        line = line.replace("(","").replace(")","").trim();
        String[] coord = line.split(",",2);
        Point p = new Point(Integer.parseInt(coord[0]),Integer.parseInt(coord[1]));
        D.add(p);
      }

      br.close();
    }catch(FileNotFoundException e){
      System.out.println("Error: File not found");
      return;
    }catch(IOException e){
      e.printStackTrace();
      return;
    }catch(NumberFormatException e){
      e.printStackTrace();
      return;
    }
    
    //Calculate minimum spanning tree
    int n = D.size();     //Number of iterations
    float sum = 0;
    boolean fail = false;

    Set<Point> V = new HashSet<>();
    PriorityQueue<Edge> minEdges = new PriorityQueue<>();
    Set<Edge> EMST;
    if(n<=10)
      EMST = new HashSet<>();

    Point p1 = D.iterator().next();   //First point in set
    D.remove(p1);
    V.add(p1);
    for(int i=0;i<n-1 && !fail;i++){
      //Add all new edges to the ordered set
      for(Point p2:D){
        Edge e = new Edge(p1,p2);
        minEdges.add(e);
      }

      //Find edge with minimum weight
      Edge minEdge;
      do{
        minEdge = minEdges.poll();
      }while(V.contains(minEdge.getP2()));

      E.add(minEdge);
      D.remove(minEdge.getP2());
      V.add(minEdge.getP2());
      p1 = minEdge.getP2();

      if(minEdge.getWeight() > maxWeight)
        fail=true;
    }

    System.out.println("Final EMST:");
    if(!fail){
      float sum = 0;
      for(Edge e:E){
        sum+=e.getWeight();
        System.out.println(e.toString());
      }

      System.out.println("w(E): " + sum);
    }else{
      System.out.println("FAIL");
    }
  }

}
