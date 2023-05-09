/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.File;  
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author stanley and kevin
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
//@SpringBootApplication
@SpringBootApplication()
public class Application {
    public static HashMap<String,String[]> profiles=new HashMap<>();

    public static void main(String[] args){
        //Kevin and I have created local databases as of current that have different credientials
        //This implementation of hashmaps and local files is to mitigate the amount of back and forth in terms of manually changing properties.
        profiles.put("NONE",new String[]{"kevramos","password"});
        profiles.put("STANLEY",new String[]{"root",""});

        String profileName=readProfile();

        System.setProperty("spring.datasource.username", profiles.get(profileName)[0]);
        System.setProperty("spring.datasource.password", profiles.get(profileName)[1]);
    
        SpringApplication.run(Application.class, args);
    }
    /**
     * This function will read a local text file called PORFILE.txt in src/main/resources.
     * In doing so, if the current user has a database username and password than the currently described by profiles,
     * they may create their own PROFILE.txt and add their entry into the hashmap above.
     * @return The profile name in the text file that will translate to a key in the hashmap.
     */
    public static String readProfile(){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        
        String result="";
        try {
            File myObj = new File("src/main/resources/PROFILE.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                result+=data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            result="NONE";
        }
        System.out.println(result);
        if (profiles.get(result)==null) result="NONE";

        System.out.println("Profile Name: "+ result);
        return result;
    }
}