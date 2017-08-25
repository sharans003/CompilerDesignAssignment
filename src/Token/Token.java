package Token;

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
	private int startIndex;
	private int endIndex;
	//private int currentPos;
	
	public Token(TokenType t, String value) {
		this.value = value;
		this.tokenType = t; 
	}
	public Token(TokenType t, String value, int start, int end) {
		this.value = value;
		this.tokenType = t; 
		this.startIndex = start;
		this.endIndex = end;
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
	public void setTokenValue(String value) {
		this.value = value;
	}			

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
