

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;

public class frmRegjistrimiMallit extends JFrame {

	private JPanel contentPane;
	
	// Objekti per lidhje me db
	Connection conn = null;
	// Objekti per vendosjen e rezultatit
	ResultSet res = null;
	// Objekti per pyetsore
	PreparedStatement pst = null;
	private JTable tblPerdoruesit;
	private String barkodi;
	private JTextField txtKerko;
	private JComboBox cmbKerko;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblUser; 
	private JTextField txtTotali;
	private JLabel lblTotali;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRegjistrimiMallit frame = new frmRegjistrimiMallit();
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
		DefaultTableModel objM = new DefaultTableModel(new Object[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Totali"},0);
		if(Gjuhesia.gjuha.equals("alb"))
			objM = new DefaultTableModel(new Object[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Totali"},0);
		else
			objM = new DefaultTableModel(new Object[] {"Nr","Barcode","Product","Unit","Quantity","Price","Total"},0);
		String sql = "select regj.barkodi,regj.emriProduktit,regj.sasia,njm.pershkrimi,cmimet.cmimiShitjes " + 
				"from tblregjistrimiMallit regj,tblnjmatese njm,tblcmimet cmimet " + 
				"where regj.njMateseId=njm.id and regj.id=cmimet.produktetId  ";
		pst = conn.prepareStatement(sql);
		res=pst.executeQuery();
		double totPergj = 0;
		int counter = 1;
		while(res.next())
		{
			String bar = res.getString("barkodi");
			String prod = res.getString("emriProduktit");
			String njm = res.getString("pershkrimi");
			double sasia = res.getDouble("sasia");
			double cmim = res.getDouble("cmimiShitjes");
			double tot = sasia * cmim;
			String cmimi = String.format("%.2f €", cmim);
			String totStr = String.format("%.2f €",tot);
			totPergj = totPergj + tot;
			objM.addRow(new Object[] {counter,bar,prod,njm,sasia,cmimi,totStr});
			counter++;
		}
		
		getTblPerdoruesit().setModel(objM);
		pst.close();
		String totPergjStr = String.format("%.2f €", totPergj);
		txtTotali.setText(totPergjStr);
		
		
		
		
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
	tblPerdoruesit.setForeground(new Color(0, 0, 51));
	tblPerdoruesit.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
	tblPerdoruesit.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			DefaultTableModel model=(DefaultTableModel)tblPerdoruesit.getModel();
			barkodi=(String)model.getValueAt(tblPerdoruesit.getSelectedRow(),1);
			try 
			{
				String sql="select regj.barkodi,regj.emriProduktit,regj.sasia,njm.pershkrimi,cmimet.cmimiShitjes \r\n" + 
						"from tblregjistrimiMallit regj,tblnjmatese njm,tblcmimet cmimet\r\n" + 
						"where regj.njMateseId=njm.id and regj.id=cmimet.produktetId  " + 
						" and regj.barkodi='"+barkodi+"'";
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
	
	
	
	public frmRegjistrimiMallit() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try 
				{
					updateTable();					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmRegjistrimiMallit.class.getResource("/imgs/logo1icon1.png")));
		setResizable(false);
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(frmRegjistrimiMallit.class.getResource("/imgs/logo6.png")));
		setTitle("Besa Commerce");
		
		// Vendosja e lidhjes me DB permes klases sqlFiekConn dhe funksionit connectFiekDb
		conn = connectionClass.connectDb();

		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1255, 993);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JButton btnKthehuPrapa = new JButton("Kthehu prapa");
		btnKthehuPrapa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					frmMenu obj=new frmMenu();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		btnKthehuPrapa.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/icons8_Back_Arrow_48px.png")));
		btnKthehuPrapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu obj=new frmMenu();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				   {
						
						String sql = "DELETE FROM tblregjistrimiMallit WHERE barkodi='"+barkodi+"'";
						pst=conn.prepareStatement(sql);
						pst.execute();
						
					    pst.close();
					    updateTable();
					
						
					}
					catch(Exception e)
				    {
						JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
					}
			
			
			}
		});
	/*	String gjuha="Anglisht";
		if(gjuha=="Anglisht") {
			lblMallGjithsej.setText("In Total");
			lblVleraTotale.setText("Total Value(euro)");
				btnFshij.setText("Delete");
				btn.setText("Delete");
				
		}
		*/
		JMenuItem mntmShfaqTeGjitha = new JMenuItem("Shfaq të gjitha");
		mntmShfaqTeGjitha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					updateTable();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
				
				
			}
		});
		
		mntmShfaqTeGjitha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
		mnFile.add(mntmShfaqTeGjitha);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					try
					{
						DefaultTableModel objM = (DefaultTableModel)tblPerdoruesit.getModel();
						frmEdito.barkodiProduktit = (String)objM.getValueAt(tblPerdoruesit.getSelectedRow(), 1);
						frmEdito.emProduktit = (String)objM.getValueAt(tblPerdoruesit.getSelectedRow(), 2);
						String cm = (String)objM.getValueAt(tblPerdoruesit.getSelectedRow(), 5);
						cm = cm.replaceAll("[€\\s]", "");
						frmEdito.cmimiVjeter = Double.parseDouble(cm);
						frmEdito objE = new frmEdito();
						objE.setVisible(true);
						objE.setLocationRelativeTo(null);
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		mntmEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnFile.add(mntmEdit);
		
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnFile.add(mntmDelete);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		//qitu duhen ndryshime
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Connection conn = connectionClass.connectDb();
					
					int count = 1;
					java.util.Date objD = new java.util.Date();
					String dataSot = new SimpleDateFormat("yyyy-MM-dd").format(objD);
					SimpleDateFormat formatter4 = new SimpleDateFormat("HHmm");
					Date koha1 = new Date();
					String koha3 = formatter4.format(koha1);
					
					Document doc = new Document();
					String path="D:\\Regjistrimi\\RM" + dataSot +" "+koha3 +".pdf";
					PdfWriter.getInstance(doc, new FileOutputStream(path));
					doc.open();
					com.itextpdf.text.Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, BaseColor.BLACK);
					com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
					com.itextpdf.text.Font font0 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
					com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, BaseColor.BLACK);
					com.itextpdf.text.Image imde = com.itextpdf.text.Image.getInstance(frmShitja.class.getResource("/imgs/logo1.png"));
					imde.scaleToFit(250f, 100f);
					doc.add(imde);
					Paragraph  pr0  =  new  Paragraph("                                                         Regjistrimi i Mallit",font0);
					Paragraph pr2 = new Paragraph("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",font1);
					Paragraph  pr24  =  new  Paragraph("                                                                                                                                                                                                          ",font);
					doc.add(pr24);
					doc.add(pr0);
					doc.add(pr24);
					doc.add(pr2);
					

					
					
					
					float[] columnWidths = {30f, 140f, 170f, 110f,110f,120f,120f};
					com.itextpdf.text.pdf.PdfPTable tabela = new PdfPTable(columnWidths);
					tabela.setTotalWidth(PageSize.A4.getWidth()-70);
					tabela.setLockedWidth(true);
					if(Gjuhesia.gjuha.equals("alb"))
					{
						com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Nr",font2));
						c1.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Barkodi",font2));
						c2.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Emertimi",font2));
						c3.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Nj Matese",font2));
						c4.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Sasia",font2));
						c5.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Çmimi",font2));
						c6.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Totali",font2));
						c7.setBorderWidth(0);
						tabela.addCell(c1);
						tabela.addCell(c2);
						tabela.addCell(c3);
						tabela.addCell(c4);
						tabela.addCell(c5);
						tabela.addCell(c6);
						tabela.addCell(c7);
					}
					else
					{
						com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("No",font2));
						c1.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Barcode",font2));
						c2.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Product",font2));
						c3.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Meas. Unit",font2));
						c4.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Quantity",font2));
						c5.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Price",font2));
						c6.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Total",font2));
						c7.setBorderWidth(0);
						tabela.addCell(c1);
						tabela.addCell(c2);
						tabela.addCell(c3);
						tabela.addCell(c4);
						tabela.addCell(c5);
						tabela.addCell(c6);
						tabela.addCell(c7);
					}
					
					doc.add(tabela);
					doc.add(pr2);
					com.itextpdf.text.pdf.PdfPTable tabela1 = new PdfPTable(columnWidths);
					tabela1.setTotalWidth(PageSize.A4.getWidth()-70);
					tabela1.setLockedWidth(true);
					String query = "select regj.barkodi,regj.emriProduktit,regj.sasia,njm.pershkrimi,cmimet.cmimiShitjes " + 
							"from tblregjistrimiMallit regj,tblnjmatese njm,tblcmimet cmimet " + 
							"where regj.njMateseId=njm.id and regj.id=cmimet.produktetId  ";
					
					
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					double totPergjithshem = 0;
					while(rs.next())
					{
					
						String bar = rs.getString("barkodi");
						String Emri = rs.getString("emriProduktit");
			
						String pershkrimi = rs.getString("pershkrimi");
						double sasia = rs.getDouble("sasia");
						
						double cmimiblerjesmetvsh = rs.getDouble("cmimiShitjes");
						String cmimi = String.format("%.2f  €", cmimiblerjesmetvsh);
						double totali = sasia * cmimiblerjesmetvsh;
						String tot = String.format("%.2f  €", totali);
						totPergjithshem = totPergjithshem + totali;
						
						com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(""+count,font));
						c1.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(bar,font));
						c2.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(Emri,font));
						c3.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(pershkrimi,font));
						c4.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(String.format("%.2f", sasia),font));
						c5.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(cmimi,font));
						c6.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(tot,font));
						c7.setBorderWidth(0);
						tabela1.addCell(c1);
						tabela1.addCell(c2);
						tabela1.addCell(c3);
						tabela1.addCell(c4);
						tabela1.addCell(c5);
						tabela1.addCell(c6);
						tabela1.addCell(c7);
						count++;
						
						
						
					}
					pst.close();
					doc.add(tabela1);
					doc.add(pr2);
					Paragraph pr8 = new Paragraph("                                                                                                                                                                                                                    Total:  " + String.format("%.2f €", totPergjithshem),font);
					
					
					doc.add(pr8);
					
					
					doc.close();
					Desktop.getDesktop().open(new File(path));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
		});
		mntmPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnFile.add(mntmPrint);
		//mntmExit.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/icons8-exit-30.png")));
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);
		
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 257, 1230, 619);
		contentPane.add(scrollPane);
		
		setTblPerdoruesit(new JTable() 
		{
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
		getTblPerdoruesit().setFont(new Font("Arial", Font.BOLD, 14));
		getTblPerdoruesit().getTableHeader().setBackground(new Color(22,127,146));
		getTblPerdoruesit().getTableHeader().setForeground(Color.WHITE);
		getTblPerdoruesit().getTableHeader().setFont(new Font("Time New Roman", Font.BOLD, 14));
		scrollPane.setViewportView(getTblPerdoruesit());
		
		JButton btnShfaq = new JButton("Shfaq\r\n             ");
		btnShfaq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						try 
						{
							updateTable();
							
							
							
							
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + ex.getMessage());
						}
					}
			
			
			}
		});
		btnShfaq.setFont(new Font("Arial", Font.BOLD, 14));
		btnShfaq.setForeground(new Color(0, 0, 51));
		btnShfaq.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/icons8_Show_Property_48px_1.png")));
		btnShfaq.setBackground(Color.WHITE);
		btnShfaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					updateTable();					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
				
			}
		});
		btnShfaq.setBounds(212, 888, 190, 41);
		contentPane.add(btnShfaq);
		
		txtKerko = new JTextField();
		txtKerko.setFont(new Font("Arial", Font.BOLD, 14));
		txtKerko.setForeground(new Color(0, 0, 51));
		txtKerko.setBorder(null);
		txtKerko.setOpaque(false);
		txtKerko.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg0) {
    	    try 
    	       {
    	    	/*
    	    	  
				String sql = "select regj.barkodi,regj.emriProduktit,regj.sasia,njm.pershkrimi,furnitori.emriFurnitorit,cmimet.cmimiShitjes\r\n" + 
						"from tblfurnitoret furnitori,tblregjistrimiMallit regj,tblnjmatese njm,tblcmimet cmimet,tblfurnizonproduktin furnizo\r\n" + 
						"where regj.njMateseId=njm.id and regj.id=cmimet.produktetId  and furnizo.produktId=regj.id and furnizo.furnitoreId=furnitori.id";
				
    	    	 * 
    	    	 * 
    	    	 * 
    	    	 */
    	    		conn = connectionClass.connectDb();
	    			String selection=cmbKerko.getSelectedItem().toString();
	    			if(selection.equals("barcode"))  
	    			selection="barkodi";	
	    			if(selection.equals("Product Name"))  
	        			selection="emriProduktit";
	    			if(selection.equals("Quantity"))  
	        			selection="Sasia";
	
	    			DefaultTableModel objM = new DefaultTableModel(new Object[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Totali"},0);
					if(Gjuhesia.gjuha.equals("alb"))
						objM = new DefaultTableModel(new Object[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Totali"},0);
					else
						objM = new DefaultTableModel(new Object[] {"Nr","Barcode","Product","Unit","Quantity","Price","Total"},0);
					
					String sql = "select regj.barkodi,regj.emriProduktit,regj.sasia,njm.pershkrimi,cmimet.cmimiShitjes \r\n" + 
								"from tblregjistrimiMallit regj,tblnjmatese njm,tblcmimet cmimet\r\n" + 
								"where regj.njMateseId=njm.id and regj.id=cmimet.produktetId   "+ 
										" and regj."+selection+" like '%"+txtKerko.getText()+"%'";
	
					pst = conn.prepareStatement(sql);
					res=pst.executeQuery();
					double totPergj = 0;
					int counter = 1;
					while(res.next())
					{
						String bar = res.getString("barkodi");
						String prod = res.getString("emriProduktit");
						String njm = res.getString("pershkrimi");
						double sasia = res.getDouble("sasia");
						double cmim = res.getDouble("cmimiShitjes");
						double tot = sasia * cmim;
						String cmimi = String.format("%.2f €", cmim);
						String totStr = String.format("%.2f €",tot);
						totPergj = totPergj + tot;
						objM.addRow(new Object[] {counter,bar,prod,njm,sasia,cmimi,totStr});
						counter++;
					}
						
						
	
					getTblPerdoruesit().setModel(objM);
						
						
					pst.close();
					String totStr1 = String.format("%.2f €", totPergj);
					txtTotali.setText(totStr1);
				
				
    	    	}
    	    catch(Exception e)
    	        {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				try 
	    	       {
					conn = connectionClass.connectDb();
	    			String selection=cmbKerko.getSelectedItem().toString();
	    			if(selection.equals("barcode"))  
	    			selection="barkodi";	
	    			if(selection.equals("Product Name"))  
	        			selection="emriProduktit";
	    			if(selection.equals("Quantity"))  
	        			selection="Sasia";
	
	    			DefaultTableModel objM = new DefaultTableModel(new Object[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Totali"},0);
					if(Gjuhesia.gjuha.equals("alb"))
						objM = new DefaultTableModel(new Object[] {"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Totali"},0);
					else
						objM = new DefaultTableModel(new Object[] {"Nr","Barcode","Product","Unit","Quantity","Price","Total"},0);
					
					String sql = "select regj.barkodi,regj.emriProduktit,regj.sasia,njm.pershkrimi,cmimet.cmimiShitjes \r\n" + 
								"from tblregjistrimiMallit regj,tblnjmatese njm,tblcmimet cmimet\r\n" + 
								"where regj.njMateseId=njm.id and regj.id=cmimet.produktetId "+ 
										" and regj."+selection+" like '%"+txtKerko.getText()+"%'";
	
					pst = conn.prepareStatement(sql);
					res=pst.executeQuery();
					double totPergj = 0;
					int counter = 1;
					while(res.next())
					{
						String bar = res.getString("barkodi");
						String prod = res.getString("emriProduktit");
						String njm = res.getString("pershkrimi");
						double sasia = res.getDouble("sasia");
						double cmim = res.getDouble("cmimiShitjes");
						double tot = sasia * cmim;
						String cmimi = String.format("%.2f €", cmim);
						String totStr = String.format("%.2f €",tot);
						totPergj = totPergj + tot;
						objM.addRow(new Object[] {counter,bar,prod,njm,sasia,cmimi,totStr});
						counter++;
					}
						
						
	
					getTblPerdoruesit().setModel(objM);
						
						
					pst.close();
					String totStr1 = String.format("%.2f €", totPergj);
					txtTotali.setText(totStr1);
				
	    	       }
			    catch(Exception i)
			    {
			    	JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + i.getMessage());
			    }
	    	    
	    	      }
			}
		
		);
		txtKerko.setBounds(15, 229, 187, 23);
		contentPane.add(txtKerko);
		txtKerko.setColumns(10);
		
		
		JButton btnRegjistroMall = new JButton("Shto produkte");
		btnRegjistroMall.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						frmBlerjet obj=new frmBlerjet();
						obj.setVisible(true);
						obj.setLocationRelativeTo(null);
					}
			}
		});
		btnRegjistroMall.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/icons8_Add_Shopping_Cart_48px_1.png")));
		btnRegjistroMall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmBlerjet obj=new frmBlerjet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				 
				
			}
		});
		btnRegjistroMall.setForeground(new Color(0, 0, 51));
		btnRegjistroMall.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegjistroMall.setBackground(Color.WHITE);
		btnRegjistroMall.setBounds(10, 888, 190, 41);
		contentPane.add(btnRegjistroMall);
		
		JLabel lblKerko = new JLabel("Kerko:");
		lblKerko.setForeground(new Color(0, 0, 51));
		lblKerko.setFont(new Font("Arial", Font.BOLD, 14));
		lblKerko.setBounds(10, 214, 62, 23);
		contentPane.add(lblKerko);
		
		cmbKerko = new JComboBox();
		cmbKerko.setFont(new Font("Arial", Font.BOLD, 14));
		cmbKerko.setForeground(new Color(0, 0, 51));
		cmbKerko.setBackground(new Color(255, 255, 255));
		cmbKerko.setModel(new DefaultComboBoxModel(new String[] {"barkodi", "emriProduktit", "sasia"}));
		cmbKerko.setBounds(246, 217, 187, 35);
		contentPane.add(cmbKerko);
		
		JButton btnFshij = new JButton("Fshij              ");
		btnFshij.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						try 
						   {
								
								String sql = "DELETE FROM tblregjistrimiMallit WHERE barkodi='"+barkodi+"'";
								pst=conn.prepareStatement(sql);
								pst.execute();
								
							    pst.close();
							    updateTable();
								
								
							}
							catch(Exception ex)
						    {
								JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + ex.getMessage());
							}
					}
			}
		});
		btnFshij.setFont(new Font("Arial", Font.BOLD, 14));
		btnFshij.setForeground(new Color(0, 0, 51));
		
		btnFshij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   try 
			   {
					
					String sql = "DELETE FROM tblregjistrimiMallit WHERE barkodi='"+barkodi+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					
				    pst.close();
				    updateTable();	
				}
				catch(Exception e)
			    {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
			}
			
		});
		btnFshij.setBackground(Color.WHITE);
		btnFshij.setBounds(414, 888, 190, 41);
		btnFshij.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/icons8_Delete_48px.png")));

		contentPane.add(btnFshij);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(1203, 181, 46, 33);
		
		

		contentPane.add(lblFoto);
		
		JRadioButton rdbtnAlb = new JRadioButton("");
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "alb";
				lblUser.setText("Perdoruesi:");
				btnFshij.setText("Fshij");
				btnKthehuPrapa.setText("Ktheu Prapa");
				btnRegjistroMall.setText("Shto produkte");
				lblKerko.setText("Kerko:");
				btnShfaq.setText("Shfaq");
				btnFshij.setText("Fshij");
				mntmShfaqTeGjitha.setText("Shfaq të gjitha");
				mntmDelete.setText("Fshij");
				mntmExit.setText("Dil");
				lblTotali.setText("Vlera Totale:");
				//"Nr","Barkodi","Produkti","Nj Matese","Sasia","Cmimi","Totali","Furnitori"
				cmbKerko.setModel(new DefaultComboBoxModel(new String[] {"barkodi", "emriProduktit", "sasia"}));
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
				getTblPerdoruesit().getColumnModel().getColumn(5).setHeaderValue("Cmimi");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(6).setHeaderValue("Totali");
				scrollPane.setViewportView(getTblPerdoruesit());
				
			}
		});
		rdbtnAlb.setSelected(true);
		rdbtnAlb.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/alb.png")));
		buttonGroup.add(rdbtnAlb);
		rdbtnAlb.setBounds(1170, 9, 31, 23);
		contentPane.add(rdbtnAlb);
		
		JRadioButton rdbtnEng = new JRadioButton("");
		rdbtnEng.setOpaque(false);
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				Gjuhesia.gjuha = "eng";
				lblUser.setText("User:");
				btnRegjistroMall.setText("Add products");
				lblKerko.setText("Search:");
				btnShfaq.setText("Show");
				btnFshij.setText("Delete");
				btnKthehuPrapa.setText("Go Back");
				mntmShfaqTeGjitha.setText("Show");
				lblTotali.setText("Total Amount:");
				mntmDelete.setText("Delete");
				mntmExit.setText("Exit");
				cmbKerko.setModel(new DefaultComboBoxModel(new String[] {"barcode", "Product Name", "Quantity"}));
				//"Nr","Produkti","Nj Matese","Sasia","Cmimi","Totali","Furnitori"
				getTblPerdoruesit().getColumnModel().getColumn(0).setHeaderValue("Nr");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(1).setHeaderValue("Barcode");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(2).setHeaderValue("Product");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(3).setHeaderValue("Unit");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(4).setHeaderValue("Quantity");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(5).setHeaderValue("Price");
				scrollPane.setViewportView(getTblPerdoruesit());
				getTblPerdoruesit().getColumnModel().getColumn(6).setHeaderValue("Total");
				scrollPane.setViewportView(getTblPerdoruesit());
				
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/eng.png")));
		buttonGroup.add(rdbtnEng);
		rdbtnEng.setBounds(1203, 9, 31, 23);
		contentPane.add(rdbtnEng);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 250, 187, 2);
		contentPane.add(separator);

		
	
		btnKthehuPrapa.setForeground(new Color(0, 0, 51));
		btnKthehuPrapa.setFont(new Font("Arial", Font.BOLD, 14));
		btnKthehuPrapa.setBackground(Color.WHITE);
		btnKthehuPrapa.setBounds(616, 888, 190, 41);
		contentPane.add(btnKthehuPrapa);
		
		JLabel lblPerdoruesi = new JLabel("");
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 15));
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.LEFT);
		lblPerdoruesi.setForeground(Color.WHITE);
		lblPerdoruesi.setBounds(1122, 39, 118, 23);
		contentPane.add(lblPerdoruesi);
		
		 lblUser = new JLabel("Perdoruesi:");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Arial", Font.BOLD, 15));
		lblUser.setBounds(1019, 39, 93, 23);
		contentPane.add(lblUser);
		
		JLabel label_1 = new JLabel("");
		label_1.setBackground(new Color(153, 204, 255));
		label_1.setIcon(new ImageIcon(frmRegjistrimiMallit.class.getResource("/imgs/logo1.png")));
		label_1.setOpaque(true);
		label_1.setBounds(0, 0, 485, 190);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(153, 204, 255));
		label.setBounds(-14, 0, 1908, 204);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setOpaque(true);
		label_2.setBackground(new Color(0, 0, 51));
		label_2.setBounds(0, 203, 1900, 3);
		contentPane.add(label_2);
		lblPerdoruesi.setText(Useri.getEmri() + " " + Useri.getMbiemri());
		
		lblTotali = new JLabel("Vlera Totale:");
		lblTotali.setForeground(new Color(0, 0, 51));
		lblTotali.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotali.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotali.setBounds(923, 883, 118, 33);
		contentPane.add(lblTotali);
		
		txtTotali = new JTextField();
		txtTotali.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotali.setForeground(new Color(0, 0, 51));
		txtTotali.setEditable(false);
		txtTotali.setEnabled(false);
		txtTotali.setFont(new Font("Arial", Font.BOLD, 16));
		txtTotali.setBorder(null);
		txtTotali.setOpaque(false);
		txtTotali.setBounds(1053, 883, 187, 33);
		contentPane.add(txtTotali);
		txtTotali.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(1053, 914, 187, 2);
		contentPane.add(separator_2);
		if(Gjuhesia.gjuha.equals("eng")) {
			lblUser.setText("User:");
		}else {
			lblUser.setText("Perdoruesi:");
		}
		
		
	
		
				

		}
}