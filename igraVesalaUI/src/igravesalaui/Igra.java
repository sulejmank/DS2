/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igravesalaui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Imana
 */
public class Igra {
    
    public StringBuilder trenutni_pokusaj;
    
    ArrayList<Character> prethodniPokusaji = new ArrayList<>();
    public static String trazenaRec;
    int max_pokusaj = 6;
    int trenutni = 0;
    
    
  
    
    
    public Igra() throws IOException {
    trenutni_pokusaj = initializeTrenutniPokusaj();
} 


  
public StringBuilder initializeTrenutniPokusaj() {
    
    StringBuilder current = new StringBuilder();
    for(int i = 0; i < trazenaRec.length()*2; i++) {
        if(i%2 == 0) {
            current.append("_");
            
        } else {
            current.append(" ");
        }
    }
    
    return current;
    
}


public String getCurrentGuess() {
    return "Trazimo Rec  :" + "\t " + trenutni_pokusaj.toString();
}

public boolean zavrsenaIgra() {
    if(pobeda()) {
        System.out.println("bravooo!");
        return true;
        
    } else if (poraz()) 
    {
        
        return true;
    }
    return false;
}

public boolean pobeda() {
    String guess = getGuess();
    return guess.equals(trazenaRec);
}

 public void setIcon(ImageIcon icon) {
     JLabel imgL = new JLabel();
            imgL.setIcon(icon);
        }


public boolean poraz() {
    return trenutni >= max_pokusaj;
}

public String getGuess() {
    String guess = trenutni_pokusaj.toString();
    return guess.replace(" ", "");
}
public boolean vecPogodjeno(char guess) {
    
    return prethodniPokusaji.contains(guess);
}
    

 public boolean pogodak(char guess) {
        boolean isItAGoodGuess = false;
        prethodniPokusaji.add(guess);
        for (int i = 0; i < trazenaRec.length(); i++) {
            if (trazenaRec.charAt(i) == guess) {
                trenutni_pokusaj.setCharAt(i * 2, guess);
                isItAGoodGuess = true;
            }
        }
        
        if (!isItAGoodGuess) {
            trenutni++;
        }
        
        return isItAGoodGuess;
    }
 
}
