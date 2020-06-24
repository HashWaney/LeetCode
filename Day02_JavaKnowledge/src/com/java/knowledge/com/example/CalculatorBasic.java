package com.java.knowledge.com.example;


import com.java.knowledge.classloader.ICalculator;

public class CalculatorBasic implements ICalculator {

	public String calculate(String expression) {
		return expression;
	}

	public String getVersion() {
		return "1.0";
	}

}
