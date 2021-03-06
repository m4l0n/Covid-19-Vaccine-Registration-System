/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration.system;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.util.regex.*;  
/**
 *
 * @author nigel
 */
public class Login extends javax.swing.JFrame {
    String userName="", userPass="", userType="";
    private final String dataUser = "dataUser.txt";
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        
        //Resize image automatically
        ImageIcon userImage1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/userr.png")));
        Image userImage2 = userImage1.getImage();
        Image userImage3 = userImage2.getScaledInstance(userImageLogin.getWidth(), userImageLogin.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(userImage3);
        userImageLogin.setIcon(i);
        
        ImageIcon userImageRegister1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/userr.png")));
        Image userImageRegister2 = userImageRegister1.getImage();
        Image userImageRegister3 = userImageRegister2.getScaledInstance(userImageRegister.getWidth(), userImageRegister.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(userImageRegister3);
        userImageRegister.setIcon(i2);
        
        ImageIcon vaccineImage1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/vaccine.png")));
        Image vaccineImage2 = vaccineImage1.getImage();
        Image vaccineImage3 = vaccineImage2.getScaledInstance(vaccineImage.getWidth(), vaccineImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(vaccineImage3);
        vaccineImage.setIcon(i3);
        
        //Set Border to null for switchRegButton
        switchRegButton.setBorder(null);
        
        //Set Default Text on stateComboBox
        stateComboBox.setEditable(true);
        stateComboBox.setSelectedItem("State");
        
        //Set Default Text on JDateChooser
        ((JTextField)dobCalendar.getDateEditor().getUiComponent()).setText("Date of Birth");
        
        Font font = switchRegButton.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        switchRegButton.setFont(font.deriveFont(attributes));
        
        Font font2 = switchLoginButton.getFont();
        Map attributes2 = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        switchLoginButton.setFont(font.deriveFont(attributes));
        
        //Set text field prompt
        TextPrompt tpName = new TextPrompt("Name", nameText);
        TextPrompt tpPhoneNum = new TextPrompt("Phone Number", phoneNumText);
        TextPrompt tpUserReg = new TextPrompt("IC/Passport Number", idRegText);
        TextPrompt tpPassReg = new TextPrompt("Password", passRegText);
        TextPrompt tpCitizenship = new TextPrompt("Citizenship", citizenshipText);
        
        TextPrompt tpUserLogin = new TextPrompt("IC/Passport Number", idText);
        TextPrompt tpPassLogin = new TextPrompt("Password", passText);
        
        loginErrorLabel.setText("");
        
        //Generate default data
        new Personnel().firstUsers();
        new Personnel().firstVaccineCentres();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderButtonGroup = new javax.swing.ButtonGroup();
        vaccineImage = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        loginPane = new javax.swing.JPanel();
        userImageLogin = new javax.swing.JLabel();
        idText = new javax.swing.JTextField();
        passText = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        switchRegButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        loginErrorLabel = new javax.swing.JLabel();
        regPane = new javax.swing.JPanel();
        userImageRegister = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        idRegText = new javax.swing.JTextField();
        passRegText = new javax.swing.JTextField();
        phoneNumText = new javax.swing.JTextField();
        citizenshipText = new javax.swing.JTextField();
        regButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        switchLoginButton = new javax.swing.JButton();
        stateComboBox = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        dobCalendar = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Covid-19 Vaccine Registration System");
        setResizable(false);

        vaccineImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/vaccine.png"))); // NOI18N
        vaccineImage.setText("jLabel2");

        mainPanel.setPreferredSize(new java.awt.Dimension(388, 560));
        mainPanel.setLayout(new java.awt.CardLayout());

        userImageLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/userr.png"))); // NOI18N
        userImageLogin.setMaximumSize(new java.awt.Dimension(60, 60));
        userImageLogin.setMinimumSize(new java.awt.Dimension(60, 60));
        userImageLogin.setPreferredSize(new java.awt.Dimension(60, 60));

        idText.setToolTipText("Enter Username");

        passText.setToolTipText("Enter Password");

        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8_login_26px_1.png"))); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        switchRegButton.setBackground(new java.awt.Color(242, 242, 242));
        switchRegButton.setText("Register Here");
        switchRegButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        switchRegButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchRegButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Don't have an account?");

        loginErrorLabel.setForeground(new java.awt.Color(255, 51, 51));
        loginErrorLabel.setText("Username/Password combination is incorrect! Please try again");

        javax.swing.GroupLayout loginPaneLayout = new javax.swing.GroupLayout(loginPane);
        loginPane.setLayout(loginPaneLayout);
        loginPaneLayout.setHorizontalGroup(
            loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPaneLayout.createSequentialGroup()
                        .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPaneLayout.createSequentialGroup()
                                .addComponent(userImageLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(passText, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPaneLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(switchRegButton)
                        .addContainerGap())))
            .addGroup(loginPaneLayout.createSequentialGroup()
                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPaneLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPaneLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(loginErrorLabel)))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        loginPaneLayout.setVerticalGroup(
            loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPaneLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(userImageLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(passText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(loginErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(loginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(switchRegButton)))
        );

        mainPanel.add(loginPane, "loginPane");

        userImageRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/userr.png"))); // NOI18N
        userImageRegister.setMaximumSize(new java.awt.Dimension(60, 60));
        userImageRegister.setMinimumSize(new java.awt.Dimension(60, 60));
        userImageRegister.setPreferredSize(new java.awt.Dimension(60, 60));

        phoneNumText.setToolTipText("");

        regButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        regButton.setText("Register");
        regButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Have an account already?");

        switchLoginButton.setBackground(new java.awt.Color(242, 242, 242));
        switchLoginButton.setText("Login");
        switchLoginButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        switchLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchLoginButtonActionPerformed(evt);
            }
        });

        stateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Johor", "Kedah", "Kelantan", "Kuala Lumpur", "Labuan", "Malacca", "Negeri Sembilan", "Pahang", "Perak", "Perlis", "Putrajaya", "Sabah", "Sarawak", "Selangor", "Sembilan", "Terengganu" }));

        genderButtonGroup.add(jRadioButton1);
        jRadioButton1.setText("Male");

        genderButtonGroup.add(jRadioButton2);
        jRadioButton2.setText("Female");

        javax.swing.GroupLayout regPaneLayout = new javax.swing.GroupLayout(regPane);
        regPane.setLayout(regPaneLayout);
        regPaneLayout.setHorizontalGroup(
            regPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(switchLoginButton)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userImageRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
            .addGroup(regPaneLayout.createSequentialGroup()
                .addGroup(regPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regPaneLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(regButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(regPaneLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(regPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(citizenshipText)
                            .addComponent(phoneNumText)
                            .addComponent(passRegText)
                            .addComponent(idRegText)
                            .addComponent(nameText, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(stateComboBox, 0, 226, Short.MAX_VALUE)
                            .addGroup(regPaneLayout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton2))
                            .addComponent(dobCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        regPaneLayout.setVerticalGroup(
            regPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userImageRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(phoneNumText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(idRegText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passRegText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dobCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(citizenshipText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(regPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(regButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(regPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(switchLoginButton)
                    .addComponent(jLabel2)))
        );

        mainPanel.add(regPane, "regPane");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vaccineImage, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vaccineImage, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void switchRegButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchRegButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "regPane");
    }//GEN-LAST:event_switchRegButtonActionPerformed

    private void switchLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchLoginButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, "loginPane");
    }//GEN-LAST:event_switchLoginButtonActionPerformed

    private void regButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regButtonActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dcn = new SimpleDateFormat("dd-MM-yyyy");
        try {
            //Check if the IC / Passport number is valid
            if(isValidUserID(idRegText.getText()))
            {
                //Check if the password format is valid
                if (isValidPassword(passRegText.getText()))
                {
                    //Initialise an empty appointment for both dose1 and dose2
                    //attribute for People
                    Appointment emptyAppointment = new Appointment();
                    emptyAppointment.setAppointmentID("none");
                    People newPeople = new People();
                    newPeople.setName(nameText.getText());
                    //Checks if the phone number format is valid
                    if (isValidPhoneNum(phoneNumText.getText())){
                        newPeople.setPhoneNum(phoneNumText.getText());
                    } else { throw new PhoneNumberFormatException("Phone Number Format Invalid!");}
                    newPeople.setUsername(idRegText.getText());
                    newPeople.setPassword(passRegText.getText());
                    newPeople.setDate(dcn.format(dobCalendar.getDate()));
                    newPeople.setState(String.valueOf(stateComboBox.getSelectedItem()));
                    newPeople.setCitizenship(citizenshipText.getText());
                    newPeople.setGender(getSelectedButton());
                    newPeople.setUserType("People");
                    newPeople.setStatus("Unvaccinated");
                    newPeople.setDose1(emptyAppointment);
                    newPeople.setDose2(emptyAppointment);
                    newPeople.registerProfile(newPeople);
                    JOptionPane.showMessageDialog(null, "Account registered "
                    + "successfully. Plese return to login page to continue.");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "IC/Passport Number format is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (PhoneNumberFormatException e){
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_regButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String result = new User().verifyLogin(idText.getText(), 
                String.valueOf(passText.getPassword()));
        if (result.equals("ADMIN"))
        {
            //Opens PersonnelGUI and dispose current form
            PersonnelGUI psn = new PersonnelGUI();
            psn.setVisible(true);
            this.dispose();
        }
        else if (isValidUserID(result))
        {
            //Opens PeopleGUI and dispose current form
            PeopleGUI ppl = new PeopleGUI(result);
            ppl.setVisible(true);
            this.dispose();
        }
        else if (result.equals("Invalid"))
        {
            loginErrorLabel.setText("Username/Password combination is "
                    + "incorrect! Please try again");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try{
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //</editor-fold>
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    //Checks the validity of password according to a set of rules
    public static boolean isValidPassword(String password)
    {
        if (password.length() < 8)
        {
            JOptionPane.showMessageDialog(null,
                    "Password must be more than 8 characters in length.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            JOptionPane.showMessageDialog(null,
                    "Password must have at least one uppercase character.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            JOptionPane.showMessageDialog(null,
                    "Password must have at least one lowercase character.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            JOptionPane.showMessageDialog(null,
                    "Password must have at least one number.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);                
            return false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";

        return true; 
    }
    
    public static boolean isValidUserID(String userID)
    {
        //Regular Expression for a valid Malaysian NRIC
        return Pattern.compile("(([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01]))-([0-9]{2})-([0-9]{4})")
                .matcher(userID).matches();
    }
    
    public static boolean isValidPhoneNum(String phoneNum)
    {
        //Regular Expression for a valid Malaysian phone number
        return Pattern.compile("\\+?6?(?:01[0-46-9]\\d{7,8}|0\\d{8})").matcher(phoneNum).matches();
    }
    
    private String getSelectedButton()
    {  
        //Loop through the Button Group to get the value of the selected button
        for (Enumeration<AbstractButton> buttons = genderButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                    return button.getText();
            }
        }
        return null;
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField citizenshipText;
    private com.toedter.calendar.JDateChooser dobCalendar;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JTextField idRegText;
    private javax.swing.JTextField idText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginErrorLabel;
    private javax.swing.JPanel loginPane;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField passRegText;
    private javax.swing.JPasswordField passText;
    private javax.swing.JTextField phoneNumText;
    private javax.swing.JButton regButton;
    private javax.swing.JPanel regPane;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JButton switchLoginButton;
    private javax.swing.JButton switchRegButton;
    private javax.swing.JLabel userImageLogin;
    private javax.swing.JLabel userImageRegister;
    private javax.swing.JLabel vaccineImage;
    // End of variables declaration//GEN-END:variables

}
