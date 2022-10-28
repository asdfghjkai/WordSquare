# WordSquare
A simple Java 11 program, for producing wordsquares

## Usage
Execute as follows
'java -jar wordsquare.jar val1 val2'

Single output to cmdline

## Impl
Something here

# Working Notes
Manually trace out in excel, notice pattern
1 2 3 4
  5 6 7
    8 9
      10
where `[i][j] == [j][i]`

```
int x = 1;
        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j++) {
                nums[i-1][j-1] = x;
                nums[j-1][i-1] = x;
                x++;
            }
        }
```