all: autocomplete

clean:
	rm -f autocomplete

autocomplete: main.c autocomplete.c autocomplete.h BBST.c BBST.h
	gcc --std=c11 -g -o autocomplete main.c autocomplete.c BBST.c -lncurses
