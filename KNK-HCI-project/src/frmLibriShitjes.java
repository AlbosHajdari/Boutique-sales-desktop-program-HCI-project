
import java.awt.EventQueue;
import com.itextpdf.text.BaseColor; 
import com.itextpdf.text.Document;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class frmLibriShitjes extends JFrame {

	
	private JPanel contentPane;
	Random objR = new Random();
	// Objekti per lidhje me db
	Connection conn = null;
	// Objekti per vendosjen e rezultatit
	ResultSet res = null;
	// Objekti per pyetsore
	PreparedStatement pst = null;
	private JTable tblPerdoruesit;
	private String barkodi;
	private JLabel lblUser;
	 public JDateChooser datePrej; 
	 private JRadioButton rdbtnEng;
	public JDateChooser dateDeri;
	private JRadioButton rdbtnAlb;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtVleraTotale;
	private JLabel lblVleraTotale;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLibriShitjes frame = new frmLibriShitjes();
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
	 * 
	 * 
	 */
public void updateTable() 
{
		
	try
	{
		int count = 1;
		double totPergjTbl = 0;
		DefaultTableModel objM = null;
		if(Gjuhesia.gjuha.equals("alb"))
			objM = new DefaultTableModel(new String[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Zbritje","Totali pa zbritje","Totali","Koha","Data","Punetori","Klienti"},0);
		else
			objM = new DefaultTableModel(new String[] {"No","Barcode","Product","Unit","Amount","Price","Disc.","Total without disc.","Total","Time","Date","Employee","Client"},0);
		String sql = "select regj.barkodi ,regj.emriProduktit ,ms.sasia ,njm.pershkrimi ,ms.cmimiShitjesMeTvsh,ms.zbritja ,ms.kohaShitjes, ms.dtShitjes, bl.emri,bl.mbiemri, st.emri,st.mbiemri"
				+ " from tblregjistrimimallit regj,tblnjmatese njm,mallishitur ms, tblBleresit bl, tblStafi st"
				+ " where regj.njMateseId=njm.id   and  regj.barkodi=ms.barkodiProduktit and ms.staffId = st.nrPersonal and ms.bleresId = bl.nrPersonal";
		pst = conn.prepareStatement(sql);
		res=pst.executeQuery();
		while(res.next())
		{
			String bar = res.getString("barkodi");
			String pro = res.getString("emriProduktit");
			String unit = res.getString("pershkrimi");
			double sasia = res.getDouble("sasia");
			double cmim = res.getDouble("cmimiShitjesMeTvsh");
			double zbritje = res.getDouble("zbritja");
			String zbrStr = String.format("%.2f", zbritje);
			zbrStr = zbrStr + " %";
			zbritje = zbritje/100;
			double totPaZb = sasia * cmim;
			double shumZbritur = totPaZb*zbritje;
			double totMeZb = totPaZb - shumZbritur;
			String totMeZbStr = String.format("%.2f €", totMeZb);
			String totPaZbrStr = String.format("%.2f €", totPaZb);
			String sasStr = String.format("%.2f", sasia);
			String cmStr = String.format("%.2f €", cmim);
			String koha = res.getString("kohaShitjes");
			String data = res.getString("dtShitjes");
			String punt = res.getString(11)+" "+res.getString(12);
			String bler = res.getString(9) + " "+res.getString(10);
			totPergjTbl = totPergjTbl + totMeZb;
			objM.addRow(new Object[] {count,bar,pro,unit,sasStr,cmStr,zbrStr,totPaZbrStr,totMeZbStr,koha,data,punt,bler});
			count++;
		}
		
		getTblPerdoruesit().setModel(objM);
		pst.close();
		txtVleraTotale.setText(String.format("%.2f €", totPergjTbl));
		
		
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
	}
	
		
}

public JTable getTblPerdoruesit() {
	return tblPerdoruesit;
}


public void setTblPerdoruesit(JTable tblPerdoruesit) {
	this.tblPerdoruesit = tblPerdoruesit;
	tblPerdoruesit.setBorder(new LineBorder(Color.BLUE));
	tblPerdoruesit.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			DefaultTableModel model=(DefaultTableModel)tblPerdoruesit.getModel();
			barkodi=(String)model.getValueAt(tblPerdoruesit.getSelectedRow(),1);
			try 
			{
				String sql="select regj.barkodi ,regj.emriProduktit ,ms.sasia ,njm.pershkrimi ,ms.cmimiShitjesMeTvsh,ms.zbritja ,ms.kohaShitjes, ms.dtShitjes, bl.emri,bl.mbiemri, st.emri,st.mbiemri " 
						+ " from tblregjistrimimallit regj,tblnjmatese njm,mallishitur ms, tblBleresit bl, tblStafi st "
						+ "where regj.njMateseId=njm.id and  regj.barkodi=ms.barkodiProduktit and ms.staffId = st.nrPersonal and ms.bleresId = bl.nrPersonal ";
				pst=conn.prepareStatement(sql);
				res=pst.executeQuery();
				
				
				
				pst.close();
				
				
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Gabim gjate mbushjes se textbox me te dhena "+e.getMessage());
			}
			
			
		}
	});
}
	
	
	
	public frmLibriShitjes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmLibriShitjes.class.getResource("/imgs/logo1icon1.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try 
				{
					updateTable();
			
		
				} 
				catch (Exception e) {
			
					JOptionPane.showMessageDialog(null, e.getMessage());
		
				}
				
			}
		});
		setResizable(false);
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
	//	setIconImage(Toolkit.getDefaultToolkit().getImage(frmLibriShitjes.class.getResource("/imgs/logo6.png")));
		setTitle("Besa Commerce");
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		datePrej = new JDateChooser();
		
		datePrej.setDateFormatString("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(2018, Calendar.JANUARY, 1);
		java.util.Date d1 = cal.getTime();
		datePrej.setDate(d1);
		
		datePrej.setBounds(770, 211, 203, 47);
		contentPane.add(datePrej);
		
		dateDeri = new JDateChooser();
		dateDeri.setDateFormatString("dd/MM/yyyy");
		java.util.Date d2 = new java.util.Date();
		dateDeri.setDate(d2);
		dateDeri.setBounds(1043, 211, 203, 47);
		contentPane.add(dateDeri);
		
		// Vendosja e lidhjes me DB permes klases sqlFiekConn dhe funksionit connectFiekDb
		conn = connectionClass.connectDb();

		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1264, 1006);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		
		
		JMenuItem mntmShfaqTeGjitha = new JMenuItem("Shfaq");
		mntmShfaqTeGjitha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					   updateTable();
				
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
				
				
			}
		});
		mntmShfaqTeGjitha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		mnFile.add(mntmShfaqTeGjitha);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		//mntmExit.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/icons8-exit-30.png")));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printoAlb();
			}
		});
		mntmPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnFile.add(mntmPrint);
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);
;
		

	
		
		
		
		JButton btnKthehuPrapa = new JButton("Kthehu prapa");
		btnKthehuPrapa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					frmMenu obj = new frmMenu();
					
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		btnKthehuPrapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				frmMenu obj = new frmMenu();
				
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnKthehuPrapa.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/icons8_Back_Arrow_48px.png")));
		btnKthehuPrapa.setForeground(Color.BLACK);
		btnKthehuPrapa.setFont(new Font("Arial", Font.BOLD, 14));
		btnKthehuPrapa.setBackground(Color.WHITE);
		btnKthehuPrapa.setBounds(440, 897, 203, 42);
		contentPane.add(btnKthehuPrapa);
		
		JButton btnPrinto = new JButton("Print");
		btnPrinto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					printoAlb();
				}
			}
		});
		btnPrinto.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/icons8_Bill_48px_2.png")));
		btnPrinto.setForeground(Color.BLACK);
		btnPrinto.setFont(new Font("Arial", Font.BOLD, 14));
		btnPrinto.setBackground(Color.WHITE);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 261, 1241, 628);
		contentPane.add(scrollPane);
		
		setTblPerdoruesit(new JTable() {
			Color ngjyra = new Color(102, 204, 255);
			@Override
			public Component prepareRenderer(TableCellRenderer ren, int r, int c) {
				Component com = super.prepareRenderer(ren, r, c);
				if (r % 2 == 0 && !isRowSelected(r)) {
					com.setBackground(new Color(135,206,250));
					com.setForeground(Color.BLACK);
				} else if (isRowSelected(r)) {
					com.setBackground(ngjyra);
					com.setForeground(Color.BLACK);
				} else {
					com.setBackground(Color.WHITE);
					com.setForeground(Color.BLACK);
				}

				return com;
			}
		});
		
		
		
		getTblPerdoruesit().setBackground(new Color(255, 215, 0));
		getTblPerdoruesit().setFont(new Font("Arial", Font.BOLD, 12));
		getTblPerdoruesit().getTableHeader().setBackground(new Color(22,127,146));
		getTblPerdoruesit().getTableHeader().setForeground(Color.WHITE);
		getTblPerdoruesit().getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		scrollPane.setViewportView(getTblPerdoruesit());
		
		
		JLabel lblPrej = new JLabel("Prej");
		lblPrej.setForeground(SystemColor.inactiveCaptionText);
		lblPrej.setFont(new Font("Arial", Font.BOLD, 14));
		lblPrej.setBounds(712, 220, 46, 38);
		contentPane.add(lblPrej);
		
		JLabel lblDeri = new JLabel("Deri");
		lblDeri.setForeground(SystemColor.inactiveCaptionText);
		lblDeri.setFont(new Font("Arial", Font.BOLD, 14));
		lblDeri.setBounds(995, 220, 46, 38);
		contentPane.add(lblDeri);
		
		JButton btnFiltro = new JButton("Filtro");
		btnFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					 try
		                {
		            	    DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		            	    int count = 1;
		            		double totPergjTbl = 0;
		            		DefaultTableModel objM = null;
		            		if(Gjuhesia.gjuha.equals("alb"))
		            			objM = new DefaultTableModel(new String[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Zbritje","Totali pa zbritje","Totali","Koha","Data","Punetori","Klienti"},0);
		            		else
		            			objM = new DefaultTableModel(new String[] {"No","Barcode","Product","Unit","Amount","Price","Disc.","Total without disc.","Total","Time","Date","Employee","Client"},0);
		            		
							String sql = "select regj.barkodi ,regj.emriProduktit ,ms.sasia ,njm.pershkrimi ,ms.cmimiShitjesMeTvsh,ms.zbritja ,ms.kohaShitjes, ms.dtShitjes, bl.emri,bl.mbiemri, st.emri,st.mbiemri " 
									+ " from tblregjistrimimallit regj,tblnjmatese njm,mallishitur ms, tblBleresit bl, tblStafi st "
									+ "where regj.njMateseId=njm.id  and  regj.barkodi=ms.barkodiProduktit and ms.staffId = st.nrPersonal and ms.bleresId = bl.nrPersonal and  ms.dtShitjes>='"+df.format(datePrej.getDate())+"' and ms.dtShitjes<='"+df.format(dateDeri.getDate())+"'";
							pst = conn.prepareStatement(sql);
							res=pst.executeQuery();
							while(res.next())
							{
								String bar = res.getString("barkodi");
								String pro = res.getString("emriProduktit");
								String unit = res.getString("pershkrimi");
								double sasia = res.getDouble("sasia");
								double cmim = res.getDouble("cmimiShitjesMeTvsh");
								double zbritje = res.getDouble("zbritja");
								String zbrStr = String.format("%.2f", zbritje);
								zbrStr = zbrStr + " %";
								zbritje = zbritje/100;
								double totPaZb = sasia * cmim;
								double shumZbritur = totPaZb*zbritje;
								double totMeZb = totPaZb - shumZbritur;
								String totMeZbStr = String.format("%.2f €", totMeZb);
								String totPaZbrStr = String.format("%.2f €", totPaZb);
								String sasStr = String.format("%.2f", sasia);
								String cmStr = String.format("%.2f €", cmim);
								String koha = res.getString("kohaShitjes");
								String data = res.getString("dtShitjes");
								String punt = res.getString(11)+" "+res.getString(12);
								String bler = res.getString(9) + " "+res.getString(10);
								totPergjTbl = totPergjTbl + totMeZb;
								objM.addRow(new Object[] {count,bar,pro,unit,sasStr,cmStr,zbrStr,totPaZbrStr,totMeZbStr,koha,data,punt,bler});
								count++;
							}
							
							getTblPerdoruesit().setModel(objM);
							pst.close();
							txtVleraTotale.setText(String.format("%.2f €", totPergjTbl));
							
					
						
							
						}
		               catch(Exception e)
		                {
							JOptionPane.showMessageDialog(null,e.getMessage());
						}	
						
				}
			}
		});
		btnFiltro.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/icons8_Search_Property_48px.png")));
	//	btnFiltro.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/icons8-low-priority-40.png")));
		btnFiltro.setFont(new Font("Arial", Font.BOLD, 14));
		btnFiltro.setBackground(Color.WHITE);
		btnFiltro.setForeground(Color.BLACK);
		btnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
                {
            	    DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
            	    int count = 1;
            		double totPergjTbl = 0;
            		DefaultTableModel objM = null;
            		if(Gjuhesia.gjuha.equals("alb"))
            			objM = new DefaultTableModel(new String[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Zbritje","Totali pa zbritje","Totali","Koha","Data","Punetori","Klienti"},0);
            		else
            			objM = new DefaultTableModel(new String[] {"No","Barcode","Product","Unit","Amount","Price","Disc.","Total without disc.","Total","Time","Date","Employee","Client"},0);
            		
					String sql = "select regj.barkodi ,regj.emriProduktit ,ms.sasia ,njm.pershkrimi ,ms.cmimiShitjesMeTvsh,ms.zbritja ,ms.kohaShitjes, ms.dtShitjes, bl.emri,bl.mbiemri, st.emri,st.mbiemri " 
							+ " from tblregjistrimimallit regj,tblnjmatese njm,mallishitur ms, tblBleresit bl, tblStafi st "
							+ "where regj.njMateseId=njm.id and  regj.barkodi=ms.barkodiProduktit and ms.staffId = st.nrPersonal and ms.bleresId = bl.nrPersonal and  ms.dtShitjes>='"+df.format(datePrej.getDate())+"' and ms.dtShitjes<='"+df.format(dateDeri.getDate())+"'";
					pst = conn.prepareStatement(sql);
					res=pst.executeQuery();
					while(res.next())
					{
						String bar = res.getString("barkodi");
						String pro = res.getString("emriProduktit");
						String unit = res.getString("pershkrimi");
						double sasia = res.getDouble("sasia");
						double cmim = res.getDouble("cmimiShitjesMeTvsh");
						double zbritje = res.getDouble("zbritja");
						String zbrStr = String.format("%.2f", zbritje);
						zbrStr = zbrStr + " %";
						zbritje = zbritje/100;
						double totPaZb = sasia * cmim;
						double shumZbritur = totPaZb*zbritje;
						double totMeZb = totPaZb - shumZbritur;
						totPergjTbl = totPergjTbl + totMeZb;
						String totMeZbStr = String.format("%.2f €", totMeZb);
						String totPaZbrStr = String.format("%.2f €", totPaZb);
						String sasStr = String.format("%.2f", sasia);
						String cmStr = String.format("%.2f €", cmim);
						String koha = res.getString("kohaShitjes");
						String data = res.getString("dtShitjes");
						String punt = res.getString(11)+" "+res.getString(12);
						String bler = res.getString(9) + " "+res.getString(10);
						objM.addRow(new Object[] {count,bar,pro,unit,sasStr,cmStr,zbrStr,totPaZbrStr,totMeZbStr,koha,data,punt,bler});
						count++;
					}
					
					String totPergjTblStr = String.format("%.2f €", totPergjTbl);
					txtVleraTotale.setText(totPergjTblStr);
					getTblPerdoruesit().setModel(objM);
					pst.close();
			
				
					
				}
               catch(Exception e)
                {
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
				
			
			}
		});
		btnFiltro.setBounds(10, 897, 203, 42);
		contentPane.add(btnFiltro);
		
		
		
		rdbtnAlb = new JRadioButton("");
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setSelected(true);
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "alb";
				lblPrej.setText("Prej");
				lblDeri.setText("Deri");
				btnFiltro.setText("Filtro");
				lblUser.setText("Perdoruesi:");
				mntmShfaqTeGjitha.setText("Shfaq");
				btnKthehuPrapa.setText("Kthehu");
				btnPrinto.setText("Printo");
				mntmExit.setText("Dalja");

				lblVleraTotale.setText("Vlera Totale:");
				//"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Zbritje","Totali pa zbritje","Totali","Koha e Shitjes","Data e Shitjes","Punetori","Klienti"
				getTblPerdoruesit().getColumnModel().getColumn(0).setHeaderValue("Nr");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(1).setHeaderValue("Barkodi");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(2).setHeaderValue("Produkti");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(3).setHeaderValue("Nj Matese");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(4).setHeaderValue("Sasia");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(5).setHeaderValue("Çmimi");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(6).setHeaderValue("Zbritje");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(7).setHeaderValue("Totali pa zbritje");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(8).setHeaderValue("Totali");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(9).setHeaderValue("Koha");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(10).setHeaderValue("Data");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(11).setHeaderValue("Punetori");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(12).setHeaderValue("Klienti");
				scrollPane.setViewportView(getTblPerdoruesit());
			
			}
		});
		rdbtnAlb.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/alb.png")));
		buttonGroup.add(rdbtnAlb);
		rdbtnAlb.setBounds(1180, 9, 32, 23);
		contentPane.add(rdbtnAlb);
		
		rdbtnEng = new JRadioButton("");
		rdbtnEng.setOpaque(false);
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Gjuhesia.gjuha = "eng";
				lblPrej.setText("From");
				lblDeri.setText("At");
				btnFiltro.setText("Filter");
				lblUser.setText("User:");
				mntmShfaqTeGjitha.setText("Show");
				btnKthehuPrapa.setText("Go back");
				btnPrinto.setText("Print");
				mntmExit.setText("Exit");
				lblVleraTotale.setText("Total Amount:");
				getTblPerdoruesit().getColumnModel().getColumn(0).setHeaderValue("No");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(1).setHeaderValue("Barcode");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(2).setHeaderValue("Product");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(3).setHeaderValue("Meas. Unit");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(4).setHeaderValue("Amount");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(5).setHeaderValue("Price");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(6).setHeaderValue("Disc.");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(7).setHeaderValue("Total without disc.");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(8).setHeaderValue("Total");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(9).setHeaderValue("Time");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(10).setHeaderValue("Date");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(11).setHeaderValue("Employee");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(12).setHeaderValue("Client");
				scrollPane.setViewportView(getTblPerdoruesit());
				
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/eng.png")));
		buttonGroup.add(rdbtnEng);
		rdbtnEng.setBounds(1214, 9, 32, 23);
		contentPane.add(rdbtnEng);
		
	
	//	btnPrinto.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/icons8-pdf-64.png")));
		btnPrinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        	printoAlb();
				}

			

				
				
			
		});
		btnPrinto.setBounds(225, 897, 203, 42);
		contentPane.add(btnPrinto);
		
		JLabel lblPerdoruesi = new JLabel("");
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.LEFT);
		lblPerdoruesi.setForeground(Color.WHITE);
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblPerdoruesi.setBounds(1139, 34, 112, 23);
		contentPane.add(lblPerdoruesi);
		
		lblUser = new JLabel("");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Arial", Font.BOLD, 14));
		lblUser.setBounds(1015, 34, 112, 23);
		contentPane.add(lblUser);
		
		
		JLabel label_2 = new JLabel("");
		label_2.setOpaque(true);
		label_2.setBackground(new Color(0,0,51));
		label_2.setBounds(-8, 200, 1878, 3);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBackground(new Color(153, 204, 255));
		label_3.setOpaque(true);
		label_3.setIcon(new ImageIcon(frmLibriShitjes.class.getResource("/imgs/logo1.png")));
		label_3.setBounds(10, 0, 520, 187);
		contentPane.add(label_3);
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(153,204,255));
		label.setOpaque(true);
		label.setBounds(0, 0, 1878, 203);
		contentPane.add(label);
		
		lblVleraTotale = new JLabel("Vlera Totale:");
		lblVleraTotale.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVleraTotale.setForeground(new Color(0, 0, 51));
		lblVleraTotale.setFont(new Font("Arial", Font.BOLD, 14));
		lblVleraTotale.setBounds(960, 901, 104, 35);
		contentPane.add(lblVleraTotale);
		
		if(Gjuhesia.gjuha.equals("alb")) {
			lblPrej.setText("Prej");
			lblDeri.setText("Deri");
			btnFiltro.setText("Filtro");
			lblUser.setText("Perdoruesi:");
			mntmShfaqTeGjitha.setText("Shfaq");
			btnKthehuPrapa.setText("Kthehu");
			btnPrinto.setText("Printo");
			mntmExit.setText("Dalja");
			lblVleraTotale.setText("Vlera Totale:");

		}
		else {
			lblPrej.setText("From");
			lblDeri.setText("At");
			btnFiltro.setText("Filter");
			lblUser.setText("User:");
			mntmShfaqTeGjitha.setText("Show");
			btnKthehuPrapa.setText("Go back");
			btnPrinto.setText("Print");
			mntmExit.setText("Exit");
			lblVleraTotale.setText("Total Amount:");
		}
		
		lblPerdoruesi.setText(Useri.getEmri()+" "+Useri.getMbiemri());
		
		
		
		txtVleraTotale = new JTextField();
		txtVleraTotale.setHorizontalAlignment(SwingConstants.RIGHT);
		txtVleraTotale.setForeground(new Color(0, 0, 51));
		txtVleraTotale.setOpaque(false);
		txtVleraTotale.setFont(new Font("Arial", Font.BOLD, 14));
		txtVleraTotale.setBorder(null);
		txtVleraTotale.setEnabled(false);
		txtVleraTotale.setBounds(1072, 901, 174, 35);
		contentPane.add(txtVleraTotale);
		txtVleraTotale.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1076, 934, 170, 2);
		contentPane.add(separator);
		
	
		
				

		}


