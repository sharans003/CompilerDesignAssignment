package Token;

public class Token {

	public enum TokenType {
		INTEGER, 
		PLUS,
		MINUS,
		MULTIPLY,
		DIVIDE,
		EQUALS,
		PRINT,		
		ERROR,
		WHITESPACE,
		VARIABLE,
		OPENBRACE,
		CLOSEBRACE;

		public boolean isOperator() {
			if(this.equals(MINUS) || this.equals(DIVIDE) || this.equals(PLUS) || this.equals(MULTIPLY) || this.equals(EQUALS)) {
				return true;
			}
			return false;
		}

		public boolean isOperand() {
			if(this.equals(INTEGER) || this.equals(VARIABLE)) {
				return true;
			}
			return false;
		}
		public boolean isOpenBrace() {
			if(this.equals(OPENBRACE)) return true;
			return false;
		}
		public boolean isCloseBrace() {
			if(this.equals(CLOSEBRACE)) return true;
			return false;
		}

		public boolean isVariable() {
			if(this.equals(VARIABLE)) return true;
			return false;
		}

		public boolean isInteger() {
			if(this.equals(INTEGER)) return true;
			return false;
		}
	}
	
	private String value;
	private TokenType tokenType;
	private int startIndex;
	private int endIndex;

	
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
