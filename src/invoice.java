
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hasintha
 */
public class invoice extends javax.swing.JFrame {
    //  ArrayList name=new ArrayList();

    Connect con = new Connect();
    private final DefaultTableModel dtm;
    int genID;
    String cusname = "";
    String cashiername = "";
    String noi = "";
    String tot = "";
    String dis = "";
    String netamnt = "";
    String balance = "";
    String paid = "";
    Date date = null;
    SimpleDateFormat sdf = null;
    String fdate = "";
    
    ResultSet rsm1 =null;
    ResultSet rsm2 = null;
    ResultSet rsm3 = null;
    
    String querym1 = "";
    String querym2 = "";
    String querym3 = "";
    
    

     
    public invoice() {
        initComponents();
        jList1.setVisible(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        dtm = (DefaultTableModel) tableTxt.getModel();
        discountTxt.setText("0");
        getdata();
        getmaxid();

        jScrollPane3.setVisible(false);
       this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            System.err.println("Closed");
            Runtime r = Runtime.getRuntime();
            r.gc();
        }
    });
                }

    public void removefromstock() {

        try {
            for (int count = 0; 0 < dtm.getRowCount(); count++) {
                int id = Integer.parseInt(dtm.getValueAt(count, 0).toString());
                System.out.println(id);
            }
        } catch (NumberFormatException numberFormatException) {
        }
    }

    public void listload() {
        jScrollPane3.setVisible(true);
        if (barcodeTxt.getText().equals("")) {

            jScrollPane3.setVisible(false);
        } else {

        }
        try {

            DefaultListModel lm = new DefaultListModel();
            String query = "select * from item where name like'" + barcodeTxt.getText() + "%'";
            lm.removeAllElements();
            int v = 0;
            ResultSet rs = con.getQuery(query);
            while (rs.next()) {
                lm.addElement(rs.getString("name"));
                jList1.setModel(lm);
                v++;
            }
            if (v >= 1) {
                jList1.setVisible(true);
            } else {
                jList1.setVisible(false);
            }
            rs.close();
        } catch (Exception e) {

        }finally{
             System.gc();   
        }
    }

    public void getmaxid() {
        String query = "SELECT MAX(id) AS max FROM invoice";
        Connect con = new Connect();
        ResultSet rset = con.getQuery(query);
        int maxint = 0;
        try {
            while (rset.next()) {
                maxint = rset.getInt("max") + 1;

                rset.close();
            }
        } catch (Exception e) {
        }finally{
             System.gc();   
        }
        invIDLbl.setText("" + maxint);

    }

    public void getdata() {
        try {
            String query = "SELECT name FROM customer";
            String query2 = "SELECT name FROM cashier";

            ResultSet rset = con.getQuery(query);
            ResultSet rset2 = con.getQuery(query2);

            try {

                while (rset.next()) {
                    String name = rset.getString("name");
                    customerCombo.addItem(name);
                }
                rset.close();
                while (rset2.next()) {
                    String name = rset2.getString("name");
                    cashierCombo.addItem(name);
                }
                rset2.close();

            } catch (SQLException e) {
                //Logger.getLogger(ViewCus.class.getName()).log(Level.SEVERE, null, e);
                qtyTxt.requestFocus();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }finally{
             System.gc();   
        }
    }

    public void getfromid() {
        try {

            String query = "SELECT name, retail_price FROM item WHERE id='" + itemidTxt.getText() + "'";

            ResultSet rset = con.getQuery(query);
            try {
                while (rset.next()) {

                    String itmname = rset.getString("name");
                    String uprice = rset.getString("retail_price");

                    itemnameTxt.setText(itmname);
                    unitpriceTxt.setText(uprice);
                    jLabalnoti.setText("");

                    qtyTxt.requestFocus();
                    qtyTxt.setText("");
                }
                rset.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                qtyTxt.requestFocus();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }finally{
             System.gc();   
        }

    }

    private void getfromitems() {
        String bar = barcodeTxt.getText();
        boolean f = false;
        if (bar.equals("")) {
        } else {
            try {

                String query = "SELECT id,name, retail_price FROM item WHERE id=(SELECT item_id FROM barcode WHERE barcode='" + bar + "')";

                ResultSet rset = con.getQuery(query);
                try {
                    while (rset.next()) {
                        int id = rset.getInt("id");
                        String itmname = rset.getString("name");
                        String uprice = rset.getString("retail_price");
                        itemidTxt.setText("" + id);
                        itemnameTxt.setText(itmname);
                        unitpriceTxt.setText(uprice);
                        jLabalnoti.setText("");
                        qtyTxt.requestFocus();
                        qtyTxt.setText("");
                        f = true;
                    }
                    rset.close();
                } catch (SQLException e) {
                    //  Logger.getLogger(ViewCus.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println("invoice.getfromitems()");
                }
            } catch (Exception e) {
                System.out.println("Test");
            }finally{
             System.gc();   
        }

        }
        if (f) {
            System.err.println("found");

            barcodeTxt.setText("");
        } else {
            System.err.println("not found");
            jScrollPane3.setVisible(true);
            listload();
        }

    }

    /**
     * invoice ---- start
     */
    private void balance() {
        try {
            double paid = Double.parseDouble(paidTxt.getText());
            double netamouunt = Double.parseDouble(netTxt.getText());
            double balance = paid - netamouunt;
            balanceTxt.setText("" + balance);

        } catch (NumberFormatException e) {
        }finally{
             System.gc();   
        }

    }

    private void setTotalValue() {
        String qty = qtyTxt.getText().trim();
        String uprice = unitpriceTxt.getText().trim();
        try {
            double qtyD = Double.parseDouble(qty);
            double upriceD = Double.parseDouble(uprice);

            double total = qtyD * upriceD;
            totTxt.setText("" + total);

        } catch (NumberFormatException e) {
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        invIDLbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        barcodeTxt = new javax.swing.JTextField();
        itemidTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        itemnameTxt = new javax.swing.JTextField();
        qtyTxt = new javax.swing.JFormattedTextField();
        unitpriceTxt = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        totTxt = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        discountTxt = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTxt = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        subtotal = new javax.swing.JFormattedTextField();
        discountTotal = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        netTxt = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        paidTxt = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        balanceTxt = new javax.swing.JFormattedTextField();
        checkoutBtn = new javax.swing.JButton();
        noiTxt = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        starlbl = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        customerCombo = new javax.swing.JComboBox<>();
        cashierCombo = new javax.swing.JComboBox<>();
        jLabalnoti = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        onoffslider = new javax.swing.JSlider();
        onofflable = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Invoice");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Invoice");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 10, 80));

        jLabel6.setFont(new java.awt.Font("FrankRuehl", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Happy Selling..");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1280, 100));

        jList1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jList1.setForeground(new java.awt.Color(102, 102, 255));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jList1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, 490, 110));

        invIDLbl.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        invIDLbl.setForeground(new java.awt.Color(255, 51, 51));
        invIDLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(invIDLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 120, 100, 30));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Cashier :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 170, -1, 40));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Barcode or Search an item");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, -1, -1));

        barcodeTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        barcodeTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                barcodeTxtFocusLost(evt);
            }
        });
        barcodeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcodeTxtActionPerformed(evt);
            }
        });
        barcodeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                barcodeTxtKeyReleased(evt);
            }
        });
        jPanel1.add(barcodeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, 490, 30));

        itemidTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        itemidTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemidTxtFocusLost(evt);
            }
        });
        itemidTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemidTxtKeyReleased(evt);
            }
        });
        jPanel1.add(itemidTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 60, 30));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Qty");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, 40, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Item Name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, -1, -1));

        itemnameTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        itemnameTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemnameTxtFocusLost(evt);
            }
        });
        itemnameTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemnameTxtMouseClicked(evt);
            }
        });
        itemnameTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemnameTxtKeyReleased(evt);
            }
        });
        jPanel1.add(itemnameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 180, 30));

        qtyTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        qtyTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        qtyTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qtyTxtFocusLost(evt);
            }
        });
        qtyTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyTxtKeyReleased(evt);
            }
        });
        jPanel1.add(qtyTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, 50, 30));

        unitpriceTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        unitpriceTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        unitpriceTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                unitpriceTxtFocusLost(evt);
            }
        });
        jPanel1.add(unitpriceTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 320, 60, 30));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Unit Price");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, -1, -1));

        totTxt.setEditable(false);
        totTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        totTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel1.add(totTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 320, 70, 30));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Total");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 300, -1, -1));

        discountTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        discountTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel1.add(discountTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 320, 70, 30));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Discount");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 300, -1, -1));

        addBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addBtnMouseReleased(evt);
            }
        });
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 390, 140, 40));

        removeBtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        removeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                removeBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                removeBtnMouseReleased(evt);
            }
        });
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });
        removeBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                removeBtnKeyReleased(evt);
            }
        });
        jPanel1.add(removeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 390, 140, 40));

        tableTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tableTxt.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tableTxt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Qty", "Unit Price", "Total", "Discount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTxt.setDragEnabled(true);
        tableTxt.setGridColor(new java.awt.Color(255, 255, 255));
        tableTxt.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableTxt);
        if (tableTxt.getColumnModel().getColumnCount() > 0) {
            tableTxt.getColumnModel().getColumn(0).setMaxWidth(70);
            tableTxt.getColumnModel().getColumn(1).setMaxWidth(250);
            tableTxt.getColumnModel().getColumn(2).setMaxWidth(80);
            tableTxt.getColumnModel().getColumn(3).setMaxWidth(80);
            tableTxt.getColumnModel().getColumn(4).setMaxWidth(80);
            tableTxt.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 660, 600));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("hkjhkk");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, -1));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Total");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, 80, 40));

        subtotal.setEditable(false);
        subtotal.setForeground(new java.awt.Color(255, 102, 51));
        subtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        subtotal.setText("0.00");
        subtotal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        subtotal.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jPanel1.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 470, 110, 40));

        discountTotal.setEditable(false);
        discountTotal.setForeground(new java.awt.Color(255, 102, 51));
        discountTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        discountTotal.setText("0.00");
        discountTotal.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jPanel1.add(discountTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 470, 60, 40));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Net Amount");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 520, -1, 30));

        netTxt.setEditable(false);
        netTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        netTxt.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        netTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netTxtActionPerformed(evt);
            }
        });
        jPanel1.add(netTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, 110, -1));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Paid");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 520, -1, -1));

        paidTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        paidTxt.setText("0");
        paidTxt.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        paidTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                paidTxtFocusLost(evt);
            }
        });
        paidTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paidTxtKeyReleased(evt);
            }
        });
        jPanel1.add(paidTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 520, 110, -1));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Balance");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 550, -1, 30));

        balanceTxt.setEditable(false);
        balanceTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        balanceTxt.setText("0.00");
        balanceTxt.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jPanel1.add(balanceTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 560, 110, -1));

        checkoutBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        checkoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checkout.png"))); // NOI18N
        checkoutBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkoutBtnFocusLost(evt);
            }
        });
        checkoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkoutBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                checkoutBtnMouseReleased(evt);
            }
        });
        checkoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutBtnActionPerformed(evt);
            }
        });
        jPanel1.add(checkoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 680, 170, 50));

        noiTxt.setEditable(false);
        noiTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        noiTxt.setText("0");
        noiTxt.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jPanel1.add(noiTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 560, 110, -1));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("No of Items");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, -1, -1));

        starlbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        starlbl.setForeground(new java.awt.Color(204, 0, 0));
        starlbl.setText("*");
        jPanel1.add(starlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 560, 30, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo invoice.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, 350, 110));

        jPanel1.add(customerCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 170, 150, 30));

        jPanel1.add(cashierCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 170, 150, 30));

        jLabalnoti.setBackground(new java.awt.Color(255, 255, 255));
        jLabalnoti.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabalnoti.setForeground(new java.awt.Color(255, 102, 102));
        jLabalnoti.setOpaque(true);
        jPanel1.add(jLabalnoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 520, 20));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Item ID");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 60, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Customer :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, -1, 30));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Incoide ID :");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 120, -1, 30));

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
        jPanel1.add(onoffslider, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 630, 60, -1));

        onofflable.setFont(new java.awt.Font("Open Sans Semibold", 1, 14)); // NOI18N
        onofflable.setForeground(new java.awt.Color(102, 102, 102));
        onofflable.setText("on");
        jPanel1.add(onofflable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 630, -1, 30));

        jLabel31.setFont(new java.awt.Font("Open Sans Semibold", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Print Bill :");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 630, 70, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1280, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barcodeTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_barcodeTxtFocusLost

        //getfromitems();
        jLabalnoti.setText("");

    }//GEN-LAST:event_barcodeTxtFocusLost

    private void barcodeTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeTxtKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            getfromitems();
            jLabalnoti.setText("The barcode not found! You can search for an item instead");

        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jList1.requestFocus(true);
            jList1.setSelectedIndex(0);
            jList1.setSelectionMode(1);

        }
        listload();
    }//GEN-LAST:event_barcodeTxtKeyReleased

    private void itemidTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemidTxtKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            getfromid();
            jLabalnoti.setText("The Id not found!");

        }

    }//GEN-LAST:event_itemidTxtKeyReleased

    private void itemnameTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemnameTxtFocusLost

    }//GEN-LAST:event_itemnameTxtFocusLost

    private void itemnameTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemnameTxtMouseClicked

    }//GEN-LAST:event_itemnameTxtMouseClicked

    private void itemnameTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemnameTxtKeyReleased

    }//GEN-LAST:event_itemnameTxtKeyReleased

    private void qtyTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qtyTxtFocusLost
        setTotalValue();
        if (itemidTxt.getText().equals("")) {
            qtyTxt.setText("0.00");
        }
    }//GEN-LAST:event_qtyTxtFocusLost

    private void qtyTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyTxtKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addBtn.doClick();
            qtyTxt.setText("");
            barcodeTxt.requestFocus();

        }
    }//GEN-LAST:event_qtyTxtKeyReleased

    private void unitpriceTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_unitpriceTxtFocusLost
        setTotalValue();
    }//GEN-LAST:event_unitpriceTxtFocusLost

    private void barcodeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcodeTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeTxtActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        try {
            String id = itemidTxt.getText();
            String itmname = itemnameTxt.getText();
            double qty = Double.parseDouble(qtyTxt.getText());
            double uprice = Double.parseDouble(unitpriceTxt.getText());
            double discount = Double.parseDouble(discountTxt.getText());
            double total = qty * uprice;

            int i;
            boolean found = false;
            for (i = 0; i < tableTxt.getRowCount(); i++) {
                String idTbl = tableTxt.getValueAt(i, 0).toString();

                if (idTbl.equals(id)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                double oldQty = Double.parseDouble(tableTxt.getValueAt(i, 2).toString());
                double oldTotal = Double.parseDouble(tableTxt.getValueAt(i, 4).toString());

                double oldDiscount = Double.parseDouble(tableTxt.getValueAt(i, 5).toString());
                double newDiscount = oldDiscount + discount;

                double newQty = oldQty + qty;
                double newTotal = newQty * uprice;

                tableTxt.setValueAt(newQty, i, 2);
                tableTxt.setValueAt(uprice, i, 3);
                tableTxt.setValueAt(newTotal, i, 4);

                double subTotalOld = Double.parseDouble(subtotal.getText()) - oldTotal;
                double newSubTotal = subTotalOld + newTotal;
                subtotal.setText("" + newSubTotal);

                tableTxt.setValueAt(newDiscount, i, 5);
                double discountTotalOld = Double.parseDouble(discountTotal.getText()) - oldDiscount;
                double newTotDiscount = discountTotalOld + newDiscount;
                discountTotal.setText("" + newTotDiscount);

            } else {
                dtm.addRow(new Object[]{id, itmname, qty, uprice, total, discount});
                double subTotalOld = Double.parseDouble(subtotal.getText());
                double newSubTotal = subTotalOld + total;
                subtotal.setText("" + newSubTotal);

                double discountOld = Double.parseDouble(discountTotal.getText());
                double newdiscountTotal = discountOld + discount;
                discountTotal.setText("" + newdiscountTotal);

            }
            itemidTxt.setText("");
            itemnameTxt.setText("");

            unitpriceTxt.setText("");
            discountTxt.setText("0");
            totTxt.setText("");
            barcodeTxt.setText("");

            double netTotal = Double.parseDouble(subtotal.getText());
            double netDiscount = Double.parseDouble(discountTotal.getText());
            double netamount = netTotal - netDiscount;
            netTxt.setText("" + netamount);

            double oldtotalQty = Double.parseDouble(noiTxt.getText());
            double newtotalQty = oldtotalQty + qty;
            noiTxt.setText("" + newtotalQty);
            qtyTxt.setText("");

        } catch (NumberFormatException e) {
        }finally{
             System.gc();   
        }
        balance();

    }//GEN-LAST:event_addBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        int selectedRow = tableTxt.getSelectedRow();
        if (selectedRow >= 0) {
            double total = Double.parseDouble(tableTxt.getValueAt(selectedRow, 4).toString());
            double subTotalOld = Double.parseDouble(subtotal.getText());
            double subnewTotal = subTotalOld - total;
            subtotal.setText("" + subnewTotal);

            double discount = Double.parseDouble(tableTxt.getValueAt(selectedRow, 5).toString());
            double discountOld = Double.parseDouble(discountTotal.getText());
            double newdiscount = discountOld - discount;
            discountTotal.setText("" + newdiscount);

            double noItems = Double.parseDouble(tableTxt.getValueAt(selectedRow, 2).toString());
            double noItemsOld = Double.parseDouble(noiTxt.getText());
            double newnoItems = noItemsOld - noItems;
            noiTxt.setText("" + newnoItems);

            dtm.removeRow(selectedRow);
            double netTotal = Double.parseDouble(subtotal.getText());
            double netDiscount = Double.parseDouble(discountTotal.getText());
            double netamount = netTotal - netDiscount;
            netTxt.setText("" + netamount);

            balance();
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void removeBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_removeBtnKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_removeBtnKeyReleased

    private void paidTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paidTxtFocusLost
        balance();

    }//GEN-LAST:event_paidTxtFocusLost

    private void paidTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidTxtKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            balance();

        }
    }//GEN-LAST:event_paidTxtKeyReleased

    private void checkoutBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkoutBtnFocusLost
        dtm.setRowCount(0);
        subtotal.setValue(0.00);
        discountTotal.setValue(0.00);
        noiTxt.setValue(0.00);
        netTxt.setValue(0.00);
        paidTxt.setValue(0.00);
        balanceTxt.setValue(0.00);

    }//GEN-LAST:event_checkoutBtnFocusLost

    private void checkoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutBtnActionPerformed
        int id = 1;
        int id2 = 1;
        cusname = customerCombo.getSelectedItem().toString();
        cashiername = cashierCombo.getSelectedItem().toString();
        noi = noiTxt.getText();
        tot = subtotal.getText();
        dis = discountTotal.getText();
        netamnt = netTxt.getText();
        balance = balanceTxt.getText();
        paid = paidTxt.getText();
        date = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fdate = sdf.format(date);

        try {
            querym1 = "select id from customer where name='" + cusname + "'";
            rsm1 = con.getQuery(querym1);
            while (rsm1.next()) {
                id = rsm1.getInt("id");
            }
            querym2 = "select id from cashier where name='" + cashiername + "'";
            rsm2 = con.getQuery(querym2);
            while (rsm2.next()) {
                id = rsm2.getInt("id");
            }
            querym3 = "INSERT INTO invoice(date,total,discount,payment,balance,customerid,cashierid,netamount) VALUES("
                    + "'" + fdate + "',"
                    + "'" + tot + "',"
                    + "'" + dis + "',"
                    + "'" + paid + "',"
                    + "'" + balance + "',"
                    + "'" + id + "',"
                    + "'" + id2 + "',"
                    + "'" + netamnt + "'"
                    + ")";
            genID = con.setQuery(querym3, "");
            for (int i = 0; i < tableTxt.getRowCount(); i++) {
                int item_id = Integer.parseInt(tableTxt.getValueAt(i, 0).toString());
                String name = tableTxt.getValueAt(i, 1).toString();
                Double qty = Double.parseDouble(tableTxt.getValueAt(i, 2).toString());
                Double total = Double.parseDouble(tableTxt.getValueAt(i, 4).toString());
                String queryrem = "UPDATE item SET qty =qty-" + qty + "WHERE id=" + item_id;
                String query2 = "INSERT INTO invoice_has_item(invoice_id,item_id,name,qty,total) VALUES("
                        + "'" + genID + "',"
                        + "'" + item_id + "',"
                        + "'" + name + "',"
                        + "'" + qty + "',"
                        + "'" + total + "'"
                        + ")";
                int genID2 = con.setQuery(query2, "");
                con.setQuery(queryrem);
                getmaxid();
                barcodeTxt.requestFocus();
            }
            rsm1.close();
            rsm2.close();
            print_code();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            con=null;
            Runtime r = Runtime.getRuntime();
            r.gc();
            System.gc();  
        }

        
        
    }//GEN-LAST:event_checkoutBtnActionPerformed
    
    public void print_code(){
          try {        
            HashMap map=new HashMap();
            map.put("id",genID);
            ReportView re=new ReportView("C:\\Users\\Hasintha\\Documents\\NetBeansProjects\\POS\\Reports\\123456.jasper",map);
            re.setVisible(true);
            String report = "C:\\Users\\Hasintha\\Documents\\NetBeansProjects\\POS\\Reports\\123456.jasper";
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,map,new Connect().Conn());
            PrinterJob printerJob = PrinterJob.getPrinterJob();

            PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
            printerJob.defaultPage(pageFormat);

            int selectedService = 0;
            AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName("HP Deskjet 1510 series", null));
            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
            try {
                printerJob.setPrintService(printService[selectedService]);
                
            } catch (Exception e) {

                System.out.println(e);
            }
            JRPrintServiceExporter exporter;
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(MediaSizeName.INVOICE);
            printRequestAttributeSet.add(new Copies(1));
            //JasperPrintManager.printReport(report,false);
            // these are deprecated
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            
            exporter.exportReport();

        } catch (JRException e) {
            System.out.print(e);
        }finally{
             System.gc();   
        }
        
    }
    private void netTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_netTxtActionPerformed

    private void checkoutBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkoutBtnMouseReleased
        // TODO add your handling code here:
        checkoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checkout.png"))); // NOI18N
    }//GEN-LAST:event_checkoutBtnMouseReleased

    private void checkoutBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkoutBtnMousePressed
        checkoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checkout_clicked.png"))); // NOI18N        // TODO add your handling code here:
    }//GEN-LAST:event_checkoutBtnMousePressed

    private void addBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseReleased
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N        // TODO add your handling code here:
    }//GEN-LAST:event_addBtnMouseReleased

    private void addBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMousePressed
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_clicked.png"))); // NOI18N        // TODO add your handling code here:
    }//GEN-LAST:event_addBtnMousePressed

    private void removeBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeBtnMouseReleased
        // TODO add your handling code here:
        removeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
    }//GEN-LAST:event_removeBtnMouseReleased

    private void removeBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeBtnMousePressed
        // TODO add your handling code here:
        removeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove_clicked.png"))); // NOI18N
    }//GEN-LAST:event_removeBtnMousePressed

    private void itemidTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemidTxtFocusLost
        getfromid();
    }//GEN-LAST:event_itemidTxtFocusLost

    private void barcodeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeTxtKeyPressed

    }//GEN-LAST:event_barcodeTxtKeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked

        String tmp = (String) jList1.getSelectedValue();
        barcodeTxt.setText(tmp);
        try {

            String query = "SELECT * FROM item WHERE name='" + barcodeTxt.getText() + "'";

            ResultSet rset = con.getQuery(query);
            try {
                while (rset.next()) {
                    String itemid = rset.getString("id");
                    String itmname = rset.getString("name");
                    String uprice = rset.getString("retail_price");
                    if (evt.getClickCount() == 2) {
                        itemidTxt.setText(itemid);
                        itemnameTxt.setText(itmname);
                        unitpriceTxt.setText(uprice);
                        jLabalnoti.setText("");

                        qtyTxt.requestFocus();
                        qtyTxt.setText("");
                        jScrollPane3.setVisible(false);
                    }
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                qtyTxt.requestFocus();
            }finally{
             System.gc();   
        }
        } catch (Exception e2) {
            e2.printStackTrace();
        }finally{
             System.gc();   
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyReleased
        // TODO add your handling code here:
        String tmp = (String) jList1.getSelectedValue();
        barcodeTxt.setText(tmp);
        try {

            String query = "SELECT * FROM item WHERE name='" + barcodeTxt.getText() + "'";

            ResultSet rset = con.getQuery(query);
            try {
                while (rset.next()) {
                    String itemid = rset.getString("id");
                    String itmname = rset.getString("name");
                    String uprice = rset.getString("retail_price");
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        itemidTxt.setText(itemid);
                        itemnameTxt.setText(itmname);
                        unitpriceTxt.setText(uprice);
                        jLabalnoti.setText("");

                        qtyTxt.requestFocus();
                        qtyTxt.setText("");
                        jScrollPane3.setVisible(false);
                    }
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                qtyTxt.requestFocus();
            }finally{
             System.gc();   
        }
        } catch (Exception e2) {
            e2.printStackTrace();
        }finally{
             System.gc();   
        }

    }//GEN-LAST:event_jList1KeyReleased

    private void onoffsliderAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_onoffsliderAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_onoffsliderAncestorMoved

    private void onoffsliderAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_onoffsliderAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_onoffsliderAncestorAdded

    private void onoffsliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onoffsliderMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_onoffsliderMouseClicked

    private void onoffsliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onoffsliderMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_onoffsliderMousePressed

    private void onoffsliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onoffsliderMouseReleased
        // TODO add your handling code here:

       
    }//GEN-LAST:event_onoffsliderMouseReleased

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
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new invoice().setVisible(true);
            }
        });
       
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JFormattedTextField balanceTxt;
    private javax.swing.JTextField barcodeTxt;
    private javax.swing.JComboBox<String> cashierCombo;
    private javax.swing.JButton checkoutBtn;
    private javax.swing.JComboBox<String> customerCombo;
    private javax.swing.JFormattedTextField discountTotal;
    private javax.swing.JFormattedTextField discountTxt;
    private javax.swing.JLabel invIDLbl;
    private javax.swing.JTextField itemidTxt;
    private javax.swing.JTextField itemnameTxt;
    private javax.swing.JLabel jLabalnoti;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JFormattedTextField netTxt;
    private javax.swing.JFormattedTextField noiTxt;
    private javax.swing.JLabel onofflable;
    private javax.swing.JSlider onoffslider;
    private javax.swing.JFormattedTextField paidTxt;
    private javax.swing.JFormattedTextField qtyTxt;
    private javax.swing.JButton removeBtn;
    private javax.swing.JLabel starlbl;
    private javax.swing.JFormattedTextField subtotal;
    private javax.swing.JTable tableTxt;
    private javax.swing.JFormattedTextField totTxt;
    private javax.swing.JFormattedTextField unitpriceTxt;
    // End of variables declaration//GEN-END:variables
}
