package lib;

import java.util.ArrayList;

public class Constants {
    // Where files are stored before stemming
    //TODO: change this to the directory in Crawler's directory to take its output 
    public static final String filesDir = new String("2500/");
    // where files are stored before indexing and after stemming
    public static final String stemmedDir = new String("2500_output/");
    // Indexer output folder
    public static final String IndexerOutput = new String("IndexerOutput/");
    // How many rows will be executed per query, increase this to make queries faster
    public static final int rowsPerQuery = 20000;
    // Number of threads. This will be changed to the number of files if number of files is less than it
    public static final int NThreads = 8;
    // Number of files made
    public static int NFILES = 0;
    // Number of files per Indexer query
    public static final int filesPerIndexerQuery = 5;
    // Paths coming out of indexer
    public static ArrayList<String>[] indexerPaths;

    //initialize pahts
    public static void initialize(){
        indexerPaths = new ArrayList[NThreads];
        for (int i = 0; i < indexerPaths.length; i++) {
            indexerPaths[i] = new ArrayList<String>();
        }
    }
}
