
import java.awt.BorderLayout;
import java.util.regex.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
public class frmSignUp extends JFrame {
	
	private JLabel lblPozita; 
	private JLabel lblpassi ;
	private JLabel lblEmri;
	private JLabel lblMbiemri;
	private JLabel lblDataELindjes;
	private JLabel lblTel;
	private JLabel lblEmail;
	private JComboBox cmbQytetet;
	private JLabel lblUsername;
	private JLabel lblGjinia;
	private JLabel lblpozita;
	private JLabel lblemail;
	private JLabel lblvendi;
	private JLabel lbldata;
	private JLabel lblmbiemri;
	private JLabel lblemri;
	private JLabel lbltel;
	private JLabel lbluser;
	private JPanel contentPane;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private JTextField txtUser;
	private JLabel lblKthehuPrapa;
	private JTextField txtEmail;
	private JTextField txtTel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbMashkull;
	private JRadioButton rdbFemer; 
	PreparedStatement pst = null;
	ResultSet res= null;
	Connection conn = null;
	private JRadioButton rdbtnA;
	private JPasswordField txtPassi;
	private JComboBox cmbShteti;
	private JDateChooser txtData;
	private JLabel lblKonfirmoFjalekalimin;
	private JPasswordField txtKonfirmo;
	private JLabel lblKonfirmo;
	private JLabel lblQytetiVendbanimit;
	private JLabel lblqyteti;
	private JComboBox cmbPozita;
	private JLabel lblNrPersonal;
	private JTextField txtNrPersonal;
	private JLabel lblnrPersonal;
	private JLabel lblNewLabel;
	private JLabel lblPaga_1;
	private JTextField txtPaga;
	private JLabel lblPaga;
	private JRadioButton rdbtnAlb;
	private JRadioButton rdbtnEng;
	private JSeparator separator_1;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JSeparator separator_5;
	private JSeparator separator_6;
	private JSeparator separator_7;
	Color ngjyra = new Color(22,127,146);
	Color ngjyra1 = new Color(0, 0, 51);
	private JLabel label;
	JLabel lblVendbanimi_1 = new JLabel("Shteti vendbanimit: ");
	JLabel lblPassword = new JLabel("Fjalekalimi:");
	JButton btnRegjistrohu = new JButton("Regjistrohu");
	JMenuItem mntmExit = new JMenuItem("Dalja");
	private JLabel lblShitesi;
	private JLabel lblPerdoruesi;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSignUp frame = new frmSignUp();
					
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
	
	
	