public void printoAlb() {
	try {
		
		
		 

		
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		String dataSot = df.format(dateDeri.getDate());
		
		int counter  = objR.nextInt(5);
		Document doc = new Document();
		String path="D:\\Shitjet\\Libri" + dataSot +" " + counter +".pdf";
		PdfWriter.getInstance(doc, new FileOutputStream(path));
		doc.open();
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, BaseColor.BLACK);
		com.itextpdf.text.Font font0 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
		com.itextpdf.text.Image imde = com.itextpdf.text.Image.getInstance(frmShitja.class.getResource("/imgs/logo1.png"));
		imde.scaleToFit(250f, 100f);
		doc.add(imde);
		Paragraph  pr0  =  new  Paragraph("                                                            Libri i Shitjes",font0);
		Paragraph pr2 = new Paragraph("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",font);
		Paragraph  pr24  =  new  Paragraph("                                                                                                                                                                                                             ",font);
		doc.add(pr24);
		doc.add(pr0);
		doc.add(pr24);
		doc.add(pr2);
		

		
		
		
		float[] columnWidths = {30f, 140f, 80f, 100f,100f,100f,110f,100,110f};
		com.itextpdf.text.pdf.PdfPTable tabela = new PdfPTable(columnWidths);
		tabela.setTotalWidth(PageSize.A4.getWidth()-70);
		tabela.setLockedWidth(true);
		if(Gjuhesia.gjuha.equals("alb"))
		{
			com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Nr",font));
			c1.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Emertimi",font));
			c2.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Nj Matese",font));
			c3.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Sasia",font));
			c4.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Çmimi",font));
			c5.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Zbritje",font));
			c6.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Totali pa zbritje",font));
			c7.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Shuma e zbritur",font));
			c8.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Totali",font));
			c9.setBorderWidth(0);
			tabela.addCell(c1);
			tabela.addCell(c2);
			tabela.addCell(c3);
			tabela.addCell(c4);
			tabela.addCell(c5);
			tabela.addCell(c6);
			tabela.addCell(c7);
			tabela.addCell(c8);
			tabela.addCell(c9);
		}
		else
		{
			com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("No",font));
			c1.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Product",font));
			c2.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Meas. Unit",font));
			c3.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Quantity",font));
			c4.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Price",font));
			c5.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Disc.",font));
			c6.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Total without disc.",font));
			c7.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Disc. amount",font));
			c8.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Total",font));
			c9.setBorderWidth(0);
			tabela.addCell(c1);
			tabela.addCell(c2);
			tabela.addCell(c3);
			tabela.addCell(c4);
			tabela.addCell(c5);
			tabela.addCell(c6);
			tabela.addCell(c7);
			tabela.addCell(c8);
			tabela.addCell(c9);
		}
		
		doc.add(tabela);
		doc.add(pr2);
		com.itextpdf.text.pdf.PdfPTable tabela1 = new PdfPTable(columnWidths);
		tabela1.setTotalWidth(PageSize.A4.getWidth()-70);
		tabela1.setLockedWidth(true);
		conn = connectionClass.connectDb();
		int count = 1;
		double totPergjTbl = 0;
		String query = "select regj.barkodi ,regj.emriProduktit ,ms.sasia ,njm.pershkrimi ,ms.cmimiShitjesMeTvsh,ms.zbritja ,ms.kohaShitjes, ms.dtShitjes, bl.emri,bl.mbiemri, st.emri,st.mbiemri " 
				+ " from tblregjistrimimallit regj,tblnjmatese njm,mallishitur ms, tblBleresit bl, tblStafi st "  
				+ " where regj.njMateseId=njm.id and  regj.barkodi=ms.barkodiProduktit and ms.staffId = st.nrPersonal and ms.bleresId = bl.nrPersonal and  ms.dtShitjes>='"+df.format(datePrej.getDate())+"' and ms.dtShitjes<='"+df.format(dateDeri.getDate())+"'";
		pst = conn.prepareStatement(query);
		res = pst.executeQuery();
		while(res.next())
		{
	
			String pro = res.getString("emriProduktit");
			String unit = res.getString("pershkrimi");
			double sasia = res.getDouble("sasia");
			double cmim = res.getDouble("cmimiShitjesMeTvsh");
			double zbritje = res.getDouble("zbritja");
			String zbrStr = String.format("%.2f", zbritje);
			zbrStr = zbrStr + " %";
			zbritje = zbritje/100;
			double totPaZb = sasia * cmim;
			double shumZbritur = totPaZb*zbritje;
			double totMeZb = totPaZb - shumZbritur;
			totPergjTbl = totPergjTbl + totMeZb;
			String totMeZbStr = String.format("%.2f €", totMeZb);
			String totPaZbrStr = String.format("%.2f €", totPaZb);
			String shumZbriturStr = String.format("%.2f €", shumZbritur);
			String sasStr = String.format("%.2f", sasia);
			String cmStr = String.format("%.2f €", cmim);
			totPergjTbl = totPergjTbl + totMeZb;
			
			//columnNames = {"Nr", "Emertimi", "Nj Matese", "Sasia", "Cmimi","Zbritje","Totali pa Zbritje", "Shuma e zbritur", "Totali"};
			
			com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(""+count,font));
			c1.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(pro,font));
			c2.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(unit,font));
			c3.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(sasStr,font));
			c4.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(cmStr,font));
			c5.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(zbrStr,font));
			c6.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(totPaZbrStr,font));
			c7.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(shumZbriturStr,font));
			c8.setBorderWidth(0);
			com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(totMeZbStr,font));
			c9.setBorderWidth(0);
			tabela1.addCell(c1);
			tabela1.addCell(c2);
			tabela1.addCell(c3);
			tabela1.addCell(c4);
			tabela1.addCell(c5);
			tabela1.addCell(c6);
			tabela1.addCell(c7);
			tabela1.addCell(c8);
			tabela1.addCell(c9);
			count++;
		
		
		}
		pst.close();
		
		doc.add(tabela1);
		
		doc.add(pr2);
		Paragraph pr8 = new Paragraph("                                                                                                                                                                                                                                                  Total:           " + String.format("%.2f €", totPergjTbl),font);
		doc.add(pr8);
		
		
		doc.close();
		Desktop.getDesktop().open(new File(path));
	
	}
	catch(Exception e) {
		System.err.println(e.getMessage());
	}
	
	System.out.println("Itext Program executed");

}

}
