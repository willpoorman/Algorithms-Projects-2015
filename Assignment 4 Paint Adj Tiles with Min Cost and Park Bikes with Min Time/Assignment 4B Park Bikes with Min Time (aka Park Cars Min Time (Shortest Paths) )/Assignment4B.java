// Name:Will Poorman
// ID: 010628458
//Sources:
//  https://en.wikipedia.org/wiki/Breadth-first_search

import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class Assignment4B {

  public boolean isValidMove(String[][] map, boolean[][] visited, int row, int col, int[] dest){
    //System.out.println("Row being checked = " + row + "\n" + "Col being checked = " + col);//----
    return ( isOnMap(map, row, col)  )
       &&  ( !visited[ row ][ col ]  )
       &&  ( ( map[ row ][ col ].equals(".") )
          || ( map[ row ][ col ].equals("B") )
          //|| ( map[ row ][ col ].equals("V") )
          || ( (row == dest[0]) && (col == dest[1] ) ) );
  }

  public boolean isOnMap(String[][] map, int row, int col) {
    return (row >= 0) && (row < map.length) && (col >= 0) && (col < map[0].length);
  }

  public int shortestPathWeightBFS(String[][] map, int[] bike, int[] space) { //based on https://en.wikipedia.org/wiki/Breadth-first_search
    //System.out.println("Inside shortestPathWeightBFS");
    int minWeight = 0;
    ArrayList<int[]> queue = new ArrayList<int[]>();
    boolean[][] visited = new boolean[ map.length ][ map[0].length ];
    int[] currentPos, nextPos;

    queue.add(bike);
    while(!queue.isEmpty()) {

      currentPos = new int[]{ queue.get(0)[0], queue.get(0)[1] };
      visited[ currentPos[0] ][ currentPos[1] ] = true;
      //map[ currentPos[0] ][ currentPos[1] ] = "V";

      queue.remove(0);

      for(int rowAdj = -1; rowAdj < 2; rowAdj++) {

        for(int colAdj = -1; colAdj < 2; colAdj++) {

          nextPos = new int[]{  currentPos[0]+rowAdj, currentPos[1]+colAdj  };

          if( (rowAdj == 0) || (colAdj == 0) ) {

            if( ( nextPos[0] == space[0] )  &&  ( nextPos[1] == space[1] ) ) {
              //map[ currentPos[0] ][ currentPos[1] ] = "B";
              //printMap(map);
              return minWeight;

            } else if( isValidMove(map, visited, nextPos[0], nextPos[1], space) ) {
              //map[ nextPos[0] ][ nextPos[1] ] = "V";
              //printMap(map);
              visited[ nextPos[0] ][ nextPos[1] ] = true;
              queue.add(nextPos);
            }
          }
  			}
  		}

      minWeight++;
      //System.out.println("minWeight = " + minWeight +"\n");
      //map[ currentPos[0] ][ currentPos[1] ] = "B";
    }

    return -1;
  }


  //Test method
  public void printMap(String[][] map) {

    System.out.println();
    for(String[] row : map) {
      for(String col : row) {
        System.out.print(col);
      }
      System.out.println();
    }
    System.out.println();

  }


  public String[][] arrayTo2DArray(String[] input, ArrayList<int[]> bikes, ArrayList<int[]> spaces) { //based on strToIntArray from Assignment4A
    String[] inputParsed;
    String[][] output = new String[input.length][];
    int[] coord = new int[2];

    for(int i = 0; i < input.length; i++) {
      inputParsed = input[i].split("");
      output[i] = new String[inputParsed.length];
      for(int j = 0; j < inputParsed.length; j++) {
        if(inputParsed[j].equals("B")){
          coord = new int[]{i, j};
          bikes.add(coord);

        } else if(inputParsed[j].equals("S")){
          coord = new int[]{i, j};
          spaces.add(coord);

        }
        output[i][j] = inputParsed[j];
      }
    }

    //Test that input saved to output correctly
    printMap(output);

    //Test that bikes were saved correctly
    // System.out.println("Bikes:");
    // for(int[] bike : bikes) {
    //   for (int i = 0; i < bike.length; i++) {
    //     if(i == 0) {
    //       System.out.print("Row = ");
    //     } else {
    //       System.out.print("Col = ");
    //     }
    //     System.out.println(bike[i]);
    //   }
    //   System.out.println();
    // }

    //Test that spaces were saved correctly
    // System.out.println("Spaces:");
    // for(int[] space : spaces) {
    //   for (int i = 0; i < space.length; i++) {
    //     if(i == 0) {
    //       System.out.print("Row = ");
    //     } else {
    //       System.out.print("Col = ");
    //     }
    //     System.out.println(space[i]);
    //   }
    //   System.out.println();
    // }

    return output;
  }

  public int minTime(String[] map) {
    ArrayList<int[]> bikes = new ArrayList<int[]>();
    ArrayList<int[]> spaces = new ArrayList<int[]>();
    ArrayList<int[]> bikePaths = new ArrayList<int[]>();
    int[] bikePath;

    String[][] mapAs2DArray = arrayTo2DArray(map, bikes, spaces);

    for(int[] bike : bikes) {
      //System.out.println("Bike #" + bikes.indexOf(bike));
      bikePath = new int[spaces.size()];

      for(int[] space : spaces) {
        bikePath[spaces.indexOf(space)] = shortestPathWeightBFS(mapAs2DArray, bike, space);
        //System.out.println("Space #" + spaces.indexOf(space));
      }

      bikePaths.add(bikePath);
      //System.out.println("BikePath #" + bikePaths.indexOf(bikePath));
    }

    //Test that bikePaths were saved correctly
    int count;
    for(int[] bike : bikePaths) {
      System.out.println("Bike #" + bikePaths.indexOf(bike));
      count = 0;
      for (int path : bike) {
        System.out.println("to Space #" + count + ": " + path);
        count++;
      }
    }



    return 0;
	}

  public static void main(String[] args) {
    String[][] testMaps = {{"B..FS",
                            "...FF",
                            "....."},

                           {"B..B.",
                            "FFFFF",
                            "..S.S"},

                           {"B",
                            "S"},

                          {"..S.",
                           ".B.S",
                           "....",
                           ".B.."},/*,

                           {"...FFFF.",
                            "B..FS...",
                            "..FFFFF.",
                            "........"},

                           {"BBB..FS.S.S",
                            "BBB..F.S.S.",
                            "BB.........",
                          "BB...FSSSSS"}*/};

    Assignment4B test = new Assignment4B();

    for(String[] testMap : testMaps) {
      test.minTime(testMap);
    }
  }
}
