import jdk.jfr.StackTrace;

public class Matrix {
    double[]data;
    int rows;
    int cols;
    //...
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    Matrix(double[][] d){
        for(int i=0;i<d.length;i++){
            for(int j=0;j<d[i].length;j++)
                System.out.print(d[i][j]+" ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
    }
}