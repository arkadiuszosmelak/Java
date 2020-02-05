/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klient_dupleks;

import java.io.*;
import java.net.*;

/**
 *
 * @author dredi
 */

 
public class Klient_dupleks
{

   public static final int PORT=50007;
   public static final String HOST = "127.0.0.1";
 
   public static void main(String[] args) throws IOException  
   {           
        Boolean polaczenie = true;
        BufferedReader klaw;
        PrintWriter outp;  
        String str;

          //nawiazanie polaczenia z serwerem                     
        Socket sock;                                           
        sock=new Socket(HOST,PORT);                            
        System.out.println("Nawiazalem polaczenie: "+sock); 

        klaw=new BufferedReader(new InputStreamReader(System.in));
        outp = new PrintWriter(sock.getOutputStream());

        //tworzenie watka odbierajacego                            
        Odbior o = new Odbior(sock);
        o.start();
        
        while(o.isAlive())
        {
            try
          {      
               //System.out.print("<Wysylamy:> ");                                                
               str=klaw.readLine();
               outp.println(str);                                                               
               outp.flush();
               if( "koniec".equals(str))
               {
                    klaw.close();                                                                    
                    outp.close();                                                                    
                    sock.close();
                    break;
               }
          }
         catch (SocketException err) 
           {
            System.err.println("Polaczenie zostalo przerwane z powodu bledu zwiazanego z TCP");
           }
          catch( IOException e )
           {
              System.err.println("Problem z polaczeniem!");
              System.exit(1);
           }
        }
 

        //zamykanie polaczenia  
            klaw.close();                                                                    
            outp.close();                                                                    
            sock.close();                                
            System.out.println(" koniec połączenia! ");
     
                  
      
    
 
 
 
   }
}
