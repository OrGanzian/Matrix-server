package algorithms;

import server.DiagonalMatrix;
import server.Igraph;
import server.Index;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Bfs is a generic class to traverse over a graph. Init example:   Bfs<Matrix, Index>.
 * @param <T> type of the graph, MUST implement Igraph.
 * @param <R> Type of every node in the graph
 */
public class Bfs<T extends Igraph<R>, R> {

    T graph;

    public Bfs(T graph) {
        this.graph = graph;
    }

    public List<List<R>> findAllPaths(R start, R end) {

        List<List<R>> allPaths = new LinkedList<>();

        Queue< LinkedList<R> > queue =new LinkedList();

        HashSet<R> visited = new HashSet<R>();

        LinkedList<R> startList = new LinkedList<R>();
        startList.add(start);
        queue.add(startList);

        while (!queue.isEmpty()) {
            LinkedList<R> curr = queue.poll();
            R lastIndex = curr.getLast();
            if (lastIndex.equals(end)) {
                allPaths.add(curr);
                continue;
            }

            LinkedList<R> lastIndexNeighbors = (LinkedList<R>) graph.getReachables(lastIndex);
            int numOfNeighbors = lastIndexNeighbors.size();
            if (numOfNeighbors == 0) {
                continue;
            }

            for (int i = 0; i <numOfNeighbors ; i++) {
                LinkedList<R> ListToPush= (LinkedList<R>) curr.clone();
                if (visited.contains(lastIndexNeighbors.get(i))) {
                    continue;
                }
                ListToPush.addLast(lastIndexNeighbors.get(i));
                queue.add(ListToPush);

            }

            visited.add(lastIndex);

        }


        return allPaths;
    }

    public int getShortestDistance(R start, R end) {
        int shortestDistance=0;

        List<List<R>> allPaths =this.findAllPaths(start, end);
        if (allPaths.isEmpty()) {
            return 0;
        }
        shortestDistance = allPaths.get(0).size();
        for (List<R> i : allPaths) {
            if (i.size() < shortestDistance) {
                shortestDistance = i.size();
            }
        }

        return shortestDistance;
    }

    public List<List<R>> findShortestPaths(R start, R end) {
        List<List<R>> allPaths =this.findAllPaths(start, end);
        int shortestDistance = this.getShortestDistance(start, end);
        List<List<R>> allShortestPaths = new LinkedList<>();
        for (List<R> i:allPaths) {
            if (i.size() <= shortestDistance) {
                allShortestPaths.add(i);
            }
        }
        return allShortestPaths;
    }


    public static void main(String[] args) {
        int[][] source = {
                {1,0,0},
                {1,0,0},
                {1,0,0},

        };
        DiagonalMatrix matrix = new DiagonalMatrix(source);
        Bfs<DiagonalMatrix, Index> bfs = new Bfs<>(matrix);
        List<List<Index>> getAllNodes = bfs.findAllPaths(new Index(0,0),new Index(2,1));
        System.out.println(getAllNodes);
        
    }


}
