package algorithms;

import server.Igraph;
import server.Index;
import server.Matrix;

import java.util.LinkedList;
import java.util.Stack;

public class Dfs<T extends Igraph<Index>>  {

    T graph;

    public Dfs(T graph) {
        this.graph = graph;
    }

    LinkedList<Index> getSccByNode(Index index) {
        LinkedList<Index> visited = new LinkedList<>();
        Stack<Index> stack = new Stack<>();

        stack.add(index);
        visited.add(index);

        while (!stack.isEmpty()) {
            Index curr = stack.pop();

            LinkedList<Index> currNeighbors = (LinkedList<Index>) this.graph.getReachables(curr);
            if (currNeighbors.isEmpty()) {
                continue;
            }
            else
                {
                for (Index i : currNeighbors) {
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


    public static void main(String[] args) {
        int[][] source = {
                {1,0,0},
                {1,1,0},
                {1,0,1},

        };
        Matrix matrix = new Matrix(source);
        Dfs<Matrix> dfs = new Dfs<>(matrix);
        LinkedList<Index> getScc = dfs.getSccByNode(new Index(0, 0));
        System.out.println(getScc);

    }


}
