
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
public  class Stock extends javax.swing.JFrame {
    DefaultTableModel dtm;
    /**
     * Creates new form Stock
     */
    public Stock() {
        initComponents();
         autoidget();
        loadTable();
        this.setLocationRelativeTo(null);
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
                //System.err.println(num);
            }rs.close(); 
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
            String query="select * from stock";            
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
              String Cid=rs.getString("id");
              String name=rs.getString("name");  
              String date=rs.getString("ordered_date");
              dtm.addRow(new Object[]{Cid,name,date});
            }rs.close(); 
           }catch(SQLException e){
              JOptionPane.showMessageDialog(null, e);
           }finally{
             System.gc();   
        }
        
    }
     public void search(){ 
        try{
            Connect con=new Connect();
            String query="select * from stock where id like '"+jtxtSearch.getText()+"%' or name like '"+jtxtSearch.getText()+"%'"; 
            dtm.setRowCount(0);
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
            String Cid=rs.getString("id");
            String name=rs.getString("name");  
            String date=rs.getString("ordered_date");
            dtm.addRow(new Object[]{Cid,name,date});
            }rs.close(); 
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }
        
    }
     
     public void update(){
         if(jtxtID.getText().equals("")){
           // JOptionPane.showMessageDialog(null, "No data to update");
            lblnoti.setText("Insert the Stock Id*");
            return ;
        }

        try{
            jDateChooser2.setDateFormatString("yyyy/MM/dd");
            lblnoti.setText("");
            Connect con=new Connect();
            String query="update stock set name='"+jtxtSNAME.getText()
            +"',ordered_date='"+((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText()
            +"' where id='"+ jtxtID.getText()+"' ";
            con.setQuery(query);
            JOptionPane.showMessageDialog(null, "Updated the data");
            jtxtID.setText("");
            jtxtSNAME.setText("");
            ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).setText(""); 
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
                lblw1.setText("Insert the Stock Name*");
                return;
            }
           jDateChooser1.setDateFormatString("yyyy/MM/dd");
            lblw1.setText("");
            String query="insert into stock(name,ordered_date) values('"+jtxtSName.getText()
            +"','"+((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText() 
            +"')";
            int genId=con.setQuery(query,"");

            JOptionPane.showMessageDialog(null, "New Stock Has Been Added!");
            autoidget();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Stock Id Already Has Been Added!");
        }finally{
             System.gc();   
        }
        loadTable();
        jtxtSName.setText("");
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(""); 
            
     }
     public void tableclick(){
              try{
            jDateChooser2.setDateFormatString("yyyy/MM/dd");
            Connect con=new Connect();
            int row=jTable1.getSelectedRow();
            String table_click=(jTable1.getModel().getValueAt(row, 0).toString());
            String query="select * from stock where id='"+table_click+"'";

            ResultSet rs=con.getQuery(query);
            if(rs.next()){
                String add1=rs.getString("id");
                jtxtID.setText(add1);
                String add2=rs.getString("name");
                jtxtSNAME.setText(add2);
                String add3=rs.getString("id");
                jtxtSearch.setText(add3);
                SimpleDateFormat date=new SimpleDateFormat("yyyy/MM/dd");
                Date dateValue=null;
                try {
                    dateValue=date.parse(rs.getString("ordered_date"));
                } catch (ParseException ex) {
                    Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
             System.gc();   
        }
                jDateChooser2.setDate(dateValue);

            }
            rs.close(); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void delete(){
           int action=0;
            if(!jtxtSearch.getText().equals("")){
                action=JOptionPane.showConfirmDialog(null,"Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
                if(action==00){
                    try{
                        Connect con=new Connect();
                        String query="delete from stock where id='"+jtxtSearch.getText()+"'";
                        con.setQuery(query);
                        jtxtID.setText("");
                        jtxtSNAME.setText("");
                        ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).setText(""); 
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                    }finally{
             System.gc();   
        }
                }
            }
            loadTable();
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
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtxtSearch = new javax.swing.JTextField();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblw1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxtID = new javax.swing.JTextField();
        jtxtSName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jtxtSNAME = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lblnoti = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stocks");
        setBackground(new java.awt.Color(42, 128, 185));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Ordered Date: ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", " Name", "Ordered Date"
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 350, 330));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, 50));

        jLabel1.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Search");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, 20));

        jtxtSearch.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSearch.setBorder(null);
        jtxtSearch.setOpaque(false);
        jtxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSearchKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 24, 140, 30));

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        deleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deleteBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deleteBtnMouseReleased(evt);
            }
        });
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel2.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 90, 30));

        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        updateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                updateBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                updateBtnMouseReleased(evt);
            }
        });
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        updateBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updateBtnKeyReleased(evt);
            }
        });
        jPanel2.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 90, 40));

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
        btnAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnAddKeyReleased(evt);
            }
        });
        jPanel2.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 130, 40));

        lblw1.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lblw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 150, 30));

        lblId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblId.setForeground(new java.awt.Color(255, 102, 102));
        jPanel2.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 100, 40));

        jLabel30.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Stock Id: ");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 40));

        jLabel11.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Stock Name: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, -1, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, 40));

        jtxtID.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtID.setBorder(null);
        jtxtID.setOpaque(false);
        jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtIDKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 140, 30));

        jtxtSName.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSName.setForeground(new java.awt.Color(51, 51, 51));
        jtxtSName.setBorder(null);
        jtxtSName.setOpaque(false);
        jtxtSName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSNameKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 140, 30));

        jLabel12.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Ordered Date: ");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, 40));

        jLabel13.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Stock Name: ");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 40));

        jLabel31.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Stock Id: ");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 100, 40));

        jtxtSNAME.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSNAME.setBorder(null);
        jtxtSNAME.setOpaque(false);
        jtxtSNAME.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSNAMEKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 140, 30));

        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyReleased(evt);
            }
        });
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 119, 160, 30));

        lblnoti.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(lblnoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 140, 20));

        jDateChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooser2KeyReleased(evt);
            }
        });
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 160, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 300, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 440));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 700, 440));

        jPanel3.setBackground(new java.awt.Color(42, 128, 185));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Open Sans Semibold", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Stocks");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 70));

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

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        delete();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        update();

    }//GEN-LAST:event_updateBtnActionPerformed
