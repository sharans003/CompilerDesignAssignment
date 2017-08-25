package Interpreter;

import java.util.ArrayList;
import java.util.List;

import Sentence.Sentence;
import Token.Token;

public class Interpreter {

	public List<Token> operandStack;
	public List<Token> operatorStack;

	public void parse(List<Sentence> sentences) {
		for(Sentence s : sentences) {
			this.operandStack = new ArrayList<>();
			this.operatorStack = new ArrayList<>();
			
			List<Token> tokenList = s.getTokenList();
			for(Token t:tokenList) {
				if(t.getTokenType().isOperator()) {
					
				} else if(t.getTokenType().isOperand()) {
					
				}
			}
		}
	}


}
