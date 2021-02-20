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

    public List<R> getSccByNode(R node) {
        List<R> visited = new LinkedList<>();
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

    public List<List<R>> getAllScc() {

        List<List<R>> allScc = new LinkedList<>();
        List<R> allNodes= (LinkedList<R>) this.graph.getAllNodes();

        while (!allNodes.isEmpty()) {

            R curr = ((LinkedList<R>) allNodes).getFirst();
            List<R> listToPush = this.getSccByNode(curr);

            for (R i : listToPush) {
                allNodes.remove(i);

            }
            allScc.add(listToPush);
        }

        allScc.sort((Comparator.comparingInt(List::size)));
        Collections.reverse(allScc);
        return allScc;

    }

   

    public static void main(String[] args) {
        int[][] source = {
                {1, 0, 1},
                {1, 0, 1},
                {0, 0, 1},


        };
        DiagonalMatrix matrix = new DiagonalMatrix(source);

       Dfs<DiagonalMatrix, Index> dfs = new Dfs<DiagonalMatrix,Index>(matrix);
//        LinkedList<Index> getScc = dfs.getSccByNode(new Index(0, 0));
//        System.out.println(getScc);
        List<List<Index>> allScc = new LinkedList<>();
        allScc = dfs.getAllScc();
        System.out.println(allScc);

       // System.out.println(new Index(4, 8).getRow());


        MatrixAlgo matrixAlgo = new MatrixAlgo(matrix);
        Integer ans = matrixAlgo.validSubmarines(allScc);

        System.out.println(ans);

    }


}
