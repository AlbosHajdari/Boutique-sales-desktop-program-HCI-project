import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.awt.image.*;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class frmLogin extends JFrame {

	private JPanel contentPane;
	//objekti per lidhje
	Connection conn=null;
		//objekti per vendosje te rezultatit
	ResultSet res=null;
		//objekti per query
	PreparedStatement pst=null;
	private JTextField txtPerdoruesi;
	private JPasswordField txtFjalekalimi;
	private JButton btnKycu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel label_2;
	private JRadioButton rdbtnAlb;
	private JRadioButton rdbtnEng;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
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
	public frmLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				txtPerdoruesi.requestFocus();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmLogin.class.getResource("/imgs/logoPune.png")));
		setBackground(Color.WHITE);
		setResizable(false);
		conn = connectionClass.connectDb();
		setTitle("Besa Commerce");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(frmLogin.class.getResource("/imgs/logo6.png.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 649);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel();
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(103, 237, 122, 33);
		contentPane.add(label);
		
		txtPerdoruesi = new JTextField();
		txtPerdoruesi.setForeground(new Color(0, 0, 51));
		txtPerdoruesi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				label.setText("");
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtPerdoruesi.getText().length()==0)
					if(Gjuhesia.gjuha.equals("alb"))
						label.setText("Perdoruesi:");
					else
						label.setText("Username:");
			}
		});
		
		txtPerdoruesi.setBorder(null);
		txtPerdoruesi.setOpaque(false);
		txtPerdoruesi.setToolTipText("");
		txtPerdoruesi.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPerdoruesi.setColumns(10);
		txtPerdoruesi.setBackground(new Color(240, 255, 255));
		txtPerdoruesi.setBounds(103, 232, 308, 38);
		contentPane.add(txtPerdoruesi);
		
		label_1 = new JLabel();
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setBounds(103, 360, 122, 33);
		contentPane.add(label_1);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setForeground(new Color(0, 0, 51));
		txtFjalekalimi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_1.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtFjalekalimi.getText().length()==0)
					if(Gjuhesia.gjuha.equals("alb"))
						label_1.setText("Fjalëkalimi:");
					else
						label_1.setText("Password:");
			}
		});
		txtFjalekalimi.setOpaque(false);
		txtFjalekalimi.setBorder(null);
		txtFjalekalimi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
						loginAlb();
					else
						loginEng();
				}
			}
		});
		txtFjalekalimi.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFjalekalimi.setColumns(10);
		txtFjalekalimi.setBackground(new Color(240, 255, 255));
		txtFjalekalimi.setBounds(103, 355, 308, 38);
		contentPane.add(txtFjalekalimi);
		
		btnKycu = new JButton();
		btnKycu.setBorder(null);
		btnKycu.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/lck.png")));
		btnKycu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
						loginAlb();
					else
						loginEng();
				}
			}
		});
		btnKycu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
					loginAlb();
				else
					loginEng();
			}
		});
		btnKycu.setForeground(Color.WHITE);
		btnKycu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnKycu.setBackground(new Color(135, 206, 250));
		btnKycu.setBounds(96, 449, 271, 53);
		contentPane.add(btnKycu);
		
		label_2 = new JLabel();
		label_2.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		label_2.setBackground(Color.WHITE);
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmForgotPass obj = new frmForgotPass();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_2.setForeground(Color.BLUE);
				label_2.setFont(new Font("Times New Roman",Font.BOLD+Font.ITALIC,13));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				label_2.setForeground(Color.BLACK);
				label_2.setFont(new Font("Times New Roman",Font.ITALIC,13));
			}
		});
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(new Color(0, 0, 51));
		label_2.setBounds(126, 502, 271, 21);
		contentPane.add(label_2);
		
		rdbtnAlb = new JRadioButton("");
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setBackground(new Color(0, 0, 128));
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "alb";
				label.setText("Perdoruesi:");
				label_1.setText("Fjal\u00EBkalimi:");
				btnKycu.setText("Ky\u00E7u");
				label_2.setText("Keni harruar fjalekalimin?"); 
				if(txtPerdoruesi.getText().length()>0)
					label.setText("");
				if(txtFjalekalimi.getText().length()>0)
					label_1.setText("");
			}
		});
		rdbtnAlb.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setSelected(true);
		buttonGroup.add(rdbtnAlb);
		rdbtnAlb.setBounds(398, 9, 27, 25);
		contentPane.add(rdbtnAlb);
		
		rdbtnEng = new JRadioButton("");
		rdbtnEng.setOpaque(false);
		rdbtnEng.setBackground(new Color(0, 0, 128));
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "eng";
				label.setText("Username:");
				label_1.setText("Password:");
				btnKycu.setText("Sign in");
				label_2.setText("Forgot password?");
				if(txtPerdoruesi.getText().length()>0)
					label.setText("");
				if(txtFjalekalimi.getText().length()>0)
					label_1.setText("");
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/eng.png")));
		buttonGroup.add(rdbtnEng);
		rdbtnEng.setBounds(422, 9, 27, 25);
		contentPane.add(rdbtnEng);
		
