import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Counter{
    private String file1;
    private String file2;

    public Counter(String file1,String file2) throws FileNotFoundException{
        setFile1(file1);
        setFile2(file2);
    }

    // setters in case users want to change one of the files without creating a whole new object

    public void setFile1(String file1) throws FileNotFoundException{
        if(!new File(file1).exists()){
            throw new FileNotFoundException("File "+file1+" doesn't exist.");
        }
        this.file1=file1;
    }

    public void setFile2(String file2) throws FileNotFoundException{
        if(!new File(file2).exists()){
            throw new FileNotFoundException("File "+file2+" doesn't exist.");
        }
        this.file2=file2;
    }

    public void count() throws IOException{
        Map<Character,Integer> counts=new HashMap<>();
        for(char ch='A';ch<='Z';++ch) // put zeroes for upper case letters
            counts.put(ch, 0);
        for(char ch='a';ch<='z';++ch) // put zeroes for lower case letters
            counts.put(ch, 0);
        try(BufferedReader reader=new BufferedReader(new FileReader(file1))){ // try with resources 
            int character=reader.read();
            while(character!=-1){
                char ch=(char)character;
                if(counts.containsKey(ch)) // if hashmap has a key that matches the character then it's a letter that we need to count, so we add it
                    counts.put(ch,counts.get(ch)+1);
                character=reader.read();
            }
        }
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(file2))){
            for(Map.Entry<Character,Integer> entry:counts.entrySet()){ // makes hashmap iterable and iterates over each entry (each letter) to write into file2
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }
}