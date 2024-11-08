import java.io.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the path to (or name of) the input file: ");
        String file1=scanner.nextLine();
        System.out.print("Enter the path to (or name of) the output file: ");
        String file2=scanner.nextLine();
        scanner.close();
        try{
            Counter counter=new Counter(file1,file2); // can work with absolute and relative paths 
            counter.count();
            System.out.println("Result output is in "+file2);
        }
        catch(FileNotFoundException e){
            System.err.println("Error: "+e.getMessage());
        }
        catch(IOException e){
            System.err.println("Error (while reading or writing the file): "+e.getMessage());
        }
    }
}