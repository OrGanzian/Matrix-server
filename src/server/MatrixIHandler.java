package server;

import algorithms.Bfs;
import algorithms.Dfs;
import algorithms.MatrixAlgo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class MatrixIHandler implements IHandler {

    private DiagonalMatrix matrix;
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
                    this.matrix = new DiagonalMatrix(primitiveMatrix);
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


                    if (!this.matrix.isSizeValid(50)) {
                        throw new IllegalArgumentException("input not valid");
                    }
                    Bfs<DiagonalMatrix, Index> bfs = new Bfs<DiagonalMatrix, Index>(this.matrix);

                    LinkedList<LinkedList<Index>> paths = new LinkedList<>();
                    paths = bfs.findShortestPaths(this.start, this.end);
                    objectOutputStream.writeObject(paths);


                    break;
                }

                case "all scc": {

                    Dfs<DiagonalMatrix, Index> dfs = new Dfs<DiagonalMatrix, Index>( this.matrix);

                    LinkedList<LinkedList<Index>> allScc = new LinkedList<>();
                    allScc = dfs.getAllScc();
                    objectOutputStream.writeObject(allScc);


                    break;
                }
                case "submarines": {

                    MatrixAlgo matrixAlgo = new MatrixAlgo(matrix);
                    Dfs<DiagonalMatrix, Index> dfs = new Dfs<DiagonalMatrix, Index>( this.matrix);
                    Integer NumberOfValidSubmarines = matrixAlgo.validSubmarines(dfs.getAllScc());

                    objectOutputStream.writeObject(NumberOfValidSubmarines);


                    break;
                }
            }
        }
    }
}