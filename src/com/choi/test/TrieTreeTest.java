package com.choi.test;

import javax.xml.crypto.Data;

import com.choi.tools.TrieTree;

public class TrieTreeTest {

	static TrieTree trieTree = new TrieTree();
	
	public static void exceptionTest(){
		// 插入null
		try {
			trieTree.insert(null);
		} catch (NullPointerException e) {
			System.out.println("通过");
		}
		// 插入非法字符
		trieTree.insert("abd@");
	}
	
	public static void concurrentTest() throws InterruptedException{
		final String[] data = {"string", "word", "world", "string", "word", "world", "app", "apply","application"};
		Runnable run = new Runnable() {
			@Override
			public void run() {
				int n = 9;
				for(int i=0; i<n; i++){
					trieTree.insert(data[i]);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread[] threads = new Thread[3];
		for(int i=0; i<3; i++){
			threads[i] = new Thread(run);
			threads[i].start();
		}
		for(int i=0; i<3; i++){
			threads[i].join();
		}
		for(int i=0; i<9; i++){
			System.out.println(data[i]+": "+trieTree.findWord(data[i]));
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		TrieTreeTest.concurrentTest();
	}
}
