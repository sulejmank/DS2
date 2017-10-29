/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igravesalaui;

import static igravesalaui.IgraVesalaUI.igra;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Imana
 */
public class IgraVesalaUI  {
    
        
      public static Igra igra;
   
      public static int br_pogresnih = 0;
   
    public static void main(String[] args) throws IOException {
        
        String serverAdd = "localhost";
        
        try {
            Socket socket = new Socket(serverAdd, 8081);
        
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String answer = input.readLine();

            Igra.trazenaRec = answer;

        }
        catch (IOException e) {
             JOptionPane.showMessageDialog(null, "Server is not running!!");
        }
       
        igra = new Igra();
        
        JFrame frame = new JFrame();
       
        JPanel panel = new JPanel();
        ImageIcon img = new ImageIcon(br_pogresnih+".png");
        JLabel imgL = new JLabel(img);
        
        JTextField txt = new JTextField(2);
        JButton btn2 = new JButton();
        JTextField txt2 = new JTextField(20);
        
        JPanel panel2 = new JPanel();
        btn2.setText("Nova Rec");
        panel.add(btn2);
        
        JButton btn1 = new JButton();
        btn1.setText("probaj");
        panel.add(imgL);
        
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(500, 400);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("igra vesala");
        GridBagConstraints c = new GridBagConstraints();
        
        txt2.setEditable(false);
        txt2.setText(igra.getCurrentGuess());
        frame.setResizable(false);
        
        
        
          
        
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ulaz = txt.getText();
                char guess = ulaz.charAt(0);
                
                if(igra.vecPogodjeno(guess)){
                    
                    JOptionPane.showMessageDialog(null, "To slovo ste vec pokusali, probajte drugo!");
                    txt.setText("");
                }
               else if(igra.pogodak(guess)) {
                   
                      JOptionPane.showMessageDialog(null, "POGDAK!!");
                       txt.setText("");
                       txt2.revalidate();
                       txt2.repaint();
                       txt2.setText(igra.getCurrentGuess());
                       
                       if(igra.pobeda()){
                           JOptionPane.showMessageDialog(null, "Pobedili ste!!!");
                       }
                       
                       }  else {
                   
                   JOptionPane.showMessageDialog(null, "Nema tog slova!");
                    txt.setText("");
                    br_pogresnih++;
                    panel.removeAll();
                    ImageIcon img = new ImageIcon(br_pogresnih+".png");
                    JLabel imgL = new JLabel(img);
                    panel.add(imgL);
                    panel.add(txt,c);
                    panel.add(btn1,c);
                    panel.add(txt2,c);
                    panel.add(btn2,c);
                    txt2.setText(igra.getCurrentGuess());
                    
                    if(br_pogresnih > 6) {
                        JOptionPane.showMessageDialog(null, "Izgubili ste!! Trazena rec je bila :" + igra.trazenaRec);
                        txt2.setText("");
                      
                    }
                   
                    imgL.revalidate();
                    imgL.repaint();
                    
                    panel.revalidate();
                    panel.repaint();
                    
                    
               }
            }
            
        });
        
       btn2.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                
            br_pogresnih = 0;
               
            
            igra.trenutni_pokusaj = igra.initializeTrenutniPokusaj();
            igra.prethodniPokusaji = new ArrayList<>();
            
            Socket socket = null;
                try {
                    socket = new Socket(serverAdd, 8081);
                } catch (IOException ex) {
                    Logger.getLogger(IgraVesalaUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        BufferedReader input;
                try {
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String answer = input.readLine();
                    Igra.trazenaRec = answer;
                } catch (IOException ex) {
                    Logger.getLogger(IgraVesalaUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                
      
          
                 panel.removeAll();
                    ImageIcon img = new ImageIcon(br_pogresnih+".png");
                    JLabel imgL = new JLabel(img);
                    panel.add(imgL);
                    panel.add(txt,c);
                    panel.add(btn1,c);
                    panel.add(txt2,c);
                    panel.add(btn2,c);
                    txt2.setText(igra.getCurrentGuess());

                    panel.revalidate();
                    panel.repaint();
    
          
                    
            }
           
       });
        
        
       c.gridx = 3;
       c.gridy = 1;
       panel.add(txt,c);
       panel.add(btn1,c);
       panel.add(txt2,c);
       panel.add(btn2,c);
       
    }

  
    
}
