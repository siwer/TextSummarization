import java.util.ArrayList;
import java.util.List;
/**
 * @author Simon Werner
 * @version 1.0.0
 * This class contains functions for extracting features from Sentences
 */
public class Preprocessing {

    //relative Position des Satzes im Text
    public double positionInTextRel (List<String> sentence, List<List<String>> text) {
        double tmp = (text.indexOf(sentence) +1)/ (double) text.size();
        return tmp;
    }

    //Anzahl Wörter in Satz
    public double countWords (List<String> sentence, List<List<String>> sentences) {
        double partOf = 0.0;
        int sum = 0;
        for (List<String> sent : sentences){sum += sent.size();}
        partOf = sentence.size() / (double) sum;
        return partOf;
    }

    //Prüfung ob erster Satz
    public double isFirst (List<String> sentence, List<List<String>> text) {
        if (text.indexOf(sentence) == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
