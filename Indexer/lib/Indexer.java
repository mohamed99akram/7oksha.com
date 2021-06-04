package lib;

import java.util.ArrayList;

// This class takes the output of the stemmer and stores each word in the database with its docNum and index
// Threads of it are created in the main function
public class Indexer implements Runnable {
    // array of file paths
    ArrayList<String> Docs;
    theDataBase db;
    int start, end;
    int myThreadNumber;
    ArrayList<String> paths;
    public Indexer(ArrayList<String> Docs, int start, int end, theDataBase db, int threadNumber) {
        this.Docs = Docs;
        this.start = start;
        this.end = end;
        this.db = db;
        this.myThreadNumber = threadNumber;
        paths = Constants.indexerPaths[this.myThreadNumber];
        System.out.println("Thread#"+ this.myThreadNumber+" will be working on files from "+this.start+" to "+this.end);
    }

    // TODO| identify each word whether it's h1, h2, h3, p, div, etc. and put its
    // TODO| priority according to this (Sooo hard)
    @Override
    public void run() {
        for (int i = this.start; i < this.end; i++) {
            int starting_i = i;
            ArrayList<String> fileTerms = new ArrayList<>();
            ArrayList<Integer> takenWordsIndecies = new ArrayList<>();
            ArrayList<Integer> docNumArr = new ArrayList<>();
            for (int w = 0; w < Constants.filesPerIndexerQuery && i < this.end; w++, i++) {
                // read the output of the stemmer
                ReadFile rFile = new ReadFile(Constants.stemmedDir + this.Docs.get(i));
                // indx of words
                Integer indx = 0;
                // array of lines
                String[] lines = rFile.file.split("\n");
                // array of words
                for (int j = 0; j < lines.length; j++) {
                    String[] terms = lines[j].split(" ");
                    for (int k = 0; k < terms.length; k++) {
                        String ok = FilterString.termOk(terms[k]);
                        if (!ok.isBlank()) {
                            fileTerms.add(ok);
                            takenWordsIndecies.add(indx);
                            docNumArr.add(Integer.parseInt(this.Docs.get(i).replace(".txt", "")));
                        }
                        // true index in the original document
                        indx++;
                    }
                }
            }
            // will be increased next loop
            i--;
            System.out.println("Thread #" + myThreadNumber + " Inserting files: From " + starting_i + " To " + i
            + " Into the file system");


            // File path
            String pathToRows = "thread#"+this.myThreadNumber+"_From_"+starting_i+"_to_"+i+".txt";
            paths.add(pathToRows);
            //Inserting to file system
            IndexerFiled indxFiled = new IndexerFiled(fileTerms,docNumArr,takenWordsIndecies,0);
            indxFiled.insertDataInto(pathToRows);
            // insert array of words in the database & remove .txt from its name
            // db.insertIndexedFile(fileTerms, takenWordsIndecies, docNumArr, 0);
            // TODO syncronize NFILES
            System.out.println("Total Files done = "+ Constants.NFILES);
            Constants.NFILES+=i-starting_i+1;
            // TODO delete this
            System.out.println("Thread #" + myThreadNumber + " Filesystem.Files Done: From " + starting_i + " To " + i);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> files = new ArrayList<>();
        files.add("Files/in2.txt");
        theDataBase db = new theDataBase();
        Thread t1 = new Thread(new Indexer(files, 0, 1, db, 0));
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
