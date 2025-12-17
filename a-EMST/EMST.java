import java.util.*;
import java.lang.*;
import java.io.*;

class Point{
  private int x;
  private int y;

  public Point(int x, int y){ this.x=x; this.y=y; };
  public int getX(){ return x; }
  public int getY(){ return y; }
  public String toString(){
    return "("+x+","+y+")";
  }

  public double distanceFrom(Point p){
    return Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y));
  }
};

class Edge{
  private Point p1;
  private Point p2;

  
  public Edge(Point p1, Point p2){ this.p1=p1; this.p2=p2; }
  public double getWeight(){
    return p1.distanceFrom(p2);
  }
};

public class EMST{

  public static void main(String[] args){
    if(args.length!=2){
      System.out.println("Invalid command line arguments: expected...");
      return;
    }

    double maxWeight;
    Set<Point> D = new HashSet<>();
    Set<Edge> Edges = new HashSet<>();
    
    //Get maxWeight from argument
    try{ 
      maxWeight = Double.parseDouble(args[1]);
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

    System.out.println("maxWeight: " + maxWeight);
    System.out.println("Points: ");
    for(Point p : D){
      System.out.println(p.toString());
    }
  }

}
