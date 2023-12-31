package MVC;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// TODO: quitar final
final class View extends javax.swing.JFrame {

    public View() {
        initComponents();
        setVisible(true);
        setFocusable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resetButton = new javax.swing.JButton();
        resultField = new javax.swing.JTextField();
        divButton = new javax.swing.JButton();
        mulButton = new javax.swing.JButton();
        subButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        equalButton = new javax.swing.JButton();
        dotButton = new javax.swing.JButton();
        num1Button = new javax.swing.JButton();
        num2Button = new javax.swing.JButton();
        num3Button = new javax.swing.JButton();
        num4Button = new javax.swing.JButton();
        num5Button = new javax.swing.JButton();
        num6Button = new javax.swing.JButton();
        num7Button = new javax.swing.JButton();
        num8Button = new javax.swing.JButton();
        num9Button = new javax.swing.JButton();
        num0Button = new javax.swing.JButton();
        binButton = new javax.swing.JButton();
        primoButton = new javax.swing.JButton();
        dataButton = new javax.swing.JButton();
        avgButton = new javax.swing.JButton();
        memButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocationByPlatform(true);
        setResizable(false);

        resetButton.setText("C");
        resetButton.setFocusable(false);

        resultField.setEditable(false);
        resultField.setBackground(new java.awt.Color(255, 255, 255));
        resultField.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        resultField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        resultField.setFocusable(false);

        divButton.setText("/");
        divButton.setFocusable(false);

        mulButton.setText("*");
        mulButton.setFocusable(false);

        subButton.setText("-");
        subButton.setFocusable(false);

        addButton.setText("+");
        addButton.setFocusable(false);

        equalButton.setText("=");
        equalButton.setFocusable(false);

        dotButton.setText(".");
        dotButton.setFocusable(false);

        num1Button.setText("1");
        num1Button.setFocusable(false);
        num1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                num1ButtonActionPerformed(evt);
            }
        });

        num2Button.setText("2");
        num2Button.setFocusable(false);

        num3Button.setText("3");
        num3Button.setFocusable(false);

        num4Button.setText("4");
        num4Button.setFocusable(false);

        num5Button.setText("5");
        num5Button.setFocusable(false);

        num6Button.setText("6");
        num6Button.setFocusable(false);

        num7Button.setText("7");
        num7Button.setFocusable(false);

        num8Button.setText("8");
        num8Button.setFocusable(false);

        num9Button.setText("9");
        num9Button.setFocusable(false);

        num0Button.setText("0");
        num0Button.setFocusable(false);

        binButton.setText("Binario");

        primoButton.setText("Primo");
        primoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primoButtonActionPerformed(evt);
            }
        });

        dataButton.setText("Data");

        avgButton.setText("Avg");
        avgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avgButtonActionPerformed(evt);
            }
        });

        memButton.setText("M+");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultField)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(num0Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(num1Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(num2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(num3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(equalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(primoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(num7Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(num4Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(binButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(memButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(num8Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(num5Button, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(num9Button, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(divButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(avgButton))
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(num6Button, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mulButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(resultField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(binButton, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(primoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dataButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avgButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(divButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mulButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num7Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num8Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num9Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num5Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num6Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(num1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(num0Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(equalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void avgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avgButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_avgButtonActionPerformed

    private void primoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_primoButtonActionPerformed

    private void num1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_num1ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_num1ButtonActionPerformed

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JTextField getResultField() {
        return resultField;
    }

    public JButton getDivButton() {
        return divButton;
    }

    public JButton getDotButton() {
        return dotButton;
    }

    public JButton getEqualButton() {
        return equalButton;
    }

    public JButton getMulButton() {
        return mulButton;
    }

    public JButton getNum0Button() {
        return num0Button;
    }

    public JButton getNum1Button() {
        return num1Button;
    }

    public JButton getNum2Button() {
        return num2Button;
    }

    public JButton getNum3Button() {
        return num3Button;
    }

    public JButton getNum4Button() {
        return num4Button;
    }
    
    public JButton getNum5Button() {
        return num5Button;
    }

    public JButton getNum6Button() {
        return num6Button;
    }

    public JButton getNum7Button() {
        return num7Button;
    }

    public JButton getNum8Button() {
        return num8Button;
    }

    public JButton getNum9Button() {
        return num9Button;
    }
 
    public JButton getSubButton() {
        return subButton;
    }
    
    public JButton getPrimoButton() {
        return primoButton;
    }

    public JButton getAvgButton() {
        return avgButton;
    }
    
    public JButton getBinButton() {
        return binButton;
    }
    
    public JButton getMemButton() {
        return memButton;
    }
    
    public JButton getDataButton() {
        return dataButton;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton avgButton;
    private javax.swing.JButton binButton;
    private javax.swing.JButton dataButton;
    private javax.swing.JButton divButton;
    private javax.swing.JButton dotButton;
    private javax.swing.JButton equalButton;
    private javax.swing.JButton memButton;
    private javax.swing.JButton mulButton;
    private javax.swing.JButton num0Button;
    private javax.swing.JButton num1Button;
    private javax.swing.JButton num2Button;
    private javax.swing.JButton num3Button;
    private javax.swing.JButton num4Button;
    private javax.swing.JButton num5Button;
    private javax.swing.JButton num6Button;
    private javax.swing.JButton num7Button;
    private javax.swing.JButton num8Button;
    private javax.swing.JButton num9Button;
    private javax.swing.JButton primoButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField resultField;
    private javax.swing.JButton subButton;
    // End of variables declaration//GEN-END:variables
}