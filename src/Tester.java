import java.util.List;

import Sentence.Sentence;
import Utils.Utils;

public class Tester {

	public static String inputFilePath = "";

	public static void main(String[] args) {
		List<String> inputStrings ;
		
		if(inputFilePath.isEmpty()) {
			inputStrings = Utils.readFromConsole() ;
		} else {
			inputStrings = Utils.readFromFile(inputFilePath);
		}
		
		List<Sentence> sentences = Sentence.convertTextToSentences(inputStrings);
		Sentence.tokenize(sentences);
		
		for(Sentence s : sentences) {
			s.printTokens();
		}
	}
}
