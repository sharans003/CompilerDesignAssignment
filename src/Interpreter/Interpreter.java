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
	public void parse(List<Sentence> sentences) {
		for(Sentence s : sentences) {
			this.operandStack = new ArrayList<>();
			this.operatorStack = new ArrayList<>();

			List<Token> tokenList = s.getTokenList();
			for(Token t:tokenList) {
				if(t.getTokenType().isOperator()) {
					System.out.println(t.getTokenValue());
					this.operatorStack.add(0, t);
				} else if(t.getTokenType().isOperand()) {
					System.out.println(t.getTokenValue());
					this.operandStack.add(0, t);
				}
			}

			while(operatorStack.size() > 0) {

				Token op = operatorStack.remove(0);
				Token no1 = operandStack.remove(0);
				Token no2 = operandStack.remove(0);
				int result = -666;
				Token tokenResult;
				switch(op.getTokenValue().trim()){
				case "+":
					result = Integer.parseInt(no1.getTokenValue()) + Integer.parseInt(no2.getTokenValue());
					tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
					operandStack.add(0, tokenResult);
					break;
				case "-":
					result = Integer.parseInt(no2.getTokenValue()) - Integer.parseInt(no1.getTokenValue());
					tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
					operandStack.add(0, tokenResult);
					break;
				case "*":
					result = Integer.parseInt(no1.getTokenValue()) * Integer.parseInt(no2.getTokenValue());
					tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
					operandStack.add(0, tokenResult);
					break;
				case "/":
					result = Integer.parseInt(no2.getTokenValue()) / Integer.parseInt(no1.getTokenValue());
					tokenResult = new Token(TokenType.INTEGER, String.valueOf(result));
					operandStack.add(0, tokenResult);
					break;
				case "=":
					variableMap.put(no2.getTokenValue(), no1.getTokenValue());
					break;

				}

			}
			if(operandStack.size() ==1)
				System.out.println("result is:"+operandStack.remove(0).getTokenValue());
			else {
				System.out.println("Something went wrong in arithmetic parsing");				
			}
		}
	}


}
