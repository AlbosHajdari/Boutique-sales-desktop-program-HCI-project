
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;

public class frmKtheProduktin extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	JScrollPane scrollPane = new JScrollPane();
	JLabel label = new JLabel("");
	JButton btnKtheProdukte = new JButton("Kthe produkte");
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	JMenuItem mntmMbyll = new JMenuItem("Mbyll");
	static ResultSet res = null;
	protected static double sasiaPerKthim = 0;
	protected static double sasiaAktuale = 0;
	protected static boolean aMeKthy = true;
	int numri = 0;
	int i = 0;
	private static Connection conn;
	private String barkodi;
	private static String IDfshijProduktin;
	private static PreparedStatement pst;
	private BufferedImage imgKerkoProduktin = null;
	private String[] columnNames = {"Nr" , "Barkodi" , "Sasia" , "Cmimi shitjes", "Zbritja", "Koha shitjes" , "Data e shitjes"};
	private DefaultTableModel model = new DefaultTableModel(null, columnNames)
	{
		@Override
        public boolean isCellEditable(int row, int column)
        {
            if (column == 5 || column == 12)
            	return true;
            else
            	return false;
        }

	};
	private final JButton btnShqip = new JButton("");
	private final JButton btnAnglisht = new JButton("");
	private final static JTextField txtKerkoProduktin = new JTextField();
	private final JLabel lblKerkoProduktin = new JLabel("");
	private final JLabel lblEmriIProduktit = new JLabel("Emri i produktit");
	private final JSeparator separator = new JSeparator();
	private final JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
	private final JLabel lblShitesi = new JLabel();
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmKtheProduktin frame = new frmKtheProduktin();
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
	public frmKtheProduktin() {
		setResizable(false);
		setTitle("Besa Commerce");
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmKtheProduktin.class.getResource("/imgs/logo1icon1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1261, 980);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label.setIcon(new ImageIcon(frmKtheProduktin.class.getResource("/imgs/puneLogo1.png")));
		label.setBounds(0, 23, 574, 170);
		contentPane.add(label);
		
		scrollPane.setBounds(12, 206, 1231, 685);
		contentPane.add(scrollPane);
		table = new javax.swing.JTable(model) {
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
		table.setFont(new Font("Arial", Font.BOLD, 13));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(new Color(22,127,146));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Time New Roman", Font.BOLD, 12));
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table);
		InsertTable();
		btnKtheProdukte.setForeground(new Color(255, 255, 255));
		btnKtheProdukte.setFont(new Font("Arial", Font.BOLD, 15));
		btnKtheProdukte.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
					btnKtheProdukte.doClick();
			}
		});
		btnKtheProdukte.setSelectedIcon(new ImageIcon(frmKtheProduktin.class.getResource("/imgs/KtheProduktin.png")));
		btnKtheProdukte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0)
					JOptionPane.showMessageDialog(null,"Ju duhet te selektoni nje rresht.");
				else
					{
					int[] selectedRows = table.getSelectedRows();
					if(selectedRows.length > 1)
					{
					
						for (i =0; i<selectedRows.length; i++)
						{
							String IDfshijProduktin =  (String) table.getValueAt(selectedRows[i], 1);
							double sasiEProduktit = Double.parseDouble((String) table.getValueAt(selectedRows[i], 3));
							String barkodi = (String) table.getValueAt(selectedRows[i], 1);
							//ndryshim
							String sql1 = "delete from malliShitur where barkodiProduktit = '" +IDfshijProduktin+"'";
							String sql2 = "update tblregjistrimimallit set sasia = sasia+"+sasiEProduktit+" where barkodi= '"+barkodi+"'"; //ndryshim
							try
							{
								Statement stmt1 = conn.createStatement();
								stmt1.executeUpdate(sql1);
								
								Statement stmt2 = conn.createStatement();
								stmt2.executeUpdate(sql2);
							}
							catch (SQLException ep)
							{
								JOptionPane.showMessageDialog(null, ep);
							}
						}
						UpdateTable();
						DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
						alignCenter.setHorizontalAlignment(JLabel.CENTER);
						
						DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
						alignRight.setHorizontalAlignment(JLabel.RIGHT);	
						
						
						table.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
						table.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
						table.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
						table.getColumnModel().getColumn(4).setCellRenderer(alignRight);
						table.getColumnModel().getColumn(5).setCellRenderer(alignRight);
						table.getColumnModel().getColumn(6).setCellRenderer(alignRight);
						table.getColumnModel().getColumn(7).setCellRenderer(alignRight);
						
						
						table.getColumnModel().getColumn(0).setPreferredWidth(40);
						table.getColumnModel().getColumn(1).setPreferredWidth(100);
						table.getColumnModel().getColumn(2).setPreferredWidth(120);
						table.getColumnModel().getColumn(3).setPreferredWidth(50);
						table.getColumnModel().getColumn(4).setPreferredWidth(100);
						table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
					}
					else
					{
						IDfshijProduktin =  (String) table.getValueAt(table.getSelectedRow(), 1);
						sasiaAktuale = Double.parseDouble((String) table.getValueAt(table.getSelectedRow(), 3));
						barkodi = (String) table.getValueAt(table.getSelectedRow(), 1);
						aMeKthy = false;
						frmSasia obj = new frmSasia();
						obj.setVisible(true);
						obj.setLocationRelativeTo(null);
					}
					
				}
				
			}
		});

		DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
		alignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
		alignRight.setHorizontalAlignment(JLabel.RIGHT);	
		
		
		table.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(4).setCellRenderer(alignRight);
		table.getColumnModel().getColumn(5).setCellRenderer(alignRight);
		table.getColumnModel().getColumn(6).setCellRenderer(alignRight);
		table.getColumnModel().getColumn(7).setCellRenderer(alignRight);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrollPane.setViewportView(table);
		btnKtheProdukte.setBackground(new Color(153, 204, 255));
		
		btnKtheProdukte.setIcon(new ImageIcon(frmKtheProduktin.class.getResource("/imgs/KtheProduktin.png")));
		btnKtheProdukte.setBounds(1018, 904, 225, 37);
		contentPane.add(btnKtheProdukte);
		
		
		menuBar.setBounds(0, 0, 1602, 21);
		contentPane.add(menuBar);
		
		menuBar.add(mnFile);
		
		mntmMbyll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmMbyll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mnFile.add(mntmMbyll);
		
		NdryshoGjuhen();
		
		btnShqip.setOpaque(false);
		btnShqip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnShqip.doClick();
			}
		});
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha="alb";
				NdryshoGjuhen();
			}
		});
		btnShqip.setIcon(new ImageIcon(frmKtheProduktin.class.getResource("/imgs/alb.png")));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setAlignmentX(0.5f);
		btnShqip.setBounds(1180, 23, 25, 25);
		
		contentPane.add(btnShqip);
		btnAnglisht.setOpaque(false);
		btnAnglisht.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnAnglisht.doClick();
			}
		});
		btnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha="eng";
				NdryshoGjuhen();
			}
		});
		btnAnglisht.setIcon(new ImageIcon(frmKtheProduktin.class.getResource("/imgs/eng.png")));
		btnAnglisht.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnglisht.setAlignmentX(0.5f);
		btnAnglisht.setBounds(1206, 23, 25, 25);
		
		contentPane.add(btnAnglisht);
		txtKerkoProduktin.setFont(new Font("Arial", Font.BOLD, 15));
		txtKerkoProduktin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				UpdateTable();
			}
		});
		txtKerkoProduktin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblEmriIProduktit.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtKerkoProduktin.getText().length()==0)
					if(Gjuhesia.gjuha.equals("alb"))
						lblEmriIProduktit.setText("Emri i produktit:");
					else
						lblEmriIProduktit.setText("Product's name:");
			}
		});
		txtKerkoProduktin.setOpaque(false);
		txtKerkoProduktin.setColumns(10);
		txtKerkoProduktin.setBorder(null);
		txtKerkoProduktin.setBounds(22, 215, 181, 25);
		
		contentPane.add(txtKerkoProduktin);
		lblKerkoProduktin.setIcon(new ImageIcon(frmKtheProduktin.class.getResource("/imgs/kerkoProduktin.png")));
		lblKerkoProduktin.setBounds(215, 200, 45, 40);
		
		try
		{
			imgKerkoProduktin = ImageIO.read(frmKerkoProduktin.class.getResource("/imgs/kerkoProduktin.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Image dimgkerkoProduktin = imgKerkoProduktin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imageKerkoProduktin = new ImageIcon(dimgkerkoProduktin);
		lblKerkoProduktin.setIcon(imageKerkoProduktin);
		
		contentPane.add(lblKerkoProduktin);
		lblEmriIProduktit.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmriIProduktit.setForeground(new Color(0, 0, 51));
		lblEmriIProduktit.setBounds(22, 215, 136, 25);
		
		contentPane.add(lblEmriIProduktit);
		separator.setBounds(22, 238, 181, 2);
		
		contentPane.add(separator);
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setForeground(new Color(255, 255, 255));
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblPerdoruesi.setBackground(Color.WHITE);
		lblPerdoruesi.setBounds(1009, 56, 94, 21);
		
		contentPane.add(lblPerdoruesi);
		lblShitesi.setForeground(new Color(255, 255, 255));
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblShitesi.setBackground(Color.WHITE);
		lblShitesi.setBounds(1115, 56, 128, 21);
		lblShitesi.setText(Useri.getEmri() + " " + Useri.getMbiemri());
		contentPane.add(lblShitesi);
		lblNewLabel_1.setBackground(new Color(0, 0, 51));
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(0, 200, 1280, 3);
		
		contentPane.add(lblNewLabel_1);
		lblNewLabel.setBackground(new Color(153, 204, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 13, 1602, 189);
		
		contentPane.add(lblNewLabel);
		}
	public void InsertTable()
	{
		try 
		{
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr." , "Barkodi" , "Emertimi" ,"Sasia"  ,"Cmimi","Zbritja","Koha e shitjes", "Data e shitjes"  }, 0)
			{
				@Override
		        public boolean isCellEditable(int row, int column)
		        {
					return false;
		        }

			};
			
			conn = connectionClass.connectDb();
			String sql = "select  m.id, m.barkodiProduktit,m.sasia,m.cmimiShitjesMeTVSH,m.zbritja, m.kohaShitjes, m.dtShitjes, r.emriProduktit \r\n" + 
						"from mallishitur m,tblregjistrimimallit r  \r\n" + 
						"where m.barkodiProduktit = r.barkodi and m.dtShitjes >= '2018-01-01';";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = res.getString("id");
				String barkodi = res.getString("barkodiProduktit");
				String sasia = res.getString("sasia");
				String cmimiShitjesMeTVSH = res.getString("cmimiShitjesMeTVSH") + " €";
				String zbritja =  res.getString("zbritja")+" %";
				String kohaShitjes =  res.getString("kohaShitjes");
				String dtShitjes =  res.getString("dtShitjes");
				String emri =  res.getString("emriProduktit");
				
				
				
				model.addRow(new Object[] {c,barkodi,emri,sasia,cmimiShitjesMeTVSH,zbritja,kohaShitjes,dtShitjes});	
			}
			pst.close();
			table.setModel(model);
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
	protected static void KtheProduktet()
	{		
		
					String koha = (String)table.getValueAt(table.getSelectedRow(), 6);
					String data = (String)table.getValueAt(table.getSelectedRow(), 7);
					int id = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
					//ndryshim
					String sql1 = "";
					if(sasiaAktuale == sasiaPerKthim)
						sql1 = "delete from malliShitur where barkodiProduktit = '" +IDfshijProduktin+"' and kohaShitjes = '"+koha+"' and dtShitjes = '"+data+"' and id = "+id;
					else
						sql1 = "update mallishitur set sasia = sasia -"+sasiaPerKthim+" where barkodiProduktit = '" +IDfshijProduktin+"' and kohaShitjes = '"+koha+"' and dtShitjes = '"+data+"' and id = "+id;
					String sql2 = "update tblregjistrimimallit set sasia = sasia+"+sasiaPerKthim+" where barkodi= '"+IDfshijProduktin+"'"; //ndryshim
					try
					{
						Statement stmt1 = conn.createStatement();
						stmt1.executeUpdate(sql1);
						
						Statement stmt2 = conn.createStatement();
						stmt2.executeUpdate(sql2);
					}
					catch (SQLException e)
					{
						JOptionPane.showMessageDialog(null, e);
					}
					
					UpdateTable();
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					
					DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
					alignRight.setHorizontalAlignment(JLabel.RIGHT);	
					
					
					table.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
					table.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
					table.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
					table.getColumnModel().getColumn(4).setCellRenderer(alignRight);
					table.getColumnModel().getColumn(5).setCellRenderer(alignRight);
					table.getColumnModel().getColumn(6).setCellRenderer(alignRight);
					table.getColumnModel().getColumn(7).setCellRenderer(alignRight);
					
					
					table.getColumnModel().getColumn(0).setPreferredWidth(40);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(120);
					table.getColumnModel().getColumn(3).setPreferredWidth(50);
					table.getColumnModel().getColumn(4).setPreferredWidth(100);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		
	}
	private static void UpdateTable()
	{
		try 
		{
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr." , "Barkodi" , "Emertimi" ,"sasia"  ,"Cmimi","Zbritja","Koha e shitjes", "Data e shitjes"  }, 0)
			{
				@Override
		        public boolean isCellEditable(int row, int column)
		        {
					return false;
		        }

			};
			
			conn = connectionClass.connectDb();
			String sql = "select  m.id, m.barkodiProduktit,m.sasia,m.cmimiShitjesMeTVSH,m.zbritja, m.kohaShitjes, m.dtShitjes, r.emriProduktit \r\n" + 
						"from mallishitur m,tblregjistrimimallit r \r\n" + 
						"where m.barkodiProduktit = r.barkodi  and r.emriProduktit like '%"+txtKerkoProduktin.getText()+"%'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = res.getString("id");
				String barkodi = res.getString("barkodiProduktit");
				String sasia = res.getString("sasia");
				String cmimiShitjesMeTVSH = res.getString("cmimiShitjesMeTVSH")+ " €";
				String zbritja =  res.getString("zbritja");
				String kohaShitjes =  res.getString("kohaShitjes");
				String dtShitjes =  res.getString("dtShitjes");
				String emri =  res.getString("emriProduktit");
				
				
				
				model.addRow(new Object[] {c,barkodi,emri,sasia,cmimiShitjesMeTVSH,zbritja,kohaShitjes,dtShitjes});				
			}
			pst.close();
			table.setModel(model);
			
			DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
			alignCenter.setHorizontalAlignment(JLabel.CENTER);
			
			DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
			alignRight.setHorizontalAlignment(JLabel.RIGHT);	
			
			
			table.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
			table.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
			table.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
			table.getColumnModel().getColumn(4).setCellRenderer(alignRight);
			table.getColumnModel().getColumn(5).setCellRenderer(alignRight);
			table.getColumnModel().getColumn(6).setCellRenderer(alignRight);
			table.getColumnModel().getColumn(7).setCellRenderer(alignRight);
			
			
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
	private void NdryshoGjuhen()
	{
		if (Gjuhesia.gjuha=="eng")
		{
			mntmMbyll.setText("Exit");
			
			btnKtheProdukte.setText("Return products");
			lblPerdoruesi.setText("User:");
			
			if(txtKerkoProduktin.getText().length()==0)
			{
				lblEmriIProduktit.setText("Product's name:");
			}
			else
			{
				lblEmriIProduktit.setText("");
			}
			
			table.getColumnModel().getColumn(0).setHeaderValue("No.");
			table.getColumnModel().getColumn(1).setHeaderValue("Barcode");
			table.getColumnModel().getColumn(2).setHeaderValue("Description");
			table.getColumnModel().getColumn(3).setHeaderValue("Amount");
			table.getColumnModel().getColumn(4).setHeaderValue("Price without ATV");
			table.getColumnModel().getColumn(5).setHeaderValue("Discount");
			table.getColumnModel().getColumn(6).setHeaderValue("Time of sale");
			table.getColumnModel().getColumn(7).setHeaderValue("Date of sale");
			
			
			DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
			alignCenter.setHorizontalAlignment(JLabel.CENTER);
			
			DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
			alignRight.setHorizontalAlignment(JLabel.RIGHT);	

			scrollPane.setViewportView(table);
		}
		else
		{
			mntmMbyll.setText("Mbylle");
			
			btnKtheProdukte.setText("Kthe produkte");
			lblPerdoruesi.setText("Perdoruesi:");
			
			if(txtKerkoProduktin.getText().length()==0)
			{
				lblEmriIProduktit.setText("Emri i produktit:");
			}
			else
			{
				lblEmriIProduktit.setText("");
			}
			
			table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			table.getColumnModel().getColumn(1).setHeaderValue("Barkodi");
			table.getColumnModel().getColumn(2).setHeaderValue("Emertimi");
			table.getColumnModel().getColumn(3).setHeaderValue("Sasia");
			table.getColumnModel().getColumn(4).setHeaderValue("Cmimi");
			table.getColumnModel().getColumn(5).setHeaderValue("Zbritja");
			table.getColumnModel().getColumn(6).setHeaderValue("Koha e shitjes");
			table.getColumnModel().getColumn(7).setHeaderValue("Data e shitjes");

			scrollPane.setViewportView(table);
		}
	}
}
