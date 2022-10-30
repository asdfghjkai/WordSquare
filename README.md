# WordSquare
A simple Java 11 program, for calculating WordSquares from a given String.

## Explanation
WordSquares are grids, of n*n dimensions, where n is the length of the words within, whereby the word in the first row is equal to the word in the first column, and so on for the remaining columns.

For example, with the input `4 eeeeddoonnnsssrv` the following square would be produced.
```
rose
oven
send
ends
```

And so on and so forth, other examples can be found in the [WordSquareTestCase](src/test/java/uk/co/kaichance/wordsquare/algorithm/WordSquareTestCases.java) class, alongside solutions. 
Note that test case 3 in particular has more than one solution, this application only finds the first.

Note: There might not be only one solution to provided characters! Source dictionaries are sorted on load, and therefore should be consistent.

## Usage
Execute as follows

`java -jar wordsquare.jar 4 eeeeddoonnnsssrv dictionary.txt array`

Output is to the command line. Both the dictionary and array arguments are optional, but to input the latter, the former is required, as no advanced option parsing is included.

| Argument         | Description                                                                                                                 |
|------------------|-----------------------------------------------------------------------------------------------------------------------------|
| java             | Call the JVM                                                                                                                |
| -jar             | Flag to inform the JVM we will be providing a JAR                                                                           |
| wordsquare.jar   | Name of the JAR as provided in 'releases'                                                                                   |
| 4                | Number of characters in the desired word output (Equal to sqrt of number of letters provided)                               |
| eeeeddoonnnsssrv | Wordsquare text to solve against                                                                                            |
| dictionary.txt   | (Optional) Alternative dictionary name                                                                                      |
| array            | (Optional*) Specifies the mode for the WordGrid to operate in. 'array' uses arrays to back the grid, no argument uses a map |
To view diagnostic outputs, alter [logback.xml](src/main/resources/logback.xml)

## Requirements
When building code, ensure the following
- IDE has Lombok plugin enabled
- IDE has annotation processing enabled


## Implementation
### Tools and Libraries
- Apache Commons Collections
- Apache Commons Lang
- Slf4j
- Logback
- Lombok
- JUnit Jupiter
- JProfiler
- IntelliJ IDEA Community
- Sonarlint Plugin for IntelliJ

The implementation uses a depth first recursive solve, whereby the inputs are limited as we traverse further in the depths,
to only allow a dictionary of applicable words to be used, to be able to resolve performance. Staircasing down the depths towards a solution, 
and then stopping the recursion when exceeding max depth, or on solution.

# Steps Taken To Discovering Solution
- Limit input dictionary to only desired output word lengths. Stream inputs to avoid load on GC, and avoid traversing more than once.
- Limit dictionary on a depth basis, to further reduce the dataset used for solving, at the expense of filtering
- Where `[i][j] == [j][i]`, and only one character is required where `i == j`
- Craft validation around the above rule, and use this to filter acceptable results
- Ensure the application is tolerant to bad arguments passed
- Ensure the application is tolerant to configurable logging
- Implement an alternative implementation, using a map versus 2D arrays to solve
- Use a parallel stream, the cost of merging streams is negated by performance improvements
- When streaming, following inspection with JProfiler, utilise StringUtils.countMatches within the stream in CoreAlgorithm over creating another structure and iterating twice, to improve performance
- Refactor, add JavaDoc coverage, and testing coverage.
- Write README

## Testing Coverage
All tests passing - coverage at 100% class, 97% method, and 90% line.