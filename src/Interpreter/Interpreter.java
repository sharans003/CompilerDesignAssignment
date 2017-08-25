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
			System.out.println("Sentence is: "+s.getLineOfText());
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
				Token tok1 = operandStack.remove(0);
				Token tok2 = operandStack.remove(0);
				String  num1 = null;
				String num2 = null;
				if(tok1.getTokenType().equals(TokenType.VARIABLE) || tok2.getTokenType().equals(TokenType.VARIABLE)) {


					try {
						Integer.parseInt(tok1.getTokenValue());
						num1 = tok1.getTokenValue();
					} catch(Exception e) {
						if(variableMap.containsKey(tok1.getTokenValue())) {
							// variable was already defined.
							num1 = variableMap.get(tok1.getTokenValue());
						} else {
							// error
							System.out.println("Variable1 not defined!");
						}
					}
					try {
						Integer.parseInt(tok2.getTokenValue());
						num2 = tok2.getTokenValue();
					} catch(Exception e) {
						if(variableMap.containsKey(tok2.getTokenValue())) {
							// variable was already defined.
							num2 = variableMap.get(tok2.getTokenValue());
						} else {
							// error
							System.out.println("Variable2 not defined!");
						}
					}
				}
				int result;
				Token tokenResult;
				switch(op.getTokenValue().trim()){
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
					variableMap.put(tok2.getTokenValue(), tok1.getTokenValue());
					break;

				}

			}

		}
		if(operandStack.size() ==1)
			System.out.println("result is:"+operandStack.remove(0).getTokenValue());
		else {
			System.out.println("Something went wrong in arithmetic parsing");				
		}
	}


}
