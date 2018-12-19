
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import java.awt.Component;
public class frmBlerjet extends JFrame {
	private String gjinia = "";
	private JPanel contentPane;
	private JTextField txtBarkodi;
	private JTextField txtProdukti;
	private JTextField txtFurnitori;
	private JTextField txtSasia;
	private JTextField txtCbPaTvsh;
	private JTextField txtTotaliBlerje;
	private JTextField txtCsPaTvsh;
	private JLabel lblKthehu;
	private JRadioButton rdbShqip;
	private JRadioButton rdbEng;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	PreparedStatement pst = null;
	ResultSet res = null;
	Connection conn = null;
	private JComboBox cmbShteti;
	private JComboBox cmbQyteti;
	private JComboBox cmbNjMatese;
	private JLabel lblUseri;
	private JLabel lblPerdoruesi;
	private boolean shiko = true;
	private double cmimiBlerjesMeTvsh =0;
	private double cmimiShitjesMeTvsh =0; 
	private double totali = 0;
	private JLabel lblprodukti;
	private JLabel lblbarkodi; 
	private boolean shikoTani = true;
	
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblnjmatese;
	private JLabel lblsasia;
	private JLabel lblfurnitori;
	private JLabel lblshteti;
	private JLabel lblqyteti;
	private JLabel lblCbPaTvsh;
	private JLabel lblCsPaTvsh;
	private String njesia = "";
	frmMenu objMenuNdryshimi = new frmMenu();
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel lblData;
	private JLabel lblDate;
	private JLabel label_1;
	private JLabel label_16;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBlerjet frame = new frmBlerjet();
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
	public frmBlerjet() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmBlerjet.class.getResource("/imgs/logo1icon1.png")));
		setTitle("Besa Commerce");
		
		conn = connectionClass.connectDb();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1273, 861);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlbiMall = new JLabel("");
		lblAlbiMall.setBackground(Color.WHITE);
		lblAlbiMall.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/logo1.png")));
		lblAlbiMall.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbiMall.setFont(new Font("Castellar", Font.BOLD, 40));
		lblAlbiMall.setBounds(0, 0, 495, 201);
		contentPane.add(lblAlbiMall);
		
		JLabel lblBarkodi = new JLabel();
		lblBarkodi.setForeground(new Color(0, 0, 51));
		lblBarkodi.setFont(new Font("Arial", Font.BOLD, 14));
		lblBarkodi.setBounds(12, 306, 138, 41);
		contentPane.add(lblBarkodi);
		
		cmbNjMatese = new JComboBox();
		cmbNjMatese.setForeground(new Color(0, 0, 51));
		cmbNjMatese.setFont(new Font("Arial", Font.BOLD, 14));
		cmbNjMatese.setBounds(162, 493, 248, 47);
		contentPane.add(cmbNjMatese);
		
		
		txtBarkodi = new JTextField();
		txtBarkodi.setForeground(new Color(0, 0, 51));
		txtBarkodi.setOpaque(false);
		txtBarkodi.setBorder(null);
		txtBarkodi.setFont(new Font("Arial", Font.BOLD, 14));
		txtBarkodi.setBounds(162, 306, 211, 44);
		contentPane.add(txtBarkodi);
		txtBarkodi.setColumns(10);
		
		JLabel lblProdukti = new JLabel();
		lblProdukti.setForeground(new Color(0, 0, 51));
		lblProdukti.setFont(new Font("Arial", Font.BOLD, 14));
		lblProdukti.setBounds(12, 399, 138, 44);
		contentPane.add(lblProdukti); 
		
		txtProdukti = new JTextField();
		txtProdukti.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try
				{
					if(!txtProdukti.getText().equals(""))
					{
						StringBuilder str = new StringBuilder(txtProdukti.getText());
						str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
						txtProdukti.setText(str.toString());
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		txtProdukti.setForeground(new Color(0, 0, 51));
		txtProdukti.setOpaque(false);
		txtProdukti.setBorder(null);
		txtProdukti.setFont(new Font("Arial", Font.BOLD, 14));
		txtProdukti.setColumns(10);
		txtProdukti.setBounds(162, 399, 211, 47);
		contentPane.add(txtProdukti);
		
		JLabel lblFurnitori = new JLabel();
		lblFurnitori.setForeground(new Color(0, 0, 51));
		lblFurnitori.setFont(new Font("Arial", Font.BOLD, 14));
		lblFurnitori.setBounds(430, 306, 146, 44);
		contentPane.add(lblFurnitori); 
		
		txtFurnitori = new JTextField();
		txtFurnitori.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try
				{
					if(!txtFurnitori.getText().equals(""))
					{
						StringBuilder str = new StringBuilder(txtFurnitori.getText());
						str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
						txtFurnitori.setText(str.toString());
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		txtFurnitori.setForeground(new Color(0, 0, 51));
		txtFurnitori.setOpaque(false);
		txtFurnitori.setBorder(null);
		txtFurnitori.setFont(new Font("Arial", Font.BOLD, 14));
		txtFurnitori.setColumns(10);
		txtFurnitori.setBounds(588, 303, 242, 47);
		contentPane.add(txtFurnitori);
		
		JLabel lblNjMatese = new JLabel();
		lblNjMatese.setForeground(new Color(0, 0, 51));
		lblNjMatese.setFont(new Font("Arial", Font.BOLD, 14));
		lblNjMatese.setBounds(12, 493, 138, 44);
		contentPane.add(lblNjMatese); 
		
		JLabel lblSasia = new JLabel();
		lblSasia.setForeground(new Color(0, 0, 51));
		lblSasia.setFont(new Font("Arial", Font.BOLD, 14));
		lblSasia.setBounds(12, 600, 138, 41);
		contentPane.add(lblSasia); 
		
		txtSasia = new JTextField();
		txtSasia.setForeground(new Color(0, 0, 51));
		txtSasia.setOpaque(false);
		txtSasia.setBorder(null);
		txtSasia.setFont(new Font("Arial", Font.BOLD, 14));
		txtSasia.setColumns(10);
		txtSasia.setBounds(162, 603, 211, 41);
		contentPane.add(txtSasia);
		
		JLabel lblCmimiIBlerjes = new JLabel();
		lblCmimiIBlerjes.setForeground(new Color(0, 0, 51));
		lblCmimiIBlerjes.setFont(new Font("Arial", Font.BOLD, 14));
		lblCmimiIBlerjes.setBounds(851, 309, 142, 44);
		
		contentPane.add(lblCmimiIBlerjes);
		
		txtCbPaTvsh = new JTextField();
		txtCbPaTvsh.setForeground(new Color(0, 0, 51));
		txtCbPaTvsh.setOpaque(false);
		txtCbPaTvsh.setBorder(null);
		txtCbPaTvsh.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCbPaTvsh.setFont(new Font("Arial", Font.BOLD, 14));
		txtCbPaTvsh.setColumns(10);
		txtCbPaTvsh.setBounds(1005, 309, 211, 41);
		contentPane.add(txtCbPaTvsh);
		
		JLabel lblTotaliPerPagese = new JLabel();
		lblTotaliPerPagese.setForeground(new Color(0, 0, 51));
		lblTotaliPerPagese.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotaliPerPagese.setBounds(851, 496, 142, 44);
		contentPane.add(lblTotaliPerPagese); 
		
		txtTotaliBlerje = new JTextField();
		txtTotaliBlerje.setForeground(new Color(0, 0, 51));
		txtTotaliBlerje.setOpaque(false);
		txtTotaliBlerje.setBorder(null);
		txtTotaliBlerje.setEnabled(false);
		txtTotaliBlerje.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotaliBlerje.setFont(new Font("Arial", Font.BOLD, 14));
		txtTotaliBlerje.setColumns(10);
		txtTotaliBlerje.setBounds(1005, 496, 211, 44);
		contentPane.add(txtTotaliBlerje);
		
		
		JLabel lblQyteti = new JLabel();
		lblQyteti.setForeground(new Color(0, 0, 51));
		lblQyteti.setFont(new Font("Arial", Font.BOLD, 14));
		lblQyteti.setBounds(430, 496, 146, 44);
		contentPane.add(lblQyteti);
		
		cmbQyteti = new JComboBox();
		cmbQyteti.setForeground(new Color(0, 0, 51));
		cmbQyteti.setFont(new Font("Arial", Font.BOLD, 14));
		cmbQyteti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mbushQytetet();
			}
		});
		cmbQyteti.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				mbushQytetet();
			}
		});
		cmbQyteti.setBounds(588, 496, 242, 44);
		contentPane.add(cmbQyteti);
		
		JLabel lblShteti = new JLabel();
		lblShteti.setForeground(new Color(0, 0, 51));
		lblShteti.setFont(new Font("Arial", Font.BOLD, 14));
		lblShteti.setBounds(430, 402, 158, 44);
		contentPane.add(lblShteti);
		
		cmbShteti = new JComboBox();
		cmbShteti.setForeground(new Color(0, 0, 51));
		cmbShteti.setFont(new Font("Arial", Font.BOLD, 14));
		cmbShteti.setBounds(588, 402, 242, 44);
		contentPane.add(cmbShteti);
		
		JLabel lblCmimiIShitjes = new JLabel();
		lblCmimiIShitjes.setForeground(new Color(0, 0, 51));
		lblCmimiIShitjes.setFont(new Font("Arial", Font.BOLD, 14));
		lblCmimiIShitjes.setBounds(851, 402, 142, 44);
		contentPane.add(lblCmimiIShitjes);
		
		txtCsPaTvsh = new JTextField();
		txtCsPaTvsh.setForeground(new Color(0, 0, 51));
		txtCsPaTvsh.setOpaque(false);
		txtCsPaTvsh.setBorder(null);
		txtCsPaTvsh.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCsPaTvsh.setFont(new Font("Arial", Font.BOLD, 14));
		txtCsPaTvsh.setColumns(10);
		txtCsPaTvsh.setBounds(1005, 402, 211, 44);
		contentPane.add(txtCsPaTvsh);
		
		JButton btnShto = new JButton();
		btnShto.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/buy6.png")));
		btnShto.setForeground(new Color(255, 255, 255));
		btnShto.setBackground(new Color(153, 204, 255));
		btnShto.setBorder(null);
		btnShto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
					{
						if(validimiShqip() == false)
							JOptionPane.showMessageDialog(null, "Keni gabime ne formen tuaj!");
						else
						{
							try {
								shtoMallinEkzistues();
								
								
								
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, ex.getMessage());
							}
							
						}
					}
					else
					{
						if(validimiEng() == false)
							JOptionPane.showMessageDialog(null,"You have errors in your form" );
						else
						{
							try {
								shtoMallinEkzistues();
								
								
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, ex.getMessage());
							}
							
						}
					}
				}
			}
			
		});
		btnShto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
				{
					if(validimiShqip() == false)
						JOptionPane.showMessageDialog(null, "Keni gabime ne formen tuaj!");
					else
						shtoMallinEkzistues();
				}
				else
				{
					if(validimiEng() == false)
						JOptionPane.showMessageDialog(null,"You have errors in your form" );
					else
						shtoMallinEkzistues();
				}
			}
		});
		btnShto.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnShto.setBounds(488, 721, 279, 50);
		contentPane.add(btnShto);
		
		rdbShqip = new JRadioButton("");
		rdbShqip.setForeground(Color.WHITE);
		rdbShqip.setOpaque(false);
		rdbShqip.setBackground(new Color(255, 255, 255));
		rdbShqip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
				btnShto.setText("Shto");
				lblCmimiIShitjes.setText("Cmimi i shitjes:");
				lblShteti.setText("Shteti vendbanimit:");
				lblQyteti.setText("Qyteti vendbanimit:");
				lblTotaliPerPagese.setText("Totali per pagese:");
				
				lblBarkodi.setText("Barkodi:");
				lblPerdoruesi.setText("Perdoruesi:");
				lblSasia.setText("Sasia:");
				lblProdukti.setText("Produkti:");
				lblNjMatese.setText("Nj. matese:");
				lblCmimiIBlerjes.setText("Cmimi i blerjes:");
				lblFurnitori.setText("Furnitori:");
				fillCombo();
				lblKthehu.setText("Kthehu prapa");
				Gjuhesia.gjuha= "alb";
				if(shikoTani == false)
					validimiShqip();
				
				objMenuNdryshimi.setState("alb");
				lblData.setText("Data:");
				
			}
		});
		rdbShqip.setSelected(true);
		buttonGroup.add(rdbShqip);
		rdbShqip.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/alb.png")));
		rdbShqip.setBounds(1178, 9, 33, 25);
		contentPane.add(rdbShqip);
		
		rdbEng = new JRadioButton("");
		rdbEng.setForeground(Color.WHITE);
		rdbEng.setOpaque(false);
		rdbEng.setBackground(new Color(255, 255, 255));
		rdbEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				btnShto.setText("Submit");
				lblCmimiIShitjes.setText("Sales price:");
				lblShteti.setText("Country of residence:");
				lblQyteti.setText("City of residence:");
				lblTotaliPerPagese.setText("Total:");
				lblBarkodi.setText("Barcode:");
				lblPerdoruesi.setText("User:");
				lblSasia.setText("Quantity:");
				lblProdukti.setText("Product:");
				lblNjMatese.setText("Unit:");
				lblCmimiIBlerjes.setText("Purchase price:");
				lblFurnitori.setText("Supplier:");
				lblKthehu.setText("Back");
				if(shikoTani == false)
					validimiEng();
				Gjuhesia.gjuha= "eng";
				
				objMenuNdryshimi.setState("eng");
				fillCombo();
				lblData.setText("Date:");
				
				
			}
		});
		buttonGroup.add(rdbEng);
		rdbEng.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/eng.png")));
		rdbEng.setBounds(1206, 9, 33, 25);
		contentPane.add(rdbEng);
		
		lblPerdoruesi = new JLabel();
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setForeground(Color.WHITE);
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 16));
		lblPerdoruesi.setBounds(1013, 43, 93, 31);
		contentPane.add(lblPerdoruesi);
		
		lblUseri = new JLabel("");
		lblUseri.setHorizontalAlignment(SwingConstants.LEFT);
		lblUseri.setForeground(Color.WHITE);
		lblUseri.setFont(new Font("Arial", Font.BOLD, 16));
		lblUseri.setBounds(1118, 43, 121, 31);
		contentPane.add(lblUseri);
		
		lblbarkodi = new JLabel("");
		lblbarkodi.setForeground(Color.RED);
		lblbarkodi.setFont(new Font("Arial", Font.ITALIC, 9));
		lblbarkodi.setBounds(162, 350, 248, 16);
		contentPane.add(lblbarkodi);
		
		lblprodukti = new JLabel("");
		lblprodukti.setFont(new Font("Arial", Font.ITALIC, 9));
		lblprodukti.setForeground(Color.RED);
		lblprodukti.setBounds(162, 447, 248, 16);
		contentPane.add(lblprodukti);
		
		lblnjmatese = new JLabel("");
		lblnjmatese.setForeground(Color.RED);
		lblnjmatese.setFont(new Font("Times New Roman", Font.ITALIC, 9));
		lblnjmatese.setBounds(162, 542, 248, 16);
		contentPane.add(lblnjmatese);
		
		lblsasia = new JLabel("");
		lblsasia.setForeground(Color.RED);
		lblsasia.setFont(new Font("Arial", Font.ITALIC, 9));
		lblsasia.setBounds(162, 645, 248, 16);
		contentPane.add(lblsasia);
		
		lblfurnitori = new JLabel("");
		lblfurnitori.setForeground(Color.RED);
		lblfurnitori.setFont(new Font("Arial", Font.ITALIC, 9));
		lblfurnitori.setBounds(588, 350, 242, 16);
		contentPane.add(lblfurnitori);
		
		lblshteti = new JLabel("");
		lblshteti.setForeground(Color.RED);
		lblshteti.setFont(new Font("Arial", Font.ITALIC, 9));
		lblshteti.setBounds(588, 447, 242, 16);
		contentPane.add(lblshteti);
		
		lblqyteti = new JLabel("");
		lblqyteti.setForeground(Color.RED);
		lblqyteti.setFont(new Font("Arial", Font.ITALIC, 9));
		lblqyteti.setBounds(588, 542, 242, 16);
		contentPane.add(lblqyteti);
		
		lblCbPaTvsh = new JLabel("");
		lblCbPaTvsh.setForeground(Color.RED);
		lblCbPaTvsh.setFont(new Font("Arial", Font.ITALIC, 9));
		lblCbPaTvsh.setBounds(1010, 350, 240, 16);
		contentPane.add(lblCbPaTvsh);
		
		lblCsPaTvsh = new JLabel("");
		lblCsPaTvsh.setForeground(Color.RED);
		lblCsPaTvsh.setFont(new Font("Arial", Font.ITALIC, 9));
		lblCsPaTvsh.setBounds(1010, 447, 240, 16);
		contentPane.add(lblCsPaTvsh);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(162, 348, 248, 293);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(162, 444, 248, 197);
		contentPane.add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(162, 639, 248, 2);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(588, 344, 242, 6);
		contentPane.add(separator_4);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(1005, 346, 245, 191);
		contentPane.add(separator_6);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(1005, 534, 245, 3);
		contentPane.add(separator_8);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(1005, 443, 245, 94);
		contentPane.add(separator_9);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/euro.png")));
		label_2.setBounds(1217, 317, 33, 33);
		contentPane.add(label_2);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/euro.png")));
		label_4.setBounds(1217, 507, 33, 33);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/euro.png")));
		label_5.setBounds(1217, 416, 33, 33);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/bill.png")));
		label_6.setBounds(377, 608, 33, 33);
		contentPane.add(label_6);
		
		label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/supply.png")));
		label_8.setBounds(797, 314, 33, 33);
		contentPane.add(label_8);
		
		label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/barcode.png")));
		label_9.setBounds(377, 317, 33, 33);
		contentPane.add(label_9);
		
		label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(frmBlerjet.class.getResource("/imgs/if_home_309062.png")));
		label_10.setBounds(377, 413, 33, 33);
		contentPane.add(label_10);
		
		lblKthehu = new JLabel();
		lblKthehu.setForeground(Color.WHITE);
		lblKthehu.setHorizontalAlignment(SwingConstants.LEFT);
		lblKthehu.setFont(new Font("Arial", Font.BOLD, 16));
		lblKthehu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
				frmMenu obj = new frmMenu();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblKthehu.setBounds(1118, 100, 132, 29);
		contentPane.add(lblKthehu);
		
		lblData = new JLabel();
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setFont(new Font("Arial", Font.BOLD, 16));
		lblData.setForeground(Color.WHITE);
		lblData.setBounds(1555, 66, 76, 33);
		contentPane.add(lblData);
		
		lblDate = new JLabel("");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Arial", Font.BOLD, 16));
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(1118, 70, 121, 33);
		contentPane.add(lblDate);
		fillCombo();
		if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
		{
			btnShto.setText("Shto");
			lblCmimiIShitjes.setText("Cmimi i shitjes:");
			lblShteti.setText("Shteti vendbanimit:");
			lblQyteti.setText("Qyteti vendbanimit:");
			lblTotaliPerPagese.setText("Totali per pagese:");
			lblBarkodi.setText("Barkodi:");
			lblPerdoruesi.setText("Perdoruesi:");
			lblKthehu.setText("Kthehu prapa");
			lblSasia.setText("Sasia:");
			lblProdukti.setText("Produkti:");
			lblNjMatese.setText("Nj. matese:");
			lblCmimiIBlerjes.setText("Cmimi i blerjes:");
			lblFurnitori.setText("Furnitori:");
			lblData.setText("Data:");
			
		}
		else
		{
			lblData.setText("Date:");
			btnShto.setText("Submit");
			lblCmimiIShitjes.setText("Sales price:");
			lblShteti.setText("Country of residence:");
			lblQyteti.setText("City of residence:");
			lblTotaliPerPagese.setText("Total:");
			lblBarkodi.setText("Barcode:");
			lblPerdoruesi.setText("User:");
			lblSasia.setText("Quantity:");
			lblProdukti.setText("Product:");
			lblNjMatese.setText("Unit:");
			lblKthehu.setText("Back");
			lblCmimiIBlerjes.setText("Purchase price:");
			lblFurnitori.setText("Supplier:");
		}
		java.util.Date data = new java.util.Date();
		DateFormat dtForm = new SimpleDateFormat("dd/MM/yyyy");
		String dt = dtForm.format(data);
		lblDate.setText(dt);
		
		label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(0, 0, 102));
		label_1.setBounds(0, 239, 1776, 3);
		contentPane.add(label_1);
		
		label_16 = new JLabel("");
		label_16.setOpaque(true);
		label_16.setBackground(new Color(153, 204, 255));
		label_16.setBounds(0, 0, 1776, 242);
		contentPane.add(label_16);
		
	}
	private void fillCombo()
	{
		try
		{
			ArrayList<String> shtetet = new ArrayList<String>();
			String query ="select shteti from tblshtetet ";
			
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
			
			
			ArrayList<String> njMatese = new ArrayList<String>();
			njMatese.add("");
			String sql = "select pershkrimi from tblnjmatese order by id";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
				String pozite = res.getString("pershkrimi");
				
				njMatese.add(pozite);
			}
			pst.close();
			cmbNjMatese.removeAllItems();
			String[] njm = njMatese.toArray(new String[njMatese.size()]);
			
			cmbNjMatese.setModel(new DefaultComboBoxModel(njm));
			
			
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		String emriMbiemri = Useri.getEmri() + " " + Useri.getMbiemri();
		lblUseri.setText(emriMbiemri);
	}
	private void mbushQytetet()
	{
		
		try
		{
			cmbQyteti.removeAllItems();
			ArrayList<String> qytetet = new ArrayList<String>();
			
			
			
			String query = "select q.qyteti from tblqytetet q, tblshtetet s where q.sid =s.id and s.shteti ='"+cmbShteti.getSelectedItem()+"'";
			qytetet.add("");
			pst = conn.prepareStatement(query);
			res = pst.executeQuery();
			
			while(res.next())
			{
				String qytet = res.getString("qyteti");
				qytetet.add(qytet);
			}
			pst.close();
			
			cmbQyteti.setModel(new DefaultComboBoxModel(qytetet.toArray()));
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	private void shtoMallinEkzistues()
	{
		try {

			njesia = cmbNjMatese.getSelectedItem().toString();

			
	
			//qitu kem bo dicka ndryshim kemi shtu string format
			
			
			
			totali = Double.parseDouble(txtCbPaTvsh.getText())* Double.parseDouble(txtSasia.getText());
			
			txtTotaliBlerje.setText(String.format("%.2f",totali));
			
			
			String sql = "select count(id) from tblRegjistrimimallit where barkodi = '"+txtBarkodi.getText()+"'";
			String idProduktit = "",sasia ="";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
				int i = res.getInt("count(id)");
				if(i==0)
				{
					shiko = false;
					shtoMallinERi();
				}
				else
				{
					sql = "select id, sasia from tblRegjistrimimallit where barkodi = '"+txtBarkodi.getText()+"'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					while(res.next())
					{
						idProduktit = res.getString("id");
						sasia = res.getString("sasia");
					}
					
					sql = "update tblregjistrimimallit set sasia = "+(Double.parseDouble(sasia) + Double.parseDouble(txtSasia.getText()))+" where id = "+Integer.parseInt(idProduktit);
					pst = conn.prepareStatement(sql);
					pst.executeUpdate();
					sql = "select count(id) from tblfurnitoret where emriFurnitorit ='"+txtFurnitori.getText()+"'";
					
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					while(res.next())
					{
						if(res.getInt("count(id)")==0)
						{
							
							shiko = false;

							sql = "insert into tblfurnitoret(emriFurnitorit,adreseId) values('"+txtFurnitori.getText()+"',(select id from tblqytetet where qyteti = '"+cmbQyteti.getSelectedItem()+"'))";
							
							pst = conn.prepareStatement(sql);
							pst.executeUpdate();
							sql = "insert into tblfurnizonproduktin(furnitoreId,produktId) values((select id from tblfurnitoret where emriFurnitorit = '"+txtFurnitori.getText()+"'),(select id from tblregjistrimimallit where barkodi = '"+txtBarkodi.getText()+"'))";
							pst = conn.prepareStatement(sql);
							pst.executeUpdate();
							sql = "update tblcmimet  set cmimiShitjes = "+Double.parseDouble(txtCsPaTvsh.getText())+" where produktetId = "+Integer.parseInt(idProduktit);
							pst = conn.prepareStatement(sql);
							pst.executeUpdate();
							
						}
						else
						{

							sql = "update tblcmimet  set cmimiShitjes = "+Double.parseDouble(txtCsPaTvsh.getText())+" where produktetId = "+Integer.parseInt(idProduktit);
							pst = conn.prepareStatement(sql);
							pst.executeUpdate();
							
						}
					}
				}
			}
			
			
			sql = "insert into tblmalliBlere(barkodi,emriProduktit,sasia,njMateseId,furnitoreId,cmimiBlerjes,cmimiTotalBlerjes,dtBlerjes,nrPersonalStafit)"
					+ "values('"+txtBarkodi.getText()+"','"+txtProdukti.getText()+"',"+Double.parseDouble(txtSasia.getText())+",(select id from tblnjmatese where pershkrimi = '"+cmbNjMatese.getSelectedItem()+"'),("
									+"select id from tblfurnitoret where emriFurnitorit = '"+txtFurnitori.getText()+"'),"+Double.parseDouble(txtCbPaTvsh.getText())+","+Double.parseDouble(txtTotaliBlerje.getText())+",curdate(),'"+Useri.getNrPersonal()+"')";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
			
			
			if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
				JOptionPane.showMessageDialog(null,"Regjistrimi u krye me sukses");
			else
				JOptionPane.showMessageDialog(null,"The registration has been successfully completed");
			txtBarkodi.setText("");
			txtProdukti.setText("");
			txtSasia.setText("");
			txtCbPaTvsh.setText("");
			txtCsPaTvsh.setText("");
			txtFurnitori.setText("");
			txtTotaliBlerje.setText("");
			cmbQyteti.setSelectedIndex(0);
			cmbShteti.setSelectedIndex(0);
			cmbNjMatese.setSelectedIndex(0);
			
				
		} 
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		
	}
	private void shtoMallinERi()
	{
		try
		{

			
			

			
			
			String sql = "insert into tblregjistrimiMallit(barkodi,emriProduktit,sasia,njmateseId)"
					+ "values('"+txtBarkodi.getText()+"','"+txtProdukti.getText()+"',"+Double.parseDouble(txtSasia.getText())+","
							+ "(select id from tblnjmatese where pershkrimi = '"+cmbNjMatese.getSelectedItem()+"'))";
			

			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			sql = "select count(id) from tblfurnitoret where emriFurnitorit ='"+txtFurnitori.getText()+"'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
				if(res.getInt("count(id)")==0)
				{
					shiko = false;
					
					sql = "insert into tblfurnitoret(emriFurnitorit,adreseId) values('"+txtFurnitori.getText()+"',(select id from tblqytetet where qyteti = '"+cmbQyteti.getSelectedItem()
							+"' and sid = (select id from tblshtetet where shteti = '"+cmbShteti.getSelectedItem()+"' )))";
					
					pst = conn.prepareStatement(sql);
					pst.executeUpdate();
					
					
				}
			}
			sql = "insert into tblfurnizonproduktin(furnitoreId,produktId) values((select id from tblfurnitoret where emriFurnitorit = '"+txtFurnitori.getText()+"'),(select id from tblregjistrimimallit where barkodi = '"+txtBarkodi.getText()+"'))";

			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			sql = "insert into tblcmimet(cmimiShitjes,produktetId) values("+Double.parseDouble(txtCsPaTvsh.getText())+",(select id from tblregjistrimimallit  where barkodi ='"+txtBarkodi.getText()+"'))";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

			pst.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	private boolean validimiShqip()
	{
		try
		{
			
			
			String gjinia = "";
			Pattern cmim = Pattern.compile("^[0-9]+[\\.]?[0-9]*$");
			String produktet = "^[a-zA-ZëËÇç\\Ø0-9\\.\\/\\-\\s]+$", emrat = "^[a-zA-ZëËÇç]+$",sasia="^[0-9]+[.]?[0-9]*$",brendi = "^[a-zA-Z]+[\\s]?[a-zA-Z]+$",barkod = "^[a-zA-Z0-9]+$",furnitor = "^[a-zA-Z0-9ëËÇç\\s\\.]+$";
			int counter = 0;
			
			
			
			if(cmbShteti.getSelectedIndex() == 0)
			{
				counter++;
				lblshteti.setText("Ju lutem zgjedheni shtetin e vendbanimit!");
			}
			if(cmbQyteti.getSelectedIndex() == 0)
			{
				counter++;
				lblqyteti.setText("Ju lutem zgjedheni qytetin e vendbanimit!");
			}
			if(cmbNjMatese.getSelectedIndex() == 0)
			{
				counter++;
				lblnjmatese.setText("Ju lutem zgjedheni njesine matese!");
			}
			
					
			if(!Pattern.matches(barkod, txtBarkodi.getText()))
			{
				lblbarkodi.setText("Ju lutem shenoni nje barkod valid, nuk lejohet perdorimi i karaktereve speciale!");
				counter++;
			}
			
			if(!Pattern.matches(produktet, txtProdukti.getText()))
			{
				lblprodukti.setText("Ju lutem shenoni nje produkt valid!"); 
				counter++;
							
			}
			if(!Pattern.matches(sasia, txtSasia.getText()))
			{
				lblprodukti.setText("Sasia nuk eshte valide!"); 
				counter++;
							
			}
			
			if(!Pattern.matches(furnitor, txtFurnitori.getText()))
			{
				lblfurnitori.setText("Ju lutem shenoni nje furnitor valid!"); 
				counter++;
							
			}
			
			if(!cmim.matcher(txtCbPaTvsh.getText()).matches())
			{
				lblCbPaTvsh.setText("Cmimi i blerjes nuk eshte valid!"); 
				counter++;
							
			}
			if(!cmim.matcher(txtCsPaTvsh.getText()).matches())
			{
				lblCbPaTvsh.setText("Cmimi i shitjes nuk eshte valid!"); 
				counter++;
							
			}
			
			if(txtBarkodi.getText().equals(""))
			{
				lblbarkodi.setText("Barkodi nuk duhet te jete i zbrazet!");
				counter++;
			}
			
			if(txtProdukti.getText().equals(""))
			{
				lblprodukti.setText("Produkti nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtCbPaTvsh.getText().equals(""))
			{
				lblCbPaTvsh.setText("Cmimi i blerjes nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtCsPaTvsh.getText().equals(""))
			{
				lblCsPaTvsh.setText("Cmimi i shitjes nuk duhet te jete i zbrazet!");
				counter++;
			}
			
			
			
			if(txtSasia.getText().equals(""))
			{
				lblsasia.setText("Sasia nuk duhet te jete e zbrazet!");
				counter++;
			}
			if(txtFurnitori.getText().equals(""))
			{
				lblfurnitori.setText("Furnitori nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(counter != 0)
			{
				shikoTani = false;
				return false;
			}
			
			else
			{

				
				
				lblCsPaTvsh.setText("");
				lblCsPaTvsh.setText("");
				lblqyteti.setText("");
				lblshteti.setText("");
				lblprodukti.setText("");
				lblfurnitori.setText("");
				lblsasia.setText("");
				lblnjmatese.setText("");
				lblbarkodi.setText("");
				
				return true;
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage());
			
		}
		return false;
	}
	private boolean validimiEng()
	{
		try
		{
			
			
			Pattern cmim =Pattern.compile("^[0-9]+[\\.]?[0-9]*$");
			String produktet = "^[a-zA-ZëËÇç\\Ø0-9\\.\\/\\-\\s]+$",emrat = "^[a-zA-Z]+$",sasia="^[0-9]+$",barkod = "^[a-zA-Z0-9]+$",furnitor = "^[a-zA-Z]+[\\s]?[a-zA-Z]+$";
			int counter = 0;
			
			
			
			if(cmbShteti.getSelectedIndex() == 0)
			{
				counter++;
				lblshteti.setText("Select country of residence!");
			}
			if(cmbQyteti.getSelectedIndex() == 0)
			{
				counter++;
				lblqyteti.setText("Select city of residence!");
			}
			if(cmbNjMatese.getSelectedIndex() == 0)
			{
				counter++;
				lblnjmatese.setText("Select unit!");
			}
			
					
			if(!Pattern.matches(barkod, txtBarkodi.getText()))
			{
				lblbarkodi.setText("Enter a valid barcode!");
				counter++;
			}
			
			if(!Pattern.matches(produktet, txtProdukti.getText()))
			{
				lblprodukti.setText("Enter a valid product!"); 
				counter++;
							
			}
			if(!Pattern.matches(sasia, txtSasia.getText()))
			{
				lblprodukti.setText("Enter a valid quantity!"); 
				counter++;
							
			}
			
			if(!Pattern.matches(furnitor, txtFurnitori.getText()))
			{
				lblfurnitori.setText("Enter a valid supplier!"); 
				counter++;
							
			}
			
			
			if(!cmim.matcher(txtCbPaTvsh.getText()).matches())
			{
				lblCbPaTvsh.setText("Enter a valid purchase cost!"); 
				counter++;
							
			}
			if(!cmim.matcher(txtCsPaTvsh.getText()).matches())
			{
				lblCbPaTvsh.setText("Enter a valid sale price!"); 
				counter++;
							
			}
			
			if(txtBarkodi.getText().equals(""))
			{
				lblbarkodi.setText("Barcode should not be empty!");
				counter++;
			}
			
			if(txtProdukti.getText().equals(""))
			{
				lblprodukti.setText("Product should not be empty!");
				counter++;
			}
			if(txtCbPaTvsh.getText().equals(""))
			{
				lblCbPaTvsh.setText("Purchase cost should not be empty!");
				counter++;
			}
			if(txtCsPaTvsh.getText().equals(""))
			{
				lblCsPaTvsh.setText("Sale price should not be empty!");
				counter++;
			}
			
			
			
			if(txtSasia.getText().equals(""))
			{
				lblsasia.setText("Quantity should not be empty!");
				counter++;
			}
			if(txtFurnitori.getText().equals(""))
			{
				lblfurnitori.setText("Supplier should not be empty!");
				counter++;
			}
			if(counter != 0)
			{
				shikoTani = false;
				return false;
			}
			
			else
			{

				
				
				lblCsPaTvsh.setText("");
				lblCsPaTvsh.setText("");
				lblqyteti.setText("");
				lblshteti.setText("");
				lblprodukti.setText("");
				lblfurnitori.setText("");
				lblsasia.setText("");
				lblnjmatese.setText("");
				lblbarkodi.setText("");
				
				return true;
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			
		}
		return false;
	}
}
