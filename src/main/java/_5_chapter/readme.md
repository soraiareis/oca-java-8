# Chapter 5 - Class Design

## Introducing Class Inheritance
- `Inheritance` is the process by which the new child subclass automatically includes any public or protected primitives, objects, or methods defined in the parent class.
- Java supports `single inheritance`, by which a class may inherits from only one direct parent class.
- Java also support multiple levels of inheritance, by which one class ,ay extend another class, which in turn extends another class.
- You can extend a class any number of times, allowing each descendent to gain access to its ancestor's member.
- Java does allow one exception to the `single inheritance` rule: classes may implement multiple interfaces.
- It is possible to prevent a class from being extended by marking the class with the `final` modifier.

### Extending a Class
- You can extend a class adding the parent class name in the definition using the `extends` keyword.

### Applying Access Modifiers
- You can apply access modifiers to classes.
- The `public` access modifier applied to a class indicates that it can be referenced and used in any class.
- The `default package private` modifier indicates the class can be accessed only by a class within the same package.

### Creating Java Objects
- All classes inherit from `java.lang.Object` (it is the only class that does not have a parent class).

### Defining Constructors
- The first statement of every constructor is either a call to another constructor within the class, using this(), or a call to a constructor in the direct parent class, using super().
- Like the `this()` command, the `super()` command may only be used as the first statement of the constructor.
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
4. If the parent does not have no-argument constructor and the child does not define any constructors, the compiler will throw and error and try to insert a default no-argument constructor into the child class.
5. If the parent does not have no-argument constructor, the compiler requires an explicit call to a parent constructor in each child constructor.

**Calling Constructors**
- The parent constructor is always executed before the child constructor. 

### Calling Inherited Class Members
- Java classes mayb use any `public` or `protected` member of the parent class, including methods, primitives, or objects references.
- If the parent class and child class are part of the same package, the child may also use any default members defined in the parent class.
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
Page 246



### Inheriting Variables




## Creating Abstract Classes

## Implementing Interfaces

## Understanding Polymorphism