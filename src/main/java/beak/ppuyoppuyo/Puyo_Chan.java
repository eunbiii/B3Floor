package beak.ppuyoppuyo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Puyo_Chan {
	
	static char[][] graph = new char[12][6];
	static boolean[][] visit = new boolean[12][6];
	static int[][] d = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		br.close();
		Queue<Position> q = new LinkedList<Position>();
		
		while(true) {
			
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(graph[i][j] != '.' && !visit[i][j]) {
						visit[i][j] = true;
						q.addAll( bfs(graph[i][j], new Position(i, j)) );
					}
				}
			}
			
			if(q.isEmpty()) break;
			else {
				result++;
				clearPuyo(q);
				q.clear();
			}
		}
		
		System.out.println(result);
	}
	
	public static void clearPuyo(Queue<Position> q) {
		
		for (Position p : q) {
			for (int i = p.r; i > 0; i--) {
				graph[i][p.c] = graph[i - 1][p.c];
			}
			
			graph[0][p.c] = '.';
		}
		
		for (int i = 0; i < 12; i++) {
			Arrays.fill(visit[i], false);
		}
	}
	
	public static Queue<Position> bfs(char ch, Position pos) {
		Queue<Position> q = new LinkedList<Position>();
		Queue<Position> saveQ = new LinkedList<Position>();
		
		q.add(pos);
		saveQ.add(pos);
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Position p = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nr = p.r + d[j][0];
					int nc = p.c + d[j][1];
					
					if(nr < 0 || nr >= 12 || nc < 0 || nc >= 6 
							|| visit[nr][nc] || graph[nr][nc] != ch) continue;
					
					visit[nr][nc] = true;
					
					q.add(new Position(nr, nc));
					saveQ.add(new Position(nr, nc));
				}
			}
		}
		
		if(saveQ.size() < 4) saveQ.clear();
		
		return saveQ;
	}
	
	static class Position {
		int r, c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
