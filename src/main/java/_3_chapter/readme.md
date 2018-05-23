# Chapter 3 - Core Java APIs

- API stands for Application Programming Interface.
- The Java APIs are a group of class or interface definitions that gives you access to a service or functionality.

## Creating and Manipulating Strings

### Concatenation
- `Concatenation` is placing one String before another String and combining the together.
- Rules are:
  - If both operands are numeric, `+` means numeric addition;
  - If either operand is a String, `+` means concatenation;
  - The expression is evaluated left to right.
- Also remembers that if you have a String `s` and a statement `s += "2"` it means `s = s + "2"`.

### Immutability
- Once a String object is created, it is not allowed to change. It cannot be made larger or smaller, and you cannot change one of the characters inside.
- String is `immutable`.
- Immutable classes only have getters, they do not have setters.
- Immutable classes in Java are `final`, and subclasses can't add mutable behavior.
  ```
  String s1 = "1";
  String s2 = s1.concat("2");
  s2.concat("3"); // There is not effect since Strings are immutable
  System.out.println(s2); // It prints "12"
  ```

### The String Pool
- String uses a lot of memory, so Java realizes that many strings repeat and solves this issue by reusing them.
- The `string pool` (also known as intern pool) is a location in Java Virtual Machine (JVM) that collects all these strings.
- The `string pool` contains literal values that appear in the program.
  - "name" is a literal and goes into the `string pool`
  - myObject.toString() is NOT a literal, so it does NOT goes into the `string pool`
- Strings not in the `string pool` are garbage collected just like any other object.
  ```
  String name = "Fluffy"; // Uses string pool
  String name = new String("Fluffy"); // Do NOT use string pool, and is less efficient
  ```

### Important String Methods
- Need to remember:
  - String is a sequence of characters;
  - Java counts from 0 when indexed.

- `length()`: returns the number of characters in the String.
  - Method signature: `int length()`
  ```
  String string = "animals";
  System.out.println(string.length()); // 7
  ```
- `charAt()`: returns the character that is in a specific position.
  - Method signature: `char charAt(int index)`
  ```
  String string = "animals";
  System.out.println(string.charAt(0)); // a
  ```
- `indexOf()`: looks at the string and finds the first index that matches the desired value.
  - Can work with individual character or string as input.
  - Returns -1 when no match is found.
  - Methods signature:
    - `int indexOf(int ch)`
    - `int indexOf(int ch, int fromIndex)`
    - `int indexOf(String str)`
    - `int indexOf(String str, int fromIndex)`
  ```
  String string = "animals";
  System.out.println(string.indexOf('a')); // 0
  System.out.println(string.indexOf("al")); // 4
  System.out.println(string.indexOf('a', 4)); // 4
  System.out.println(string.indexOf("al", 5)); // -1
  ```
- `substring()`: it returns parts of the string.
  - The first parameter is the index to start with.
  - The second parameter is optional, and is the end you want to stop at (it does not include this index).
  - Methods signature:
      - `String substring(int beginIndex)`
      - `String substring(int beginIndex, int endIndex)`
  ```
  String string = "animals";
  System.out.println(string.substring(3)); // mals
  System.out.println(string.substring(3, 6)); // mal
  System.out.println(string.substring(3, 7)); // mals

  System.out.println(string.substring(3, 3)); // empty string
  System.out.println(string.substring(3, 2)); // throws exception
  System.out.println(string.substring(3, 8)); // throws exception
  ```
- `toLowerCase()` and `toUpperCase()`: it converts the string to lower or upper case.
  - Methods signature:
    - `String toLowerCase()`
    - `String toUpperCase()`
- `equals()` and `equalsIgnoreCase()`: checks if two String objects contain exactly the same characters in the same order.
  - Methods signature:
    - `boolean equals(Object obj)`
    - `boolean equalsIgnoreCase(String str)`
