
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

@SuppressWarnings("serial")
public class frmStafi extends JFrame {

	private JPanel contentPane;
	//objekti per lidhje
	Connection conn=connectionClass.connectDb();
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTable tblBleresit;
	private JScrollPane scrollPane;
	private JTextField txtSearch;
	static public int PersonalNr = 0;
	Color ngjyra =  new Color(39, 117, 239);
	JRadioButton rdbtnAlb = new JRadioButton("");
	JRadioButton rdbtnEng = new JRadioButton("");
	JLabel lblKerko = new JLabel("K\u00EBrko:");
	JMenuItem mntmShto = new JMenuItem("Shto anetare te ri");
	JButton btnEdito = new JButton("Edito");
	DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Nr Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"}, 0);
	
	private final JButton btnFshij = new JButton("Fshij");
	private final JButton btnRegjistro = new JButton("Regjistro");
	private final JButton btnKtheuPrapa = new JButton("Kthehu prapa");
	private final JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
	private final JLabel lblShitesi = new JLabel();
	private final JLabel label_1 = new JLabel("");
	private final JLabel label_3 = new JLabel("");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmStafi frame = new frmStafi();
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
	public frmStafi() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmStafi.class.getResource("/imgs/logoPune.png")));
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				insertTable();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		}); 

		setTitle("Besa Commerce");
		conn = connectionClass.connectDb();
		//updateTable();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1286, 962);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmShto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignUp obj = new frmSignUp();
				obj.setVisible(true);
			
			}
		});
		mntmShto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnFile.add(mntmShto);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnEdito.setBorder(new LineBorder(new Color(0, 0, 51)));
		btnEdito.setBackground(new Color(255, 255, 255));
		btnEdito.setForeground(new Color(0, 0, 51));
		
		btnEdito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, tblBleresit.getSelectedRow());
				editoPunetorin pn = new editoPunetorin();
				String nr = (String) tblBleresit.getValueAt(tblBleresit.getSelectedRow(),1);
				 PersonalNr = Integer.valueOf(nr);
				 pn.setVisible(true);
			}
		});
		btnEdito.setFont(new Font("Arial", Font.BOLD, 16));
		btnEdito.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/icons8_Edit_Property_48px.png")));
		btnEdito.setBounds(1091, 226, 185, 53);
		contentPane.add(btnEdito);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(135, 206, 250)));
		scrollPane.setBounds(10, 226, 1069, 662);
		contentPane.add(scrollPane);
		
		tblBleresit = new javax.swing.JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer ren, int r, int c) {
				Component com = super.prepareRenderer(ren, r, c);
				if (r % 2 == 0 && !isRowSelected(r)) {
					com.setBackground(Color.WHITE);
					com.setForeground(Color.BLACK);
				} else if (isRowSelected(r)) {
					com.setBackground(new Color(22,150,146));
					com.setForeground(Color.BLACK);
				} else {
					com.setBackground(new Color(234,243,243));
					com.setForeground(Color.BLACK);
				}
				
				tblBleresit.setBackground(new Color(255, 255, 255));
				tblBleresit.setFont(new Font("Arial", Font.BOLD, 13));
				tblBleresit.getTableHeader().setReorderingAllowed(false);
				scrollPane.setViewportView(tblBleresit);
				
				if(isRowSelected(tblBleresit.getSelectedRow())) {
					btnEdito.setEnabled(true);
					btnFshij.setEnabled(true);
				}
				else
				{
					btnEdito.setEnabled(false);
					btnFshij.setEnabled(false);
				}
				return com;
			}
		};
		tblBleresit.setForeground(new Color(0, 0, 51));
		tblBleresit.setFont(new Font("Arial", Font.BOLD, 13));
		scrollPane.setViewportView(tblBleresit);
		tblBleresit.getTableHeader().setBackground(new Color(22,127,146));
		tblBleresit.getTableHeader().setForeground(Color.WHITE);
		tblBleresit.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
		lblKerko.setForeground(new Color(255, 255, 255));

		lblKerko.setFont(new Font("Arial", Font.BOLD, 13));
		lblKerko.setBounds(10, 167, 74, 27);
		contentPane.add(lblKerko);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(255, 255, 255));
		txtSearch.setFont(new Font("Arial", Font.BOLD, 13));
		txtSearch.setBorder(null);
		txtSearch.setOpaque(false);
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateTable();
			}
		});
		
		txtSearch.setBounds(89, 167, 209, 27);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(94, 190, 202, 3);
		contentPane.add(separator);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/puneLogo1.png")));
		label_2.setBounds(10, 0, 472, 167);
		contentPane.add(label_2);
		
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			//"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"
			public void mouseReleased(MouseEvent e) {
					Gjuhesia.gjuha="eng";
					NdryshoGjuhen();
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/eng.png")));
		rdbtnEng.setOpaque(false);
		rdbtnEng.setBounds(1243, 9, 33, 25);
		contentPane.add(rdbtnEng);
		
		
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			//"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita"}, 0);
			public void mouseReleased(MouseEvent arg0) {
					Gjuhesia.gjuha="alb";
					NdryshoGjuhen();
			}
		});
		rdbtnAlb.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setSelected(true);
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setBounds(1208, 9, 33, 25);
		contentPane.add(rdbtnAlb);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/icons8_Search_48px.png")));
		label.setBounds(297, 167, 55, 40);
		contentPane.add(label);
		btnFshij.setBorder(new LineBorder(new Color(0, 0, 51)));
		btnFshij.setBackground(new Color(255, 255, 255));
		btnFshij.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/icons8_Delete_48px.png")));
		btnFshij.setForeground(new Color(0, 0, 51));
		btnFshij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteStaff();
				insertTable();
			}
		});
		btnFshij.setFont(new Font("Arial", Font.BOLD, 13));
		btnFshij.setBounds(1091, 302, 185, 53);
		
		contentPane.add(btnFshij);
		btnRegjistro.setBorder(new LineBorder(new Color(0, 0, 51)));
		btnRegjistro.setBackground(new Color(255, 255, 255));
		btnRegjistro.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/icons8_Add_User_Male_48px.png")));
		btnRegjistro.setForeground(new Color(0, 0, 51));
		btnRegjistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignUp objSignUp = new frmSignUp();
				objSignUp.setVisible(true);
				objSignUp.setLocationRelativeTo(null);
			}
		});
		btnRegjistro.setFont(new Font("Arial", Font.BOLD, 13));
		btnRegjistro.setBounds(1091, 376, 185, 53);
		
		contentPane.add(btnRegjistro);
		btnKtheuPrapa.setBorder(new LineBorder(new Color(0, 0, 51)));
		btnKtheuPrapa.setBackground(new Color(255, 255, 255));
		btnKtheuPrapa.setIcon(new ImageIcon(frmStafi.class.getResource("/imgs/icons8_Back_Arrow_48px.png")));
		btnKtheuPrapa.setForeground(new Color(0, 0, 51));
		btnKtheuPrapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu objMenu = new frmMenu();
				objMenu.setVisible(true);
				objMenu.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnKtheuPrapa.setFont(new Font("Arial", Font.BOLD, 13));
		btnKtheuPrapa.setBounds(1091, 456, 185, 53);
		
		contentPane.add(btnKtheuPrapa);
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setForeground(new Color(255, 255, 255));
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 13));
		lblPerdoruesi.setBackground(Color.WHITE);
		lblPerdoruesi.setBounds(1056, 43, 90, 27);
		
		contentPane.add(lblPerdoruesi);
		lblShitesi.setForeground(new Color(255, 255, 255));
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 13));
		lblShitesi.setBackground(Color.WHITE);
		lblShitesi.setBounds(1158, 43, 114, 27);
		lblShitesi.setText(Useri.getEmri()+ " "+Useri.getMbiemri());
		contentPane.add(lblShitesi);
		label_3.setOpaque(true);
		label_3.setBackground(new Color(0, 0, 51));
		label_3.setBounds(0, 211, 1417, 3);
		
		contentPane.add(label_3);
		label_1.setBackground(new Color(153, 204, 255));
		label_1.setOpaque(true);
		label_1.setBounds(0, 0, 1417, 213);
		
		contentPane.add(label_1);

		
		if(Gjuhesia.gjuha=="eng")
		{
			model = new DefaultTableModel(new String[]{"Nr.", "Personal No", "Name","Surname","Birth Date","Gender","Telephone","City of Residence","Country of Residence","Position","Salary"}, 0);
			lblKerko.setText("Search:");
			mntmShto.setText("Add new employees");
			scrollPane.setViewportView(tblBleresit);
			btnEdito.setText("Edit");
			lblPerdoruesi.setText("User:");
			btnKtheuPrapa.setText("Go back");
			btnRegjistro.setText("Register");
			btnFshij.setText("Delete");
			
		}
		else
		{
			model = new DefaultTableModel(new String[]{"Nr.", "Nr Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"}, 0);
			lblKerko.setText("Kerko:");
			mntmShto.setText("Shto anetare te ri");
			scrollPane.setViewportView(tblBleresit);
			btnEdito.setText("Edito");
			lblPerdoruesi.setText("Perdoruesi:");
			btnKtheuPrapa.setText("Kthehu mbrapa");
			btnRegjistro.setText("Regjistro");
			btnFshij.setText("Fshij");
		}
	}
	
	public void insertTable()
	{
	

	try 
	{
		model = new DefaultTableModel(new String[]{"Nr.", "Nr Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"}, 0);
		if(Gjuhesia.gjuha.equals("alb"))
			model = new DefaultTableModel(new String[]{"Nr.", "Nr Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"}, 0);
		else
			model = new DefaultTableModel(new String[]{"Nr.", "Personal no", "Name","Surname","Birth Date","Gender","Telephone","City of Residence","Country of Residence","Position","Salary"}, 0);
		int counter = 1;
		String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,q.qyteti,s.shteti,b.tel,b.paga,p.pershkrimi from tblstafi b,tblgjinia g, tblqytetet q, tblshtetet s, tblpozita p where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id and b.poziteid = p.id";

		pst = conn.prepareStatement(sql);
		res = pst.executeQuery();
		while(res.next())
		{
		
			String c = String.valueOf(counter);
			String np = res.getString("nrPersonal");
			String em = res.getString("emri");
			String mb = res.getString("mbiemri");
			String dt = res.getString("dtLindjes");
			String gj = res.getString("pershkrimi");
			String qt = res.getString("qyteti");
			String st = res.getString("shteti");
			String tel = res.getString("tel");
		
			String pozita = res.getString(10);
			String[] data = dt.split("-");
			String viti = data[0];
			String muaji = data[1];
			String dita = data[2];
			String dataFinale = dita+"/"+muaji+"/"+viti;
			String euro = "\u20ac";
			String paga = res.getString("paga");
			paga = paga +" "+euro;
			model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,qt,st,pozita,paga});
			counter++;
			
		}
		
		pst.close();
		tblBleresit.setModel(model);
		
		
		
	} 
	catch (Exception e) 
	{
		JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
	}
}
	
	public void updateTable()
	{
	try 
	{
		model = new DefaultTableModel(new String[]{"Nr.", "Nr Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"}, 0);
		if(Gjuhesia.gjuha.equals("alb"))
			model = new DefaultTableModel(new String[]{"Nr.", "Nr Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"}, 0);
		else
			model = new DefaultTableModel(new String[]{"Nr.", "Personal no", "Name","Surname","Birth Date","Gender","Telephone","City of Residence","Country of Residence","Position","Salary"}, 0);
		int counter = 1;
		String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,q.qyteti,s.shteti,b.tel,p.pershkrimi,b.paga from tblstafi b,tblgjinia g, tblqytetet q, tblshtetet s, tblpozita p where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id and b.poziteid = p.id and b.nrPersonal like '%"+txtSearch.getText()+"%'";
		
		pst = conn.prepareStatement(sql);
		res = pst.executeQuery();
		while(res.next())
		{
		
			String c = String.valueOf(counter);
			String np = res.getString("nrPersonal");
			String em = res.getString("emri");
			String mb = res.getString("mbiemri");
			String dt = res.getString("dtLindjes");
			String gj = res.getString("pershkrimi");
			String qt = res.getString("qyteti");
			String st = res.getString("shteti");
			String tel = res.getString("tel");
			String pozita = res.getString(10);
			String[] data = dt.split("-");
			String viti = data[0];
			String muaji = data[1];
			String dita = data[2];
			String dataFinale = dita+"/"+muaji+"/"+viti;
			String euro = "\u20ac";
			String paga = res.getString("paga");
			paga = paga +" "+euro;
			model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,qt,st,pozita,paga});
			counter++;
			
		}
		pst.close();
		tblBleresit.setModel(model);
		
		
	} 
	catch (Exception e) 
	{
		JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
	}
	NdryshoGjuhen();
	}
	private void NdryshoGjuhen()
	{
		if(Gjuhesia.gjuha=="eng")
		{
			lblKerko.setText("Search:");
			tblBleresit.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			tblBleresit.getColumnModel().getColumn(1).setHeaderValue("Personal No");
			tblBleresit.getColumnModel().getColumn(2).setHeaderValue("Name");
			tblBleresit.getColumnModel().getColumn(3).setHeaderValue("Surname");
			tblBleresit.getColumnModel().getColumn(4).setHeaderValue("Birth Date");
			tblBleresit.getColumnModel().getColumn(5).setHeaderValue("Gender");
			tblBleresit.getColumnModel().getColumn(6).setHeaderValue("Telephone");
			tblBleresit.getColumnModel().getColumn(7).setHeaderValue("City of Residence");
			tblBleresit.getColumnModel().getColumn(8).setHeaderValue("Country of Residence");
			tblBleresit.getColumnModel().getColumn(9).setHeaderValue("Position");
			tblBleresit.getColumnModel().getColumn(10).setHeaderValue("Salary");
			mntmShto.setText("Add new employees");
			scrollPane.setViewportView(tblBleresit);
			btnEdito.setText("Edit");
			lblPerdoruesi.setText("User:");
			btnKtheuPrapa.setText("Go back");
			btnRegjistro.setText("Register");
			btnFshij.setText("Delete");
			
		}
		else
		{
			lblKerko.setText("Kerko:");
			tblBleresit.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			tblBleresit.getColumnModel().getColumn(1).setHeaderValue("Nr Personal");
			tblBleresit.getColumnModel().getColumn(2).setHeaderValue("Emri");
			tblBleresit.getColumnModel().getColumn(3).setHeaderValue("Mbiemri");
			tblBleresit.getColumnModel().getColumn(4).setHeaderValue("Data e lindjes");
			tblBleresit.getColumnModel().getColumn(5).setHeaderValue("Gjinia");
			tblBleresit.getColumnModel().getColumn(6).setHeaderValue("Telefoni");
			tblBleresit.getColumnModel().getColumn(7).setHeaderValue("Qyteti vendbanimit");
			tblBleresit.getColumnModel().getColumn(8).setHeaderValue("Shteti vendbanimit");
			tblBleresit.getColumnModel().getColumn(9).setHeaderValue("Pozita");
			tblBleresit.getColumnModel().getColumn(10).setHeaderValue("Paga");
			mntmShto.setText("Shto anetare te ri");
			scrollPane.setViewportView(tblBleresit);
			btnEdito.setText("Edito");
			lblPerdoruesi.setText("Perdoruesi:");
			btnKtheuPrapa.setText("Kthehu mbrapa");
			btnRegjistro.setText("Regjistro");
			btnFshij.setText("Fshij");
		}
	}
	private void deleteStaff()
	{
		try {
				
			DefaultTableModel modeli = (DefaultTableModel)tblBleresit.getModel();
			
			int[] rreshtat = tblBleresit.getSelectedRows();
			
			for(int i=0;i<rreshtat.length;i++)
			{
				String id = (String) modeli.getValueAt(rreshtat[i], 1);
				
				String sql = "delete from tblstafi where nrPersonal = '"+id+"';";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
			
			
		}
			pst.close();
			updateTable();
		
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
	}
}