import java.util.HashMap;

public class DocumentProps {
    private HashMap<String,Double> termFreqMap;
    private HashMap<String,Integer> wordCountMap;

    public HashMap<String, Double> getTermFreqMap() {
        return termFreqMap;
    }

    public void setTermFreqMap(HashMap<String, Double> termFreqMap) {
        this.termFreqMap = termFreqMap;
    }

    public HashMap<String, Integer> getWordCountMap() {
        return wordCountMap;
    }

    public void setWordCountMap(HashMap<String, Integer> wordCountMap) {
        this.wordCountMap = wordCountMap;
    }
}
