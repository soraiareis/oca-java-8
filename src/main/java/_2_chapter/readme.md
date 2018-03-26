# Chapter 2 - Operators and Statements

## Understanding Java Operators

- `Operator` is a special symbol that can be applied to a set of variables, values or literals and that returns a result.
- Type of `operators`:
  - unary: one operand
  - binary: two operands
  - ternary: three operands
- Java operators are not necessarily evaluated from left-to-right order, for example:
  ```
  int y = 4;
  double x = 3 + 2 * --y;
  ```
  - In this example, it first decrement 1 from y (then y = 3), and then multiply by 2, and finally add 3.
- Unless overridden with parentheses, Java operators follow order of operation. If two operators are on the same level, then Java guarantees left-to-right evaluation.

| Operator                                | Symbols and examples                               |
| --------------------------------------- | -------------------------------------------------- |
| Post-unary operators                    | expression++, expression--                         |
| Pre-unary operators                     | ++expression, --expression                         |
| Other unary operators                   | ~, +, -, !                                         |
| Multiplication/Division/Modulus         | *, /, %                                            |
| Addition/Subtraction                    | +, -                                               |
| Shift operators                         | <<, >>, >>>                                        |
| Relational operators                    | <, >, <=, >=, instanceof                           |
| Equal to/not equal to                   | ==, !=                                             |
| Logical operators                       | &, ^, \|                                           |
| Short-circuit logical operators         | &&, \||                                            |
| Ternary operators                       | boolean expression ? expression1 : expression2     | 
| Assignment operators                    | =, +=, -=, *=, /=, %=, &=, ^=, \|=, <<=, >>=, >>>= |

**MEMORIZE THIS TABLE FOR EXAM**

## Working with Binary Arithmetic Operators

- They can be used to perform mathematical operations on variables, create logical expression, as well as perform basic variable assignments.

### Arithmetic Operators
- `Arithmetic operators` Are often encountered in early mathematics and include:
  - addition +
  - subtraction -
  - multiplication *
  - division /
  - modulus % = remainder operator = remainder of two values (11 % 2 is 2) 
- Also includes ++ and --
- All `arithmetic operators` may be applied to any primitive, except boolean.
- Only + and += may be applied to String, which results in concatenation.

### Numeric Promotion Rules
* If two values have different data type, Java will automatically promote one of the values to the larger of the two data types;
* If one of the values is integral and the other is floating-point, Java will automatically promote the integral to floating-point;
* Smaller data types, namely `byte`, `short`, and `char`, are first promoted to `int` any time they are used with a **binary** arithmetic operator, event if neither of the operands is int;
* After all promotions has occurred and the operands have the same data type, the resulting value will have the same data type as its promoted operands.

## Working with Unary Operators
- A `unary operator` is one that requires exactly one operand, or variable, to function.

| Unary operator | Description                                             |
| -------------- | ------------------------------------------------------- |
| +              | Indicates a number is positive                          |
| -              | Indicates a number is negative or negates an expression |
| ++             | Increments a value by 1                                 |
| --             | Decrements a value by 1                                 |
| !              | Inverts a Boolean's logical value                       |

### Increment and Decrement Operators
- `Pre-increment or Pre-decrement operator`: the operator is applied first and the value return is the new value.
- `Post-increment or Post-decrement operator`: the original value of the expression is returned, with operator applied after the value is returned.

## Using Additional Binary Operators

### Assignment Operators
- An `assignment operator` is a binary operator that modifies, or assigns, the variable with the result.
- The simplest assignment operator is =.
- Java will automatically promote from smaller to larger data types, and will throw an exception if detects you are trying to convert larger to smaller.
  - Examples (they do not compile):
    - int x = 1.0;
    - short y = 1921222
    - int z = 9f;

### Casting Primitive Values
- We can fix the previous samples by casting the results to a smaller data type.
  - Examples (now they compile):
    - int x = (int)1.0;
    - short y = (short)1921222
    - int z = (int)9f;
    
### Compound Assignment Operators
- Only two `compound assignment operators` are required on the exam: `+=` and `-=`.
- The arithmetic operation is applies the left- and right-hand side and stores the value on the left-hand side.
  ```
  int x = 2, z = 3;
  x = x * z;
  x += z; // same as x = x + z
  ```
- It only applies to already defined variables and cannot be used to declare a new variable.
- `Compound operators` save us from having to explicitly cast a value, for example:
  ```
  long x = 10;
  int y = 5;
  y = y * x; // DOES NOT COMPILE
  ```
  ```
  long x = 10;
  int y = 5;
  y *= x; // IT COMPILES - It is automatically casted
  ```
- The result of the assignment is an expression in and of itself, for example:
  ```
  long x = 5;
  long y = (x=3);
  System.out.println(x); // Outputs 3
  System.out.println(y); // Also, outputs 3
  ```

