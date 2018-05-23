# Chapter 5 - Class Design

## Introducing Class Inheritance
- `Inheritance` is the process by which the new child subclass automatically includes any public or protected primitives, objects, or methods defined in the parent class.
- Java supports `single inheritance`, by which a class may inherits from only one direct parent class.
- Java also support multiple levels of inheritance, by which one class may extend another class, which in turn extends another class.
- You can extend a class any number of times, allowing each descendent to gain access to its ancestor's member.
- Java does allow one exception to the `single inheritance` rule: classes may implement multiple interfaces.
- It is possible to prevent a class from being extended by marking the class with the `final` modifier.

### Extending a Class
- You can extend a class adding the parent class name in the definition using the `extends` keyword.

### Applying Access Modifiers
- You can apply access modifiers to classes.
- The `public` access modifier applied to a class indicates that it can be referenced and used in any class.
- The `default package private` modifier indicates the class can be accessed only by a class within the same package.
- `protected` and `private` cannot be used with classes.

### Creating Java Objects
- All classes inherit from `java.lang.Object` (it is the only class that does not have a parent class).

### Defining Constructors
- The first statement of every constructor is either a call to another constructor within the class, using this(), or a call to a constructor in the direct parent class, using super().
- Like the `this()` command, the `super()` command may only be used as the first statement of the constructor.
  - With this rule, a call to both `this()` and `super()` in the same constructor are not allowed since they both need to be the first statement. 
- If the parent class has more than one constructor, the child class may use any valid parent constructor.

**Understanding Compiler Enhancements**
- Java compiler automatically inserts a call to the no-argument constructor `super()` if the first statement is not a call to the parent constructor.
- If the parent class does not have a no-argument constructor you must create at least one constructor in your child class that explicitly calls a parent constructor via `super()`.
  ```
  public class Mammal {
    public Mammal(int age){
    }
  }
  
  public class Elephant extends Mammal { // DOES NOT COMPILE
  }
  ```

**Reviewing Constructor Rules**
1. The first statement of every constructor is a call to another constructor within the class using `this()`, or a call to a constructor in the direct parent class using `super()`.
2. The `super()` call may not be used after the first statement of the constructor.
3. If no `super()` call is declared in a constructor, Java will insert a no-argument `super()` as the first statement of the constructor.
4. If the parent does not have no-argument constructor and the child does not define any constructors, the compiler will throw an error and try to insert a default no-argument constructor into the child class.
5. If the parent does not have no-argument constructor, the compiler requires an explicit call to a parent constructor in each child constructor.

**Calling Constructors**
- The parent constructor is always executed before the child constructor. 

### Calling Inherited Class Members
- Java classes may use any `public` or `protected` member of the parent class, including methods, primitives, or objects references.
- If the parent class and child class are part of the same package, the child may also use any `default` members defined in the parent class.
- A child class may never access a `private` member of the parent class, at least not through any direct reference.
  - A `private` member may be accessed indirectly via a `public` or `protected` method.
- You can use the keyword `this` to access a member of the parent class that are accessible from the child class.
  ```
  class Fish {
    protected int size;
    private int age;
    
    public Fish(int age) {
      this.age = age;
    }
    
    public int getAge() {
      return age;
    }
  }
  
  public class Shark extends Fish {
    private int numberOfFins = 8;
    
    public Shark(int age) {
      super(age);
      this.size = 4;
    }
    
    public void displaySharkDetails() {
      System.out.print("Shark with age: " + getAge());
      System.out.print(" and " + this.size + " meters long");
      System.out.print(" with " + this.numberOfFins + " fins");
    }
  }
  ```
- You can explicitly reference a member of the parent class by using `super` keyword.
  ```
  public void displaySharkDetails() {
    System.out.print("Shark with age: " + super.getAge());
    System.out.print(" and " + super.size + " meters long");
    System.out.print(" with " + this.numberOfFins + " fins");
  }
  ```
- If the child class overrides a member of the parent class, `this` and `super` could have different effects when applied to a class member.

### Inheriting Methods

