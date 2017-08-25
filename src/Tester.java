import java.util.List;

import Interpreter.Interpreter;
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
		// Step 1 : Tokenize
		List<Sentence> sentences = Sentence.convertTextToSentences(inputStrings);
		Sentence.tokenize(sentences);
		

		//Step 2 : Remove WhiteSpaceTokens
		for(int i =0; i<sentences.size();i++){
			Sentence sentence = Sentence.removeWhiteSpaceTokens(sentences.get(i));
			sentences.set(i, sentence);
		}
//		for(Sentence s : sentences) {
//			s.printTokens();
//		}
		//Step 3: Interpret validity of all sentences just based on operands
		Interpreter interpreter = new Interpreter();
		interpreter.evaluateString(sentences);		
		
		// Step 4: 
		
	}


}
