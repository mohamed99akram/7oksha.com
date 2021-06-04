package lib;

import java.util.ArrayList;
// This class puts the output of the indxer in a file system in the form of (v1,v2,v2)
public class IndexerFiled {
    public ArrayList<String> words;
    public ArrayList<Integer> docNum;
    public ArrayList<Integer> indecies;
    public int priority;

    public IndexerFiled(ArrayList<String> words, ArrayList<Integer> docNum, ArrayList<Integer> indecies, int priority) {
        this.words = words;
        this.docNum = docNum;
        this.indecies = indecies;
        this.priority = priority;
    }

    public void insertDataInto(String path) {
        String data = new String();
        for (int i = 0; i < words.size(); i++) {
            data+="('" + words.get(i)+"', "+docNum.get(i)+", "+indecies.get(i)+", "+priority+")\n";
        }
       new OutputFile(path, data);
    }
}