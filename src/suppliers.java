
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
public  class suppliers extends javax.swing.JFrame {
    DefaultTableModel dtm;
    /**
     * Creates new form suppliers
     */
    public suppliers() {
        initComponents();
        autoidget();
        loadTable();
        this.setLocationRelativeTo(null);
    }

     //to genarate next id to category///////////////////////////////////////////////////////
    public void autoidget(){
        Connect con=new Connect();
        String query="select max(id) as max from supplier ";
        
        try {
            ResultSet rs=con.getQuery(query);
            if(rs.next()){
                int num=rs.getInt("max");
               num=num+1;
               String x=""+num;
                jlblId.setText(x);
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
       
         try{
            
            String query="select * from supplier";            
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
              String Cid=rs.getString("id");
              String name=rs.getString("name"); 
              String add=rs.getString("adress");
              String tel=rs.getString("tel"); 
              dtm.addRow(new Object[]{Cid,name,add,tel});
            }rs.close(); 
           }catch(SQLException e){
              JOptionPane.showMessageDialog(null, e);
           }finally{
             try{
                con.Conn().close();
             }catch(Exception E){}
         }
        }catch(Exception e){}
       finally{
             System.gc();   
        }
    }
     public void search(){ 
        try{
            Connect con=new Connect();
            String query="select * from supplier where id like '"+jtxtSearch.getText()+"%' or name like '"+jtxtSearch.getText()+"%'"; 
            dtm.setRowCount(0);
            ResultSet rs=con.getQuery(query);
            while(rs.next()){
              String Cid=rs.getString("id");
              String name=rs.getString("name"); 
              String add=rs.getString("adress");
              String tel=rs.getString("tel"); 
              dtm.addRow(new Object[]{Cid,name,add,tel});
            }
            rs.close(); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }
        
    }
     public void inserting(){
          Connect con=new Connect();
        try {
            if(jtxtSname.getText().equals("")){
                lblw1.setText("Insert the Supplier Name*");
                return;
            }
             lblw1.setText("");
            String query="insert into supplier(name,adress,tel) values('"+jtxtSname.getText()
                    +"','"+jtxtSadd.getText()
                    +"','"+jtxtStel.getText()
                    +"')";
            int genId=con.setQuery(query,"");
           
            JOptionPane.showMessageDialog(null, "New Supplier Has Been Added!");
            autoidget();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Supplier Id Already Has Been Added!");
        }finally{
             System.gc();   
        }
        loadTable();
        jtxtSname.setText("");
        jtxtSadd.setText("");
        jtxtStel.setText("");
       loadTable();
     }
     public void update(){
         if(jtxtID.getText().equals("")){
           // JOptionPane.showMessageDialog(null, "No data to update");
           lblnoti.setText("Insert the Supplier Id*");
            return ;
        }
         lblnoti.setText("");
            try{
                Connect con=new Connect();
                String query="update supplier set name='"+jtxtSName.getText()
                +"',adress='"+jtxtSAdd.getText()
                +"',tel='"+jtxtSTel.getText()
                +"' where id='"+ jtxtID.getText()+"' ";
                con.setQuery(query);
                JOptionPane.showMessageDialog(null, "Updated the data");
                jtxtID.setText("");
                jtxtSName.setText("");
                jtxtSAdd.setText("");
                jtxtSTel.setText("");
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null, e);
            }finally{
             System.gc();   
        }
            
        
        loadTable();
     }
     public void tableclick(){
         try{
              Connect con=new Connect();
            int row=jTable1.getSelectedRow();
            String table_click=(jTable1.getModel().getValueAt(row, 0).toString());
            String query="select * from supplier where id='"+table_click+"'";
        
            ResultSet rs=con.getQuery(query);
            if(rs.next()){
                String add1=rs.getString("id");
                jtxtID.setText(add1);
                 String add2=rs.getString("name");
                jtxtSName.setText(add2);
                String add3=rs.getString("id");
                jtxtSearch.setText(add3);
                String add4=rs.getString("adress");
                jtxtSAdd.setText(add4);
                String add5=rs.getString("tel");
                jtxtSTel.setText(add5);
                
            }
            rs.close(); 
        }catch(SQLException e){
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
                String query="delete from supplier where id='"+jtxtSearch.getText()+"'";
                con.setQuery(query);
                jtxtID.setText("");
                jtxtSName.setText("");
                jtxtSAdd.setText("");
                jtxtSTel.setText("");
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
        jPanel3 = new javax.swing.JPanel();
        jlblId = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtSearch = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtxtSname = new javax.swing.JTextField();
        jtxtSadd = new javax.swing.JTextField();
        jtxtStel = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtxtSTel = new javax.swing.JTextField();
        jtxtSAdd = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jtxtSName = new javax.swing.JTextField();
        jtxtID = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblw1 = new javax.swing.JLabel();
        lblnoti = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(42, 128, 185));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblId.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jlblId.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(jlblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 40));

        jLabel11.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Supplier Name: ");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton6KeyReleased(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 130, 30));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton7KeyReleased(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 100, 30));

        jLabel2.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel2.setText("Search");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 40));

        jLabel4.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 40));

        jtxtSearch.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSearch.setBorder(null);
        jtxtSearch.setOpaque(false);
        jtxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSearchKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 140, 30));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 90, 30));

        jTable1.setFont(new java.awt.Font("Open Sans Semibold", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Address", "Tel Number"
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
        jScrollPane2.setViewportView(jTable1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 400, 420));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 160, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 160, 60));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 160, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 160, 60));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, -1, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 160, 60));

        jLabel12.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Supplier Tel: ");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 40));

        jLabel13.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Supplier Address: ");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 40));

        jtxtSname.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSname.setBorder(null);
        jtxtSname.setOpaque(false);
        jtxtSname.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                jtxtSnameAncestorRemoved(evt);
            }
        });
        jtxtSname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSnameKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtSname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 140, 30));

        jtxtSadd.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSadd.setBorder(null);
        jtxtSadd.setOpaque(false);
        jtxtSadd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSaddKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtSadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 140, 30));

        jtxtStel.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtStel.setBorder(null);
        jtxtStel.setOpaque(false);
        jtxtStel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtStelKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtStel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 140, 30));

        jLabel14.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Supplier Tel: ");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, 40));

        jtxtSTel.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSTel.setBorder(null);
        jtxtSTel.setOpaque(false);
        jtxtSTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSTelKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtSTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 140, 30));

        jtxtSAdd.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSAdd.setBorder(null);
        jtxtSAdd.setOpaque(false);
        jtxtSAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSAddKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtSAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 140, 30));

        jLabel15.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Supplier Address: ");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, 40));

        jLabel16.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Supplier Name: ");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, 40));

        jtxtSName.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtSName.setBorder(null);
        jtxtSName.setOpaque(false);
        jtxtSName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSNameKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtSName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 140, 30));

        jtxtID.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jtxtID.setBorder(null);
        jtxtID.setOpaque(false);
        jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtIDKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 140, 30));

        jLabel29.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Supplier Id: ");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 100, 40));

        jLabel30.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Supplier Id: ");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 40));

        lblw1.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(lblw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 150, 20));

        lblnoti.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(lblnoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 120, 20));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 260, 10));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 720, 550));

        jLabel1.setFont(new java.awt.Font("Open Sans Semibold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Suppliers");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//add item
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        inserting();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtxtSnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSnameKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSadd.requestFocus();
        }
    }//GEN-LAST:event_jtxtSnameKeyReleased

    private void jtxtSaddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSaddKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtStel.requestFocus();
        }
    }//GEN-LAST:event_jtxtSaddKeyReleased

    private void jtxtStelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtStelKeyReleased
         if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton6.requestFocus();
        }
        
    }//GEN-LAST:event_jtxtStelKeyReleased
