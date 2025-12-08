
Good method to print array.
Arrays.toString(arr)

Remember that in Java type is boolean and not bool.

PriorityQueue<Double> pq = new PriorityQueue<>();
Note: by default priority queue is min heap.

To make it max heap, you have to pass the comparator.
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

// making a priority queue where each element in 2 size array
PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

