#include "autocomplete.h"
#include "BST.h"
#include <stdio.h>

#define MAX_SUGGESTIONS 10

int main() {
    char* suggestions[MAX_SUGGESTIONS];
    char* input[MAX_SUGGESTIONS] = {"abcde\0", "abdcs\0", "abcd\0", "asd\0", "abc\0", "abdce\0",
                                    "as\0", "abbccd\0", "abdc", "ab\0"};
    for(int i = 0; i < MAX_SUGGESTIONS; i++) {
        printf("i = %d\n", i);
        printf("Input is %s\n", input[i]);
        get_suggestions(input[i], suggestions, MAX_SUGGESTIONS);
        for(int i = 0; i < MAX_SUGGESTIONS; i++) {
            printf("suggestions[%d] = %s\n", i, suggestions[i]);
        }
        printf("\n");
        add_line(input[i]);
    }
}
