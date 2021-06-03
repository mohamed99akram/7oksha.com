package DBManagement;
import java.util.ArrayList;

import lib.*;
public class FoundSites {
    public static void main(String[] args) {
        
    ReadFile rf = new ReadFile("allhashes.txt");
    theDataBase db = new theDataBase();
    String[] hashes = rf.file.split("\n");
    ArrayList<Integer>allhashes = new ArrayList<>();
    ArrayList<String>URLs = new ArrayList<>();
    for(int i = 0; i< hashes.length; i++){
        allhashes.add(Integer.parseInt(hashes[i]));
        URLs.add("www."+i);
    }
    db.insertFoundSites(URLs, allhashes);
    }
}
