import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class frmRecoverPass extends JFrame {

	private JPanel contentPane;
	private JButton btnRuajFjalekalimin; 
	private JPasswordField txtFjalekalimi;
	private JPasswordField txtKonfirmo;
	private String nrPersonal = "";
	private JLabel lblfjalekalimi;
	private JLabel lblFjalekalimiIRi;
	private JLabel lblKonfirmo;
	private JLabel lblkonfirmo;
	private JRadioButton radioButton; 
	PreparedStatement pst = null;
	Connection conn = null;
	ResultSet res = null;
	private JLabel label_2;
	private JRadioButton rdbtnAlb; 
	private boolean check = true;
	private JLabel label_3;

	/**
	 * Launch the application.
	 */
	public void setNrPersonal(String nrPersonal)
	{
		this.nrPersonal = nrPersonal;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRecoverPass frame = new frmRecoverPass();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public frmRecoverPass() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmRecoverPass.class.getResource("/imgs/logo1icon1.png")));
		setTitle("Besa Commerce");
		conn = connectionClass.connectDb();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 662);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFjalekalimiIRi = new JLabel();
		lblFjalekalimiIRi.setForeground(Color.LIGHT_GRAY);
		lblFjalekalimiIRi.setFont(new Font("Arial", Font.BOLD, 15));
		lblFjalekalimiIRi.setBounds(127, 275, 154, 35);
		contentPane.add(lblFjalekalimiIRi);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setForeground(new Color(0, 0, 51));
		txtFjalekalimi.setFont(new Font("Arial", Font.BOLD, 15));
		txtFjalekalimi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lblFjalekalimiIRi.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(Gjuhesia.gjuha.equals("alb"))
				{
					if(txtFjalekalimi.getText().length() == 0)
						lblFjalekalimiIRi.setText("Fjalëkalimi i ri");
			
				}
				else
				{
					if(txtFjalekalimi.getText().length() == 0)
						lblFjalekalimiIRi.setText("New password");
				}
			}
		});
		txtFjalekalimi.setBorder(null);
		txtFjalekalimi.setOpaque(false);
		txtFjalekalimi.setBounds(127, 275, 263, 35);
		contentPane.add(txtFjalekalimi);
		txtFjalekalimi.setColumns(10);
		
		txtKonfirmo = new JPasswordField();
		txtKonfirmo.setForeground(new Color(0, 0, 51));
		txtKonfirmo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					recover();
				}
			}
		});
		txtKonfirmo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lblKonfirmo.setText("");
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(Gjuhesia.gjuha.equals("alb"))
				{
					if(txtKonfirmo.getText().length() == 0)
						lblKonfirmo.setText("Konfirmo fjalëkalimin");
				}
				else
				{
					if(txtKonfirmo.getText().length() == 0)
						lblKonfirmo.setText("Confirm password");
				
				}
			}
		});
		txtKonfirmo.setFont(new Font("Arial", Font.BOLD, 15));
		txtKonfirmo.setBorder(null);
		txtKonfirmo.setOpaque(false);
		txtKonfirmo.setColumns(10);
		txtKonfirmo.setBounds(127, 357, 263, 35);
		contentPane.add(txtKonfirmo);
		
		
		lblKonfirmo = new JLabel();
		lblKonfirmo.setForeground(Color.LIGHT_GRAY);
		lblKonfirmo.setFont(new Font("Arial", Font.BOLD, 15));
		lblKonfirmo.setBounds(127, 357, 154, 35);
		contentPane.add(lblKonfirmo);
		
		btnRuajFjalekalimin = new JButton();
		btnRuajFjalekalimin.setForeground(new Color(255, 255, 255));
		btnRuajFjalekalimin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					recover();
				}
			}
		});
		btnRuajFjalekalimin.setIcon(new ImageIcon(frmRecoverPass.class.getResource("/imgs/lck.png")));
		btnRuajFjalekalimin.setFont(new Font("Arial", Font.BOLD, 16));
		btnRuajFjalekalimin.setBackground(new Color(135, 206, 255));
		btnRuajFjalekalimin.setBorder(null);
		btnRuajFjalekalimin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recover();
			}
		});
		btnRuajFjalekalimin.setBounds(125, 472, 212, 44);
		contentPane.add(btnRuajFjalekalimin);
		
		lblfjalekalimi = new JLabel("");
		lblfjalekalimi.setBounds(77, 309, 313, 16);
		contentPane.add(lblfjalekalimi);
		
		lblkonfirmo = new JLabel("");
		lblkonfirmo.setBounds(77, 390, 313, 16);
		contentPane.add(lblkonfirmo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(77, 308, 313, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(74, 390, 322, 2);
		contentPane.add(separator_1);
		
		rdbtnAlb = new JRadioButton("");
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "alb";
				if(txtFjalekalimi.getText().length() == 0)
					lblFjalekalimiIRi.setText("Fjalëkalimi!");
				if(txtKonfirmo.getText().length() == 0)
					lblKonfirmo.setText("Konfirmo fjalëkalimin!");
				btnRuajFjalekalimin.setText("Ruaj fjalëkalimin");
				if(check == false)
					recover();
			}
		});
		rdbtnAlb.setIcon(new ImageIcon(frmRecoverPass.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setBounds(389, 9, 33, 25);
		contentPane.add(rdbtnAlb);
		
		radioButton = new JRadioButton("");
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(txtFjalekalimi.getText().length() == 0)
					lblFjalekalimiIRi.setText("New password");
				if(txtKonfirmo.getText().length() == 0)
					lblKonfirmo.setText("Confirm password");
				btnRuajFjalekalimin.setText("Save new password");
				Gjuhesia.gjuha = "eng";
				if(check == false)
					recover();
			}
		});
		radioButton.setIcon(new ImageIcon(frmRecoverPass.class.getResource("/imgs/eng.png")));
		radioButton.setOpaque(false);
		radioButton.setBounds(416, 9, 33, 25);
		contentPane.add(radioButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmRecoverPass.class.getResource("/imgs/lock7.png")));
		label.setBounds(74, 260, 50, 50);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmRecoverPass.class.getResource("/imgs/lock6.png")));
		label_1.setBounds(74, 342, 50, 50);
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmRecoverPass.class.getResource("/imgs/puneLogo1.png")));
		label_2.setBounds(0, 0, 457, 192);
		contentPane.add(label_2);
		
		label_3 = new JLabel("");
		label_3.setOpaque(true);
		label_3.setBackground(new Color(135,206,255));
		label_3.setBounds(0, 537, 463, 109);
		contentPane.add(label_3);
		if(Gjuhesia.gjuha.equals("alb"))
		{
			if(txtFjalekalimi.getText().length() == 0)
				lblFjalekalimiIRi.setText("Fjalëkalimi!");
			if(txtKonfirmo.getText().length() == 0)
				lblKonfirmo.setText("Konfirmo fjalëkalimin!");
			btnRuajFjalekalimin.setText("Ruaj Fjalekalimin");
			
		}
		else
		{
			if(txtFjalekalimi.getText().length() == 0)
				lblFjalekalimiIRi.setText("New password");
			if(txtKonfirmo.getText().length() == 0)
				lblKonfirmo.setText("Confirm password");
			btnRuajFjalekalimin.setText("Save new password");
		}
	}
	
	public void recover()
	{
		try
		{
			
			if(txtFjalekalimi.getText().length() <8)
			{
				if(Gjuhesia.gjuha.equals("alb"))
				{
					lblfjalekalimi.setText("Fjalekalimi duhet te jete se paku 8 karaktere!");
					check = false;
				}
				else
				{
					check = false;
					lblfjalekalimi.setText("The password should be at least 8 chars");
				}
				txtFjalekalimi.requestFocus();
			}
			else if(!txtFjalekalimi.getText().equals(txtKonfirmo.getText()))
			{
				if(Gjuhesia.gjuha.equals("alb"))
				{
					lblkonfirmo.setText("Fjalekalimi nuk perputhet!");
					check = false;
				}
				else
				{
					check = false;
					lblkonfirmo.setText("The password doesn't match");
				}
				txtFjalekalimi.setText("");
				txtKonfirmo.setText("");
				txtFjalekalimi.requestFocus();
			}
			else
			{
				
				String salt = Encryption.generateSalt();
				String pass = txtFjalekalimi.getText() + salt;
				String passHash = Encryption.SHA1(pass);
				JOptionPane.showMessageDialog(null, nrPersonal + " "+passHash+"  "+salt);
				String query1 = "UPDATE tblStafi SET passwordi = '"+passHash+"', salt = '"+salt+"' WHERE nrPersonal = '"+nrPersonal+"'";
				Statement stmt1 = conn.createStatement();
				stmt1.executeUpdate(query1);
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Fjalëkalimi juaj është ndryshuar me sukses!");
				else
					JOptionPane.showMessageDialog(null, "Your password has been successfully changed!");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Something bad  frmRecoverPass! "+ex.getMessage());
		}
	}
}
