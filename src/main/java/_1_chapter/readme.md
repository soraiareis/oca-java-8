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
