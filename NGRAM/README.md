# NGRAM Generator

>  a simple command line utility that reads in strings from standard input and 
> produces a list of all the ngrams contained in the input. An ngram is a 
> length-n sequence of characters, sometimes used for language modeling or other 
> kinds of text analysis. 

## Example 

`echo -n "Hello" | ./ngram 1`

```
H
e
l
l
o
```

`echo -n "213 is great" | ./ngram 2`

```
21
23
3 
 i
is
s 
 g
gr
re
ea
at
```

## Reference

[NGRAM
Generator](https://www.cs.grinnell.edu/~curtsinger/teaching/2019S/CSC213/assignments/ngram/)
