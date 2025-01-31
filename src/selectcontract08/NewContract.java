package selectcontract08;

import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static selectcontract08.ContractModel.INDEX_OF_CONTRACT_ID;
import static selectcontract08.ContractModel.NUMBER_OF_CONTRACT_ATTRIBUTES;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
/**
 *
 * @author Jianping
 */
public class NewContract extends javax.swing.JDialog {

    /**
     * Creates new form NewContract
     */
    private ContractController controller;
    //create a instance of controller, so that we can linked into controller, and update the UI

    public NewContract(JFrame parent, boolean modal, ContractModel model, ContractController contractController) {
        super(parent, modal);
        this.controller = contractController; // Store the controller reference
        initComponents();
        setNewOriginCityList(model.getNewOriginCityList());
        setNewDestinationList(model.getNewDestinationList());

//        key step to show content in the combo box
//        setNewDestinationList(model.getDestinationCityList());
//        addcomboBoxListenerForJComboBoxNewOriginCity(new ItemListener() {
//        @Override
//        public void itemStateChanged(ItemEvent e) {
//            // Check if origin and destination are the same
//            if (jComboBoxNewDestination.getSelectedItem() != null &&
//                e.getItem().toString().equals(jComboBoxNewDestination.getSelectedItem().toString())) {
//                JOptionPane.showMessageDialog(null, "Origin and destination cannot be the same.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
//                // Reset the origin combo box
//                jComboBoxNewOriginCity.setSelectedIndex(-1);
//            }
//        }
//    });
//        
//        addcomboBoxListenerForJComboBoxNewDestination(new ItemListener() {
//        @Override
//        public void itemStateChanged(ItemEvent e) {
//            // Check if origin and destination are the same
//            if (jComboBoxNewOriginCity.getSelectedItem() != null &&
//                e.getItem().toString().equals(jComboBoxNewOriginCity.getSelectedItem().toString())) {
//                JOptionPane.showMessageDialog(null, "Destination and origin cannot be the same.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
//                // Reset the destination combo box
//                jComboBoxNewDestination.setSelectedIndex(-1);
//            }
//        }
//    });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    void addcomboBoxListenerForJComboBoxNewOriginCity(ItemListener listenForComboBox) {
        jComboBoxNewOriginCity.addItemListener(listenForComboBox);
    }
    //add itemlistenner to the combo box

    void addcomboBoxListenerForJComboBoxNewDestination(ItemListener listenForComboBox) {
        jComboBoxNewDestination.addItemListener(listenForComboBox);
    }
    //add itemlistenner to the combo box

    public void setNewOriginCityList(String[] cityList) {
        final DefaultComboBoxModel model = new DefaultComboBoxModel(cityList);
        this.jComboBoxNewOriginCity.setModel(model);
    }

    public void setNewDestinationList(String[] cityList) {
        final DefaultComboBoxModel model = new DefaultComboBoxModel(cityList);
        this.jComboBoxNewDestination.setModel(model);

    }

    private void setUpDisplay() {
        jTextNewContractID.setText("");
        jTextNewOrder.setText("");
        jComboBoxNewDestination.setSelectedIndex(0);
        jComboBoxNewOriginCity.setSelectedIndex(0);
    }

    private boolean isContractIdDuplicate() throws ParserConfigurationException, SAXException {

        ArrayList<String> contrctIDList = new ArrayList<>();
        boolean isDuplicate = false;

        try {

//            FileReader fileReader = new FileReader("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract08\\src\\selectcontract08\\contracts.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line;
            File inputFile = new File("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract08\\src\\selectcontract08\\contract.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("contract");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String contractID = eElement.getElementsByTagName("contractID").item(0).getTextContent();
                    String originCity = eElement.getElementsByTagName("originCity").item(0).getTextContent();
                    String destCity = eElement.getElementsByTagName("destCity").item(0).getTextContent();
                    String orderItem = eElement.getElementsByTagName("orderItem").item(0).getTextContent();

                    contrctIDList.add(contractID);
                }
            }

//            while ((line = bufferedReader.readLine()) != null) {
//                String[] tokens = line.split(",", NUMBER_OF_CONTRACT_ATTRIBUTES);
//
//                String contractID = tokens[INDEX_OF_CONTRACT_ID];
//
//                contrctIDList.add(contractID);
//            }
//            fileReader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        for (String x : contrctIDList) {
            if (x.equals(jTextNewContractID.getText().toUpperCase())) {
                isDuplicate = true;
            }
        }

