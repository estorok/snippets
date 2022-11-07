package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

import java.util.*;

/**
 * Searcher provides methods to search for paths through graphs.
 */
public class Searcher {

    // This class does not represent an abstract data type.

    /**
     * Returns the shortest path, taking into account weights on each edge,
     * from the given start node to the given end node in the given graph.
     *
     * @param start data of start node.
     * @param end data of end node.
     * @param g graph to find shortest paths through.
     * @param <T> the type of node data in the given graph.
     * @return the shortest weighted path between the given start and end node
     * in the given graph.
     * @throws IllegalArgumentException if start, end, or g are null,
     * or g does not contain the start node.
     * @spec.requires Each edge of g has data of non-negative value.
     */
    public static <T> Path<T> shortestWeightedPath(
            T start,
            T end,
            Graph<T, Double> g) {

        if (start == null || end == null || g == null || !g.containsNode(start)) {
            throw new IllegalArgumentException();
        }

        // We will examine paths according to the least expensive paths first;
        // we will also keep track of the shortest paths to a given node given
        // a starting node.

        Comparator<Path<T>> byPathCost = Comparator.comparingDouble(Path::getCost);
        Queue<Path<T>> activePaths = new PriorityQueue<>(byPathCost);
        Map<T, Path<T>> finishedPaths = new HashMap<>();
        activePaths.add(new Path<>(start));

        while (!activePaths.isEmpty()) {
            // Examine the shortest path available for us to traverse.
            Path<T> minPath = activePaths.remove();
            T minPathEnd = minPath.getEnd();

            if (minPathEnd.equals(end)) {
                // We have found the shortest path from the start to the end;
                // return it.
                return minPath;
            }

            if (!finishedPaths.containsKey(minPathEnd)) {
                // We have not yet traversed to this path.
                Graph<T, Double>.Node pathEndNode = g.getNode(minPathEnd);
                Set<Graph<T, Double>.Edge> nextEdges = g.getChildEdges(pathEndNode);
                for (Graph<T, Double>.Edge e : nextEdges) {
                    if (!finishedPaths.containsKey(e.child().data())) {
                        // For each child node of the end of the current path,
                        // extend a path to that node and add it to the queue
                        // of paths to consider.
                        Path<T> newPath = minPath.extend(
                                e.child().data(), e.data()
                        );
                        activePaths.add(newPath);
                    }
                }
                // We have explored the path.
                finishedPaths.put(minPathEnd, minPath);
            }
        }

        return null;
    }
}
