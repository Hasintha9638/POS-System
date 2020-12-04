
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hasintha
 */
public class inventory extends javax.swing.JFrame {
    Connect con = new Connect();
    DefaultTableModel dtm;
    ResultSet rs;
    
    /**
     * Creates new form inventory
     */
    public inventory() {
        initComponents();
        Table();
        this.setExtendedState(MAXIMIZED_BOTH);
        editBtn.setVisible(false);
        viewBtn.setVisible(false);

    }
    String temp = "";

    //about load table
    public void Table() {
        editBtn.setVisible(false);
        viewBtn.setVisible(false);
        
        dtm = (DefaultTableModel) jTable1.getModel();
        String location = "";
        String stock = "";
     
       
        try {
            
            String query = "select * from item";
            dtm.setRowCount(0);
          
            rs = con.getQuery(query);
            while (rs.next()) {
                if (rs.getString("deleted").equals("1")) {
                    continue;
                } else {
                    String itemid = rs.getString("id");
                    String name = rs.getString("name");
                    String itembuying_price = rs.getString("buying_price");
                    String itemretail_price = rs.getString("retail_price");
                    String itemwholesale_price = rs.getString("wholesale_price");
                    String itemqty = rs.getString("qty");
                    String expired_date = rs.getString("expired_date");
                    int catogoryid = rs.getInt("catogory_id");
                    int supplierid = rs.getInt("supplier_id");
                    int stockid = rs.getInt("stock_id");
                    int storeid = rs.getInt("store_id");
                    String catogory = "";
                    String supplier = "";
                    
                    String query4 = "select name from supplier where id='" + supplierid + "'";
                    ResultSet rs2 = con.getQuery(query4);
                    while (rs2.next()) {
                        supplier = rs2.getString("name");
                    }
                    String query5 = "select name from catogory where id='" + catogoryid + "'";
                    ResultSet rs3 = con.getQuery(query5);
                    while (rs3.next()) {
                        catogory = rs3.getString("name");
                    }
                    String query7 = "select name from stock where id='" + stockid + "'";
                    ResultSet rs7 = con.getQuery(query7);
                    while (rs7.next()) {
                        stock = rs7.getString("name");
                    }
                    String query6 = "select name from store where id='" + storeid + "'";
                    ResultSet rs4 = con.getQuery(query6);
                    while (rs4.next()) {
                        location = rs4.getString("name");
                    }
                    dtm.addRow(new Object[]{itemid, name, catogory, supplier, stock, location, itemqty, itembuying_price, itemretail_price, itemwholesale_price, expired_date});
                    rs2.close();
                    rs3.close();
                    rs7.close();
                    rs4.close();
//  con=null;
                }
      
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }finally{
             System.gc();  
             
        }
        
    }
    
    //search code
    public void search() {

        try {
           
            String query = "select * from item where id like '" + jtxtSearch.getText() + "%' or name like '" + jtxtSearch.getText() + "%'";
            dtm.setRowCount(0);
            rs = con.getQuery(query);
            while (rs.next()) {
                String itemid = rs.getString("id");
                String name = rs.getString("name");
                String itembuying_price = rs.getString("buying_price");
                String itemretail_price = rs.getString("retail_price");
                String itemwholesale_price = rs.getString("wholesale_price");
                String itemqty = rs.getString("qty");
                String expired_date = rs.getString("expired_date");
                int catogoryid = rs.getInt("catogory_id");
                int supplierid = rs.getInt("supplier_id");
                int stockid = rs.getInt("stock_id");
                int storeid = rs.getInt("store_id");
                String stock = "";
                String store = "";
                String catogory = "";
                String supplier = "";
                String query4 = "select name from supplier where id='" + supplierid + "'";
                ResultSet rs2 = con.getQuery(query4);
                while (rs2.next()) {
                    supplier = rs2.getString("name");
                }
                String query5 = "select name from catogory where id='" + catogoryid + "'";
                ResultSet rs3 = con.getQuery(query5);
                while (rs3.next()) {
                    catogory = rs3.getString("name");
                }
                String query6 = "select name from stock where id='"+stockid+"'";
                ResultSet rs4 = con.getQuery(query6);
                while (rs4.next()){
                    stock = rs4.getString("name");
                }
                String query7 = "select name from store where id='"+storeid+"'";
                ResultSet rs5 = con.getQuery(query7);
                while (rs5.next()){
                    store = rs5.getString("name");
                }
                
                dtm.addRow(new Object[]{itemid, name, catogory, supplier, stock, store, itemqty, itembuying_price, itemretail_price, itemwholesale_price, expired_date});
                rs2.close();
                rs3.close();
                rs5.close();
                rs4.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        supplierBtn = new javax.swing.JButton();
        itemsBtn = new javax.swing.JButton();
        categoriesBtn = new javax.swing.JButton();
        stockBtn = new javax.swing.JButton();
        storeBtn = new javax.swing.JButton();
        invoiceBtn = new javax.swing.JButton();
        customerBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtxtSearch = new javax.swing.JTextField();
        delBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 17, 77));

        supplierBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supplier.png"))); // NOI18N
        supplierBtn.setToolTipText("Configure Suppliers");
        supplierBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                supplierBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                supplierBtnMouseReleased(evt);
            }
        });
        supplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierBtnActionPerformed(evt);
            }
        });
        jPanel2.add(supplierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 120, 50));

        itemsBtn.setBackground(new java.awt.Color(255, 255, 255));
        itemsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itmes.png"))); // NOI18N
        itemsBtn.setToolTipText("Configure items");
        itemsBtn.setBorder(null);
        itemsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemsBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemsBtnMouseReleased(evt);
            }
        });
        itemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsBtnActionPerformed(evt);
            }
        });
        jPanel2.add(itemsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 50));

        categoriesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categories.png"))); // NOI18N
        categoriesBtn.setToolTipText("Configure Categories");
        categoriesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                categoriesBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                categoriesBtnMouseReleased(evt);
            }
        });
        categoriesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesBtnActionPerformed(evt);
            }
        });
        jPanel2.add(categoriesBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 120, 50));

        stockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stocks.png"))); // NOI18N
        stockBtn.setToolTipText("Configure Stocks");
        stockBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stockBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                stockBtnMouseReleased(evt);
            }
        });
        stockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockBtnActionPerformed(evt);
            }
        });
        jPanel2.add(stockBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 90, 50));

        storeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store.png"))); // NOI18N
        storeBtn.setToolTipText("Configure Stores");
        storeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                storeBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                storeBtnMouseReleased(evt);
            }
        });
        storeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeBtnActionPerformed(evt);
            }
        });
        jPanel2.add(storeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 100, 50));

        invoiceBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice.png"))); // NOI18N
        invoiceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                invoiceBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                invoiceBtnMouseReleased(evt);
            }
        });
        jPanel2.add(invoiceBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 120, 50));

        customerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
        customerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                customerBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                customerBtnMouseReleased(evt);
            }
        });
        customerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBtnActionPerformed(evt);
            }
        });
        jPanel2.add(customerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 22, 120, 50));

        jTabbedPane1.addTab("  Home  ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setText("Add Barcodes");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 32, -1, -1));

        jButton8.setText("Generate barcodes");
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jTabbedPane1.addTab("  Manage  ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setText("Items Reports");

        jButton4.setText("Accounting Reports");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton3)
                .addGap(53, 53, 53)
                .addComponent(jButton4)
                .addContainerGap(1026, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("  Reports  ", jPanel4);

        panel.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1350, 130));

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Id", "Name", "Category", "Supplier", "Stock", "Location", "Qty", "Buying Price", "Retail Price", "Wholesale Price", "Expire Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(180);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(180);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(220);
        }

        panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1330, 440));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Inventory");
        panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Search Items");
        panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 30));

        jtxtSearch.setFont(new java.awt.Font("Open Sans Semibold", 1, 14)); // NOI18N
        jtxtSearch.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSearch.setBorder(null);
        jtxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSearchKeyReleased(evt);
            }
        });
        panel.add(jtxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 280, 30));

        delBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        delBtn.setToolTipText("Delete");
        delBtn.setBorder(null);
        delBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                delBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                delBtnMouseReleased(evt);
            }
        });
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });
        delBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                delBtnKeyReleased(evt);
            }
        });
        panel.add(delBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 90, 30));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 380, 40));

        jLabel5.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Path to your success..");
        panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 50, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Inventory_backgroundq.png"))); // NOI18N
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1350, 180));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 210, 70, 40));

        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editBtnMouseReleased(evt);
            }
        });
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        panel.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 90, 30));

        viewBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        viewBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewBtnMouseReleased(evt);
            }
        });
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });
        panel.add(viewBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 90, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, 40));

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1370, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSearchKeyReleased
        search();
    }//GEN-LAST:event_jtxtSearchKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        editBtn.setVisible(true);
        viewBtn.setVisible(true);
        try {
            int row = jTable1.getSelectedRow();
            String table_click = (jTable1.getModel().getValueAt(row, 0).toString());
            jtxtSearch.setText(jTable1.getModel().getValueAt(row, 1).toString());
            String query = "select * from item where id='" + table_click + "'";
            rs = con.getQuery(query);
            if (rs.next()) {
                temp = rs.getString("id");

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        int action = 0;
        if (!temp.equals("")) {
            action = JOptionPane.showConfirmDialog(null, "Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
            if (action == 00) {
                try {
                    
                    //String query="delete from item where id='"+temp+"'";
                    String query = "UPDATE item SET deleted='1' WHERE id='" + temp + "'";
                    con.setQuery(query);
                    con=null;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }finally{
             System.gc();   
        }

            }
        }
        Table();
    }//GEN-LAST:event_delBtnActionPerformed

    private void delBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_delBtnKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int action = 0;
            if (!jtxtSearch.getText().equals("")) {
                action = JOptionPane.showConfirmDialog(null, "Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
                if (action == 00) {
                    try {
                        String query = "delete from item where id='" + jtxtSearch.getText() + "'";
                        con.setQuery(query);
                        con=null;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }finally{
                     System.gc();   
                     }
                }
            }
            Table();
        }
    }//GEN-LAST:event_delBtnKeyReleased

    private void delBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMouseReleased
        delBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
    }//GEN-LAST:event_delBtnMouseReleased

    private void delBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMousePressed
        delBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_clicked.png"))); // NOI18N
    }//GEN-LAST:event_delBtnMousePressed

    private void itemsBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsBtnMouseReleased
        itemsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itmes.png"))); // NOI18N
    }//GEN-LAST:event_itemsBtnMouseReleased

    private void itemsBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsBtnMousePressed
        // TODO add your handling code here:
        itemsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/items_clicked.png"))); // NOI18N
    }//GEN-LAST:event_itemsBtnMousePressed

    private void categoriesBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriesBtnMouseReleased
        // TODO add your handling code here:
        categoriesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categories.png"))); // NOI18N
    }//GEN-LAST:event_categoriesBtnMouseReleased

    private void categoriesBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriesBtnMousePressed
        // TODO add your handling code here:
        categoriesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categories_clicked.png"))); // NOI18N
    }//GEN-LAST:event_categoriesBtnMousePressed

    private void stockBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockBtnMouseReleased
        // TODO add your handling code here:
        stockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stocks.png"))); // NOI18N
    }//GEN-LAST:event_stockBtnMouseReleased

    private void stockBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockBtnMousePressed
        // TODO add your handling code here:
        stockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stocks_clicked.png"))); // NOI18N
    }//GEN-LAST:event_stockBtnMousePressed

    private void storeBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeBtnMouseReleased
        // TODO add your handling code here:
        storeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store.png"))); // NOI18N
    }//GEN-LAST:event_storeBtnMouseReleased

    private void storeBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeBtnMousePressed
        // TODO add your handling code here:
        storeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store_clicked.png"))); // NOI18N
    }//GEN-LAST:event_storeBtnMousePressed

    private void supplierBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierBtnMouseReleased
        // TODO add your handling code here:
        supplierBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supplier.png"))); // NOI18N
    }//GEN-LAST:event_supplierBtnMouseReleased

    private void supplierBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierBtnMousePressed
        // TODO add your handling code here:
        supplierBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/supplier_clicked.png"))); // NOI18N
    }//GEN-LAST:event_supplierBtnMousePressed

    private void itemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemsBtnActionPerformed
        Items i = new Items();
        i.setVisible(true);
    }//GEN-LAST:event_itemsBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Table();//refresh the table
        editBtn.setVisible(false);
        viewBtn.setVisible(false);
        jtxtSearch.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int action = 0;
            if (!jtxtSearch.getText().equals("")) {
                action = JOptionPane.showConfirmDialog(null, "Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
                if (action == 00) {
                    try {
                        String query = "delete from item where id='" + jtxtSearch.getText() + "'";
                        con.setQuery(query);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }finally{
                      System.gc();   
                    }
                }
            }
            Table();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void categoriesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesBtnActionPerformed
        category x = new category();
        x.setVisible(true);
    }//GEN-LAST:event_categoriesBtnActionPerformed

    private void stockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockBtnActionPerformed
        Stock x = new Stock();
        x.setVisible(true);
    }//GEN-LAST:event_stockBtnActionPerformed

    private void storeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeBtnActionPerformed
        Store x = new Store();
        x.setVisible(true);
    }//GEN-LAST:event_storeBtnActionPerformed

    private void supplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierBtnActionPerformed
        suppliers x = new suppliers();
        x.setVisible(true);
    }//GEN-LAST:event_supplierBtnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Inserting_Barcode x = new Inserting_Barcode();
        x.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void invoiceBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceBtnMouseReleased
        // TODO add your handling code here:
        invoiceBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice.png"))); // NOI18N
    }//GEN-LAST:event_invoiceBtnMouseReleased

    private void invoiceBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceBtnMousePressed
        // TODO add your handling code here:
        invoiceBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice_clicked.png"))); // NOI18N
    }//GEN-LAST:event_invoiceBtnMousePressed

    private void customerBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerBtnMousePressed
        // TODO add your handling code here:
        customerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer_clicked.png"))); // NOI18N
    }//GEN-LAST:event_customerBtnMousePressed

    private void customerBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerBtnMouseReleased
        // TODO add your handling code here:
        customerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
    }//GEN-LAST:event_customerBtnMouseReleased

    private void editBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseReleased
        // TODO add your handling code here:
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
    }//GEN-LAST:event_editBtnMouseReleased

    private void editBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMousePressed
        // TODO add your handling code here:
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_clicked.png"))); // NOI18N
    }//GEN-LAST:event_editBtnMousePressed

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
        // TODO add your handling code here:
        //editBtn.setVisible(false);
    }//GEN-LAST:event_jTable1FocusLost

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        Items i = new Items();
        i.setVisible(true);
        int row = jTable1.getSelectedRow();
        int id = (Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString()));
        i.getfrominv(id);
    }//GEN-LAST:event_editBtnActionPerformed

    private void viewBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBtnMouseReleased
        // TODO add your handling code here:
        viewBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
    }//GEN-LAST:event_viewBtnMouseReleased

    private void viewBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBtnMousePressed
        // TODO add your handling code here:
        viewBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view_clicked.png"))); // NOI18N
    }//GEN-LAST:event_viewBtnMousePressed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        // TODO add your handling code here:
        try{
        view v = new view();
        v.setVisible(true);
        int row = jTable1.getSelectedRow();
        int id = (Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString()));
        String name = (jTable1.getModel().getValueAt(row, 1).toString());
        String cat = (jTable1.getModel().getValueAt(row, 2).toString());
        String sup = (jTable1.getModel().getValueAt(row, 3).toString());
        String sto = (jTable1.getModel().getValueAt(row, 4).toString());
        String loc = (jTable1.getModel().getValueAt(row, 5).toString());
        double qty = Double.parseDouble(jTable1.getModel().getValueAt(row, 6).toString());
        double bprice = Double.parseDouble(jTable1.getModel().getValueAt(row, 7).toString());
        double rprice = Double.parseDouble(jTable1.getModel().getValueAt(row, 8).toString());
        double wprice = Double.parseDouble(jTable1.getModel().getValueAt(row, 9).toString());
        String date = (jTable1.getModel().getValueAt(row, 10).toString());
        
        v.getid(id, name, cat, sup, sto, loc, qty, bprice, rprice, wprice, date);
        
        
        }catch(Exception e){
        }finally{
             System.gc();   
        }
    }//GEN-LAST:event_viewBtnActionPerformed

    private void customerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBtnActionPerformed
        // TODO add your handling code here:
        customer c = new customer();
        c.setVisible(true);
    }//GEN-LAST:event_customerBtnActionPerformed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton1MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton categoriesBtn;
    private javax.swing.JButton customerBtn;
    private javax.swing.JButton delBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton invoiceBtn;
    private javax.swing.JButton itemsBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtxtSearch;
    private javax.swing.JPanel panel;
    private javax.swing.JButton stockBtn;
    private javax.swing.JButton storeBtn;
    private javax.swing.JButton supplierBtn;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}
