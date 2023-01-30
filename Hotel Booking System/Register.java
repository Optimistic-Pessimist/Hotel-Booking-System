 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class Register extends JFrame {
    
    private JTextField txtTitle;
    private JTextField txtFirstname;
    private JTextField txtLastname;
    private JTextField txtUEmail;
    private JTextField txtMobileNo;
    private JPasswordField txtPass;
    private JTextField txtAddress1;
    private JTextField txtTown;
    private JTextField txtCounty;
    private JTextField txtPostCode;
    private JTextField txtPaymentMethod;
    private JLabel lblPic1;
    private ImageIcon icon1;
    
    
    
    
    
    private JLabel lblTitle;    
    private JLabel lblFirstname;
    private JLabel lblLastname;
    private JLabel lblUEmail; 
    private JLabel lblMobileNo;
    private JLabel lblPass;
    private JLabel lblAddress1;
    private JLabel lblTown;
    private JLabel lblCounty;
    private JLabel lblPostCode;
    private JLabel lblPaymentMethod;
    
    

    private JButton btnReg;
    private JButton btnBack;

    private Database database;
    
    /**
     * Constructor for objects of class Login
     */
    
    public Register() {
        
        database = new Database();
        
        setLayout(new FlowLayout());
        
        //Creating new JTextField objects and values
        txtTitle = new JTextField(15);
        txtFirstname = new JTextField(15);
        txtAddress1 = new JTextField(15);
        txtLastname = new JTextField(15);
        txtUEmail = new JTextField(15);
        txtMobileNo = new JTextField(15);
        txtPass = new JPasswordField(15);
        txtTown = new JTextField(15);
        txtCounty = new JTextField(15);
        txtPostCode = new JTextField(15);
        txtPaymentMethod = new JTextField(15);
        
        
        
        
        //Creating new JButton objects and values
        btnReg = new JButton("Register");
        btnBack = new JButton("Back");

        //Creating new JLabel objects and label values
        lblTitle = new JLabel("Title:");
        lblTitle.setForeground(Color.white);
        lblFirstname = new JLabel("First Name:");
        lblFirstname.setForeground(Color.white);
        lblLastname = new JLabel("Last Name");
        lblLastname.setForeground(Color.white);        
        lblUEmail = new JLabel("EMail");
        lblUEmail.setForeground(Color.white);
        lblMobileNo = new JLabel("MobileNo");
        lblMobileNo.setForeground(Color.white);
        lblPass = new JLabel("Password");
        lblPass.setForeground(Color.white);
        lblAddress1 = new JLabel("Address1");
        lblAddress1.setForeground(Color.white);
        lblTown = new JLabel("Town");
        lblTown.setForeground(Color.white);
        lblCounty = new JLabel("County");
        lblCounty.setForeground(Color.white);
        lblPostCode = new JLabel("PostCode");
        lblPostCode.setForeground(Color.white);
        lblPaymentMethod = new JLabel("PaymentMethod");
        lblPaymentMethod.setForeground(Color.white);
        icon1 = new ImageIcon("hotel1.jpg");
        lblPic1 = new JLabel();
        lblPic1.setIcon(icon1);
        
        //Creating a container for the components 
        Container con = getContentPane();
        con.setBackground(Color.black);
        
        //Making the container a Flow Layout
        con.setLayout(new FlowLayout());
        
        //Adding the components to the container
        con.add(lblTitle);
        con.add(txtTitle);
        con.add(lblFirstname);
        con.add(txtFirstname);
        con.add(lblLastname);
        con.add(txtLastname);
        con.add(lblUEmail);
        con.add(txtUEmail);
        con.add(lblMobileNo);
        con.add(txtMobileNo); 
        con.add(lblPass);
        con.add(txtPass);
        con.add(lblAddress1);
        con.add(txtAddress1);
        con.add(lblTown);
        con.add(txtTown);
        con.add(lblCounty);
        con.add(txtCounty);
        con.add(lblPostCode);
        con.add(txtPostCode);
        con.add(lblPaymentMethod);
        con.add(txtPaymentMethod);
        
        
        
        
        con.add(btnReg);
        con.add(btnBack); 
        con.add(lblPic1);
        
        
        
        
        //Calling the buttonActions() method
        buttonActions();

        pack();
        setVisible(true);
        setSize(700,400);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //buttonActions() method
    private void buttonActions() {
        btnReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "INSERT INTO customer(Title, FirstName, Lastname, EMail, MobileNo, Password,Address1, Town, County, PostCode, PaymentMethod) "
                        + "VALUES ('" + txtTitle.getText() + "', '" + txtFirstname.getText() + "', '" +
                        txtLastname.getText() + "', '" + txtUEmail.getText() + "', '" + txtMobileNo.getText() + "', '" + txtPass.getText() + "', '" + txtAddress1.getText() + "', '" + txtTown.getText()+ "', '" + txtCounty.getText() + "', '" + txtPostCode.getText() + "', '" + txtPaymentMethod.getText() + "');";



                    database.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "You have successfully registered!");

                    dispose();
                    new Login();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
    }
}