**Overriding a Method**
- If you want to define a new version of an existing method in a child class that makes use of definition in the parent class you can override the method.
- Override a method means declaring a new method with the signature and return type as the method in the parent class.
- When overriding a method you may reference the parent version of the method using the `super` keyword.
  - Keywords `this` and `super` allow you to select between current and parent version of the method.
  ```
  public class Canine {
    public double getAverageWeight(){
      return 50;
    }
  }
  
  public class Wolf extends Canine {
    public double getAverageWeight(){
      return super.getAverageWeight()+20;
    }
    
    public static void main(String[] args) {
      System.out.println(new Canine().getAverageWeight());    // 50.0
      System.out.println(new Wolf().getAverageWeight());      // 70.0
    }
  }
  ```
- Checks that compilers does when overriding a method:
1. The method in the child class must have the same signature as the method in the parent class.
2. The method in the child class must be at least as accessible or more accessible than the method on the parent class.
3. The method in the child class may not throw a checked exception that is new or broader than the class of any exception thrown in the parent class method.
4. If the method returns a value, it must be the same or a subclass of the method in the parent class, known as `covariant return types`.

**Redeclaring private Methods**
- In Java it is not possible to override a `private` method in a parent class since the parent method is not accessible from the child class.
- Just because the child class does not have access it does not mean the child class cannot define its own version of the method.
  - It just mean that the method on child class is not an overridden version.

**Hiding Static Methods**
- A `hidden method` occurs when a child class defines a static method with the same name and signature as a static method defined in a parent class.
- The four previous rules for overriding a method applies to `hidden methods`.
- It has an additional rule:
5. The method defined in the child class must be marked as `static` if it is marked as `static` in the parent class (method hiding). Likewise, the method must not be marked as `static` in the child class if it is not marked as `static` in the parent class (method overriding). 

**Overriding vs. Hiding Methods**
- `Overriding` a method the child method replaces the parent method in calls of both parent and child.
  ```
  public class Marsupial {
    public boolean isBiped() {
      return false;
    }
    public void getMarsupialDescription() {
      System.out.println("Marsupial walks on two legs: " + isBiped());
    }
  }
  
  public class Kangaroo extends Marsupial {
    public boolean isBiped() {
      return true;
    }
    public void getKangarooDescription() {
      System.out.println("Kangaroo hops on two legs: " + isBiped());
    }
    public static void main(String[] args) {
      Kangaroo joey = new Kangaroo();
      joey.getMarsupialDescription();     // Marsupial walks on two legs: true
      joey.getKangarooDescription();      // Kangaroo hops on two legs: true
    }
  }
  ```
  
- A `hiding` method only replace parent methods in the calls defined in the child class.
  ```
  public class Marsupial {
    public static boolean isBiped() {
      return false;
    }
    public void getMarsupialDescription() {
      System.out.println("Marsupial walks on two legs: " + isBiped());
    }
  }
  
  public class Kangaroo extends Marsupial {
    public static boolean isBiped() {
      return true;
    }
    public void getKangarooDescription() {
      System.out.println("Kangaroo hops on two legs: " + isBiped());
    }
    public static void main(String[] args) {
      Kangaroo joey = new Kangaroo();
      joey.getMarsupialDescription();     // Marsupial walks on two legs: false
      joey.getKangarooDescription();      // Kangaroo hops on two legs: true
    }
  }
  ```

**Creating final methods**
- `final` methods cannot be overridden or hidden.
- You can create a `final` method on parent class, but it cannot be overridden or hidden by the child class.

### Inheriting Variables
- Java does not allow variables to be overridden but instead hidden.
- When you hide a variable you define a variable with the same name as a variable in a parent class (it does not need to be `static` to be hidden).
- This creates two copies of the variable within an instance of the child class:
  - one instance defined for the parent reference;
  - another defined for the child reference.
