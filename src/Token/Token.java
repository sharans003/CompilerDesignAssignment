package Token;

import Token.Token.TokenType;

public class Token {

	public enum TokenType {
		INTEGER, 
		PLUS, 
		PRINT,
		EOF,
		ERROR,
		WHITESPACE;
	}
	
	private String value;
	private TokenType tokenType;
	
	public Token(TokenType t, String value) {
		this.value = value;
		this.tokenType = t; 
	}
	
	public TokenType getTokenType() {
		return this.tokenType;
	}
	public void setTokenType(TokenType t) {
		this.tokenType = t;
	}
	public String getTokenValue() {
		return this.value;
	}
	public void setTokenValue() {
		this.value = value;
	}
	
	public static TokenType getTokenType(String line) {
		if(isNumeric(line)) {
			return TokenType.INTEGER;
		} else if(isPlus(line)) {
			return TokenType.PLUS;
		} else if (isPrint(line)) {
			return TokenType.PRINT;
		} else if(isWS(line)) {
			return TokenType.WHITESPACE;
		} else {
			return TokenType.ERROR;
		}
	}
	
	public static boolean isNumeric(String str) {
	  return str.matches("\\d+(\\.\\d+)?");  
	}
	
	public static boolean isPrint(String str) {
		return str.matches("print");
	}
	
	public static boolean isPlus(String str) {
		return str.matches("\\+");
	}
	public static boolean isWS(String str) {
		return str.matches("\\s+");
	}
}
