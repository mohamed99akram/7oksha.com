package src;

import lib.*;

public class Main {
  public static void main(String[] args) {
    Constants.initialize();
    IndexerThreading.goIndexer();
  }
}