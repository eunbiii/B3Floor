package algoSpot.COUNTPALIN;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.InputStream;


public class Palindrome_Chan {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);

		COUNTPALIN solver = new COUNTPALIN();

		int T = in.nextInt();
		while(T-- > 0) {
			solver.solve(in, out);
		}
		
		out.close();
	}
}

class COUNTPALIN {

	public void solve(InputReader in, PrintWriter out) {
		
		int L = in.nextInt();
		String s = in.next();
		
		char[] str = addBoundaries(s);
		
		L = str.length;
		int[] a = new int[L];
		
		int p = 0, R = 0, l = 0, r = 0;
		for (int i = 1; i < L; i++) {
			if (i >= R) {
				l = i - 1;
				r = i + 1;
			} else {
				int i2 = 2 * p - i;
				
				if (a[i2] < R - i) {
					a[i] = a[i2];
					l = -1;
				} else {
					a[i] = R - i;
					l = i - a[i] - 1;
					r = i + a[i] + 1;
				}
			}
			
			while (l >= 0 && r < L && str[l] == str[r]) {
				a[i]++;
				l--;
				r++;
			}
			
			if (i + a[i] > R) {
				R = i + a[i];
				p = i;
			}
		}
		
		long ans = 0;
		for (int i = 0; i < L; i++) {
			if(i % 2 == 1) a[i]++;
			ans += a[i] / 2;
		}
		
		out.println(ans);
	}

	private char[] addBoundaries(String s) {
		char[] convert = new char[s.length() * 2 - 1];
		
		convert[0] = s.charAt(0);
		
		for (int i = 1; i < s.length(); i++) {
			convert[2 * i - 1] = '|';
			convert[2 * i] = s.charAt(i);
		}
		
		return convert;
	}
}

class InputReader {

	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}
