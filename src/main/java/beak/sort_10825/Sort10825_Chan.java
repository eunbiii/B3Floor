import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sort10825_Chan {
	
	public static void main(String[] args) {
		Main m = new Main();
		m.init();
	}
	
	public void init() {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Queue<Student> q = new LinkedList<Student>();
		
		while(N-- > 0) {
			String name = sc.next();
			int korean = sc.nextInt();
			int english = sc.nextInt();
			int mathematics = sc.nextInt();
			
			Student p = new Student(name, korean, english, mathematics);
			
			q.offer(p);
		}
		
		sc.close();
		
		divide(q);
		
		for (Student p : q) {
			System.out.println(p.getName());
		}
	}
	
	public void divide(Queue<Student> q) {
		if(q.size() <= 1) return;
		
		int mid = q.size() / 2;
		
		Queue<Student> left = new LinkedList<Student>();
		Queue<Student> right = new LinkedList<Student>();
		
		while(mid-- > 0) left.offer(q.poll());
		while(!q.isEmpty()) right.offer(q.poll());
		
		divide(left);
		divide(right);
		merge(q, left, right);
	}
	
	public void merge(Queue<Student> q, Queue<Student> left, Queue<Student> right) {
		
		while (!left.isEmpty() && !right.isEmpty()) {
			boolean comp = compare(left.peek(), right.peek());
			q.offer(comp ? left.poll() : right.poll());
		}
		
		while(!left.isEmpty()) q.offer(left.poll());
		while(!right.isEmpty()) q.offer(right.poll());
	}
	
	public boolean compare(Student o1, Student o2) {
		
		if(o1.getKorean() == o2.getKorean()) {
			
			if(o1.getEnglish() == o2.getEnglish()) {
				
				if(o1.getMathematics() == o2.getMathematics()) {
					
					return o1.getName().compareTo(o2.getName()) < 0;
					
				} else return o1.getMathematics() > o2.getMathematics();
				
			} else return o1.getEnglish() < o2.getEnglish();
			
		} else return o1.getKorean() > o2.getKorean();
	}
	
	public class Student {
		private String name;
		private int korean, english, mathematics;
		
		public Student() {}
		
		public Student(String name, int korean, int english, int mathematics) {
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.mathematics = mathematics;
		}

		public String getName() {
			return name;
		}

		public int getKorean() {
			return korean;
		}

		public int getEnglish() {
			return english;
		}

		public int getMathematics() {
			return mathematics;
		}
	}
}
