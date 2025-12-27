import java.lang.*;
import java.io.*;
import java.util.*;

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

  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(!(o instanceof Point)) return false;
    Point p = (Point) o;
    return this.x == p.x && this.y == p.y;
  }

  @Override
  public int hashCode(){
    return java.util.Objects.hash(x,y);
  }

  public double distanceFrom(Point p){
    return Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y));
  }
};

class BucketKey{
  private int x;
  private int y;

  public BucketKey(double x,double y,double maxWeight) {
    this.x = (int) Math.round(x/maxWeight);
    this.y = (int) Math.round(y/maxWeight);
  }

  public BucketKey(Point p, double maxWeight){
    this.x = (int) Math.floor(p.getX()/maxWeight);
    this.y = (int) Math.floor(p.getY()/maxWeight);
  }

  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(!(o instanceof BucketKey)) return false;
    BucketKey k = (BucketKey) o;
    return this.x == k.x && this.y == k.y;
  }

  @Override
  public int hashCode(){
    return java.util.Objects.hash(x,y);
  }
  public String toString(){
    return "("+x+","+y+")";
  }
}

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

  public static double calculate_aEMST(Set<Point> D, double maxWeight, Map<Point,Point> EMST){
    double sum = 0;                         //w(E)
    Map<BucketKey,HashSet<Point>> grid = new HashMap<>(); //Map containing buckets of size alpha*alpha
    Set<Point> V = new HashSet<>();         //Visited points
    PriorityQueue<PointDistance> minHeap = new PriorityQueue<>();   //Pair point distance from EMST
    Map<Point,Double> distanceMST = new HashMap<>();                //Minimum distance from EMST for each point

    //Initialize distance map and grid
    for(Point p:D){
      //Add point in corresponding bucket
      BucketKey key = new BucketKey(p,maxWeight);
      grid.putIfAbsent(key,new HashSet<Point>());
      grid.get(key).add(p);

      distanceMST.put(p,Double.POSITIVE_INFINITY);
    }

    //Initialize arbitrary point
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
      for(int i=-2;i<=2;i++){
        for(int j=-2;j<=2;j++){
          double keyX = p1.getX()+i*maxWeight;
          double keyY = p1.getY()+j*maxWeight;
          BucketKey key = new BucketKey(keyX,keyY,maxWeight);

          for(Point p2:grid.getOrDefault(key,new HashSet<Point>())){
            if(!V.contains(p2)){
                double w = p1.distanceFrom(p2);
                //if(w>maxWeight)continue;

              if(w < distanceMST.get(p2)){
                distanceMST.put(p2,w);
                minHeap.add(new PointDistance(p2,w));
                EMST.put(p2,p1);
              }
            }
          }
        }
      }
    }

    return (!fail) ? sum : Double.POSITIVE_INFINITY;
  }

  public static void main(String[] args){

    //Guard
    if(args.length<2){
      System.err.println("usage: java EMST <input_path> <alfa>");
      return;
    }

    //********* Input *************//
    double maxWeight;                  //Maximum weight between two edges in EMST
    Set<Point> D = new HashSet<>();    //Set of Points (x,y)
    Map<Point,Point> Parent = new HashMap<>();        //Calculated EMST

    //Get maxWeight from argument
    try{ 
      maxWeight = Double.parseDouble(args[1]);
    }catch(NumberFormatException e){
      System.err.println(e.getMessage());
      return;
    }

    //Get points in file from argument and put them in the corresponding bucket
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
      System.out.println("Errore durante la lettura dei dati");
      return;
    }

    //Calculate EMST
    double sum = calculate_aEMST(D,maxWeight,Parent);

    //********** Output **********//
    if(Double.isInfinite(sum)){
      System.out.println("FAIL");
      return;
    }
    System.out.println(Math.round(sum*100.0)/100.0);
    
    if(D.size()<=10){    //Print EMST
      for(Map.Entry<Point,Point> p:Parent.entrySet()){
        System.out.println(p.getKey().toString()+p.getValue().toString());
      }
    }
  }
}
