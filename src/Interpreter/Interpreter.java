package Interpreter;

import Sentence.Sentence;
import Token.Token;
import Token.Token.TokenType;

public class Interpreter {

	public Sentence sentence;
	public int positionOfTraversal;
	//public Token currentToken; // What is this for?
	
	public Token getNextToken(){
		//String lineCopy = new String(sentence.getLineOfText());
		/*
		if(this.positionOfTraversal > sentence.getLineOfText().length() - 1) {
			return new Token(TokenType.EOF,"");
		} else {
			 
			String currentChar = Character.toString(this.sentence.getLineOfText().charAt(positionOfTraversal));
			TokenType t = this.getTokenType(currentChar);
			this.positionOfTraversal += 1;
			return new Token(t, currentChar);
			 
		}*/
		return null;
		
	}
	

}
