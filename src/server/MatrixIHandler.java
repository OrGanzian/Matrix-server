package server;

import algorithms.Bfs;
import algorithms.Dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class MatrixIHandler implements IHandler {

    private Matrix matrix;
    private Index start, end;

    public MatrixIHandler() {
        this.resetParams();
    }

    private void resetParams() {
        this.matrix = null;
        this.start = null;
        this.end = null;
    }

    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);

        this.resetParams();

        boolean dowork = true;
        while (dowork) {
            switch (objectInputStream.readObject().toString()) {
                case "stop": {
                    dowork = false;
                    break;
                }
                case "matrix": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    this.matrix = new Matrix(primitiveMatrix);
                    this.matrix.printMatrix();

                    break;
                }
                case "start index": {
                    this.start = (Index) objectInputStream.readObject();
                    break;
                }
                case "end index": {
                    this.end = (Index) objectInputStream.readObject();
                    break;
                }
                case "shortest path": {


                    if (this.matrix.isSizeValid(50) == false) {
                        throw new IllegalArgumentException("input not valid");
                    }
                    Bfs<Matrix, Index> bfs = new Bfs<>(this.matrix);

                    LinkedList<LinkedList<Index>> paths = new LinkedList<>();
                    paths = bfs.findShortestPaths(this.start, this.end);
                    objectOutputStream.writeObject(paths);


                    break;
                }
                case "TaskTwo": {
                    //   Index start = (Index) objectInputStream.readObject();
                    Collection<Index> reachables = new ArrayList<>();
                    if (this.matrix != null) {
                        reachables.addAll(this.matrix.getReachables(start));
                    }
                    System.out.println("result: " + reachables);
                    objectOutputStream.writeObject(reachables);
                    break;

                }
                case "all scc": {

                    Dfs<Matrix, Index> dfs = new Dfs<>(this.matrix);

                    LinkedList<LinkedList<Index>> allScc = new LinkedList<>();
                    allScc = dfs.getAllScc();
                    objectOutputStream.writeObject(allScc);


                    break;
                }
            }
        }
    }
}