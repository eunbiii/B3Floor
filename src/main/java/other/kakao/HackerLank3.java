
public class HackerLank3 {

	public static void main(String[] args) {
		
		//abbreviation list
		String[] list = {
				"1/2+3/4",
				"5/7+3/8",
				"11/11+12/13"
		};
		
		String[] res = result(list);
		for (String s : res) System.out.println(s);
	}

	public static String[] result(String[] list) {
		
		for (int i = 0; i < list.length; i++) {
			String[] abb = list[i].split("[+]");
			int[] num = new int[4];
			
			for (int j = 0; j < 2; j++) {
				String[] div = abb[j].split("[/]");
				num[2 * j] = Integer.parseInt(div[0]);
				num[2 * j + 1] = Integer.parseInt(div[1]);
			}
			
			int upper = num[0] * num[3] + num[1] * num[2];
			int downer = num[1] * num[3];
			
			int gcd = GCD(upper, downer);
			
			upper /= gcd;
			downer /= gcd;
			
			list[i] = upper + "/" + downer;
		}
		
		return list;
	}
	
	public static int GCD(int a, int b) {
		if(a == b) return a;
		
		int max = a > b ? a : b;
		int min = a > b ? b : a;
		int r;
		
		do {
			r = max % min;
			max = min;
			min = r;
		} while(r > 0);
		
		return max;
	}

}
