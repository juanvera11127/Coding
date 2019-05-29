import java.util.ArrayList;

class Hashtable<K,V> implements DictionaryInterface<K,V> {

	protected int tableSize;
	protected int size;
	ArrayList<LinkedList<Entry<K,V>>> table;
	
	public Hashtable(int buckets) {
		tableSize = buckets;
		size = 0;
		table = new ArrayList<>();
		for(int i = 0; i < buckets; i++) {
			table.add(0, null);
		}
	}
	
	protected class Entry<K,V> {
		protected K key;
		protected V value;
		
		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}


	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}

	public V put(K key, V value){
		int arrayIndex = Math.abs(key.hashCode())%tableSize;
		LinkedList<Entry<K,V>> bucket = table.get(arrayIndex);
		if(bucket == null) {
			bucket = new LinkedList<Entry<K,V>>();
			bucket.add(0, new Entry<K,V>(key, value));
			table.set(arrayIndex, bucket);
			size++;
		}
		else {
			for(int i = 0; i < bucket.size(); i++) {
				Entry<K,V> entry = bucket.get(0);
				bucket.remove(0);
				if(entry.key.equals(key)) {
					V toReturn = entry.value;
					bucket.add(0,new Entry<K,V>(key,value));
					return toReturn;
				}
				bucket.add(bucket.size(), entry);
			}
			bucket.add(0,  new Entry<K,V>(key,value));
			size++;
		}
		return value;
	}

	public V get(K key) throws DictionaryKeyNotFoundException{
		int arrayIndex = Math.abs(key.hashCode())%tableSize;
		LinkedList<Entry<K,V>> bucket = table.get(arrayIndex);
		if(bucket == null) {
			throw new DictionaryKeyNotFoundException("Error: Key not found");
		}
		else {
			for(int i = 0; i < bucket.size(); i++) {
				Entry<K,V> entry = bucket.get(0);
				bucket.remove(0);
				if(entry.key.equals(key)) {
					bucket.add(0,entry);
					return entry.value;
				}
				bucket.add(bucket.size(), entry);
			}

			throw new DictionaryKeyNotFoundException("Error: Key not found.");
		}
	}

	public void remove(K key) throws DictionaryKeyNotFoundException{
		int arrayIndex = Math.abs(key.hashCode())%tableSize;
		LinkedList<Entry<K,V>> bucket = table.get(arrayIndex);
		if(bucket == null) {
			throw new DictionaryKeyNotFoundException("Error: Key not found.");
		}
		else {
			for(int i = 0; i < bucket.size; i++) {
				Entry<K,V> entry = bucket.get(0);
				bucket.remove(0);
				if(entry.key.equals(key)) {
					size--;
					return;
				}
				bucket.add(bucket.size(), entry);
			}
			throw new DictionaryKeyNotFoundException("Error: Key not found.");
		}
	}

	public void clear(){
		for(int i = 0; i < table.size(); i++) {
			table.set(i, null);
		}
		size = 0;
	}

	public LinkedList<K> getKeys(){
		LinkedList<K> temp = new LinkedList<K>();
		for(int i = 0; i < table.size(); i++) {
			if(table.get(i) != null) {
				for(int j = 0; j < table.get(i).size(); j++) {
					temp.add(0, table.get(i).get(j).key);
				}
				
			}
		}
		return temp;
	}



	public boolean contains(K key){

		int index = Math.abs(key.hashCode()) % table.size();
		LinkedList<Entry<K,V> > bucket = table.get(index);
		if (bucket == null){
			return false;
		}
		else{
			for (int ii = 0; ii < bucket.size(); ii++){
				Entry<K,V> entry = bucket.get(ii);
				if (entry.key.equals(key)){
					return true;
				}
			}
		}
		return false;
	}

	// Returns the size of the biggest bucket (most collisions) in the hashtable.
	public int biggestBucket() {
		int biggestBucket = 0;
		for(int i = 0; i < table.size(); i++) {
			// Loop through the table looking for non-null locations.
			if (table.get(i) != null) {
				// If you find a non-null location, compare the bucket size against the largest
				// bucket size found so far. If the current bucket size is bigger, set biggestBucket
				// to this new size.
				LinkedList<Entry<K,V> > bucket = table.get(i);
				if (biggestBucket < bucket.size())
					biggestBucket = bucket.size();
			}
		}
		return biggestBucket; // Return the size of the biggest bucket found.
	}

	// Returns the average bucket length. Gives a sense of how many collisions are happening overall.
	public float averageBucket() {
		float bucketCount = 0; // Number of buckets (non-null table locations)
		float bucketSizeSum = 0; // Sum of the size of all buckets
		for(int i = 0; i < table.size(); i++) {
			// Loop through the table
			if (table.get(i) != null) {
				// For a non-null location, increment the bucketCount and add to the bucketSizeSum
				LinkedList<Entry<K,V> > bucket = table.get(i);
				bucketSizeSum += bucket.size();
				bucketCount++;
			}
		}

		// Divide bucketSizeSum by the number of buckets to get an average bucket length.
		return bucketSizeSum/bucketCount;
	}
	public String toString(){

		String[] stringArray = new String[size];
		int counter = 0;
		for (int ii = 0; ii < table.size(); ii++){
			if (table.get(ii) != null){
				for (int jj = 0; jj < table.get(ii).size(); jj++){
					stringArray[counter] = table.get(ii).get(jj).key.toString() + ": " + table.get(ii).get(jj).value.toString();
					counter++;
				}
			}
		}
		return String.join("\n",stringArray);
	}
}
