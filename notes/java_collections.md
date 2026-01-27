
Good method to print array.
Arrays.toString(arr)

// sorting of array
Arrays.sort(arr);

// comparing array
Arrays.equals(arr1, arr2);

Remember that in Java type is boolean and not bool.

PriorityQueue<Double> pq = new PriorityQueue<>();
Note: by default priority queue is min heap.

To make it max heap, you have to pass the comparator.
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

// making a priority queue where each element in 2 size array
PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

Collections.reverse(wordList);

// This is how you reset stringBuilder
StringBuilder word = new StringBuilder();
word.setLength(0);

--------------------------------------------------------------------------------
// Looping over Java Map

>> Approach 1
Map<String, Integer> map = new HashMap<>();
map.put("Apple", 1);
map.put("Banana", 2);
map.put("Orange", 3);

map.forEach((key, value) -> {
    System.out.println("Key: " + key + ", Value: " + value);
});

Note: in this first approach - remember that you cannot use local variable of method. 
You will get error like this => Line 14: error: local variables referenced from a lambda expression must be final or effectively final;

>> Approach 2

// Source - https://stackoverflow.com/a
// Posted by ScArcher2, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-13, License - CC BY-SA 4.0

Map<String, String> map = ...
for (Map.Entry<String, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + "/" + entry.getValue());
}

// Source - https://stackoverflow.com/a
// Posted by ScArcher2, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-13, License - CC BY-SA 4.0

for (var entry : map.entrySet()) {
    System.out.println(entry.getKey() + "/" + entry.getValue());
}

>> Approach 3: You can also pass comparator as lambda directly in the collections.sort method as argument.

List<String> charStrings = new ArrayList<String>();

// Our comparator is (a, b) -> b.length() - a.length().
// If a is longer than b, then a negative number will be returned
// telling the sort algorithm to place a first. Otherwise, a positive
// number will be returned, telling it to place a second.
// This results in a longest to shortest sorted list of the strings.
Collections.sort(charStrings, (a, b) -> b.length() - a.length());

--------------------------------------------------------------------------------

// This is also a good example: we can also sort directly using frequency map

// Count up the occurances.
Map<Character, Integer> counts = new HashMap<>();
for (char c : s.toCharArray()) {
    counts.put(c, counts.getOrDefault(c, 0) + 1);
}
        
// Make a list of the keys, sorted by frequency.
List<Character> characters = new ArrayList<>(counts.keySet());        
Collections.sort(characters, (a, b) -> counts.get(b) - counts.get(a));

- Remeber that valueSet() method does not exist in java for hash map. only keyset and entryset exists.

- ArrayList has one useful method to remove the last element. This is quite useful in recursion questions.
Useful Method: list.removeLast(); // Introduced in Java 21, so it won't work in older java versions
Instead of this method use: list.remove(list.size()-1);


--------------------------------------------------------------------------------

- This is important for priority queue implementation in questions.
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