- `startsWith()` and `endsWith()`: look at whether the provided value matches part of the String.
  - **Important!** It is case-sensitive.
  - Methods signature:
    - `boolean startsWith(String prefix)`
    - `boolean endsWith(String suffix)`
- `contains()`: also look if value matches part of the String, but it can be anywhere.
  - **Important!** It is case-sensitive.
  - Method signature: `boolean contains(String str)`
- `replace()`: does a simple search and replace on the string.
  - It replaces all the matches.
  - Methods signature:
    - `String replace(char oldChar, char newChar)`
    - `String replace(CharSequence oldChar, CharSequence newChar)`
  ```
  System.out.println("abcabc".replace('a', 'A')); // AbcAbc
  System.out.println("abcabc".replace("ab", "AB")); // ABcABc
  ```
- `trim()`: removes white spaces in the beginning and end of a String.
  - It removes tabs, spaces and trailing newline.
  - Method signature: `String trim()`
  ```
  System.out.println("abc".trim()); // abc
  System.out.println("\t    a b c\n".trim()); // a b c
  ```

## Using the StringBuilder Class
  ```
  10: String alpha = "";
  11: for (char current = 'a'; current <= 'z'; current++)
  12:   alpha += current;
  13: System.out.println(alpha);
  ```
- The above code block generates a new String every time it passes on line 12, because String is immutable an cannot be changed.
- The `StringBuilder` class creates String without storing all those interim String values.
- Unlike `String` class, `StringBuilder` class is not immutable.
  ```
  15: StringBuilder alpha = new StringBuilder();
  16: for (char current = 'a'; current <= 'z'; current++)
  17:   alpha.append(current);
  18: System.out.println(alpha);
  ```

### Mutability and Chaining
- When we chain `String` method calls, the result is a new String with the answer.
- Chaining `StringBuilder` does NOT work this way. `StringBuilder` changes its own state and returns a reference to itself.
  ```
  StringBuilder sb = new StringBuilder("start");
  sb.append("+middle");                         // sb = "start+middle"
  StringBuilder same = sb.append("+end");       // "start+middle+end"
  ```
- Check this code:
  ```
  StringBuilder a = new StringBuilder("abc");
  StringBuilder b = a.append("de");
  b = b.append("f").append("g");
  System.out.println("a=" + a);   // abcdefg
  System.out.println("b=" + b);   // abcdefg
  ```
  - There is only one `StringBuilder` object in this code. We can say that because `new StringBuilder();` was called just once.
- There are 3 ways of creating a `StringBuilder`:
  - `StringBuilder sb1 = new StringBuilder();` // empty
  - `StringBuilder sb2 = new StringBuilder("animal");` // create the object with "animal" character sequence
  - `StringBuilder sb3 = new StringBuilder(10);` // reserve a certain amount of slots

### Capaciyy
- When `StringBuilder` is constructed `new StringBuilder();` it may start at the default capacity of 16.
- The programmer can also choose the capacity on creation: `new StringBuilder(10);`.
- But if the the programmer passes a `String` on the constructor `new StringBuilder("animal");`, the capacity with be the size of the `String` plus the default capacity.
 - In this case the string is 6, plus the default 16, the capacity will be 22.

### Important StringBuilder Methods
- `charAt()`, `indexOf()`, `length()` and `substring()`: are the same as in String class.
- `append()`: it adds the parameter to the StringBuilder and returns a reference to the current StringBuilder.
  - There are a lot of methods signature, but below is the common one.
  - Method signature: `StringBuilder append(String str)`
- `insert()`: it adds characters to the StringBuilder at the requested index.
  - There are a lot of methods signature, but below is the common one.
  - Method signature: `StringBuilder insert(int offset, String str)`
  ```
  StringBuilder sb = new StringBuilder("animals");
  sb.insert(7, "-");    // sb = animals-
  sb.insert(0, "-");    // sb = -animals-
  sb.insert(4, "-");    // sb = -ani-mals-
  ```
