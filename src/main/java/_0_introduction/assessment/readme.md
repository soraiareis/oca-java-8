1- A compile error happen when a String is not initiated ("String a_b;").

2- String Literal (String str = "Java";) x String Object (String str = new String("Java");): The first uses the internal pool of string objects. If there already exists a string value "Java", then str will reference of that string and no new String object will be created.

3- A method cannot have a more restrictive access (i.e., protected) than the interface (i.e., public).

3- Abstract classes cannot be instantiated.

4- while (count++ < 3): first evaluates "count < 3" and after increments count with 1.

8- If a class A extends another class B. The constructor of A calls the constructor of B.

8- If a method on the parent class is private, the parent method will be executed and not the one overridden.

9- Only checked exceptions are required to be handled or declared.

9- Runtime exceptions are commonly thrown by both the JVM and programmer code.

9- Checked exceptions are usually thrown by programmer code. Errors are intended to be thrown by JVM (while a programmer could throw, this is not recommended).

18- LocalDate is static to create a new date (LocalDate.of(y, m, d)) instead of constructor (new LocalDate). Old Calendar constants starts months with zero and new Month constants starts with one. 

 