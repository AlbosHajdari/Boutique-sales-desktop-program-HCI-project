import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import java.awt.image.*;
import java.awt.Color;
import java.sql.*;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
public class frmMenu extends JFrame {

	private JPanel contentPane;
	private JLabel lblUseri =  new JLabel("");
	PreparedStatement pst = null;
	Connection conn = null;
	ResultSet res = null;
	private JMenuBar menuBar;
	private JLabel lblKlientat;
	private JRadioButton rdbtnAlb;
	private JRadioButton rdbtnEng;
	private JLabel lblDalja;
	private JLabel lblshtostaf;
	private JLabel lblData;
	private JLabel lblstafi;
	private JMenuItem mntmStafi;
	private JMenuItem mntmShtoAnetareTe;
	private JMenu mnShitja;
	private static String pozita= "";
	private JMenuItem mntmShitja;
	private JMenu mnProduktet;
	private JMenuItem mntmLibriBlerjes;
	private JMenuItem mntmRegjistrimiIMallit;
	private JMenuItem mntmLibriShitjes;
	private JMenuItem mntmShtoMallTe;
	private JMenu mnRaportet;
	private JLabel lblLibriIBlerjes;
	private JLabel lblShtoProdukte;
	private JLabel lblShtoStaf;
	private JLabel lblShitja;
	private JMenuItem mntmFurnizuesit;
	private JMenu mnDefinicionet; 
	private JMenuItem mntmShtoKlientTe;
	private JLabel lblLibriIShitjes;
	private JMenuItem mntmExit;
	private JLabel lblStafi;
	private JLabel lblShtoKlient;
	private JMenu mnDalja;
	private JLabel lblPerdoruesi;
	private JLabel lblfurnizohu;
	private JMenuItem mntmKlientet;
	
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenu frame = new frmMenu();
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
	public frmMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMenu.class.getResource("/imgs/logo1icon1.png")));
		conn = connectionClass.connectDb();
		setResizable(false);

		setTitle("Besa Commerce");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1226, 924);
		
		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 51));
		setJMenuBar(menuBar);
		
		mnProduktet = new JMenu();
		mnProduktet.setForeground(new Color(0, 0, 51));
		menuBar.add(mnProduktet);
		
		mntmRegjistrimiIMallit = new JMenuItem();
		mntmRegjistrimiIMallit.setForeground(new Color(0, 0, 51));
		mntmRegjistrimiIMallit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegjistrimiMallit obj = new frmRegjistrimiMallit();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		mntmRegjistrimiIMallit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnProduktet.add(mntmRegjistrimiIMallit);
		
		mntmShtoMallTe = new JMenuItem();
		mntmShtoMallTe.setForeground(new Color(0, 0, 51));
		mntmShtoMallTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBlerjet obj = new frmBlerjet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		mntmShtoMallTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnProduktet.add(mntmShtoMallTe);
		
		mnRaportet = new JMenu();
		mnRaportet.setForeground(new Color(0, 0, 51));
		menuBar.add(mnRaportet);
		
		mntmLibriBlerjes = new JMenuItem();
		mntmLibriBlerjes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmRaportet obj = new frmRaportet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		mntmLibriBlerjes.setForeground(new Color(0, 0, 51));
		mntmLibriBlerjes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnRaportet.add(mntmLibriBlerjes);
		
		mntmLibriShitjes = new JMenuItem();
		mntmLibriShitjes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLibriShitjes obj = new frmLibriShitjes();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		mntmLibriShitjes.setForeground(new Color(0, 0, 51));
		mntmLibriShitjes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnRaportet.add(mntmLibriShitjes);
		
		mnDefinicionet = new JMenu();
		mnDefinicionet.setForeground(new Color(0, 0, 51));
		menuBar.add(mnDefinicionet);
		
		mntmFurnizuesit = new JMenuItem();
		mntmFurnizuesit.setForeground(new Color(0, 0, 51));
		mntmFurnizuesit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFurnitoret obj = new frmFurnitoret();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			
			}
		});
		mntmFurnizuesit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmFurnizuesit);
		
		mntmKlientet = new JMenuItem();
		mntmKlientet.setForeground(new Color(0, 0, 51));
		mntmKlientet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				frmKlientet obj = new frmKlientet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		mntmKlientet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmKlientet);
		
		mntmShtoKlientTe = new JMenuItem();
		mntmShtoKlientTe.setForeground(new Color(0, 0, 51));
		mntmShtoKlientTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmBleresi obj = new frmBleresi();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmShtoKlientTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmShtoKlientTe);
		
		mntmStafi = new JMenuItem();
		mntmStafi.setForeground(new Color(0, 0, 51));
		mntmStafi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pozita.equals("Pronar"))
				{
					frmStafi obj = new frmStafi();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
			}
		});
		mntmStafi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmStafi);
		
		mntmShtoAnetareTe = new JMenuItem();
		mntmShtoAnetareTe.setForeground(new Color(0, 0, 51));
		mntmShtoAnetareTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));
		mntmShtoAnetareTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pozita.equals("Pronar"))
				{
					frmSignUp obj = new frmSignUp();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
			}
		});
		mnDefinicionet.add(mntmShtoAnetareTe);
		
		mnShitja = new JMenu();
		mnShitja.setForeground(new Color(0, 0, 51));
		menuBar.add(mnShitja);
		
		mntmShitja = new JMenuItem();
		mntmShitja.setForeground(new Color(0, 0, 51));
		mntmShitja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmShitja obj = new frmShitja();
			obj.setVisible(true);
			obj.setLocationRelativeTo(null);
			}
		});
		mntmShitja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnShitja.add(mntmShitja);
		
		
		mnDalja = new JMenu();
		mnDalja.setForeground(new Color(0, 0, 51));
		menuBar.add(mnDalja);
		
		mntmExit = new JMenuItem();
		mntmExit.setForeground(new Color(0, 0, 51));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Useri.setNrPersonal("");
				Useri.setEmri("");
				Useri.setMbiemri("");
				String dalja; 
				if(Gjuhesia.gjuha.equals("alb"))
					dalja = "Kaloni bukur!";
				else
					dalja = "Have a nice day!";
				JOptionPane.showMessageDialog(null,dalja);
				dispose();
				frmLogin obj = new frmLogin();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mnDalja.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(102, 204, 255));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblshitja = new JLabel("");
		lblshitja.setVerticalAlignment(SwingConstants.BOTTOM);
		lblshitja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmShitja obj = new frmShitja();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		
		lblshitja.setBounds(164, 193, 100, 100);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(frmMenu.class.getResource("/imgs/buy4.png"));
		    
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null,ex.getMessage());
		}
		Image dimg = img.getScaledInstance(100, 100,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblshitja.setIcon(imageIcon);
		contentPane.add(lblshitja);
		
		JLabel lblFurnizohu = new JLabel("");
		lblFurnizohu.setBackground(Color.WHITE);
		lblFurnizohu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmFurnitoret obj = new frmFurnitoret();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				
			}
		});
		lblFurnizohu.setPreferredSize(new Dimension(100, 100));
		lblFurnizohu.setMinimumSize(new Dimension(50, 50));
		lblFurnizohu.setMaximumSize(new Dimension(100, 100));
		lblFurnizohu.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/supply1.png")));
		lblFurnizohu.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFurnizohu.setBounds(428, 193, 100, 100);
		contentPane.add(lblFurnizohu);
		
		lblShitja = new JLabel();
		lblShitja.setForeground(new Color(0, 0, 51));
		lblShitja.setFont(new Font("Arial", Font.BOLD, 17));
		lblShitja.setHorizontalAlignment(SwingConstants.CENTER);
		lblShitja.setBounds(164, 306, 100, 23);
		contentPane.add(lblShitja);
		
		lblfurnizohu = new JLabel();
		lblfurnizohu.setForeground(new Color(0, 0, 51));
		lblfurnizohu.setFont(new Font("Arial", Font.BOLD, 17));
		lblfurnizohu.setHorizontalAlignment(SwingConstants.CENTER);
		lblfurnizohu.setBounds(428, 306, 100, 23);
		contentPane.add(lblfurnizohu);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmKlientet obj = new frmKlientet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/clients1.png")));
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBounds(692, 193, 100, 100);
		contentPane.add(label);
		
		lblKlientat = new JLabel();
		lblKlientat.setForeground(new Color(0, 0, 51));
		lblKlientat.setHorizontalAlignment(SwingConstants.CENTER);
		lblKlientat.setFont(new Font("Arial", Font.BOLD, 17));
		lblKlientat.setBounds(692, 306, 100, 23);
		contentPane.add(lblKlientat);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBleresi obj = new frmBleresi();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/clients2.png")));
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setBounds(692, 425, 100, 100);
		contentPane.add(label_1);
		
		lblShtoKlient = new JLabel();
		lblShtoKlient.setForeground(new Color(0, 0, 51));
		lblShtoKlient.setHorizontalAlignment(SwingConstants.CENTER);
		lblShtoKlient.setFont(new Font("Arial", Font.BOLD, 17));
		lblShtoKlient.setBounds(692, 538, 100, 23);
		contentPane.add(lblShtoKlient);
		
		lblStafi = new JLabel();
		lblStafi.setForeground(new Color(0, 0, 51));
		lblStafi.setHorizontalAlignment(SwingConstants.CENTER);
		lblStafi.setFont(new Font("Arial", Font.BOLD, 17));
		lblStafi.setBounds(956, 306, 100, 23);
		contentPane.add(lblStafi);
		
		lblstafi = new JLabel("");
		lblstafi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pozita.equals("Pronar"))
				{
					frmStafi obj = new frmStafi();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
					dispose();
				}
				
			}
		});
		lblstafi.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/staff1.png")));
		lblstafi.setVerticalAlignment(SwingConstants.BOTTOM);
		lblstafi.setBounds(956, 193, 100, 100);
		contentPane.add(lblstafi);
		
		lblShtoStaf = new JLabel();
		lblShtoStaf.setForeground(new Color(0, 0, 51));
		lblShtoStaf.setHorizontalAlignment(SwingConstants.CENTER);
		lblShtoStaf.setFont(new Font("Arial", Font.BOLD, 17));
		lblShtoStaf.setBounds(956, 538, 100, 23);
		contentPane.add(lblShtoStaf);
		
		lblshtostaf = new JLabel("");
		lblshtostaf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pozita.equals("Pronar"))
				{
					frmSignUp obj = new frmSignUp();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		lblshtostaf.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/staff2.png")));
		lblshtostaf.setVerticalAlignment(SwingConstants.BOTTOM);
		lblshtostaf.setBounds(956, 425, 100, 100);
		contentPane.add(lblshtostaf);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				frmBlerjet obj = new frmBlerjet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_2.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/buy5.png")));
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setBounds(164, 425, 100, 100);
		contentPane.add(label_2);
		
		lblShtoProdukte = new JLabel();
		lblShtoProdukte.setForeground(new Color(0, 0, 51));
		lblShtoProdukte.setHorizontalAlignment(SwingConstants.CENTER);
		lblShtoProdukte.setFont(new Font("Arial", Font.BOLD, 17));
		lblShtoProdukte.setBounds(152, 538, 128, 23);
		contentPane.add(lblShtoProdukte);
		
		JLabel label_5 = new JLabel("");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				frmRaportet obj = new frmRaportet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		label_5.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/blerje2.png")));
		label_5.setVerticalAlignment(SwingConstants.BOTTOM);
		label_5.setBounds(428, 425, 100, 100);
		contentPane.add(label_5);
		
		lblLibriIBlerjes = new JLabel();
		lblLibriIBlerjes.setForeground(new Color(0, 0, 51));
		lblLibriIBlerjes.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibriIBlerjes.setFont(new Font("Arial", Font.BOLD, 17));
		lblLibriIBlerjes.setBounds(417, 538, 128, 23);
		contentPane.add(lblLibriIBlerjes);
		
		JLabel label_6 = new JLabel("");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				frmLibriShitjes obj = new frmLibriShitjes();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		label_6.setSize(new Dimension(100, 100));
		label_6.setMinimumSize(new Dimension(50, 50));
		label_6.setMaximumSize(new Dimension(100, 100));
		label_6.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/sell1.png")));
		label_6.setVerticalAlignment(SwingConstants.BOTTOM);
		label_6.setBounds(164, 639, 100, 100);
		contentPane.add(label_6);
		
		lblLibriIShitjes = new JLabel();
		lblLibriIShitjes.setForeground(new Color(0, 0, 51));
		lblLibriIShitjes.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibriIShitjes.setFont(new Font("Arial", Font.BOLD, 17));
		lblLibriIShitjes.setBounds(152, 752, 128, 23);
		contentPane.add(lblLibriIShitjes);
		
		lblPerdoruesi = new JLabel();
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 16));
		lblPerdoruesi.setForeground(new Color(255, 255, 255));
		lblPerdoruesi.setBounds(981, 829, 91, 29);
		contentPane.add(lblPerdoruesi);
		
		
		lblUseri.setForeground(new Color(255, 255, 255));
		lblUseri.setFont(new Font("Arial", Font.BOLD, 16));
		lblUseri.setBounds(1075, 829, 141, 29);
		
		contentPane.add(lblUseri);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Useri.setNrPersonal("");
				Useri.setEmri("");
				Useri.setMbiemri("");
				String dalja = "Kaloni bukur!";
				if(Gjuhesia.gjuha.equals("alb"))
				{
					dalja = "Kaloni bukur!";
				}
				else
				{
					dalja ="Have a nice day!";
				}
				JOptionPane.showMessageDialog(null,dalja);
				dispose();
				frmLogin obj = new frmLogin();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		label_4.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/exit.png")));
		label_4.setVerticalAlignment(SwingConstants.BOTTOM);
		label_4.setBounds(428, 639, 100, 100);
		contentPane.add(label_4);
		
		lblDalja = new JLabel();
		lblDalja.setHorizontalAlignment(SwingConstants.CENTER);
		lblDalja.setForeground(new Color(0, 0, 51));
		lblDalja.setFont(new Font("Arial", Font.BOLD, 17));
		lblDalja.setBounds(428, 752, 100, 23);
		contentPane.add(lblDalja);
		
		JLabel label_9 = new JLabel("");
		BufferedImage fotoja = null;
		try {
		    fotoja = ImageIO.read(frmMenu.class.getResource("/imgs/logo1.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dfotoja = fotoja.getScaledInstance(539, 147,Image.SCALE_SMOOTH);
		ImageIcon fotoIcon = new ImageIcon(dfotoja);
		label_9.setIcon(fotoIcon);
		label_9.setBounds(340, 0, 606, 180);
		contentPane.add(label_9);
		
		rdbtnAlb = new JRadioButton("");
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				labelatAl();
				Gjuhesia.gjuha = "alb";
			}
		});
		
		rdbtnAlb.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setBounds(1152, 0, 36, 25);
		contentPane.add(rdbtnAlb);
		
		rdbtnEng = new JRadioButton("");
		
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Gjuhesia.gjuha = "eng";
				labelatEng();
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/eng.png")));
		rdbtnEng.setOpaque(false);
		rdbtnEng.setBounds(1180, -1, 36, 25);
		contentPane.add(rdbtnEng);
		
		lblData = new JLabel("");
		lblData.setFont(new Font("Arial", Font.BOLD, 16));
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setBounds(0, 829, 82, 29);
		contentPane.add(lblData);
		
		JLabel lblSetData = new JLabel("");
		lblSetData.setForeground(new Color(255, 255, 255));
		lblSetData.setFont(new Font("Arial", Font.BOLD, 16));
		lblSetData.setBounds(94, 829, 148, 29);
		contentPane.add(lblSetData);
		
		JLabel label_8 = new JLabel("");
		label_8.setOpaque(true);
		label_8.setBackground(new Color(153, 204, 255));
		label_8.setBounds(0, 788, 1220, 90);
		contentPane.add(label_8);
		perdoruesi();
		if(Gjuhesia.gjuha.equals("alb"))
		{
			labelatAl();
		}
		else
		{
			labelatEng();
		}
		java.util.Date data = new java.util.Date();
		DateFormat obj = new SimpleDateFormat("dd/MM/yyyy");
		String dataAk = obj.format(data).toString();
		lblSetData.setText(dataAk);
		
	}
	public void perdoruesi()
	{
		try
		{
			String emri = Useri.getEmri();
			String mbiemri = Useri.getMbiemri();
			String nrPersonal = Useri.getNrPersonal();
			lblUseri.setText(emri+" "+mbiemri);
			
			String sql = "select p.pershkrimi from tblpozita p,tblStafi s  where p.id = s.poziteId and s.nrPersonal = '"+nrPersonal+"'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();			
			if(res.next());
			{
				pozita = res.getString("pershkrimi");
				
			}
			pst.close();
			
			
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage());
			
		}
		
	}
	public void setState(String radio)
	{
		if(radio.equals("alb"))
		{
			rdbtnAlb.setSelected(true);
			labelatAl();
		}
		else if(radio.equals("eng"))
		{
			rdbtnEng.setSelected(true);
			labelatEng();
		}
	}
	public void labelatAl()
	{
		mnProduktet.setText("Produktet");
		mntmRegjistrimiIMallit.setText("Regjistrimi i mallit");
		mntmShtoMallTe.setText("Shto produkte");
		mnRaportet.setText("Raportet");
		mntmLibriBlerjes.setText("Libri Blerjes");
		mntmLibriShitjes.setText("Libri i shitjes");
		mnDefinicionet.setText("Administrimi");
		mntmFurnizuesit.setText("Furnizuesit");
		mntmKlientet.setText("Klientet");
		mntmShtoKlientTe.setText("Shto klient");
		mntmStafi.setText("Stafi");
		mntmShtoAnetareTe.setText("Shto anëtar");
		mnShitja.setText("Shitja");
		mntmShitja.setText("Shitja");
		mnDalja.setText("Dalja");
		mntmExit.setText("Dalja");
		lblShitja.setText("Shitja");
		lblfurnizohu.setText("Furnitorët");
		lblKlientat.setText("Klientët");
		lblShtoKlient.setText("Shto klient");
		lblStafi.setText("Stafi");
		lblShtoStaf.setText("Shto staf");
		lblShtoProdukte.setText("Shto produkte");
		lblLibriIBlerjes.setText("Libri i blerjeve");
		lblLibriIShitjes.setText("Libri i shitjeve");
		lblPerdoruesi.setText("Perdoruesi:");
		lblDalja.setText("Dalja");
		lblData.setText("Data:");
	}
	public void labelatEng()
	{
		mnProduktet.setText("Products");
		mntmRegjistrimiIMallit.setText("Products registration");
		mntmShtoMallTe.setText("Add products");
		mnRaportet.setText("Reports");
		mntmLibriBlerjes.setText("Purchase book");
		mntmLibriShitjes.setText("Sales book");
		mnDefinicionet.setText("Administration");
		mntmFurnizuesit.setText("Suppliers");
		mntmKlientet.setText("Clients");
		mntmShtoKlientTe.setText("Add clients");
		mntmStafi.setText("Staff");
		mntmShtoAnetareTe.setText("Add staff");
		mnShitja.setText("Sale");
		mntmShitja.setText("Sale");
		mnDalja.setText("Exit");
		mntmExit.setText("Exit");
		lblShitja.setText("Sale");
		lblfurnizohu.setText("Suppliers");
		lblKlientat.setText("Clients");
		lblShtoKlient.setText("Add clients");
		lblStafi.setText("Staff");
		lblShtoStaf.setText("Add staff");
		lblShtoProdukte.setText("Add products");
		lblLibriIBlerjes.setText("Purchase book");
		lblLibriIShitjes.setText("Sales book");
		lblPerdoruesi.setText("User:");
		lblDalja.setText("Exit");
		lblData.setText("Date:");
	}
}
