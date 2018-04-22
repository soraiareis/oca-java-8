# Chapter 4 - Methods and Encapsulation

## Designing Methods

  ```
  public final void nap(int minutes) throws InterruptedException {
    // take a nap
  }
  
  <access modifier> <optional specifier> <return type> <method name>(<parameters>) <optional exception list>{
    // method code
  }
  ```

### Access Modifiers
- There are four choices fo access modifiers:
  - `public`: the method can be called from any class.
  - `private`: the method can only be called within the same class.
  - `protected`: the method can only be called by classes in the same package or subclasses.
  - `default (package private) access` (omitted access modifier): the method can only be called by classes in the same package.

### Optional Specifiers
- You can have multiple specifiers in the same method (although not all combinations are legal).
- The possible specifiers are: 
  - `static`: used for class methods.
  - `final`: used when a method cannot be overridden by a subclass.
  - `synchronized`: Not on OCA exam. Only on OCP.
  - `native`: Neither on OCA nor on OCP exams. Used when interacting with code written in another language.
  - `strictfp`: Neither on OCA nor on OCP exams. Used for making floating-point calculations portable.

### Return Type
- The return type might be an actual Java type such as String or int.
- If there is not return type, the void keyword is used.

### Method Name
- An identifier may only contain letters, numbers, $, or _.
- The first character cannot be a number.
- Reserved words are not allowed.
- By convention, methods begging with a lowercase letter.

### Parameter List
- Although the parentheses are required, it does not have to contain any parameter.
- If you have multiple parameters you separate them with commas.

### Optional Exception List
- You can list as many types of exceptions as you want separated by commas.

### Method Body
- It has braces that contain zero or more Java statements.

## Working with Varargs (variable arguments)
- A `vararg` parameter must be the last element in a method's parameter list.
- A method can only have one `vararg` parameter.
- When calling a method with a `vararg` parameter you have a choice:
  - pass an array; or
  - list the elements of the array (and let Java create for you); or
  - omit the `vararg` values (and Java will create an array with length zero).

## Applying Access Modifiers
- Most restrictive to least restrictive:
  - `private`: only accessible within the same class.
  - `default (package private) access`: private and other classes in the same package.
  - `protected`: default access and child classes. It applies under two scenarios:
    - A member is used without referring to a variable: takes the advantage of inheritance and protected access is allowed.
    - A member is used through a variable: In this case the rules for the reference type of the variable are what matter. If it is a subclass, protected access is allowed. 
  - `public`: protected and classes in other packages.

### Designing Static Methods and Fields
- Static methods do not require an instance of the class.
- Static methods have two main purposes:
  - For utility or helper methods that don't require any object state.
  - For state that is shared by all instances of a class, like a counter.
  
### Static vs. Instance
- An static member cannot call a instance member (field or method). 
- Static member cannot reference a nonstatic member.

### Static Variables
- Static variables are meant to change as the program runs.
- Other static variables are meant to never change during program. 
  - This type of variable is called `constant`.
  - It uses the `final` modifier to ensure the variable never changes.
- `static final` constants use a different naming convention: they are all uppercase letters with underscores between words.
  ```
  private static final int NUM_BUCKETS = 45;
  ```

### Static Initialization
  ```
  private static final int NUM_SECONDS_PER_HOUR;
  static {
    int numSecondsPerMinute = 60;
    int numMinutesPerHour = 60;
    NUM_SECONDS_PER_HOUR = numSecondsPerMinute * numMinutesPerHour;
  }
  ```
- Static initializer runs when the class is first used.

### Static Imports
- Static imports are for importing static members of classes.
- Like a regular import you can used wildcard or import a specific member. 

## Passing Data Among Methods
- Java is a "pass-by-value" language, which means that a copy of the variable is made and the method receives that copy.

## Overloading Methods
- `Method overloading` occurs when there are different method signatures with the same name but different type/numbers of parameters.
- Everything other than the method can vary for overloading methods. This means there can be different access modifiers, specifiers (like static), return types, and exceptions lists.
- The second method does not compile because it only differs from the original by return type and the parameter list is the same.
  ```
  public void fly(int numMiles) { }
  public int fly(int numMiles) { }   // DOES NOT COMPILE
  ```
- The same happen bellow. The parameter list continues the same.
  ```
  public void fly(int numMiles) { }
  public static void fly(int numMiles) { }   // DOES NOT COMPILE
  ```

### Overloading and Varargs
- Java treats `varargs` as if they were array. This means the bellow methods are considered the same.
  ```
  public void fly(int[] lengths) { }
  public void fly(int... lengths) { }   // DOES NOT COMPILE
  ```
- However, it differs of the way we call each one:
  ```
  fly(new int[] { 1, 2, 3 });
  fly(1, 2, 3);
  ```

