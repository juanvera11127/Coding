// Dictionary that stores values of type V indexed by keys of type K
interface DictionaryInterface<K,V> {
	public boolean isEmpty(); // returns true if the Dictionary is empty, false otherwise.

	public int size(); // Returns the number of key/value pairs stored in the dictionary.

	// Adds a value stored under the given key. If the key has already been stored in the Dictionary,
	// replaces the value associated with the key and returns the old value. If the key isn't in the dictionary
	// returns null.
	public V put(K key, V value);

	public V get(K key); // Retrieves the value stored with the key.

	public boolean contains(K key);

	public void remove(K key); // Deletes the key/value pair stored with the given key.

	public void clear(); // Empties the dictionary.

	public LinkedList<K> getKeys(); // Returns an array of all the keys currently stored in the Dictionary.
}