- `delete()` and `deleteCharAt()`: it removes characters of the StringBuilder.
  - Methods signature:
    - `StringBuilder delete(int start, int end)`
    - `StringBuilder deleteCharAt(int index)` // removes just one character
- `reverse()`: it reverses the characters of the StringBuilder.
  - Method signature: `StringBuilder reverse()`
- `toString()`: converts StringBuilder into String.
  - Method signature: `String toString()`

### StringBuilder vs. StringBuffer
- `StringBuffer` is the older version, do the same thing, but it is slower because it is thread safe.
- `StringBuilder` is the best option now.

## Understanding Equality
  ```
  StringBuilder one = new StringBuilder();
  StringBuilder two = new StringBuilder();
  StringBuilder three = one.append("a");
  System.out.println(one == two);     // false - complete different objects
  System.out.println(one == three);   // true - points to the same objects
  ```

  ```
  String x = "Hello World";
  String y = "Hello World";
  System.out.println(x == y);     // true - both are on the same location of memory
  ```

  ```
  String x = "Hello World";
  String y = " Hello World".trim();
  System.out.println(x == y);     // false - y is computed at runtime, since is different runtime
                                  // a new object is created
  ```

  ```
  String x = new String("Hello World");
  String y = "Hello World";
  System.out.println(x == y);     // false - when we specify new String() we tell Java to create
                                  // a new object
  ```
- To validate logical equality instead of object equality we can use `equals()`:
  ```
  String x = "Hello World";
  String y = " Hello World".trim();
  System.out.println(x.equals(y));     // true
  ```
- If a class does not have an `equals()` method, Java determines if the reference points to the same object.
- Java does not allow comparison (==) of String and StringBuilder. The code does not compile.

## Understanding Java Arrays
- An `array` is an area of memory on the heap with space for a designated number of elements.
- A `String` and `StringBuilder` are an array with some methods. The `StringBuilder` array can be replaced with bigger arrays.
- An `array` is an ordered list. It contains duplicates.

### Creating an Array of Primitives
- Common way to create arrays:
  ```
  int[] numbers1 = new int[3];
  ```
- All elements are set to the default value for that type.
- Another way to create arrays is to specify all the elements:
  ```
  int[] numbers2 = new int[] {42, 55, 99};
  ```
- Java understand this last sample as redundant since you already specified the type in the beginning and the initial values already gives the size. So you can write:
  ```
  int[] numbers2 = {42, 55, 99}; // this is called anonymous array
  ```
- You can type [] before or after the name:
  ```
  int[] numAnimals;
  int numAnimals2[];
  ```

### Creating an Array with Reference Variables
- Arrays can be created with any type, even with classes we create.
  ```
  String[] bugs = {"cricket", "beetle", "ladybug"};
  String[] alias = bugs;
  System.out.println(bugs.equals(alias));     // true
  System.out.println(bugs.toString());        // [Ljava.lang.String;@160bc7c0
  java.util.Arrays.toString(bugs);            // [cricket, beetle, lady-bug]
  ```
  - `equals()` returns true because of reference equality, the `equals()` method on array does not look at the elements of the array.
  - `toString()` prints the reference.
  - `java.util.Arrays.toString()` prints the values of an array.
- Force bigger type into smaller type:
  ```
  3: String[] strings = { "stringValue" };
  4: Object[] objects = strings;
  5: String[] againStrings = (String[]) objects;
  6: againStrings[0] = new StringBuilder(); // DOES NOT COMPILE
  7: objects[0] = new StringBuilder();      // Careful!
  ```
- Line 6 does not compile because a String[] only allows String objects.
- Line 7 compiles, but throws an `ArrayStoreException`.
  - Although a StringBuilder can be assigned to an Object, we have a String[] referred to from an Object[], and Strings only allows String objects.

### Using an Array
- `length` returns the array size.
- `Arrays.sort()` allows to sort array elements.
  - Sort order: `numbers`, `uppercase letters` and then `lowercase letters`.
    ```
    String[] strings = {"N", "L", "n", "O", "S"};
    System.out.println(Arrays.sort(strings));       // [L, N, O, S, n]
    ```