- If you are referencing the variable from the parent class, the variable in the parent class is used.
- If you are referencing the variable from the child class, the variable in the child class is used.
- You can reference the parent variable using the `super` keyword. 
  ```
  public class Rodent {
    protected int tailLength = 4;
    public void getRodentDetails() {
      System.out.println("[parentTail=" + tailLength + "]");
    }
  }

  public class Mouse extends Rodent {
    protected int tailLength = 8;
    public void getMouseDetails() {
      System.out.println("[tail=" + tailLength + ",parentTail=" + super.tailLength+ "]");
    }
    public static void main(String[] args) {
      Mouse mouse = new Mouse();
      mouse.getRodentDetails();       // [parentTail=4]
      mouse.getMouseDetails();        // [tail=8,parentTail=4]
    }
  }
  ```

## Creating Abstract Classes
- Let's say we want to provide some reusable variable and methods in the parent class, whereas the developers provide specific implementations or overrides methods in the child class, but we don't want an instance of the parent class be instantiated.
- In Java you can accomplish this by using an `abstract class`.
- An `abstract class` is a class marked with `abstract` keyword and cannot be instantiate.

### Defining an Abstract Class
- An `abstract class` may include non-abstract methods and variables.
- An `abstract class` is not required to include any abstract methods.
- An `abstract method` may only be defined in an `abstract class`.
- The following class does not compile:
  - First method `swim()` does not compile because two braces are provided instead of semicolon.
  - Second method `getAge()` does not compile because it provides a body to an abstract method.
  ```
  public abstract class Turtle {
    public abstract void swim() {}    // DOES NOT COMPILE
    public abstract int getAge() {    // DOES NOT COMPILE
      return 10;
    }
  }
  ```
- Although you cannot provide a default implementation to an `abstract method`, you can still define a non-abstract method with a body, and the subclass has the option to override it. 
- An `abstract class` cannot be marked as `final`.
  - An `abstract class` cannot be instantiated, so the child class needs to extend it to be instantiated.
  - A `final class` cannot be extended (by definition), so marking an `abstract class` as `final` does not makes sense.
- It is the same for `abstract methods`, they cannot be marked as `final`.
- An `abstract method` cannot be `private`. It does not makes sense since you need access to this method on the child class.

### Creating a Concrete Class
- An `abstract class` becomes useful when it is extended by a `concrete class`.
- A `concrete class` is the first non-abstract subclass that extends an `abstract class`, and it is required to implement all inherited abstract methods.

### Extending an Abstract Class
- An `abstract class` can extend other `abstract class`, and in this case it is not required to provide implementations for any of the abstract methods.
- A `concrete class` that extends an `abstract class` must implement all inherited abstract methods.
  ```
  public abstract class Animal {
    public abstract String getName();
  }
  public abstract class BigCat extends Animal {
    public abstract void roar();
  }
  public abstract class Lion extends BigCat {
    public String getName() {
      return "Lion";
    }
    public void roar() {
      System.out.println("The Lion lets out a loud ROAR!");
    }
  }
  ```
- A `concrete class` is not required to provide an implementation for an `abstract class` only if the intermediate `abstract class` provides the implementation.
  ```
  public abstract class Animal {
    public abstract String getName();
  }
  public abstract class BigCat extends Animal {
    public String getName() {
      return "BigCat";
    }
    public abstract void roar();
  }
  public abstract class Lion extends BigCat {
    public void roar() {
      System.out.println("The Lion lets out a loud ROAR!");
    }
  }
  ```

**Abstract Class Definition Rules**
1. Abstract classes cannot be instantiated directly.
2. Abstract classes may be defined with any number, including zero, of abstract and non-abstract methods.
3. Abstract classes may not be marked as `private`, `protected` or `final`.
4. An abstract class that extends another abstract class inherits all of its abstract methods and its own abstract methods.
5. The first concrete class that extends and abstract class must provide an implementation for all of the inherited abstract methods.

**Abstract Methods Definition Rules**
1. Abstract methods may only be defined in abstract classes.
2. Abstract methods may not be declared `private` or `final`.
3. Abstract methods must not provide a method body/implementation in the abstract class for which it is declared.
4. Implementing an abstract method in a subclass follows the same rules for overriding a method.
  - The name and signature must be the same;
  - The visibility of the method in the subclass must be at least as accessible as the method in the parent class.

