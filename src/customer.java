
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class customer extends javax.swing.JFrame {
  DefaultTableModel dtm;
    /**
     * Creates new form customer
     */
    public customer() {
        initComponents();
        autoidget();
        loadTable();
        this.setLocationRelativeTo(null);
    }
    
     public void autoidget(){
        Connect con=new Connect();
        String query="select max(id) as max from customer ";
        
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
        }
        
    }
    public void loadTable(){
        dtm=(DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);
       
         try{
            Connect con=new Connect();
            String query="select * from customer";            
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
           }
        
    }
     public void search(){ 
        try{
            Connect con=new Connect();
            String query="select * from customer where id like '"+jtxtSearch.getText()+"%' or name like '"+jtxtSearch.getText()+"%'"; 
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
        }
        
    }
     public void inserting(){
          Connect con=new Connect();
        try {
            if(jtxtSname.getText().equals("")){
                lblw1.setText("Insert the customer Name*");
                return;
            }
             lblw1.setText("");
            String query="insert into customer(name,adress,tel) values('"+jtxtSname.getText()
                    +"','"+jtxtSadd.getText()
                    +"','"+jtxtStel.getText()
                    +"')";
            int genId=con.setQuery(query,"");
           
            JOptionPane.showMessageDialog(null, "New Customer Has Been Added!");
            autoidget();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Customer Id Already Has Been Added!");
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
           lblnoti.setText("Insert the customer Id*");
            return ;
        }
         lblnoti.setText("");
            try{
                Connect con=new Connect();
                String query="update customer set name='"+jtxtSName.getText()
                +"',adress='"+jtxtSAdd.getText()
                +"',tel='"+jtxtSTel.getText()
                +"' where id='"+ jtxtID.getText()+"' ";
                con.setQuery(query);
                JOptionPane.showMessageDialog(null, "Updated the Customer Information");
                jtxtID.setText("");
                jtxtSName.setText("");
                jtxtSAdd.setText("");
                jtxtSTel.setText("");
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null, e);
            }
            
        
        loadTable();
     }
     public void tableclick(){
         try{
              Connect con=new Connect();
            int row=jTable1.getSelectedRow();
            String table_click=(jTable1.getModel().getValueAt(row, 0).toString());
            String query="select * from customer where id='"+table_click+"'";
        
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
        }
     }
     public void delete(){
          int action=0;
        if(!jtxtSearch.getText().equals("")){
            action=JOptionPane.showConfirmDialog(null,"Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
            if(action==00){
            try{
                Connect con=new Connect();
                String query="delete from customer where id='"+jtxtSearch.getText()+"'";
                con.setQuery(query);
                jtxtID.setText("");
                jtxtSName.setText("");
                jtxtSAdd.setText("");
                jtxtSTel.setText("");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            }
        }
        loadTable();
     }
      public void searchupdate(){
        try{
            Connect con= new Connect();
            String query="Select * from customer where id='"+jtxtID.getText()+"' ";
           ResultSet rs = con.getQuery(query);
                if(rs.next()){
                String add1=rs.getString("id");
                jtxtID.setText(add1);
                 String add2=rs.getString("name");
                jtxtSName.setText(add2);
                String add4=rs.getString("adress");
                jtxtSAdd.setText(add4);
                String add5=rs.getString("tel");
                jtxtSTel.setText(add5);
                    }              
        }
        catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtxtSname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtxtSadd = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtxtStel = new javax.swing.JTextField();
        jlblId = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        lblw1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        updateBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jtxtSTel = new javax.swing.JTextField();
        jtxtSAdd = new javax.swing.JTextField();
        jtxtSName = new javax.swing.JTextField();
        jtxtID = new javax.swing.JTextField();
        lblnoti = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtxtSearch = new javax.swing.JTextField();
        viewBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(42, 128, 185));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Open Sans Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Customers");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel18.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Happy Customer -  Happy Deal..");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, 40));

        jtxtSname.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtSname.setForeground(new java.awt.Color(22, 160, 134));
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
        jPanel3.add(jtxtSname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 140, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Customer Name: ");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Customer Address: ");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 40));

        jtxtSadd.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtSadd.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSadd.setBorder(null);
        jtxtSadd.setOpaque(false);
        jtxtSadd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSaddKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtSadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 140, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Customer Tel: ");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 40));

        jtxtStel.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtStel.setForeground(new java.awt.Color(22, 160, 134));
        jtxtStel.setBorder(null);
        jtxtStel.setOpaque(false);
        jtxtStel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtStelKeyReleased(evt);
            }
        });
        jPanel3.add(jtxtStel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 140, 30));

        jlblId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblId.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(jlblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, 40));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Customer Id: ");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 40));

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
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 120, 30));

        lblw1.setForeground(new java.awt.Color(255, 51, 51));
        jPanel3.add(lblw1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 150, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/happy customer resized.jpg"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 220, 230));

        jTabbedPane1.addTab("    Add Customer   ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel4.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 100, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_text.png"))); // NOI18N
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, 40));

        jtxtSTel.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtSTel.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSTel.setBorder(null);
        jtxtSTel.setOpaque(false);
        jtxtSTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSTelKeyReleased(evt);
            }
        });
        jPanel4.add(jtxtSTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 140, 30));

        jtxtSAdd.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtSAdd.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSAdd.setBorder(null);
        jtxtSAdd.setOpaque(false);
        jtxtSAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSAddKeyReleased(evt);
            }
        });
        jPanel4.add(jtxtSAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 140, 30));

        jtxtSName.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtSName.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSName.setBorder(null);
        jtxtSName.setOpaque(false);
        jtxtSName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSNameKeyReleased(evt);
            }
        });
        jPanel4.add(jtxtSName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 140, 30));

        jtxtID.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtID.setForeground(new java.awt.Color(22, 160, 134));
        jtxtID.setBorder(null);
        jtxtID.setOpaque(false);
        jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtIDKeyReleased(evt);
            }
        });
        jPanel4.add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 140, 30));

        lblnoti.setForeground(new java.awt.Color(255, 51, 51));
        jPanel4.add(lblnoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 120, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Supplier Id: ");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Supplier Name: ");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Supplier Address: ");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Supplier Tel: ");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, 40));

        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/searchbtn.png"))); // NOI18N
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                searchBtnMouseReleased(evt);
            }
        });
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel4.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 50, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit customer resized.jpg"))); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 220, 230));

        jTabbedPane1.addTab("   Update Customer   ", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Open Sans Semibold", 0, 12)); // NOI18N
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(4);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 610, 240));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 270, -1));

        jtxtSearch.setFont(new java.awt.Font("Open Sans Semibold", 0, 14)); // NOI18N
        jtxtSearch.setForeground(new java.awt.Color(22, 160, 134));
        jtxtSearch.setBorder(null);
        jtxtSearch.setOpaque(false);
        jtxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSearchKeyReleased(evt);
            }
        });
        jPanel5.add(jtxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 210, 30));

        viewBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
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
        jPanel5.add(viewBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 90, 30));

        delBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
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
        jPanel5.add(delBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 90, 30));

        jTabbedPane1.addTab("View Customer", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtSnameAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jtxtSnameAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtSnameAncestorRemoved

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
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           inserting();
           jtxtSname.requestFocus();
        }

    }//GEN-LAST:event_jtxtStelKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        inserting();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyReleased
       
    }//GEN-LAST:event_jButton6KeyReleased

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        update();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void updateBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateBtnKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            update();
        }
    }//GEN-LAST:event_updateBtnKeyReleased

    private void jtxtSTelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSTelKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           update();
        }
    }//GEN-LAST:event_jtxtSTelKeyReleased

    private void jtxtSAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSAddKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSTel.requestFocus();
        }
    }//GEN-LAST:event_jtxtSAddKeyReleased

    private void jtxtSNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSNameKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSAdd.requestFocus();
        }
    }//GEN-LAST:event_jtxtSNameKeyReleased

    private void jtxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIDKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            jtxtSName.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            searchupdate();
        }
    }//GEN-LAST:event_jtxtIDKeyReleased

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

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_viewBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        searchupdate();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void updateBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBtnMouseReleased
        // TODO add your handling code here:
        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
    }//GEN-LAST:event_updateBtnMouseReleased

    private void updateBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBtnMousePressed
        // TODO add your handling code here:
        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_clicked.png"))); // NOI18N
    }//GEN-LAST:event_updateBtnMousePressed

    private void searchBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBtnMouseReleased
        // TODO add your handling code here:
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/searchbtn.png"))); // NOI18N
    }//GEN-LAST:event_searchBtnMouseReleased

    private void searchBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBtnMousePressed
        // TODO add your handling code here:
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/searchbtn_clicked.png"))); // NOI18N
    }//GEN-LAST:event_searchBtnMousePressed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delBtnActionPerformed

    private void delBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMouseReleased
        // TODO add your handling code here:
        delBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
    }//GEN-LAST:event_delBtnMouseReleased

    private void delBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMousePressed
        // TODO add your handling code here:
        delBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete_clicked.png"))); // NOI18N
    }//GEN-LAST:event_delBtnMousePressed

    private void viewBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBtnMouseReleased
        // TODO add your handling code here:
        viewBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
    }//GEN-LAST:event_viewBtnMouseReleased

    private void viewBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBtnMousePressed
        // TODO add your handling code here:
        viewBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_clicked.png"))); // NOI18N
    }//GEN-LAST:event_viewBtnMousePressed

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
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delBtn;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
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
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}
