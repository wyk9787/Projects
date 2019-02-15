# Copy On Write (lazy copy)

This program creates a lazy copy mechanism in user space by using the special
effect of `mremap`: when the second parameter `old_size` equals to 0, we can
create an extra mapping in the virtual memory that maps to the same physical
page as long as that page is shareable. For more info, refer to the man page of
[`mremap`](http://man7.org/linux/man-pages/man2/mremap.2.html)

## Usage

```
make clean all
./lazycopy-test
```

This is a simple test program that verifies the lazy copy actually works by
comparing to the eager copy (normally how people think copy works).
