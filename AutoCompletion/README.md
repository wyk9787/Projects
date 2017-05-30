# Auto Completion

## Description

- Using AVL Balanced Binary Search Tree to implement the the auto completion funationality like the one in the terminal 

## Install
- Need to install ncurses library

## Usage
- To build the program, run `make` and `./autocomplete`. 

- Start typing to see autocomplete suggestions. If you hit enter you will get a clear line. If you hit enter with a blank line or press ctrl-C the program will quit.


## Acknowledgement

- Charlie Curtsinger  


<!---->
<!--# History-based autocomplete-->
<!--For this challenge problem, you will write a command auto-complete system. The provided code implements a simple terminal-like input system that reads input one character at a time. After each character, the main program calls the `get_suggestions` function to get a list of suggested completed commands. After you type a line and hit the enter key, the line is added to the history and should can be used as a suggestion in the future. Your task is to implement the `add_word` and `get_suggestions` functions stubbed out in autocomplete.c. You should not change main.c.-->
<!---->
<!--## How to make suggestions-->
<!--The `get_suggestions` function should return commands in the history that start with the line that has been typed in so far. For example, the partial input "Hel" should produce suggestions "Hello" and "Hello World!", assuming these two lines are in our history. You do not need to save history across runs of the program.-->
<!---->
<!--You must implement your suggestion search in a way that can handle a large number of commands in the history. A linear search over a linked list of previous commands will not be acceptable!-->
<!---->
<!--## Building, running, and testing the program-->
<!--To build the program, run `make` and `./autocomplete`. Start typing to see autocomplete suggestions (the default suggestions are not very good). If you hit enter you will get a clear line. If you hit enter with a blank line or press ctrl-C the program will quit.-->
<!---->
<!--You will need the ncurses library on your machine to build this project. All MathLAN machines have this library, but I am happy to help you set it up on your own machine if you run Linux or MacOS.-->
<!---->
<!--To test the program with a larger history, run the program with additional file name parameters. For example `./autocomplete history.txt` will load each line of the history.txt file into the history at startup. You should also test your autocomplete code with a larger history file, like `/usr/share/dict/words`.-->
