import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ooo
 */
public class PlayFair {
    
    public static void main(String args[]){
        PlayFair p = new PlayFair();
    }
    
    private JFrame frame;
    private JLabel l1 , l2 , l3;
    private JPanel panel , panel2 ,panel3;
    private JButton b1 , b2 ;
    private JTextField tf1 , tf2;
    private JFileChooser fc;
    
    public PlayFair(){
        prepare();
    }
    //DESIGN GUI STARTS
    private void prepare(){
        frame = new JFrame("Playfair algorithim");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3,1));
        frame.setVisible(true);
        l1 = new JLabel("Text",JLabel.CENTER);
        l1.setOpaque(true);
        l1.setBackground(Color.BLACK);
        l1.setForeground(Color.WHITE);
        l2 = new JLabel("Keyword",JLabel.CENTER);
        l2.setOpaque(true);
        l2.setBackground(Color.BLACK);
        l2.setForeground(Color.WHITE);
        l3 = new JLabel("",JLabel.CENTER);
        b1 = new JButton("Encrypt");
        b2 = new JButton("Decrypt");
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(255,204,0));
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(new Color(255,204,0));
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(new Color(255,204,0));
        fc = new JFileChooser();
        b1.addActionListener((ActionEvent e)->{
            try{
                
                String s = tf1.getText(),s1 = tf2.getText();
                if(s.length()>0){
                    l3.setText(cipherText(s,s1));
                }else{
                    s="";
                    int approve = fc.showOpenDialog(frame);
                    if(approve == JFileChooser.APPROVE_OPTION){
                    File  f = fc.getSelectedFile();
                    try{
                    FileInputStream fis = new FileInputStream(f);
                    int c;
                    while((c=fis.read())!= -1){
                        s+=(char)c;
                    }
                    
                    }catch(IOException w){
                        l3.setText(w.toString());
                    }
                    Thread t = new Thread();
                    try{
                        t.sleep(1000);
                    }catch(InterruptedException ez){
                        ez.printStackTrace();
                    }
                    int ap = fc.showOpenDialog(frame);
                    if(ap == JFileChooser.APPROVE_OPTION){
                    File f2 = fc.getSelectedFile();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(f2));
                    dos.writeBytes(cipherText(s,s1));
                    //l3.setText(cipherText(s,s1));
                    }
                    }
                }
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }catch(Exception ex){
                l3.setText(ex.toString());
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }
        });
        b2.addActionListener((ActionEvent e)->{
            try{
                String s = tf1.getText(),s1 = tf2.getText();
                if(s.length()>0){
                    l3.setText(plainText(s,s1));
                }else{
                    s="";
                    int approve = fc.showOpenDialog(frame);
                    if(approve == JFileChooser.APPROVE_OPTION){
                    File  f = fc.getSelectedFile();
                    try{
                    FileInputStream fis = new FileInputStream(f);
                    int c;
                    while((c=fis.read())!= -1){
                        s+=(char)c;
                    }
                    
                    }catch(IOException w){
                        l3.setText(w.toString());
                    }
                    Thread t = new Thread();
                    try{
                        t.sleep(1000);
                    }catch(InterruptedException ez){
                        ez.printStackTrace();
                    }
                    int ap = fc.showOpenDialog(frame);
                    if(ap == JFileChooser.APPROVE_OPTION){
                    File f2 = fc.getSelectedFile();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(f2));
                    dos.writeBytes(plainText(s,s1));
                    //l3.setText(cipherText(s,s1));
                    }
                    }
                }
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }catch(Exception ex){
                l3.setText(ex.toString());
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }
        });
        panel.add(l1);
        panel.add(tf1);
        panel.add(l2);
        panel.add(tf2);
        panel2.add(b1);
        panel2.add(b2);
        panel3.add(l3);
        frame.add(panel);
        frame.add(panel2);
        frame.add(panel3);
        frame.getContentPane().setBackground(new Color(255,204,0));
        frame.setVisible(true);
    }
    
    public static String cipherText(String plainText , String keyword){
        Object [] alphabet = {"a","b","c","d","e","f","g","h","i/j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        Vector v = new Vector();
        Vector v2 = new Vector();
        Vector store = new Vector();
        Vector end = new Vector();
        String cipher = "";
        Vector row1 = new Vector();
        Vector row2 = new Vector();
        Vector row3 = new Vector();
        Vector row4 = new Vector();
        Vector row5 = new Vector();
        //checking and manipulating my keyword then adding it as first elements of my table
        for(int i = 0; i < keyword.length(); i++){
            char c = keyword.charAt(i);
            if(!v.contains(""+c))
                v.add(""+c);
        }
        //filling the rest the table with alphabet letters considering no duplication
        for(Object c : alphabet)
            if(!v.contains(c))
                v.add(c);
        //filling 5x5 table using five vectors with my previous vector which holds 25 letters
        for(int i = 0; i < v.size(); i++){
            if(i>=0&&i<5)
                row1.add(v.get(i));
            else if(i>=5&&i<10)
                row2.add(v.get(i));
            else if(i>=10&&i<15)
                row3.add(v.get(i));
            else if(i>=15&&i<20)
                row4.add(v.get(i));
            else if(i>=20&&i<25)
                row5.add(v.get(i));
        }
        //handling my plainText considering no duplications 
        String s = "";
        for(int i = 0; i < plainText.length(); i++){
            Object c = plainText.charAt(i);
            if(!v2.isEmpty()&&v2.get(i-1)==c&&v2.indexOf(v2.get(i-1))%2==0)
                v2.add('x');
            else if(v2.isEmpty())
                v2.add(c);
            else
                v2.add(c);
        }
        //ensuring the plainText's size should be even otherwise x will be added to the end of the string
        if(v2.size()%2==1)
            v2.add('x');
        //enumerating v2 and then adding it's element to a string (s)
        Enumeration e = v2.elements();
        while(e.hasMoreElements())
            s += e.nextElement();
        
        
        ////classifying my plainText into pairs
        for(int d=0; d<s.length(); d+=2){
            pairs p;
            if(s.charAt(d)=='i'||s.charAt(d)=='j')
                p = new pairs(("i/j"),""+s.charAt(d+1));
            else if(s.charAt(d+1)=='i'||s.charAt(d+1)=='j')
                p = new pairs(""+s.charAt(d),"i/j");
            else
                p = new pairs(""+s.charAt(d),""+s.charAt(d+1));
            store.add(p);
        }
        
        //ATTENTION YOU MAY LOOSE YOUR BRAIN HERE
        
        int a = 0, b = 0;
        for(Object ve : store){
            pairs p = (pairs)ve;
            //ENCRYPTION STATRS
            //CASE1: BOTH LETTERS ARE IN THE SAME ROW
            if(row1.contains(p.getOne())&&row1.contains(p.getTwo())){             
                if(row1.indexOf(p.getOne())!=4){
                    a = row1.indexOf(p.getOne())+1;
                }else{
                    a = 0;
                }if(row1.indexOf(p.getTwo())!=4){
                    b = row1.indexOf(p.getTwo())+1;
                }else{
                    b = 0;
                }
                pairs resault = new pairs((String)row1.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row2.contains(p.getTwo())){
                
                if(row2.indexOf(p.getOne())!=4)
                    a = row2.indexOf(p.getOne())+1;
                else
                    a = 0;
                if(row2.indexOf(p.getTwo())!=4)
                    b = row2.indexOf(p.getTwo())+1;
                else
                    b = 0;
                pairs resault = new pairs((String)row2.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row3.contains(p.getTwo())){
                
                if(row3.indexOf(p.getOne())!=4)
                    a = row3.indexOf(p.getOne())+1;
                else 
                    a = 0;
                if(row3.indexOf(p.getTwo())!=4)
                    b = row3.indexOf(p.getTwo())+1;
                else
                    b = 0;
                pairs resault = new pairs((String)row3.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row4.contains(p.getTwo())){
                
                if(row4.indexOf(p.getOne())!=4)
                    a = row4.indexOf(p.getOne())+1;
                else
                    a = 0;
                if(row4.indexOf(p.getTwo())!=4)
                    b = row4.indexOf(p.getTwo())+1;
                else
                    b = 0;
                pairs resault = new pairs((String)row4.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row5.contains(p.getTwo())){
             
                if(row5.indexOf(p.getOne())!=4)
                    a = row5.indexOf(p.getOne())+1;
                else 
                    a = 0;
                if(row5.indexOf(p.getTwo())!=4)
                    b = row5.indexOf(p.getTwo())+1;
                else
                    b = 0;
                pairs resault = new pairs((String)row5.get(a),(String)row5.get(b));
                end.add(resault);
            }
            //CASE2: BOTH LETTERS ARE IN THE SAME COLUMN
            else if(row1.contains(p.getOne())&&row2.contains(p.getTwo())&&row1.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row3.contains(p.getTwo())&&row1.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row4.contains(p.getTwo())&&row1.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row5.contains(p.getTwo())&&row1.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row1.contains(p.getTwo())&&row2.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row3.contains(p.getTwo())&&row2.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row4.contains(p.getTwo())&&row2.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row5.contains(p.getTwo())&&row2.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row1.contains(p.getTwo())&&row3.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row2.contains(p.getTwo())&&row3.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row4.contains(p.getTwo())&&row3.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row5.contains(p.getTwo())&&row3.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row1.contains(p.getTwo())&&row4.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row2.contains(p.getTwo())&&row4.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row3.contains(p.getTwo())&&row4.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row5.contains(p.getTwo())&&row4.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row1.contains(p.getTwo())&&row5.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row2.contains(p.getTwo())&&row5.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row3.contains(p.getTwo())&&row5.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row4.contains(p.getTwo())&&row5.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row5.get(b));
                end.add(resault);
            }
            //CASE3: THE TWO LETTERS ARE COMPLETELY DEFFIRENT (EACH IN A DEFFIRENT ROW AND DEFFIRENT COLUMN)
            else if(row1.contains(p.getOne())&&row2.contains(p.getTwo())){
             
                a = row2.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row3.contains(p.getTwo())){
              
                a = row3.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row4.contains(p.getTwo())){
                
                a = row4.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row5.contains(p.getTwo())){
            
                a = row5.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row1.contains(p.getTwo())){
          
                a = row1.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row3.contains(p.getTwo())){
           
                a = row3.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row4.contains(p.getTwo())){
             
                a = row4.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row5.contains(p.getTwo())){
              
                a = row5.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row1.contains(p.getTwo())){
            
                a = row1.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row2.contains(p.getTwo())){
               
                a = row2.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row4.contains(p.getTwo())){
              
                a = row4.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row5.contains(p.getTwo())){
               
                a = row5.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row1.contains(p.getTwo())){
               
                a = row1.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row2.contains(p.getTwo())){
              
                a = row2.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row3.contains(p.getTwo())){
                
                a = row3.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row5.contains(p.getTwo())){
             
                a = row5.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row1.contains(p.getTwo())){
                
                a = row1.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row2.contains(p.getTwo())){
              
                a = row2.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row3.contains(p.getTwo())){
              
                a = row3.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row4.contains(p.getTwo())){
             
                a = row4.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row4.get(b));
                end.add(resault);
            }
            
        }
        
        //ENUMERATING END AND PLACE IT'S ELEMENT INTO A STRING THAT OUR METHOD RETURNS CONSIDERING I/J REPLACED WITH I
        Enumeration ex = end.elements();
        String one , two ,sum="";
        while(ex.hasMoreElements()){
            pairs p = (pairs)ex.nextElement();
            one = p.getOne();
            two = p.getTwo();
            if(one == "i/j")
                one = "i";
            if(two == "i/j")
                two = "i";
            sum+=one+two;
        }
        return sum;//sum is a string which is the cipher text
    }
    
    //DECRYPTION METHOD
    public static String plainText(String cipherText , String keyword){
        Object [] alphabet = {"a","b","c","d","e","f","g","h","i/j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        Vector v = new Vector();
        Vector v2 = new Vector();
        Vector store = new Vector();
        Vector end = new Vector();
        String cipher = "";
        Vector row1 = new Vector();
        Vector row2 = new Vector();
        Vector row3 = new Vector();
        Vector row4 = new Vector();
        Vector row5 = new Vector();
        //checking and manipulating my keyword then adding it's element as first elements of my table
        for(int i = 0; i < keyword.length(); i++){
            char c = keyword.charAt(i);
            if(!v.contains(""+c))
                v.add(""+c);
        }
        //filling the rest of the table with alphabet letters considering no duplication
        for(Object c : alphabet)
            if(!v.contains(c))
                v.add(c);
        //filling 5x5 table using five vectors  which each one holds 5 letters //sum = 25
        for(int i = 0; i < v.size(); i++){
            if(i>=0&&i<5)
                row1.add(v.get(i));
            else if(i>=5&&i<10)
                row2.add(v.get(i));
            else if(i>=10&&i<15)
                row3.add(v.get(i));
            else if(i>=15&&i<20)
                row4.add(v.get(i));
            else if(i>=20&&i<25)
                row5.add(v.get(i));
        }
        //handling my cipherText considering every i or j should be replaced by i/j
        for(int i = 0; i < cipherText.length(); i++){
            if(cipherText.charAt(i)=='i'||cipherText.charAt(i)=='j'){
                String s = "i/j";
                v2.add(s);
            }else{   
                Object c = cipherText.charAt(i);
                v2.add(""+c);
            }
        }
        //classifying my cipherText into pairs  
        for(int d=0; d<v2.size(); d+=2){
            pairs p;
            if(v2.get(d)=="i/j")
                p = new pairs((String)v2.get(d),(String)v2.get(d+1));
            else if(v2.get(d+1)=="i/j")
                p = new pairs((String)v2.get(d),(String)v2.get(d+1));
            else
                p = new pairs(""+cipherText.charAt(d),""+cipherText.charAt(d+1));
            store.add(p);
        }
        
        
        //ATTENTION YOU MAY LOOSE YOUR BRAIN HERE
        
        int a = 0, b = 0;
        for(Object ve : store){
            pairs p = (pairs)ve;
            //DECRYPTION STATRS
            //CASE1: BOTH LETTERS ARE IN THE SAME ROW
            if(row1.contains(p.getOne())&&row1.contains(p.getTwo())){             
                if(row1.indexOf(p.getOne())!=0){
                    a = row1.indexOf(p.getOne())-1;
                }else{
                    a = 4;
                }if(row1.indexOf(p.getTwo())!=0){
                    b = row1.indexOf(p.getTwo())-1;
                }else{
                    b = 4;
                }
                pairs resault = new pairs((String)row1.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row2.contains(p.getTwo())){
                
                if(row2.indexOf(p.getOne())!=0)
                    a = row2.indexOf(p.getOne())-1;
                else
                    a = 4;
                if(row2.indexOf(p.getTwo())!=0)
                    b = row2.indexOf(p.getTwo())-1;
                else
                    b = 4;
                pairs resault = new pairs((String)row2.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row3.contains(p.getTwo())){
                
                if(row3.indexOf(p.getOne())!=0)
                    a = row3.indexOf(p.getOne())-1;
                else 
                    a = 4;
                if(row3.indexOf(p.getTwo())!=0)
                    b = row3.indexOf(p.getTwo())-1;
                else
                    b = 4;
                pairs resault = new pairs((String)row3.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row4.contains(p.getTwo())){
                
                if(row4.indexOf(p.getOne())!=0)
                    a = row4.indexOf(p.getOne())-1;
                else
                    a = 4;
                if(row4.indexOf(p.getTwo())!=0)
                    b = row4.indexOf(p.getTwo())-1;
                else
                    b = 4;
                pairs resault = new pairs((String)row4.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row5.contains(p.getTwo())){
             
                if(row5.indexOf(p.getOne())!=0)
                    a = row5.indexOf(p.getOne())-1;
                else 
                    a = 4;
                if(row5.indexOf(p.getTwo())!=0)
                    b = row5.indexOf(p.getTwo())-1;
                else
                    b = 4;
                pairs resault = new pairs((String)row5.get(a),(String)row5.get(b));
                end.add(resault);
            }
            //CASE2: BOTH LETTERS ARE IN THE SAME COLUMN
            else if(row1.contains(p.getOne())&&row2.contains(p.getTwo())&&row1.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row3.contains(p.getTwo())&&row1.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row4.contains(p.getTwo())&&row1.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row5.contains(p.getTwo())&&row1.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row1.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row5.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row1.contains(p.getTwo())&&row2.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row3.contains(p.getTwo())&&row2.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row4.contains(p.getTwo())&&row2.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row5.contains(p.getTwo())&&row2.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row2.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row1.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row1.contains(p.getTwo())&&row3.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row2.contains(p.getTwo())&&row3.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row4.contains(p.getTwo())&&row3.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row5.contains(p.getTwo())&&row3.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row3.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row2.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row1.contains(p.getTwo())&&row4.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row2.contains(p.getTwo())&&row4.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row3.contains(p.getTwo())&&row4.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row5.contains(p.getTwo())&&row4.indexOf(p.getOne())==row5.indexOf(p.getTwo())){
                a = row4.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row3.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row1.contains(p.getTwo())&&row5.indexOf(p.getOne())==row1.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row2.contains(p.getTwo())&&row5.indexOf(p.getOne())==row2.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row3.contains(p.getTwo())&&row5.indexOf(p.getOne())==row3.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row4.contains(p.getTwo())&&row5.indexOf(p.getOne())==row4.indexOf(p.getTwo())){
                a = row5.indexOf(p.getOne());
                b = a;
                pairs resault = new pairs((String)row4.get(a),(String)row3.get(b));
                end.add(resault);
            }
            //CASE3: THE TWO LETTERS ARE COMPLETELY DEFFIRENT (EACH IN A DEFFIRENT ROW AND DEFFIRENT COLUMN)
            else if(row1.contains(p.getOne())&&row2.contains(p.getTwo())){
             
                a = row2.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row3.contains(p.getTwo())){
              
                a = row3.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row4.contains(p.getTwo())){
                
                a = row4.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row1.contains(p.getOne())&&row5.contains(p.getTwo())){
            
                a = row5.indexOf(p.getTwo());
                b = row1.indexOf(p.getOne());
                pairs resault = new pairs((String)row1.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row1.contains(p.getTwo())){
          
                a = row1.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row3.contains(p.getTwo())){
           
                a = row3.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row4.contains(p.getTwo())){
             
                a = row4.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row2.contains(p.getOne())&&row5.contains(p.getTwo())){
              
                a = row5.indexOf(p.getTwo());
                b = row2.indexOf(p.getOne());
                pairs resault = new pairs((String)row2.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row1.contains(p.getTwo())){
            
                a = row1.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row2.contains(p.getTwo())){
               
                a = row2.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row4.contains(p.getTwo())){
              
                a = row4.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row4.get(b));
                end.add(resault);
            }else if(row3.contains(p.getOne())&&row5.contains(p.getTwo())){
               
                a = row5.indexOf(p.getTwo());
                b = row3.indexOf(p.getOne());
                pairs resault = new pairs((String)row3.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row1.contains(p.getTwo())){
               
                a = row1.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row2.contains(p.getTwo())){
              
                a = row2.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row3.contains(p.getTwo())){
                
                a = row3.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row4.contains(p.getOne())&&row5.contains(p.getTwo())){
             
                a = row5.indexOf(p.getTwo());
                b = row4.indexOf(p.getOne());
                pairs resault = new pairs((String)row4.get(a),(String)row5.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row1.contains(p.getTwo())){
                
                a = row1.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row1.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row2.contains(p.getTwo())){
              
                a = row2.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row2.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row3.contains(p.getTwo())){
              
                a = row3.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row3.get(b));
                end.add(resault);
            }else if(row5.contains(p.getOne())&&row4.contains(p.getTwo())){
             
                a = row4.indexOf(p.getTwo());
                b = row5.indexOf(p.getOne());
                pairs resault = new pairs((String)row5.get(a),(String)row4.get(b));
                end.add(resault);
            }
            
        }
        
        Enumeration ex = end.elements();
        
        String one , two ,sum="";
        while(ex.hasMoreElements()){
            
            pairs p = (pairs)ex.nextElement();
            one = p.getOne();
            two = p.getTwo();
            if(one == "i/j")
                one = "i";
            if(two == "i/j")
                two = "i";
            sum+=one+two;
        }
        return sum;
    }
    
}
class pairs{
    String one , two;
    pairs(){
        
    }
    pairs(String one , String two){
        this.one = one;
        this.two = two;
    }
    public String getOne(){
        return one;
    }
    public String getTwo(){
        return two;
    }
}
