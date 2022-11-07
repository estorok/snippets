package pathfinder.implTest;

import graph.Graph;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import pathfinder.Searcher;
import pathfinder.datastructures.Path;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Tests for the implementation of Dijkstra's Algorithm.
 */
public class TestSearcher {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test
    public void testAvoidsLoop() {
        Graph<String, Double> g = new Graph<>();
        Graph<String, Double>.Node a = g.new Node("a");
        Graph<String, Double>.Node b = g.new Node("b");
        Graph<String, Double>.Node c = g.new Node("c");

        g.addNode(a);
        g.addNode(b);
        g.addNode(c);

        g.addEdge(a, b, 0.00001);
        g.addEdge(b, a, 0.00001);
        g.addEdge(b, c, 1000000D);

        Path<String> p = Searcher.shortestWeightedPath("a", "c", g);
        Iterator<Path<String>.Segment> i = p.iterator();
        Path<String>.Segment s1 = i.next();
        Path<String>.Segment s2 = i.next();
        assertFalse(i.hasNext());
        assertEquals("a", s1.getStart());
        assertEquals("b", s1.getEnd());
        assertEquals("b", s2.getStart());
        assertEquals("c", s2.getEnd());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionForNull() {
        Searcher.shortestWeightedPath(null, "a", new Graph<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionForNoStartNode() {
        Searcher.shortestWeightedPath("a", "b", new Graph<>());
    }
}
