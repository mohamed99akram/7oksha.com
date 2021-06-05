package src;

import lib.*;

public class Main {
  public static void main(String[] args) {
    Constants.initialize();
    IndexerThreading.goIndexer();
    String allQueriesOutput = new String("INSERT INTO INDEXER VALUES");
    for (int i = 0; i < Constants.allQueries.length;i++) {
      allQueriesOutput+=Constants.allQueries[i];
      allQueriesOutput+="\n";
    }
    allQueriesOutput+=";";
    new OutputFile("Indexer.sql", allQueriesOutput);
  }
}