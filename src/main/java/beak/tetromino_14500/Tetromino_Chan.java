package beak.tetromino_14500;


import java.util.Scanner;


public class Tetromino_Chan {

	static int width, height;
	
	static int[][] d = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
	
	static int[][] graph;
	static boolean[][] visit;
	
	static int max = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		width = sc.nextInt();
		height = sc.nextInt();
		
		graph = new int[width][height];
		visit = new boolean[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				graph[i][j] = sc.nextInt();
			}
		}
		
		sc.close();
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				dfs(1, i, j, 0);
				otherShapeCheck(i, j);
			}
		}
		
		System.out.println(max);
	}

	public static void dfs(int n, int r, int c, int sum) {
		
		if (n == 4) {
			max = Math.max(max, sum + graph[r][c]);
			return;
		}
		
		visit[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			
			if (nr >= 0 && nr < width && nc >= 0 && nc < height && !visit[nr][nc]) {
				dfs(n + 1, nr, nc, sum + graph[r][c]);
			}
		}
		
		visit[r][c] = false;
	}

	public static void otherShapeCheck(int r, int c) {
		
		int status = 0;
		
		if(r == width - 1 || r == 0) status++;
		if(c == height - 1 || c == 0) status++;
		
		if(status == 2) return;
		
		int sum = graph[r][c];
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			
			if (nr >= 0 && nr < width && nc >= 0 && nc < height) {
				sum += graph[nr][nc];
				min = Math.min(min, graph[nr][nc]);
			}
		}
		
		max = Math.max(max, status == 0 ? sum - min : sum);
	}
	
}
