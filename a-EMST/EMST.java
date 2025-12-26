import java.lang.*;
import java.io.*;
import java.util.*;
import java.util.Iterator;
import java.util.Set; 
import java.util.HashSet;
import java.util.PriorityQueue;

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

class PointDistance implements Comparable<PointDistance>{
  double distance;
  Point p;

  public PointDistance(Point p, double distance){
    this.p = p;
    this.distance = distance;
  }

  public int compareTo(PointDistance pd){
    if(this.distance>pd.distance) return 1;
    if(this.distance<pd.distance) return -1;
    return 0;
  }
}

public class EMST{
  public static void main(String[] args){

    //Guard
    if(args.length<2){
      System.err.println("usage: java EMST <input_path> <alfa>");
      return;
    }

    //********* Input *************//
    float maxWeight;                  //Maximum weight between two edges in EMST
    Set<Point> D = new HashSet<>();   //Set of Points (x,y)

    //Get maxWeight from argument
    try{ 
      maxWeight = Float.parseFloat(args[1]);
    }catch(NumberFormatException e){
      System.err.println(e.getMessage());
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
      System.out.println(e.getMessage());
      return;
    }catch(IOException e){
      System.out.println(e.getMessage());
      return;
    }catch(NumberFormatException e){
      System.out.println(e.getMessage());
      return;
    }

    //******** Calculate minimum spanning tree **********//
    int n = D.size();                       //Number of points
    double sum = 0;                         //w(E)
    Set<Point> V = new HashSet<>();         //Visited points
    PriorityQueue<PointDistance> minHeap = new PriorityQueue<>();   //Pair point distance from EMST
    Map<Point,Double> distanceMST = new HashMap<>();                //Minimum distance from EMST for each point
    Map<Point,Point> parent= new HashMap<>();                       //EMST

    
    for(Point p:D){
      distanceMST.put(p,Double.POSITIVE_INFINITY);
    }
    Point p1 = D.iterator().next();
    distanceMST.put(p1,0.0);
    minHeap.add(new PointDistance(p1,0.0));

    boolean fail = false;
    while(!minHeap.isEmpty() && !fail){
      //Get closest point to EMST
      PointDistance current = minHeap.poll();
      p1 = current.p;
      if(V.contains(p1)) continue;
      V.add(p1);
      sum+= current.distance;

      if(current.distance > maxWeight){
        fail = true;
      }

      //Update distances
      for(Point p2:D){
        if(!V.contains(p2)){
          double w = p1.distanceFrom(p2);
          //if(w>maxWeight)continue;

          if(w < distanceMST.get(p2)){
            distanceMST.put(p2,w);
            minHeap.add(new PointDistance(p2,w));
            if(n<=10)
              parent.put(p2,p1);
          }
        }
      }
    }

    //********** Output **********//
    if(fail){
      System.out.println("FAIL");
      return;
    }

    System.out.println(Math.round(sum*100.0)/100.0);

    if(n<=10){    //Print EMST
      for(Map.Entry<Point,Point> p:parent.entrySet()){
        System.out.println(p.getKey().toString()+p.getValue().toString());
      }
    }
  }
}

//Time complexity: O(n^2)
//Space complexity: O(n)
