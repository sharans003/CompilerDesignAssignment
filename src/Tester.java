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
		
		for(Sentence s : sentences) {
			s.printTokens();
		}
		//Step 2 : Remove WhiteSpaceTokens
		removeWhiteSpaceTokens(sentences);
		
		//Step 3: Interpret Tokens
		
	}

	private static void removeWhiteSpaceTokens(List<Sentence> sentences) {
		for(int i = 0; i < sentences.size(); i++) {
			Sentence sentence = sentences.get(i);
			 List<Token> tl = sentence.getTokenList();
			 List<Integer> indexToPop = new ArrayList<>();
			 for(int j = 0; j <tl.size(); j++) {
				 if(tl.get(j).getTokenType() == TokenType.WHITESPACE) {
					 indexToPop.add(j);
				 }
			 }
			 for(int j = indexToPop.size()-1;j>=0;j--) {
				 tl.remove(indexToPop.get(j));
			 }
			sentence.setTokenList(tl);
			sentences.set(i, sentence);
		}
		
	}
}
