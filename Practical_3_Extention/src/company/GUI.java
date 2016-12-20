package company;

import javax.swing.*;
import java.io.File;


class GUI extends javax.swing.JFrame {


    GUI() {
        initComponents();
        chooser = new JFileChooser();

    }


    private void initComponents() {
        JScrollPane jScrollPane1 = new JScrollPane();
        jTextAreaDefinition = new javax.swing.JTextArea();
        JButton jButtonGetDefinitionFile = new JButton();
        jTextFieldDefinition = new javax.swing.JTextField();
        JButton jButtonGetInputFile = new JButton();
        jTextFieldInputFile = new javax.swing.JTextField();
        JButton jButtonCheck = new JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaDefinition.setColumns(20);
        jTextAreaDefinition.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDefinition);

        jButtonGetDefinitionFile.setText("Select Definition File");
        jButtonGetDefinitionFile.addActionListener(this::jButtonGetDefinitionFileActionPerformed);

        jButtonGetInputFile.setText("Select Input File");
        jButtonGetInputFile.addActionListener(this::jButtonGetInputFileActionPerformed);

        jButtonCheck.setText("Check");
        jButtonCheck.addActionListener(this::jButtonCheckActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButtonCheck)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jButtonGetInputFile, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButtonGetDefinitionFile)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(jTextFieldInputFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                                                .addComponent(jTextFieldDefinition, javax.swing.GroupLayout.Alignment.LEADING)))
                                                .addGap(30, 30, 30)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextFieldDefinition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonGetDefinitionFile)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldInputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonGetInputFile))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCheck)
                                .addContainerGap())
        );
        pack();
    }

    private void jButtonGetDefinitionFileActionPerformed(java.awt.event.ActionEvent evt) {
        definitionFiles = createFileChooserDefinitions("Select Input File");
        if (definitionFiles != null) {
            if(definitionFiles.length ==1)
                jTextFieldDefinition.setText(definitionFiles[0].getAbsolutePath());
            else
                jTextFieldDefinition.setText("Multiple files selected");
        }
    }

    private void jButtonGetInputFileActionPerformed(java.awt.event.ActionEvent evt) {
        jTextFieldInputFile.setText(createFileChooser("Select Input File"));
    }

    private void jButtonCheckActionPerformed(java.awt.event.ActionEvent evt) {
        //Check the directory field contain something
        if (!jTextFieldInputFile.getText().equals("") && !jTextFieldDefinition.getText().equals("")){
            jTextAreaDefinition.setText("");
            Main.startJob();
        }
        else if (jTextFieldInputFile.getText().equals("") && !jTextFieldDefinition.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No input file specified","Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (!jTextFieldInputFile.getText().equals("") && jTextFieldDefinition.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No definition file specified","Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,"No files specified","Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String createFileChooser(String title){
        //Set the default directory of the file chooser to the directory of project
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        chooser.setDialogTitle(title);
        //Only allow users to select files
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);// disable the "All files" option
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().toString();//Return the directory of the selected file
        }
        else {
            //If nothing is selected display warning and return null
            JOptionPane.showMessageDialog(this,"No input file specified"," Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }


    private File[] createFileChooserDefinitions(String title){
        //Set the default directory of the file chooser to the directory of project
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        chooser.setDialogTitle(title);
        chooser.setMultiSelectionEnabled(true);
        //Only allow users to select files
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);// disable the "All files" option
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFiles();
            //return chooser.getSelectedFile().toString();//Return the directory of the selected file
        }
        else {
            //If nothing is selected display warning and return null
            JOptionPane.showMessageDialog(this,"No input file specified"," Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }


    public JTextField getjTextFieldDefinition() {
        return jTextFieldDefinition;
    }

    JTextField getjTextFieldInputFile() {
        return jTextFieldInputFile;
    }

    JTextArea getjTextAreaDefinition() {
        return jTextAreaDefinition;
    }

    File[] getDefinitionFiles() {
        return definitionFiles;
    }

    private File[] definitionFiles;
    private JFileChooser chooser;
    private javax.swing.JTextArea jTextAreaDefinition;
    private javax.swing.JTextField jTextFieldDefinition;
    private javax.swing.JTextField jTextFieldInputFile;
}
