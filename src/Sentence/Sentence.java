package Sentence;

import java.util.ArrayList;
import java.util.List;

import Token.Token;
import Token.Token.TokenType;

public class Sentence {

	public String lineOfText;
	public List<Token> tokenList;

	public Sentence(String lineOfText) {
		this.lineOfText = lineOfText;
		this.tokenList = new ArrayList<>();
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
		for(int j = 0; j<sentences.size(); j++) {
			Sentence sentence = sentences.get(j);
			
			for(int i = 0; i< sentence.getLineOfText().length(); i++) {
				String charAtIndex = sentence.getLineOfText().substring(i,i+1);
				TokenType type = Token.getTokenType(charAtIndex);
				Token t = new Token(type, charAtIndex);
				sentences.get(j).getTokenList().add(t);
			}
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
}
