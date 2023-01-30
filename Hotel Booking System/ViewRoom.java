 

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ViewRoom extends JFrame {
    private DefaultListModel<String> roomItems;
    private JList<String> roomList;

    private JButton bookRoom;
    private JButton backBtn;
    
    private JLabel arrivalDate;
    private JLabel departureDate;
    private JTextField aDate;
    private JTextField dDate;
    private JLabel lblPic1;
    private ImageIcon icon1;

    private Integer customerId;

    private Database database;

    ViewRoom(Integer customerId) {
        setLayout(new FlowLayout());
        this.customerId = customerId;
        database = new Database();

        roomItems = new DefaultListModel<>();
        roomList = new JList<>(roomItems);
        JScrollPane listScroll = new JScrollPane(roomList);
        listScroll.setPreferredSize(new Dimension(250, 80));
        listScroll.setBackground(Color.black);
        listScroll.setForeground(Color.white);

        bookRoom = new JButton("Book Room");
        backBtn = new JButton("Back");
        
        
        arrivalDate = new JLabel("Enter Arrival Date");
        departureDate = new JLabel("Enter Departure Date");
        aDate = new JTextField(10);
        dDate = new JTextField(10);
        icon1 = new ImageIcon("hotel.jpg");
        lblPic1 = new JLabel();
        lblPic1.setIcon(icon1);
        
        
        Container con = getContentPane();
        //Making the container a Flow Layout
        con.setBackground(Color.black);
        con.setLayout(new FlowLayout());    

        JLabel lblRooms = new JLabel("View Rooms");
        con.add(lblRooms);
        lblRooms.setForeground(Color.white);        

        con.add(listScroll);

        
        con.add(arrivalDate);
        arrivalDate.setForeground(Color.white); 
        con.add(aDate);
        con.add(departureDate);
        departureDate.setForeground(Color.white); 
        
        con.add(dDate);
        con.add(bookRoom);


        add(backBtn);
        con.add(lblPic1);

        buttonActions();
        getRooms();

        pack();
        setVisible(true);
        setSize(300,300);
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

        roomList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String listItem = roomList.getSelectedValue();
                int roomId = Integer.parseInt(listItem.split("-")[0].trim());

                try {
                    String query = "SELECT * FROM `room` WHERE `roomid` = " + roomId;
                    database.statement = database.conn.createStatement();
                    database.resultSet = database.statement.executeQuery(query);                    

                    while (database.resultSet.next()) {
                        String roomid = "RoomID: " + database.resultSet.getString("roomid") + "\n";
                        String roomtype = "Room Type: " + database.resultSet.getString("roomtype") + "\n";
                        String floorlevel = "Floor Level: " + database.resultSet.getString("floorlevel") + "\n";
                        

                        JOptionPane.showMessageDialog(null, roomid + roomtype + floorlevel);
                            
                    }
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        });

        bookRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String listItem = roomList.getSelectedValue();
                int roomId = Integer.parseInt(listItem.split("-")[0].trim());
                String arrivalDate = aDate.getText();
                String departureDate =dDate.getText();
                int manager_id = 1;
                String booking_status = "Pending";
                String payment_status = "";

                try {

                String query = "INSERT INTO booking (customerid, roomid, managerid, arrivaldate, departuredate, bookingstatus, paid) " +
                        "VALUES ('" + customerId + "', '" + roomId + "', '" + manager_id + "', '" + arrivalDate + "', '" + departureDate + "', '" + booking_status + "', '" + payment_status + "')";
                database.statement = database.conn.createStatement();
                database.statement.executeUpdate(query);

            } catch (SQLException s) {
                s.printStackTrace();
            }

            }
        });
    }

    public void getRooms() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date cDate = new Date(System.currentTimeMillis());
            String currentDate = dateFormat.format(cDate); // Process to get current date in YYYY-MM-DD format

            String query = "SELECT * FROM room"; 
            database.statement = database.conn.createStatement();
                    database.resultSet = database.statement.executeQuery(query);            

            while (database.resultSet.next()) {
                String roomId = database.resultSet.getString("roomid");
                String roomtype = database.resultSet.getString("roomtype");

                roomItems.addElement(roomId + " - " + roomtype);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
