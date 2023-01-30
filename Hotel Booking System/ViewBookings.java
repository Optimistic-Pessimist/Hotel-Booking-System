import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

class ViewBookings extends JFrame {
    private DefaultListModel<String> bookingItems;
    private JList<String> bookingsList;

    private JButton deleteBtn;
    private JButton backBtn;
    private JLabel lblPic1;
    private ImageIcon icon1;

    private Integer customerId;
    
    private String roomId;
    //private String role;
    private Database database;

    ViewBookings(Integer customerId) {
        setLayout(new FlowLayout());

        this.customerId = customerId;
        //this.role = role;
        database = new Database();
        
        //Creating a container for the components 
        Container con = getContentPane();
        //Making the container a Flow Layout
        con.setBackground(Color.black);
        con.setLayout(new FlowLayout());    
             
        bookingItems = new DefaultListModel<>();
        bookingsList = new JList<>(bookingItems);
        JScrollPane listScroll = new JScrollPane(bookingsList);
        listScroll.setPreferredSize(new Dimension(250, 80));
        listScroll.setBackground(Color.black);
        listScroll.setForeground(Color.white);
        icon1 = new ImageIcon("hotel1.jpg");
        lblPic1 = new JLabel();
        lblPic1.setIcon(icon1);
        

        deleteBtn = new JButton("Delete Bookings");
        backBtn = new JButton("Back");

        con.add(listScroll);

        con.add(deleteBtn);
        
        con.add(backBtn);
        con.add(lblPic1);
        

        buttonActions();
        getBookings();

        pack();
        setVisible(true);
        setSize(300,200);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void buttonActions() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomerMain(customerId);
            }
        });

        bookingsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    String listItem = bookingsList.getSelectedValue();
                    int roomId = Integer.parseInt(listItem.split("-")[1].trim());

                    String query = "SELECT * FROM room WHERE `RoomID` = " + roomId;
                    
                    database.statement = database.conn.createStatement();
                    database.resultSet = database.statement.executeQuery(query);                    

                    while (database.resultSet.next()) {
                        String roomid = "RoomID: " + database.resultSet.getString("RoomID") + "\n";
                        String roomtype = "Room Type: " + database.resultSet.getString("RoomType") + "\n";
                        String floorlevel = "Floor Level: " + database.resultSet.getString("FloorLevel") + "\n";
                        

                        JOptionPane.showMessageDialog(null, roomid + roomtype + floorlevel);
                } 
            }
                catch (SQLException s) {
                    s.printStackTrace();
                } 
                catch (NullPointerException e1) {

                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String listItem = bookingsList.getSelectedValue();

                int bookingId = Integer.parseInt(listItem.split("-")[0].trim());
                try {
                    String query = "DELETE FROM booking WHERE BookingID = " + bookingId;
                    database.statement.executeUpdate(query);

                    bookingItems.removeAllElements();
                    getBookings();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        });
    }

    public void getBookings() {
        try {
            ArrayList<Integer> bookingIDs = new ArrayList<Integer>();
            ArrayList<Integer> roomIDs = new ArrayList<Integer>();

            String query = "SELECT * FROM booking WHERE  CustomerID = " + customerId;
            
            database.statement = database.conn.createStatement();
                    database.resultSet = database.statement.executeQuery(query);
            
            while (database.resultSet.next()) {
                int bookingId = database.resultSet.getInt(1);
                int roomId = database.resultSet.getInt(3);

                bookingIDs.add(bookingId);
                roomIDs.add(roomId);
            }

            for (int i = 0; i < bookingIDs.size(); i ++) {
                query = "SELECT * FROM room WHERE RoomID = " + roomIDs.get(i);
                database.statement = database.conn.createStatement();
                    database.resultSet = database.statement.executeQuery(query);
                
                while (database.resultSet.next()) {
                    bookingItems.addElement(bookingIDs.get(i) + " - " + roomIDs.get(i) + " - " + database.resultSet.getString("roomtype"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
