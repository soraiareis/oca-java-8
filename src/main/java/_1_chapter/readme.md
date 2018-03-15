# Chapter 1 - Java Building Blocks

- Java building blocks: `class`, `interface` and `enum` (not part of OCA).
- An `object` is a runtime instance of a `class` in memory.
- All `objects` instantiated represents the state of the program.
- Classes have `fields` and `methods` as members.

## Comments
- Comments (do not appear on OCA exam)
  - Single-line comment: `// sigle-line comment`
  - Multiple-line comment: 
    ```
    /* Multiple-line 
     * comment
     */
    ```
  - Javadoc comment: 
    ```
    /** (the difference from multiple-line is the double asterisc) 
     * Javadoc multiple-line comment 
     * @author Soraia
     */
    ```

## Classes vs. Files
- Java does not require that the class be `public`.
- It is possible to put two classes in the same file. 
  - If you do so, at most one of the classes in the file is allowed to be public.
  - If there is a public class it needs to match the filename.
  
## main() Method
- The result of a file `.java` compilation is a file of `bytecode` with the same name but with `.class` extension.
- Each file can contain only one class.
- The filename must match the class name, including case, and have `.java` extension.
- If a `main()` method is not present the process will throw an error `Main method not found ...`.
- If a `main()` method is present but is not `static` Java will throw an error as well.
- The `main()` method can receive the following as a parameter list:
  - String[] args
  - String args[]
  - String... args
- When trying to read a non-existent position in an array Java throws: `java.lang.ArrayIndexOutOfBoundsException`.

## Packages and Imports

- Packages are logic group of classes.
- Import statements tells Java which packages to look in for classes.
- When you have a line of code referencing a class not imported, Java compiler gives the error: `<class> cannot be resolved to a type`.
- Wilcards (*) are used to import all classes of a package. It does NOT import child packages, fields or methods. It imports only classes (there is a special case for static imports).
- Adding so many classes using wildcards does NOT slow down the program. The compiler check it out what is actually needed.
- A special package called `java.lang` is automatically imported (do NOT need to be imported). This packages hold System class, for example.
- When the classes is found in two packages Java compiler gives the error: `The type <class> is ambiguous`.
  ```
  import java.util.*;
  import java.sql.*;
  
  public class Conflict {
    Date date; // COMPILER SHOWS AN ERROR HERE
    // Some more code
  } 
  ```
- In the above case if we explicit say which Date we want we can still import the other package. **If you explicitly import a class name, it takes precedence over any wildcards.**
  ```
  import java.util.Date; // NOW THE COMPILER DO NOT SHOW AN ERROR
  import java.sql.*;
  
  public class Conflict {
    Date date;
    // Some more code
  } 
  ```
- If you have two imports with the same class name Java compiler gives the error: `The import <import> collides with another import statement`.
  ```
  import java.util.Date; 
  import java.sql.Date; // COMPILER SHOWS AN ERROR HERE
  
  public class Conflict {
    Date date;
    // Some more code
  } 
  ```
- If you really need to use two classes with same name, pick one to use in the import and use the other's fully qualified class name.
  ```
  import java.util.Date; 

  public class Conflict {
    Date date;
    java.sql.Date sqlDate;
  } 
  ```
- You can tell that a class is on `default` package because it does not have a package name.
- Why we need packages? Avoid naming conflicts and allow others reuse.

### Coding Format on Exam

- If the exam isn't asking about imports the line numbers will NOT begin with 1.
  ``` 
   6: public void method(ArrayList list) {
   7:   if (list.isEmpty()){
   8:     System.out.println("e");
   9:   } else {
  10:    System.out.println("n");
  11:   }
  12: } 
  ```
- If you see line number 1 or no lines number it means you have to make sure imports aren't missing.
- The exam will let you know what package classes are in unless they are covered in the objectives.
- You will be expected to know that `ArrayList` is in `java.util`.
- Some code will not have the main() method. In this case assume the main() method, class definition, and all necessary imports are present.

## Creating Objects

- An Object is an instance of a Class. To create an instance of a class you just need: `Class class = new Class();`.
- Constructors are a special type of method that creates a new object.
- The name of the constructor matches the name of the class and there is no return type.
  ``` 
   public class Chick {
    public Chick() {} // This is right
    public ChickConstructor() {} // Wrong
    public void Chick() {} // Wrong
   }
  ```
- The main purpose of a constructor is to initialize fields. But you can also initialize a field directly on line.
  ``` 
   public class Chicken {
    int numEggs = 0; // On line
    String name;
    public Chicken() {
      name = "Duke"; // On constructor
    }
   }
  ``` 
