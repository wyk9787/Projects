# Parallel Data Structures 

Basic implementation of thread-safe stack, queue, and dictionary with
variants-oriented testing supported by Google Test.

## Usage
```
make clean all

# Run stack test
cd stack
./stack
cd ..

# Run queue test
cd queue
./queue
cd ..

# Run dictionary test
cd dictionray
./dictionary
cd ..
```

## Invariants

### Stack

* Invariant 1
For every value V that has been pushed onto the stack p times and returned by pop q
times, there must be p-q copies of this value on the stack. This only holds if p >= q.

* Invariant 2
No value should ever be returned by pop if it was not first passed to push by some thread.

* Invariant 3
If a thread pushes value A and then pushes value B, and no other thread pushes these specific values, A must not be popped from the stack before popping B.

### Queue

* Invariant1:
For every value V that has been put into the queue p times and returned by
take q times, there must be p-q copies of this value on the queue. This
only holds if p >= q.

* Invariant2:
No value should ever be returned by take if it was not first passed to put
by some thread

* Invariant3:
If a thread puts value A and then takes value B, and no other thread puts
these specific values, A must be taken from the queue before putting B.


### Dictionary

* Invariant1:
For every distinct key-value pair that has been set into the dict and
returned by get, we should get the same key-value pair.

* Invariant2:
Only -1 should be returned by get if the key was not first passed to set by some thread

* Invariant3:
Whenever after we set a key-value pair with the key that already exists in the
dict, when we call get, dict should return the updated value.