- `Arrays.binarySearch(array, value)` allows to search in a *sorted* array (needs to be sorted).
  ```
  int[] numbers = {2, 4, 6, 8};
  System.out.println(Arrays.binarySearch(numbers, 2));  // 0
  System.out.println(Arrays.binarySearch(numbers, 1));  // -1
  ```
  - When it is not found it returns a negative value showing one smaller than the negative of index, where a match needs to be inserted to preserve the sorted order.
  - When an unsorted array is presented look for an answer `unpredictable output`.
- `Varargs`: variable arguments.
  - Syntax: `String... args`

### Multidimensional Arrays

- Can be declared like:
  ```
  int[][] vars1;
  int vars2[][];
  int[] vars3[];
  ```
- You can specify the size of a multidimensional array like:
  ```
  String[][] rectangle = new String[3][2];
  ```
- Declaring asymmetric arrays:
  ```
  int[][] differentSize = {{1, 4}, {3}, {9, 8, 7}};
  int[][] args = new int[4][];
  args[0] = new int[5];
  args[1] = new int[3];
  ```

## Understanding an ArrayList

- An `array` you have to choose the size of it and you are stuck with that choice.
- An `ArrayList` let you size at runtime as needed.
- `ArrayList` is just like an `array`, an ordered list that can contains duplicates.
- None of them are `immutable` (values can be changed, only size of `arrays` cannot be changed).

### Creating an ArrayList
- Possible ways to initiate an ArrayList:
  ```
  ArrayList list1 = new ArrayList();      // Creates an ArrayList with space for default number of
                                          // elements, but not fill it.
  ArrayList list2 = new ArrayList(10);    // Creates an ArrayList with a specific number of slots,
                                          // but also not fill it.
  ArrayList list3 = new ArrayList(list2); // Creates a copy of list2. It copies both size and values.

  ArrayList<String> list4 = new ArrayList<String>();  // Creates an ArrayList with specific type.
  ArrayList<String> list5 = new ArrayList<>();        // We can omit the type on the right side.
  ```
- If the type is not specified it can take any kind of object except primitive.
  ```
  List list = new ArrayList();
  list.add(1);
  list.add("A");
  list.add(new Integer(5));
  System.out.println(list);     // [1, A, 5]
  ```
- `ArrayList` implements an interface called `List`, which means an `ArrayList` is a `List`.
- We can store an `ArrayList` in a `List`, but not vice versa (interfaces cannot be instantiated).
  ```
  List<String> list6 = new ArrayList<>();
  ArrayList<String> list7 = new List<>();  // DOES NOT COMPILE
  ```

### Using an ArrayList
- We are going to see a "class" named E. It isn't a class really. E is used as a meaning of `any class that this array can hold`.
- `toString()`: print the content.
- `add()`: insert a new value on the `ArrayList`.
  - Methods signature:
    - `boolean add(E element)` // always returns true
    - `void add(int index, E element)`
- `remove()`: removes the first matching value in the `ArrayList`.
  - Methods signature:
    - `boolean remove(Object object)` // return whether a match was removed
    - `E remove(int index)`           // returns the element that was removed
- `set()`: change one of the elements in the array without changing the size.
  - Method signature: `E set(int index, E newElement)`
- `isEmpty()` and `size()`: looks at how many slots are in use.
  - Methods signature:
    - `boolean isEmpty()`
    - `int size()`
- `clear()`: provides an easy way to discard all elements of the `ArrayList`.
  - Method signature: `void clear()`
- `contains()`: check if a certain value is in the `ArrayList`.
  - Method signature: `boolean contains(Object object)`
- `equals()`: compare two lists to see if they contain the same elements in the same order.
  - Method signature: `boolean equals(Object object)`

### Wrapper Classes
- How can we use primitives with `ArrayList`?
  - We need to use `Wrapper Classes`, which is an object type that corresponds to the primitive.

