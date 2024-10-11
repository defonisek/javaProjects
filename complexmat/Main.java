package complexmat;

class Main{
    public static void main(String[] args){
        // filling matrix 1 (example one, one by one)
        Matrix m1=new Matrix(2,2);
        m1.set(0,0,new Complex(1,2));
        m1.set(0,1,new Complex(3));
        m1.set(1,0,new Complex(-12,0));
        m1.set(1,1,new Complex(-2,1));
        // filling matrix 2 (example two, via arrays)
        Complex[][] arr=new Complex[][] {{new Complex(1),new Complex(2,2)},{new Complex(3,0),new Complex(-1.5,-3)}};
        Matrix m2=new Matrix(2,2,arr);
        // printing the matrices
        System.out.println("First matrix:");
        m1.print();
        System.out.println("\n");
        System.out.println("Second matrix:");
        m2.print();
        System.out.println("\n");
        // addition
        System.out.println("Addition:");
        Matrix sum=m1.add(m2);
        sum.print();
        System.out.println("\n");
        // subtraction
        System.out.println("Subtraction:");
        Matrix sub=m1.subtract(m2);
        sub.print();
        System.out.println("\n");
        // multiplication
        System.out.println("Multiplication:");
        Matrix mul=m1.multiply(m2);
        mul.print();
        System.out.println("\n");
        // transpose m1
        System.out.println("Transpose:");
        Matrix trans=m1.transpose();
        trans.print();
        // bigger matrix
        System.out.println("\n");
        System.out.println("Bigger matrix:");
        Complex[][] bigarr=new Complex[][] {{new Complex(1),new Complex(2,2),new Complex(3,0)},{new Complex(3,0),new Complex(-1.5,-3),new Complex(1,2)},{new Complex(1,2),new Complex(3),new Complex(-1.5,-3)}};
        Matrix m3=new Matrix(3,3,bigarr);
        m3.print();
        // bigger matrix transposition
        System.out.println("\n");
        System.out.println("Transposition of a bigger matrix:");
        Matrix trans3=m3.transpose();
        trans3.print();
        // rows!=columns matrix
        System.out.println("\n");
        System.out.println("Rows!=Columns Matrix 1:");
        Complex[][] arr2=new Complex[][] {{new Complex(1),new Complex(2,2),new Complex(3)},{new Complex(3,0),new Complex(-1.5,-3),new Complex(1,2)}};
        Matrix m4=new Matrix(2,3,arr2);
        m4.print();
        // second one
        System.out.println("\n");
        System.out.println("Rows!=Columns Matrix 2:");
        Complex[][] arr3=new Complex[][] {{new Complex(1),new Complex(2,2)},{new Complex(3,0),new Complex(-1.5,-3)},{new Complex(1,2),new Complex(3)}};
        Matrix m5=new Matrix(3,2,arr3);
        m5.print();
        // multiplication of two matrices with rows!=columns
        System.out.println("\n");
        System.out.println("Multiplication of two matrices with rows!=columns:");
        Matrix mul2=m4.multiply(m5);
        mul2.print();
    }
}