import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CommentWindow extends JFrame {
    private int fileId;
    private JTable commentsTable;
    private DefaultTableModel tableModel;

    public CommentWindow(int fileId) {
        this.fileId = fileId;
        setTitle("Comment Window for File ID: " + fileId);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id_comment");
        tableModel.addColumn("Comment");
        commentsTable = new JTable(tableModel);

        List<Comment> comments = getCommentsForFile(fileId);
        for (Comment comment : comments) {
            tableModel.addRow(new Object[]{comment.getId(), comment.getText()});
        }

        JScrollPane scrollPane = new JScrollPane(commentsTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addCommentButton = new JButton("Ajouter un commentaire");
        JButton deleteFileButton = new JButton("Supprimer un fichier");
        buttonPanel.add(addCommentButton);
        buttonPanel.add(deleteFileButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commentText = JOptionPane.showInputDialog(CommentWindow.this, "Entrez votre commentaire :");
                if (commentText != null && !commentText.isEmpty()) {
                    addComment(commentText);
                    int newCommentId = getLastCommentId();
                    if (newCommentId != -1) {
                        tableModel.addRow(new Object[]{newCommentId, commentText});
                    }
                }
            }
        });

        deleteFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(CommentWindow.this, "Voulez-vous vraiment supprimer ce fichier?");
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteFile();
                    CommentWindow.this.dispose();
                }
            }
        });

        pack();
    }

    // Method to fetch comments based on fileId
    private List<Comment> getCommentsForFile(int fileId) {
		return null;
        // Your implementation to fetch comments from the database based on fileId
    }

    // Method to add a comment
    private void addComment(String commentText) {
        // Your implementation to add a comment to the database
    }

    // Method to get the last comment id
    private int getLastCommentId() {
		return fileId;
        // Your implementation to get the last inserted comment id from the database
    }

    // Method to delete a file
    private void deleteFile() {
        // Your implementation to delete a file from the database
    }
}
