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

class Wyslij extends Thread
{
   Socket sock;
   BufferedReader sockReader;
   PrintWriter outp;
   String str;
   
   
 
   public Wyslij(Socket sock) throws IOException                                         
   {                                                                                    
        this.sock=sock;                                                                   
        this.sockReader=new BufferedReader(new InputStreamReader(System.in));                                                                                              
        this.outp=new PrintWriter(sock.getOutputStream());  
   }                                                                                    
 
   public void run() 
   {          
       try
       {      
            System.out.print("<Wysylamy:> ");                                                
            str=sockReader.readLine();                                                      
            outp.println(str);                                                               
            outp.flush();
       }
       
       catch( IOException e )
        {
           System.err.println("Problem z polaczeniem!");
           System.exit(1);
        }
   }                 
}