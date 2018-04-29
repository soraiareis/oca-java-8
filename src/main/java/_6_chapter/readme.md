# Chapter 6 - Exceptions

## Understanding Exceptions
- An `exception` is Java's way of saying: "I don't know what to do right now. You deal with it."
- When you write a code you can either deal with the exception or make it the calling code's problem.
- On OCA exam, exceptions deal largely with mistakes in the program.
- The key point to remember is that exceptions alter the program flow.
- Java has a `Throwable` superclass for all objects that represent these events.

``` bash
├── java,lang.Object
│   └── java.lang.Throwable
│       ├── java.lang.Exception
│       │    └── java.lang.RuntimeException
└────── └── java.lang.Error
```

- `Error` means something went so horribly wrong that your program should not attempt to recover from it.
  - **Example:** disk drive `disappeared`.
- `RuntimeException` (also known as `unchecked exceptions`) tend to be unexpected but not necessarily fatal.
  - **Example:** accessing an invalid array index.
- A `checked exception` includes `Exception` and all subclasses that do not extend `RuntimeException`, they tend to be more anticipated.
  - **Example:** trying to read a file that does not exist.

- Java has a rule called the `handle or declare rule`, which means that for `checked exceptions` Java requires the code to either handle them or declare them in the method signature.
  ``` 
  void fall() throws Exception {
    throw new Exception();
  }
  ```
- `throw`: tells Java you want to throw and Exception.
- `throws`: declares the method might throw an Exception (and also might not).

- On the exam you will see two types of code that result in an exception:
  - Questions about exceptions can be hidden in questions that appear something else.
  - The code below throws an `ArrayIndexOutOfBoundsException`.
  ``` 
  String[] animals = new String[0];
  System.out.println(animals[0]);
  ```
  - The second way is to explicitly request Java to throw one.
  ``` 
  throw new Exception();
  throw new Exception("Ow! I fell.");
  throw new RuntimeException();
  throw new RuntimeException("Ow! I fell.");
  ```
- When creating an exception you can usually pass a `String` parameter with a message or you can pass no parameter.

| Type              | How to recognize                                           | Okay for program to catch? | Is program required to handle or declare? |
| ----------------- | ---------------------------------------------------------- | -------------------------- | ----------------------------------------- |
| Runtime exception | Subclass of RuntimeException                               | Yes                        | No                                        |
| Checked exception | Subclass of Exception but not subclass of RuntimeException | Yes                        | Yes                                       |
| Error             | Subclass of Error                                          | No                         | No                                        |


## Using a `try` Statement
- Java use `try statement` to separate the logic that might throw and exception from the logic to handle the exception.
- The syntax is:
  ``` 
  try {
    // also referred to as protected code
  } catch ( exception_type_identifier ) {
    // exception handler
  }
  ```
- The code in the `try` block is run normally. If any of the statements throw an exception that can be caught by the exception type listed in the catch block.
- The curly braces are required for `try` and `catch` blocks.
- The `try` statement lets you run code at the end with a `finally clause` regardless of whether an exception is thrown.
  ``` 
  try {
    // protected code
  } catch ( exception_type_identifier ) {
    // exception handler
  } finally {
    // finally block
  }
  ```
- The `catch` and the `finally` blocks needs to go in this order.
- The `catch` block is not required if a `finally` block is informed.
  ``` 
  try {
    // protected code
  } finally {
    // finally block
  }
  ```
- The only exception that the `finally` block does not run is when you have a `System.exit` statement in either `try` or `catch` block (`System.exit` ends the program).

- For the OCA exam you must be able to:
  - recognize if the exception is a checked or an unchecked exception;
  - determine if any of the exceptions are subclasses of the others.
- All the bellow exceptions are `unchecked exceptions`.  
  ``` 
  class AnimalsOutForAWalk extends RuntimeException { }
  class ExhibitClosed extends RuntimeException { }
  class ExhibitClosedForLunch extends ExhibitClosed { }
  ```
- We can catch more than one type of exception:
  ``` 
  try {
    seeAnimal();
  } catch (AnimalsOutForAWalk e) {
    System.out.print("try back later");     // only runs if the type is AnimalsOutForAWalk
  } catch (ExhibitClosed e) {
    System.out.print("not today");          // only runs if the type is ExhibitClosed
  }
  ```
- Java looks at the catch blocks in the order they appear.
- If it is impossible for one of the catch blocks to be executed, a compiler error about unreachable code occurs.
  - This happens when a superclass is caught before a subclass. 
  ``` 
  try {
    seeAnimal();
  } catch (ExhibitClosed e) {
    System.out.print("try back later");
  } catch (ExhibitClosedForLunch e) {       // DOES NOT COMPILE
    System.out.print("not today");
  }
  ```
  
