package Interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Sentence.Sentence;
import Token.Token;
import Token.Token.TokenType;

public class Interpreter {

	public List<Token> operandStack;
	public List<Token> operatorStack;
	public HashMap<String,String> variableMap = new HashMap<String,String>(); 

	public Interpreter() {
		this.operandStack = new ArrayList<>();
		this.operatorStack = new ArrayList<>();
	}

	public Token evaluateString(List<Sentence> sentences) throws Exception {
		for(Sentence s: sentences) {
			this.operandStack = new ArrayList<>();
			this.operatorStack = new ArrayList<>();
			boolean isPrint = false;
			List<Token> tokenList = s.getTokenList();
			for(Token t: tokenList) {
				if(t.getTokenType().equals(TokenType.PRINT)) isPrint = true;
				//System.out.println("CurrentToken is: "+t.getTokenValue());
				if(t.getTokenType().equals(TokenType.WHITESPACE)) {
					continue;
				}
				
				if(t.getTokenType().equals(TokenType.INTEGER)) {
					operandStack.add(0, t);
				} else if(t.getTokenType().equals(TokenType.VARIABLE)) {
					if(variableMap.containsKey(t.getTokenValue())) {
					Token var = new Token(TokenType.INTEGER, variableMap.get(t.getTokenValue()));
					operandStack.add(0, var);
					} else {
						operandStack.add(0,t);
					}
					
				} else if(t.getTokenType().equals(TokenType.OPENBRACE)) {
					operatorStack.add(0,t);
				} else if(t.getTokenType().equals(TokenType.CLOSEBRACE)) {
					
					while(!operatorStack.get(0).getTokenType().equals(TokenType.OPENBRACE) && !operatorStack.isEmpty()) {
						
						Token tok1 = operandStack.remove(0);
						Token tok2 = operandStack.remove(0);
						Token operator = operatorStack.remove(0);
						Token result = applyOperator(tok1,tok2,operator);
						
					}
					if(operatorStack.get(0).getTokenType().equals(TokenType.OPENBRACE) && !operatorStack.isEmpty()) operatorStack.remove(0);
				} else if(t.getTokenType().isOperator()) {					
					while(!operatorStack.isEmpty() && hasPrecedence(t,operatorStack.get(0))) {
						Token tok1 = operandStack.remove(0);
						Token tok2 = operandStack.remove(0);
						Token operator = operatorStack.remove(0);
						Token result = applyOperator(tok1,tok2,operator);
						if(result != null) {
							operandStack.add(0,result);
						}
					}
					operatorStack.add(0,t);
				}
				
			}
			while(!operatorStack.isEmpty()) {
				Token operator = operatorStack.remove(0);
				Token tok1 = operandStack.remove(0);
				Token tok2 = operandStack.remove(0);
				
				Token result = applyOperator(tok1,tok2,operator);
				if(result != null) {
					operandStack.add(0,result);
				}
			}
			if(isPrint)
			System.out.println("Print Statement...  "+operandStack.get(0).getTokenValue());
			
		}
		return operandStack.remove(0);
	}
	private boolean hasPrecedence(Token t1, Token t2) {
		if(t2.getTokenType().isCloseBrace() || t2.getTokenType().isOpenBrace()) return false;
		if(
				(t1.getTokenType().equals(TokenType.MULTIPLY) || t1.getTokenType().equals(TokenType.DIVIDE)) &&
				(t2.getTokenType().equals(TokenType.PLUS) || t2.getTokenType().equals(TokenType.MINUS))
		  ) {
			return false;
		} 
		return true;
	}

	private Token applyOperator(Token tok1, Token tok2, Token operator) throws Exception {
		Token tokenResult = null;
		int result;
		String num1 = tok1.getTokenValue();
		String num2 = tok2.getTokenValue();
		switch(operator.getTokenValue().trim()){
		case "+":
			//result = Integer.parseInt(tok1.getTokenValue()) + Integer.parseInt(tok2.getTokenValue());
			result = Integer.parseInt(num1) + Integer.parseInt(num2);
			tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
			operandStack.add(0, tokenResult);
			break;
		case "-":
			//result = Integer.parseInt(tok2.getTokenValue()) - Integer.parseInt(tok1.getTokenValue());
			result = Integer.parseInt(num2) - Integer.parseInt(num1);
			tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
			operandStack.add(0, tokenResult);
			break;
		case "*":
			//result = Integer.parseInt(tok1.getTokenValue()) * Integer.parseInt(tok2.getTokenValue());
			result = Integer.parseInt(num1) * Integer.parseInt(num2);
			tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
			operandStack.add(0, tokenResult);
			break;
		case "/":
			//result = Integer.parseInt(tok2.getTokenValue()) / Integer.parseInt(tok1.getTokenValue());
			result = Integer.parseInt(num2) / Integer.parseInt(num1);
			tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
			operandStack.add(0, tokenResult);
			break;
		case "=":
			checkAssignment(tok1,tok2);
			variableMap.put(tok2.getTokenValue(), tok1.getTokenValue());
			Token varVal = new Token(TokenType.INTEGER, tok1.getTokenValue());
			operandStack.add(0, varVal);
			break;

		}

		return tokenResult;
	}

	private void checkAssignment(Token tok1, Token tok2) throws Exception{
		// correct thing is : token1 is a number, token 2 is a variable
		if(tok1.getTokenType().isVariable() && tok2.getTokenType().isInteger()) {
			throw new Exception();
		}
		if(tok1.getTokenType().isInteger() && tok2.getTokenType().isInteger()) {
			throw new Exception();
		}
		if(tok1.getTokenType().isVariable() && tok2.getTokenType().isVariable()) {
			if(!variableMap.containsKey(tok1.getTokenValue())) {
				throw new Exception();
			}
		}
	}

}
