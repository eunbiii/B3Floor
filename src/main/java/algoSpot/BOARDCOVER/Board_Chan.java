package algoSpot.BOARDCOVER;
import java.util.Scanner;

public class Board_Chan {
	
	public static boolean[][] map;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int num = scan.nextInt();

		while (num-- > 0) {
			int row = scan.nextInt() + 2;
			int col = scan.nextInt() + 2;

			map = new boolean[row][col];
			
			// 자유영역 계산, 만약 자유영역이 3의 배수가 아니면 기각
			int free = 0;

			for (int i = 0; i < row - 2; i++) {
				String str = scan.next();
				str = str.trim();

				for (int j = 0; j < col - 2; j++) {
					if (str.charAt(j) == '.') {
						map[i + 1][j + 1] = true;
						free++;
					}
				}
			}
			
			//결과
			int count = 0;

			if (free == 0 || free % 3 != 0) {
				count = 0;
			} else {
				count = insert(free);
			}
			
			System.out.println(count);
		}
		
		scan.close();
	}

	public static int insert(int free) {

		// 맨윗줄 맨왼쪽 구함
		int tx = 0; // targetX
		int ty = 0; // targetY
		
		Branch[] p = new Branch[4]; //중심을 기준으로 2개의 좌표 저장 배열
		
		getCenter:
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[i].length - 1; j++) {
				
				if (map[i][j]) {
					tx = j; // targetX
					ty = i; // targetY
					
					break getCenter;
				}
			}
		}
		
		p[0] = new Branch(ty + 1, tx, ty, tx + 1); //남, 동
		p[1] = new Branch(ty + 1, tx, ty + 1, tx + 1); //남, 남동
		p[2] = new Branch(ty, tx + 1, ty + 1, tx + 1); //동, 남동
		p[3] = new Branch(ty + 1, tx, ty + 1, tx - 1); //남, 남서
		
		// 만약 남은칸이 3이고, 채울 수 있는 형태이면, 1 반환
		if (free == 3) {
			
			for (Branch b : p) {
				if(map[b.y1][b.x1] && map[b.y2][b.x2]) return 1;
			}
			
			return 0;
		}
		
		int count = 0;
		
		for (Branch b : p) {
			if (map[b.y1][b.x1] && map[b.y2][b.x2]) {
				
				//지나갔다는 점 표시
				map[ty][tx] = false;
				map[b.y1][b.x1] = false;
				map[b.y2][b.x2] = false;
				
				//재귀호출
				count += insert(free - 3);
				
				//다음 계산을 위해 맵 복구
				map[ty][tx] = true;
				map[b.y1][b.x1] = true;
				map[b.y2][b.x2] = true;
			}
		}

		return count;
	}

}

class Branch {
	int x1, x2, y1, y2;
	
	public Branch(int y1, int x1, int y2, int x2) {
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x2;
		this.y2 = y2;
	}
}