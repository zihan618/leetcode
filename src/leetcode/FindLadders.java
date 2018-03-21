package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.xml.transform.Templates;

public class FindLadders {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String beginWord = "a";
		String endWord = "c";
		String[] wordList = {"a","b","c" };
		System.out.println((new FindLadders()).findLadders(beginWord, endWord, Arrays.asList(wordList)));
	}

	

	/**
	 * 用队列找两个字符串的编辑距离
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		// 初始化矩阵
		ArrayList<List<String>> res = new ArrayList<>();
		if(!wordList.contains(endWord)) {
			return res;
		}
		HashSet<String> hashSet = new HashSet<>();
		for(int i=0; i<wordList.size(); i++) {
			hashSet.add(wordList.get(i));
		}
		hashSet.add(beginWord);
		hashSet.add(endWord);
		String[] strings = new String[hashSet.size()];
		hashSet.toArray(strings);
		boolean[][] matrix = new boolean[hashSet.size()][hashSet.size()];
		
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][i] = false;
			for (int j = i+1; j < matrix[i].length; j++) {
		//		System.out.println(strings[i] + strings[j]);
				boolean tmp = isClosed(strings[i], strings[j]);
				matrix[i][j] = tmp;
				matrix[j][i] = tmp;
			}
		}
		//记住字符串对应下标
		HashMap<String, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < strings.length; i++) {
			hashMap.put(strings[i], i);
		}		
		//使用队列 和一个辅助数组 找到最短距离
		boolean[] reached = new boolean[strings.length];
		LinkedList<Integer> queue = new LinkedList<>();
		int[] distance = new int[strings.length];
		
		queue.addLast(hashMap.get(beginWord));
		reached[hashMap.get(beginWord)] = true;
		distance[hashMap.get(beginWord)] = 0;
		
		while (!queue.isEmpty() && !queue.contains(hashMap.get(endWord))) {
			int index = queue.pop();
			for(int i=0; i<strings.length; i++) {
				if(matrix[index][i] && !reached[i]) {
					reached[i] = true;
					distance[i] = distance[index] + 1;
					queue.addLast(i);
				}
			}
		}
		
		if( distance[hashMap.get(endWord)] == 0) {
			return res;
		}
		int max_dist = distance[hashMap.get(endWord)];
		 
		Stack<String> stack = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		stack.push(beginWord);
		stack2.push(next(matrix, hashMap.get(beginWord), -1));
		String[] tmp = new String[max_dist + 1];
	
		while (!stack.isEmpty()) {
			
			if(stack2.peek() == strings.length || (stack.size() == max_dist+1 && !stack.peek().equals(endWord))) {
				stack.pop();
				stack2.pop();
				if(!stack.isEmpty()) {
					int now_pos = stack2.pop();
					now_pos = next(matrix, hashMap.get(stack.peek()), now_pos);
					while (now_pos < strings.length && stack.contains(strings[now_pos])) {
						now_pos = next(matrix, hashMap.get(stack.peek()), now_pos);
					}
					stack2.push(now_pos);
				}
				continue;
			}
			if(stack.peek().equals(endWord)) {
				stack.toArray(tmp);
				res.add( Arrays.asList(tmp.clone()));
			//	System.out.println(stack.toString());
				stack.pop();
				stack.pop();
				stack2.pop();
				stack2.pop();
				if(!stack.isEmpty()) {
					int now_pos = stack2.pop();
					now_pos = next(matrix, hashMap.get(stack.peek()), now_pos);
					while (now_pos < strings.length && stack.contains(strings[now_pos])) {
						now_pos = next(matrix, hashMap.get(stack.peek()), now_pos);
					}
					stack2.push(now_pos);
				}
				continue;
			}
			
			stack.push(strings[stack2.peek()]);
			stack2.push(next(matrix, hashMap.get(stack.peek()), -1));
		}
		return res;

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
	
	int next(boolean[][] matrix, int pre_index, int now_index) {
		int i = now_index+1;
		for(; i<matrix[0].length; i++) {
			if(matrix[pre_index][i] ) {
				break;
			}
		}
		return i;
		
	}
}
