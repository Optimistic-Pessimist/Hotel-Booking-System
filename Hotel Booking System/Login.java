import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

class Login extends JFrame {
    private JTextField txtUEmail;
    private JPasswordField txtPass;

    private JButton btnLogin;
    private JButton btnReg;
            
    private JLabel lblUEmail;
    private JLabel lblPass;
    private JLabel lblAcc;
    private JLabel lblConn;
    private JLabel lblCheck; 
    private JLabel lblPic1;
    private JLabel lblPic2;
    ImageIcon icon1, icon2;
       
    private JComboBox<String> accountType;

    private Database database;

    /**
     * Constructor for objects of class Login
     */
    public Login()
    {
        
        database = new Database();         
        setLayout(new FlowLayout());         
        String[] accountTypes = { "Customer", "Manager", };
        //Creating the Combo Box and passing an array into it to display account type
        accountType = new JComboBox(accountTypes);        
        
        //Creating new JLabel objects and label values
        lblUEmail = new JLabel("Customer Email:");
        lblUEmail.setForeground(Color.white);
        lblPass = new JLabel("Password:");
        lblPass.setForeground(Color.white);
        lblAcc = new JLabel("Account Type:");
        lblAcc.setForeground(Color.white);
        lblConn = new JLabel("");
        lblConn.setForeground(Color.white);
        
        lblCheck = new JLabel("");  
        lblCheck.setForeground(Color.white);
        
        icon1 = new ImageIcon("hotel1.jpg");        
        lblPic1 = new JLabel();        
        
        lblPic1.setIcon(icon1);        
        
        //Creating new JTextField object and value
        txtUEmail = new JTextField(15);
        
        //Creating new JPasswordField object and value
        txtPass = new JPasswordField(15);     
        
        //Creating the JButton objects
        btnReg = new JButton("Register");
        btnLogin = new JButton("Login");
        //Declaring a label that we can use to provide information
        //ie. it could say "Incorrect Login Details"   
        
        //Creating a container for the components 
        Container con = getContentPane();
        //Making the container a Flow Layout
        con.setBackground(Color.black);
        con.setLayout(new FlowLayout());
                     
        //Adding the components to the container
        con.add(lblUEmail);
        con.add(txtUEmail);
        con.add(lblPass);
        con.add(txtPass);
        con.add(lblAcc);
        con.add(accountType);
        con.add(btnReg);
        con.add(btnLogin);
        con.add(lblCheck);
        con.add(lblConn);
        con.add(lblPic1);        

        if(database.conn != null)
        {
            lblConn.setText("Successful connection to database");
        }
        else
        {
            lblConn.setText("Cannot connect to database.  Please see your Systems Administrator.");
        }           

        //Calling the buttonActions() method
        buttonActions();
        
        setVisible(true);       
        setSize(400,600);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    
    //buttonActions method
    public void buttonActions() {
        //Event Handler for btnLogin
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userType = Objects.requireNonNull(accountType.getSelectedItem()).toString();
                switch (userType) {
                    case "Customer":
                        try {
                            database.statement = database.conn.createStatement();
                            database.resultSet = database.statement.executeQuery("SELECT * FROM customer");
                            
                            while (database.resultSet.next()) {
                                int customerId = database.resultSet.getInt(1);
                                String user = database.resultSet.getString(5);
                                String pass = database.resultSet.getString(7);


                                if ((user.equals(txtUEmail.getText()))&&(pass.equals(txtPass.getText()))) {
                                    JOptionPane.showMessageDialog(null, "Correct login details!");
                                    dispose();
                                    new CustomerMain(customerId);
                                    return;
                                } 
                            }                                
                               JOptionPane.showMessageDialog(null, "Incorrect login details!");
                                   
                               } catch (SQLException s) {
                                   s.printStackTrace();
                        }
                        break;
                    
                    case "Manager":
                        try {
                            database.statement = database.conn.createStatement();
                            database.resultSet = database.statement.executeQuery("SELECT * FROM manager");

                            while (database.resultSet.next()) {
                                int menagerId = database.resultSet.getInt(1);
                                String user = database.resultSet.getString(4);
                                String pass = database.resultSet.getString(6);

                                if ((user.equals(txtUEmail.getText()))&&(pass.equals(txtPass.getText()))) {
                                    JOptionPane.showMessageDialog(null, "Correct login details!");
                                    dispose();
                                    //new AdminMain(txtUEmail.getText());
                                    return;
                                }
                            }

                            JOptionPane.showMessageDialog(null, "Incorrect login details!");
                        } catch (SQLException s) {
                            s.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        //Event handler for btnReg
        btnReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Register();
            }
        });
    }
}
