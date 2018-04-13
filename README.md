# Coding assignment

For Eclipse use "sbt eclipse" command in order to generate configuration files.  
With sbt do as usual: sbt run or sbt package then go to the target folder  

## How it works
The program will ask you for the exercise number.  
After giving this number, it will generate a csv file corresponding to the result of the exercise.
The file **transaction.txt** must be in the same folder than the package jar.  

## Exercise trait
Each class that extends this trait must implement three methods:  
- *solve* method that return the result a the exercise
- *write* method that takes the result of solve method to save it in a file
- *statement* method that describe the exercise
