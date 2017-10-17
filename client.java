package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tranhung2027
 */
public class client extends JFrame{
    static ServerSocket ss;
    
    static DataInputStream dis;
    static DataOutputStream dos;
     static JTextArea txtChat=new JTextArea();
     static JTextField txtEnter=new JTextField();
//     static JButton btn=new JButton("Send");
    
    //tao form
    public client(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Client");
        
        txtEnter.setLocation(2, 540);
        txtEnter.setSize(590, 30);
        txtEnter.setBackground(new Color(186,194,219));
        txtEnter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
                
               try { 
                    String send="";
                    send=txtEnter.getText().trim();
                
                    dos.writeUTF(send);
                } catch (Exception ex) {
                    
                }
                txtEnter.setText("");
            }
        });
        txtChat.setLocation(20, 15);
        txtChat.setSize(550, 500);
        txtChat.setBackground(new Color(186,194,219));
        
        this.add(txtEnter);
        this.add(txtChat);
        
    }

    public static void main(String args[]) throws IOException{
        new client();
      
        try{
            Socket s=new Socket("localhost",1200);
            System.out.println("connect to "+s.getInetAddress()+" port \t:"+s.getPort());
             
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            String str="";
            while(!str.equals("exit")){
                str=dis.readUTF();
                txtChat.setText(txtChat.getText().trim()+"\n Sever:\t"+str);

            }
             s.close();
            dis.close();
            dos.close();
        }catch(Exception e){}
       System.out.println("client close");
        
    };
}
