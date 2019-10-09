import java.util.*;

public class SimpleSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> inputDocuments = DocumentsArchive();
        List<DocumentProps> documentPropsList = new ArrayList<>();
        TFIDFCalculatior tfidfCalc = new TFIDFCalculatior();
        List<Map<String, Double>> TermIndex = new ArrayList<>();

        //Question: which is more optimal in this case, basic for loop or foreach stream?
        inputDocuments.forEach((inputDoc) -> {
            DocumentProps doc = new DocumentProps();
            doc.setWordCountMap(tfidfCalc.mapTerms(inputDoc));
            doc.setTermFreqMap(tfidfCalc.calculateTF(doc.getWordCountMap()));
            documentPropsList.add(doc);
        });
        HashMap<String, Double> idfMap = tfidfCalc.calculateIDF(documentPropsList);

        for (DocumentProps doc : documentPropsList) {
            HashMap<String, Double> tfIDF = new HashMap<>();

            HashMap<String, Double> termFreq = doc.getTermFreqMap();
            termFreq.forEach((k,v)-> {
                double idfVal = 0.0;
                if (idfMap.containsKey(k))
                {
                    idfVal = idfMap.get(k);
                }
                double tfidfValue = v * idfVal;
                tfIDF.put(k, tfidfValue);
            });
            TermIndex.add(tfIDF);
        }

        Map<Double, String> SearchResult = new TreeMap<>(Collections.reverseOrder());;
        while (true)
        {
            SearchResult.clear();
            System.out.println("Enter search term:");
            String input = sc.next();
            for (int i = 0; i < documentPropsList.size(); i++) {
                if (TermIndex.get(i).containsKey(input.toLowerCase())) {
                    SearchResult.put(TermIndex.get(i).get(input), "Document " + (i+1));
                }
            }
            System.out.println(SearchResult.values());
        }
    }
    private static List<String> DocumentsArchive() {
        List<String> documents = new ArrayList<>();
        documents.add("the brown fox jumped over the brown dog");
        documents.add("the lazy brown dog sat in the corner");
        documents.add("the red fox bit the lazy dog");
        documents.add("The fox");
        return documents;
    }

}
