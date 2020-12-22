package server;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class MatrixIHandler implements IHandler {

    private Matrix matrix;
    private Index start, end;

    public MatrixIHandler() {
        this.resetParams();
    }
    private void resetParams(){
        this.matrix = null;
        this.start = null;
        this.end = null;
    }

    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {

        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);

        this.resetParams();

        boolean dowork = true;
        while (dowork) {
            switch (objectInputStream.readObject().toString()) {
                case "stop":{
                    dowork= false;
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
                case "TaskOne": {
                    Index indexAdjacentIndices = (Index) objectInputStream.readObject();
                    Collection<Index> adjacentIndices = new ArrayList<>();
                    if (this.matrix != null){
                        adjacentIndices.addAll(this.matrix.getAdjacentIndices(indexAdjacentIndices));
                    }
                    System.out.println("result: " + adjacentIndices);
                    objectOutputStream.writeObject(adjacentIndices);
                    break;
                }
                case "TaskTwo": {
                    Index start = (Index) objectInputStream.readObject();
                    Collection<Index> reachables = new ArrayList<>();
                    if (this.matrix != null){
                        reachables.addAll(this.matrix.getReachables(start));
                    }
                    System.out.println("result: " + reachables);
                    objectOutputStream.writeObject(reachables);
                    break;
                }
            }
        }
    }
}
