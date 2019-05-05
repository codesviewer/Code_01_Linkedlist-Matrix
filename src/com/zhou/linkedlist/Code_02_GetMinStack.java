package com.zhou.linkedlist;

import java.util.Stack;

public class Code_02_GetMinStack {
	
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	public Code_02_GetMinStack() {
	    this.stackData = new Stack<Integer>();
	    this.stackMin = new Stack<Integer>();
	}
	
	public void push(int newNum) {
	    if (this.stackMin.isEmpty()) {
	      this.stackMin.push(newNum);
	    } else if (newNum < this.getmin()) {
	      this.stackMin.push(newNum);
	    } else {
	      int newMin = this.stackMin.peek();
	      this.stackMin.push(newMin);
	    }
	    this.stackData.push(newNum);
	  }

	  public int pop() {
	    if (this.stackData.isEmpty()) {
	      throw new RuntimeException("Your stack is empty.");
	    }
	    this.stackMin.pop();
	    return this.stackData.pop();
	  }

	  public int getmin() {
	    if (this.stackMin.isEmpty()) {
	      throw new RuntimeException("Your stack is empty.");
	    }
	    return this.stackMin.peek();
	  }
	  
	  public static void main(String[] args) {

		Code_02_GetMinStack stack = new Code_02_GetMinStack();
	    stack.push(3);
	    System.out.println(stack.getmin());
	    System.out.println("====================================");
	    
	    stack.push(4);
	    System.out.println(stack.getmin());
	    System.out.println("====================================");
	    
	    stack.push(1);
	    System.out.println(stack.getmin());
	    System.out.println("====================================");
	    
	    System.out.println(stack.pop());
	    System.out.println("====================================");
	    
	    System.out.println(stack.pop());
	    System.out.println("====================================");
	    
	    System.out.println(stack.pop());
	    System.out.println("====================================");
	    
	  }
	
}
