
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
public class frmBleresi extends JFrame {
	private JLabel lblEmri;
	private JLabel lblMbiemri;
	private JLabel lblVendbanimi;
	private JLabel lblDataELindjes;
	private JLabel lblTel;
	private JLabel lblEmail;
	private JLabel lblGjinia;
	private JLabel opop;
	private JLabel lblvendi;
	private JLabel lbldata;
	private JLabel asfa;
	private JLabel asa;
	private JLabel iiiii;
	private JPanel contentPane;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private JTextField txtEmail;
	private JTextField txtTel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbMashkull;
	private JRadioButton rdbFemer; 
	PreparedStatement pst = null;
	ResultSet res= null;
	Connection conn = null;
	private JRadioButton rdbtnA;
	private JComboBox cmbShteti;
	private JDateChooser txtData;
	private JComboBox cmbQyteti;
	private JLabel lblVendbanimi_1 = new JLabel("Shteti i vendbanimit: ");
	private JLabel lblQytetiVendbanimit;
	private JLabel lblqyteti;
	private JLabel lblNrPersonal;
	private JTextField txtNrPersonal;
	private JLabel lblnrPersonal;
	private JPanel panel;
	private JSeparator separator_5;
	private JButton btnShqip;
	private JButton btnAnglisht;
	private JButton btnRegjistrohu = new JButton("Regjistrohu");
	private JMenuItem mntmExit = new JMenuItem("Mbylle");
	private Integer gjuhaId = 1;
	private JLabel label;
	private JLabel lblPerdoruesi;
	private JLabel lblShitesi;
	private JLabel lblmbiemri;
	private JLabel lblemri;
	private JLabel lblemail;
	private JLabel lbltel;
	private JLabel label_1;
	private JLabel lblKthehu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBleresi frame = new frmBleresi();
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
	public frmBleresi() {
		conn = connectionClass.connectDb();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmBleresi.class.getResource("/imgs/logo1icon1.png")));
		setResizable(false);
		setTitle("Besa Commerce");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1267, 800);
		
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
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(64, 128, 255), 1, true));
		panel.setBounds(12, 250, 1237, 476);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		lblEmri = new JLabel("Emri:");
		lblEmri.setForeground(new Color(0, 0, 51));
		lblEmri.setBounds(12, 18, 109, 44);
		panel.add(lblEmri);
		lblEmri.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblMbiemri = new JLabel("Mbiemri:");
		lblMbiemri.setForeground(new Color(0, 0, 51));
		lblMbiemri.setBounds(391, 18, 155, 44);
		panel.add(lblMbiemri);
		lblMbiemri.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblGjinia = new JLabel("Gjinia:");
		lblGjinia.setForeground(new Color(0, 0, 51));
		lblGjinia.setBounds(12, 149, 109, 44);
		panel.add(lblGjinia);
		lblGjinia.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblDataELindjes = new JLabel("Data e lindjes:");
		lblDataELindjes.setForeground(new Color(0, 0, 51));
		lblDataELindjes.setBounds(813, 18, 155, 44);
		panel.add(lblDataELindjes);
		lblDataELindjes.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataELindjes.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblEmail = new JLabel("E-maili:");
		lblEmail.setForeground(new Color(0, 0, 51));
		lblEmail.setBounds(391, 149, 155, 44);
		panel.add(lblEmail);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblTel = new JLabel("Numri i telefonit:");
		lblTel.setForeground(new Color(0, 0, 51));
		lblTel.setBounds(813, 149, 155, 44);
		panel.add(lblTel);
		lblTel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTel.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtEmri = new JTextField();
		txtEmri.setForeground(new Color(0, 0, 51));
		txtEmri.setOpaque(false);
		txtEmri.setBorder(null);
		txtEmri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
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
		
		lblQytetiVendbanimit = new JLabel("Qyteti i vendbanimit:");
		lblQytetiVendbanimit.setForeground(new Color(0, 0, 51));
		lblQytetiVendbanimit.setBounds(813, 281, 155, 44);
		panel.add(lblQytetiVendbanimit);
		lblQytetiVendbanimit.setHorizontalAlignment(SwingConstants.LEFT);
		lblQytetiVendbanimit.setFont(new Font("Arial", Font.BOLD, 14));
		lblVendbanimi_1.setForeground(new Color(0, 0, 51));
		
		lblVendbanimi_1.setBounds(391, 281, 155, 44);
		panel.add(lblVendbanimi_1);
		lblVendbanimi_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblNrPersonal = new JLabel("Nr. personal:");
		lblNrPersonal.setForeground(new Color(0, 0, 51));
		lblNrPersonal.setBounds(12, 281, 109, 44);
		panel.add(lblNrPersonal);
		lblNrPersonal.setFont(new Font("Arial", Font.BOLD, 14));
		txtEmri.setBounds(123, 18, 211, 47);
		panel.add(txtEmri);
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
		txtMbiemri.setBounds(547, 15, 211, 47);
		panel.add(txtMbiemri);
		txtMbiemri.setFont(new Font("Arial", Font.BOLD, 14));
		txtMbiemri.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(0, 0, 51));
		txtEmail.setBorder(null);
		txtEmail.setOpaque(false);
		txtEmail.setBounds(547, 146, 211, 47);
		panel.add(txtEmail);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 14));
		txtEmail.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setForeground(new Color(0, 0, 51));
		txtTel.setBorder(null);
		txtTel.setOpaque(false);
		txtTel.setBounds(967, 146, 211, 47);
		panel.add(txtTel);
		txtTel.setFont(new Font("Arial", Font.BOLD, 14));
		txtTel.setColumns(10);
		
		rdbMashkull = new JRadioButton("M");
		rdbMashkull.setForeground(new Color(0, 0, 51));
		rdbMashkull.setBackground(new Color(255, 255, 255));
		rdbMashkull.setBounds(123, 147, 61, 47);
		panel.add(rdbMashkull);
		rdbMashkull.setFont(new Font("Arial", Font.BOLD, 14));
		rdbMashkull.setSelected(true);
		buttonGroup.add(rdbMashkull);
		
		rdbFemer = new JRadioButton("F");
		rdbFemer.setForeground(new Color(0, 0, 51));
		rdbFemer.setBackground(new Color(255, 255, 255));
		rdbFemer.setBounds(195, 147, 61, 47);
		panel.add(rdbFemer);
		rdbFemer.setFont(new Font("Arial", Font.BOLD, 14));
		buttonGroup.add(rdbFemer);
		btnRegjistrohu.setForeground(new Color(255, 255, 255));
		
		btnRegjistrohu.setBackground(new Color(153, 204, 255));
		btnRegjistrohu.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/2001877-32.png")));
		btnRegjistrohu.setBounds(487, 416, 263, 47);
		panel.add(btnRegjistrohu);
		btnRegjistrohu.setFont(new Font("Arial", Font.BOLD, 16));
		
		rdbtnA = new JRadioButton("A");
		rdbtnA.setForeground(new Color(0, 0, 51));
		rdbtnA.setBackground(new Color(255, 255, 255));
		rdbtnA.setBounds(260, 147, 61, 47);
		panel.add(rdbtnA);
		buttonGroup.add(rdbtnA);
		rdbtnA.setFont(new Font("Arial", Font.BOLD, 14));
		
		asa = new JLabel("");
		asa.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/gogo.png")));
		asa.setBounds(335, 18, 44, 44);
		panel.add(asa);
		asa.setToolTipText("emer");
		asa.setForeground(Color.RED);
		asa.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		asfa = new JLabel("");
		asfa.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/gogo.png")));
		asfa.setBounds(758, 18, 43, 44);
		panel.add(asfa);
		asfa.setToolTipText("mbiemer");
		asfa.setForeground(Color.RED);
		asfa.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		lblvendi = new JLabel("");
		lblvendi.setBounds(547, 326, 279, 16);
		panel.add(lblvendi);
		lblvendi.setToolTipText("vendbanim");
		lblvendi.setForeground(Color.RED);
		lblvendi.setFont(new Font("Arial", Font.ITALIC, 12));
		
		lbldata = new JLabel("");
		lbldata.setBounds(967, 60, 253, 16);
		panel.add(lbldata);
		lbldata.setForeground(Color.RED);
		lbldata.setFont(new Font("Arial", Font.ITALIC, 12));
		
		opop = new JLabel("");
		opop.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/em.png")));
		opop.setBounds(758, 149, 43, 44);
		panel.add(opop);
		opop.setForeground(Color.RED);
		opop.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		iiiii = new JLabel("");
		iiiii.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/telzi.png")));
		iiiii.setBounds(1176, 149, 44, 44);
		panel.add(iiiii);
		iiiii.setForeground(Color.RED);
		iiiii.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		cmbShteti = new JComboBox();
		cmbShteti.setForeground(new Color(0, 0, 51));
		cmbShteti.setFont(new Font("Arial", Font.BOLD, 14));
		cmbShteti.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				mbushQytetet();
			}
		});
		cmbShteti.setBorder(new LineBorder(new Color(204, 255, 255)));
		cmbShteti.setModel(new DefaultComboBoxModel(new String[] {""}));
		cmbShteti.setBounds(547, 278, 254, 47);
		panel.add(cmbShteti);
		
		
		
		cmbQyteti = new JComboBox();
		cmbQyteti.setForeground(new Color(0, 0, 51));
		cmbQyteti.setFont(new Font("Arial", Font.BOLD, 14));
		cmbQyteti.setBorder(new LineBorder(new Color(204, 255, 255)));
		cmbQyteti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mbushQytetet();	
			}
		});
		
		cmbQyteti.setModel(new DefaultComboBoxModel(new String[] {""}));
		cmbQyteti.setBounds(967, 278, 253, 47);
		panel.add(cmbQyteti);
		
		lblqyteti = new JLabel("");
		lblqyteti.setBounds(967, 326, 253, 16);
		panel.add(lblqyteti);
		lblqyteti.setToolTipText("vendbanim");
		lblqyteti.setForeground(Color.RED);
		lblqyteti.setFont(new Font("Arial", Font.ITALIC, 12));
		
		txtNrPersonal = new JTextField();
		txtNrPersonal.setForeground(new Color(0, 0, 51));
		txtNrPersonal.setBorder(null);
		txtNrPersonal.setOpaque(false);
		txtNrPersonal.setBounds(123, 279, 211, 47);
		panel.add(txtNrPersonal);
		txtNrPersonal.setFont(new Font("Arial", Font.BOLD, 14));
		txtNrPersonal.setColumns(10);
		
		lblnrPersonal = new JLabel("");
		lblnrPersonal.setBounds(123, 327, 256, 16);
		panel.add(lblnrPersonal);
		lblnrPersonal.setToolTipText("emer");
		lblnrPersonal.setForeground(Color.RED);
		lblnrPersonal.setFont(new Font("Arial", Font.ITALIC, 12));
		
		txtData = new JDateChooser();
		txtData.setDateFormatString("dd/MM/yyyy");
		txtData.setBorder(null);
		txtData.setOpaque(false);
		txtData.getCalendarButton().setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/icons8-calendar-20.png")));
		txtData.setBounds(967, 15, 253, 47);
		panel.add(txtData);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(123, 60, 256, 265);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(123, 323, 256, 2);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(547, 61, 254, 132);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(547, 192, 254, 1);
		panel.add(separator_4);
		
		separator_5 = new JSeparator();
		separator_5.setBounds(967, 191, 253, 2);
		panel.add(separator_5);
		
		lblmbiemri = new JLabel("");
		lblmbiemri.setToolTipText("vendbanim");
		lblmbiemri.setForeground(Color.RED);
		lblmbiemri.setFont(new Font("Arial", Font.ITALIC, 12));
		lblmbiemri.setBounds(548, 60, 253, 16);
		panel.add(lblmbiemri);
		
		lblemri = new JLabel("");
		lblemri.setToolTipText("vendbanim");
		lblemri.setForeground(Color.RED);
		lblemri.setFont(new Font("Arial", Font.ITALIC, 12));
		lblemri.setBounds(123, 61, 256, 16);
		panel.add(lblemri);
		
		lblemail = new JLabel("");
		lblemail.setToolTipText("vendbanim");
		lblemail.setForeground(Color.RED);
		lblemail.setFont(new Font("Arial", Font.ITALIC, 12));
		lblemail.setBounds(547, 192, 254, 16);
		panel.add(lblemail);
		
		lbltel = new JLabel("");
		lbltel.setForeground(Color.RED);
		lbltel.setFont(new Font("Arial", Font.ITALIC, 12));
		lbltel.setBounds(969, 192, 251, 16);
		panel.add(lbltel);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/if_Username_372902.png")));
		label_1.setToolTipText("emer");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label_1.setBounds(335, 281, 44, 44);
		panel.add(label_1);
		btnRegjistrohu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
					signUp();
			}
		});
		btnRegjistrohu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					signUp();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
					
				}
			}
		});
		
		lblPerdoruesi = new JLabel("Perdoruesi:");
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setForeground(new Color(0, 0, 51));
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblPerdoruesi.setBackground(Color.WHITE);
		lblPerdoruesi.setBounds(1048, 51, 83, 25);
		contentPane.add(lblPerdoruesi);
		
		btnShqip = new JButton("");
		btnShqip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnShqip.doClick();

				lblKthehu.setText("Kthehu Prapa");
			}
		});
		NdryshoGjuhen();
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha="alb";
				NdryshoGjuhen();
				lblKthehu.setText("Kthehu Prapa");
			}
		});
		btnShqip.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/alb.png")));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setAlignmentX(0.5f);
		btnShqip.setBounds(1189, 13, 25, 25);
		contentPane.add(btnShqip);
		
		btnAnglisht = new JButton("");
		btnAnglisht.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnAnglisht.doClick();
				
					lblKthehu.setText("Back");
			}
		});
		btnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha="eng";
				NdryshoGjuhen();

				lblKthehu.setText("Back");
			}
		});
		btnAnglisht.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/eng.png")));
		btnAnglisht.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnglisht.setAlignmentX(0.5f);
		btnAnglisht.setBounds(1224, 13, 25, 25);
		contentPane.add(btnAnglisht);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(frmBleresi.class.getResource("/imgs/puneLogo1.png")));
		label.setBounds(374, 13, 513, 173);
		contentPane.add(label);
		
		lblShitesi = new JLabel("useri1");
		lblShitesi.setForeground(new Color(0, 0, 51));
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblShitesi.setBackground(Color.WHITE);
		lblShitesi.setBounds(1143, 51, 106, 25);
		lblShitesi.setText(Useri.getEmri() + " "+ Useri.getMbiemri());
		contentPane.add(lblShitesi);
		
		lblKthehu = new JLabel("Kthehu prapa");
		lblKthehu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frmKlientet obj = new frmKlientet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblKthehu.setForeground(new Color(0, 0, 51));
		lblKthehu.setFont(new Font("Arial", Font.BOLD, 14));
		lblKthehu.setBackground(Color.WHITE);
		lblKthehu.setBounds(1143, 89, 106, 25);
		contentPane.add(lblKthehu);
		fillCombo();
		if(Gjuhesia.gjuha.equals("alb"))
			lblKthehu.setText("Kthehu Prapa");
		else
			lblKthehu.setText("Back");
		
	}
	private void fillCombo()
	{
		try
		{
			ArrayList<String> shtetet = new ArrayList<String>();
			
			String query = "select * from tblshtetet";
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
			String sql = "select pershkrimi from tblpozita";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
				String pozite = res.getString("pershkrimi");
				pozita.add(pozite);
			}
			pst.close();
			
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
	
	private void signUp()
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = txtData.getDate();
			String datE = dateFormat.format(date);
			
			String gjinia = "";
			String emrat = "^[a-zA-ZëËÇç]+", email = "^[a-zA-Z0-9]+[\\.]?[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]{2,4}$",tel="^[0-9]+$",user="^[a-zA-Z0-9._]+$";
			int counter = 0;
			int dataPersonit = date.getYear();
			int dataAktuale = new Date().getYear();
			if(txtTel.getText().length()<9)
			{
				counter++;
				iiiii.setText("Shenoni nje numer valid!");
			}
			if((dataAktuale - dataPersonit)<16)
			{
						
				counter++;
				lbldata.setText("Mosha eshte me e vogel se 16!'");
						
			}
			if(cmbShteti.getSelectedIndex() == 0)
			{
				counter++;
				lblvendi.setText("Ju lutem zgjedheni shtetin e vendbanimit");
			}
			if(cmbQyteti.getSelectedIndex() == 0)
			{
				counter++;
				lblqyteti.setText("Ju lutem zgjedheni qytetin e vendbanimit");
			}
			
			if(!Pattern.matches(email, txtEmail.getText()))
			{
				counter++;
				opop.setText("Ju lutem shenoni nje email ne formatin e sakte: user@example.com"); 
			}
			if(!Pattern.matches(tel, txtTel.getText()))
			{
				counter++;
					
				iiiii.setText("Ju lutem shenoni numrin e telit ne formatin e sakte: +383000111");
			}
			
				
			
			if(!Pattern.matches(emrat, txtEmri.getText()))
			{
				asa.setText("Ju lutem shenoni nje emer valid!"); 
				counter++;
							
			}
			
			if(!Pattern.matches(emrat, txtMbiemri.getText()))
			{
				asfa.setText("Ju lutem shenoni nje mbiemer valid!"); 
				counter++;
							
			}
			
			if(txtEmri.getText().equals(""))
			{
				asa.setText("Emri nuk duhet te jete i zbrazet!");
				counter++;
			}
			
			if(txtMbiemri.getText().equals(""))
			{
				asfa.setText("Mbiemri nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtEmail.getText().equals(""))
			{
				opop.setText("Email nuk duhet te jete i zbrazet!");
				counter++;
			}
			
			if(txtTel.getText().equals(""))
			{
				iiiii.setText("Telefoni nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtNrPersonal.getText().equals(""))
			{
				lblnrPersonal.setText("Nr. Personal nuk duhet te jete i zbrazet!");
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

				String sql = "insert into tblBleresit(nrPersonal,emri,mbiemri,gjiniaId,dtLindjes,tel,email,adreseId)"
							+ "values('"+txtNrPersonal.getText()+"','"+txtEmri.getText()+"','"+txtMbiemri.getText()+"',(select id from tblgjinia where pershkrimi = '"+gjinia+"'),'"+datE+"','"
							+ txtTel.getText()+"','"+txtEmail.getText()+"',(select id from tblqytetet where qyteti = '"+cmbQyteti.getSelectedItem()+"' and sid = (select s.id from tblshtetet s where s.shteti = '"+ cmbShteti.getSelectedItem()+"')))";
				pst = conn.prepareStatement(sql);
				pst.execute();
				pst.close();
				
				JOptionPane.showMessageDialog(null, "Regjistrimi u krye me sukses");
				txtEmri.setText("");
				txtMbiemri.setText("");
				cmbQyteti.setSelectedIndex(0);
				cmbShteti.setSelectedIndex(0);
				txtData.setDate(null);
				txtNrPersonal.setText("");
				lblnrPersonal.setText("");
				lblqyteti.setText("");
				lblvendi.setText("");
				txtTel.setText("");
				txtEmail.setText("");
				lbldata.setText("");
				asa.setText("");
				asfa.setText("");
				lblvendi.setText("");
				iiiii.setText("");
				opop.setText("");
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Something Bad happened"+ex.getMessage());
			
		}
	}
	private void NdryshoGjuhen()
	{
		if(Gjuhesia.gjuha=="eng")
		{
			fillCombo();
			lblEmri.setText("First name:");
			lblMbiemri.setText("Last name:");
			lblGjinia.setText("Gender:");
			lblDataELindjes.setText("Birth date:");
			lblEmail.setText("E-mail:");
			lblTel.setText("Phone number:");
			lblVendbanimi_1.setText("Current state:");
			lblQytetiVendbanimit.setText("Current city:");
			lblNrPersonal.setText("Personal no. :");
			lblPerdoruesi.setText("User:");
			//lblKthehu.setText("Back");
			btnRegjistrohu.setText("Sign up");
			mntmExit.setText("Exit");
		}
		else
		{
			fillCombo();
			lblEmri.setText("Emri:");
			lblMbiemri.setText("Mbiemri:");
			lblGjinia.setText("Gjinia:");
			lblDataELindjes.setText("Data e lindjes:");
			lblEmail.setText("E-maili:");
			lblTel.setText("Numri i telefonit:");
			lblVendbanimi_1.setText("Shteti i vendbanimit:");
			lblQytetiVendbanimit.setText("Qyteti i vendbanimit:");
			lblNrPersonal.setText("Nr. personal:");
			lblPerdoruesi.setText("Perdoruesi:");
//			lblKthehu.setText("Back");
			btnRegjistrohu.setText("Regjistrohu");
			mntmExit.setText("Mbylle");
		}
	}
}
