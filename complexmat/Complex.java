package complexmat;

public class Complex{
    private double real;
    private double imag;
    public Complex(double real, double imag){
        this.real=real;
        this.imag=imag;
    }
    public Complex(double real){
        this.real=real;
        this.imag=0;
    }
    public double getReal(){
        return real;
    }
    public double getImag(){
        return imag;
    }
    public Complex add(Complex other){
        double newr=this.real+other.real;
        double newi=this.imag+other.imag;
        return new Complex(newr,newi);
    }
    public Complex subtract(Complex other){
        double newr=this.real-other.real;
        double newi=this.imag-other.imag;
        return new Complex(newr,newi);
    }
    // the formula is (a+ib)(c+id)=(ac-bd)+i(ad+bc)
    public Complex multiply(Complex other){
        double newr=this.real*other.real-this.imag*other.imag;
        double newi=this.real*other.imag+this.imag*other.real;
        return new Complex(newr,newi);
    }
    // division is not needed since there's no matrix operation that would require division of its elements
    // public Complex divide(Complex other){
    //     double newr=(this.real*other.real+this.imag*other.imag)/(other.real*other.real+other.imag*other.imag);
    //     double newi=(this.imag*other.real-this.real*other.imag)/(other.real*other.real+other.imag*other.imag);
    //     return new Complex(newr,newi);
    // }
    public void print(){
        System.out.print(this.real + " + " + this.imag + "i");
    }
}