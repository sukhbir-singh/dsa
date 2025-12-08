
int[] numbers = {10, 20, 30, 40, 50};

// Use IntStream.of(array).sum() to calculate the sum
int sum = IntStream.of(numbers).sum();

OR

int[] arr = {1,2,4,5,62,4,6,2,44,23,90};

int sum = Arrays.stream(arr).sum();
System.out.println("sum: "+sum);

int max = Arrays.stream(arr).max().getAsInt();
System.out.println("max: "+max);


