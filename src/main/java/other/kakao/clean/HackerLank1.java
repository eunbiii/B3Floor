import java.util.HashMap;

public class HackerLank1 {
	
	/*어떤 투표의 득표자를 배열로 정리했습니다
	배열에서 가장 많이 득표한 이름을 고르시오
	득표 횟수가 같을 경우 알파벳 내림차순으로 최다득표자를 결정합니다
	예) lampard와 gerrard의 표가 동률일 때 알파벳 내림차순에 따라 lampard가 최다득표자*/
	
	public static void main(String[] args) {
		
		//name list
		String[] list = {
				"hazard",
				"kante",
				"cesar",
				"kante",
				"morata",
				"hazard",
				"willian",
				"pedro",
				"cesc",
				"kante"
		};
		
		String res = result(list);
		System.out.println(res);
	}

	public static String result(String[] list) {
		String name = "";
		int max = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (String s : list) {
			int val = 1;
			
			if(map.containsKey(s)) val += map.get(s);
			map.put(s, val);
		}
		
		for (String s : map.keySet()) {
			int val = map.get(s);
			
			if(val < max) continue;
			else if(val > max) {
				max = val;
				name = s;
			} else if(name.compareTo(s) < 0) {
				name = s;
			}
		}
		
		return name;
	}

}
