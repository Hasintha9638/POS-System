
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
public  class Store extends javax.swing.JFrame {
    DefaultTableModel dtm;
    /**
     * Creates new form Store
     */
    public Store() {
        initComponents();
        autoidget();
        loadTable();
    }
         //to genarate next id to category///////////////////////////////////////////////////////
    public void autoidget(){
        Connect con=new Connect();
        String query="select max(id) as max from stock ";
        
        try {
            ResultSet rs=con.getQuery(query);
            if(rs.next()){
                int num=rs.getInt("max");
               num=num+1;
               String x=""+num;
                lblId.setText(x);
            }
            rs.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             System.gc();   
        }
        
    }
    public void loadTable(){
        dtm=(DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);  
         try{
            Connect con=new Connect();
            String query="select * from store";            
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
              String Cid=rs.getString("id");
              String name=rs.getString("name");
              String loc=rs.getString("location");
              dtm.addRow(new Object[]{Cid,name,loc});
            }
            rs.close(); 
           }catch(SQLException e){
              JOptionPane.showMessageDialog(null, e);
           }finally{
             System.gc();   
        }
        
    }
    
    //search from table
     public void search(){ 
        try{
            Connect con=new Connect();
            String query="select * from store where id like '"+jtxtSearch.getText()+"%' or name like '"+jtxtSearch.getText()+"%'"; 
            dtm.setRowCount(0);
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
            String Cid=rs.getString("id");
            String name=rs.getString("name");  
             String loc=rs.getString("location");
              dtm.addRow(new Object[]{Cid,name,loc});
            }
            rs.close(); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }
        
    }
     
     public void update(){
         if(jtxtID.getText().equals("")){
           // JOptionPane.showMessageDialog(null, "No data to update");
            lblnoti.setText("Insert the Store Id*");
            return ;
        }

        try{
            lblnoti.setText("");
            Connect con=new Connect();
            String query="update store set name='"+jtxtSNAME.getText()
            +"',location='"+jtxtLOC.getText()
            +"' where id='"+ jtxtID.getText()+"' ";
            con.setQuery(query);
            JOptionPane.showMessageDialog(null, "Updated the Store");
            jtxtID.setText("");
            jtxtSNAME.setText("");
            jtxtLOC.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }

        loadTable();
         
     }
     public void inserting(){
           Connect con=new Connect();
        try {
            if(jtxtSName.getText().equals("")){
                lblw1.setText("Insert the Store Name*");
                return;
            }
          
            lblw1.setText("");
            String query="insert into store(name,location) values('"+jtxtSName.getText()
            +"','"+jtxtLoc.getText() 
            +"')";
            int genId=con.setQuery(query,"");

            JOptionPane.showMessageDialog(null, "New Store Has Been Added!");
            autoidget();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Store Id Already Has Been Added!");
        }finally{
             System.gc();   
        }
        loadTable();
        jtxtSName.setText("");
        jtxtLoc.setText(""); 
            
     }
     
     public void delete(){
          int action=0;
            if(!jtxtSearch.getText().equals("")){
                action=JOptionPane.showConfirmDialog(null,"Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
                if(action==00){
                    try{
                        Connect con=new Connect();
                        String query="delete from store where id='"+jtxtSearch.getText()+"'";
                        con.setQuery(query);
                        jtxtID.setText("");
                        jtxtSNAME.setText("");
                        jtxtLOC.setText("");
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                    }finally{
             System.gc();   
        }
                }
            }
            loadTable();
     }
     
     public void tableclick(){
         try{
           
            Connect con=new Connect();
            int row=jTable1.getSelectedRow();
            String table_click=(jTable1.getModel().getValueAt(row, 0).toString());
            String query="select * from store where id='"+table_click+"'";

            ResultSet rs=con.getQuery(query);
            if(rs.next()){
                String add1=rs.getString("id");
                jtxtID.setText(add1);
                String add2=rs.getString("name");
                jtxtSNAME.setText(add2);
                String add3=rs.getString("id");
                jtxtSearch.setText(add3);
               
                String add4=rs.getString("location");
                jtxtLOC.setText(add4);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblw1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtxtSName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jtxtID = new javax.swing.JTextField();
        jtxtSNAME = new javax.swing.JTextField();
        lblnoti = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jtxtLOC = new javax.swing.JTextField();
        jtxtLoc = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(42, 128, 185));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", " Name", "Location"
            }
        ));
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
            jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 360, 370));

        jLabel1.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Search");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, 40));

        jtxtSearch.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSearch.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSearch.setBorder(null);
        jtxtSearch.setOpaque(false);
        jtxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSearchKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 140, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 90, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton2KeyReleased(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 100, 40));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        btnAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnAddKeyReleased(evt);
            }
        });
        jPanel2.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 140, 40));

        lblw1.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lblw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 150, 20));

        lblId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 100, 40));

        jLabel30.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Store Id: ");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 40));

        jLabel11.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Store Name: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, -1, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, 40));

        jtxtSName.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSName.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSName.setBorder(null);
        jtxtSName.setOpaque(false);
        jtxtSName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSNameKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 140, 30));

        jLabel13.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Location: ");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 40));

        jLabel31.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Stock Id: ");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, 30));

        jtxtID.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtID.setForeground(new java.awt.Color(22, 160, 134));
        jtxtID.setBorder(null);
        jtxtID.setOpaque(false);
        jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtIDKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 140, 30));

        jtxtSNAME.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSNAME.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSNAME.setBorder(null);
        jtxtSNAME.setOpaque(false);
        jtxtSNAME.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSNAMEKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 140, 30));

        lblnoti.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lblnoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 140, 20));

        jLabel14.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Stock Name: ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, 50));

        jLabel15.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Location: ");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 40));

        jtxtLOC.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtLOC.setForeground(new java.awt.Color(22, 160, 134));
        jtxtLOC.setBorder(null);
        jtxtLOC.setOpaque(false);
        jtxtLOC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtLOCKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtLOC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 140, 30));

        jtxtLoc.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtLoc.setForeground(new java.awt.Color(22, 160, 134));
        jtxtLoc.setBorder(null);
        jtxtLoc.setOpaque(false);
        jtxtLoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtLocKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 140, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 290, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 720, 470));

        jLabel2.setFont(new java.awt.Font("Open Sans Semibold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Stores");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 720, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tableclick();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
           delete();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jtxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSearchKeyReleased
        search();
    }//GEN-LAST:event_jtxtSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        delete();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyReleased

    }//GEN-LAST:event_jButton2KeyReleased

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        inserting();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddKeyReleased

    }//GEN-LAST:event_btnAddKeyReleased

    private void jtxtSNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSNameKeyReleased
        if (evt.getKeyCode() ==KeyEvent.VK_DOWN) {
            jtxtLoc.requestFocus();
        }
    }//GEN-LAST:event_jtxtSNameKeyReleased

    private void jtxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIDKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSNAME.requestFocus();
        }
    }//GEN-LAST:event_jtxtIDKeyReleased

    private void jtxtSNAMEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSNAMEKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtLOC.requestFocus();
        }
    }//GEN-LAST:event_jtxtSNAMEKeyReleased

    private void jtxtLocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtLocKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          inserting();
        }
    }//GEN-LAST:event_jtxtLocKeyReleased

    private void jtxtLOCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtLOCKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          update();
        }
    }//GEN-LAST:event_jtxtLOCKeyReleased

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseReleased

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
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Store().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtLOC;
    private javax.swing.JTextField jtxtLoc;
    private javax.swing.JTextField jtxtSNAME;
    private javax.swing.JTextField jtxtSName;
    private javax.swing.JTextField jtxtSearch;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblnoti;
    private javax.swing.JLabel lblw1;
    // End of variables declaration//GEN-END:variables
}
