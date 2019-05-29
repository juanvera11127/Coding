import java.awt.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphTraversal {

	private boolean[][] arr;
	private int startCol;
	private int endCol;
	private HashMap<Entry, Integer> distance;
	private HashMap<Entry, Entry> cameFrom;
	private Queue<Entry> toVisit;
	private HashSet<Integer> visited;
	private Board board;
	public static boolean isDone;
	
	public class Entry {
		public int a;
		public int b;
		public int val;

		public Entry(int a, int b) {
			this.a = a;
			this.b = b;
			val = a * 1000 + b;
		}

	}

	public GraphTraversal(boolean[][] arr, int a, int b, Board board) {
		this.board = board;
		this.arr = arr;
		startCol = a;
		endCol = b;
		isDone = false;

		Entry start = new Entry(0, startCol);
		
		toVisit = new LinkedList<Entry>();
		visited = new HashSet<>();
		distance = new HashMap<>();
		cameFrom = new HashMap<>();
		
		toVisit.add(start);
		distance.put(start, 0);
		cameFrom.put(start, null);
		Board.solution[start.a][start.b] = true; 
		
		System.out.println("initializing maze search...");
		traverse();
	}

	public void traverse() {
		
		while (toVisit.size() > 0) {
			
			Entry entry = toVisit.remove();
			if (entry.a == arr.length - 1 && entry.b == endCol) {
				System.out.println("done!");
				//create list form cameFrom chain
				
				while(cameFrom.get(entry) != null) {
					Board.solution[entry.a][entry.b] = true;
					entry = cameFrom.get(entry);
				}
				board.repaint();
				return;
			}
			
			if(visited.contains(entry.val)) {
				continue;
			}
			
			visited.add(entry.val);
			
			Entry curr;
			int dist;
			// for loop and enqueue surrounding trues
			curr = new Entry(entry.a + 1, entry.b);			
			if(curr.a < arr.length && !arr[entry.a+1][entry.b]) {
				checkEntry(entry, curr);
			}				
			curr = new Entry(entry.a - 1, entry.b);
			if(entry.a > 0  && !arr[entry.a-1][entry.b]) {
				checkEntry(entry, curr);
			}
			curr = new Entry(entry.a, entry.b+1);
			if(entry.b + 1 < arr.length && !arr[entry.a][entry.b+1]) {
				checkEntry(entry, curr);
			}
			curr = new Entry(entry.a, entry.b-1);
			if(entry.b > 0 && !arr[entry.a][entry.b-1]) {
				checkEntry(entry, curr);
			}
		}
		System.out.println("Error: invalid maze");
		isDone = true;
		return;
	}
	
	private void checkEntry(Entry entry, Entry curr) {
		int dist;
		dist = distance.get(entry) + 1;
		if(!distance.containsKey(curr) || dist < distance.get(curr)) {
			distance.put(curr, dist);
			cameFrom.put(curr, entry);
			toVisit.add(curr);
		}
	}

}
