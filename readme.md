## Graph search

### Searcher

The code in Searcher is a graph search algorithm to find 
the shortest path between two nodes in a directed graph 
according to total edge weight.
This approach can also be used to do a depth-first search.

Since I enforced non-negative edge, weights, 
I was able to implement Dijkstra's algorithm for this purpose.

### TestSearcher

TestSearcher contains unit tests for the correctness of the search implementation.

I wrote the unit tests using JUnit.

## Mandelbrot

Python script generates a HSV plot of the Mandelbrot set 
in a specified coordinate range, where the hue of a coordinate 
is determined by the number of iterations to 'escape' 
or be guaranteed not to converge under the recurrence z^2 + c.
