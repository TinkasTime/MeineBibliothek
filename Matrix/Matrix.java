package Matrix;

public class Matrix {

    int row;
    int column;
    double[][] data;

    public Matrix(int rows, int columns) {
        this.row = rows;
        this.column = columns;
        this.data = new double[row][column];
    }

    public Matrix(double[][] exsistingData) {
        if (exsistingData == null || exsistingData.length == 0) {
            throw new IllegalArgumentException("Die angegebene Matrix kann nicht null oder leer sein.");
        }
        if (exsistingData[0] == null || exsistingData[0].length == 0) {
            throw new IllegalArgumentException("Die Reihen der angegebenen Matrix können nicht null oder leer sein.");
        }

        this.row = exsistingData.length;
        this.column = exsistingData[0].length;
        this.data = new double[row][column];
        
        for (int i = 0; i < this.row; i++) {
            if (exsistingData[i].length != this.column) {
                throw new IllegalArgumentException("Alle Reihen einer Matrix müssen gleich lang sein.");
            }
            for (int j = 0; j < this.column; j++) {
                this.data[i][j] = exsistingData[i][j];
            }
        }
    }

    public Matrix(double[] exsistingData) {
        if (exsistingData == null || exsistingData.length == 0) {
            throw new IllegalArgumentException("Die angegebene Matrix kann nicht null oder leer sein.");
        }

        this.row = exsistingData.length;
        this.column = 1;
        this.data = new double[row][column];

        for (int i = 0; i < this.row; i++) {
            this.data[i][0] = exsistingData[i];
        }
    }

    public void fill(double value) {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.data[i][j] = value;
            }
        }
    }

    public void print() {
        double temp = 0.0;

        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[i].length; j++) {

                temp = this.data[i][j];
                temp = ((double)((int)(temp *10000.0)))/10000.0;
                System.out.print(temp + " ");

            }
            System.out.println();
        }
        System.out.println();
    }

    public void randomize() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public Matrix add(Matrix otherMatrix) {
        if (this.row != otherMatrix.row || this.column != otherMatrix.column) {
            throw new IllegalArgumentException(
                "Matrizen müssen für die Addition die gleichen Dimensionen haben.");
        }

        Matrix newMatrix = new Matrix(this.row, this.column);

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                newMatrix.data[i][j] = this.data[i][j] + otherMatrix.data[i][j];
            }
        }

        return newMatrix;
    }

    public Matrix sub(Matrix otherMatrix) {
        if (this.row != otherMatrix.row || this.column != otherMatrix.column) {
            throw new IllegalArgumentException(
                "Matrizen müssen für die Subtraktion die gleichen Dimensionen haben.");
        }

        Matrix newMatrix = new Matrix(this.row, this.column);

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                newMatrix.data[i][j] = this.data[i][j] - otherMatrix.data[i][j];
            }
        }

        return newMatrix;
    }

    public Matrix multiply(double skalar) {
        Matrix newMatrix = new Matrix(this.row, this.column);

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                newMatrix.data[i][j] = this.data[i][j] * skalar;
            }
        }

        return newMatrix;
    }

    public Matrix elementWiseMultiply(Matrix otherMatrix) {
        if (this.row != otherMatrix.row || this.column != otherMatrix.column) {
            throw new IllegalArgumentException(
                "Matrizen müssen für die elementweise Multiplikation die gleichen Dimensionen haben.");
        }

        Matrix newMatrix = new Matrix(this.row, this.column);

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                newMatrix.data[i][j] = this.data[i][j] * otherMatrix.data[i][j];
            }
        }

        return newMatrix;
    }

    public Matrix dot(Matrix otherMatrix) {
        if (this.column != otherMatrix.row) {
            throw new IllegalArgumentException(
                "Für die Matrix-Multiplikation müssen die Spalten der ersten Matrix den Zeilen der zweiten Matrix entsprechen.");
        }

        Matrix newMatrix = new Matrix(this.row, otherMatrix.column);
        double sum_prod = 0.0;

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < otherMatrix.column; j++) {
                sum_prod = 0;
                for (int k = 0; k < otherMatrix.row; k++) {
                    sum_prod = sum_prod + (this.data[i][k]*otherMatrix.data[k][j]);
                }
                newMatrix.data[i][j] = sum_prod;
            }
        }

        return newMatrix;
    }

    public Matrix transpose() {
        Matrix newMatrix = new Matrix(this.column, this.row);

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                newMatrix.data[j][i] = this.data[i][j];
            }
        }

        return newMatrix;
    }

    public Matrix flatten() {
        Matrix newMatrix = new Matrix(1, this.row*this.column);

        int count = 0;

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                newMatrix.data[0][count] = this.data[i][j];
                count++;
            }
        }

        return newMatrix;
    }

    public Matrix reshape(int newRow, int newColumn) {
        if ((this.row*this.column) != (newRow*newColumn)) {
            throw new IllegalArgumentException(
                "Neue Dimensionen müssen die gleiche Gesamtanzahl an Elementen haben.");
        }

        Matrix newMatrix = new Matrix(newRow, newColumn);
        double[] flatDataTemp = this.flatten().toArray1D();
        int count = 0;

        for (int i = 0; i < newRow; i++) {
            for (int j = 0; j < newColumn; j++) {
                newMatrix.data[i][j] = flatDataTemp[count];
                count++;
            }
        }

        return newMatrix;
    }

    public double[][] toArray2D() {
        return this.data;
    }

    public double[] toArray1D() {
        if (this.row != 1 && this.column != 1) {
            throw new UnsupportedOperationException(
                "Matrix ist kein Vektor, weder 1 Zeile noch 1 Spalte."
            );
        }

        double[] newArr = new double[this.row*this.column];
        int count = 0;

        if (this.row == 1) {
            for (int j = 0; j < this.column; j++) {
                newArr[count] = this.data[0][j];
                count++;
            }
        } else {
            for (int i = 0; i < this.row; i++) {
                newArr[count] = this.data[i][0];
                count++;
            }
        }

        return newArr;
    }

    public Matrix copy() {
        Matrix newMatrix = new Matrix(this.data);
        return newMatrix;
    }

}