- For most cases you don't need to declare a constructor. There is one scenario that will be explain in Chapter 5.
- It is possible to read and write on fields directly from the caller like: `chicken.numEggs = 0;`.
- `Code block` is a code between braces {}.
- Code blocks outside a method are called `instance initializers`.
- Order of initialization:
  - `Fields` and `instance initializers` are run in the ORDER THEY APPEAR in the file.
  - The `constructor` runs AFTER all `fields` and `instance initializers` have run.
  
## Distinguish Between Object References and Primitives

### Primitive Types

- Java has 8 built-in data types refered as `Primitive Types`. 
- They are building blocks fo Java objects, because Java objects are a complex collection of primitive data types.
- For the exam you need to know the relative sizes and what can be stored in each one of them.

| Keyword       | Type                        |
| ------------- | --------------------------- |
| boolean       | true or false               |
| byte          | 8-bit integral value        |
| short         | 16-bit integral value       |
| int           | 32-bit integral value       |
| long          | 64-bit integral value       |
| float         | 32-bit floating-point value |
| double        | 64-bit floating-point value |
| char          | 16-bit Unicode value        |

- A float requires the letter f following the number (i.e., 123.45f) so Java knows it is float.
- You should know a byte can hold a value from -128 to 127.
  - A byte is 8 bits = 2ˆ8 = 256 values
  - Since we have 0 (zero) included, Java takes from positive side.
- When a number is present in the code it is called a `literal`. By default Java understands numbers as an `int`.
  ``` 
   long max = 3123456789; // DOES NOT COMPILE 
  ``` 
  - This does not compile because the number is bigger than an `int`. 
  - You need to explicit that the number is not an `int` and it is a `long` with the lette L in the end.
  ``` 
     long max = 3123456789L; // Works now 
  ``` 
- The is a way to change the base of numbers:
  - octal (digits 0-7): prefix is `0`, for example `017`
  - hexadecimal (digits 0-9 and letters A-F): prefix is `0x` or `0X`, for example `0xFF` 
  - binary (digits 0-1): prefix is `0b` or `0B`, for example `0b10` 
- You can have underscores in numbers to make them easier to read: 
  ``` 
     int million1 = 1000000;
     int million2 = 1_000_000; 
  ``` 
- Underscores can be added anywhere except:
  - beginning of a literal = `_1000.00`;
  - end of a literal = `1000.00_`;
  - right before a decimal point = `1000_.00`;
  - right after a decimal point = `1000._00`.

### Reference Types

- `Reference types` refers to an object.
- Unlike `primitive types` (that holds their values in memory), references do not hold the value of the object they refer to.
- `Reference types` points to an object by storing the memory address where the object is located = `pointer`.
- A value is assigned to a reference in one of the two ways:
  - assigned to another object of the same type (`String greeting = "How are you?";`);
  - assigned to a new object using the new keyword (`Date today = new Data();`);
  
### Primitive vs. Reference

- Reference types can be assigned null. Primitive types will give an error if you attempt to assign null;
- Reference types can be used to call methods. Primitive types do not have methods declared.  
- Primitive types begin with lowercase and reference types, since they are classes, they start with uppercase.

## Declaring and Initializing Variables

- You can declare and initialize multiple variables in the same line if they are from same type: `String s3 = "yes", s4 = "no";`
- Java has rules regarding `identifiers`:
  - It cannot start with numbers;
  - Special characters are not allowed, such as `@`, `*`;
  - The name must begin with a `letter` or the symbol `$` or `_`;
  - Subsequent characters may also be numbers; 
  - You cannot use Java reserved words.
  
## Understanding Default Initialization of Variables

**Local Variable**
- A `local variable` is a variable defined within a method.
- Must be initialized before use.
- Do not have default value.
- Contains garbage data until initialized.
- The compiler will not allow read uninitialized value, and show an error: `Variable <var> might not have been initialized`.

**Instance and Class Variable**
- `Instance variables` are also called fields.
- `Class variables` are shared across multiple objects. You can tell a variable is a class variable because it has the keyword `static`.
- They are not required to be initialized. 
- As soon as they are declared they receive default value.
  - `null` to objects and `0/false` to primitives.

| Variable Type                           | Default Initialization Value |
| --------------------------------------- | ---------------------------- |
| boolean                                 | false                        |
| byte, short, int, long                  | 0                            |
| float, double                           | 0.0                          |
| char                                    | '\u0000' (NUL)               |
| All object references (everything else) | null                         |

*MEMORIZE THIS TABLE FOR EXAM* 