| Primitive Type | Wrapper Class     |
| -------------- | ----------------- |
| boolean        | Boolean           |
| byte           | Byte              |
| short          | Short             |
| int            | Integer           |
| long           | Long              |
| float          | Float             |
| double         | Double            |
| char           | Character         |

- Converting String to primitive or wrapper class.

| Wrapper Class  | String to Primitive Type      | String to Wrapper Class                       |
| -------------- | ----------------------------- | --------------------------------------------- |
| Boolean        | Boolean.parseBoolean("true"); | Boolean.valueOf("TRUE");                      |
| Byte           | Byte.parseByte("1");          | Byte.valueOf("2");                            |
| Short          | Short.parseShort("1");        | Short.valueOf("2");                           |
| Integer        | Integer.parseInt("1");        | Integer.valueOf("2"); or Integer.decode("2"); |
| Long           | Long.parseLong("1");          | Long.valueOf("2");                            |
| Float          | Float.parseFloat("1");        | Float.valueOf("2");                           |
| Double         | Double.parseDouble("1");      | Double.valueOf("2.2");                        |
| Character      | None                          | None                                          |

- `new Boolean(String s)`: if passing a non null string equal to "true" - ignoring case (so it can be "True", or "true", or "TRUE", etc.) - it will be `true`. Otherwise, it will be false.
  - `new Boolean("yes")`: produces `false`.

### Autoboxing
- The process of typing primitive values and Java converting it into the relevant wrapper class.
  ```
  List<Double> weights = new ArrayList<>();
  weights.add(50.5);                  // autoboxes double primitive to Double class
  weights.add(new Double(60));
  ```
- Be careful autoboxing Integer. Removes takes an int parameter, Java calls that method instead of autoboxing.
- To remove the 2 from the list we can write `numbers.remove(new Integer(2));`
  ```
  List<Integer> numbers = new ArrayList<>();
  numbers.add(1);
  numbers.add(2);
  numbers.remove(1); // Removes index 1
  System.out.println(numbers); // Prints 1
  ```

### Converting Between array and List
- `ArrayList` into `array`
  ```
  List<String> list = new ArrayList<>();
  list.add("hawk");
  list.add("robin");
  Object[] objectArray = list.toArray();
  System.out.println(objectArray.length);   // 2
  String[] stringArray = list.toArray(new String[0]);
  System.out.println(stringArray.length);   // 2
  ```
- `array` into `List`
  - A backed list is created where a change in array is reflected on the List and vice versa.
  - It is a fixed size list, so we cannot remove items from this List.
  ```
  String[] array = {"hawk", "robin"};         // [hawk, robin]
  List<String> list = Arrays.asList(array);
  System.out.println(list.size());            // 2
  list.set(1, "test");                        // [hawk, test] - change both list and array
                                              // (they point to same reference)
  array[0] = "new";                           // [new, test] - change both list and array
                                              // (they point to same reference)
  list.remove(1);                             // throws UnsupportedOperation Exception
  ```

### Sorting ArrayList
  ```
  List<Integer> numbers = new ArrayList<>();
  numbers.add(99);
  numbers.add(5);
  numbers.add(81);
  Collections.sort(numbers);
  System.out.println(numbers);   // [5, 81, 99]
  ```

## Working with Dates and Times

### Creating Dates and Times
- When working with dates and times, the first thing to do is decide how much information you need. The exam gives you three choices:
  - `LocalDate`: contains just a date.
  - `LocalTime`: contains just a time.
  - `LocalDateTime`: contains both a date and time, but no time zone.
- Oracle recommends avoiding time zones unless you really need them. If you need to deal with time zone use `ZonedDateTime`.
- The three classes has a static method called `now()` that gives the current date and time. The output depends on your the local date and time where the code is running.
  ```
  System.out.println(LocalDate.now());      // 2015-01-20
  System.out.println(LocalTime.now());      // 12:45:18.401
  System.out.println(LocalDateTime.now());  // 2015-01-20T12:45:18.401
  ```
