import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Kenoboss
 * @version 1.0.0
 * This class contains corpus function for the project TextSummarization
 *
 */
public class Corpus {
	
	List < List < String > > cLines;
	
	public List<List<String>> getcLines() {
		return cLines;
	}


	public void setcLines(List<List<String>> cLines) {
		this.cLines = cLines;
	}
	
	public Corpus () {
		cLines = this.readIn();
	}

	public List < List < String > > readIn () {
		Helper helper = new Helper();
		String s = System.getProperty("user.dir");
		String path = s+"/data/news_summary.csv";
		List < List < String > > lines = helper.readCSVFile(path, ",", null);
		
		return lines;
	}
	
	public List<HashMap<String, String> > createCorpus(){
		List<HashMap<String, String> > result = new ArrayList<>();
		Corpus corpus = new Corpus();
		List < List < String > > cLines = corpus.readIn();
		int lineCounter = 0;
		ArrayList<Integer> errorList = new ArrayList<>();
        for(List<String> line: cLines) {
        	lineCounter++;
        	try {
        		HashMap<String, String> tmp = new HashMap<>();
            	tmp.put("author", line.get(0));
            	tmp.put("date", line.get(1));
            	tmp.put("headlines", line.get(2));
            	tmp.put("read_more", line.get(3));
            	tmp.put("text", line.get(4));
            	tmp.put("ctext", line.get(5));
        		result.add(tmp);
        	}
        	catch (Exception e) {
        		errorList.add(lineCounter);
        	}

        }
        if (errorList.size() > 0) {
        	double errorRate = (double)errorList.size() / cLines.size();
        }
		
		return result;
	}

}
