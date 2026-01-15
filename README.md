The project is built using Maven.
To compile the source code, run all unit tests, and build the library, execute the following command from the project root directory:

mvn clean install

This command performs the following steps:
Compiles all Java source files
Executes the complete unit test suite
Generates the library JAR file

File Format

The library processes flat text files with the following characteristics:
Each line in the file represents a single record
Fields within a record are separated by a delimiter
The delimiter is defined using the @FileSource annotation on the target POJO class
Fields are mapped to object properties using the @Column annotation

Supported Delimiters

Pipe (|)
Comma (,)
Semicolon (;)

Sample Files
transactions.txt
T1|100|2024-01-01
T2|99999|2024-01-02

customers.txt
John,john@mail.com,25
Maria,wrongmail,200

audits.txt
192.168.1.1;3
999.1.1;7
