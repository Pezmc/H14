package ibms;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben
 */
public class Interface extends javax.swing.JFrame {

    /** Creates new form Interface */
    public Interface() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jFrame1 = new javax.swing.JFrame();
    generate = new javax.swing.JButton();
    requestholiday = new javax.swing.JButton();
    view = new javax.swing.JButton();
    edit = new javax.swing.JButton();
    greeting = new javax.swing.JLabel();
    driverList = new javax.swing.JComboBox();
    textArea1 = new java.awt.TextArea();

    org.jdesktop.layout.GroupLayout jFrame1Layout = new org.jdesktop.layout.GroupLayout(jFrame1.getContentPane());
    jFrame1.getContentPane().setLayout(jFrame1Layout);
    jFrame1Layout.setHorizontalGroup(
      jFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 400, Short.MAX_VALUE)
    );
    jFrame1Layout.setVerticalGroup(
      jFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 300, Short.MAX_VALUE)
    );

    setMinimumSize(new java.awt.Dimension(717, 262));

    generate.setLabel("Generate Roster");
    generate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        generateActionPerformed(evt);
      }
    });

    requestholiday.setLabel("Request Holiday");

    view.setLabel("View Roster");
    view.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        viewActionPerformed(evt);
      }
    });

    edit.setLabel("Edit Roster");

    greeting.setText("Welcome!");

    driverList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Driver list" }));
    driverList.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        driverListActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(requestholiday, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
          .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(edit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
          .add(layout.createSequentialGroup()
            .add(62, 62, 62)
            .add(greeting))
          .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(generate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
          .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(view, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
          .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(driverList, 0, 161, Short.MAX_VALUE)))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(textArea1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 524, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(20, 20, 20)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(textArea1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 222, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(layout.createSequentialGroup()
            .add(greeting)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(generate)
            .add(14, 14, 14)
            .add(driverList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(view)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(edit)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(requestholiday)))
        .add(20, 20, 20))
    );
  }// </editor-fold>//GEN-END:initComponents

    private HashMap<Integer, HashMap<Integer, HashMap<Integer, Driver>>> driverTimes;
    private HashMap<Integer, HashMap<Integer, HashMap<Integer, Bus>>> busTimes;
    private static String[] driverNames = {""};
    int selection = 0;
    Roster myRoster;
    String message = "";

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
        try {
            // TODO add your handling code here:
            myRoster = new Roster();
            textArea1.setText("Roster is currently being generated...");

            myRoster = RosterGenerator.generateRoster();
            message = "" + myRoster;

            textArea1.setText("Roster has been generated...");

            driverTimes = myRoster.getDriverTimes();
            busTimes = myRoster.getBusTimes();

        }  catch (Exception ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_generateActionPerformed

    private void driverListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driverListActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_driverListActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        // TODO add your handling code here:
        textArea1.setText(message);
        textArea1.setCaretPosition(0);
    }//GEN-LAST:event_viewActionPerformed


    public static void main(String args[]) {

        new Interface().setVisible(true);
        
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox driverList;
  private javax.swing.JButton edit;
  private javax.swing.JButton generate;
  private javax.swing.JLabel greeting;
  private javax.swing.JFrame jFrame1;
  private javax.swing.JButton requestholiday;
  private java.awt.TextArea textArea1;
  private javax.swing.JButton view;
  // End of variables declaration//GEN-END:variables

}
