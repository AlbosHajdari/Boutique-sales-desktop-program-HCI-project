
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class frmFurnitoret extends JFrame {

	private JPanel contentPane;
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTable tblBleresit;
	private JScrollPane scrollPane;
	private JTextField txtKerkoFurnitorin;
	private JLabel lblEmriIFurnitorit = new JLabel("Emri i furnitorit");
	JMenuItem mntmMbylle = new JMenuItem("Mbylle");
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	
	JLabel lblKrko = new JLabel("K\u00EBrko:");
	private DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Furnitori", "Produkti","Qyteti i vendbanimit","Shteti i vendbanimit"}, 0);
	private JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
	private JLabel lblShitesi = new JLabel("");
	JButton btnShqip = new JButton("");
	JButton btnAnglisht = new JButton("");
	private JLabel label = new JLabel("");
	private JLabel label_3 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFurnitoret frame = new frmFurnitoret();
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
	public frmFurnitoret() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmFurnitoret.class.getResource("/imgs/logo1icon1.png")));
		setTitle("Besa Commerce");
		conn = connectionClass.connectDb();
		setBounds(100, 100, 1252, 900);
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		mntmMbylle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mntmMbylle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		mnFile.add(mntmMbylle);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 238, 1230, 588);
		contentPane.add(scrollPane);
		
		tblBleresit = new javax.swing.JTable(model) {
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
		tblBleresit.setFont(new Font("Arial", Font.BOLD, 13));
		tblBleresit.setCellSelectionEnabled(true);
		tblBleresit.getTableHeader().setReorderingAllowed(false);
		tblBleresit.getTableHeader().setBackground(new Color(22,127,146));
		tblBleresit.getTableHeader().setForeground(Color.WHITE);
		tblBleresit.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
		tblBleresit.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(tblBleresit);
		scrollPane.setViewportView(tblBleresit);
		
		lblKrko.setFont(new Font("Arial", Font.BOLD, 16));
		lblKrko.setBounds(1306, 968, 55, 21);
		contentPane.add(lblKrko);
		insertTable();
		
		tblBleresit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
		alignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		tblBleresit.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
		tblBleresit.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
		tblBleresit.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
		tblBleresit.getColumnModel().getColumn(4).setCellRenderer(alignCenter);
		
		
		tblBleresit.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblBleresit.getColumnModel().getColumn(1).setPreferredWidth(205);
		tblBleresit.getColumnModel().getColumn(2).setPreferredWidth(205);
		tblBleresit.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblBleresit.getColumnModel().getColumn(4).setPreferredWidth(70);
		
		tblBleresit.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
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
				Gjuhesia.gjuha="alb";
				NdryshoGjuhen();
				if(txtKerkoFurnitorin.getText().length()==0)
				{
					lblEmriIFurnitorit.setText("Emri i furnitorit:");
				}
				else
				{
					lblEmriIFurnitorit.setText("");
				}
			}
		});
		btnShqip.setIcon(new ImageIcon(frmFurnitoret.class.getResource("/imgs/alb.png")));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setAlignmentX(0.5f);
		btnShqip.setBounds(1181, 13, 25, 25);
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
				if(txtKerkoFurnitorin.getText().length()==0)
				{
					lblEmriIFurnitorit.setText("Supplier's name:");
				}
				else
				{
					lblEmriIFurnitorit.setText("");
				}
			}
		});
		btnAnglisht.setIcon(new ImageIcon(frmFurnitoret.class.getResource("/imgs/eng.png")));
		btnAnglisht.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnglisht.setAlignmentX(0.5f);
		btnAnglisht.setBounds(1209, 13, 25, 25);
		contentPane.add(btnAnglisht);
		
		txtKerkoFurnitorin = new JTextField();
		txtKerkoFurnitorin.setForeground(new Color(255, 255, 255));
		txtKerkoFurnitorin.setFont(new Font("Arial", Font.BOLD, 14));
		txtKerkoFurnitorin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				UpdateTable();
			}
		});
		NdryshoGjuhen();
		txtKerkoFurnitorin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblEmriIFurnitorit.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtKerkoFurnitorin.getText().length()==0)
					if(Gjuhesia.gjuha.equals("alb"))
						lblEmriIFurnitorit.setText("Emri i furnitorit:");
					else
						lblEmriIFurnitorit.setText("Supplier's name:");
					
			}
		});
		txtKerkoFurnitorin.setBorder(null);
		txtKerkoFurnitorin.setOpaque(false);
		txtKerkoFurnitorin.setColumns(10);
		txtKerkoFurnitorin.setBounds(12, 194, 229, 31);
		contentPane.add(txtKerkoFurnitorin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 223, 259, 2);
		contentPane.add(separator);
		lblEmriIFurnitorit.setForeground(new Color(255, 255, 255));
		lblEmriIFurnitorit.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblEmriIFurnitorit.setBounds(12, 194, 135, 26);
		contentPane.add(lblEmriIFurnitorit);
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setForeground(new Color(255, 255, 255));
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 15));
		lblPerdoruesi.setBackground(Color.WHITE);
		lblPerdoruesi.setBounds(1026, 51, 88, 21);
		
		contentPane.add(lblPerdoruesi);
		lblShitesi.setHorizontalAlignment(SwingConstants.LEFT);
		lblShitesi.setForeground(new Color(255, 255, 255));
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 15));
		lblShitesi.setBackground(Color.WHITE);
		lblShitesi.setText(Useri.getEmri()+ " " + Useri.getMbiemri());
		lblShitesi.setBounds(1118, 51, 116, 21);
		
		contentPane.add(lblShitesi);
		label_3.setBackground(new Color(0, 0, 51));
		label_3.setOpaque(true);
		label_3.setForeground(new Color(0, 0, 51));
		label_3.setBounds(0, 223, 1811, 3);
		
		contentPane.add(label_3);
		label.setIcon(new ImageIcon(frmFurnitoret.class.getResource("/imgs/logo1.png")));
		label.setBounds(10, 0, 558, 181);
		
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmFurnitoret.class.getResource("/imgs/icons8_Search_48px.png")));
		label_1.setBounds(241, 177, 48, 48);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBackground(new Color(153, 204, 255));
		label_2.setOpaque(true);
		label_2.setBounds(0, 0, 1811, 225);
		contentPane.add(label_2);
		}
	
	private void insertTable()
	{
		try 
		{
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Furnitori", "Produkti","Qyteti i vendbanimit","Shteti i vendbanimit"}, 0)
			{
				@Override
		        public boolean isCellEditable(int row, int column)
		        {
					return false;
		        }

			};
			int counter = 1;
			String sql = "select  f.emriFurnitorit, m.emriProduktit,q.qyteti,s.shteti from tblfurnitoret f,tblfurnizonproduktin fb,tblregjistrimimallit m,tblqytetet q, tblshtetet s where f.id=fb.furnitoreid and m.id= fb.produktid  and f.adreseid = q.id and q.sid = s.id";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String np = res.getString("emriFurnitorit");
				String em = res.getString("emriProduktit");
				String dt = res.getString("qyteti");
				String gj = res.getString("shteti");
				
				model.addRow(new Object[] {c,np,em,dt,gj});
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
	private void UpdateTable()
	{
		try 
		{
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Furnitori", "Produkti","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
			int counter = 1;
			String sql = "select  f.emriFurnitorit, m.emriProduktit,q.qyteti,s.shteti from tblfurnitoret f,tblfurnizonproduktin fb,tblregjistrimimallit m,tblqytetet q, tblshtetet s where f.id=fb.furnitoreid and m.id= fb.produktid  and f.adreseid = q.id and q.sid = s.id and f.emriFurnitorit like '%"+txtKerkoFurnitorin.getText()+"%'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String np = res.getString("emriFurnitorit");
				String em = res.getString("emriProduktit");
				String dt = res.getString("qyteti");
				String gj = res.getString("shteti");
				
				model.addRow(new Object[] {c,np,em,dt,gj});
				counter++;
				
			}
			
			pst.close();
			tblBleresit.setModel(model);
			
			tblBleresit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
			alignCenter.setHorizontalAlignment(JLabel.CENTER);	
			
			
			tblBleresit.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
			tblBleresit.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
			tblBleresit.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
			tblBleresit.getColumnModel().getColumn(4).setCellRenderer(alignCenter);
			
			
			tblBleresit.getColumnModel().getColumn(0).setPreferredWidth(10);
			tblBleresit.getColumnModel().getColumn(1).setPreferredWidth(205);
			tblBleresit.getColumnModel().getColumn(2).setPreferredWidth(205);
			tblBleresit.getColumnModel().getColumn(3).setPreferredWidth(70);
			tblBleresit.getColumnModel().getColumn(4).setPreferredWidth(70);
			
			tblBleresit.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
	private void NdryshoGjuhen()
	{
		if(Gjuhesia.gjuha=="eng")
		{
			if(txtKerkoFurnitorin.getText().length()==0)
			{
				lblEmriIFurnitorit.setText("Supplier's name:");
			}
			else
			{
				lblEmriIFurnitorit.setText("");
			}
			
			mntmMbylle.setText("Exit");
			
			
			lblPerdoruesi.setText("User:");
			
			tblBleresit.getColumnModel().getColumn(0).setHeaderValue("No.");
			tblBleresit.getColumnModel().getColumn(1).setHeaderValue("Supplier");
			tblBleresit.getColumnModel().getColumn(2).setHeaderValue("Product");
			tblBleresit.getColumnModel().getColumn(3).setHeaderValue("Current city");
			tblBleresit.getColumnModel().getColumn(4).setHeaderValue("Current country");
			
			scrollPane.setViewportView(tblBleresit);
		}
		else
		{
			if(txtKerkoFurnitorin.getText().length()==0)
			{
				lblEmriIFurnitorit.setText("Emri i furnitorit:");
			}
			else
			{
				lblEmriIFurnitorit.setText("");
			}
			
			mntmMbylle.setText("Mbylle");
			
			
			lblPerdoruesi.setText("Perdoruesi:");
			
			tblBleresit.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			tblBleresit.getColumnModel().getColumn(1).setHeaderValue("Furnitori");
			tblBleresit.getColumnModel().getColumn(2).setHeaderValue("Produkti");
			tblBleresit.getColumnModel().getColumn(3).setHeaderValue("Qyteti i vendbanimit");
			tblBleresit.getColumnModel().getColumn(4).setHeaderValue("Shteti i vendbanimit");
			
			scrollPane.setViewportView(tblBleresit);
		}
	}
}