        return isDuplicate;
    }
    //a method read content in the contracts.txt, then conpare everything with newContractID to see if it's a duplicate.

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextNewContractID = new javax.swing.JTextField();
        jButtonFinish = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextNewOrder = new javax.swing.JTextField();
        jButtonCancel = new javax.swing.JButton();
        jComboBoxNewOriginCity = new javax.swing.JComboBox<>();
        jComboBoxNewDestination = new javax.swing.JComboBox<>();
        jButtonReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("New Contract ID");

        jTextNewContractID.setToolTipText("please enter your contractID with the format of a single digit 1 to 9 and followed by 3 letters(upper or lower), the letters will be converted into uppercases eventually");
        jTextNewContractID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNewContractIDActionPerformed(evt);
            }
        });

        jButtonFinish.setText("Finish");
        jButtonFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinishActionPerformed(evt);
            }
        });

        jLabel2.setText("New Origin city");

        jLabel3.setText("New Destination");

        jLabel4.setText("New Order");

        jButtonCancel.setText("Cancel");
        jButtonCancel.setToolTipText("");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jComboBoxNewOriginCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxNewDestination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFinish)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextNewContractID, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNewOriginCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNewDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCancel)
                        .addGap(29, 29, 29)
                        .addComponent(jButtonReset)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextNewContractID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxNewOriginCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxNewDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFinish)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonReset))
                .addGap(101, 101, 101))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNewContractIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNewContractIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNewContractIDActionPerformed

    private void jButtonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinishActionPerformed
        System.out.println(jComboBoxNewDestination.getSelectedItem());
        System.out.println(jComboBoxNewOriginCity.getSelectedItem());
        Pattern patternNewContractID = compile("^[1-9][A-Za-z][A-Za-z][A-Za-z]$");
        Matcher matcherNewContractID = patternNewContractID.matcher(jTextNewContractID.getText());
        boolean verificationForNewContractID = matcherNewContractID.matches();
        Pattern patternNewOrder = compile("^[a-zA-Z]+(?: [a-zA-Z]+)*$");
        //^(?=.*[a-zA-Z])[a-zA-Z ]+$
        Matcher matcherNewOrder = patternNewOrder.matcher(jTextNewOrder.getText());
        boolean verificationForNewOrder = matcherNewOrder.matches();
        JOptionPane newMessages;
        newMessages = new JOptionPane();

        if (!verificationForNewContractID) {
            newMessages.showMessageDialog(null, "Please enter the correct contract ID, it should be one non-zero digit with 3 letters");
        } else if (verificationForNewContractID && jComboBoxNewDestination.getSelectedItem() == jComboBoxNewOriginCity.getSelectedItem()) {
            newMessages.showMessageDialog(null, "original city and destination can't be the same!");
        } else if (verificationForNewContractID && jComboBoxNewDestination.getSelectedItem() != jComboBoxNewOriginCity.getSelectedItem() && !verificationForNewOrder) {
            newMessages.showMessageDialog(null, "The order item is not valid. It must be text and cannot be exclusively numbers or contain commas.");
        } else try {
            if (isContractIdDuplicate()) {
                newMessages.showMessageDialog(null, "Duplicate Contaract exits, please use another ID");
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract08\\src\\selectcontract08\\contracts.txt", true));
                    writer.write(jTextNewContractID.getText().toUpperCase() + ",");
                    writer.write(jComboBoxNewOriginCity.getSelectedItem() + ", ");
                    writer.write(jComboBoxNewDestination.getSelectedItem() + ", ");
                    writer.write(jTextNewOrder.getText() + "\n");

                    try {
                        File inputFile = new File("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract08\\src\\selectcontract08\\contract.xml");
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Element rootElement;
                        Document doc;

                        // root element
                        if (inputFile.exists()) {
                            doc = dBuilder.parse(inputFile);
                            rootElement = (Element) doc.getFirstChild();
                        } else {
                            doc = dBuilder.newDocument();
                            rootElement = doc.createElement("contracts");
                            doc.appendChild(rootElement);
                        }

                        // contract element
                        Element contract = doc.createElement("contract");
                        rootElement.appendChild(contract);

                        // setting attribute to element
                        // contract.setAttribute("id", "1");
                        // contractID element
                        Element contractID = doc.createElement("contractID");
                        contractID.appendChild(doc.createTextNode(jTextNewContractID.getText().toUpperCase()));
                        contract.appendChild(contractID);

                        // originCity element
                        Element originCity = doc.createElement("originCity");
                        originCity.appendChild(doc.createTextNode(jComboBoxNewOriginCity.getSelectedItem().toString()));
                        contract.appendChild(originCity);

                        // destCity element
                        Element destCity = doc.createElement("destCity");
                        destCity.appendChild(doc.createTextNode(jComboBoxNewDestination.getSelectedItem().toString()));
                        contract.appendChild(destCity);

                        // orderItem element
                        Element orderItem = doc.createElement("orderItem");
                        orderItem.appendChild(doc.createTextNode(jTextNewOrder.getText()));
                        contract.appendChild(orderItem);

                        // write the content into xml file
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();

                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract08\\src\\selectcontract08\\contract.xml"));

// Output to console for testing
// StreamResult consoleResult = new StreamResult(System.out);
                        transformer.transform(source, result);
// transformer.transform(source, consoleResult);

                        System.out.println("File saved!");
                        

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//            writer.write(currentDate);
                    writer.close();
                    newMessages.showMessageDialog(null, "New Contract added");
                    setUpDisplay();
                    controller.refreshMainInterface();
                    setUpDisplay();
                } catch (IOException ex) {
                    Logger.getLogger(ConfirmBid.class.getName()).log(Level.SEVERE, null, ex);
                    setUpDisplay();
                }

            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(NewContract.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(NewContract.class.getName()).log(Level.SEVERE, null, ex);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonFinishActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        dispose();
        // dispose means when user clicking the cancel button, it will close the the dialog.
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        setUpDisplay();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonResetActionPerformed

    /**
     * @param args the command line arguments //
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NewContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NewContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NewContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NewContract.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                NewContract dialog = new NewContract(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonFinish;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JComboBox<String> jComboBoxNewDestination;
    private javax.swing.JComboBox<String> jComboBoxNewOriginCity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextNewContractID;
    private javax.swing.JTextField jTextNewOrder;
    // End of variables declaration//GEN-END:variables
}
