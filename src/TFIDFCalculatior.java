import java.util.*;

public class TFIDFCalculatior {
    private SortedSet<String> wordList = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

    public HashMap<String, Double> calculateTF(HashMap<String, Integer> inputMap) {
        
        HashMap<String, Double> TFMap = new HashMap<>();
        double finalSum = inputMap.values().stream().mapToDouble(v -> v).sum();;
        inputMap.forEach((k, v) -> {
            double tf = v / finalSum;
            TFMap.put(k,tf);
        });
        return TFMap;
    }

    public HashMap<String, Double> calculateIDF(List<DocumentProps> docProps) {
        HashMap<String, Double> IDFMap = new HashMap<>();
        int size = docProps.size();
        double wordCount;

        for (String word : wordList) {
            wordCount = 0;
            for (DocumentProps doc : docProps) {
                HashMap<String, Integer> tempMap = doc.getWordCountMap();
                if (tempMap.containsKey(word))
                {
                    wordCount++;
                }
            }
            double idf = Math.log(size / wordCount);
            IDFMap.put(word, idf);
        }
        return IDFMap;
    }

    public HashMap<String, Integer> mapTerms(String input) {
        HashMap<String, Integer> termCount = new HashMap<>();
        //Scanner due to simplicity of test data, would use BufferedReader with file input
        Scanner sc = new Scanner(input);
        while (sc.hasNext())
        {
            //If input complexity increases, implement a filter function to ignore all non-alphabetical inputs.
            String word = sc.next().toLowerCase();
            wordList.add(word);
            if (termCount.containsKey(word))
            {
                termCount.put(word, termCount.get(word)+1);
            }else {
                termCount.put(word, 1);
            }
        }
        sc.close();
        Map<String, Integer> treeMap = new TreeMap<>(termCount);
        return new HashMap<>(treeMap);
    }
}
