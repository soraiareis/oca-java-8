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