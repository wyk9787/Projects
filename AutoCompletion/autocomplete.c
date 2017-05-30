#include "autocomplete.h"
#include "BBST.h"

#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Add a line of input to our list of previously-seen inputs. Once added, this
 * string could be returned as a suggested input for a future line.
 *
 * \param line  The line to add to our history
 */

 //Binary search tree to store data
BiTree T = NULL;

//size of the binary seach tree
long long size = 0;

void add_line(char* line) {
    Status taller;
    if(InsertAVL(&T, strdup(line), &taller)){
        size ++;
    }
}

/**
 * Search our history for lines that begin with `input` and return up to
 * `max_suggestions` of them by writing pointers into the `suggestions` array.
 * If there are fewer than `max_suggestions` suggestions, write NULL into the
 * empty suggestion slots.
 *
 * \param input             The currently typed line
 * \param suggestions       The array we should write suggestions into
 * \param max_suggestions   The maximum number of suggestions we should return.
 */
void get_suggestions(char* input, const char** suggestions, size_t max_suggestions) {
    for(int i=0; i < max_suggestions; i++) {
      suggestions[i] = NULL;
    }
    BiTree p;
    if(search_bst(T, input, NULL, &p)){
        int index = 0;
        in_order_traverse(p, suggestions, &index, input, max_suggestions);
    }
}
