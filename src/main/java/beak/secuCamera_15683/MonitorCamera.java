import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MonitorCamera {
	
	static int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int[][] graph;
	static ArrayList<Camera> camera;
	static int res;
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		camera = new ArrayList<Camera>();
		res = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				graph[i][j] = sc.nextInt();
				if(graph[i][j] > 0 && graph[i][j] < 6) {
					Camera c = new Camera(i, j, graph[i][j]);
					camera.add(c);
				}
			}
		}
		
		sc.close();
		dfs();
		System.out.println(res);
	}
	
	public static void dfs() {
		
		if(camera.isEmpty()) {
			int size = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(graph[i][j] == 0) size++;
				}
			}
			
			res = Math.min(res, size);
			return;
		}
		
		Camera cam = camera.remove(0);
		boolean[] detect = new boolean[4];
		int[][] backup = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			System.arraycopy(graph[i], 0, backup[i], 0, M);
		}
		
		switch(cam.type) {
			case 1:
				for (int i = 0; i < 4; i++) {
					detect[i] = true;
					
					monitoring(detect, cam.r, cam.c);
					dfs();
					rollBack(backup);
					
					detect[i] = false;
				}
				
				break;
			case 2:
				for (int i = 0; i < 2; i++) {
					detect[i] = detect[i + 2] = true;
					
					monitoring(detect, cam.r, cam.c);
					dfs();
					rollBack(backup);
					
					detect[i] = detect[i + 2] = false;
				}
				
				break;
			case 3:
				for (int i = 0; i < 4; i++) {
					detect[i] = detect[(i + 1) % 4] = true;
					
					monitoring(detect, cam.r, cam.c);
					dfs();
					rollBack(backup);
					
					detect[i] = detect[(i + 1) % 4] = false;
				}
				
				break;
			case 4:
				Arrays.fill(detect, true);
				
				for (int i = 0; i < 4; i++) {
					detect[i] = false;
					
					monitoring(detect, cam.r, cam.c);
					dfs();
					rollBack(backup);
					
					detect[i] = true;
				}
				
				break;
			case 5:
				Arrays.fill(detect, true);
				
				monitoring(detect, cam.r, cam.c);
				dfs();
				rollBack(backup);
				
				break;
		}
		
		camera.add(0, cam);
	}
	
	public static void monitoring(boolean[] detect, int r, int c) {
		
		for (int i = 0; i < detect.length; i++) {
			if(!detect[i]) continue;
			int nr = r, nc = c;
			
			while(true) {
				nr += dir[i][0];
				nc += dir[i][1];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || graph[nr][nc] == 6) break;
				else if(graph[nr][nc] == 0) graph[nr][nc] = -1;
			}
		}
	}
	
	public static void rollBack(int[][] backup) {
		for (int i = 0; i < N; i++) {
			System.arraycopy(backup[i], 0, graph[i], 0, M);
		}
	}
	
	public static class Camera {
		int r, c;
		int type;
		
		public Camera(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
}