//insert the data
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
      
        inserting();
    }//GEN-LAST:event_btnAddActionPerformed

    private void jtxtSNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSNameKeyReleased
      if (evt.getKeyCode() ==KeyEvent.VK_DOWN) {
          jDateChooser1.requestFocus();
        }
    }//GEN-LAST:event_jtxtSNameKeyReleased

    private void jDateChooser1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyReleased
        if (evt.getKeyCode() ==KeyEvent.VK_DOWN) {
          jDateChooser1.requestFocus();
        }
         if (evt.getKeyCode() ==KeyEvent.VK_ENTER) {
           inserting();
        }
    }//GEN-LAST:event_jDateChooser1KeyReleased
//add
    private void btnAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddKeyReleased
       
    }//GEN-LAST:event_btnAddKeyReleased

    private void jtxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIDKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSNAME.requestFocus();
        }
    }//GEN-LAST:event_jtxtIDKeyReleased

    private void jtxtSNAMEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSNAMEKeyReleased
      if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jDateChooser2.requestFocus();
        }
    }//GEN-LAST:event_jtxtSNAMEKeyReleased

    private void jDateChooser2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser2KeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            updateBtn.requestFocus();
        }
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           update();
        }
    }//GEN-LAST:event_jDateChooser2KeyReleased

    private void updateBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateBtnKeyReleased
      
    }//GEN-LAST:event_updateBtnKeyReleased

    private void btnAddMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseReleased
        // TODO add your handling code here:
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
    }//GEN-LAST:event_btnAddMouseReleased

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed
        // TODO add your handling code here:
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_clicked.png"))); // NOI18N
    }//GEN-LAST:event_btnAddMousePressed

    private void updateBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBtnMouseReleased
        // TODO add your handling code here:
        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
    }//GEN-LAST:event_updateBtnMouseReleased

    private void updateBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBtnMousePressed
        // TODO add your handling code here:
        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_clicked.png"))); // NOI18N
    }//GEN-LAST:event_updateBtnMousePressed

    private void deleteBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMouseReleased
        // TODO add your handling code here:
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
    }//GEN-LAST:event_deleteBtnMouseReleased

    private void deleteBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMousePressed
        // TODO add your handling code here:
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_clicked.png"))); // NOI18N
    }//GEN-LAST:event_deleteBtnMousePressed

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
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton deleteBtn;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtSNAME;
    private javax.swing.JTextField jtxtSName;
    private javax.swing.JTextField jtxtSearch;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblnoti;
    private javax.swing.JLabel lblw1;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