- Only the last exception to be thrown matters:
  ``` 
  try {
    throw new RuntimeException();
  } catch (RuntimeException e) {
    throw new RuntimeException();
  } finally { 
    throw new Exception();        // This is the exception that gets thrown.
  }
  ```
  
## Recognizing Common Exception Types
- You need to memorize three types of exceptions for the exam:
  - runtime exceptions;
  - checked exceptions;
  - errors.
- You will need to recognize which type of an exception it is and whether it is thrown by JVM or a programmer.

### Runtime Exceptions
- Extend `RuntimeException`.
- They do not have to be handled or declared.
- They can be thrown by the programmer or by the JVM.
- Common `runtime exceptions` include the following:
  - **ArithmeticException** Thrown by the JVM when code attempts to divide by zero.
  - **ArrayIndexOutOfBoundsException** Thrown by the JVM when code uses an illegal index to access an array.
  - **ClassCastException** Thrown by the JVM when an attempt is made to cast an object to a subclass of which it is not an instance.
  - **IllegalArgumentException** Thrown by the programmer to indicate that a method has been passed an illegal or inappropriate argument.
  - **NullPointerException** Thrown by the JVM when there is a `null` reference where an object is required.
  - **NumberFormatException** Thrown by the programmer when an attempt is made to convert a string to a numeric type but the string does not have an appropriate format.
  
### Checked Exceptions
- Checked exceptions have `Exception` in their hierarchy but not `RuntimeException`.
- They must be handled or declared.
- They can be thrown by the programmer or by the JVM.
- Common `checked exceptions` include the following:
  - **FileNotFoundException** Thrown programmatically when code tries to reference a file that does not exists.
    - It is a subclass of `IOException`.
  - **IOException** Thrown programmatically when there is a problem reading or writing a file.

### Errors
- Extend the `Error` class.
- Should not be handled or declared.
- They are thrown by the JVM.
- Errors are rare, but you might see these:
  - **ExceptionInInitializerError** Thrown by the JVM when a static initializer throws an exception and does not handle it.
  - **StackOverflowError** Thrown by the JVM when a method calls itself too many times (`infinite recursion`).
  - **NoClassDefFoundError** Thrown by the JVM when a class that the code uses is available at compile time but not runtime.

## Calling Methods That Throw Exceptions
  ``` 
  class NoMoreCarrotsException extends Exception {}
  public class Bunny {
    public static void main (String[] args) {
      eatCarrots();             // DOES NOT COMPILE
    }
    private static void eatCarrot() throws NoMoreCarrotsException {
    }
  }
  ```
- The code would compile if we change the the `main` method to either of these:
  ``` 
  public static void main (String[] args) throws NoMoreCarrotsException { // declare exception
    eatCarrots();
  }
  ```
  
  ``` 
  public static void main (String[] args) {
    try {
      eatCarrots();
    } catch (NoMoreCarrotsException e) {  // handle exception
      System.out.print("sad rabbit");
    }
  }
  ```
  
- When a class overrides a method from a superclass or implements a method from an interface, it is not allowed to add new checked exceptions to the method signature.
  ``` 
  class CanNotHopException extends Exception {}
  class Hopper {
    public void hop() {}
  }
  class Bunny extends Hopper {
    public void hop() throws CanNotHopException {}  // DOES NOT COMPILE
  }
  ```
- A subclass is allowed to declare fewer exceptions than the superclass or interface.
  ``` 
  class CanNotHopException extends Exception {}
  class Hopper {
    public void hop() throws CanNotHopException{}
  }
  class Bunny extends Hopper {
    public void hop() {}  
  }
  ```
- A class is allowed to declare a subclass of an exception type.
  - The superclass or interface has already taken care of a broader type.
  ``` 
  class CanNotHopException extends Exception {}
  class Hopper {
    public void hop() throws Exception {}
  }
  class Bunny extends Hopper {
    public void hop() throws CanNotHopException{}  
  }
  ```
- This rule applies only for checked exceptions. The following code is legal because it has a runtime exception in the subclass version.
  ``` 
  class Hopper {
    public void hop(){}
  }
  class Bunny extends Hopper {
    public void hop() throws IllegalStateException {}  
  }
  ```

### Printing an Exception 
- There are three ways:
  - let Java print it out;
    ``` 
    System.out.println(e);
    ```
  - print just the message; or
    ``` 
    System.out.println(e.getMessage());
    ```
  - print where the stack trace comes from.
    ``` 
    e.printStackTrace();
    ```