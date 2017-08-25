package Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Sentence.Sentence;
import Token.Token.TokenType;

public class Tokenizer {
	public String lineofText;
	public int pos;
	public int startIndex;
	public int endIndex;

	public Tokenizer(String lineOfText) {
		this.lineofText = lineOfText;
		this.startIndex = 0;
		this.endIndex = 0;
	}
	public int getPos() {
		return this.pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public static List<Token>  tokenize(Sentence sentence) {

		Tokenizer tokenizer = new Tokenizer(sentence.getLineOfText());
		List<Token> tokenList = new ArrayList<>();

		while(tokenizer.pos < tokenizer.lineofText.length()) {
			
			Token t = tokenMatcher(tokenizer,TokenType.INTEGER) ;
			if(t != null) tokenList.add(t);
			t = tokenMatcher(tokenizer,TokenType.MULTIPLY) ;
			if(t != null) tokenList.add(t);
			t = tokenMatcher(tokenizer,TokenType.DIVIDE) ;
			if(t != null) tokenList.add(t);
			t = tokenMatcher(tokenizer,TokenType.MINUS) ;
			if(t != null) tokenList.add(t);
			t = tokenMatcher(tokenizer,TokenType.PLUS) ;
			if(t != null) tokenList.add(t);
			t = tokenMatcher(tokenizer,TokenType.EQUALS) ;
			if(t != null) tokenList.add(t);
			t = tokenMatcher(tokenizer,TokenType.WHITESPACE) ;
			if(t != null) tokenList.add(t);
			t = tokenMatcher(tokenizer,TokenType.PRINT) ;
			if(t != null) tokenList.add(t);

		}
		return tokenList;
	}

	private static Token tokenMatcher(Tokenizer tokenizer, TokenType tt) {		
		String ss = tokenizer.lineofText.substring(tokenizer.getPos(), tokenizer.lineofText.length());
		String pat ;
		switch(tt) {
		case INTEGER:
			pat = "^(\\d+)";
			break;
		case PLUS:
			pat = "^(\\+)";
			break;
		case MINUS:
			pat = "^(\\-)";
			break;
		case DIVIDE:
			pat = "^(\\/)";
			break;
		case MULTIPLY:
			pat = "^(\\*)";
			break;
		case PRINT:
			pat = "^(print.+)";
			break;
		case WHITESPACE:
			pat = "^(\\s+)";
			break;			
		default:
			pat = null;

		}
		if(pat != null) {
			Pattern p = Pattern.compile(pat);
			Matcher m = p.matcher(ss);
			if(m.find()) {
				//System.out.println("Group  "+m.group());
				Token token = new Token(tt, m.group(), tokenizer.pos, tokenizer.pos+m.group().length()-1);
				tokenizer.setPos(tokenizer.pos+m.group().length());
				return token;
			} 
		}
		return null;
	}

	public static boolean isPrint(String str) {
		// Needs to change
		return str.matches("print");
	}

	public static boolean isPlus(String str) {
		return str.matches("\\+");
	}
	public static boolean isWS(String str) {
		return str.matches("\\s+");
	}



}
