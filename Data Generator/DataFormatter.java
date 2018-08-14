import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class DataFormatter{

    public static void main(String[] args){
        if(args.length != 3){
            System.out.println(
                "Usage: DataFormatter <input1> <input2>  <outputfile>");
            return; 
        }

        int MAXNAMES = 500;

        String[] validEmailDomains = {
            "@ucsc.edu",
            "@gmail.com",
            "@yahoo.com",
            "@hotmail.com",
            "@potato.org"
        };

        File firstNameCSV = null;
        File lastNameCSV = null;
        FileWriter outputFile = null;        

        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> lastNames = new ArrayList<String>();
 


        try{
            firstNameCSV = new File(args[0]);
            lastNameCSV = new File(args[1]);

            Scanner firstNameScanner= new Scanner(firstNameCSV);
            Scanner lastNameScanner= new Scanner(lastNameCSV);
            
            while(firstNameScanner.hasNextLine() && lastNameScanner.hasNextLine()){
                firstNames.add(firstNameScanner.nextLine());
                lastNames.add(lastNameScanner.nextLine());
            }

            firstNameScanner.close();
            lastNameScanner.close();

            

        }
        catch(Exception e){
            System.out.print("Input file error:\n" +e.getMessage());
        }

        try{
            PrintWriter out= new PrintWriter(new FileWriter(args[2]));
            Random rand= new Random();
            
            for(int i= 0; i<MAXNAMES; i++){
                int uniqueID = i+1;
                String fName = firstNames.get(rand.nextInt(firstNames.size()));
                String lName = lastNames.get(rand.nextInt(lastNames.size()));
                String emailDomain = 
                    validEmailDomains[rand.nextInt(validEmailDomains.length)];

                String email = fName.substring(0,1) + lName + emailDomain;
                email = email.toLowerCase();
                out.println(uniqueID+"|"+fName+"|"+lName+"|"+email);
            }
            out.close();
        }
        catch(Exception e){
            System.out.println("Output file error:\n" + e.getMessage());
        }
    }

}
