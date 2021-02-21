package algorithms;

import server.DiagonalMatrix;
import server.Igraph;
import server.Index;
import server.Matrix;

import java.util.*;

public class Dfs<T extends Igraph<R>, R> {

    T graph;

    public Dfs(T graph) {
        this.graph = graph;
    }



    public Set<R> getSccByNode(R node) {
        Set<R> visited = new HashSet<>();
        Stack<R> stack = new Stack<>();

        stack.add(node);
        visited.add(node);

        while (!stack.isEmpty()) {
            R curr = stack.pop();

            LinkedList<R> currNeighbors = (LinkedList<R>) this.graph.getReachables(curr);
            if (currNeighbors.isEmpty()) {
                continue;
            } else {
                for (R i : currNeighbors) {
                    if (visited.contains(i)) {
                        continue;
                    }
                    stack.add(i);
                    visited.add(i);
                }
            }

        }
        return visited;
    }

    public List<Set<R>> getAllScc() {

        List<Set<R>> allScc = new LinkedList<>();
        List<R> allNodes= (LinkedList<R>) this.graph.getAllNodes();

        while (!allNodes.isEmpty()) {

            R curr = ((LinkedList<R>) allNodes).getFirst();
            Set<R> listToPush = this.getSccByNode(curr);

            for (R i : listToPush) {
                allNodes.remove(i);

            }
            allScc.add(listToPush);
        }

        allScc.sort((Comparator.comparingInt(Set::size)));
        Collections.reverse(allScc);
        return allScc;

    }




}