## Implementing Interfaces
- Java allow classes to implement any number of `interfaces`.
- An `interface` is an abstract data type that define a list of abstract public methods that any class implementing the interface must provide.
- An `interface` can also include a list of constant variables and defaults methods.
  ```
  public abstract interface CanBurrow {
    public static final int MINIMUM_DEPTH = 2;
    public abstract int getMaximumDepth();
  }
  ```
- An `interface` is not declared an abstract class although has many of the same properties of abstract class.
- The method modifiers in this example, `abstract` and `public`, are assumed. Whether or not you provide them, the compiler will automatically insert them as part of the method definition.
- A class may implement multiple interfaces:
  - If any of the interfaces defines an abstract methods, Elephant would be required to implement them.
  ```
  public class Elephant implements WalksOnFourLegs, HasTrunk, Herbivore {
  }
  ```
  
### Defining and Interface
- Rules to create an interface:
1. Interfaces cannot be instantiated directly.
2. An interface is not required to have any methods.
3. An interface may not be marked as `final`.
4. All top-level interfaces are assumed to have `public` or `default access`. They are assumed to be `abstract` whether this keyword is used or not.
5. All non-default methods in an interface are assumed to have modifiers `abstract` and `public` in their definition. 

### Inheriting an Interface
- Inheritance rules to extend an interface:
1. An interface that extends another interface, as well as an abstract class that implements and interface, inherits all of the abstract methods as its own abstract methods.
2. The first concrete class that implements an interface, or extends an abstract class that implements an interface, must provide an implementation for all of the inherited abstract methods.

- An `interface` may be extended using `extends` keyword.
- An `interface` may extend multiple interfaces.
  ```
  public interface HasTail {
    public int getTailLength();
  }
  
  public interface HasWhiskers {
    public int getNumberOfWhiskers();
  }
  
  public interface Seal extends HasTail, HasWhiskers {
  }
  ```

**Classes, Interfaces, and keywords**
- A class cannot extend an interface.
- An interface can extend another interface.
- An interface cannot implement another interface.

**Abstract Methods and Multiple Inheritance**
- If two abstract interface methods have identical behavior (the same method signature) creating a class that implements one of the two methods automatically implements the second method.
  ```
  public interface Herbivore {
    public void eatPlants();
  }
  
  public interface Omnivore {
    public void eatPlants();
    public void eatMeat();
  }
  
  public class Bear implements Herbivore, Omnivore {
    public void eatPlants() {
      System.out.println("Eating plants");
    }
    public void eatMeat() {
      System.out.println("Eating meat");
    }
  }
  ```
- If the two methods have different signature it is considered a method overload and then the class needs to implement both methods.
- If the two methods are the same and the return type are different the code will not compile.

### Interface Variables
1. Interface variables are assumed to be `public`, `static` and `final`. Therefore, marking a variable as `private`or `protected` will trigger a compiler error, as well as marking any variable as `abstract`.
2. The value of an interface variable must be set when it is declared since it is marked as `final`.

- Interface variables are essentially constant variables.

### Default Interface Methods
- A `default method` is a method defined within an interface with the `default` keyword in which a method body is provided.
- A `default method` defines an abstract method with a default implementation.
- Classes have the option to override the default method, but they are not required to do so.
  - If the class does not override the method, the default implementation will be used.

  ```
  public interface IsWarmBlooded {
    boolean hasScales();
    public default double getTemperature() {
      return 10.0;
    }
  }
  ```
- `Default method` rules are:
1. A `default method` may only be declared within an interface and not within a class or abstract class.
2. A `default method` must be marked with `default` keyword. If a method is marked as `default`, it must provide a method body.
3. A `default method` is not assumed to be `static`, `final`, or `abstract`, as it may be used or overridden by a class that implements the interface.
4. Like all methods in an interface, a `default method` is assumed to be `public` and will not compile if marked as `private` or `protected`.