### Relational Operators
- `Relational operators` compare two expressions and return a boolean value.
- We can use `<` (less than), `<=` (less than or equal to), `>` (greater than) or `>` (grater than or equal to), are applied to primitive data types only.
- If two numeric operands are not of the same type, the smaller one is promoted to the larger.
- The other `relational operator` is `instanceof` which is applied to object references and classes or interfaces.
- `A instanceof B`: It returns `true` if `A` points to a instance of class, subclass, or an interface `B`.
- `instanceof` is out of scope of OCA.

### Logical Operators
- `Logical operators` (&, |, ^) may be applied to both numeric and boolean data types.
- When applied to `numeric` they are referred to as `bitwise operators`.
- When applied to `boolean` they are referred to as `logical operators`.

**x & y (AND)** 
|           | y = true | y = false |
| --------- | -------- | --------- |
| x = true  | true     | false     |
| x = false | false    | false     |

**x | y (INCLUSIVE OR)** 
|           | y = true | y = false |
| --------- | -------- | --------- |
| x = true  | true     | true      |
| x = false | true     | false     |

**x ^ y (EXCLUSIVE OR)** 
|           | y = true | y = false |
| --------- | -------- | --------- |
| x = true  | false    | true      |
| x = false | true     | false     |

- Tips to remember this table:
  - AND is only `true` if both operands are `true`
  - Inclusive OR is only `false` if both operands are `false`
  - Exclusive OR is only `true` if the operands are different
  
- `Short-circuit operators` (&& and ||): are identical to logical operators, except that the right-hand side of the expression is never evaluated if the final result can be determined by the left-hand side.
  ```
  boolean x = true || (y < 4)
  ```
  
### Equality Operators
- There is a semantic difference between `two objects are the same` and `two objects are equivalent`.
- For numeric and boolean primitives, there is no such distinction.
- `Equals operator` (==) and `not equals operator` (!=) compare two operands and return a boolean value.
- Can be used in:
  - Comparing two numeric primitive values. If the operands are not of the same type, the smaller one is promoted to the larger.
  - Comparing two boolean values.
  - Comparing two objects, including `null` and `String` values.
- For object comparison the equality operator is applied to the references of the objects, not the objects itself. Two references are equal if and only if they point to the same object, or both point to null.
  ```
  File x = new File("myFile.txt");
  File y = new File("myFile.txt");
  File z = x;
  System.out.println(x == y); // Outputs false
  System.out.println(x == z); // Outputs true
  ```

## Understanding Java Statements
- A Java `statement` is a complete unit of execution terminated with a semicolon (;).
- `Control flow statements` break up the flow of execution by using decision making, looping and branching, allowing the application to selectively execute particular segments of the code.
- Can be applied to a singles expression as well as a block of code.

### The `if-then` and `if-then-else` Statements
- The `if-then` allows an application to execute a particular block of code if and only if a boolean expression evaluates to true.
- For readability, it is a good practice to put blocks ({}) always on control flow statements, although it is not required.
- We can append additional `if-then` statements to an `else` when we want to expand.
- In the `if-then-else` process will continue the execution until encounters an `if-then` statement that evaluates to true.
- Important:
  - The `if-then` needs to be a boolean expression, otherwise it won't compile.
    ```
    int x = 1;
    if (x) { // DOES NOT COMPILE
      ...
    }
    ```
  - Be wary of assignment operators being used as if they were equals == operators.
    ```
    int x = 1;
    if (x = 5) { // DOES NOT COMPILE
      ...
    }
    ```
    
### Ternary Operator
- The `ternary operator` is the only operator that takes three operands:
  ```
  boolean expression ? expression1 : expression2 
  ```
- For readability, it is helpful to add parentheses around expressions.
- There is no requirement that the second and third expressions have the same data type, although it may come to play when combined with the assignment.
- Only one of the right-hand expressions will be evaluated at runtime. 
- If one of the two right-hand side expressions performs a side effect, then it may no be applied.

### The `switch` Statement
- A `switch` statement is a complex decision-making structure in which a single value is evaluated and flow is directed to the first matching branch, known as `case` statement.
- If no such `case` statement is found that matches the value, an optional `default` is called.
- If no such `default` option is available, the entire `switch` statement will be skipped.
  ```
  switch (variableToTest){
    case constantExpression1: // may contain 0 or more cases
      // code
      break; // optional
    case constantExpression2:
      // code
      break;
    default:
      //code
  } 
  ```
- Data types supported (**must know for the exam**):
  - byte and Byte
  - short and Short
  - char and Character
  - int and Integer
  - enum values

- The values in each `case` statement must be compile-time constant values of the same data type as the `switch` value. 
  - This means you can only use `literals`, `enums` or `final constant variables`. 
  - If a variable is final, but been passed as a parameter, it is not considered constant and therefore not valid. 
- If `break;` is being use in each case, the order of the cases does not matter. However, without `break;` statements the order matters since after an execution of one `case`, it will go automatically to the next case and continues until it reaches a `break;` or the end.
 