	public frmSignUp() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmSignUp.class.getResource("/imgs/logo1icon1.png")));
		setTitle("Besa Commerce");
		conn = connectionClass.connectDb();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1267, 967);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(204, 255, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblEmri = new JLabel("Emri:");
		lblEmri.setBounds(12, 260, 107, 44);
		lblEmri.setForeground(new Color(0, 0, 51));
		contentPane.add(lblEmri);
		lblEmri.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblMbiemri = new JLabel("Mbiemri:");
		lblMbiemri.setBounds(403, 261, 152, 44);
		lblMbiemri.setForeground(new Color(0, 0, 51));
		contentPane.add(lblMbiemri);
		lblMbiemri.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblUsername = new JLabel("Perdoruesi:");
		lblUsername.setBounds(12, 635, 107, 44);
		lblUsername.setForeground(new Color(0, 0, 51));
		contentPane.add(lblUsername);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblGjinia = new JLabel("Gjinia:");
		lblGjinia.setBounds(12, 393, 107, 44);
		lblGjinia.setForeground(new Color(0, 0, 51));
		contentPane.add(lblGjinia);
		lblGjinia.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblDataELindjes = new JLabel("Data e lindjes:");
		lblDataELindjes.setBounds(816, 260, 160, 44);
		lblDataELindjes.setForeground(new Color(0, 0, 51));
		contentPane.add(lblDataELindjes);
		lblDataELindjes.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataELindjes.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(403, 393, 152, 44);
		lblEmail.setForeground(new Color(0, 0, 51));
		contentPane.add(lblEmail);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblTel = new JLabel("Tel:");
		lblTel.setBounds(816, 393, 160, 44);
		lblTel.setForeground(new Color(0, 0, 51));
		contentPane.add(lblTel);
		lblTel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTel.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblPozita = new JLabel("Pozita:");
		lblPozita.setBounds(12, 751, 107, 44);
		lblPozita.setForeground(new Color(0, 0, 51));
		contentPane.add(lblPozita);
		lblPozita.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtEmri = new JTextField();
		txtEmri.setForeground(new Color(0, 0, 51));
		txtEmri.setBorder(null);
		txtEmri.setOpaque(false);
		txtEmri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				try
				{
					if(!txtEmri.getText().equals(""))
					{
						StringBuilder str = new StringBuilder(txtEmri.getText());
						str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
						txtEmri.setText(str.toString());
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		txtEmri.setBounds(131, 259, 211, 47);
		contentPane.add(txtEmri);
		txtEmri.setFont(new Font("Arial", Font.BOLD, 14));
		txtEmri.setColumns(10);
		
		txtMbiemri = new JTextField();
		txtMbiemri.setForeground(new Color(0, 0, 51));
		txtMbiemri.setBorder(null);
		txtMbiemri.setOpaque(false);
		txtMbiemri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try
				{
					if(!txtMbiemri.getText().equals(""))
					{
						StringBuilder str = new StringBuilder(txtMbiemri.getText());
						str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
						txtMbiemri.setText(str.toString());
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		txtMbiemri.setBounds(552, 260, 211, 47);
		contentPane.add(txtMbiemri);
		txtMbiemri.setFont(new Font("Arial", Font.BOLD, 14));
		txtMbiemri.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setForeground(new Color(0, 0, 51));
		txtUser.setBorder(null);
		txtUser.setOpaque(false);
		txtUser.setBounds(131, 632, 211, 47);
		contentPane.add(txtUser);
		txtUser.setFont(new Font("Arial", Font.BOLD, 14));
		txtUser.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(0, 0, 51));
		txtEmail.setBorder(null);
		txtEmail.setOpaque(false);
		txtEmail.setBounds(552, 390, 211, 47);
		contentPane.add(txtEmail);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 14));
		txtEmail.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setForeground(new Color(0, 0, 51));
		txtTel.setBorder(null);
		txtTel.setOpaque(false);
		txtTel.setBounds(988, 390, 211, 47);
		contentPane.add(txtTel);
		txtTel.setFont(new Font("Arial", Font.BOLD, 14));
		txtTel.setColumns(10);
		
		rdbMashkull = new JRadioButton("M");
		rdbMashkull.setForeground(new Color(0, 0, 51));
		rdbMashkull.setOpaque(false);
		rdbMashkull.setBounds(131, 393, 70, 44);
		contentPane.add(rdbMashkull);
		rdbMashkull.setFont(new Font("Arial", Font.BOLD, 14));
		rdbMashkull.setSelected(true);
		buttonGroup.add(rdbMashkull);
		
		rdbFemer = new JRadioButton("F");
		rdbFemer.setForeground(new Color(0, 0, 51));
		rdbFemer.setOpaque(false);
		rdbFemer.setBounds(208, 393, 70, 44);
		contentPane.add(rdbFemer);
		rdbFemer.setFont(new Font("Arial", Font.BOLD, 14));
		buttonGroup.add(rdbFemer);
		btnRegjistrohu.setForeground(new Color(255, 255, 255));
		
		btnRegjistrohu.setBackground(new Color(153, 204, 255));
		btnRegjistrohu.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/2001877-32.png")));
		btnRegjistrohu.setBorder(new LineBorder(new Color(0, 102, 0), 1, true));
		btnRegjistrohu.setBounds(490, 828, 268, 53);
		contentPane.add(btnRegjistrohu);
		btnRegjistrohu.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblVendbanimi_1.setForeground(new Color(0, 0, 51));
		
		lblVendbanimi_1.setBounds(403, 509, 152, 44);
		contentPane.add(lblVendbanimi_1);
		lblVendbanimi_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		rdbtnA = new JRadioButton("A");
		rdbtnA.setForeground(new Color(0, 0, 51));
		rdbtnA.setOpaque(false);
		rdbtnA.setBounds(282, 393, 70, 44);
		contentPane.add(rdbtnA);
		buttonGroup.add(rdbtnA);
		rdbtnA.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblemri = new JLabel("");
		lblemri.setBounds(131, 308, 249, 16);
		contentPane.add(lblemri);
		lblemri.setToolTipText("emer");
		lblemri.setForeground(Color.RED);
		lblemri.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lblmbiemri = new JLabel("");
		lblmbiemri.setBounds(552, 308, 252, 16);
		contentPane.add(lblmbiemri);
		lblmbiemri.setToolTipText("mbiemer");
		lblmbiemri.setForeground(Color.RED);
		lblmbiemri.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lblvendi = new JLabel("");
		lblvendi.setBounds(552, 557, 252, 16);
		contentPane.add(lblvendi);
		lblvendi.setToolTipText("vendbanim");
		lblvendi.setForeground(Color.RED);
		lblvendi.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lbldata = new JLabel("");
		lbldata.setBounds(988, 308, 252, 16);
		contentPane.add(lbldata);
		lbldata.setForeground(Color.RED);
		lbldata.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lbluser = new JLabel("");
		lbluser.setBounds(131, 679, 249, 16);
		contentPane.add(lbluser);
		lbluser.setForeground(Color.RED);
		lbluser.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lblemail = new JLabel("");
		lblemail.setBounds(552, 437, 252, 16);
		contentPane.add(lblemail);
		lblemail.setForeground(Color.RED);
		lblemail.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lbltel = new JLabel("");
		lbltel.setBounds(988, 437, 252, 16);
		contentPane.add(lbltel);
		lbltel.setForeground(Color.RED);
		lbltel.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lblpozita = new JLabel("");
		lblpozita.setBounds(131, 796, 249, 16);
		contentPane.add(lblpozita);
		lblpozita.setToolTipText("pozite");
		lblpozita.setForeground(Color.RED);
		lblpozita.setFont(new Font("Arial", Font.ITALIC, 10));
		lblPassword.setForeground(new Color(0, 0, 51));
		
		lblPassword.setBounds(403, 635, 152, 44);
		contentPane.add(lblPassword);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtPassi = new JPasswordField();
		txtPassi.setForeground(new Color(0, 0, 51));
		txtPassi.setBorder(null);
		txtPassi.setOpaque(false);
		txtPassi.setBounds(552, 632, 211, 47);
		contentPane.add(txtPassi);
		txtPassi.setFont(new Font("Arial", Font.BOLD, 14));
		txtPassi.setColumns(10);
		
		lblpassi = new JLabel("");
		lblpassi.setBounds(552, 679, 252, 16);
		contentPane.add(lblpassi);
		lblpassi.setForeground(Color.RED);
		lblpassi.setFont(new Font("Arial", Font.ITALIC, 10));
		
		lblKonfirmoFjalekalimin = new JLabel("Konfirmo fjalekalimin:");
		lblKonfirmoFjalekalimin.setForeground(new Color(0, 0, 51));
		lblKonfirmoFjalekalimin.setBounds(816, 635, 160, 44);
		contentPane.add(lblKonfirmoFjalekalimin);
		lblKonfirmoFjalekalimin.setHorizontalAlignment(SwingConstants.LEFT);
		lblKonfirmoFjalekalimin.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtKonfirmo = new JPasswordField();
		txtKonfirmo.setForeground(new Color(0, 0, 51));
		txtKonfirmo.setBorder(null);
		txtKonfirmo.setOpaque(false);
		txtKonfirmo.setBounds(988, 632, 211, 47);
		contentPane.add(txtKonfirmo);
		txtKonfirmo.setFont(new Font("Arial", Font.BOLD, 14));
		txtKonfirmo.setColumns(10);
		
		lblKonfirmo = new JLabel("");
		lblKonfirmo.setBounds(988, 679, 252, 16);
		contentPane.add(lblKonfirmo);
		lblKonfirmo.setForeground(Color.RED);
		lblKonfirmo.setFont(new Font("Arial", Font.ITALIC, 10));
		
		cmbShteti = new JComboBox();
		cmbShteti.setForeground(new Color(0, 0, 51));
		cmbShteti.setFont(new Font("Arial", Font.BOLD, 14));
		cmbShteti.setBorder(new LineBorder(new Color(204, 255, 255)));
		cmbShteti.setModel(new DefaultComboBoxModel(new String[] {""}));
		cmbShteti.setBounds(552, 513, 252, 47);
		contentPane.add(cmbShteti);
		
		lblQytetiVendbanimit = new JLabel("Qyteti vendbanimit:");
		lblQytetiVendbanimit.setForeground(new Color(0, 0, 51));
		lblQytetiVendbanimit.setBounds(816, 509, 160, 44);
		contentPane.add(lblQytetiVendbanimit);
		lblQytetiVendbanimit.setHorizontalAlignment(SwingConstants.LEFT);
		lblQytetiVendbanimit.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblqyteti = new JLabel("");
		lblqyteti.setBounds(988, 557, 288, 16);
		contentPane.add(lblqyteti);
		lblqyteti.setToolTipText("vendbanim");
		lblqyteti.setForeground(Color.RED);
		lblqyteti.setFont(new Font("Arial", Font.ITALIC, 10));
		
		cmbPozita = new JComboBox();
		cmbPozita.setForeground(new Color(0, 0, 51));
		cmbPozita.setFont(new Font("Arial", Font.BOLD, 14));
		cmbPozita.setBorder(new LineBorder(new Color(204, 255, 255)));
		cmbPozita.setModel(new DefaultComboBoxModel(new String[] {""}));
		cmbPozita.setBounds(131, 748, 249, 47);
		contentPane.add(cmbPozita);
		
		lblNrPersonal = new JLabel("Nr. Personal:");
		lblNrPersonal.setForeground(new Color(0, 0, 51));
		lblNrPersonal.setBounds(12, 513, 107, 44);
		contentPane.add(lblNrPersonal);
		lblNrPersonal.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtNrPersonal = new JTextField();
		txtNrPersonal.setForeground(new Color(0, 0, 51));
		txtNrPersonal.setBorder(null);
		txtNrPersonal.setOpaque(false);
		txtNrPersonal.setBounds(131, 509, 211, 47);
		contentPane.add(txtNrPersonal);
		txtNrPersonal.setFont(new Font("Arial", Font.BOLD, 14));
		txtNrPersonal.setColumns(10);
		
		lblnrPersonal = new JLabel("");
		lblnrPersonal.setBounds(131, 557, 249, 16);
		contentPane.add(lblnrPersonal);
		lblnrPersonal.setToolTipText("emer");
		lblnrPersonal.setForeground(Color.RED);
		lblnrPersonal.setFont(new Font("Arial", Font.ITALIC, 10));
		
		txtData = new JDateChooser();
		txtData.setDateFormatString("dd/MM/yyyy");
		txtData.setOpaque(false);
		txtData.setBounds(988, 260, 252, 47);
		contentPane.add(txtData);
		
		lblPaga_1 = new JLabel("Paga:");
		lblPaga_1.setForeground(new Color(0, 0, 51));
		lblPaga_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblPaga_1.setBounds(816, 751, 160, 44);
		contentPane.add(lblPaga_1);
		
		txtPaga = new JTextField();
		txtPaga.setForeground(new Color(0, 0, 51));
		txtPaga.setFont(new Font("Arial", Font.BOLD, 14));
		txtPaga.setOpaque(false);
		txtPaga.setBorder(null);
		txtPaga.setBounds(988, 748, 211, 47);
		contentPane.add(txtPaga);
		txtPaga.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(131, 305, 249, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(131, 677, 249, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(131, 557, 249, 2);
		contentPane.add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(552, 309, 252, 2);
		contentPane.add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(552, 435, 252, 2);
		contentPane.add(separator_4);
		
		separator_5 = new JSeparator();
		separator_5.setBounds(552, 679, 252, 2);
		contentPane.add(separator_5);
		
		separator_6 = new JSeparator();
		separator_6.setBounds(988, 435, 252, 363);
		contentPane.add(separator_6);
		
		separator_7 = new JSeparator();
		separator_7.setBounds(988, 679, 252, 119);
		contentPane.add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(988, 796, 252, 2);
		contentPane.add(separator_8);
		
		lblPaga = new JLabel("");
		lblPaga.setToolTipText("pozite");
		lblPaga.setForeground(Color.RED);
		lblPaga.setFont(new Font("Arial", Font.ITALIC, 10));
		lblPaga.setBounds(988, 796, 252, 16);
		contentPane.add(lblPaga);
		
		lblShitesi = new JLabel();
		lblShitesi.setText("lala");
		lblShitesi.setForeground(new Color(255, 255, 255));
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 15));
		lblShitesi.setBackground(Color.WHITE);
		lblShitesi.setBounds(1121, 43, 119, 25);
		lblShitesi.setText(Useri.getEmri() + " "+Useri.getMbiemri());
		contentPane.add(lblShitesi);
		
		lblPerdoruesi = new JLabel("Perdoruesi:");
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setForeground(new Color(255, 255, 255));
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 15));
		lblPerdoruesi.setBackground(Color.WHITE);
		lblPerdoruesi.setBounds(1016, 43, 93, 25);
		contentPane.add(lblPerdoruesi);

		btnRegjistrohu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
					if(Gjuhesia.gjuha.equals("alb"))
					{
					
						signUp();
					}
					else
					{
						signUpEng();
					}
			}
		});
		btnRegjistrohu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(Gjuhesia.gjuha.equals("alb"))
					{
					
						signUp();
					}
					else
					{
						signUpEng();
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
					
				}
			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(153, 204, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/puneLogo1.png")));
		lblNewLabel.setBounds(0, 0, 476, 169);
		contentPane.add(lblNewLabel);
		
		rdbtnAlb = new JRadioButton("");
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
		
				Gjuhesia.gjuha = "alb";				
				NdryshoGjuhen();
				signUp();
			}
		});
		rdbtnAlb.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setSelected(true);
		rdbtnAlb.setBounds(1164, 9, 39, 25);
		contentPane.add(rdbtnAlb);
		
		rdbtnEng = new JRadioButton("");
		rdbtnEng.setOpaque(false);
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Gjuhesia.gjuha = "eng";
				NdryshoGjuhen();
				signUpEng();
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/eng.png")));
		rdbtnEng.setBounds(1193, 9, 39, 25);
		contentPane.add(rdbtnEng);
		
		lblKthehuPrapa = new JLabel();
		lblKthehuPrapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmMenu obj = new frmMenu();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblKthehuPrapa.setText("Kthehu prapa");
		lblKthehuPrapa.setForeground(Color.WHITE);
		lblKthehuPrapa.setFont(new Font("Arial", Font.BOLD, 15));
		lblKthehuPrapa.setBackground(Color.WHITE);
		lblKthehuPrapa.setBounds(1121, 74, 119, 25);
		contentPane.add(lblKthehuPrapa);
		
		label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(0, 0, 51));
		label.setBounds(0, 201, 1752, 3);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(153, 204, 255));
		label_1.setBounds(0, 0, 1752, 204);
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/gogo.png")));
		label_2.setBounds(341, 259, 39, 45);
		contentPane.add(label_2);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/carda1.png")));
		label_3.setBounds(341, 511, 41, 45);
		contentPane.add(label_3);
		
		label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/if_Username_372902.png")));
		label_4.setBounds(341, 635, 39, 45);
		contentPane.add(label_4);
		
		label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/gogo.png")));
		label_5.setBounds(765, 262, 39, 45);
		contentPane.add(label_5);
		
		label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/em.png")));
		label_6.setBounds(765, 392, 39, 45);
		contentPane.add(label_6);
		
		label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/if_lock-24_103178.png")));
		label_7.setBounds(765, 635, 39, 45);
		contentPane.add(label_7);
		
		label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/telzi.png")));
		label_8.setBounds(1201, 392, 39, 45);
		contentPane.add(label_8);
		
		label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/if_Safety01_928417.png")));
		label_9.setBounds(1201, 634, 39, 45);
		contentPane.add(label_9);
		
		label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(frmSignUp.class.getResource("/imgs/if_business-money-cash-salary-stack-glyph_763511.png")));
		label_10.setBounds(1201, 750, 39, 45);
		contentPane.add(label_10);
		
		cmbQytetet = new JComboBox();
		cmbQytetet.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				mbushQytetet();
			}
		});
		cmbQytetet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mbushQytetet();
			}
		});
		cmbQytetet.setFont(new Font("Arial", Font.BOLD, 14));
		cmbQytetet.setBounds(988, 513, 252, 47);
		contentPane.add(cmbQytetet);
		fillCombo();
		NdryshoGjuhen();
		
	}
		
	private void fillCombo()
	{
		try
		{
			ArrayList<String> shtetet = new ArrayList<String>();
			
			String query = "select shteti from tblshtetet ";
			
			shtetet.add("");
			pst = conn.prepareStatement(query);
			res = pst.executeQuery();
			
			while(res.next())
			{
				String shtet = res.getString("shteti");
				shtetet.add(shtet);
			}
			pst.close();
			cmbShteti.removeAllItems();
			cmbShteti.setModel(new DefaultComboBoxModel(shtetet.toArray()));
			
			
			ArrayList<String> pozita = new ArrayList<String>();
			pozita.add("");
			String sql = "select pershkrimi from tblpozita ";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
				String pozite = res.getString("pershkrimi");
				pozita.add(pozite);
			}
			pst.close();
			cmbPozita.removeAllItems();
			cmbPozita.setModel(new DefaultComboBoxModel(pozita.toArray()));
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	private void mbushQytetet()
	{
		try
		{
			cmbQytetet.removeAllItems();
			ArrayList<String> qytetet = new ArrayList<String>();
			
			
			
			String query = "select q.qyteti from tblqytetet q, tblshtetet s where q.sid =s.id and s.shteti ='"+cmbShteti.getSelectedItem()+"'";
			
			if(Gjuhesia.gjuha.equals("alb"))
				query = "select q.qyteti from tblqytetet q, tblshtetet s where q.sid =s.id and s.shteti ='"+cmbShteti.getSelectedItem()+"'";
			else
				query = "select q.qyteti from tblqytetet q, tblshtetet s where q.sid =s.id and s.shteti ='"+cmbShteti.getSelectedItem()+"'";
			qytetet.add("");
			pst = conn.prepareStatement(query);
			res = pst.executeQuery();
			
			while(res.next())
			{
				String qytet = res.getString("qyteti");
				qytetet.add(qytet);
			}
			pst.close();
			
			cmbQytetet.setModel(new DefaultComboBoxModel(qytetet.toArray()));
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public void signUp()
	{
		try
		{	
			if(!txtEmri.getText().equals(""))
				txtEmri.setText( txtEmri.getText().substring(0, 1).toUpperCase() + txtEmri.getText().substring(1));
			if(!txtMbiemri.getText().equals(""))
				txtMbiemri.setText( txtMbiemri.getText().substring(0, 1).toUpperCase() + txtMbiemri.getText().substring(1));
			
			txtEmail.setText(txtEmail.getText().toLowerCase());
			
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = txtData.getDate();
			String datE = dateFormat.format(date);
			String salt = Encryption.generateSalt();
			String saltedPass = txtPassi.getText() + salt;
			String passHash = Encryption.SHA1(saltedPass);
			String gjinia = "";
			String emrat = "^[a-zA-Z]+", email = "^[a-zA-Z0-9]+[\\.]?[a-zA-Z0-9]+@[a-zA-Z]*[\\.]?[a-zA-Z\\-]+\\.[a-z]{2,4}$",tel="^[0-9]+$",user="^[a-zA-Z0-9._]+$";
			int counter = 0;
			Pattern cmimi = Pattern.compile("^[0-9]+[\\\\.]?[0-9]+$");
			int dataPersonit = date.getYear();
			int dataAktuale = new Date().getYear();
			if(txtTel.getText().length()<9)
			{
				counter++;
				lbltel.setText("Shenoni nje numer valid!");
			}
			if((dataAktuale - dataPersonit)<18)
			{
						
				counter++;
				lbldata.setText("Mosha eshte me e vogel se 18!'");
						
			}
			if(cmbShteti.getSelectedIndex() == 0)
			{
				counter++;
				lblvendi.setText("Ju lutem zgjedheni shtetin e vendbanimit");
			}
			if(cmbQytetet.getSelectedIndex() == 0)
			{
				counter++;
				lblqyteti.setText("Ju lutem zgjedheni qytetin e vendbanimit");
			}
			if(cmbPozita.getSelectedIndex() == 0)
			{
				counter++;
				lblpozita.setText("Ju lutem zgjedheni poziten e punes");
			}
			if(!Pattern.matches(email, txtEmail.getText()))
			{
				counter++;
				lblemail.setText("Ju lutem shenoni nje email ne formatin e sakte: user@example.com"); 
			}
			if(!Pattern.matches(tel, txtTel.getText()))
			{
				counter++;
					
				lbltel.setText("Ju lutem shenoni numrin e telit ne formatin e sakte: 0383000111");
			}
			
					
			if(!Pattern.matches(user, txtUser.getText()))
			{
				lbluser.setText("Ju lutem shenoni nje username valid, nuk lejohet perdorimi i karaktereve speciale!");
				counter++;
			}
			
			if(!Pattern.matches(emrat, txtEmri.getText()))
			{
				lblemri.setText("Ju lutem shenoni nje emer valid!"); 
				counter++;
							
			}
			if(!txtPassi.getText().equals(txtKonfirmo.getText()))
			{
				counter++;
				lblKonfirmo.setText("Fjalekalimi nuk perputhet!");
			}
			if(!Pattern.matches(emrat, txtMbiemri.getText()))
			{
				lblmbiemri.setText("Ju lutem shenoni nje mbiemer valid!"); 
				counter++;
							
			}
			if(txtPassi.getText().length()<8)
			{
				counter++;
				lblpassi.setText("Fjalekalimi duhet te kete se paku 8 karaktere!");
			}
			
			if(txtPassi.getText().equals(""))
			{
				lblpassi.setText("Fjalekalimi nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtEmri.getText().equals(""))
			{
				lblemri.setText("Emri nuk duhet te jete i zbrazet!");
				counter++;
			}
			
			if(txtMbiemri.getText().equals(""))
			{
				lblmbiemri.setText("Mbiemri nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtEmail.getText().equals(""))
			{
				lblemail.setText("Email nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtUser.getText().equals(""))
			{
				lbluser.setText("Perdoruesi nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtTel.getText().equals(""))
			{
				lbltel.setText("Telefoni nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtNrPersonal.getText().equals(""))
			{
				lblnrPersonal.setText("Nr. Personal nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtPaga.getText().equals("") || !cmimi.matcher(txtPaga.getText()).matches())
			{
				lblPaga.setText("Paga nuk eshte e sakte");
				counter++;
			}
			if(counter != 0)
			{
				JOptionPane.showMessageDialog(null, "Keni gabime ne formen tuaj");
			}
			
			else
			{
				if(rdbMashkull.isSelected())
					gjinia = "Mashkull";
				else if(rdbFemer.isSelected())
					gjinia = "Femer";
				else
					gjinia = "Papercaktuar";
				String nrSeriku = Encryption.generateSalt();
				String eMail = salt + txtEmail.getText() + nrSeriku;
				String mail = Encryption.SHA1(eMail);

				String sql = "insert into tblstafi(nrPersonal,emri,mbiemri,username,passwordi,salt,gjiniaId,dtlindjes,tel,email,nrSerik,adreseId,poziteId,paga)\r\n" + 
						"values('"+txtNrPersonal.getText()+"','"+txtEmri.getText()+"','"+txtMbiemri.getText()+"','"+txtUser.getText()+"','"+passHash+"','"+salt+"',(select id from tblgjinia where pershkrimi = '"+gjinia+"'),'"+datE+"','"+txtTel.getText()+"','"+mail+"','"+nrSeriku+"',(select q.id from tblqytetet q, tblshtetet s where q.qyteti = '"+cmbQytetet.getSelectedItem()+"' and q.sid = s.id and s.shteti='"+cmbShteti.getSelectedItem()+"'),(select id from tblpozita where pershkrimi = '"+cmbPozita.getSelectedItem()+"'), "+txtPaga.getText()+")";
				pst = conn.prepareStatement(sql);
				pst.execute();
				pst.close();
				
				JOptionPane.showMessageDialog(null, "Regjistrimi u krye me sukses");
				txtEmri.setText("");
				txtMbiemri.setText("");
				cmbQytetet.setSelectedIndex(0);
				cmbShteti.setSelectedIndex(0);
				txtUser.setText("");
				
				txtNrPersonal.setText("");
				lblnrPersonal.setText("");
				lblqyteti.setText("");
				lblvendi.setText("");
				txtTel.setText("");
				cmbPozita.setSelectedIndex(0);
				txtEmail.setText("");
				lbldata.setText("");
				lblemri.setText("");
				lblmbiemri.setText("");
				lblvendi.setText("");
				lblpozita.setText("");
				lbltel.setText("");
				lblemail.setText("");
				lbluser.setText("");
				lblpassi.setText("");
				lblKonfirmo.setText("");
				txtPassi.setText("");
				txtKonfirmo.setText("");
				txtEmri.requestFocus();
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Sign UP alb"+ex.getMessage());
			
		}
		NdryshoGjuhen();
	}
	public void signUpEng()
	{
		try
		{	if(!txtEmri.getText().equals(""))
				txtEmri.setText( txtEmri.getText().substring(0, 1).toUpperCase() + txtEmri.getText().substring(1));
			if(!txtMbiemri.getText().equals(""))
				txtMbiemri.setText( txtMbiemri.getText().substring(0, 1).toUpperCase() + txtMbiemri.getText().substring(1));
			
			txtEmail.setText(txtEmail.getText().toLowerCase());
			
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = txtData.getDate();
			String datE = dateFormat.format(date);
			String salt = Encryption.generateSalt();
			String saltedPass = txtPassi.getText() + salt;
			String passHash = Encryption.SHA1(saltedPass);
			String gjinia = "";
			String emrat = "^[a-zA-ZëËÇç]+", email = "^[a-zA-Z0-9]+[\\.]?[a-zA-Z0-9]+@[a-zA-Z]*[\\.]?[a-zA-Z\\-]+\\.[a-z]{2,4}$",tel="^[0-9]+$",user="^[a-zA-Z0-9._]+$";
			int counter = 0;
			Pattern cmimi = Pattern.compile("^[0-9]+[\\\\.]?[0-9]+$");
			int dataPersonit = date.getYear();
			int dataAktuale = new Date().getYear();
			if(txtTel.getText().length()<9)
			{
				counter++;
				lbltel.setText("Enter a valid number!");
			}
			if((dataAktuale - dataPersonit)<18)
			{
						
				counter++;
				lbldata.setText("Age should be greater than 17!");
						
			}
			if(cmbShteti.getSelectedIndex() == 0)
			{
				counter++;
				lblvendi.setText("Select country of residence!");
			}
			if(cmbQytetet.getSelectedIndex() == 0)
			{
				counter++;
				lblqyteti.setText("Select city of residence!");
			}
			if(cmbPozita.getSelectedIndex() == 0)
			{
				counter++;
				lblpozita.setText("Select work position!");
			}
			if(!Pattern.matches(email, txtEmail.getText()))
			{
				counter++;
				lblemail.setText("Enter a valid email: user@example.com"); 
			}
			if(!Pattern.matches(tel, txtTel.getText()))
			{
				counter++;
					
				lbltel.setText("Enter a valid number: 0383000111");
			}
			
					
			if(!Pattern.matches(user, txtUser.getText()))
			{
				lbluser.setText("Enter a valid username!");
				counter++;
			}
			
			if(!Pattern.matches(emrat, txtEmri.getText()))
			{
				lblemri.setText("Enter a valid name!"); 
				counter++;
							
			}
			if(!txtPassi.getText().equals(txtKonfirmo.getText()))
			{
				counter++;
				lblKonfirmo.setText("The password doesn't match!");
			}
			if(!Pattern.matches(emrat, txtMbiemri.getText()))
			{
				lblmbiemri.setText("Enter a valid surname!"); 
				counter++;
							
			}
			if(txtPassi.getText().length()<8)
			{
				counter++;
				lblpassi.setText("Password should be at least 8 characters!");
			}
			
			if(txtPassi.getText().equals(""))
			{
				lblpassi.setText("Password field can't be empty!");
				counter++;
			}
			if(txtEmri.getText().equals(""))
			{
				lblemri.setText("Name field can't be empty!");
				counter++;
			}
			
			if(txtMbiemri.getText().equals(""))
			{
				lblmbiemri.setText("Surname can't be empty!");
				counter++;
			}
			if(txtEmail.getText().equals(""))
			{
				lblemail.setText("Email cant be empty!");
				counter++;
			}
			if(txtUser.getText().equals(""))
			{
				lbluser.setText("Username can't be empty!");
				counter++;
			}
			if(txtTel.getText().equals(""))
			{
				lbltel.setText("Phone number can't be empty!");
				counter++;
			}
			if(txtNrPersonal.getText().equals(""))
			{
				lblnrPersonal.setText("Personal No. can't be empty!");
				counter++;
			}
			if(txtPaga.getText().equals("") || !cmimi.matcher(txtPaga.getText()).matches())
			{
				lblPaga.setText("Salary is wrong!");
				counter++;
			}
			if(counter != 0)
			{
				JOptionPane.showMessageDialog(null, "You have errors on application");
			}
			
			else
			{
				if(rdbMashkull.isSelected())
					gjinia = "Mashkull";
				else if(rdbFemer.isSelected())
					gjinia = "Femer";
				else
					gjinia = "Papercaktuar";
				
				String nrSeriku = Encryption.generateSalt();
				String eMail = salt + txtEmail.getText() + nrSeriku;
				String mail = Encryption.SHA1(eMail);
				String pozita = "";
				if(cmbPozita.getSelectedItem().equals("Owner"))
				{
					pozita = "pronar";
				}
				else if(cmbPozita.getSelectedItem().equals("Manager"))
					pozita = "menaxher";
				else if(cmbPozita.getSelectedItem().equals("Worker"))
					pozita = "Punetor";
					
				String sql = "insert into tblstafi(nrPersonal,emri,mbiemri,username,passwordi,salt,gjiniaId,dtlindjes,tel,email,nrSerik,adreseId,poziteId,paga)\r\n" + 
						"values('"+txtNrPersonal.getText()+"','"+txtEmri.getText()+"','"+txtMbiemri.getText()+"','"+txtUser.getText()+"','"+passHash+"','"+salt+"',(select id from tblgjinia where pershkrimi = '"+gjinia+"'),'"+datE+"','"+txtTel.getText()+"','"+mail+"','"+nrSeriku+"',(select q.id from tblqytetet q, tblshtetet s where q.qyteti = '"+cmbQytetet.getSelectedItem()+"' and q.sid = s.id and s.shteti='"+cmbShteti.getSelectedItem()+"'),(select id from tblpozita where pershkrimi = '"+cmbPozita.getSelectedItem()+"'), "+txtPaga.getText()+")";
				pst = conn.prepareStatement(sql);
				pst.execute();
				pst.close();
				
				JOptionPane.showMessageDialog(null, "Registration successfully completed!");
				txtEmri.setText("");
				txtMbiemri.setText("");
				cmbQytetet.setSelectedIndex(0);
				cmbShteti.setSelectedIndex(0);
				txtUser.setText("");
				txtNrPersonal.setText("");
				lblnrPersonal.setText("");
				lblqyteti.setText("");
				lblvendi.setText("");
				txtTel.setText("");
				cmbPozita.setSelectedIndex(0);
				txtEmail.setText("");
				lbldata.setText("");
				lblemri.setText("");
				lblmbiemri.setText("");
				lblvendi.setText("");
				lblpozita.setText("");
				lbltel.setText("");
				lblemail.setText("");
				lbluser.setText("");
				lblpassi.setText("");
				lblKonfirmo.setText("");
				txtPassi.setText("");
				txtKonfirmo.setText("");
				txtEmri.requestFocus();
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Sign Up eng "+ex.getMessage());
			
		}
		NdryshoGjuhen();
	}
	private void NdryshoGjuhen()
	{
		if(Gjuhesia.gjuha=="alb")
		{
			fillCombo();
			lblEmri.setText("Emri:");
			lblGjinia.setText("Gjuha:");
			rdbtnA.setText("A");
			lblNrPersonal.setText("Nr.Personal:");
			lblUsername.setText("Perdoruesi:");
			lblPozita.setText("Pozita:");
			lblMbiemri.setText("Mbiemri:");
			lblVendbanimi_1.setText("Vendbanimi:");
			lblPassword.setText("Fjalekalimi:");
			lblPaga_1.setText("Paga:");
			lblDataELindjes.setText("Data e lindjes:");
			lblQytetiVendbanimit.setText("Qyteti:");
			lblKonfirmoFjalekalimin.setText("Konfirmo Fjalekalimin:");
			btnRegjistrohu.setText("Regjistrohu");
			mntmExit.setText("Dalja");
			lblKthehuPrapa.setText("Kthehu prapa");
			lblPerdoruesi.setText("Perdoruesi:");
			
		}
		else
		{
			fillCombo();
			lblEmri.setText("Name:");
			lblGjinia.setText("Gender:");
			rdbtnA.setText("O");
			lblNrPersonal.setText("Personal ID:");
			lblUsername.setText("User:");
			lblPozita.setText("Position:");
			lblMbiemri.setText("Surname:");
			lblVendbanimi_1.setText("State of residence:");
			lblPassword.setText("Password:");
			lblPaga_1.setText("Salary:");
			lblDataELindjes.setText("Birthdate:");
			lblQytetiVendbanimit.setText("City of Residence:");
			lblKonfirmoFjalekalimin.setText("Confirm Password:");
			btnRegjistrohu.setText("Register");
			lblKthehuPrapa.setText("Back");
			mntmExit.setText("Exit");
			lblPerdoruesi.setText("User:");
			
		}
	}
}