### Putting It All Together
- Java calls the most specific method it can.
- The following is the order Java uses to choose the right overloaded method:
  - Exact method by type;
  - Larger primitive type;
  - Autoboxed type;
  - Varargs.

## Creating Constructors
- Every Java class has a constructor. Even if you do not specify one Java will create one for you without parameter, which is called `default constructor`.
- You can have multiple constructors in a class, but they need to have different signatures.
- If we have a constructor and want to call another constructor to avoid duplications you need to use `this`.
  ```
  public class Hamster {
    private String color;
    private int weight;
  
    public Hamster(int weight){
      this(weight, "brown");
    }
    
    public Hamster(int weight, String color){
      this.weight = weight;
      this.color = color;
    }
  }
  ```
- `this()` has a special condition to be used: it needs to be the first statement in the constructor.
  ```
  public Hamster(int weight){
    System.out.println("in constructor");
    this(weight, "brown");                // DOES NOT COMPILE
  }
  ```
- Constructor chaining: is a technique to have each constructor add one parameter until getting to the constructor that does all the work.
- `final` fields can be assigned inside the constructor.

### Order of Initialization
- This list must be memorized for the exam:
1. If there is a superclass, initialize it first.
2. Static variable declarations and static initializers in the order they appear in the file.
3. Instance variable declarations and instance initializers in the order they appear in the file.
4. The constructor.

- Keep in mind that the four rules applies only if an object is instantiated. If the class is referred to without a `new` call, only rules 1 and 2 applies.
  ```
  public class InitializationOrder {
    private String name = "Torchie";
    { System.out.println(name); }
    private static int COUNT = 0;
    static { System.out.println(COUNT); }
    {COUNT++; System.out.println(COUNT);}
    public InitializationOrder() {
      System.out.println("constructor");
    }
    public static void main(String[] args) {
      System.out.println("read to constructor");
      new InitializationOrder();
    }
  }
  ```
- The output looks like this:
  ```
  0
  read to constructor
  Torchie
  1
  constructor
  ```
  
## Encapsulating Data
- `Encapsulation` means we set up the class so only methods in the class with the variables can refer to the instance variables. Callers are required to use these methods.
- The instance variables are `private`.
- We create `accessor method` or a `getter` to read the value.
- We create `mutator method` or a `setter` to update the value.
- For encapsulation instance variables are `private` and getters/setters are `public`.
- `JavaBeans` are reusable software components. It call and instance variable a `property`.
- The only thing you need to know about `JavaBeans` is the naming conventions bellow:
  - Properties are private.
  - Getter methods begin with `is` or `get` if the property is a boolean.
  - Getter methods begin with `get` if the property is not a boolean.
  - Setter methods begin with `set`.
  - The method name must have a prefix of `set/get/is`, followed by the first letter of the property in uppercase, followed by the rest of the property name.

### Creating Immutable Classes
- Encapsulating data is helpful because it prevents callers from making uncontrolled changes to your class.
- Another common technique is making classes immutable so they cannot be changed at all.
- `Immutable Classes` are helpful because you know they will always be the same.
- To make a class immutable you just need to omit the setters.
- But we still want to the able to specify the initial values: we use constructor then.

## Writing Simple Lambdas
- Java 8 added the ability to write code using another style: `Functional Programming`.
- `Functional Programming` is a way of writing code more declaratively. You specify what you want to do rather than dealing with the state of objects.
- `Functional Programming` uses lambda expressions to write code.
- A `lambda expression` is a block of code that gets passed around.
- A `lambda expression` is like a method that you can pass as if it were a variable.

### Lambda Syntax
- The simplest lambda expression you can write is (they are equivalent):
  ```
  a -> a.canHop()
  
  OR
  
  (Animal a) -> { return a.canHop(); }
  ```
- `lambda expression` omitting optional parts:
  ```
  a -> a.canHop()
  <parameter name> -> <body>
  ```
  
- `lambda expression` including optional parts:
  ```
  (Animal a) -> { return a.canHop(); }
  (<optional parameter type> <parameter name>) -> { return <body>; } // return and ; required because in block
  ```
- Java does not allow us to redeclare a local variable, so the following is an issue:
  ```
  (a, b) -> { int a = 0; return 5; }    // DOES NOT COMPILE
  ```
  
### Predicates
- Lambdas works with interfaces that have only one method.
- But if we have to to create a lot of interfaces to do the same for different objects? We can use Predicates.
- Java 8 integrates the Predicate interface into some existing classes.
- There is only one we need to know for OCA exam: `removeIf()` from `ArrayList`.
  ```
  List<String> bunnies = new ArrayList<>();
  bunnies.add("long ear");
  bunnies.add("floppy");
  bunnies.add("hoppy");
  System.out.println(bunnies);              // [long ear, floppy, hoppy]
  bunnies.removeIf(s -> s.charAt(0) != 'h');
  System.out.println(bunnies);              // [hoppy]
  ```