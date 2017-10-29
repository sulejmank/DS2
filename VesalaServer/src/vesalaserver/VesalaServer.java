/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vesalaserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Imana
 */
public class VesalaServer {

    /**
     * @param args the command line arguments
     */
    
    static ArrayList<String> recnik = new ArrayList<>();
    private static FileReader fileReader;
    private static BufferedReader buffFileReader;
    public static String trazenaRec;
    public static void initializeStreams() throws IOException {
    
    try {
        File inFile = new File("recnik.txt");
        fileReader = new FileReader(inFile);
        buffFileReader = new BufferedReader(fileReader);
        
        String currentLine = buffFileReader.readLine();
            while(currentLine != null) {
                recnik.add(currentLine);
                currentLine = buffFileReader.readLine();
            } 
            buffFileReader.close();
            fileReader.close();
    } catch (IOException e) {
        System.out.println("stream error!");
    }
}
    public static String izaberiRec() {
    
    Random rand = new Random();
    int wordId = Math.abs(rand.nextInt()) % recnik.size();
   
    return recnik.get(wordId);
    }
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket server = new ServerSocket(8081);
        
        
        try {
            while(true) {
               initializeStreams();
                System.out.println("Server is running!");
                Socket socket = server.accept();
                
                try {
                    
                  
                System.out.println("Client Connected!");
                PrintWriter  out = new PrintWriter(socket.getOutputStream(), true);
              
                out.println(izaberiRec());
                
                }
                finally {
                    socket.close();
                }
            }  
        }  finally {
                server.close();
        
        }
        
    }
}