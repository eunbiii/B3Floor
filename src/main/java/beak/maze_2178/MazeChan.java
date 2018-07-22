package beak.maze_2178;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeChan {

	static char[][] graph;
	static boolean[][] visited;
	static int n = 0, m = 0;
	static int[][] dir = { {1, 0}, {-1, 0},  {0, 1}, {0, -1} };
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		
		graph = new char[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			String nString = input.next();
			for (int j = 0; j < m; j++) {
				graph[i][j] = nString.charAt(j);
			}
		}
		
		input.close();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = false;
			}
		}
		
		find();
	}
	
	private static void find() {
		int roadCount = 0;
		boolean finish = false;
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { 0, 0 });
		
		while (!finish) {
			int qSize = q.size();
			
			for (int i = 0; i < qSize; i++) {
				int[] pos = q.poll();
				
				if (pos[0] == n - 1 && pos[1] == m - 1) finish = true;
				
				for (int j = 0; j < 4; j++) {
					int nr = pos[0] + dir[j][0];
					int nc = pos[1] + dir[j][1];
					
					if (nr >= n || nr < 0 || nc >= m || nc < 0 
							|| graph[nr][nc] == '0' || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
			
			roadCount++;
		}
		
		System.out.println(roadCount);
	}
}
