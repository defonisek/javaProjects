package complexmat;

public class Matrix{
    private Complex[][] arr;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        arr = new Complex[rows][cols];
        // filling the matrix with 0+0i
        for(int i=0;i<rows;++i){
            for(int j=0;j<cols;++j){
                arr[i][j]=new Complex(0,0);
            }
        }
    }
    public Matrix(int rows,int cols,Complex[][] arr){
        this.rows = rows;
        this.cols = cols;
        this.arr = arr;
    }
    public int getRows(){
        return rows;
    }
    public int getCols(){
        return cols;
    }
    public Complex getElement(int i,int j){
        return arr[i][j];
    }
    public void set(int i,int j,Complex c){
        arr[i][j]=c;
    }
    public void print(){
        for(int i=0;i<rows;++i){
            for(int j=0;j<cols;++j){
                // printing row elements separated with a space (can be changed in the second "print")
                arr[i][j].print();
                System.out.print(" ");
            }
            // going to the next row
            System.out.println("\n");
        }
    }
    public Matrix add(Matrix other){
        Matrix res=new Matrix(rows,cols);
        for(int i=0;i<rows;++i){
            for(int j=0;j<cols;++j){
                res.arr[i][j]=arr[i][j].add(other.arr[i][j]);
            }
        }
        return res;
    }
    public Matrix multiply(Matrix other){
        Matrix res=new Matrix(rows,other.cols);
        for(int i=0;i<rows;++i){
            for(int j=0;j<other.cols;++j){
                for(int k=0;k<cols;++k){
                    res.arr[i][j]=res.arr[i][j].add(arr[i][k].multiply(other.arr[k][j]));
                }
            }
        }
        return res;
    }
    public Matrix subtract(Matrix other){
        Matrix res=new Matrix(rows,cols);
        for(int i=0;i<rows;++i){
            for(int j=0;j<cols;++j){
                res.arr[i][j]=arr[i][j].subtract(other.arr[i][j]);
            }
        }
        return res;
    }
    public Matrix transpose(){
        Matrix res=new Matrix(cols,rows);
        for(int i=0;i<rows;++i){
            for(int j=0;j<cols;++j){
                res.arr[j][i] = arr[i][j];
            }
        }
        return res;
    }


}