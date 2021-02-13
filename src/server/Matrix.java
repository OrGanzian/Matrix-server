package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Matrix implements Serializable, Igraph<Index> {
    int[][] primitiveMatrix;

    public Matrix(int[][] oArray) {
        primitiveMatrix = Arrays
                .stream(oArray)
                .map(row -> row.clone())
                .toArray(value -> new int[value][]);
    }

    public void printMatrix() {
        for (int[] row : primitiveMatrix) {
            String s = Arrays.toString(row);
            System.out.println(s);
        }
    }

    public final int[][] getPrimitiveMatrix() {
        return primitiveMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : primitiveMatrix) {
            stringBuilder.append(Arrays.toString(row));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Collection<Index> getAdjacentIndices(final Index index) {
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try {
            extracted = primitiveMatrix[index.row + 1][index.column];
            list.add(new Index(index.row + 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column + 1];
            list.add(new Index(index.row, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row - 1][index.column];
            list.add(new Index(index.row - 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column - 1];
            list.add(new Index(index.row, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return list;
    }

    public Integer getValue(Index index) {
        return (Integer) primitiveMatrix[index.row][index.column];
    }

    public Collection<Index> getReachables(Index index) {
        LinkedList<Index> filteredIndices = new LinkedList<>();
        this.getAdjacentIndices(index).stream().filter(i -> getValue(i) == 1)
                .map(neighbor -> filteredIndices.add(neighbor)).collect(Collectors.toList());
        return filteredIndices;
    }

    public Boolean isSizeValid(int maximum) {
        if (this.primitiveMatrix.length > maximum
                || this.primitiveMatrix[0].length > maximum) {
            return false;
        }
        return true;

    }


    public static void main(String[] args) {
        int[][] source = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        Matrix matrix = new Matrix(source);
        LinkedList<Index> lastIndexNeighbors = (LinkedList<Index>) matrix.getReachables(new Index(2, 1));
        System.out.println(lastIndexNeighbors);
        System.out.println(lastIndexNeighbors.getLast());

    }

}