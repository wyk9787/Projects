#include <stdio.h>

int main() {
  char c = 'y';
  while(c == 'y') {
    int m, n;
    printf("Please enter two numbers: \n");
    scanf("%d%d", &m, &n);
    int ans1, ans2 = 0; //ans1 is user's input, ans2 is computer's input

    if(m % (n+1) != 0) {
      printf("I will start first.\n");
      ans2 = m % (n+1);
      printf("I go %d\n", ans2);
      m -= ans2;
      printf("Please enter your number.\n");
      scanf("%d", &ans1);
      while(! (ans1 - ans2 > 0 && ans1 - ans2 < n)) {
        printf("It's an invalid input. Please try again.\n");
        scanf("%d", &ans1);
      }
    } else {
      printf("You start first.\n");
      printf("Please enter your number.\n");
      scanf("%d", &ans1);
    }
    int diff;
    while(ans1 < m - n - 1) {
      ans2 += n + 1;
      printf("I go %d\n", ans2);
      printf("Please enter your number.\n");
      scanf("%d", &ans1);
      while(! (ans1 - ans2 > 0 && ans1 - ans2 < n+1)) {
        printf("It's an invalid input. Please try again.\n");
        scanf("%d", &ans1);
      }
    }
    printf("I go %d\n", ans2+n+1);
    printf("Sorry you lost.\n");
    printf("Wanna try again? (y/n)\n");
    getchar();
    scanf("%c", &c);
  }
  printf("Thanks for playing.\n");
}
