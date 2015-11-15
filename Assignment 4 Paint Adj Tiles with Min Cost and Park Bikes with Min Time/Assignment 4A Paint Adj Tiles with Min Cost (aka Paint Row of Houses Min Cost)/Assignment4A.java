// Name:Will Poorman
// ID: 010628458
//Sources:
//  http://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java
//  http://karmaandcoding.blogspot.com/2012/02/house-coloring-with-red-blue-and-green.html

import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class Assignment4A {

  public int[][] strToIntArray(String[] input) { //based on top answer on this page: http://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java
    String[] inputParsed;
    int[][] output = new int[input.length][];

    for(int i = 0; i < input.length; i++) {
      inputParsed = input[i].split(" ");
      output[i] = new int[inputParsed.length];
      //System.out.println(inputParsed.length);
      for(int j = 0; j < inputParsed.length; j++) {
        //System.out.println("j = " + j);
        output[i][j] = Integer.parseInt(inputParsed[j]);
      }
    }

    return output;
  }

  public int minPrice(String[] tiles) { //based on http://karmaandcoding.blogspot.com/2012/02/house-coloring-with-red-blue-and-green.html
    int[][] tilesAsInt;
    int minTotalPrice = 0;
    int[] minPrices;
    int[] result;

    tilesAsInt = strToIntArray(tiles);
    minPrices = new int[tilesAsInt[0].length];
    result = new int[tilesAsInt[0].length];

    for(int i = 0; i < (tilesAsInt.length); i++) {

      for(int j = 0; j < result.length; j++) {
        result[j] = Math.min( (tilesAsInt[i][j] + minPrices[(j+1)%(minPrices.length)]), (tilesAsInt[i][j] + minPrices[(j+2)%(minPrices.length)])  );
      }

      for(int j = 0; j < minPrices.length; j++) {
        minPrices[j] = result[j];
      }

    }

    minTotalPrice = Math.min( Math.min(minPrices[0], minPrices[1]), minPrices[2] );

    return minTotalPrice;
	}

  public static void main(String[] args) {
    String[][] tiles = {{"1 2 3", "10 20 30", "100 200 300"},
                        {"2 4 6", "10 10 10", "4 2 6"},
                        {"2 4 6", "10 10 10", "4 6 2"},
                        {"2 3 4", "100 100 100", "53 63 73", "59 42 5", "52 52 46", "7 8 4"},
                        {"1 2 5", "40 50 60", "200 300 400"},
                        {"1 2 5", "1 50 60", "1 2 4", "1 2 7", "1 2 3"},
                        {"1 2 5", "1 2 5", "1 2 5", "1 50 60", "1 2 4", "1 2 7", "100 200 3"}};

    Assignment4A test = new Assignment4A();

    for(int i = 0; i < tiles.length; i++) {
      System.out.println("min for tiles[" + i + "] = " + test.minPrice(tiles[i]));
    }

  }
}
