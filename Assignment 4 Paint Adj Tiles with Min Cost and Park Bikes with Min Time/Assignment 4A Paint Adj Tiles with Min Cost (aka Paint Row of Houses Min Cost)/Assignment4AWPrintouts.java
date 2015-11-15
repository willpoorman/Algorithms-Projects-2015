// Name:Will Poorman
// ID: 010628458
//Sources:
//  http://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java

import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class Assignment4A {

  int blocked; //is a class variable so that the value carries across multiple calls of findMinTilePrice to know which tile color is not allowed due to adjacent tile's chosen color

  public int findMinTilePrice(int[] colorPrices) {
    int min;
    int newBlock = this.blocked;

    System.out.println("in findMin...");

    if(this.blocked == 0) {
      min = colorPrices[1];
      newBlock = 1;
    } else {
      min = colorPrices[0];
      newBlock = 0;
    }

    System.out.println("start min = " + min);
    System.out.println("blocked index = " + blocked);

    for(int i = 0; i < colorPrices.length; i++) {
      if((min > colorPrices[i]) && (i != this.blocked)) {
        min = colorPrices[i];
        newBlock = i;
        System.out.println("new min = " + min);
      }
    }

    this.blocked = newBlock;

    System.out.println("returning min = " + min);
    System.out.println("blocked index = " + blocked + "\n");

    return min;
  }

  public int[] strToIntArray(String input) { //based on top answer on this page: http://stackoverflow.com/questions/7646392/convert-string-to-int-array-in-java
    String[] inputParsed = input.split(" ");
    int[] output = new int[inputParsed.length];

    for(int i = 0; i < inputParsed.length; i++) {
      try {
        output[i] = Integer.parseInt(inputParsed[i]);
      } catch (NumberFormatException e) {
        System.out.println("Sorry, not a valid number");
      }
    }

    return output;
  }

  public int minPrice(String[] tiles) {
    int[] tile;
    this.blocked = 3; //default to 3 since each tile will have 3 prices so index from 0 to 2 and we don't want to block to start with
    int min = 0;

    System.out.println("in minPrice");

    for(int i = 0; i < tiles.length; i++) {
      tile = strToIntArray(tiles[i]);

      for(int j = 0; j < tile.length; j++) {
        System.out.println("tile[" + j + "] = " + tile[j]);
      }

      min += findMinTilePrice(tile);
    }

    return min;
	}

  public static void main(String[] args) {
    String[] tiles = {"2 4 6", "1 1 1", "4 2 6"};
    Assignment4A test = new Assignment4A();

    System.out.println("min = " + test.minPrice(tiles));

  }
}
