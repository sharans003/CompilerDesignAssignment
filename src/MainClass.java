import java.util.List;

import Interpreter.Interpreter;
import Sentence.Sentence;
import Utils.Utils;

public class MainClass {
	 /* If you prefer to read off a file provide the path here. If left empty a prompt will appear on the console.*/
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
		Sentence.tokenize(sentences); /* Implemented a tokenizer which tokenizes the entered sentence into custom tokens */
		

		//Step 2 : Remove WhiteSpaceTokens
		for(int i =0; i<sentences.size();i++){
			Sentence sentence = Sentence.removeWhiteSpaceTokens(sentences.get(i)); /* Done as a pre-processing step as this removes the need for rules for Whitespaces during evaluation */
			sentences.set(i, sentence);
		}

		//Step 3: Interpret validity of all sentences just based on operands
		Interpreter interpreter = new Interpreter();
		interpreter.evaluateString(sentences); // Evaluate the correctness of the interpreter					
	}


}