- Creating specific dates:
  - `LocalDate`
    - `LocalDate.of(2015, Month.JANUARY, 20);`
    - `LocalDate.of(2015, 1, 20);`
  - `LocalTime`
    - `LocalTime.of(6, 15);           // hour and minute`
    - `LocalTime.of(6, 15, 30);       // + seconds`
    - `LocalTime.of(6, 15, 30, 200);  // + nanoseconds`
  - `LocalDateTime`: can combine dates and times (there are more combinations).
    - `LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);  // can pass just hour and minutes or also pass nanoseconds`
    - `LocalDateTime.of(date1, time1);                        // passing LocalDate and LocalTime objects`
- We are NOT allowed to construct a date and time objects directly.
  ```
  LocalDate d = new LocalDate(); // DOES NOT COMPILE
  ```

### Manipulating Dates and Times
- Date and time classes are immutable, so we need to remember to assign the results to a reference variable so they are not lost.
  ```
  LocalDate date = LocalDate.of(2014, Month.JANUARY, 20); // 2014-01-20
  date = date.plusDays(2);                                // 2014-01-22
  date = date.plusWeeks(1);                               // 2014-01-29
  date = date.plusMonths(1);                              // 2014-02-28
  date = date.plusYears(5);                               // 2019-02-28
  date = date.minusDays(1);                               // 2019-02-27

  LocalTime time = LocalTime.of(5, 15);

  LocalDateTime dateTime = LocalDateTime.of(date, time);  // 2019-02-27T05:15

  dateTime = dateTime.minusHours(2);                     // 2019-02-27T03:15
  ```

- It is common for date and time to be chained:
  ```
  LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);

  LocalTime time = LocalTime.of(5, 15);

  LocalDateTime dateTime = LocalDateTime.of(date, time);  // 2014-01-20T05:15

  dateTime = dateTime.plusDays(2).minusHours(2);          // 2014-01-22T03:15
  ```

### Working with Periods
- `Period` is an arbitrary period of time.
- It is a day or more of a time.
- It only works with `LocalDate` or `LocalDateTime` (does not work with `LocalTime`).
- It does not allow chaining, so the last Period method call counts.
  ```
  Period anually = Period.ofYears(1);
  Period quarterly = Period.ofMonths(3);
  Period everyThreeWeeks = Period.ofWeeks(3);
  Period everyOtherDay = Period.ofDays(2);
  Period everyYearAndAWeek = Period.of(1, 0, 7);

  Period quarterly = Period.ofYears(1).ofMonths(3); // Only last method call counts

  LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
  date = date.plus(anually);                              // 2015-01-20
  ```

- `Duration` is intended for smaller units of time (days, hours, minutes, seconds or nanoseconds).
- `Duration` is not on the exam since it works the same as `Period`.

### Formatting Dates and Times
- Date and time classes support many ways to get data out of them.
  ```
  LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
  System.out.println(date.getDayOfWeek());                // MONDAY
  System.out.println(date.getMonth());                    // JANUARY
  System.out.println(date.getYear());                     // 2020
  System.out.println(date.getDayOfYear());                // 20
  ```
- `DateTimeFormatter` class provides a way to format any type of date and/or time object.
  ```
  LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
  LocalTime time = LocalTime.of(11, 12, 34);
  LocalDateTime dateTime = LocalDateTime.of(date, time);

  System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));          // 2020-01-20
  System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));          // 11:12:34
  System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2020-01-20T11:12:34

  // Using FormatStyle
  System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
  System.out.println(time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
  System.out.println(dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

  // Create your own format
  System.out.println(date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
  ```

### Parsing Dates and Times
  ```
  DateTimeFormatter f = DateTimeFormatter.ofPattern("MM dd yyyy");
  LocalDate date = LocalDate.parse("01 02 2015", f);  // 2015-01-02
  LocalTime time = LocalTime.parse("11:22");          // 11:22
  ```