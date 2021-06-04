package lib;

import java.util.ArrayList;

// This class inserts files output from indexer into the databse
public class IndexerDBInserter implements Runnable{
    ArrayList<String>paths;
    public IndexerDBInserter(ArrayList<String> paths){
        this.paths = paths;
    }
    @Override
    public void run() {
        insertIntoDB(this.paths);
    }
     boolean insertIntoDB(ArrayList<String> paths) {
        try {
            for (int i = 0; i < paths.size(); i++) {
                ReadFile rf = new ReadFile(paths.get(i));
                theDataBase db = new theDataBase();
                String[] rows = rf.file.split("\n");
                for (int j = 0; j < rows.length; j++) {
                    //TODO change this if performing bulk insertion
                    db.insertRow(rows[j]);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}