import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomerMain extends JFrame {
    private JButton btnViewRooms;
    private JButton btnViewBookings;
    private JButton btnUpdate;
    private JButton btnLogout;
    private JLabel lblPic1;
    ImageIcon icon1;

    private Integer customerId;
    //private String role;

    public CustomerMain(int customerId) {
        setLayout(new FlowLayout());

        this.customerId = customerId;
        //this.role = role;      
        
        //Declaring a container for the components 
        Container con = getContentPane();
        
        con.setBackground(Color.black);
        
        //Making the container a Flow Layout
        con.setLayout(new FlowLayout());        
        
        //Creating new JButon objects
        btnViewRooms = new JButton("View Rooms");
        btnViewBookings = new JButton("View Bookings");
        btnUpdate = new JButton("Edit Account Details");
        btnLogout = new JButton("Logout");
        icon1 = new ImageIcon("hotel1.jpg");
        lblPic1 = new JLabel();
        lblPic1.setIcon(icon1);
        
        
        //Adding the components to the container
        con.add(btnViewRooms);
        con.add(btnViewBookings);
        con.add(btnUpdate);
        con.add(btnLogout);
        con.add(lblPic1);

        buttonActions();

        pack();
        setVisible(true);
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonActions() {
        btnViewRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewRoom(customerId);
            }
        });

        btnViewBookings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewBookings(customerId);
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditAccount(customerId);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
    }
}
