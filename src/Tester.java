import java.util.ArrayList;
import java.util.List;

import Sentence.Sentence;
import Token.Token;
import Token.Token.TokenType;
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
		for(Sentence s : sentences) {
			s.printTokens();
		}
		//Step 3: Interpret Tokens
		
	}


}
