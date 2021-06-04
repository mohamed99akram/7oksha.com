package lib;

// This class detects stop words (like a, an, this,etc..)
// and also returns only english letters and numbers
public class FilterString {

    public static String termOk(String term){
        String finalTerm = new String(term);
        // detect stop words
        // Only Alphabetic or numeral
        // TODO change allowed letter?
        finalTerm = finalTerm.replaceAll("[^a-zA-Z]", ""); // #https://www.geeksforgeeks.org/how-to-remove-all-non-alphanumeric-characters-from-a-string-in-java/
        if (StopWord.isStopWord(finalTerm)) {
            return "";
        }
         return finalTerm;
    }

    public static void main(String[] args) {
        System.out.println(FilterString.termOk("(Hello@!()>., World"));
    }
}
