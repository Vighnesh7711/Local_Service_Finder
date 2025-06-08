import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminForm1 extends javax.swing.JFrame {

    public AdminForm1() {
        initComponents();
        fetchAndDisplayData(); // Call this method to load data on form load
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        // existing components here...

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Address", "Contact No", "DOB", "Service Type", "Experience"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void fetchAndDisplayData() {
        // Establish the database connection
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServiceApp", "root", "student");

            // SQL query to retrieve the data
            String sql = "SELECT * FROM ServiceProviders where service_type='Electrician Services'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Get the table model
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // Clear existing rows
            model.setRowCount(0);

            // Loop through the ResultSet and add rows to the table model
            while (rs.next()) {
                Object[] row = {
                    rs.getString("address"),
                    rs.getString("contact_number"),
                    rs.getString("DOB"),
                    rs.getString("service_type"),
                    rs.getString("Experience")
                };
                model.addRow(row);
            }

            // Close resources
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm1().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
