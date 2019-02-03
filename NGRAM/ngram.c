#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv) {
  // Make sure the program is run with an N parameter
  if (argc != 2) {
    fprintf(stderr, "Usage: %s N (N must be >= 1)\n", argv[0]);
    exit(1);
  }

  // Convert the N parameter to an integer
  int N = atoi(argv[1]);

  // Make sure N is >= 1
  if (N < 1) {
    fprintf(stderr, "Invalid N value %d\n", N);
    exit(1);
  }

  // Now read from standard input and print out ngrams until we reach the end of
  // the input
  char str[N + 1];
  fgets(str, N + 1, stdin);
  if (strlen(str) == N) {
    printf("%s\n", str);
  } else {
    return 0;
  }
  char c[2];
  char newStr[N + 1];
  while (fgets(c, 2, stdin)) {
    // copy str to newStr besides the first character
    memcpy(newStr, str + 1, N - 1);

    // copy the new character to newStr
    memcpy(newStr + N - 1, c, 1);
    newStr[N] = '\0';
    printf("%s\n", newStr);

    memset(str, 0, N + 1);
    memcpy(str, newStr, N + 1);
    memset(newStr, 0, N + 1);
    memset(c, 0, 2);
  }
}
