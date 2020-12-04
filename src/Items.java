
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.connectcode.Code128Auto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hasintha
 */
public class Items extends javax.swing.JFrame {
      Connect con = new Connect();
      DefaultTableModel dtm;
    /**
     * Creates new form Items
     */
    boolean slideron = false;

    public Items() {
        initComponents();
        autoidget();
        getdata();
        Table();
        this.setLocationRelativeTo(null);
        jtxtItmName.requestFocus();
    }

    public void getfrominv(int i) {
        int id = i;
        jTabbedPane1.setSelectedIndex(1);

        try {
            String query = "select * from item where id='" + id + "'";

            ResultSet rs = con.getQuery(query);
            if (rs.next()) {
                String add1 = rs.getString("id");
                jtxtID.setText(add1);
                String add2 = rs.getString("name");
                jtxtINAME.setText(add2);
                jtxtBP.setText(rs.getString("buying_price"));
                jtxtRP.setText(rs.getString("retail_price"));
                jtxtWP.setText(rs.getString("wholesale_price"));
                jtxtQTY.setText(rs.getString("qty"));

//================== Category ID Start ==============================================================
                String q = "SELECT name FROM catogory WHERE id ='" + rs.getInt("catogory_id") + "'";
                ResultSet rs3 = con.getQuery(q);

                String name = "";
                if (rs3.next()) {
                    name = rs3.getString("name");
                }
                String query_e = "(SELECT  name FROM catogory)";
                ResultSet rs2 = con.getQuery(query_e);
                while (rs2.next()) {
                    if (rs2.getString("name") == name) {
                        continue;
                    }
                    comboCAT.addItem(rs2.getString("name"));

                }
                rs3.close();
                rs2.close();
                //=======================End of Category ID and Supplier ID Start===============================
                String qsup = "SELECT name FROM supplier WHERE id ='" + rs.getInt("supplier_id") + "'";
                ResultSet rs4 = con.getQuery(qsup);

                String name_sup = "";
                if (rs4.next()) {
                    name_sup = rs4.getString("name");
                }
                String query_supname = "(SELECT  name FROM catogory)";
                ResultSet rs5 = con.getQuery(query_supname);
                while (rs5.next()) {
                    if (rs5.getString("name") == name_sup) {
                        continue;
                    }
                    comboSUP.addItem(rs5.getString("name"));

                }
                rs4.close();
                rs5.close();
                //=======================End of Supplier ID and Stock ID Start===============================
                String qstock = "SELECT name FROM stock WHERE id ='" + rs.getInt("stock_id") + "'";
                ResultSet rs6 = con.getQuery(qstock);

                String name_stock = "";
                if (rs6.next()) {
                    name_sup = rs6.getString("name");
                }
                String query_stockname = "(SELECT  name FROM stock)";
                ResultSet rs7 = con.getQuery(query_stockname);
                while (rs7.next()) {
                    if (rs7.getString("name") == name_stock) {
                        continue;
                    }
                    comboSTOCK.addItem(rs7.getString("name"));

                }
                rs6.close();
                rs7.close();
                //=======================End of Stock ID and Store ID Start===============================
                String qstore = "SELECT name FROM store WHERE id ='" + rs.getInt("store_id") + "'";
                ResultSet rs8 = con.getQuery(qstore);

                String name_store = "";
                if (rs8.next()) {
                    name_store = rs8.getString("name");
                }
                String query_storename = "(SELECT  name FROM store)";
                ResultSet rs9 = con.getQuery(query_storename);
                while (rs9.next()) {
                    if (rs9.getString("name") == name_store) {
                        continue;
                    }
                    comboSTORE.addItem(rs9.getString("name"));

                }
                rs6.close();
                rs7.close();
                //=======================End of Store ID===============================
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();
            
        }
    }

    // to load combo box*******************************
    public void getdata() {
        try {
            
            String query = "SELECT name FROM catogory";
            String query2 = "SELECT name FROM stock";
            String query3 = "SELECT name FROM store";
            String query4 = "SELECT name FROM supplier";

            ResultSet rset = con.getQuery(query);
            ResultSet rset2 = con.getQuery(query2);
            ResultSet rset3 = con.getQuery(query3);
            ResultSet rset4 = con.getQuery(query4);

            try {

                while (rset.next()) {
                    String name = rset.getString("name");
                    combocate.addItem(name);
                }
                while (rset2.next()) {
                    String name = rset2.getString("name");
                    combostock.addItem(name);
                }
                while (rset3.next()) {
                    String name = rset3.getString("name");
                    combostore.addItem(name);
                }
                while (rset4.next()) {
                    String name = rset4.getString("name");
                    combosup.addItem(name);
                }
                rset.close();
                rset2.close();
                rset3.close();
                rset4.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (HeadlessException e2) {
            JOptionPane.showMessageDialog(null, e2);
        }finally{
             System.gc();   
        }
    }

    //search method 
    public void search() {
        try {
          
            String query = "select * from item where id like '" + jtxtSearch.getText() + "%' or name like '" + jtxtSearch.getText() + "%'";
            dtm.setRowCount(0);
            ResultSet rs = con.getQuery(query);
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
                rs3.close();
                rs2.close();
                dtm.addRow(new Object[]{itemid, name, catogory, supplier, itemqty, itembuying_price, itemretail_price, itemwholesale_price, expired_date});
            }

            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }

    }

    public void searchupdate() {
        try {
           
            String query = "Select * from item where id='" + jtxtID.getText() + "' ";
            ResultSet rs = con.getQuery(query);
            if (rs.next()) {
                String add1 = rs.getString("name");
                jtxtINAME.setText(add1);
                String add3 = rs.getString("Buying_price");
                jtxtBP.setText(add3);
                String add4 = rs.getString("Retail_price");
                jtxtRP.setText(add4);
                String add5 = rs.getString("Wholesale_price");
                jtxtWP.setText(add5);
                String add6 = rs.getString("qty");
                jtxtQTY.setText(add6);
                SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
                Date dateValue = null;
                try {
                    dateValue = date.parse(rs.getString("expired_date"));
                } catch (ParseException ex) {
                    Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                }
                jDateChooser2.setDate(dateValue);
                int id = rs.getInt("catogory_id");
                String query3 = "select name from catogory where id='" + id + "'";
                ResultSet rs2 = con.getQuery(query3);
                while (rs2.next()) {
                    comboCAT.addItem(rs2.getString("name"));
                }
                int id2 = rs.getInt("supplier_id");
                String query4 = "select name from supplier where id='" + id2 + "'";
                ResultSet rs3 = con.getQuery(query4);
                while (rs3.next()) {
                    comboSUP.addItem(rs3.getString("name"));
                }
                int id3 = rs.getInt("stock_id");
                String query5 = "select name from stock where id='" + id3 + "'";
                ResultSet rs4 = con.getQuery(query5);
                while (rs4.next()) {
                    comboSTOCK.addItem(rs4.getString("name"));
                }
                int id4 = rs.getInt("store_id");
                String query6 = "select name from store where id='" + id4 + "'";
                ResultSet rs5 = con.getQuery(query6);
                while (rs5.next()) {
                    comboSTORE.addItem(rs5.getString("name"));
                }

                rs3.close();
                rs2.close();
                rs4.close();
                rs5.close();
            } else {
                JOptionPane.showMessageDialog(null, "The book id not found");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }
    }

    //to genarate next id///////////////////////////////////////////////////////
    public void autoidget() {
        
        String query = "select max(id) as max from item ";
        try {
            ResultSet rs = con.getQuery(query);
            if (rs.next()) {
                int num = rs.getInt("max");
                num = num + 1;
                String x = "" + num;
                jlblid.setText(x);
                Code128Auto code128 = new Code128Auto();
                String barcode = code128.encode(jlblid.getText());
                jtxtAB.setText(barcode);
                jtxtAB.setFont(new java.awt.Font("CCode128_S3_Trial", java.awt.Font.PLAIN, 24));
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }

    }

    //view data
    public void Table() {
        dtm = (DefaultTableModel) jTable1.getModel();
        try {
           
            String query = "select * from item";
            dtm.setRowCount(0);
            ResultSet rs = con.getQuery(query);
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

                String catogory = "";
                String supplier = "";
                String query4 = "select name from supplier where id='" + supplierid + "'";
                ResultSet rs2 = con.getQuery(query4);
                while (rs2.next()) {
                    supplier = rs2.getString("name");
                }
                rs2.close();

                String query5 = "select name from catogory where id='" + catogoryid + "'";
                ResultSet rs3 = con.getQuery(query5);
                while (rs3.next()) {
                    catogory = rs3.getString("name");
                }
                rs3.close();
                dtm.addRow(new Object[]{itemid, name, catogory, supplier, itemqty, itembuying_price, itemretail_price, itemwholesale_price, expired_date});
            }
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
             System.gc();   
        }
    }

    public void clear2() {
        jtxtINAME.setText("");
        jtxtBP.setText("");
        jtxtRP.setText("");
        jtxtWP.setText("");
        jtxtQTY.setText("");
        ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).setText("");
        comboCAT.setSelectedIndex(0);
        comboSTOCK.setSelectedIndex(0);
        comboSTORE.setSelectedIndex(0);
        comboSUP.setSelectedIndex(0);
    }

    public void inserting() {

    
        int id = 1;
        int id2 = 1;
        int id3 = 1;
        int id4 = 1;
        jDateChooser1.setDateFormatString("yyyy/MM/dd");
        String cat = combocate.getSelectedItem().toString();
        String stock = combostock.getSelectedItem().toString();
        String store = combostore.getSelectedItem().toString();
        String sup = combosup.getSelectedItem().toString();

        try {
            String query3 = "select id from catogory where name='" + cat + "'";
            ResultSet rs = con.getQuery(query3);
            while (rs.next()) {
                id = rs.getInt("id");
            }
            String query4 = "select id from supplier where name='" + sup + "'";
            ResultSet rs2 = con.getQuery(query4);
            while (rs2.next()) {
                id2 = rs2.getInt("id");
            }
            String query5 = "select id from stock where name='" + stock + "'";
            ResultSet rs3 = con.getQuery(query5);
            while (rs3.next()) {
                id3 = rs3.getInt("id");
            }
            String query6 = "select id from store where name='" + store + "'";
            ResultSet rs4 = con.getQuery(query6);
            while (rs4.next()) {
                id4 = rs4.getInt("id");
            }
            if (slideron) {
                String query = "INSERT INTO item(name,buying_price,retail_price,wholesale_Price,qty,expired_date,catogory_id,supplier_id,stock_id,store_id,barcode) VALUES("
                        + "'" + jtxtItmName.getText() + "',"
                        + "'" + jtxtBprice.getText() + "',"
                        + "'" + jtxtRprice.getText() + "',"
                        + "'" + jtxtWprice.getText() + "',"
                        + "'" + jtxtQty.getText() + "',"
                        + "'" + ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText() + "',"
                        + "'" + id + "',"
                        + "'" + id2 + "',"
                        + "'" + id3 + "',"
                        + "'" + id4 + "',"
                        + "'" + jtxtAB.getText() + "'"
                        + ")";

                int genId = con.setQuery(query, "");
            } else {
                String query = "INSERT INTO item(name,buying_price,retail_price,wholesale_Price,qty,expired_date,catogory_id,supplier_id,stock_id,store_id,barcode) VALUES("
                        + "'" + jtxtItmName.getText() + "',"
                        + "'" + jtxtBprice.getText() + "',"
                        + "'" + jtxtRprice.getText() + "',"
                        + "'" + jtxtWprice.getText() + "',"
                        + "'" + jtxtQty.getText() + "',"
                        + "'" + ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText() + "',"
                        + "'" + id + "',"
                        + "'" + id2 + "',"
                        + "'" + id3 + "',"
                        + "'" + id4 + "',"
                        + "'" + "NULL" + "'"
                        + ")";

                int genId = con.setQuery(query, "");
            }
            JOptionPane.showMessageDialog(null, "Item Added Successfuly!");
            rs.close();
            rs2.close();
            rs3.close();
            rs4.close();
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
             System.gc();   
        }
        Table();
        clear();
        autoidget();
    }

    public void delete() {
        int action = 0;
        if (!jtxtSearch.getText().equals("")) {
            action = JOptionPane.showConfirmDialog(null, "Do you really want to delete ", "delete", JOptionPane.YES_OPTION);
            if (action == 00) {
                try {
                    
                    String query = "delete from item where id='" + jtxtID.getText() + "'";
                    con.setQuery(query);
                    clear2();
                    jtxtID.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }finally{
             System.gc();   
        }
            }
        }
        Table();

    }

    public void clear() {
        jtxtItmName.setText("");
        jtxtBprice.setText("");
        jtxtRprice.setText("");
        jtxtWprice.setText("");
        jtxtQty.setText("");
        ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).setText("");
        combocate.setSelectedIndex(0);
        combostock.setSelectedIndex(0);
        combostore.setSelectedIndex(0);
        combosup.setSelectedIndex(0);

        onoffslider.setValue(50);
        onofflable.setText("Off");
        onofflable.setForeground(new Color(102, 102, 102));
        jtxtAB.setEnabled(false);
        slideron = false;
    }

    public void update() {
       
        int id = 1;
        int id2 = 1;
        int id3 = 1;
        int id4 = 1;
        jDateChooser2.setDateFormatString("yyyy/MM/dd");
        String cat = comboCAT.getSelectedItem().toString();
        String stock = comboSTOCK.getSelectedItem().toString();
        String store = comboSTORE.getSelectedItem().toString();
        String sup = comboSUP.getSelectedItem().toString();

        try {
            String query3 = "select id from catogory where name='" + cat + "'";
            ResultSet rs = con.getQuery(query3);
            while (rs.next()) {
                id = rs.getInt("id");
            }
            String query4 = "select id from supplier where name='" + sup + "'";
            ResultSet rs2 = con.getQuery(query4);
            while (rs2.next()) {
                id2 = rs2.getInt("id");
            }
            String query5 = "select id from stock where name='" + stock + "'";
            ResultSet rs3 = con.getQuery(query5);
            while (rs3.next()) {
                id3 = rs3.getInt("id");
            }
            String query6 = "select id from store where name='" + store + "'";
            ResultSet rs4 = con.getQuery(query6);
            while (rs4.next()) {
                id4 = rs4.getInt("id");
            }
            String query = "update item set name='" + jtxtINAME.getText()
                    + "',buying_price='" + jtxtBP.getText()
                    + "',retail_price='" + jtxtRP.getText()
                    + "',wholesale_Price='" + jtxtWP.getText()
                    + "',qty='" + jtxtQTY.getText()
                    + "',expired_date='" + ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText()
                    + "',catogory_id='" + id
                    + "',supplier_id='" + id2
                    + "',stock_id='" + id3
                    + "',store_id='" + id4 + "' where id='" + jtxtID.getText() + "' ";

            con.setQuery(query);
            JOptionPane.showMessageDialog(null, "Updated the data");
            rs.close();
            rs2.close();
            rs3.close();
            rs4.close();

        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }finally{
             System.gc();   
        }
    }

    public void tableclick() {
        try {
           
            int row = jTable1.getSelectedRow();
            String table_click = (jTable1.getModel().getValueAt(row, 0).toString());
            String query = "select * from item where id='" + table_click + "'";

            ResultSet rs = con.getQuery(query);
            if (rs.next()) {
                String add1 = rs.getString("id");
                jtxtID.setText(add1);
                String add2 = rs.getString("name");
                jtxtSearch.setText(add2);
            }
            rs.close();
        } catch (SQLException e) {
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

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelNewItem = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxtRprice = new javax.swing.JTextField();
        jlblid = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtWprice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtxtItmName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtxtBprice = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jtxtQty = new javax.swing.JTextField();
        combostore = new javax.swing.JComboBox<>();
        combocate = new javax.swing.JComboBox<>();
        combosup = new javax.swing.JComboBox<>();
        combostock = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jtxtAB = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        onoffslider = new javax.swing.JSlider();
        jLabel31 = new javax.swing.JLabel();
        onofflable = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jtxtQTY = new javax.swing.JTextField();
        jtxtWP = new javax.swing.JTextField();
        jtxtRP = new javax.swing.JTextField();
        jtxtID = new javax.swing.JTextField();
        jtxtBP = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        comboSTORE = new javax.swing.JComboBox<>();
        comboSTOCK = new javax.swing.JComboBox<>();
        comboSUP = new javax.swing.JComboBox<>();
        comboCAT = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jtxtINAME = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtxtSearch = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        panelNewItem.setBackground(new java.awt.Color(255, 255, 255));
        panelNewItem.setMaximumSize(new java.awt.Dimension(21474836, 21474836));
        panelNewItem.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Retail Price: ");
        panelNewItem.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Category :");
        panelNewItem.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 70, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Supplier :");
        panelNewItem.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 60, 30));

        jtxtRprice.setBackground(new java.awt.Color(255, 255, 255));
        jtxtRprice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtRprice.setBorder(null);
        jtxtRprice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtRpriceFocusGained(evt);
            }
        });
        jtxtRprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtRpriceActionPerformed(evt);
            }
        });
        jtxtRprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtRpriceKeyReleased(evt);
            }
        });
        panelNewItem.add(jtxtRprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, 30));

        jlblid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblid.setForeground(new java.awt.Color(255, 51, 51));
        panelNewItem.add(jlblid, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 60, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Wholesale Price: ");
        panelNewItem.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 110, 40));

        jtxtWprice.setBackground(new java.awt.Color(255, 255, 255));
        jtxtWprice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtWprice.setBorder(null);
        jtxtWprice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtWpriceFocusGained(evt);
            }
        });
        jtxtWprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtWpriceActionPerformed(evt);
            }
        });
        jtxtWprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtWpriceKeyReleased(evt);
            }
        });
        panelNewItem.add(jtxtWprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 150, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Expire Date :");
        panelNewItem.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Item Name: ");
        panelNewItem.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 46, -1, 30));

        jtxtItmName.setBackground(new java.awt.Color(255, 255, 255));
        jtxtItmName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtItmName.setBorder(null);
        jtxtItmName.setOpaque(false);
        jtxtItmName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtItmNameFocusGained(evt);
            }
        });
        jtxtItmName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtItmNameActionPerformed(evt);
            }
        });
        jtxtItmName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtItmNameKeyReleased(evt);
            }
        });
        panelNewItem.add(jtxtItmName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 150, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Buying Price: ");
        panelNewItem.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        panelNewItem.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 160, 30));

        jtxtBprice.setBackground(new java.awt.Color(255, 255, 255));
        jtxtBprice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtBprice.setBorder(null);
        jtxtBprice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtBpriceFocusGained(evt);
            }
        });
        jtxtBprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtBpriceActionPerformed(evt);
            }
        });
        jtxtBprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtBpriceKeyReleased(evt);
            }
        });
        panelNewItem.add(jtxtBprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 150, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Stock:");
        panelNewItem.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 40, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Barcode :");
        panelNewItem.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, 24));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Qty:");
        panelNewItem.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 50, 24));

        jtxtQty.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtQty.setBorder(null);
        jtxtQty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtQtyFocusGained(evt);
            }
        });
        jtxtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtQtyActionPerformed(evt);
            }
        });
        jtxtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtQtyKeyReleased(evt);
            }
        });
        panelNewItem.add(jtxtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 150, 30));

        combostore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                combostoreKeyReleased(evt);
            }
        });
        panelNewItem.add(combostore, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 136, 110, 30));

        combocate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                combocateKeyReleased(evt);
            }
        });
        panelNewItem.add(combocate, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 16, 110, 30));

        combosup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                combosupKeyReleased(evt);
            }
        });
        panelNewItem.add(combosup, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 56, 110, 30));

        combostock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                combostockKeyReleased(evt);
            }
        });
        panelNewItem.add(combostock, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 96, 110, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Store :");
        panelNewItem.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 134, -1, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
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
        panelNewItem.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 70, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_items.png"))); // NOI18N
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
        panelNewItem.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 70, 40));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Item Id: ");
        panelNewItem.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 0, 60, 40));
        panelNewItem.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 150, 30));

        jtxtAB.setBackground(new java.awt.Color(204, 255, 204));
        jtxtAB.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jtxtAB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxtAB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtxtAB.setEnabled(false);
        panelNewItem.add(jtxtAB, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 140, 60));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        panelNewItem.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, 30));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        panelNewItem.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, 30));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        panelNewItem.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 160, 30));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        panelNewItem.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 160, 30));

        onoffslider.setBackground(new java.awt.Color(255, 255, 255));
        onoffslider.setForeground(new java.awt.Color(102, 102, 102));
        onoffslider.setMajorTickSpacing(25);
        onoffslider.setMinimum(50);
        onoffslider.setMinorTickSpacing(50);
        onoffslider.setPaintTicks(true);
        onoffslider.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                onoffsliderAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                onoffsliderAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        onoffslider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onoffsliderMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onoffsliderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                onoffsliderMouseReleased(evt);
            }
        });
        panelNewItem.add(onoffslider, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, 60, -1));

        jLabel31.setFont(new java.awt.Font("Open Sans Semibold", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Auto Generate :");
        panelNewItem.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 176, -1, 30));

        onofflable.setFont(new java.awt.Font("Open Sans Semibold", 1, 14)); // NOI18N
        onofflable.setForeground(new java.awt.Color(102, 102, 102));
        onofflable.setText("Off");
        panelNewItem.add(onofflable, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 176, -1, 30));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelNewItem.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 20, 340));

        jTabbedPane1.addTab("    Add Item   ", panelNewItem);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Item Id: ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 0, 60, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Item Name: ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 46, -1, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Buying Price: ");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Retail Price: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 40));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Wholesale Price: ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Expire Date");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Qty:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 230, 50, 24));

        jtxtQTY.setBackground(new java.awt.Color(255, 255, 255));
        jtxtQTY.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtQTY.setForeground(new java.awt.Color(51, 51, 51));
        jtxtQTY.setBorder(null);
        jtxtQTY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtQTYFocusGained(evt);
            }
        });
        jtxtQTY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtQTYActionPerformed(evt);
            }
        });
        jtxtQTY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtQTYKeyReleased(evt);
            }
        });
        jPanel1.add(jtxtQTY, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 100, 30));

        jtxtWP.setBackground(new java.awt.Color(255, 255, 255));
        jtxtWP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtWP.setForeground(new java.awt.Color(51, 51, 51));
        jtxtWP.setBorder(null);
        jtxtWP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtWPFocusGained(evt);
            }
        });
        jtxtWP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtWPActionPerformed(evt);
            }
        });
        jtxtWP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtWPKeyReleased(evt);
            }
        });
        jPanel1.add(jtxtWP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 183, 150, 30));

        jtxtRP.setBackground(new java.awt.Color(255, 255, 255));
        jtxtRP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtRP.setForeground(new java.awt.Color(51, 51, 51));
        jtxtRP.setBorder(null);
        jtxtRP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtRPFocusGained(evt);
            }
        });
        jtxtRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtRPActionPerformed(evt);
            }
        });
        jtxtRP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtRPKeyReleased(evt);
            }
        });
        jPanel1.add(jtxtRP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 150, 30));

        jtxtID.setBackground(new java.awt.Color(255, 255, 255));
        jtxtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtID.setForeground(new java.awt.Color(51, 51, 51));
        jtxtID.setBorder(null);
        jtxtID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtIDFocusGained(evt);
            }
        });
        jtxtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtIDActionPerformed(evt);
            }
        });
        jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtIDKeyReleased(evt);
            }
        });
        jPanel1.add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 90, 30));

        jtxtBP.setBackground(new java.awt.Color(255, 255, 255));
        jtxtBP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtBP.setForeground(new java.awt.Color(51, 51, 51));
        jtxtBP.setBorder(null);
        jtxtBP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtBPFocusGained(evt);
            }
        });
        jtxtBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtBPActionPerformed(evt);
            }
        });
        jtxtBP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtBPKeyReleased(evt);
            }
        });
        jPanel1.add(jtxtBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 150, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Store :");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Stock:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 40, 50));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Supplier :");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 60, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Category :");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 70, 40));

        jPanel1.add(comboSTORE, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 110, 30));

        jPanel1.add(comboSTOCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 110, 30));

        jPanel1.add(comboSUP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 110, 30));

        jPanel1.add(comboCAT, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 110, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 70, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_items.png"))); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 70, 40));

        jtxtINAME.setBackground(new java.awt.Color(255, 255, 255));
        jtxtINAME.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtINAME.setForeground(new java.awt.Color(51, 51, 51));
        jtxtINAME.setBorder(null);
        jtxtINAME.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtINAMEFocusGained(evt);
            }
        });
        jtxtINAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtINAMEActionPerformed(evt);
            }
        });
        jtxtINAME.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtINAMEKeyReleased(evt);
            }
        });
        jPanel1.add(jtxtINAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 150, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/searchbtn.png"))); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton5MouseReleased(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 50, 30));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 150, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, 40));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, 40));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 90, 60));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, 40));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, 50));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 120, 60));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/type_field.png"))); // NOI18N
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 120, 60));

        jTabbedPane1.addTab("     Edit Item    ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Id", "Name", "Category", "Supplier", "Stock", "Buying Price", "Retail Price", "Wholesale Price", "Expire Date"
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 610, 320));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Search Items");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jtxtSearch.setBorder(null);
        jtxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtSearchKeyReleased(evt);
            }
        });
        jPanel2.add(jtxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 6, 240, 30));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton11KeyReleased(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 90, 30));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton6MouseReleased(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 70, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel2.setText("a");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 280, -1));

        jTabbedPane1.addTab("    View Item    ", jPanel2);

        jPanel3.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 627, 410));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtRpriceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtRpriceFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtRpriceFocusGained

    private void jtxtRpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtRpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtRpriceActionPerformed

    private void jtxtWpriceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtWpriceFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtWpriceFocusGained

    private void jtxtWpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtWpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtWpriceActionPerformed

    private void jtxtItmNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtItmNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtItmNameFocusGained

    private void jtxtItmNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtItmNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtItmNameActionPerformed

    private void jtxtBpriceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtBpriceFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtBpriceFocusGained

    private void jtxtBpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtBpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtBpriceActionPerformed

    private void jtxtQtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtQtyFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtQtyFocusGained

    private void jtxtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtQtyActionPerformed

    private void jtxtWPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtWPFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtWPFocusGained

    private void jtxtWPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtWPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtWPActionPerformed

    private void jtxtRPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtRPFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtRPFocusGained

    private void jtxtRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtRPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtRPActionPerformed

    private void jtxtIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtIDFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIDFocusGained

    private void jtxtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIDActionPerformed

    private void jtxtBPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtBPFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtBPFocusGained

    private void jtxtBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtBPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtBPActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        inserting();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtxtItmNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtItmNameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtBprice.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtBprice.requestFocus();
        }
    }//GEN-LAST:event_jtxtItmNameKeyReleased

    private void jtxtBpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtBpriceKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtRprice.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtRprice.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            jtxtBprice.requestFocus();
        }
    }//GEN-LAST:event_jtxtBpriceKeyReleased

    private void jtxtRpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtRpriceKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtWprice.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtWprice.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            jtxtRprice.requestFocus();
        }
    }//GEN-LAST:event_jtxtRpriceKeyReleased

    private void jtxtWpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtWpriceKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            jtxtWprice.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtQty.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtQty.requestFocus();
        }

    }//GEN-LAST:event_jtxtWpriceKeyReleased

    private void jtxtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtQtyKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jDateChooser1.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jDateChooser1.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            jtxtQty.requestFocus();
        }
    }//GEN-LAST:event_jtxtQtyKeyReleased

    private void jDateChooser1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            combocate.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            combocate.requestFocus();
        }
    }//GEN-LAST:event_jDateChooser1KeyReleased

    private void combocateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combocateKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            combosup.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            combosup.requestFocus();
        }
    }//GEN-LAST:event_combocateKeyReleased

    private void combosupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combosupKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            combostock.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            combostock.requestFocus();
        }
    }//GEN-LAST:event_combosupKeyReleased

    private void combostockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combostockKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            combostore.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            combostore.requestFocus();
        }
    }//GEN-LAST:event_combostockKeyReleased

    private void combostoreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combostoreKeyReleased


    }//GEN-LAST:event_combostoreKeyReleased

    private void jtxtINAMEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtINAMEFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtINAMEFocusGained

    private void jtxtINAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtINAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtINAMEActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        update();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        searchupdate();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tableclick();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jtxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSearchKeyReleased
        search();
    }//GEN-LAST:event_jtxtSearchKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        delete();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            delete();
        }
    }//GEN-LAST:event_jButton11KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear2();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jtxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIDKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtINAME.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchupdate();
        }
    }//GEN-LAST:event_jtxtIDKeyReleased

    private void jtxtINAMEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtINAMEKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtBP.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtBP.requestFocus();
        }
    }//GEN-LAST:event_jtxtINAMEKeyReleased

    private void jtxtBPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtBPKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtRP.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtRP.requestFocus();
        }
    }//GEN-LAST:event_jtxtBPKeyReleased

    private void jtxtRPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtRPKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtWP.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtWP.requestFocus();
        }
    }//GEN-LAST:event_jtxtRPKeyReleased

    private void jtxtWPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtWPKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jtxtQTY.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtxtQTY.requestFocus();
        }
    }//GEN-LAST:event_jtxtWPKeyReleased

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            delete();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Table();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtxtQTYKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtQTYKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jDateChooser2.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jDateChooser2.requestFocus();
        }
    }//GEN-LAST:event_jtxtQTYKeyReleased

    private void jtxtQTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtQTYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtQTYActionPerformed

    private void jtxtQTYFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtQTYFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtQTYFocusGained

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        // TODO add your handling code here:
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_items.png"))); // NOI18N

    }//GEN-LAST:event_jButton2MouseReleased

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_items_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseReleased
        // TODO add your handling code here:
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
    }//GEN-LAST:event_jButton3MouseReleased

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        // TODO add your handling code here:
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        // TODO add your handling code here:
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_items.png"))); // NOI18N
    }//GEN-LAST:event_jButton4MouseReleased

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        // TODO add your handling code here:
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update_items_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseReleased
        // TODO add your handling code here:
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
    }//GEN-LAST:event_jButton6MouseReleased

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        // TODO add your handling code here:
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton6MousePressed

    private void onoffsliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onoffsliderMouseReleased
        // TODO add your handling code here:

        int onoff = onoffslider.getValue();
        if (onoff > 75) {
            onofflable.setText("On");
            onofflable.setForeground(new Color(22, 160, 134));
            onoffslider.setValue(100);
            slideron = false;
            jtxtAB.setEnabled(true);
        } else {
            onofflable.setText("Off");
            onofflable.setForeground(new Color(102, 102, 102));
            onoffslider.setValue(50);
            slideron = true;
            jtxtAB.setEnabled(false);
        }
    }//GEN-LAST:event_onoffsliderMouseReleased

    private void onoffsliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onoffsliderMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_onoffsliderMouseClicked

    private void onoffsliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onoffsliderMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_onoffsliderMousePressed

    private void onoffsliderAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_onoffsliderAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_onoffsliderAncestorAdded

    private void onoffsliderAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_onoffsliderAncestorMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_onoffsliderAncestorMoved

    private void jButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseReleased
        // TODO add your handling code here:
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/searchbtn.png"))); // NOI18N
    }//GEN-LAST:event_jButton5MouseReleased

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        // TODO add your handling code here:
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/searchbtn_clicked.png"))); // NOI18N
    }//GEN-LAST:event_jButton5MousePressed
    //clear method

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
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Items().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCAT;
    private javax.swing.JComboBox<String> comboSTOCK;
    private javax.swing.JComboBox<String> comboSTORE;
    private javax.swing.JComboBox<String> comboSUP;
    private javax.swing.JComboBox<String> combocate;
    private javax.swing.JComboBox<String> combostock;
    private javax.swing.JComboBox<String> combostore;
    private javax.swing.JComboBox<String> combosup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlblid;
    private javax.swing.JLabel jtxtAB;
    private javax.swing.JTextField jtxtBP;
    private javax.swing.JTextField jtxtBprice;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtINAME;
    private javax.swing.JTextField jtxtItmName;
    private javax.swing.JTextField jtxtQTY;
    private javax.swing.JTextField jtxtQty;
    private javax.swing.JTextField jtxtRP;
    private javax.swing.JTextField jtxtRprice;
    private javax.swing.JTextField jtxtSearch;
    private javax.swing.JTextField jtxtWP;
    private javax.swing.JTextField jtxtWprice;
    private javax.swing.JLabel onofflable;
    private javax.swing.JSlider onoffslider;
    private javax.swing.JPanel panelNewItem;
    // End of variables declaration//GEN-END:variables
}
