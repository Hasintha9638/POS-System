
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public  class category extends javax.swing.JFrame {
DefaultTableModel dtm;
    /**
     * Creates new form category
     */

int tempid=0;
    public category() {
        initComponents();
        this.setLocationRelativeTo(null);
        autoidget();
        loadTable();
    }
    
     //to genarate next id to category///////////////////////////////////////////////////////
    public void autoidget(){ 
        try {
            Connect con=new Connect();
            String query="select max(id) as max from catogory ";
            ResultSet rs=con.getQuery(query);
            if(rs.next()){
                int num=rs.getInt("max");
               num=num+1;
               String x=""+num;
                lblId.setText(x);
                //System.err.println(num);
            }
            rs.close();     
        } catch (SQLException ex) {
            Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void loadTable(){
        dtm=(DefaultTableModel)jTable1Cat.getModel();
        dtm.setRowCount(0);
       
         try{
            Connect con=new Connect();
            String query="select * from catogory";            
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
                if(rs.getString("deleted").equals("1")){
                    continue;
                }else{
              String Cid=rs.getString("id");
              String name=rs.getString("name");    
              dtm.addRow(new Object[]{Cid,name});
            }}
            rs.close(); 
           }catch(SQLException e){
              JOptionPane.showMessageDialog(null, e);
           }finally{
             System.gc();   
        }
        
    }
     public void search(){ 
        try{
            Connect con=new Connect();
            String query="select * from catogory where id like '"+jtxtSearch.getText()+"%' or name like '"+jtxtSearch.getText()+"%'"; 
            dtm.setRowCount(0);
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
            String id=rs.getString("id");
            String name=rs.getString("name");
              
            dtm.addRow(new Object[]{id,name,});
            }
            rs.close(); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }
        
    }
     
     public void delete(){
          int action=0;
        if(!jtxtSearch.getText().equals("")){
            action=JOptionPane.showConfirmDialog(null,"Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
            if(action==00){
            try{
                Connect con=new Connect();
                String query="update catogory set deleted ='1' where id='"+tempid+"'";
                con.setQuery(query);
                jtxtCId.setText("");
                jtxtCNAME.setText("");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }finally{
             System.gc();   
        }
            }
        }
        loadTable();
     }
     
     public void update(){
            if(jtxtCId.getText().equals("")){
           // JOptionPane.showMessageDialog(null, "No data to update");
           lblnot2.setText("insert the category id!");
            return ;
        }
       
            try{
                 lblnot2.setText("");
                Connect con=new Connect();
                String query="update catogory set name='"+jtxtCNAME.getText()
                +"' where id='"+ jtxtCId.getText()+"' ";
                con.setQuery(query);
                JOptionPane.showMessageDialog(null, "Updated the data");
                jtxtCId.setText("");
                jtxtCNAME.setText("");
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null, e);
            }finally{
             System.gc();   
        }
            
        
        loadTable();
     }
     public void inserting(){
            Connect con=new Connect();
        try {
            if(jtxtCName.getText().equals("")){
                lblw1.setText("Insert the Category Name*");
                return;
            }
             lblw1.setText("");
            String query="insert into catogory(name) values('"+jtxtCName.getText()
                    +"')";
            int genId=con.setQuery(query,"");
           
            JOptionPane.showMessageDialog(null, "New Category Has Been Added!");
            autoidget();
            
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"Category Id Already Has Been Added!");
        }finally{
             System.gc();   
        }
        loadTable();
        jtxtCName.setText("");
     }
     
     public void tableclick(){
          try{
              Connect con=new Connect();
            int row=jTable1Cat.getSelectedRow();
            String table_click=(jTable1Cat.getModel().getValueAt(row, 0).toString());
            jtxtSearch.setText(jTable1Cat.getModel().getValueAt(row, 1).toString());
            String query="select * from catogory where id='"+table_click+"'";
        
            ResultSet rs=con.getQuery(query);
            if(rs.next()){
                String add1=rs.getString("id");
                jtxtCId.setText(add1);
                 String add2=rs.getString("name");
                jtxtCNAME.setText(add2);
                tempid =rs.getInt("id");
                
                
            }
            rs.close(); 
        }catch(SQLException e){
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

        jPanel2 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jtxtCId = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1Cat = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtxtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jtxtCName = new javax.swing.JTextField();
        lblw1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jtxtCNAME = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        lblnot2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblnot3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categories");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel27.setText("Category Id: ");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 100, 20));

        jtxtCId.setEditable(false);
        jtxtCId.setBackground(new java.awt.Color(255, 255, 255));
        jtxtCId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtCId.setForeground(new java.awt.Color(255, 51, 51));
        jtxtCId.setBorder(null);
        jtxtCId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtCIdFocusGained(evt);
            }
        });
        jtxtCId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCIdActionPerformed(evt);
            }
        });
        jtxtCId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtCIdKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtCId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 70, 30));

        jLabel10.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel10.setText("Category Name: ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 40));

        jTable1Cat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1Cat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category Id", "Category Name"
            }
        ));
        jTable1Cat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1CatMouseClicked(evt);
            }
        });
        jTable1Cat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1CatKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1Cat);
        if (jTable1Cat.getColumnModel().getColumnCount() > 0) {
            jTable1Cat.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 340, 350));

        jLabel1.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel1.setText("Search");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, 20));

        jtxtSearch.setBackground(new java.awt.Color(255, 255, 255));
        jtxtSearch.setFont(new java.awt.Font("Open Sans Semibold", 0, 13)); // NOI18N
        jtxtSearch.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSearch.setBorder(null);
        jtxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSearchKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 150, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
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
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 90, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 100, 40));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddMouseReleased(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 140, 40));

        jtxtCName.setBackground(new java.awt.Color(255, 255, 255));
        jtxtCName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtCName.setForeground(new java.awt.Color(51, 51, 51));
        jtxtCName.setBorder(null);
        jtxtCName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtCNameFocusGained(evt);
            }
        });
        jtxtCName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCNameActionPerformed(evt);
            }
        });
        jtxtCName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtCNameKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtCName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, 30));

        lblw1.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lblw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 150, 20));

        lblId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblId.setForeground(new java.awt.Color(22, 160, 134));
        jPanel2.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 90, 40));

        jLabel14.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel14.setText("Category Name: ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, 40));

        jtxtCNAME.setBackground(new java.awt.Color(255, 255, 255));
        jtxtCNAME.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtCNAME.setForeground(new java.awt.Color(51, 51, 51));
        jtxtCNAME.setBorder(null);
        jtxtCNAME.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtCNAMEFocusGained(evt);
            }
        });
        jtxtCNAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCNAMEActionPerformed(evt);
            }
        });
        jtxtCNAME.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtCNAMEKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtCNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 150, 30));

        jLabel30.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel30.setText("Category Id: ");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, 40));

        lblnot2.setBackground(new java.awt.Color(255, 204, 204));
        lblnot2.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lblnot2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 150, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 150, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 160, 40));

        lblnot3.setBackground(new java.awt.Color(255, 204, 204));
        lblnot3.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lblnot3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 150, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 80, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 80, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, 40));

        jLabel2.setFont(new java.awt.Font("Open Sans Semibold", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Update Category");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 230, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtCIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtCIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCIdFocusGained

    private void jtxtCIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCIdActionPerformed

    private void jtxtCIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCIdKeyReleased
        
    }//GEN-LAST:event_jtxtCIdKeyReleased

    private void jtxtCNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtCNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCNameFocusGained

    private void jtxtCNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCNameActionPerformed

    private void jtxtCNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCNameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAdd.requestFocus();
        }
    }//GEN-LAST:event_jtxtCNameKeyReleased
