####Naive solution

Not much to be said - just check each number to see if it's divisible and add them all up.

####Applying some math

A little thought brings us to a question - why exactly are we making the computer check every number? Do we not *know* which numbers are divisible by 3 and 5? Of course we do: 3,5,6,9,10,12,15 and so on...  
And so on? Well, the pairs of remainders modulo k1 and k2 will cycle with length exactly equal to the least common multiple of k1 and k2. In this case 15.

How much do we travel from each number to the next? Well, if we start at 0:
    3, 2, 1, 3, 1, 2, 3

Adding up that sequence (wheel) indefinitely will generate all the numbers we are looking for. The rest is easy.

Unfortunately, we have to get our hands a bit dirty to express this in Clojure and have it run fast.

####Why code when we can math?

If we know so much, can't we in our wisdom just find a formula for this sum and have done with it? Well, let's see...

Breaking down the sum to cycles of 15, cycle number k would have terms:

    15 * k, 15 * k + 3, 15 * k + 5, 15 * k + 6, 15 * k + 9, 15 * k + 10, 15 * k + 12

Or, in total:

    105 * k + 45

Let's say we have m such cycles, then the total sum would be:

	105 * 0 + 45 + 105 * 1 + 45 + ... + 105 * (m-1) + 45
	105 * (1 + 2 + ... + m-1) + 45 * (m-1)
	105 * (m-1) * m / 2 + 45 * m

We have exactly [n / 15] such cycles, but also some extra terms, which fortunately we can just precalculate

    0,1,2,3 -> 0
    4,5 -> 3
    6 -> 8
    7,8,9 -> 14
    10 -> 23
    11,12 -> 33
    13,14 -> 45

    [0,0,0,0,3,3,8,14,14,14,23,33,33,45,45] 

And we have to take into account that we're adding (15 * m + 0, 15*m + 3, ...) and not just 0,3,...  
While it does complicate things, writing the formula is still relatively easy.

Compared to the previous two, this solution is brutally fast ( **O(1)** for fixed-length numbers, **O(whatever-complexity-multiplication-is)** for big numbers).

####A combinatorial look at things

Of course, playing around with wheels and modular arithmetic is fun and all, providing lots of power to express all sort of numerical problems, one cannot ignore the sheer brute force of combinatorics.

In particular, using the inclusion-exclusion principle, we can state our problem thus:

    The sum of numbers below n divisible by 3 or 5 is equal to --/-- divisible by 3 plus --/-- divisible by 5 minus --/-- divisible by 15.

We know how many of each we have - [(n-1)/3], [(n-1)/5] and [(n-1)/15]. Pretty simple to write another O(1) solution from there, even shorter than the last.

At 8 lines O(1) solution, I'd say we're done here.