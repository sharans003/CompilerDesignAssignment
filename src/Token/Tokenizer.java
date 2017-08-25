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

			String charAt = String.valueOf(tokenizer.lineofText.charAt(tokenizer.pos));
			//String charAt = tokenizer.lineofText.substring(tokenizer.pos, tokenizer.lineofText.length());
			TokenType tt;

			/*if(isDigit(charAt) == true) {
				tt = TokenType.INTEGER;

				Token token = new Token(tt, String.valueOf(tokenizer.lineofText.charAt(tokenizer.pos)), tokenizer.pos, tokenizer.pos);				

				try {
					while(tokenizer.pos + 1 < tokenizer.lineofText.length()) {
						tokenizer.pos = tokenizer.pos +1;
						String nextChar = String.valueOf(tokenizer.lineofText.charAt(tokenizer.pos));
						if(isDigit(nextChar)) {
							token.setEndIndex(tokenizer.pos);
							token.setTokenValue(tokenizer.lineofText.substring(token.getStartIndex(), token.getEndIndex()+1));
							if(tokenizer.pos +1 == tokenizer.lineofText.length()) tokenizer.pos +=1; // HACK
						} else {
							System.out.println("In break");
							break;
						}
					}
				} catch(Exception e) {
					System.out.println("In catch");
				}				
				tokenList.add(token);

			} else*/
			Token t = multipleDigit(tokenizer) ;
			if(t != null) tokenList.add(t);
			else{
				if(isPlus(charAt)) {

					tt = TokenType.PLUS;
					Token token = new Token(tt, String.valueOf(tokenizer.lineofText.charAt(tokenizer.pos)), tokenizer.pos, tokenizer.pos);				
					tokenList.add(token);
					tokenizer.pos +=1;

				} else if(isWS(charAt)) {
					tt = TokenType.WHITESPACE;
					Token token = new Token(tt, String.valueOf(tokenizer.lineofText.charAt(tokenizer.pos)), tokenizer.pos, tokenizer.pos);				
					tokenList.add(token);
					tokenizer.pos +=1;
				} else if(isPrint(charAt)) {
					tt = TokenType.PRINT;
					Token token = new Token(tt, String.valueOf(tokenizer.lineofText.charAt(tokenizer.pos)), tokenizer.pos, tokenizer.pos);					
					tokenList.add(token);
					tokenizer.pos +=1;
				} else {
					tt = TokenType.ERROR;
				}
			}

		}
		return tokenList;
	}

	private static Token multipleDigit(Tokenizer tokenizer) {		
		String ss = tokenizer.lineofText.substring(tokenizer.getPos(), tokenizer.lineofText.length());
		String pat = "^(\\d+)";
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(ss);
		if(m.find()) {
			System.out.println("Group  "+m.group());
			Token token = new Token(TokenType.INTEGER, m.group(), tokenizer.pos, tokenizer.pos+m.group().length()-1);
			tokenizer.setPos(tokenizer.pos+m.group().length());
			return token;
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
