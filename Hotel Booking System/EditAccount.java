//Imports everything needed
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditAccount extends JFrame//Shows is a JFrame
{
    //Declaring JLabels, JTextFields, JComboBoxes
    JLabel lblTitle = null;
    JLabel lblUEMail = null;
    JLabel lblPass = null;
    JLabel lblFn = null;
    JLabel lblLn = null;
    JLabel lblMobile = null;
    JLabel lblCheck = null;
    JLabel lblAddress1 = null;
    JLabel lblTown = null;
    JLabel lblCounty = null;
    JLabel lblPostCode = null;
    //JLabel lblRegistered = null;

   JTextField txtTitle = null;
    JTextField txtUEMail = null;
    JTextField txtPass = null;
    JTextField txtFn = null;
    JTextField txtLn = null;
    JTextField txtMobile = null;
   
    JTextField txtAddress1 = null;
    JTextField txtTown = null;
    JTextField txtCounty = null;
    JTextField txtPostCode = null;
    
    private JLabel lblPic1;
    ImageIcon icon1;
    
    

    private Integer customerId;
    
    

    //Declaring JButtons
    JButton btnLog, btnBack, btnEdit = null;
    //The following is declaring variables to be used to connect to the database
    //The variables are taken from the JDBC demos provided and changed to suit our needs
    Database database = new Database();
    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public EditAccount(Integer customerId)//Constructor for EditAccount
    {//Uses a lot of stuff from the RegPage
        Database database = new Database();

        this.customerId = customerId;
        //this.role = role;
        //Declares label values and textbox lengths
        lblTitle = new JLabel("Title:");
        lblTitle.setForeground(Color.white);
        lblFn = new JLabel("First Name:");
        lblFn.setForeground(Color.white);
        lblLn = new JLabel("Last Name:");
        lblLn.setForeground(Color.white);
        lblUEMail = new JLabel("EMail:");
        lblUEMail.setForeground(Color.white);
        lblMobile = new JLabel("Mobile No:");
        lblMobile.setForeground(Color.white);
        lblPass = new JLabel("Password:");
        lblPass.setForeground(Color.white);
        lblAddress1 = new JLabel("Address1:");
        lblAddress1.setForeground(Color.white);
        lblTown = new JLabel("Town:");
        lblTown.setForeground(Color.white);
        lblCounty = new JLabel("County:");
        lblCounty.setForeground(Color.white);
        lblPostCode = new JLabel("Post Code:");
        lblPostCode.setForeground(Color.white);
        icon1 = new ImageIcon("hotel1.jpg");
        lblPic1 = new JLabel();
        lblPic1.setIcon(icon1);
        
        

        //Declares more text boxes
        txtTitle = new JTextField(10);
        txtFn = new JTextField(20);
        txtLn = new JTextField(30);
        txtUEMail = new JTextField(50);
        txtMobile = new JTextField(30);
        txtPass = new JTextField(50);
        txtAddress1 = new JTextField(30);
        txtTown = new JTextField(30);
        txtCounty = new JTextField(20);
        txtPostCode = new JTextField(15);
        
        
        
        
        
        
        

        btnEdit = new JButton("Update Details");
        btnBack = new JButton("Back");
        lblCheck = new JLabel("");
        lblCheck.setForeground(Color.white);
        //Making the container and setting as a flowlayout
        Container con = getContentPane();
        con.setBackground(Color.black);
        con.setLayout(new FlowLayout());
        //The following adds the appropriate components to the screen depending on
        //what type of user account is being edited


            con.add(lblTitle);
            con.add(txtTitle);
            con.add(lblFn);
            con.add(txtFn);
            con.add(lblLn);
            con.add(txtLn);
            con.add(lblUEMail);
            con.add(txtUEMail);
            con.add(lblMobile);
            con.add(txtMobile);
            
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
            





        System.out.println(customerId);
        //System.out.println(role);              


        //adds the buttons to the container
        con.add(btnBack);
        con.add(btnEdit);
        con.add(lblCheck);
        con.add(lblPic1);

        //Packs, sets inital size and makes JFrame visible
        pack();
        setSize(600,400);
        setVisible(true);
        fillData();//Calls function fillData to fill the text fields

        btnBack.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    //Redirects users to where they came from

                        CustomerMain redirect = new CustomerMain(customerId);
                        dispose();

                    }
            });
       btnEdit.addActionListener(new ActionListener()
           {
                public void actionPerformed(ActionEvent e)
                {
                   setData();//calls function setData to commit the changes to database
                  }
         });
    }
    public void fillData(){//Function to fill the textboxes with previous information
        try{

            database.statement = database.conn.createStatement();//Get all data from whichever type being edited that matches the username
            database.resultSet = database.statement.executeQuery("SELECT * FROM customer WHERE " +  "CustomerID = \"" + customerId + "\"");

                while (database.resultSet.next()) {//Fill the text fields appropriate
                    txtTitle.setText(database.resultSet.getString("Title"));
                    txtFn.setText(database.resultSet.getString("FirstName"));
                    txtLn.setText(database.resultSet.getString("LastName"));
                    txtUEMail.setText(database.resultSet.getString("EMail"));
                    txtMobile.setText(database.resultSet.getString("MobileNo"));
                    txtPass.setText(database.resultSet.getString("Password"));
                    txtAddress1.setText(database.resultSet.getString("Address1"));
                    txtTown.setText(database.resultSet.getString("Town"));
                    txtCounty.setText(database.resultSet.getString("County"));
                    txtPostCode.setText(database.resultSet.getString("PostCode"));
                    
                    
                    
             
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }

}
    public void setData()//Function to commit changes to database
    {
            try{
                
                
                        database.statement = database.conn.createStatement();//Commit changes to the Student table entry where the username is the same
                        database.statement.executeUpdate("UPDATE Customer SET Title = \"" + txtTitle.getText() + "\", FirstName = \"" +
                        txtFn.getText() + "\", LastName = \"" + txtLn.getText() + "\", EMail  = \"" + txtUEMail.getText() + 
                        "\", MobileNo = \"" + txtMobile.getText() + "\", Password  = \"" + txtPass.getText() +
                        "\",Address1  = \"" + txtAddress1.getText() + 
                        "\",Town  = \"" + txtTown.getText() + 
                        "\",County  = \"" + txtCounty.getText() + 
                        "\", PostCode  = \"" + txtPostCode.getText() + 
                        
                        "\" WHERE CustomerID = \"" +
                        customerId + "\"");
                        
                        }
                        catch (SQLException s){
                            System.out.println("SQL statement is not executed!");//For testing purposes
                            s.printStackTrace();
                        }
                                  
                
                
                   
    }
}
