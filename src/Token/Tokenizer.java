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
			TokenType tt;			
			Token t = tokenMatcher(tokenizer,TokenType.INTEGER) ;
			t = tokenMatcher(tokenizer,TokenType.PLUS) ;
			t = tokenMatcher(tokenizer,TokenType.WHITESPACE) ;
			t = tokenMatcher(tokenizer,TokenType.PRINT) ;
			if(t != null) {
				tokenList.add(t);
				//tt = TokenType.INTEGER;
			} else {
				tt = TokenType.ERROR;
				t = new Token(tt, "ERROR IN LEXER");
			}
			/*else {
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
			}*/

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
		case PRINT:
			pat = "^(print.*)";
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
				System.out.println("Group  "+m.group());
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
