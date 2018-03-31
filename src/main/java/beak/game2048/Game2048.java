import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Game2048 {

	static int max = 0;
	static int n;
	static int[][] graph;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		graph = new int[n][n];
		int[][] original = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				graph[i][j] = sc.nextInt();
				original[i][j] = graph[i][j];
			}
		}
		
		sc.close();
		
		moving(5, original);
		
		System.out.println(max);
	}
	
	public static void moving(int move, int[][] original) {
		if(move == 0) {
			return;
		}
		
		move_row(true, move);
		move_row(false, move);
		move_col(true, move);
		move_col(false, move);
	}
	
	public static void move_row(boolean reverse, int move) {
		
		int[][] original = new int[n][n];
		
		backupGraph(original);
		
		for (int i = 0; i < n; i++) {
			
			ArrayList<Integer> q = new ArrayList<Integer>();

			for (int j = 0; j < n; j++) {
				if (graph[i][j] > 0) q.add(graph[i][j]);
			}
			
			compact(q, reverse);
			
			for (int j = 0; j < q.size(); j++) {
				graph[i][j] = q.get(j);
				max = Math.max(graph[i][j], max);
			}
		}
		
		moving(move - 1, original);
		rollbackGraph(original);
	}
	
	public static void move_col(boolean reverse, int move) {
		
		int[][] original = new int[n][n];
		
		backupGraph(original);
		
		for (int i = 0; i < n; i++) {
			
			ArrayList<Integer> q = new ArrayList<Integer>();

			for (int j = 0; j < n; j++) {
				if (graph[j][i] > 0) q.add(graph[j][i]);
			}
			
			compact(q, reverse);
			
			for (int j = 0; j < q.size(); j++) {
				graph[j][i] = q.get(j);
				max = Math.max(graph[j][i], max);
			}
		}
		
		moving(move - 1, original);
		rollbackGraph(original);
	}
	
	public static void compact(ArrayList<Integer> q, boolean reverse) {
		
		if(reverse) Collections.reverse(q);
		
		for (int j = 0; j < q.size() - 1; j++) {
			
			int p = q.get(j);
			
			if(p == q.get(j + 1)) {
				q.set(j, p * 2);
				q.remove(j + 1);
			}
			
		}
		
		while(q.size() < n) {
			q.add(0);
		}
		
		if(reverse) Collections.reverse(q);
	}
	
	public static void rollbackGraph(int[][] original) {

		for (int i = 0; i < n; i++) {
			graph[i] = Arrays.copyOf(original[i], n);
		}
	}
	
	public static void backupGraph(int[][] original) {
		
		for (int i = 0; i < n; i++) {
			original[i] = Arrays.copyOf(graph[i], n);
		}
	}
}
