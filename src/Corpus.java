import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.sampled.Line;

import edu.stanford.nlp.trees.Tree;

/**
 * 
 * @author Kenoboss
 * @version 1.0.0
 * This class contains corpus function for the project TextSummarization
 *
 */
public class Corpus {
	
	List<Entry> entries;
	

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public Corpus (boolean test) {
		List<Entry> entries = this.createCorpus(test);
		this.setEntries(entries);
	}

	public static List < List < String > > readIn () {
		Helper helper = new Helper();
		String s = System.getProperty("user.dir");
		String path = s+"/data/summary_news_clear.csv";
		List < List < String > > lines = helper.readCSVFile(path);
		
		return lines;
	}
	
	public List<Entry> createCorpus(boolean test){

		StanfordNLP snlp = new StanfordNLP();
		List < List < String > > cLines = readIn();
		List<Entry> entries = new ArrayList<Entry>();
		int lineCounter = 0;
		int i = 0;
		int maxSize = 0;

		if (test == false){maxSize = cLines.size()-1;}
		else{maxSize = 10;}

        for(List<String> line: cLines) {
        	if (lineCounter > 0 && lineCounter <= maxSize) {
        		Entry entry = new Entry();
        		entry.setId(lineCounter);
        		entry.setAuthor(line.get(0));
        		entry.setDate(line.get(1));
        		entry.setHeadlines(line.get(2));
        		entry.setUrl(line.get(3));
        		entry.setSummary(line.get(4));
        		entry.setText(line.get(5));
        		
//        		List<Tree> parseTree = new ArrayList<>();
//        		parseTree = snlp.stanfordPipeLine(line.get(4));
//        		entry.setParsingTreesSummary(parseTree);
//
//        		parseTree = snlp.stanfordPipeLine(line.get(5));
//        		entry.setParsingTreesText(parseTree);
        		
        		
        		entries.add(entry);
        		
        		i++;
        	}
			lineCounter++;
        }
		return entries;
	}
}