//add the category
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
      inserting();
       // clear();
    }//GEN-LAST:event_btnAddActionPerformed

    private void jtxtCNAMEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtCNAMEFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCNAMEFocusGained

    private void jtxtCNAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCNAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCNAMEActionPerformed

    private void jtxtCNAMEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCNAMEKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCNAMEKeyReleased
//tableclick
    private void jTable1CatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1CatMouseClicked
       tableclick();
        
    }//GEN-LAST:event_jTable1CatMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     update();
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSearchKeyReleased
        search();
    }//GEN-LAST:event_jtxtSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       delete();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1CatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1CatKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_DELETE){
           delete();
    }
    }//GEN-LAST:event_jTable1CatKeyReleased

    private void btnAddMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseReleased
        // TODO add your handling code here:
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
    }//GEN-LAST:event_btnAddMouseReleased

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed
        // TODO add your handling code here:
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_clicked.png"))); // NOI18N
    }//GEN-LAST:event_btnAddMousePressed

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        // TODO add your handling code here:
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
    }//GEN-LAST:event_jButton2MouseReleased

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_clicked.png"))); // NOI18N
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
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new category().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1Cat;
    private javax.swing.JTextField jtxtCId;
    private javax.swing.JTextField jtxtCNAME;
    private javax.swing.JTextField jtxtCName;
    private javax.swing.JTextField jtxtSearch;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblnot2;
    private javax.swing.JLabel lblnot3;
    private javax.swing.JLabel lblw1;
    // End of variables declaration//GEN-END:variables
}
