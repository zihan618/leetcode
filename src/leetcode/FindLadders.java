package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.transform.Templates;

public class FindLadders {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String beginWord = "hit";
		String endWord = "cog";
		String[] wordList = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println((new FindLadders()).findLadders(beginWord, endWord, Arrays.asList(wordList)).size());
	}

	class node{
		public String value;
		public node father;
		public HashSet<node> sons;
		public node() {
			father = null;
			sons = new HashSet<>();
		}
	}
	
	public  List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		//初始化矩阵
		boolean[][] table = new boolean[wordList.size()][wordList.size()];
		String[] strings = new String[wordList.size()];
		wordList.toArray(strings);

		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				table[i][j] = isClosed(strings[i], strings[j]);
			}
		}
		//字符串全部存入数据结构之内 
		ArrayList<Object> arrayList = new ArrayList<>();
		arrayList.addAll(Arrays.asList(strings));
		
		HashMap<String, Integer> hashMap = new HashMap<>();
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(beginWord);
		
		hashMap.put(beginWord, 0);
		boolean ischanged = false;
		System.out.println("2233");
		while (!linkedList.getFirst().equals(endWord)) {
			System.out.println("2233");
			for (int i = arrayList.indexOf(linkedList.pop()), j = 0; j < strings.length; j++) {
				System.out.println("11111");
				if (!hashMap.containsKey(strings[j].toString()) && table[i][j]) {
					hashMap.put(strings[j].toString(), hashMap.get(strings[i])+1);
					linkedList.push(strings[j].toString());
					ischanged = true;
				}
			}
			
			System.out.println("11112");
			if (!ischanged) {
				return null;
			}
		}
		
		
	}

	boolean isClosed(String string1, String string2) {
		int num = 0;
		for (int i = 0; i < string1.length(); i++) {
			if (string1.charAt(i) != string2.charAt(i)) {
				num++;
			}
		}
		return num == 1;
	}
}
