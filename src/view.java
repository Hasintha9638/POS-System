/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chethiya Dissanayake
 */
public class view extends javax.swing.JFrame {

    /**
     * Creates new form view
     */
    public view() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public void getid(int id, String name, String cat, String sup, String sto, String loc, double Qty, double bprice, double rprice, double wprice, String date){
        int i = id;
        idTxt.setText(i+"");
        this.name.append(name);
        catLbl.setText(cat);
        supLbl.setText(sup);
        locLbl.setText(loc);
        stockLbl.setText(sto);
        qtyLbl.setText(""+Qty);
        this.bprice.setText(""+bprice);
        this.rprice.setText(""+rprice);
        this.wprice.setText(""+wprice);
        expLbl.setText(date);
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        catLbl = new javax.swing.JLabel();
        supLbl = new javax.swing.JLabel();
        stockLbl = new javax.swing.JLabel();
        locLbl = new javax.swing.JLabel();
        qtyLbl = new javax.swing.JLabel();
        expLbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        wprice = new javax.swing.JFormattedTextField();
        bprice = new javax.swing.JFormattedTextField();
        rprice = new javax.swing.JFormattedTextField();
        nameTxt = new javax.swing.JScrollPane();
        name = new javax.swing.JTextArea();
        idTxt = new javax.swing.JTextField();
        srcBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Open Sans Semibold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Item Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Inventory_backgroundq.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 60));

        jLabel3.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("ID ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Category");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Supplier");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Stock");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel8.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Location");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabel9.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Quantity");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabel10.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Buying Price");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Retail Price");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 80, -1));

        jLabel12.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Wholesale Price");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

        jLabel13.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Exp. Date");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        catLbl.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        catLbl.setForeground(new java.awt.Color(22, 160, 134));
        catLbl.setText("jLabel16");
        jPanel1.add(catLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 180, 20));

        supLbl.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        supLbl.setForeground(new java.awt.Color(22, 160, 134));
        supLbl.setText("jLabel17");
        jPanel1.add(supLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 180, 20));

        stockLbl.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        stockLbl.setForeground(new java.awt.Color(22, 160, 134));
        stockLbl.setText("jLabel18");
        jPanel1.add(stockLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 180, 20));

        locLbl.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        locLbl.setForeground(new java.awt.Color(22, 160, 134));
        locLbl.setText("jLabel19");
        jPanel1.add(locLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 180, 20));

        qtyLbl.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        qtyLbl.setForeground(new java.awt.Color(22, 160, 134));
        qtyLbl.setText("jLabel20");
        jPanel1.add(qtyLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 180, 20));

        expLbl.setFont(new java.awt.Font("Open Sans Semibold", 1, 13)); // NOI18N
        expLbl.setForeground(new java.awt.Color(22, 160, 134));
        expLbl.setText("jLabel21");
        jPanel1.add(expLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 180, 20));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 10, 320));

        wprice.setEditable(false);
        wprice.setForeground(new java.awt.Color(22, 160, 134));
        wprice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        wprice.setText("0");
        wprice.setFont(new java.awt.Font("Open Sans Extrabold", 0, 13)); // NOI18N
        jPanel1.add(wprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 90, 30));

        bprice.setEditable(false);
        bprice.setForeground(new java.awt.Color(22, 160, 134));
        bprice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        bprice.setText("0");
        bprice.setFont(new java.awt.Font("Open Sans Extrabold", 0, 13)); // NOI18N
        jPanel1.add(bprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 90, 30));

        rprice.setEditable(false);
        rprice.setForeground(new java.awt.Color(22, 160, 134));
        rprice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        rprice.setText("0");
        rprice.setFont(new java.awt.Font("Open Sans Extrabold", 0, 13)); // NOI18N
        jPanel1.add(rprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 90, 30));

        nameTxt.setForeground(new java.awt.Color(51, 51, 51));
        nameTxt.setOpaque(false);

        name.setEditable(false);
        name.setColumns(20);
        name.setFont(new java.awt.Font("Open Sans Semibold", 0, 13)); // NOI18N
        name.setForeground(new java.awt.Color(22, 160, 134));
        name.setRows(1);
        nameTxt.setViewportView(name);

        jPanel1.add(nameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 170, 40));

        idTxt.setBackground(new java.awt.Color(255, 255, 255));
        idTxt.setFont(new java.awt.Font("Open Sans Semibold", 0, 13)); // NOI18N
        idTxt.setForeground(new java.awt.Color(22, 160, 134));
        jPanel1.add(idTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 80, 30));

        srcBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/searchbtn.png"))); // NOI18N
        jPanel1.add(srcBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 50, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_btn.png"))); // NOI18N
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 392, 90, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon("D:\\Projects\\POS\\no-image.png")); // NOI18N
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 290, 150));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_btn.png"))); // NOI18N
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_btn_clicked.png"))); // NOI18N
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
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField bprice;
    private javax.swing.JLabel catLbl;
    private javax.swing.JLabel expLbl;
    private javax.swing.JTextField idTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel locLbl;
    private javax.swing.JTextArea name;
    private javax.swing.JScrollPane nameTxt;
    private javax.swing.JLabel qtyLbl;
    private javax.swing.JFormattedTextField rprice;
    private javax.swing.JButton srcBtn;
    private javax.swing.JLabel stockLbl;
    private javax.swing.JLabel supLbl;
    private javax.swing.JFormattedTextField wprice;
    // End of variables declaration//GEN-END:variables
}
