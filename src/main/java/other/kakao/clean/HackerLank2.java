
public class HackerLank2 {

		
	/*24비트 RGB컬러가 2진수로 주어졌을 때
	Red/Green/Blue/Black/White 중 거리가 가장 가까운 색을 구하시오
	*/
	
	public static void main(String[] args) {
		
		//color list
		String[] list = {
				"111111111111111111100111",
				"000000001111111111111111",
				"000000001110010110111111"
		};
		
		String[] res = result(list);
		for (String s : res) System.out.println(s);
	}

	public static String[] result(String[] list) {
		Color[] color = {
				new Color(0, 0, 0, "Black"), 
				new Color(255, 255, 255, "White"),
				new Color(255, 0, 0, "Red"), 
				new Color(0, 255, 0, "Green"), 
				new Color(0, 0, 255, "Blue")
		};

		for (int i = 0; i < list.length; i++) {
			double min = Integer.MAX_VALUE;
			String cur = "";
			
			int[] val = new int[3];

			for (int j = 0; j < 3; j++) {
				String str = list[i].substring(j * 8, j * 8 + 8);
				val[j] = Integer.parseInt(str, 2);
			}

			for (Color c : color) {
				double d = c.dist(val);

				if (min == d) {
					cur = "Ambigious";
				} else if (min > d) {
					min = d;
					cur = c.getName();
				}
			}

			list[i] = cur;
		}
		
		return list;
	}

	static class Color {
		int[] c;
		String name;

		public Color(int r, int g, int b, String name) {
			int[] c = { r, g, b };
			this.c = c;
			this.name = name;
		}

		public double dist(int[] val) {
			double d = 0.0;

			for (int i = 0; i < c.length; i++) {
				d += Math.pow(val[i] - c[i], 2);
			}

			return Math.sqrt(d);
		}

		public String getName() {
			return name;
		}
	}

}
