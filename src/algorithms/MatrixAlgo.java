package algorithms;

import server.AbstractMatrix;
import server.Imatrix;
import server.Index;
import server.Matrix;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MatrixAlgo {

    Imatrix imatrix;



    public MatrixAlgo(Imatrix concreteMatrix) {
        this.imatrix = concreteMatrix;
    }

    public Boolean isSquare(List<Index> listToCheck) {

        Integer left, right, top, down;
        List<Integer> laftIndeces  = new LinkedList<>();
        List<Integer> rightIndeces = new LinkedList<>();

        for (Index i : listToCheck) {
            laftIndeces.add(i.getRow());
            rightIndeces .add(i.getColumn());
        }

        left = Collections.min(rightIndeces);
        right = Collections.max(rightIndeces);
        top = Collections.min(laftIndeces);
        down = Collections.max(laftIndeces);

        for (int i = top; i <= down; i++) {
            for (int j = left; j <= right; j++) {
                if (this.imatrix.getValue(new Index(i, j)) == 0) {
                    return false;
                }
            }
        }


        return true;
    }

    public Integer validSubmarines(List<List<Index>> allSccList) {
        Integer countValidSubmarines = 0;

        for (List<Index> i : allSccList) {
            if (this.isSquare(i) && i.size()>=2) {
                countValidSubmarines++;
            }
        }
        return countValidSubmarines;
    }



}
