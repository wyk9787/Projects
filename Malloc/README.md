# Malloc

This is a size segregated implementation of `malloc`

## Usage

```
make
LD_PRELOAD=./myallocator.so <your program>
```

## Test

```
make
cd malloc-test
make
LD_PRELOAD=../myallocator.so malloc-test
```
