
package test;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class server extends JFrame {
    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
     static JTextArea txtChat=new JTextArea();
     static JTextField txtEnter=new JTextField();
     static JButton btn=new JButton("Send");
     
     
    public server(){
        //create form
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Server");
        
        //create form
        txtEnter.setLocation(2,540);
        txtEnter.setSize(550,30);
        txtEnter.setBackground(new Color(186,194,219));
        txtEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                String send="";
                send=txtEnter.getText().trim();//
                dos.writeUTF(send);//write and send data format multithreading
                }catch(Exception ex){}
                
                txtEnter.setText("");
            }
        });
        
        
        //form chat
        txtChat.setLocation(20, 15);
        txtChat.setSize(550, 500);
        txtChat.setBackground(new Color(186,194,219));
        
        
        this.add(txtEnter);
        this.add(txtChat);

    }

    public static void main (String args[]){
        new server();
        String str="";
        
            try{
            
            ss=new ServerSocket(1200);
               
            s=ss.accept();
                
            System.out.println("connect to client ... \t"+s.getRemoteSocketAddress()+"\t post"+s.getPort()+"\t IP:"+s.getInetAddress());
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            
            while(!str.equals("exit")){
                str=dis.readUTF();//read multithreading and send to client
                txtChat.setText(txtChat.getText().trim()+"\n Client: \t"+str);
                //receive data from client in txtchat and send form chat
            }
                ss.close();
                s.close();
                dis.close();
                dos.close();
                
            }catch(Exception e){}
        System.out.println("Server close");
        
    }
    
}
