import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class SimpleInventoryGUI extends JFrame {
    private ArrayList<Product> products = new ArrayList<>();
    private DefaultTableModel tableData;
    private JTable productTable;
    private JTextField nameInput;
    private JTextField countInput;

    public SimpleInventoryGUI() {
        setTitle("Simple Inventory System");
        setSize(420, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLbl = new JLabel("Product:");
        nameLbl.setBounds(15, 15, 60, 25);
        add(nameLbl);

        nameInput = new JTextField();
        nameInput.setBounds(80, 15, 120, 25);
        add(nameInput);

        JLabel countLbl = new JLabel("Count:");
        countLbl.setBounds(220, 15, 50, 25);
        add(countLbl);

        countInput = new JTextField();
        countInput.setBounds(270, 15, 50, 25);
        add(countInput);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(340, 15, 60, 25);
        add(addBtn);

        JButton editBtn = new JButton("Edit");
        editBtn.setBounds(340, 50, 60, 25);
        add(editBtn);

        JButton removeBtn = new JButton("Remove");
        removeBtn.setBounds(340, 85, 60, 25);
        add(removeBtn);

        tableData = new DefaultTableModel(new Object[]{"Product", "Count"}, 0);
        productTable = new JTable(tableData);
        JScrollPane tablePane = new JScrollPane(productTable);
        tablePane.setBounds(15, 60, 310, 200);
        add(tablePane);

        // Add product action
        addBtn.addActionListener(e -> {
            String pname = nameInput.getText().trim();
            String pcount = countInput.getText().trim();
            if (pname.isEmpty() || pcount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill both fields.");
                return;
            }
            int countValue;
            try {
                countValue = Integer.parseInt(pcount);
                if (countValue < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Count must be a non-negative integer.");
                return;
            }
            products.add(new Product(pname, countValue));
            tableData.addRow(new Object[]{pname, countValue});
            clearInputs();
        });

        // Edit product action
        editBtn.addActionListener(e -> {
            int idx = productTable.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Select a product to edit.");
                return;
            }
            String pname = nameInput.getText().trim();
            String pcount = countInput.getText().trim();
            if (pname.isEmpty() || pcount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill both fields.");
                return;
            }
            int countValue;
            try {
                countValue = Integer.parseInt(pcount);
                if (countValue < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Count must be a non-negative integer.");
                return;
            }
            Product prod = products.get(idx);
            prod.setProductName(pname);
            prod.setProductCount(countValue);
            tableData.setValueAt(pname, idx, 0);
            tableData.setValueAt(countValue, idx, 1);
            clearInputs();
        });

        // Remove product action
        removeBtn.addActionListener(e -> {
            int idx = productTable.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Select a product to remove.");
                return;
            }
            products.remove(idx);
            tableData.removeRow(idx);
            clearInputs();
        });

        // Table row click: load data into input fields
        productTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int idx = productTable.getSelectedRow();
                if (idx != -1) {
                    nameInput.setText(tableData.getValueAt(idx, 0).toString());
                    countInput.setText(tableData.getValueAt(idx, 1).toString());
                }
            }
        });
    }

    private void clearInputs() {
        nameInput.setText("");
        countInput.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleInventoryGUI app = new SimpleInventoryGUI();
            app.setVisible(true);
        });
    }
}
