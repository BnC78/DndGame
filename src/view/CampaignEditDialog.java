package view;

import campaign.Campaign;
import character.DndCharacter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Represents a dialog window for the class <code>Campaign</code>.
 */
public class CampaignEditDialog extends javax.swing.JDialog {

    private boolean ok;
    private DefaultListModel<DndCharacter> inListModel;
    private DefaultListModel<DndCharacter> outListModel;
    private Campaign campaign;
    
    /**
     * Creates new form CampaignEditDialog
     * @param parent parent frame of this dialog
     * @param optCampaign campaign to be modified (empty if new)
     */
    public CampaignEditDialog(java.awt.Frame parent, Optional<Campaign> optCampaign) {
        super(parent, true);
        initComponents();
        ok = false;
        inListModel = new DefaultListModel<>();
        outListModel = new DefaultListModel<>();
        List<DndCharacter> outList = CharacterPanel.getAllCharacters();
        if (optCampaign.isPresent()) {
            campaign = optCampaign.get();
            tfName.setText(campaign.getName());
            tfDungeonMaster.setText(campaign.getDungeonMaster());
            tfWorld.setText(campaign.getWorld());
            List<DndCharacter> charactersInCampaign = new ArrayList<>(campaign.getCharacters());
            outList.removeAll(charactersInCampaign);
            inListModel.addAll(charactersInCampaign);
            taBackgroundStory.setText(campaign.getBackgroundStory());
            setTitle("Kampány módosítása");
        }
        outListModel.addAll(outList);
        listIn.setModel(inListModel);
        listOut.setModel(outListModel);
    }
    
    /**
     * Returns <code>true</code> if the user closed the window by clicking on the <i>Ok</i> button.
     * Else it returns <code>false</code>
     * @return if window was closed by clicking the Ok button.
     */
    public boolean isOk() {
        return this.ok;
    }
    
