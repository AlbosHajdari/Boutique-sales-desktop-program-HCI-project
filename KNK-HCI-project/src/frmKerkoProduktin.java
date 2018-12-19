
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.datatransfer.*;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class frmKerkoProduktin extends JFrame {

	private JPanel contentPane;
	private JTextField txtKerkoProduktin;
	int i =0;
	int numri = 0;
	private String SelektoniNjeRresht = "Ju duhet te selektoni nje rresht.";
	private PreparedStatement pst;
	private ResultSet res;
	private Connection conn;
	
	private JTable table_1;
	private JScrollPane scrollPane;
	private JLabel lblKerkoProduktin;
	private JLabel lblEmriIProduktit;
	private JButton btnKopjoBarkodin = new JButton();
	private BufferedImage imgKerkoProduktin = null;
	private BufferedImage imgKopjoBarkodin = null;
	private JButton btnShqip;
	private JButton btnAnglisht;
	private JLabel lblShitesi = new JLabel("");
	private JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
	private JMenuItem mntmMbylle = new JMenuItem("Mbylle");
	private DefaultTableModel model = new DefaultTableModel(new String[]{"Nr." , "Barkodi" , "Emertimi" , "Sasia"}, 0);
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmKerkoProduktin frame = new frmKerkoProduktin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmKerkoProduktin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmKerkoProduktin.class.getResource("/imgs/logo1icon1.png")));
		setTitle("Besa Commerce");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 621);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtKerkoProduktin = new JTextField();
		txtKerkoProduktin.setFont(new Font("Arial", Font.BOLD, 16));
		txtKerkoProduktin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
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
		txtKerkoProduktin.setBorder(null);
		txtKerkoProduktin.setOpaque(false);
		txtKerkoProduktin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateTable();
			}
		});
		txtKerkoProduktin.setBounds(10, 203, 152, 20);
		contentPane.add(txtKerkoProduktin);
		txtKerkoProduktin.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 236, 596, 283);
		contentPane.add(scrollPane);
		
		table_1 = new javax.swing.JTable(model) {
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
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev) {
				if(ev.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try
					{
						int rreshti = table_1.getSelectedRow();
						StringSelection stringSelection = new StringSelection(String.valueOf(table_1.getValueAt(rreshti, 1)));
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(stringSelection, null);
						dispose();
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null,SelektoniNjeRresht);
					}
				}
			}
		});
		table_1.setBackground(new Color(153, 204, 255));
		table_1.setFont(new Font("Arial", Font.BOLD, 16));
		table_1.setCellSelectionEnabled(true);
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.getTableHeader().setBackground(new Color(22,127,146));
		table_1.getTableHeader().setForeground(Color.WHITE);
		table_1.getTableHeader().setFont(new Font("Time New Roman", Font.BOLD, 12));
		table_1.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table_1);
		

		
		lblKerkoProduktin = new JLabel("");
		lblKerkoProduktin.setIcon(new ImageIcon(frmKerkoProduktin.class.getResource("/imgs/kerkoProduktin.png")));
		lblKerkoProduktin.setBounds(175, 192, 45, 40);
		contentPane.add(lblKerkoProduktin);
		
		insertTable();
		
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
		alignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		table_1.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
		table_1.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
		table_1.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
		
		table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(205);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(205);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(70);

		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		try
		{
			imgKerkoProduktin = ImageIO.read(frmKerkoProduktin.class.getResource("/imgs/kerkoProduktin.png"));
			imgKopjoBarkodin = ImageIO.read(frmKerkoProduktin.class.getResource("/imgs/KopjoBarkodin.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Image dimgkerkoProduktin = imgKerkoProduktin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imageKerkoProduktin = new ImageIcon(dimgkerkoProduktin);
		lblKerkoProduktin.setIcon(imageKerkoProduktin);
		
		Image dimgKopjoBarkodin = imgKopjoBarkodin.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imageKopjoBarkodin = new ImageIcon(dimgKopjoBarkodin);
		btnKopjoBarkodin.setBackground(new Color(153, 204, 255));
		btnKopjoBarkodin.setFont(new Font("Arial", Font.BOLD, 16));
		btnKopjoBarkodin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnKopjoBarkodin.doClick();
			}
		});
		btnKopjoBarkodin.setForeground(new Color(255, 255, 255));
		btnKopjoBarkodin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnKopjoBarkodin.setText("Kopjo barkodin");
		btnKopjoBarkodin.setIcon(imageKopjoBarkodin);
		
		lblEmriIProduktit = new JLabel("Emri i produktit");
		lblEmriIProduktit.setForeground(new Color(0, 0, 51));
		lblEmriIProduktit.setBounds(13, 206, 136, 14);
		contentPane.add(lblEmriIProduktit);
		
		scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		
		btnKopjoBarkodin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int rreshti = table_1.getSelectedRow();
					StringSelection stringSelection = new StringSelection(String.valueOf(table_1.getValueAt(rreshti, 1)));
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					dispose();
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,SelektoniNjeRresht);
				}
			}
		});
		btnKopjoBarkodin.setBounds(10, 548, 198, 31);
		contentPane.add(btnKopjoBarkodin);
		
		NdryshoGjuhen();
		
		btnShqip = new JButton("");
		btnShqip.setOpaque(false);
		btnShqip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnShqip.doClick();
			}
		});
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gjuhesia.gjuha = "alb";
				NdryshoGjuhen();
				if(txtKerkoProduktin.getText().length()==0)
				{
					lblEmriIProduktit.setText("Emri i produktit:");
				}
				else
				{
					lblEmriIProduktit.setText("");
				}
			}
		});
		btnShqip.setIcon(new ImageIcon(frmKerkoProduktin.class.getResource("/imgs/alb.png")));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setAlignmentX(0.5f);
		btnShqip.setBounds(546, 28, 25, 25);
		contentPane.add(btnShqip);
		
		btnAnglisht = new JButton("");
		btnAnglisht.setOpaque(false);
		btnAnglisht.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnAnglisht.doClick();
			}
		});
		btnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gjuhesia.gjuha = "eng";
				NdryshoGjuhen();
				if(txtKerkoProduktin.getText().length()==0)
				{
					lblEmriIProduktit.setText("Product's name:");
				}
				else
				{
					lblEmriIProduktit.setText("");
				}
			}
		});
		btnAnglisht.setIcon(new ImageIcon(frmKerkoProduktin.class.getResource("/imgs/eng.png")));
		btnAnglisht.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnglisht.setAlignmentX(0.5f);
		btnAnglisht.setBounds(581, 28, 25, 25);
		contentPane.add(btnAnglisht);
		lblPerdoruesi.setForeground(new Color(0, 0, 51));
		lblPerdoruesi.setBackground(new Color(255, 255, 255));
		
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 16));
		lblPerdoruesi.setBounds(383, 560, 95, 17);
		contentPane.add(lblPerdoruesi);
		lblShitesi.setForeground(new Color(0, 0, 51));
		lblShitesi.setBackground(new Color(255, 255, 255));
		
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 16));
		lblShitesi.setBounds(490, 560, 128, 20);
		lblShitesi.setText(Useri.getEmri());
		contentPane.add(lblShitesi);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 618, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmMbylle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mntmMbylle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmMbylle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(13, 221, 152, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(0, 0, 51));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(0, 187, 618, 3);
		contentPane.add(lblNewLabel_1);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(frmKerkoProduktin.class.getResource("/imgs/puneLogo1.png")));
		label.setBounds(0, 13, 478, 177);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(153, 204, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(153, 204, 255));
		lblNewLabel.setBounds(0, 13, 618, 177);
		contentPane.add(lblNewLabel);
		
	}
	
	private void insertTable()
	{
		try 
		{
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr." , "Barkodi" , "Emertimi" , "Sasia"}, 0)
			{
				@Override
		        public boolean isCellEditable(int row, int column)
		        {
					return false;
		        }

			};
			int counter = 1;
			
			conn = connectionClass.connectDb();
			String sql = "select barkodi ,emriProduktit, sasia from tblRegjistrimiMallit ";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String barkodi = res.getString("barkodi");
				String emri = res.getString("emriProduktit");
				
				String sasia = res.getString("sasia");
				
				model.addRow(new Object[] {c,barkodi,emri,sasia});
				counter++;
				
			}
			pst.close();
			table_1.setModel(model);
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
	
	private void updateTable()
	{
		try 
		{
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr." , "Barkodi" , "Emertimi" , "Sasia"}, 0)
			{
				@Override
		        public boolean isCellEditable(int row, int column)
		        {
					return false;
		        }

			};
			int counter = 1;
			
			conn = connectionClass.connectDb();
			String sql = "select barkodi , emriProduktit, sasia from tblRegjistrimiMallit where emriProduktit like '%"+txtKerkoProduktin.getText()+"%'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String barkodi = res.getString("barkodi");
				String emri = res.getString("emriProduktit");
				
				String sasia = res.getString("sasia");
				
				model.addRow(new Object[] {c,barkodi,emri,sasia});
				counter++;
				
			}
			pst.close();
			table_1.setModel(model);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(205);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(205);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(70);
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
			if(txtKerkoProduktin.getText().length()==0)
			{
				lblEmriIProduktit.setText("Product's name:");
			}
			else
			{
				lblEmriIProduktit.setText("");
			}
			
			lblEmriIProduktit.setText("Product's name");
			btnKopjoBarkodin.setText("Copy the barcode");
			lblPerdoruesi.setText("User:");
			mntmMbylle.setText("Exit");
			
			table_1.getColumnModel().getColumn(0).setHeaderValue("No.");
			table_1.getColumnModel().getColumn(1).setHeaderValue("Barcode");
			table_1.getColumnModel().getColumn(2).setHeaderValue("Description");
			table_1.getColumnModel().getColumn(3).setHeaderValue("Amount");
			
			scrollPane.setViewportView(table_1);
		}
		else
		{
			
			if(txtKerkoProduktin.getText().length()==0)
			{
				lblEmriIProduktit.setText("Emri i produktit:");
			}
			else
			{
				lblEmriIProduktit.setText("");
			}
			
			lblEmriIProduktit.setText("Emri i produktit");
			btnKopjoBarkodin.setText("Kopjo barkodin");
			lblPerdoruesi.setText("Perdoruesi:");
			mntmMbylle.setText("Mbylle");
			
			table_1.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			table_1.getColumnModel().getColumn(1).setHeaderValue("Barkodi");
			table_1.getColumnModel().getColumn(2).setHeaderValue("Emertimi");
			table_1.getColumnModel().getColumn(3).setHeaderValue("Sasia");
			
			scrollPane.setViewportView(table_1);
		}
	}
}
