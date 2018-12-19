
import java.sql.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.WindowFocusListener;
public class frmRaportet extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtCmimiMeTvsh;
	private JButton btnKthehu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Color ngjyra = new Color(22,127,146);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					 
					frmRaportet frame = new frmRaportet();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				
					


					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	
	public static String KohaTani() {
		long totalMilliseconds = System.currentTimeMillis();  // Obtain the total seconds since midnight, Jan 1, 1970 7 
		long totalSeconds = totalMilliseconds / 1000; // Compute the current second in the minute in the hour 10 
		long currentSecond = totalSeconds % 60;// Obtain the total minutes 13 
		long totalMinutes = totalSeconds / 60; // Compute the current minute in the hour 16 
		long currentMinute = totalMinutes % 60; // Obtain the total hours 19 
		long totalHours = totalMinutes / 60; // Compute the current hour 22 
		long currentHour = totalHours % 24;  // Display results 25 
		currentHour = currentHour + 2;
		
		String koha =  currentHour + "-" + currentMinute + "-" + currentSecond;
		
		return koha;
	}
	

	/**
	 * Create the frame.
	 */
	public frmRaportet() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmRaportet.class.getResource("/imgs/logo1icon1.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				frmDataShitja dt = new frmDataShitja();
				
				dt.setVisible(true);
				dt.setLocationRelativeTo(null);
			}
		});
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				String dataParaprake = new SimpleDateFormat("yyyy-MM-dd").format(frmDataShitja.dataDikur);
				String dataSot = new SimpleDateFormat("yyyy-MM-dd").format(frmDataShitja.dataSot);
				Connection conn;
				try {
					conn = connectionClass.connectDb();
					DefaultTableModel model = null;
					if(Gjuhesia.gjuha.equals("alb")) {
					 model = new DefaultTableModel(new String[]{"Nr.",  "Produkti","Nj Matese" ,"Sasia" , "Cmimi blerjes","Totali", "Emri I Furnitorit", "Data e Blerjes"}, 0);
					}
					else {
					 model = new DefaultTableModel(new String[]{"Nr.",  "Product","Unit", "Quantity" , "Price", "Total","Supplier","Purchase date"}, 0);
					}
					int counter = 1;
					String query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiBlerjes, tf.emriFurnitorit\r\n" + 
							"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
							"WHERE tm.njmateseId = tn.id \r\n" + 
							"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi  AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tf.emriFurnitorit = '"+frmDataShitja.emriIfurnitoritTeMarre+"' AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
					
					if(frmDataShitja.emriIfurnitoritTeMarre.equals(""))
						 query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
							"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
							"WHERE tm.njmateseId = tn.id \r\n" + 
							"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
					else
						query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
						"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
						"WHERE tm.njmateseId = tn.id \r\n" + 
						"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tf.emriFurnitorit = '"+frmDataShitja.emriIfurnitoritTeMarre+"' AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					double totPergjithshem = 0;
					while(rs.next())
					{
					
						String c = String.valueOf(counter);
						String Emri = rs.getString("emriProduktit");
			
						String pershkrimi = rs.getString("pershkrimi");
						double sasia = rs.getDouble("sasia");
						
						String dt1 = rs.getString("dtBlerjes");
						String[] dt = dt1.split("[-]");
						String dtBlerjes = dt[2]+"/"+dt[1]+"/"+dt[0];
						double cmimiblerjesmetvsh = rs.getDouble("cmimiblerjes");
						String cmimi = String.format("%.2f €", cmimiblerjesmetvsh);
						double totali = sasia * cmimiblerjesmetvsh;
						String tot = String.format("%.2f €", totali);
						String emriFurnitorit = rs.getString("emriFurnitorit");
						totPergjithshem = totPergjithshem + totali;
						model.addRow(new Object[] {c,Emri,pershkrimi,sasia,cmimi,tot,emriFurnitorit,dtBlerjes});
						counter++;
						
					}
					pst.close();
					table.setModel(model);
					table.getColumnModel().getColumn(0).setPreferredWidth(27);
					
					String totalCmimi = String.format("%.2f", totPergjithshem);
					
					
					txtCmimiMeTvsh.setText(totalCmimi);
					
					
					
				
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		setBackground(Color.WHITE);
		setTitle("Besa Commerce");

		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1237, 969);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShikoTeGjitha = new JButton("Shiko te gjitha blerjet");
		btnShikoTeGjitha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					Connection conn;
					try {
						conn = connectionClass.connectDb();
						DefaultTableModel model = null;
						if(Gjuhesia.gjuha.equals("alb")) {
						 model = new DefaultTableModel(new String[]{"Nr.",  "Produkti","Nj Matese" ,"Sasia" , "Cmimi blerjes","Totali", "Emri I Furnitorit", "Data e Blerjes"}, 0);
						}
						else {
						 model = new DefaultTableModel(new String[]{"Nr.",  "Product","Unit", "Quantity" , "Price", "Total","Supplier","Purchase date"}, 0);
						}
						int counter = 1;
						
						String query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
								"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
								"WHERE tm.njmateseId = tn.id \r\n" + 
								"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id";
					
						PreparedStatement pst = conn.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						double totPergjithshem = 0;
						while(rs.next())
						{
						
							String c = String.valueOf(counter);
							String Emri = rs.getString("emriProduktit");
				
							String pershkrimi = rs.getString("pershkrimi");
							double sasia = rs.getDouble("sasia");
							String dt1 = rs.getString("dtBlerjes");
							String[] dt = dt1.split("[-]");
							String dtBlerjes = dt[2]+"/"+dt[1]+"/"+dt[0];
							double cmimiblerjesmetvsh = rs.getDouble("cmimiblerjes");
							String cmimi = String.format("%.2f €", cmimiblerjesmetvsh);
							double totali = sasia * cmimiblerjesmetvsh;
							String tot = String.format("%.2f €", totali);
							String emriFurnitorit = rs.getString("emriFurnitorit");
							totPergjithshem = totPergjithshem + totali;
							model.addRow(new Object[] {c,Emri,pershkrimi,sasia,cmimi,tot,emriFurnitorit,dtBlerjes});
							counter++;
							
						}
						pst.close();
						table.setModel(model);
						table.getColumnModel().getColumn(0).setPreferredWidth(27);
						
						String totalCmimi = String.format("%.2f", totPergjithshem);
						
						
						txtCmimiMeTvsh.setText(totalCmimi);
						frmDataShitja.emriIfurnitoritTeMarre = "";
						
					
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
				}
				
			
		});
		btnShikoTeGjitha.setForeground(new Color(0, 0, 51));
		btnShikoTeGjitha.setBackground(new Color(255, 255, 255));
		btnShikoTeGjitha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				try {
					conn = connectionClass.connectDb();
					DefaultTableModel model = null;
					if(Gjuhesia.gjuha.equals("alb")) {
					 model = new DefaultTableModel(new String[]{"Nr.",  "Produkti","Nj Matese" ,"Sasia" , "Cmimi blerjes","Totali", "Emri I Furnitorit", "Data e Blerjes"}, 0);
					}
					else {
					 model = new DefaultTableModel(new String[]{"Nr.",  "Product","Unit", "Quantity" , "Price", "Total","Supplier","Purchase date"}, 0);
					}
					int counter = 1;
					
					String query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
							"FROM tblmalliblere tm, tblfurnizonproduktin as fp,tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
							"WHERE tm.njmateseId = tn.id \r\n" + 
							"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id";
				
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					double totPergjithshem = 0;
					while(rs.next())
					{
					
						String c = String.valueOf(counter);
						String Emri = rs.getString("emriProduktit");
			
						String pershkrimi = rs.getString("pershkrimi");
						double sasia = rs.getDouble("sasia");
						String dt1 = rs.getString("dtBlerjes");
						String[] dt = dt1.split("[-]");
						String dtBlerjes = dt[2]+"/"+dt[1]+"/"+dt[0];
						double cmimiblerjesmetvsh = rs.getDouble("cmimiblerjes");
						String cmimi = String.format("%.2f €", cmimiblerjesmetvsh);
						double totali = sasia * cmimiblerjesmetvsh;
						String tot = String.format("%.2f €", totali);
						String emriFurnitorit = rs.getString("emriFurnitorit");
						totPergjithshem = totPergjithshem + totali;
						model.addRow(new Object[] {c,Emri,pershkrimi,sasia,cmimi,tot,emriFurnitorit,dtBlerjes});
						counter++;
						
					}
					pst.close();
					table.setModel(model);
					table.getColumnModel().getColumn(0).setPreferredWidth(27);
					
					String totalCmimi = String.format("%.2f", totPergjithshem);
					
					
					txtCmimiMeTvsh.setText(totalCmimi);
					frmDataShitja.emriIfurnitoritTeMarre = "";
					
					
				
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(12, 250, 1061, 617);
		contentPane.add(scrollPane);
		
		table = new javax.swing.JTable() {
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
				
				table.setBackground(new Color(255, 255, 255));
				table.setFont(new Font("Arial", Font.BOLD, 12));
				scrollPane.setViewportView(table);

				return com;
			}
		};
		table.setForeground(new Color(0, 0, 51));
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.getTableHeader().setBackground(new Color(22,127,146));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

		scrollPane.setViewportView(table);
		
		
		
		JButton btnImportoNePdf = new JButton("Printo\r\n");
		btnImportoNePdf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try {
						
						Connection conn = connectionClass.connectDb();
						
						int count = 1;
						String dataParaprake = new SimpleDateFormat("yyyy-MM-dd").format(frmDataShitja.dataDikur);
						String dataSot = new SimpleDateFormat("yyyy-MM-dd").format(frmDataShitja.dataSot);

						
						String KohaTani = KohaTani();
						Document doc = new Document();
						String path="D:\\Blerje\\" + dataSot +" " + KohaTani +".pdf";
						PdfWriter.getInstance(doc, new FileOutputStream(path));
						doc.open();
						com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, BaseColor.BLACK);
						com.itextpdf.text.Font font0 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
						com.itextpdf.text.Image imde = com.itextpdf.text.Image.getInstance(frmShitja.class.getResource("/imgs/logo1.png"));
						imde.scaleToFit(250f, 100f);
						doc.add(imde);
						Paragraph  pr0  =  new  Paragraph("                                                            Libri i Blerjes",font0);
						Paragraph pr2 = new Paragraph("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",font);
						Paragraph  pr24  =  new  Paragraph("                                                                                                                                                                                                             ",font);
						doc.add(pr24);
						doc.add(pr0);
						doc.add(pr24);
						doc.add(pr2);
						

						
						
						
						float[] columnWidths = {30f, 160f, 90f, 110f,110f,120f,120f,90f};
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
							com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Totali",font));
							c6.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Data e blerjes",font));
							c7.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Furnitori",font));
							c9.setBorderWidth(0);
							tabela.addCell(c1);
							tabela.addCell(c2);
							tabela.addCell(c3);
							tabela.addCell(c4);
							tabela.addCell(c5);
							tabela.addCell(c6);
							tabela.addCell(c9);
							tabela.addCell(c7);
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
							com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Total.",font));
							c6.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Date",font));
							c7.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Supplier",font));
							c8.setBorderWidth(0);
							tabela.addCell(c1);
							tabela.addCell(c2);
							tabela.addCell(c3);
							tabela.addCell(c4);
							tabela.addCell(c5);
							tabela.addCell(c6);
							tabela.addCell(c8);
							tabela.addCell(c7);
						}
						
						doc.add(tabela);
						doc.add(pr2);
						com.itextpdf.text.pdf.PdfPTable tabela1 = new PdfPTable(columnWidths);
						tabela1.setTotalWidth(PageSize.A4.getWidth()-70);
						tabela1.setLockedWidth(true);
						String query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiBlerjes, tf.emriFurnitorit\r\n" + 
								"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
								"WHERE tm.njmateseId = tn.id \r\n" + 
								"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi  AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tf.emriFurnitorit = '"+frmDataShitja.emriIfurnitoritTeMarre+"' AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
						
						if(frmDataShitja.emriIfurnitoritTeMarre.equals(""))
							 query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
								"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
								"WHERE tm.njmateseId = tn.id \r\n" + 
								"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
						else
							query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
							"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
							"WHERE tm.njmateseId = tn.id \r\n" + 
							"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tf.emriFurnitorit = '"+frmDataShitja.emriIfurnitoritTeMarre+"' AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
						PreparedStatement pst = conn.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						double totPergjithshem = 0;
						while(rs.next())
						{
						
							String Emri = rs.getString("emriProduktit");
				
							String pershkrimi = rs.getString("pershkrimi");
							double sasia = rs.getDouble("sasia");
							String dt1 = rs.getString("dtBlerjes");
							String[] dt = dt1.split("[-]");
							String dtBlerjes = dt[2]+"/"+dt[1]+"/"+dt[0];
							double cmimiblerjesmetvsh = rs.getDouble("cmimiblerjes");
							String cmimi = String.format("%.2f €", cmimiblerjesmetvsh);
							double totali = sasia * cmimiblerjesmetvsh;
							String tot = String.format("%.2f €", totali);
							String emriFurnitorit = rs.getString("emriFurnitorit");
							totPergjithshem = totPergjithshem + totali;
							
							com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(""+count,font));
							c1.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(Emri,font));
							c2.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(pershkrimi,font));
							c3.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(String.format("%.2f", sasia),font));
							c4.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(cmimi,font));
							c5.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(tot,font));
							c7.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(dtBlerjes,font));
							c8.setBorderWidth(0);
							com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(emriFurnitorit,font));
							c9.setBorderWidth(0);
							tabela1.addCell(c1);
							tabela1.addCell(c2);
							tabela1.addCell(c3);
							tabela1.addCell(c4);
							tabela1.addCell(c5);
							tabela1.addCell(c7);
							tabela1.addCell(c9);
							tabela1.addCell(c8);
							count++;
							
							
							
						}
						pst.close();
						doc.add(tabela1);
						
						doc.add(pr2);
						Paragraph pr8 = new Paragraph("                                                                                                                                                                                                                                                        Total:           " + String.format("%.2f €", totPergjithshem),font);
						doc.add(pr8);
						
						
						doc.close();
						Desktop.getDesktop().open(new File(path));
						
					}
						catch(Exception e) {
							System.err.println(e.getMessage());
						}
				}
				
			}
		});
		btnImportoNePdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Connection conn = connectionClass.connectDb();
					
					int count = 1;
					String dataParaprake = new SimpleDateFormat("yyyy-MM-dd").format(frmDataShitja.dataDikur);
					String dataSot = new SimpleDateFormat("yyyy-MM-dd").format(frmDataShitja.dataSot);

					
					String KohaTani = KohaTani();
					Document doc = new Document();
					String path="D:\\Blerje\\" + dataSot +" " + KohaTani +".pdf";
					PdfWriter.getInstance(doc, new FileOutputStream(path));
					doc.open();
					com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, BaseColor.BLACK);
					com.itextpdf.text.Font font0 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
					com.itextpdf.text.Image imde = com.itextpdf.text.Image.getInstance(frmShitja.class.getResource("/imgs/logo1.png"));
					imde.scaleToFit(250f, 100f);
					doc.add(imde);
					Paragraph  pr0  =  new  Paragraph("                                                            Libri i Blerjes",font0);
					Paragraph pr2 = new Paragraph("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",font);
					Paragraph  pr24  =  new  Paragraph("                                                                                                                                                                                                             ",font);
					doc.add(pr24);
					doc.add(pr0);
					doc.add(pr24);
					doc.add(pr2);
					

					
					
					
					float[] columnWidths = {30f, 160f, 90f, 110f,110f,120f,120f,90f};
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
						com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Totali",font));
						c6.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Data e blerjes",font));
						c7.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Furnitori",font));
						c9.setBorderWidth(0);
						tabela.addCell(c1);
						tabela.addCell(c2);
						tabela.addCell(c3);
						tabela.addCell(c4);
						tabela.addCell(c5);
						tabela.addCell(c6);
						tabela.addCell(c9);
						tabela.addCell(c7);
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
						com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Total.",font));
						c6.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Date",font));
						c7.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Supplier",font));
						c8.setBorderWidth(0);
						tabela.addCell(c1);
						tabela.addCell(c2);
						tabela.addCell(c3);
						tabela.addCell(c4);
						tabela.addCell(c5);
						tabela.addCell(c6);
						tabela.addCell(c8);
						tabela.addCell(c7);
					}
					
					doc.add(tabela);
					doc.add(pr2);
					com.itextpdf.text.pdf.PdfPTable tabela1 = new PdfPTable(columnWidths);
					tabela1.setTotalWidth(PageSize.A4.getWidth()-70);
					tabela1.setLockedWidth(true);
					String query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiBlerjes, tf.emriFurnitorit\r\n" + 
							"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
							"WHERE tm.njmateseId = tn.id \r\n" + 
							"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi  AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tf.emriFurnitorit = '"+frmDataShitja.emriIfurnitoritTeMarre+"' AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
					
					if(frmDataShitja.emriIfurnitoritTeMarre.equals(""))
						 query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
							"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
							"WHERE tm.njmateseId = tn.id \r\n" + 
							"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
					else
						query = "SELECT  distinct tm.emriProduktit , tn.pershkrimi,tm.sasia , tm.dtBlerjes, tm.cmimiblerjes, tf.emriFurnitorit\r\n" + 
						"FROM tblmalliblere tm, tblfurnizonproduktin as fp, tblCmimet as tc, tblregjistrimiMallit as tr, tblFurnitoret as tf, tblnjmatese as tn\r\n" + 
						"WHERE tm.njmateseId = tn.id \r\n" + 
						"and tm.furnitoreid = tf.id AND tm.barkodi =  tr.barkodi AND tc.produktetId = tr.id AND fp.furnitoreId = tf.id AND fp.produktId = tr.id AND tf.emriFurnitorit = '"+frmDataShitja.emriIfurnitoritTeMarre+"' AND tm.dtBlerjes >='"+dataParaprake+"' AND tm.dtBlerjes <='"+dataSot+"';\r\n";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					double totPergjithshem = 0;
					while(rs.next())
					{
					
						String Emri = rs.getString("emriProduktit");
			
						String pershkrimi = rs.getString("pershkrimi");
						double sasia = rs.getDouble("sasia");
						String dt1 = rs.getString("dtBlerjes");
						String[] dt = dt1.split("[-]");
						String dtBlerjes = dt[2]+"/"+dt[1]+"/"+dt[0];
						double cmimiblerjesmetvsh = rs.getDouble("cmimiblerjes");
						String cmimi = String.format("%.2f €", cmimiblerjesmetvsh);
						double totali = sasia * cmimiblerjesmetvsh;
						String tot = String.format("%.2f €", totali);
						String emriFurnitorit = rs.getString("emriFurnitorit");
						totPergjithshem = totPergjithshem + totali;
						
						com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(""+count,font));
						c1.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(Emri,font));
						c2.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(pershkrimi,font));
						c3.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(String.format("%.2f", sasia),font));
						c4.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(cmimi,font));
						c5.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(tot,font));
						c7.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(dtBlerjes,font));
						c8.setBorderWidth(0);
						com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(emriFurnitorit,font));
						c9.setBorderWidth(0);
						tabela1.addCell(c1);
						tabela1.addCell(c2);
						tabela1.addCell(c3);
						tabela1.addCell(c4);
						tabela1.addCell(c5);
						tabela1.addCell(c7);
						tabela1.addCell(c9);
						tabela1.addCell(c8);
						count++;
						
						
						
					}
					pst.close();
					doc.add(tabela1);
					
					doc.add(pr2);
					Paragraph pr8 = new Paragraph("                                                                                                                                                                                                                                                        Total:           " + String.format("%.2f €", totPergjithshem),font);
					doc.add(pr8);
					
					
					doc.close();
					Desktop.getDesktop().open(new File(path));
					
				}
					catch(Exception e) {
						System.err.println(e.getMessage());
					}
			
				
					//JOptionPane.showMessageDialog(null, "Executed");
			}
			
			
		});
		
		btnImportoNePdf.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/icons8_Print_48px.png")));
		btnImportoNePdf.setForeground(new Color(0, 0, 51));
		btnImportoNePdf.setFont(new Font("Arial", Font.BOLD, 14));
		btnImportoNePdf.setBackground(Color.WHITE);
		btnImportoNePdf.setBounds(12, 871, 240, 57);
		contentPane.add(btnImportoNePdf);
		
		JLabel lblTotaliMeTvsh = new JLabel("Vlera Totale:");
		lblTotaliMeTvsh.setForeground(new Color(39, 117, 239));
		lblTotaliMeTvsh.setFont(new Font("Arial", Font.BOLD, 15));
		lblTotaliMeTvsh.setBounds(1085, 330, 102, 37);
		contentPane.add(lblTotaliMeTvsh);
		
		JButton btnDatetjeter = new JButton("Zgjedh një datë tjeter\r\n");
		btnDatetjeter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					frmDataShitja shitjet = new frmDataShitja();
					shitjet.setVisible(true);
					shitjet.setLocationRelativeTo(null);
				}
			}
		});
		btnDatetjeter.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/Planner_48px.png")));
		btnDatetjeter.setFont(new Font("Arial", Font.BOLD, 14));
		btnDatetjeter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDataShitja shitjet = new frmDataShitja();
				shitjet.setVisible(true);
				shitjet.setLocationRelativeTo(null);
				
			}
		});
		btnDatetjeter.setForeground(new Color(0, 0, 51));
		btnDatetjeter.setBackground(Color.WHITE);
		btnDatetjeter.setBounds(259, 871, 240, 57);
		contentPane.add(btnDatetjeter);
		
		txtCmimiMeTvsh = new JTextField();
		txtCmimiMeTvsh.setForeground(new Color(0, 0, 51));
		txtCmimiMeTvsh.setEnabled(false);
		txtCmimiMeTvsh.setFont(new Font("Arial", Font.BOLD, 15));
		txtCmimiMeTvsh.setColumns(10);
		txtCmimiMeTvsh.setBounds(1085, 370, 110, 37);
		contentPane.add(txtCmimiMeTvsh);
		
		JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
		lblPerdoruesi.setForeground(new Color(0, 0, 51));
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setBounds(963, 50, 92, 25);
		contentPane.add(lblPerdoruesi);
		
		JLabel lblUser = new JLabel("Perdoruesi:");
		lblUser.setForeground(new Color(0, 0, 51));
		lblUser.setFont(new Font("Arial", Font.BOLD, 14));
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setBounds(1067, 50, 116, 25);
		contentPane.add(lblUser);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/Product-documentation-icon.png")));
		label_1.setBounds(722, 16, 264, 225);
		contentPane.add(label_1);
		
		JRadioButton rdbtnAlb = new JRadioButton("");
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Gjuhesia.gjuha = "alb";
				btnImportoNePdf.setText("Printo");
				btnDatetjeter.setText("Zgjedh një datë tjeter");
				lblTotaliMeTvsh.setText("Vlera Totale:");
				btnShikoTeGjitha.setText("Shiko te gjitha blerjet");
				btnKthehu.setText("Kthehu prapa");
				lblPerdoruesi.setText("Perdoruesi:");
				//"Nr.",  "Emri Produktit" , "Nj Matese" , "Sasia" ,  "Cmimi blerjes", "Totali","Emri I Furnitorit","Data e Blerjes"
				table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
				table.getColumnModel().getColumn(0).setPreferredWidth(8);
				table.getColumnModel().getColumn(1).setHeaderValue("Emri i Produktit");
				table.getColumnModel().getColumn(2).setHeaderValue("Nj Matese");
				table.getColumnModel().getColumn(3).setHeaderValue("Sasia");
				table.getColumnModel().getColumn(4).setHeaderValue("Cmimi blerjes");
				table.getColumnModel().getColumn(5).setHeaderValue("Totali");
				table.getColumnModel().getColumn(6).setHeaderValue("Emri i Furnitorit");
				table.getColumnModel().getColumn(7).setHeaderValue("Data e blerjes");
				scrollPane.setViewportView(table);
			}
		});
		buttonGroup.add(rdbtnAlb);
		rdbtnAlb.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setSelected(true);
		rdbtnAlb.setBounds(1124, 16, 33, 25);
		contentPane.add(rdbtnAlb);
		
	
		
		JRadioButton rdbtnEng = new JRadioButton("");
		rdbtnEng.setOpaque(false);
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Gjuhesia.gjuha = "eng";
				
				btnImportoNePdf.setText("Print");
				btnDatetjeter.setText("Pick another date");
				lblTotaliMeTvsh.setText("Total Amount:");
				btnKthehu.setText("Back");
				lblPerdoruesi.setText("User:");
				btnShikoTeGjitha.setText("View all purchases");
				//"Nr.",  "Emri Produktit" , "Nj Matese" , "Sasia" ,  "Cmimi blerjes", "Totali","Emri I Furnitorit","Data e Blerjes"
				table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
				table.getColumnModel().getColumn(0).setPreferredWidth(8);
				table.getColumnModel().getColumn(1).setHeaderValue("Product");
				table.getColumnModel().getColumn(2).setHeaderValue("Unit");
				table.getColumnModel().getColumn(3).setHeaderValue("Quantity");
				table.getColumnModel().getColumn(4).setHeaderValue("Purchase price");
				table.getColumnModel().getColumn(5).setHeaderValue("Total");
				table.getColumnModel().getColumn(6).setHeaderValue("Supplier");
				table.getColumnModel().getColumn(7).setHeaderValue("Purchase date");
				scrollPane.setViewportView(table);
			}
		});
		buttonGroup.add(rdbtnEng);
		rdbtnEng.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/eng.png")));
		rdbtnEng.setBounds(1154, 16, 33, 25);
		contentPane.add(rdbtnEng);
		
		JLabel label_2 = new JLabel("");
		label_2.setBackground(Color.WHITE);
		label_2.setOpaque(true);
		label_2.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/logo1.png")));
		label_2.setBounds(12, 0, 629, 237);
		contentPane.add(label_2);
		
	
		btnShikoTeGjitha.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/icons8_Bill_48px_2.png")));
		btnShikoTeGjitha.setFont(new Font("Arial", Font.BOLD, 14));
		btnShikoTeGjitha.setBounds(506, 871, 240, 57);
		contentPane.add(btnShikoTeGjitha);
		
		btnKthehu = new JButton("Kthehu prapa");
		btnKthehu.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/dil.png")));
		btnKthehu.addKeyListener(new KeyAdapter() {
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
		btnKthehu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu obj = new frmMenu();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnKthehu.setForeground(new Color(0, 0, 51));
		btnKthehu.setFont(new Font("Arial", Font.BOLD, 14));
		btnKthehu.setBackground(Color.WHITE);
		btnKthehu.setBounds(753, 871, 240, 57);
		contentPane.add(btnKthehu);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(ngjyra);
		lblNewLabel.setBounds(0, 420, 1914, 545);
		contentPane.add(lblNewLabel);
		if(Gjuhesia.gjuha.equals("alb"))
		{
			btnKthehu.setText("Kthehu prapa");
			lblPerdoruesi.setText("Perdoruesi:");
			
		}
			
		else
			{
			btnKthehu.setText("Back");
			lblPerdoruesi.setText("User:");
			}
		lblUser.setText(Useri.getEmri()+" "+Useri.getMbiemri());
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(frmRaportet.class.getResource("/imgs/euro.png")));
		label_3.setBounds(1197, 370, 25, 37);
		contentPane.add(label_3);
	
	}
	}
	
	