    /**
     * If the window was closed by clicking the <i>Ok</i> button, it returns the campaign based on the input fields of this window.
     * Else it returns an empty <code>Optional</code> 
     * @return an optional campaign
     */
    public Optional<Campaign> getCampaign() {
        Optional<Campaign> optCampaign = Optional.empty();
        if (this.isOk()) {
            Campaign camp = new Campaign(tfName.getText(), tfDungeonMaster.getText(), tfWorld.getText(), taBackgroundStory.getText());
            if (campaign != null) {
                camp.setId(campaign.getId());
            }
            for (int i = 0; i < inListModel.getSize(); ++i) {
                camp.addCharacter(inListModel.elementAt(i));
            }
            optCampaign = Optional.of(camp);
        }
        return optCampaign;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lbDungeonMaster = new javax.swing.JLabel();
        tfDungeonMaster = new javax.swing.JTextField();
        lbWorld = new javax.swing.JLabel();
        tfWorld = new javax.swing.JTextField();
        lbCharacters = new javax.swing.JLabel();
        lblIn = new javax.swing.JLabel();
        lbOut = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listIn = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOut = new javax.swing.JList<>();
        lbBackgroundStory = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taBackgroundStory = new javax.swing.JTextArea();
        btnCancel = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnAddCharacter = new javax.swing.JButton();
        btnRemoveCharacter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kampány létrehozása");

        lbName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbName.setText("Név:");

        tfName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNameKeyTyped(evt);
            }
        });

        lbDungeonMaster.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbDungeonMaster.setText("DM:");

        tfDungeonMaster.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfDungeonMaster.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDungeonMasterKeyTyped(evt);
            }
        });

        lbWorld.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbWorld.setText("Világ neve:");

        tfWorld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfWorld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfWorldKeyTyped(evt);
            }
        });

        lbCharacters.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbCharacters.setText("Karakterek:");

        lblIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblIn.setText("Résztvevő");

        lbOut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbOut.setText("További");

        listIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(listIn);

        listOut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(listOut);

        lbBackgroundStory.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbBackgroundStory.setText("Háttértörténet:");

        taBackgroundStory.setColumns(20);
        taBackgroundStory.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        taBackgroundStory.setLineWrap(true);
        taBackgroundStory.setRows(5);
        taBackgroundStory.setWrapStyleWord(true);
        taBackgroundStory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taBackgroundStoryKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(taBackgroundStory);

        btnCancel.setText("Mégsem");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnAddCharacter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAddCharacter.setText("Hozzáad");
        btnAddCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCharacterActionPerformed(evt);
            }
        });

        btnRemoveCharacter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRemoveCharacter.setText("Eltávolít");
        btnRemoveCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveCharacterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lbWorld)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfWorld))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbName)
                                .addComponent(lbDungeonMaster))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfDungeonMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbCharacters)
                        .addGap(51, 51, 51)
                        .addComponent(lblIn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddCharacter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveCharacter, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbOut)
                        .addGap(88, 88, 88))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbBackgroundStory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDungeonMaster, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tfDungeonMaster))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbWorld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfWorld))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCharacters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbOut))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnAddCharacter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveCharacter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbBackgroundStory, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This function is called when the user clicks on the <i>Cancel</i> button.
     * It hides the window.
     * @param evt 
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        ok = false;
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * This function is called when the user clicks on the <i>Ok</i> button.
     * If the input fields (name, DM and world) are empty, it will show an error.
     * Else it hides the window.
     * @param evt 
     */
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        String error = "";
        if (tfName.getText().isBlank()) {
            error += "Név megadása kötelező!\n";
        }
        if (tfDungeonMaster.getText().isBlank()) {
            error += "DM megadása kötelező!\n";
        }
        if (tfWorld.getText().isBlank()) {
            error += "Világ nevének megadása kötelező!\n";
        }
        if (!error.isBlank()) {
            JOptionPane.showMessageDialog(this, error, getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        ok = true;
        setVisible(false);
    }//GEN-LAST:event_btnOkActionPerformed

    /**
     * This function is called when the user click on the <i>Hozzáad</i> button.
     * If a character is selected in the <code>listOut</code> list, it removes it from <code>outListModel</code> and adds it to <code>inListModel</code>.
     * Else it does nothing.
     * @param evt 
     */
    private void btnAddCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCharacterActionPerformed
        if (listOut.getSelectedValue() == null) {
            return;
        }
        DndCharacter character = outListModel.remove(listOut.getSelectedIndex());
        inListModel.addElement(character);
    }//GEN-LAST:event_btnAddCharacterActionPerformed

    /**
     * This function is called when the user click on the <i>Eltávolít</i> button.
     * If a character is selected in the <code>listIn</code> list, it removes it from <code>inListModel</code> and adds it to <code>outListModel</code>.
     * Else it does nothing.
     * @param evt 
     */
    private void btnRemoveCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveCharacterActionPerformed
        if (listIn.getSelectedValue() == null) {
            return;
        }
        DndCharacter character = inListModel.remove(listIn.getSelectedIndex());
        outListModel.addElement(character);
    }//GEN-LAST:event_btnRemoveCharacterActionPerformed

    /**
     * This function is called when the user types in the <code>tfName</code> text field.
     * It doesn't let the user type in more than 45 characters in the text field.
     * @param evt 
     */
    private void tfNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNameKeyTyped
        if (tfName.getText().length() > 45) {
            evt.consume();
        }
    }//GEN-LAST:event_tfNameKeyTyped

    /**
     * This function is called when the user types in the <code>tfDungeonMaster</code> text field.
     * It doesn't let the user type in more than 45 characters in the text field.
     * @param evt 
     */
    private void tfDungeonMasterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDungeonMasterKeyTyped
        if (tfDungeonMaster.getText().length() > 45) {
            evt.consume();
        }
    }//GEN-LAST:event_tfDungeonMasterKeyTyped

    /**
     * This function is called when the user types in the <code>tfWorld</code> text field.
     * It doesn't let the user type in more than 45 characters in the text field.
     * @param evt 
     */
    private void tfWorldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfWorldKeyTyped
        if (tfWorld.getText().length() > 45) {
            evt.consume();
        }
    }//GEN-LAST:event_tfWorldKeyTyped

    /**
     * This function is called when the user types in the <code>tfBackgroundStory</code> text field.
     * It doesn't let the user type in more than 300 characters in the text field.
     * @param evt 
     */
    private void taBackgroundStoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taBackgroundStoryKeyTyped
        if (taBackgroundStory.getText().length() > 300) {
            evt.consume();
        }
    }//GEN-LAST:event_taBackgroundStoryKeyTyped

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CampaignEditDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CampaignEditDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CampaignEditDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CampaignEditDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CampaignEditDialog dialog = new CampaignEditDialog(new javax.swing.JFrame(), Optional.empty());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCharacter;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnRemoveCharacter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbBackgroundStory;
    private javax.swing.JLabel lbCharacters;
    private javax.swing.JLabel lbDungeonMaster;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbOut;
    private javax.swing.JLabel lbWorld;
    private javax.swing.JLabel lblIn;
    private javax.swing.JList<DndCharacter> listIn;
    private javax.swing.JList<DndCharacter> listOut;
    private javax.swing.JTextArea taBackgroundStory;
    private javax.swing.JTextField tfDungeonMaster;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfWorld;
    // End of variables declaration//GEN-END:variables
}
