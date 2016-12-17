package IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IO {
    BufferedWriter bufferWriter = null;
    
    
    // method to check for the used directory
    public void checkDir() {
        try {
            // define directory name in String
            String dirName = "/Error/";
            File directory = new File("/Error/");
            
            // if the directory does not exist, create it
            if(!directory.exists()) {
                System.out.print("Creating Directory: " + dirName);
                boolean exists = false;
                
                try {
                    directory.mkdir();
                    exists = true;
                    System.out.print("Directory created.");
                } catch(SecurityException error) {
                    
                }
            } else {
                System.out.print("Exist.");
            }
        } catch(SecurityException error) {
            // handle error
            
        } catch(Exception error) {
            
        }
    }
    
    // a method to append data to a file
    public  void appendToFile(String errorMsg) {
        checkDir();
        
        try {
         File file = new File("/error.txt");  
         // if file doesn't exist create it
         if(!file.exists()) {
             System.out.println("No such file.");
             file.createNewFile();
         }
        
         // get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");         
        // create message string
        String fullErrorMsg = "**********\nDate: " +  dateFormat.format(new Date()) +
                "\nError description:\n" + errorMsg + "\n***********\n";
        
         // create a file writer
         FileWriter fileWriter = new FileWriter("/error.txt",true);
         // create a buffer for the fileWriter object
         bufferWriter = new BufferedWriter(fileWriter);
         bufferWriter.newLine();
         bufferWriter.write(fullErrorMsg);
         
         bufferWriter.close();
         
        } catch(IOException error) {
            
        } catch(SecurityException error) {
            
        }
    }    
}