//		BufferedImage tjeter = null;
//		try {
//		    tjeter = ImageIO.read(frmLogin.class.getResource("/imgs/albi mall1.jpg"));
//		    
//		} catch (Exception ex) {
//		    JOptionPane.showMessageDialog(null,ex.getMessage());
//		}
//		Image dtjeter = tjeter.getScaledInstance(91, 104,
//		        Image.SCALE_SMOOTH);
//		ImageIcon imTjeter = new ImageIcon(dtjeter);
//		
		JSeparator spr = new JSeparator();
		spr.setBounds(64, 268, 347, 2);
		contentPane.add(spr);
		
		JSeparator spr1 = new JSeparator();
		spr1.setBounds(64, 391, 347, 2);
		contentPane.add(spr1);
		
		label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/logo1.png")));
		label_4.setBounds(0, 9, 464, 171);
		contentPane.add(label_4);
		
		label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/lock5.png")));
		label_5.setBounds(52, 348, 50, 45);
		contentPane.add(label_5);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/tag.png")));
		label_3.setBounds(52, 232, 50, 38);
		contentPane.add(label_3);
		
		JLabel label_6 = new JLabel("");
		label_6.setOpaque(true);
		label_6.setBackground(new Color(135, 206, 255));
		label_6.setBounds(0, 536, 464, 106);
		contentPane.add(label_6);
		if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
		{
			label.setText("Perdoruesi:");
			label_1.setText("Fjal\u00EBkalimi:");
			btnKycu.setText("Ky\u00E7u");
			label_2.setText("Keni harruar fjalekalimin?");
		}
		else
		{
			label.setText("Username:");
			label_1.setText("Password:");
			btnKycu.setText("Sign in");
			label_2.setText("Forgot password?");
		}
	}
	
	public void loginAlb()
	{
		try
		{
			String sql = "select s.nrPersonal,s.emri,s.mbiemri,s.passwordi,s.salt,p.pershkrimi from tblStafi s,tblpozita p where s.poziteid = p.id and s.username = '"+txtPerdoruesi.getText()+"'";
			String passDb = "",salt ="",pozita = "",nrPersonal ="",emri="",mbiemri="";
			String welcome = "Miresevini!";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next())
			{
				nrPersonal = res.getString("nrPersonal");
				emri = res.getString("emri");
				mbiemri = res.getString("mbiemri");
				passDb = res.getString("passwordi");
				salt = res.getString("salt");
				pozita = res.getString("pershkrimi");
			}
			pst.close();
			String pass = txtFjalekalimi.getText() + salt;
			pass = Encryption.SHA1(pass);
			if(pass.equalsIgnoreCase(passDb))
			{
				Useri.setNrPersonal(nrPersonal);
				Useri.setEmri(emri);
				Useri.setMbiemri(mbiemri);
				
				JOptionPane.showMessageDialog(null, welcome);
				if(pozita.equalsIgnoreCase("punetor"))
				{
					frmShitja obj = new frmShitja();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
				else
				{
					
					frmMenu obj = new frmMenu();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
					
				}
				
				
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Perdoruesi ose fjalekalimi eshte gabim!");
				
				txtPerdoruesi.setText("");
				txtFjalekalimi.setText("");
				txtPerdoruesi.requestFocus();
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
	}
	public void loginEng()
	{
		try
		{
			String sql = "select s.nrPersonal,s.emri,s.mbiemri,s.passwordi,s.salt,p.pershkrimi from tblStafi s,tblpozita p where s.poziteid = p.id and s.username = '"+txtPerdoruesi.getText()+"'";
					
			String passDb = "",salt ="",pozita = "",nrPersonal ="",emri="",mbiemri="";
			String welcome = "Welcome!";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next())
			{
				nrPersonal = res.getString("nrPersonal");
				emri = res.getString("emri");
				mbiemri = res.getString("mbiemri");
				passDb = res.getString("passwordi");
				salt = res.getString("salt");
				pozita = res.getString("pershkrimi");
			}
			pst.close();
			String pass = txtFjalekalimi.getText() + salt;
			pass = Encryption.SHA1(pass);
			if(pass.equalsIgnoreCase(passDb))
			{
				Useri.setNrPersonal(nrPersonal);
				Useri.setEmri(emri);
				Useri.setMbiemri(mbiemri);
				
				JOptionPane.showMessageDialog(null, welcome);
				
				if(pozita.equalsIgnoreCase("Worker") || pozita.equalsIgnoreCase("Punetor"))
				{
					frmShitja obj = new frmShitja();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
				else
				{
					frmMenu obj = new frmMenu();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
				
				
				dispose();
			}
			else
			{
				
				JOptionPane.showMessageDialog(null, "Username or password is incorrect");
				
				txtPerdoruesi.setText("");
				txtFjalekalimi.setText("");
				txtPerdoruesi.requestFocus();
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
	}
}