### The `while` Statement
- A repetition control structure (loops) execute a statement of code multiple times in succession.
- By using non-constant variables, each repetition may be different.
- The simplest repetition control structure in Java is `while`.
  ```
  while (booleanExpression){
    // code
  } 
  ```
- The boolean expression is evaluated before each iteration and exits when the expression returns `false`.

### The `do-while` Statement
- Unlike a `while`, a `do-while` guarantees that the statement will be executed at least once.
- The primary difference between `while` and `do-while`, is the a `do-while` reinforce that the statement will be executed before the expression is ever evaluated.

### The `for` Statement
- A `for` statement looks like this:
  ```
  for (initialization; booleanExpression; updateStatement){
    // code
  } 
  ```
- The `initialization` and the `updateStatement` sections may contain multiple statements, separated by commas.
- Variables declared in the `initialization` have limited scope: only inside the `for` loop.
- Alternatively, variables declared before the loop but assigned a value inside the `initialization` may be used outside.
- The `booleanExpression` is evaluated on every iteration of the loop `before` the loop executes.
- The `updateStatement` is executed after the loop block of code is done.

**SITUATIONS ON THE EXAM**

- **Creating an Infinite Loop**
  ```
  for ( ; ; ){
    System.out.println("Hello World");
  } 
  ```
  - Although it seems it will throw a compiler error, this in fact will compile and print same statement over and over.
  - The components of a `for` are optional, but the semicolons are required. This will not compile: `for (  )`.

- **Adding Multiple Terms to the `for` Statement**
  ```
  int x = 0;
  for (long y = 0, z = 4; x < 5 && y < 10; x++, y++){
    System.out.println(y + " ");
  } 
  ```

- **Redeclaring a Variable in the Initialization Block**
  ```
  int x = 0;
  for (long y = 0, x = 4; x < 5 && y < 10; x++, y++){ // DOES NOT COMPILE
    System.out.println(x + " ");
  } 
  ```
  - This scenario does not compile because `x` is being redeclared in the initialization.
  - Removing the declaration on the first line or moving the `y` declaration to outside should work.

- **Using Incompatible Data Types in the Initialization Block**
  ```
  for (long y = 0, int x = 4; x < 5 && y < 10; x++, y++){ // DOES NOT COMPILE
    System.out.println(x + " ");
  } 
  ```
  - In this scenario it does not compile because the variables in the initialization needs to be of the same data type.

- **Using Loop Variables Outside the Loop**
  ```
  for (long y = 0, x = 4; x < 5 && y < 10; x++, y++){ 
    System.out.println(y + " ");
  } 
  System.out.println(x); // DOES NOT COMPILE
  ```
  - This scenario does not compile because `x` variable is declared inside the loop and cannot be used outside.

### The `for-each` Statement
- A `for-each` statement looks like this:
  ```
  for (datatype instance : collection){
    // code
  } 
  ```
- The `collection` must be a built-in Java array or an object whose class implements `java.lang.Iterable`, which includes most of the Java Collections framework (List and ArrayList).
- The `datatype instance` must be a declaration for an instance of a variable, whose type must matches the type of a member of the `collection`.
  
## Understanding Advanced Flow Control

### Adding Optional Labels
- `if-then` statements, `switch` statement and `loops` can all have optional labels.
- A label is an optional pointer to the head of a statement that allow applications to jump to it or break from it.
- It is a single word proceeded by a colon (:).
  ```
  int[][] myComplexArray = {{5,2,1,3},{3,9,8,9},{5,7,12,7}};
  OUTER_LOOP: for (int[] mySimpleArray : myComplexArray) {
    INNER_LOOP: for (int i = 0; i < mySimpleArray.length; i++) {
      System.out.print(mySimpleArray[i] + "\t");
    }
    System.out.println();
  }
  ```
- This is rarely considered good coding practice.

**This topic is not on OCA exam.**
  
### The `break` Statement
- A `break` statement transfers the flow control to the enclosing statement. It will not execute the loop anymore.
- A `break` statement looks like this:
  ```
  optional_label: while (booleanExpression){
    // code
    break optional_label;
  } 
  ```
- Notice that a `break` statement can take an optional label parameter.
- Without a label parameter, the `break` statement will terminate the nearest inner loop it is currently in the process of executing.
- With a label parameter it will jump to the label line.

### The `continue` Statement
- A `continue` statement causes flow to finish the execution of the current loop. It finishes the current iteration of the loop, but continues in the loop for next iteration.
  ```
  optional_label: while (booleanExpression){
    // code
    continue optional_label;
  } 
  ```
- Without a label parameter, the `continue` statement is applied to the nearest inner loop under execution.
- You can use labels to override this behavior.

|          | Allows optional labels | Allows unlabeled break | Allows continue statement |
| -------- | ---------------------- | ---------------------- | ------------------------- |
| if       | Yes                    | No                     | No                        |
| while    | Yes                    | Yes                    | Yes                       |
| do while | Yes                    | Yes                    | Yes                       |
| for      | Yes                    | Yes                    | Yes                       |
| switch   | Yes                    | Yes                    | No                        |