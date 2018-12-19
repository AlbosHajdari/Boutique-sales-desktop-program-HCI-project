
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class frmKlientet extends JFrame {

	private JPanel contentPane;
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTable tblBleresit;
	private JScrollPane scrollPane;
	private JTextField txtSearch;
	private JMenu mnFile = new JMenu("File");
	private JLabel lblKrko; 
	private JMenuItem mntmShtoKlientTe = new JMenuItem("Shto klient te ri");
	private JButton btnRegjistroKlientin = new JButton("Regjistro klientin");
	private JButton btnFshijKlientin = new JButton("Fshij klientin");
	private JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
	private DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","E-maili","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
	private final JButton btnKthehuMbrapa = new JButton("Kthehu mbrapa");
	private final JLabel label = new JLabel("");
	private final JLabel label_1 = new JLabel("");
	private final JLabel label_2 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmKlientet frame = new frmKlientet();
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
	public frmKlientet() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmKlientet.class.getResource("/imgs/logo1icon1.png")));
		setResizable(false);
		setTitle("Besa Commerce");
		conn = connectionClass.connectDb();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1265, 1000);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		
		mntmShtoKlientTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBleresi obj = new frmBleresi();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			
			}
		});
		mntmShtoKlientTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnFile.add(mntmShtoKlientTe);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(135, 206, 250)));
		scrollPane.setBounds(12, 258, 1238, 616);
		contentPane.add(scrollPane);
		
		tblBleresit =new javax.swing.JTable(model) {
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
				return com;
			}
		};
		tblBleresit.setFont(new Font("Arial", Font.BOLD, 14));
		tblBleresit.getTableHeader().setReorderingAllowed(false);
		tblBleresit.getTableHeader().setBackground(new Color(22,127,146));
		tblBleresit.getTableHeader().setForeground(Color.WHITE);
		tblBleresit.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
		tblBleresit.getTableHeader().setResizingAllowed(true);
		scrollPane.setViewportView(tblBleresit);
		insertTable();
		
		
		lblKrko = new JLabel("K\u00EBrko:");
		lblKrko.setForeground(new Color(0, 0, 51));
		lblKrko.setFont(new Font("Arial", Font.BOLD, 14));
		lblKrko.setBounds(12, 211, 84, 34);
		contentPane.add(lblKrko);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(0, 0, 51));
		txtSearch.setFont(new Font("Arial", Font.BOLD, 14));
		txtSearch.setBorder(null);
		txtSearch.setOpaque(false);
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateTable();
			}
		});
		txtSearch.setBounds(109, 211, 290, 34);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		btnFshijKlientin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(tblBleresit.getSelectedRowCount()==0)
						JOptionPane.showMessageDialog(null,"Ju duhet te selektoni nje rresht.");
					else
						FshijKlientin();
				}
			}
		});
		btnFshijKlientin.setForeground(new Color(0, 0, 51));
		btnFshijKlientin.setIcon(new ImageIcon(frmKlientet.class.getResource("/imgs/icons8_Delete_48px.png")));
		btnFshijKlientin.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnFshijKlientin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblBleresit.getSelectedRowCount()==0)
					JOptionPane.showMessageDialog(null,"Ju duhet te selektoni nje rresht.");
				else
					FshijKlientin();
			}
		});
		btnFshijKlientin.setBackground(Color.WHITE);
		btnFshijKlientin.setBounds(235, 879, 211, 47);
		contentPane.add(btnFshijKlientin);
		btnRegjistroKlientin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					frmBleresi obj = new frmBleresi();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
			}
		});
		btnRegjistroKlientin.setForeground(new Color(0, 0, 51));
		btnRegjistroKlientin.setIcon(new ImageIcon(frmKlientet.class.getResource("/imgs/icons8_Add_User_Male_48px.png")));
		btnRegjistroKlientin.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnRegjistroKlientin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBleresi objBleresi = new frmBleresi();
				objBleresi.setVisible(true);
				objBleresi.setLocationRelativeTo(null);
			}
		});
		btnRegjistroKlientin.setBackground(Color.WHITE);
		btnRegjistroKlientin.setBounds(12, 879, 211, 47);
		contentPane.add(btnRegjistroKlientin);
		
		JButton btnAnglisht = new JButton("");
		btnAnglisht.setOpaque(false);
		btnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha="eng";
				NdryshoGjuhen();
			}
		});
		btnAnglisht.setIcon(new ImageIcon(frmKlientet.class.getResource("/imgs/eng.png")));
		btnAnglisht.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnglisht.setAlignmentX(0.5f);
		btnAnglisht.setBounds(1225, 13, 25, 25);
		contentPane.add(btnAnglisht);
		
		JButton btnShqip = new JButton("");
		btnShqip.setOpaque(false);
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha="alb";
				NdryshoGjuhen();
			}
		});
		btnShqip.setIcon(new ImageIcon(frmKlientet.class.getResource("/imgs/alb.png")));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setAlignmentX(0.5f);
		btnShqip.setBounds(1198, 13, 25, 25);
		contentPane.add(btnShqip);
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblPerdoruesi.setForeground(new Color(255, 255, 255));
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblPerdoruesi.setBackground(Color.WHITE);
		lblPerdoruesi.setBounds(1006, 47, 109, 25);
		contentPane.add(lblPerdoruesi);
		
		JLabel lblShitesi = new JLabel();
		lblShitesi.setForeground(new Color(255, 255, 255));
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblShitesi.setBackground(Color.WHITE);
		lblShitesi.setBounds(1127, 47, 123, 25);
		lblShitesi.setText(Useri.getEmri()+ " "+Useri.getMbiemri());
		contentPane.add(lblShitesi);
		btnKthehuMbrapa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					frmShitja objShitja = new frmShitja();
					objShitja.setVisible(true);
					objShitja.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		btnKthehuMbrapa.setForeground(new Color(0, 0, 51));
		btnKthehuMbrapa.setIcon(new ImageIcon(frmKlientet.class.getResource("/imgs/icons8_Back_48px_1.png")));
		btnKthehuMbrapa.setFont(new Font("Arial", Font.BOLD, 14));
		btnKthehuMbrapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmShitja objShitja = new frmShitja();
				objShitja.setVisible(true);
				objShitja.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnKthehuMbrapa.setBackground(Color.WHITE);
		btnKthehuMbrapa.setBounds(458, 879, 211, 47);
		
		contentPane.add(btnKthehuMbrapa);
		label_2.setBackground(new Color(153, 204, 255));
		label_2.setIcon(new ImageIcon(frmKlientet.class.getResource("/imgs/logo1.png")));
		label_2.setOpaque(true);
		label_2.setBounds(10, 0, 524, 189);
		
		contentPane.add(label_2);
		label_1.setOpaque(true);
		label_1.setBackground(new Color(0, 0, 51));
		label_1.setBounds(0, 202, 1864, 3);
		
		contentPane.add(label_1);
		label.setOpaque(true);
		label.setBackground(new Color(153, 204, 255));
		label.setBounds(0, 0, 1864, 203);
		
		contentPane.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(108, 242, 290, 3);
		contentPane.add(separator);
		NdryshoGjuhen();
	}
	public void insertTable()
	{
		try 
		{
			
			
			model = new DefaultTableModel(new String[]{"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Email","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
			int counter = 1;
			String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,b.tel,b.email,q.qyteti,s.shteti from tblbleresit b,tblgjinia g, tblqytetet q, tblshtetet s where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id";
			
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
				String tel = res.getString("tel");
				String email = res.getString("email");
				String qt = res.getString("qyteti");
				String st = res.getString("shteti");
				String[] data = dt.split("-");
				String viti = data[0];
				String muaji = data[1];
				String dita = data[2];
				String dataFinale = dita+"/"+muaji+"/"+viti;
				
				model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,email,qt,st});
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
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Email","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
			int counter = 1;
			String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,b.tel,b.email,q.qyteti,s.shteti from tblbleresit b,tblgjinia g, tblqytetet q, tblshtetet s where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id and (b.nrPersonal like '%"+txtSearch.getText()+"%')";
			
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
				String tel = res.getString("tel");
				String email = res.getString("email");
				String qt = res.getString("qyteti");
				String st = res.getString("shteti");
				String[] data = dt.split("-");
				String viti = data[0];
				String muaji = data[1];
				String dita = data[2];
				String dataFinale = dita+"/"+muaji+"/"+viti;
				
				model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,email,qt,st});
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
	private void FshijKlientin()
	{		
		int[] selectedRows = tblBleresit.getSelectedRows();
		
		for (int i =0; i<selectedRows.length; i++)
		{
			Integer IDfshijKlientin =  Integer.parseInt((String) tblBleresit.getValueAt(selectedRows[i], 1));
			
			String sql1 = "delete from tblbleresit where nrPersonal = " +IDfshijKlientin;
		
			try
			{
				Statement stmt1 = conn.createStatement();
				stmt1.executeUpdate(sql1);
			}
			catch (SQLException e)
			{
				JOptionPane.showMessageDialog(null, e);
			}
		}
		insertTable();
	}
	private void NdryshoGjuhen()
	{
		if(Gjuhesia.gjuha=="eng")
		{
			mntmShtoKlientTe.setText("Add new client");
			btnRegjistroKlientin.setText("Register a client");
			btnFshijKlientin.setText("Delete the client");
			lblPerdoruesi.setText("User");
			btnKthehuMbrapa.setText("Go back");
			lblKrko.setText("Search:");
			tblBleresit.getColumnModel().getColumn(0).setHeaderValue("No.");
			tblBleresit.getColumnModel().getColumn(1).setHeaderValue("Personal number");
			tblBleresit.getColumnModel().getColumn(2).setHeaderValue("Name");
			tblBleresit.getColumnModel().getColumn(3).setHeaderValue("Surname");
			tblBleresit.getColumnModel().getColumn(4).setHeaderValue("Date of birth");
			tblBleresit.getColumnModel().getColumn(5).setHeaderValue("Gender");
			tblBleresit.getColumnModel().getColumn(6).setHeaderValue("Phone number");
			tblBleresit.getColumnModel().getColumn(7).setHeaderValue("E-mail");
			tblBleresit.getColumnModel().getColumn(8).setHeaderValue("Current city");
			tblBleresit.getColumnModel().getColumn(9).setHeaderValue("Current state");
			
			scrollPane.setViewportView(tblBleresit);
		}
		else
		{
			mntmShtoKlientTe.setText("Shto nje klient te ri");
			btnRegjistroKlientin.setText("Regjistro klient");
			btnFshijKlientin.setText("Fshije klientin");
			lblPerdoruesi.setText("Perdoruesi");
			btnKthehuMbrapa.setText("Kthehu mbrapa");
			lblKrko.setText("Kërko:");
			tblBleresit.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			tblBleresit.getColumnModel().getColumn(1).setHeaderValue("Numri Personal");
			tblBleresit.getColumnModel().getColumn(2).setHeaderValue("Emri");
			tblBleresit.getColumnModel().getColumn(3).setHeaderValue("Mbiemri");
			tblBleresit.getColumnModel().getColumn(4).setHeaderValue("Data e lindjes");
			tblBleresit.getColumnModel().getColumn(5).setHeaderValue("Gjinia");
			tblBleresit.getColumnModel().getColumn(6).setHeaderValue("Telefoni");
			tblBleresit.getColumnModel().getColumn(7).setHeaderValue("E-maili");
			tblBleresit.getColumnModel().getColumn(8).setHeaderValue("Qyteti i vendbanimit");
			tblBleresit.getColumnModel().getColumn(9).setHeaderValue("Shteti i vendbanimit");
			
			scrollPane.setViewportView(tblBleresit);
		}
	}
}