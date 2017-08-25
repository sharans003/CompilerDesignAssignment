package Sentence;

import java.util.ArrayList;
import java.util.List;
import Token.Token;
import Token.Tokenizer;
import Token.Token.TokenType;

public class Sentence {

	public String lineOfText;
	public List<Token> tokenList;
	private int tokenPointer;

	public Sentence(String lineOfText) {
		this.lineOfText = lineOfText;
		this.tokenList = new ArrayList<>();
		this.setTokenPointer(0);
	}

	public String getLineOfText() {
		return lineOfText;
	}
	public void setLineOfText(String lineOfText) {
		this.lineOfText = lineOfText;
	}
	public List<Token> getTokenList() {
		return tokenList;
	}
	public void setTokenList(List<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public static List<Sentence> convertTextToSentences(List<String> inputStrings) {
		List<Sentence> sentenceList = new ArrayList<>();
		for(String s : inputStrings) {
			sentenceList.add(new Sentence(s));
		}
		return sentenceList;		
	}

	public static void tokenize(List<Sentence> sentences) {
		for(int j = 0; j < sentences.size(); j++) {
			Sentence sentence = sentences.get(j);
			List<Token> tokens = Tokenizer.tokenize(sentence);
			sentence.setTokenList(tokens);
			sentences.set(j, sentence);
		}
	}

	public void printTokens() {

		System.out.println("Line of text is:"+ this.getLineOfText());
		List<Token> tokenList = this.getTokenList();
		for(Token t : tokenList) {
			System.out.println("Text: "+t.getTokenValue() +", type: "+t.getTokenType().toString());
		}
		System.out.println("----------------------------------------");
	}

	public int getTokenPointer() {
		return tokenPointer;
	}

	public void setTokenPointer(int tokenPointer) {
		this.tokenPointer = tokenPointer;
	}


	public static Sentence removeWhiteSpaceTokens(Sentence sentence) {
		List<Token> tl = sentence.getTokenList();
		List<Integer> indexToPop = new ArrayList<>();
		for(int j = 0; j <tl.size(); j++) {
			if(tl.get(j).getTokenType() == TokenType.WHITESPACE) {
				indexToPop.add(j);
			}
		}
		for(int j = indexToPop.size()-1;j>=0;j--) {		
			tl.remove(tl.get(indexToPop.get(j)));
		}
		sentence.setTokenList(tl);
		return sentence;
	}
}
