import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class FilesWindow extends JFrame {
    private Connection connection;
    private JTable table;

    private DefaultTableModel tableModel;

    public FilesWindow(Connection connection) {
        this.connection = connection;
        tableModel = new DefaultTableModel();
        setTitle("Liste des fichiers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM fichier";
            ResultSet resultSet = statement.executeQuery(sql);

            tableModel.addColumn("ID");
            tableModel.addColumn("Nom");
            tableModel.addColumn("Lien");

            table = new JTable(tableModel);

            while (resultSet.next()) {
                int id = resultSet.getInt("id_fichier");
                String nom = resultSet.getString("nom_fichier");
                String lien = resultSet.getString("lien_fichier");
                Object[] row = {id, nom, lien};
                tableModel.addRow(row);
            }

            table = new JTable(tableModel);

            // Add a MouseListener to handle the click event on table rows
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        int row = table.rowAtPoint(e.getPoint());
                        int fileId = (int) table.getValueAt(row, 0);
                        CommentWindow commentWindow = new CommentWindow(fileId);
                        commentWindow.setVisible(true);
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
            pack();
            setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
