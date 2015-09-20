package com.choi.tools;

public class TrieTree {

	final Trie root = new Trie();

	synchronized public void insert(String s) throws NullPointerException {
		if (s == null) {
			throw new NullPointerException();
		}
		int L = s.length();
		Trie p = root;
		for (int i = 0; i < L; i++) {
			int id = getId(s.charAt(i));
			if(id==-1){
				System.out.println("插入s时，有非法字符");
				return ;
			}
			if (p.next[id] == null) {
				p.next[id] = new Trie();
			}
			p.next[id].preCount++;
			p = p.next[id];
		}
		p.wordCount++;
	}

	public int findWord(String s){
		if(s==null){
			return 0;
		}
		int L = s.length();
		Trie p = root;
		int i = 0;
		for(; i<L; i++){
			int id = getId(s.charAt(i));
			if(id==-1){
				return 0;
			}
			if(p.next[id]==null)
				break;
			p = p.next[id];
		}
		if(i==L)
			return p.wordCount;
		else 
			return 0;
	}
	
	public int findPrefix(String s){
		if(s==null){
			return 0;
		}
		int L = s.length();
		Trie p = root;
		int i = 0;
		for(; i<L; i++){
			int id = getId(s.charAt(i));
			if(p.next[id]==null)
				break;
			p = p.next[id];
		}
		return p.preCount;
	}
	
	private int getId(char c){
		int id = 0;
		if (c <= 'z' && c >= 'a') {
			id = c - 'a';
		} else if (c <= 'Z' && c >= 'A') {
			id = c - 'A';
		} else {
			return -1;
		}
		return id;
	}
	
	class Trie {
		int preCount = 0;
		int wordCount = 0;
		Trie[] next = new Trie[52];

		public Trie() {
			for (int i = 0; i < 52; i++) {
				next[i] = null;
			}
		}
	}
}