- If a class implements two interfaces that have default methods with the same signature, the compiler will throw and error. 
  - But if the subclass overrides the method it compiles without issues.

### Static Interface Methods
- A `static method` defined in an interface is not inherited in any classes that implement the interface.
- `Static method` rules are:
1. Like all methods in an interface, as static method is assumed to be `public` and will not compile is marked as `private` or `protected`.
2. To reference the static method, a reference to the name of the interface must be used.
- Without an explicit reference to the name of the interface the code will not compile, even though Bunny implements Hop.
  ```
  public interface Hop {
    static int getJumpHeight() {
      return 8;
    }
  }
  
  public class Bunny implements Hop {
    public void printDetails() {
      System.out.println(getJumpHeight());  // DOES NOT COMPILE
                                            // should be Hop.getJumpHeight()
    }
  }
  ```
- A class that implements two interfaces containing static methods with the same signature will still compile at runtime, because static methods are not inherited by the subclass and must be accessed with a reference to the interface name.

## Understanding Polymorphism
- `Polymorphism` is the property of an object to take on many forms.
  - A Java object may be accessed using a reference with the same type as the object, a reference that is a superclass of the object, or a reference that defines an interface the object `implements`, either directly or through a superclass.
- A cast is not required if the object is being reassigned to a super type or interface of the object. 
  ```
  public class Primate {
    public boolean hasHair() {
      return true;
    }
  }
  
  public interface HasTail {
    public boolean isTailStriped();
  }
  
  public class Lemur extends Primate implements HasTail {
    public boolean isTailStriped() {
      return false;
    }
    public int age = 10;
    public static void main(String[] args) {
      Lemur lemur = new Lemur();
      System.out.println(lemur.age);                  // 10
      
      HasTail hasTail = lemur;
      System.out.println(hasTail.isTailStriped());    // false
      
      Primate primate = lemur;
      System.out.println(primate.hasHair());          // true
    }
  }
  ```
- The ability of an instance of Lemur be passed as an instance of an interface it implements as well as an instance of one of its superclass is the nature of polymorphism.
- Once the object has been assigned a new reference type, only the methods and variables available to that reference type are called without explicit cast.
  ```
  HasTail hasTail = lemur;
  System.out.println(hasTail.age);    // DOES NOT COMPILE
  ```

### Object vs. Reference
- All object are accessed by reference (you never have direct access to it).
- The object is an entity that exists in memory, allocated by Java.
- Regardless of the type of the object you have in memory, the object itself does not change.
- Since all objects inherit `java.lang.Object` they can all be reassigned to `java.lang.Object`. 
  ```
  Lemur lemur = new Lemur();
  Object lemurAsObject = lemur;
  ```
- Even though the Lemur object has been assigned a reference with a different type, the object itself has not changed (and exists as Lemur object in memory).
- What change is the ability to access methods within the Lemur class with the `lemurAsObject` reference.
  - Without an explicit cast back to Lemur, we no longer have access to Lemur properties of the object.
- So, the rules are:
1. The type of the object determines which properties exist within the object in memory.
2. The type of the reference determines which methods and variables are accessible to the Java Program. 

### Casting Objects
- The the previous example when we changed the reference type of the object we lost access to more specific methods. We can reclaim those references by casting back to the specific class.
  ```
  Primate primate = lemur;
  Lemur lemur2 = primate;   // DOES NOT COMPILE
  
  Lemur lemur3 = (Lemur)primate;
  System.out.println(lemur3.age); 
  ```
- Rules when casting variables:
1. Casting an object from a subclass to a superclass does not require an explicit cast.
2. Casting an object from a superclass to a subclass requires an explicit cast.
3. The compiler will not allow casts to unrelated types.
4. Even when the code compiles without issues, an exception may be thrown at runtime if the object being cast is not actually an instance of that class.

- Even though two classes share a related hierarchy, that does not mean an instance of one can automatically be cast to another.
- The below example throws an exception because the object being referenced is not an instance of Capybara class. 
  ```
  public class Rodent {
  }
  
  public class Capybara extends Rodent {
    public static void main(String[] args) {
      Rodent rodent = new Rodent();
      Capybara capybara = (Capybara)rodent; // Throws ClassCastException at runtime 
    }
  }
  ```