//table click
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         tableclick();
    }//GEN-LAST:event_jTable1MouseClicked
//update
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         update();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        delete();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
           delete();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jtxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIDKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSName.requestFocus();
        }
    }//GEN-LAST:event_jtxtIDKeyReleased

    private void jtxtSNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSNameKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSAdd.requestFocus();
        }
    }//GEN-LAST:event_jtxtSNameKeyReleased

    private void jtxtSAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSAddKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSTel.requestFocus();
        }
    }//GEN-LAST:event_jtxtSAddKeyReleased

    private void jtxtSTelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSTelKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jButton7.requestFocus();
        }
    }//GEN-LAST:event_jtxtSTelKeyReleased

    private void jButton7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            update();
        }
    }//GEN-LAST:event_jButton7KeyReleased

    private void jButton6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           inserting();
        }
    }//GEN-LAST:event_jButton6KeyReleased

    private void jtxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSearchKeyReleased
       search();
    }//GEN-LAST:event_jtxtSearchKeyReleased

    private void jtxtSnameAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jtxtSnameAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtSnameAncestorRemoved

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
            java.util.logging.Logger.getLogger(suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new suppliers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlblId;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtSAdd;
    private javax.swing.JTextField jtxtSName;
    private javax.swing.JTextField jtxtSTel;
    private javax.swing.JTextField jtxtSadd;
    private javax.swing.JTextField jtxtSearch;
    private javax.swing.JTextField jtxtSname;
    private javax.swing.JTextField jtxtStel;
    private javax.swing.JLabel lblnoti;
    private javax.swing.JLabel lblw1;
    // End of variables declaration//GEN-END:variables
}
