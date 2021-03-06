import java.util.List;

/**
 * 
 * @author Tobias Ziegelmayer
 * @version 1.0.0
 * This class constructs an entry of a corpus.
 * The entry contains id, author, date, source url,
 * headlines, summary and text a.s.o.
 * It also use the class StanfordNLP for annotating 
 * the summary and the text.
 *
 */
public class Entry {
	
	int id;
	String author;
	String date;
	String url;
	String headlines;
	String summary;
	String text;

	List<String> contentWordsText;
	List<String> contentWordsHeadline;
	List<List<String>> textTokens;
	List<List<String>> summaryTokens;
	List<List<String>> headlineTokens;
	List<List<String>> textLemmata;
	List<List<String>> summaryLemmata;
	List<List<String>> headlineLemmata;
	List<FeatureVector> featureVectors;
	List<Double> distances;
	List<Integer> labels;
	double meanDistance;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHeadlines() {
		return headlines;
	}
	public void setHeadlines(String headlines) {
		this.headlines = headlines;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getContentWordsText() { return contentWordsText; }
	public void setContentWordsText(List<String> contentWordsText) { this.contentWordsText = contentWordsText; }
	public List<String> getContentWordsHeadline() { return contentWordsHeadline; }
	public void setContentWordsHeadline(List<String> contentWordsHeadline) { this.contentWordsHeadline = contentWordsHeadline; }
	public List<FeatureVector> getFeatureVectors() {
		return featureVectors;
	}
	public void setFeatureVectors(List<FeatureVector> featureVectors) {
		this.featureVectors = featureVectors;
	}
	public List<List<String>> getTextTokens() {
		return textTokens;
	}
	public void setTextTokens(List<List<String>> textTokens) {
		this.textTokens = textTokens;
	}
	public List<List<String>> getSummaryTokens() {
		return summaryTokens;
	}
	public void setSummaryTokens(List<List<String>> summaryTokens) {
		this.summaryTokens = summaryTokens;
	}
	public List<List<String>> getTextLemmata() {
		return textLemmata;
	}
	public void setTextLemmata(List<List<String>> textLemmata) {
		this.textLemmata = textLemmata;
	}
	public List<List<String>> getSummaryLemmata() {
		return summaryLemmata;
	}
	public void setSummaryLemmata(List<List<String>> summaryLemmata) {
		this.summaryLemmata = summaryLemmata;
	}
	public List<List<String>> getHeadlineTokens() {
		return headlineTokens;
	}
	public void setHeadlineTokens(List<List<String>> headlineTokens) {
		this.headlineTokens = headlineTokens;
	}
	public List<List<String>> getHeadlineLemmata() {
		return headlineLemmata;
	}
	public void setHeadlineLemmata(List<List<String>> headlineLemmata) {
		this.headlineLemmata = headlineLemmata;
	}
	public List<Double> getDistances() {
		return distances;
	}
	public void setDistances(List<Double> distances) {
		this.distances = distances;
	}
	public List<Integer> getLabels() {
		return labels;
	}
	public void setLabels(List<Integer> labels) {
		this.labels = labels;
	}
	public double getMeanDistance() {
		return meanDistance;
	}
	public void setMeanDistance(double meanDistance) {
		this.meanDistance = meanDistance;
	}


	/**
	 * This method create a string from some getter method of an entry
	 * @return String
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getId()+", ");
		sb.append(this.getAuthor()+", ");
		sb.append(this.getHeadlines()+", ");
		sb.append(this.getUrl()+", ");
		sb.append(this.getDate()+", ");
		sb.append(this.getSummary()+", ");
		sb.append(this.getText()+", ");
		
		
		return sb.toString();
	}

	/**
	 * Emtpy constructor of an entry
	 */
	public Entry() {}

	/**
	 * Constructor of an entry
	 * @param text
	 * @param headline
	 */
	public Entry (String text, String headline){
		Helper helper = new Helper();
		StanfordNLP snlp = new StanfordNLP();
		this.setText(text);
		List<List<List<String>>> stanfordText = snlp.stanfordLemmatizerAndTokenizer(this.getText());
		this.setTextTokens(stanfordText.get(0));
		this.setTextLemmata(stanfordText.get(1));

		this.setHeadlines(headline);
		List<List<List<String>>> stanfordHeadline = snlp.stanfordLemmatizerAndTokenizer(this.getHeadlines());
		this.setHeadlineTokens(stanfordHeadline.get(0));
		this.setHeadlineLemmata(stanfordHeadline.get(1));
		WordFrequencies wf = new WordFrequencies();
		this.setContentWordsText(wf.getTop10(this.getTextLemmata()));
		this.setContentWordsHeadline(wf.getList(this.getHeadlineLemmata()));

		List<FeatureVector> featureVectors = helper.createFeatureVectors(this);
		this.setFeatureVectors(featureVectors);
	}
}