### Virtual Methods
- A `virtual method` is a method in which the specific implementation is not determined until runtime.
- All non-final, non-static, and non-private methods are considered virtual methods, since any of them can be overridden at runtime.
- What makes `virtual methods` special is that if you call a method on an object that overrides a method, you get the overridden method, even if the call to the method is on a parent reference or within the parent class.
  ```
  public class Bird {
    public String getName() {
      return "Unknown";
    }
    public void displayInformation() {
      System.out.println("The bird name is: " + getName());
    }
  }
  
  public class Peacock extends Bird {
    public String getName() {
      return "Peacock";
    }
    public static void main(String[] args) {
      Bird bird = new Peacock();
      bird.displayInformation();        // The bird name is: Peacock
    }
  }
  ```

### Polymorphic Parameters
- One useful application of polymorphism is the ability to pass instances of a subclass or interface to a method.
- You can define a method that takes an instance of an interface as a parameter. 
  - Any class that implements the interface can be passed to the method.
  - This property is referred to as `polymorphic parameters`.
  ```
  public class Reptile {
    public String getName() {
      return "Reptile";
    }
  }

  public class Alligator extends Reptile {
    public String getName() {
      return "Alligator";
    }
  }

  public class Crocodile extends Reptile {
    public String getName() {
      return "Crocodile";
    }
  }
      
  public class ZooWorker {
    public static void feed(Reptile reptile) {
      System.out.println("Feeding: " + reptile.getName());
    }
    public static void main(String[] args) {
      feed(new Alligator());        // Feeding: Alligator
      feed(new Crocodile());        // Feeding: Crocodile
      feed(new Reptile());          // Feeding: Reptile
    }
  }
  ```
- Polymorphism does not apply to `static` methods. So, invoking an overloaded method with parent class reference will cause invoking the parent class method itself.
- The above code behaves differently if `getName()` is `static` or `non-static`. 
  - It would print `Feeding: Reptile` for all 3 lines.
  
### Overload versus Override
| Property      | Overload                                | Override                                                           |
| ------------- | --------------------------------------- | ------------------------------------------------------------------ |
| Method Name   | Must be the same name                   | Must be the same name                                              |
| Argument List | Must be different                       | Must be the same                                                   |
| Return Type   | Can be different                        | Must be the same or covariant returns                              |
| Exceptions    | Can be different                        | Cannot throw new checked exceptions. Can narrow exceptions thrown. |
| Access Level  | Can be different                        | Must be the same or less restrictive                               |
| Invocation    | Reference type determines which version | Object type determines which version                               |

### Abstract versus Interface
| Abstract                                                                                                                                                   | Interface                                                                                                                              |              
| ---------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------|
| An abstract class can extend only one class or one abstract class at a time                                                                                |	An interface can extend any number of interfaces at a time                                                                            |
| An abstract class can extend another concrete (regular) class or abstract class	                                                                           | An interface can only extend another interface                                                                                         |
| An abstract class can have static, final, static final or non-static and non-final variable with any access specifier	                                     | An interface can only have public static final (constant) variable                                                                     |
| An abstract class can have both abstract and concrete methods	                                                                                             | An interface can have abstract methods, static methods or default methods                                                              |
| In an abstract class is mandatory to declare a method as an abstract (must have keyword “abstract”)	                                                       | In an interface is optional to declare a method as an abstract (keyword “abstract” is implicit - except for static or default methods) |
| An abstract class can have default (package private), protected and public abstract methods (cannot be private). Only non-abstract methods can be private. | An interface can have only have public methods (either abstract, default or static).                                                   |
	
**Similarities**	
- Abstract classes and interfaces cannot be instanciated.	
- As a top-level Abstract class or interface cannot be marked as private or protected.	
- Abstract classes and interfaces cannot be marked as final.	
- An Abstract methods cannot be marked as private or final.	  