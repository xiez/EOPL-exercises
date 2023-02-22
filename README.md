Exercises for the Essentials of Programming Languages 3e.

# Notes

## 1 Inductive Sets of Data

**The Smaller-Subproblem Principle:** If we can reduce a problem to a smaller subproblem, we can call the procedure that solves the problem to solve the subproblem.

**Follow the Grammar!** When defining a procedure that operates on inductively defined data, the structure of the program should be patterned after the structure of the data.

### Auxiliary Procedures and Context Arguments

```
number-elements : List → Listof(List(Int, SchemeVal))

number-elements-from : Listof(SchemeVal) × Int → Listof(List(Int,SchemeVal))
usage: (number-elements-from ’(v0 v1 v2 ...) n) = ((n v0) (n+1 v1) (n+2 v2) ...)
```

First, the proce- dure number-elements-from has a specification that is independent of the specification of number-elements. It’s very common for a programmer to write a procedure that simply calls some auxiliary procedure with some additional constant arguments.

Second, the two arguments to number-elements-from play two differ- ent roles. The first argument is the list we are working on. It gets smaller at every recursive call. The second argument, however, is an abstraction of the context in which we are working.


