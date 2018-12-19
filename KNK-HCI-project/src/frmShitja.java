
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

public class frmShitja extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtBarkodi;
	private JTable table;
	int numri = 0;
	private int count = 0;
	private PreparedStatement pst = null;
	private ResultSet res = null;
	private Connection conn = null;
	private double ShumaTotaliPaTVSH = 0.00;
	private double ShumaZbritjes = 0.00;
	private double shumatotale = 0.00;
	private double PerqindejaZbritjes = 0.00;
	private JMenuItem mntmPerqindja;
	private String id_e_klientit;
	
	private ArrayList<ArrayList<String>> values = new ArrayList<>();
	
	private Map<String, Double> map = new HashMap<>();
	
	private ArrayList<Integer> rreshtat = new ArrayList<>();

	private double sasiaDinamike = 0;
	private int k;
	private int i;
	private boolean z = true;
	private boolean y = true;
	private boolean x = true;
	private boolean t = true;
	private double Shuma_e_Zbritur= 0;
	
	private DecimalFormat df = new DecimalFormat("0.00");
	
	private JTextField txtID_klientit;
	
	private final ButtonGroup grupiRadioButonave = new ButtonGroup();
	
	private JRadioButton rdbtnKlientIZakonshem = new JRadioButton("Klient i zakonshem");
	private JRadioButton rdbtnKlientIRregullt = new JRadioButton("Klient i rregullt");
	private JTextField txtSasia;
	private JLabel lblMoney = new JLabel();
	private JLabel lblTotali2 = new JLabel();
	private JLabel lblTotaliZbritur2 = new JLabel();
	private JLabel lblTotaliPaTVSH2 = new JLabel();
	private JButton btnShto = new JButton("");
	private JScrollPane scrollPane = new JScrollPane();
	private JButton btnKerkoProduktin = new JButton();
	private JButton btnPerfundoBlerjen = new JButton("Perfundo blerjen");
	private JButton btnAnglisht = new JButton("");
	private JButton btnKtheProduktin = new JButton("Kthe produkte");
	private JButton btnKerkoKlientin = new JButton("Kerko klientin");
	private JLabel lblTotaliPaTVSH1 = new JLabel("Totali pa TVSH");
	private JLabel lblTotali1 = new JLabel("Totali");
	private JLabel lblTotaliZbritur1 = new JLabel("Totali i zbritur");
	private JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
	private JLabel lblShitesi = new JLabel("");
	private JMenuItem mntmExit = new JMenuItem("Mbylle");
	private String validimi1 = "Nuk lejohen vlera negative tek sasia apo vlera jashtw intervalit (1,100) tek zbritja.";
	private String validimi2 = "Ky produkt nuk ekziston.";
	private String validimi3 = "Ju duhet ta jepni sasine e produktit.";
	private String validimi4 = "Nuk ka sasi te mjaftueshme.";
	private String validimi5 = "Ju duhet te shisni dicka.";
	private String validimi6 ="Ky bleres nuk ekziston.";
	private String validimi7 = "BLERJA PERFUNDOI ME SUKSES!";
	private String validimi8 = "Nuk e keni selektuar asnje rresht";
	BufferedImage imgShto = null;
	BufferedImage imgKerkoProduktin = null;
	BufferedImage imgFshijArtikullin = null;
	BufferedImage imgPerfundoBlerjen = null;
	
	JLabel lblBarkodi = new JLabel("Barkodi:");
	
	JLabel lblSasia = new JLabel("Sasia:");
	
	JLabel lblIdEKlientit = new JLabel("ID e klientit:");
	
	private JButton btnFshijArtikullin = new JButton("");
	private String[] columnNames = {"Nr", "Emertimi", "Nj Matese", "Sasia", "Cmimi","Zbritje","Totali pa Zbritje", "Shuma e zbritur", "Totali"};
	private DefaultTableModel model = new DefaultTableModel(null, columnNames)
	{
		@Override
        public boolean isCellEditable(int row, int column)
        {
            if (column == 3 || column == 5)
            	return true;
            else
            	return false;
        }

	};
	private final JButton btnShqip = new JButton("");
	private final JSeparator separator_1 = new JSeparator();
	private final JMenuItem mntmLogOut = new JMenuItem("Dil");
	private final JLabel label = new JLabel("");
	private final JMenuItem mntmKthehuPrapa = new JMenuItem("Kthehu Prapa");
	
	private void NdryshoSasineOseZbritjen()
	{
		try
		{
			z=true;
			shumatotale = 0;
			ShumaTotaliPaTVSH = 0;
			ShumaZbritjes = 0;
			PerqindejaZbritjes = 0;
			y=true;
			for (i=0; i<values.size(); i++)
			{
				//qitu ndryshim prej 12 te 5
				String PQZstring = String.valueOf(table.getValueAt(i,5));
				PQZstring = PQZstring.replace(" ", "");
				PQZstring = PQZstring.replace("%", "");
				
				//columnNames = {"Nr", "Emertimi", "Nj Matese", "Sasia", "Cmimi","Zbritje","Totali pa Zbritje", "Shuma e zbritur", "Totali"};
				//ndryshim prej 14 ne 8
				String TotaliString = String.valueOf(table.getValueAt(i,8));
				TotaliString = TotaliString.replace(" ", "");
				TotaliString = TotaliString.replace("€", "");
				
				//ndryshim prej 5 ne 3
				if ( Double.parseDouble((String) table.getValueAt(i, 3)) < 0 || Double.parseDouble(PQZstring)<0 || Double.parseDouble(PQZstring)>=100)
				{
					z = false;
				}
				else
				{
					PerqindejaZbritjes = Double.parseDouble(PQZstring);
					//e kem ndrru prej 15 ne 9
					sasiaDinamike = map.get(values.get(i).get(9));
					//qitu kem bo ndryshim prej 5 ne 3
					sasiaDinamike = sasiaDinamike - Double.parseDouble(values.get(i).get(3));
					values.get(i).set(3, (String) table.getValueAt(i, 3));
					sasiaDinamike = sasiaDinamike + Double.parseDouble(values.get(i).get(3));
					//e kem ndrru prej 15 ne 9
					map.put(values.get(i).get(9), sasiaDinamike);
					z = true;
					
				}
				//e kem ndrru prej 6 ne 4
				double CmimiPerCopePaTVSH = Double.parseDouble(values.get(i).get(4));
				//e kem ndrru prej 5 ne 3
				double TotaliPaTVSH = CmimiPerCopePaTVSH*Double.parseDouble(values.get(i).get(3)); // Double.parseDouble(values.get(i).get(5)) eshte sasia

				Shuma_e_Zbritur = TotaliPaTVSH*PerqindejaZbritjes/100;
				double TotaliPerfundimtar = TotaliPaTVSH - Shuma_e_Zbritur;
				String cmFormatuar = df.format(CmimiPerCopePaTVSH)+ " €";
				table.setValueAt(String.valueOf(cmFormatuar), i, 4);
				
				//e kem ndrru prej 9 ne 6
				String tptFormatuar = df.format(TotaliPaTVSH)+ " €";
				table.setValueAt(String.valueOf(tptFormatuar), i, 6);
				
				
				//e kem ndrru prej 13 ne 7
				String shzFormatuar = df.format(Shuma_e_Zbritur)+ " €";
				table.setValueAt(String.valueOf(shzFormatuar), i, 7);
				
				//e kem ndrru prej 14 ne 8
				String tpFormatuar = df.format(TotaliPerfundimtar)+ " €";
				table.setValueAt(String.valueOf(tpFormatuar), i, 8);
				
				//columnNames = {"Nr", "Emertimi", "Nj Matese", "Sasia", "Cmimi","Zbritje","Totali pa Zbritje", "Shuma e zbritur", "Totali"};
				//e kem ndrru prej 9 ne 6
				values.get(i).set(6, String.valueOf(TotaliPaTVSH));
				//e kem ndrru prej 12 ne 5
				values.get(i).set(5, String.valueOf(PerqindejaZbritjes));
				//e kem ndrru prej 13 ne 7
				values.get(i).set(7, String.valueOf(Shuma_e_Zbritur));
				//e kem ndrru prej 14 ne 8
				values.get(i).set(8, String.valueOf(TotaliPerfundimtar));
				
				Shuma_e_Zbritur= 0;
				PerqindejaZbritjes = 0;
				
				shumatotale = shumatotale + TotaliPerfundimtar;
				ShumaTotaliPaTVSH = ShumaTotaliPaTVSH + TotaliPaTVSH;
				ShumaZbritjes = ShumaTotaliPaTVSH - shumatotale ;
			}
			lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
			lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
			
			lblTotaliPaTVSH2.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
			lblTotaliZbritur2.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmShitja frame = new frmShitja();
					
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
	public frmShitja() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtBarkodi.requestFocus();
			}
		});
		setResizable(false);
		setBounds(new Rectangle(100, 100, 1880, 1000));
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmShitja.class.getResource("/imgs/logo1icon1.png")));
		setTitle("Besa Commerce");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 1265, 995);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setFont(new Font("Times New Roman", Font.BOLD, 99));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBarkodi = new JTextField();
		txtBarkodi.setFont(new Font("Arial", Font.BOLD, 14));
		txtBarkodi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lblBarkodi.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtBarkodi.getText().length()==0)
					if(Gjuhesia.gjuha.equals("alb"))
						lblBarkodi.setText("Barkodi:");
					else
						lblBarkodi.setText("Barcode:");
			}
		});
		txtBarkodi.setOpaque(false);
		txtBarkodi.setBorder(null);
		
		txtBarkodi.setBounds(13, 328, 261, 20);
		contentPane.add(txtBarkodi);
		txtBarkodi.setColumns(10);
		
		txtSasia = new JTextField();
		txtSasia.setFont(new Font("Arial", Font.BOLD, 14));
		txtSasia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblSasia.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSasia.getText().length()==0)
					if(Gjuhesia.gjuha=="alb")
						lblSasia.setText("Sasia:");
					else
						lblSasia.setText("Amount:");	
			}
		});
		txtSasia.setOpaque(false);
		txtSasia.setBorder(null);
		txtSasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnShto.doClick();
				}
			}
		});
		
		txtSasia.setBounds(13, 383, 86, 20);
		contentPane.add(txtSasia);
		txtSasia.setColumns(10);
		btnShto.setForeground(new Color(255, 255, 255));
		btnShto.setBackground(new Color(153, 204, 255));
		btnShto.setFont(new Font("Arial", Font.BOLD, 14));

		btnShto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnShto.doClick();
				}
			}
			
		});

		btnShto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnShto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					NdryshoSasineOseZbritjen();
					if (z==false)
					{
						JOptionPane.showMessageDialog(null,validimi1);
					}
					else
					{
					Connection conn = connectionClass.connectDb();
					z= true;
					String query0 = "SELECT COUNT(*) FROM tblregjistrimimallit WHERE barkodi='"+txtBarkodi.getText()+"'";
					Statement stmt0 = conn.createStatement();
					ResultSet rs0 = stmt0.executeQuery(query0);
					while(rs0.next())
					{
						if(rs0.getInt("COUNT(*)") == 0)
						{
							y= false;
						}
					}
					//e kemi fshire nga query cmimiblerjes
					String query1 = "select r.emriProduktit, nj.pershkrimi, r.sasia,\r\n" + 
									"cs.cmimiShitjes, r.barkodi\r\n" + 
									"from tblRegjistrimiMallit r, tblNjMatese nj, tblCmimet cs \r\n" + 
									"where r.barkodi = '"+txtBarkodi.getText()+"' and r.njMateseId = nj.id \r\n" + 
									"and r.id = cs.produktetId ;";
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery(query1);
					
					String nr = String.valueOf(numri+1);
					
					if(y==false)
					{
						JOptionPane.showMessageDialog(null,validimi2);
					}
					else
						if(txtSasia.getText().length()==0)
						{
							JOptionPane.showMessageDialog(null,validimi3);
						}
						else
							while (rs1.next()) //
							{
								double sasiaRegjistrimi = rs1.getDouble("sasia");
								double sasia = Double.parseDouble(txtSasia.getText());
								if(sasia<=0)
								{
									JOptionPane.showMessageDialog(null,validimi1);
									txtSasia.requestFocus();
								}
								else if (sasia>sasiaRegjistrimi)
								{
									JOptionPane.showMessageDialog(null,validimi4);
								}
								else
								{	
									if(map.containsKey(txtBarkodi.getText()))
									{
										sasiaDinamike = map.get(txtBarkodi.getText());
										sasiaDinamike = sasiaDinamike + sasia;
										if(sasiaDinamike<=sasiaRegjistrimi)
										{
											map.put(txtBarkodi.getText(), sasiaDinamike);
										}
									}
									else
									{
										sasiaDinamike = sasia;
										map.put(txtBarkodi.getText(), sasiaDinamike);
									}
									
									if(sasiaDinamike>sasiaRegjistrimi)
									{
										JOptionPane.showMessageDialog(null,validimi4);
									}
									else
									{
										map.put(txtBarkodi.getText(), sasiaDinamike);
										double CmimiPerCopePaTVSH = rs1.getDouble("cmimiShitjes");
										double TotaliPaTVSH = CmimiPerCopePaTVSH*sasia;
										Shuma_e_Zbritur = PerqindejaZbritjes*TotaliPaTVSH;
										double TotaliPerfundimtar = TotaliPaTVSH - Shuma_e_Zbritur;
										
										shumatotale = shumatotale + TotaliPerfundimtar;
										ShumaTotaliPaTVSH = ShumaTotaliPaTVSH + TotaliPaTVSH;
										ShumaZbritjes = ShumaTotaliPaTVSH - shumatotale ;
										

										String cmFormatuar = df.format(CmimiPerCopePaTVSH);
										
										lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
										lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
										
										lblTotaliPaTVSH2.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
										lblTotaliZbritur2.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
										
										values.add(new ArrayList<>());
										
										//columnNames = {"Nr", "Emertimi", "Nj Matese", "Sasia", "Cmimi","Zbritje","Totali pa Zbritje", "Shuma e zbritur", "Totali"};
										
										values.get(numri).add(nr); 						//0
										values.get(numri).add(rs1.getString("emriProduktit"));  	//1
										values.get(numri).add(rs1.getString("pershkrimi"));  		//2
										values.get(numri).add(String.valueOf(sasia));			//3
										values.get(numri).add(String.valueOf(cmFormatuar));  	//4
										values.get(numri).add(String.valueOf(PerqindejaZbritjes));	//5
										values.get(numri).add(String.valueOf(TotaliPaTVSH));		//6
										values.get(numri).add(String.valueOf(Shuma_e_Zbritur));		//7
										values.get(numri).add(String.valueOf(TotaliPerfundimtar)); 		//8
										values.get(numri).add(rs1.getString("barkodi")); 		//9
										    
									    DefaultTableModel model = (DefaultTableModel) table.getModel();
									    Object rowData[] = new Object[15];
									    for(i = 0; i < values.size(); i++)
									    {    	
									    	String Vlera6 = df.format(Double.parseDouble(values.get(i).get(4))) + " €";
									    	String Vlera7 = df.format(Double.parseDouble(values.get(i).get(5))) + " %";
									    	
									    	String Vlera8 = df.format(Double.parseDouble(values.get(i).get(6))) + " €";
									    	String Vlera9 = df.format(Double.parseDouble(values.get(i).get(7))) + " €";
									    	String Vlera10 = df.format(Double.parseDouble(values.get(i).get(8))) + " €";
									    	
									    	rowData[0] = values.get(i).get(0);
									        rowData[1] = values.get(i).get(1);
									        rowData[2] = values.get(i).get(2);
									        rowData[3] = values.get(i).get(3);
									        rowData[4] = Vlera6;
									        rowData[5] = Vlera7;
									        rowData[6] = Vlera8;
									        rowData[7] = Vlera9;
									        rowData[8] = Vlera10;
									    }
									    model.addRow(rowData);
									    numri++;
									}	
		
									
									scrollPane.setViewportView(table);
								}
			
							}
					}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});

		btnShto.setBounds(13, 416, 158, 32);
		contentPane.add(btnShto);
		scrollPane.setBounds(new Rectangle(2, 2, 2, 2));
		scrollPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				NdryshoSasineOseZbritjen();
			}
		});
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane.setBackground(new Color(22, 127, 146));
		scrollPane.setBounds(10, 461, 1237, 351);
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
		table.setFont(new Font("Arial", Font.BOLD, 14));

		table.getTableHeader().setBackground(new Color(22,127,146));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				NdryshoSasineOseZbritjen();
			}
		});
		table.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB)
				{
					NdryshoSasineOseZbritjen();
				}
			}
		});

		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table);
		lblTotali2.setForeground(new Color(255, 255, 255));
		lblTotali2.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotali2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotali2.setText("0.00 €");
		lblTotaliPaTVSH2.setForeground(new Color(255, 255, 255));
		lblTotaliPaTVSH2.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotaliPaTVSH2.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTotaliPaTVSH2.setText("0.00 €");
		lblTotaliZbritur2.setForeground(new Color(255, 255, 255));
		lblTotaliZbritur2.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotaliZbritur2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaliZbritur2.setText("0.00 €");
		btnPerfundoBlerjen.setForeground(new Color(255, 255, 255));
		btnPerfundoBlerjen.setFont(new Font("Arial", Font.BOLD, 14));
		btnPerfundoBlerjen.setBackground(new Color(153, 204, 255));
		
		btnPerfundoBlerjen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnPerfundoBlerjen.doClick();
				}
				
			}
		});
		
		btnPerfundoBlerjen.setLayout( new BorderLayout() );
		btnPerfundoBlerjen.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPerfundoBlerjen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPerfundoBlerjen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					x=true;
					if(values.size()==0)
					{
						JOptionPane.showMessageDialog(null,validimi5);
					}
					else
					{
						NdryshoSasineOseZbritjen();
						if(z==false)
						{
							JOptionPane.showMessageDialog(null,validimi1);
						}
						else
						{	
							z= true;
							Connection conn = connectionClass.connectDb();
//							for (i=0; i<values.size();i++)
//							{
//								//e kem ndrru 15 me 9
//								String querySasia = "select sasia from tblRegjistrimimallit where barkodi = '"+String.valueOf(values.get(i).get(9)) +"'";
//								Statement stmtSasia = conn.createStatement();
//								ResultSet rsSasia = stmtSasia.executeQuery(querySasia);
//								while(rsSasia.next())
//								{
//									//e kem zevendsu 15 me 9
//									if(map.get(values.get(i).get(9))>rsSasia.getDouble("sasia"))
//									{
//										t = false;
//									}
//								}
//							}
							if(t == false)
							{
								JOptionPane.showMessageDialog(null,validimi4);
								txtSasia.requestFocus();
							}
							else
							{
								SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
								SimpleDateFormat formatter3 = new SimpleDateFormat("dd/MM/yyyy");
								Date data1 = new Date();
								String data2 = formatter1.format(data1);
								String data3 = formatter3.format(data1);
								
	
								SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
								SimpleDateFormat formatter4 = new SimpleDateFormat("HHmm");
								Date koha1 = new Date();
								String koha2 = formatter2.format(koha1);
								String koha3 = formatter4.format(koha1);
								
								
								if(rdbtnKlientIRregullt.isSelected())
								{
									id_e_klientit = txtID_klientit.getText();
									btnKerkoKlientin.setVisible(true);
									
									String query0 = "SELECT COUNT(*) FROM tblBleresit WHERE nrPersonal='"+id_e_klientit+"'";
									Statement stmt0 = conn.createStatement();
									ResultSet rs0 = stmt0.executeQuery(query0);
									while(rs0.next())
									{
										if(rs0.getInt("COUNT(*)") == 0)
										{
											x= false;
										}
									}
									if (x==false)
									{
										JOptionPane.showMessageDialog(null,validimi6);
										frmBleresi objBleresi = new frmBleresi();
										objBleresi.setVisible(true);
									}
									else
									{
										
										for (i=0; i<numri; i++)
										{	
											//ndryshim tek numri personal, stafiID
											// e kem ndrru te 15 eshte 9 
											String query1 = "INSERT INTO malliShitur (barkodiProduktit, sasia, cmimiShitjesMeTVSH,zbritja, kohaShitjes, dtShitjes, staffId, bleresId) " 
															+ "VALUES ('" + values.get(i).get(9) + "','" + values.get(i).get(3) + "','" 
															+values.get(i).get(4) + "','"+values.get(i).get(5) +"','"+koha2 +"','"  +data2 + "','"+  Useri.getNrPersonal()   + "','" + id_e_klientit + "')";
											Statement stmt1 = conn.createStatement();
											stmt1.executeUpdate(query1);
												
											String query2 = "UPDATE tblRegjistrimiMallit SET sasia = sasia-" +values.get(i).get(3)+ " WHERE barkodi = '"+values.get(i).get(9) +"'";
											Statement stmt2 = conn.createStatement();
											stmt2.executeUpdate(query2);
										}
											
										JOptionPane.showMessageDialog(null, validimi7);
	
										rdbtnKlientIZakonshem.setSelected(true);
										txtID_klientit.setText("");
										txtID_klientit.setEnabled(false);
									}
								}
								else
								{
									id_e_klientit = "1";
									for (i=0; i<numri; i++)
									{	
										String query1 = "INSERT INTO malliShitur (barkodiProduktit, sasia, cmimiShitjesMeTVSH,zbritja, kohaShitjes, dtShitjes, staffId,bleresId) " 
														+ "VALUES ('" + values.get(i).get(9) + "','" + values.get(i).get(3) + "','" 
														+values.get(i).get(4) + "','"+values.get(i).get(5) +"','"+koha2 +"','"  +data2 + "','"+ Useri.getNrPersonal() +"','"+id_e_klientit+"')";
										Statement stmt1 = conn.createStatement();
										stmt1.executeUpdate(query1);
											
										String query2 = "UPDATE tblRegjistrimiMallit SET sasia = sasia-" +values.get(i).get(3)+ " WHERE barkodi = '"+values.get(i).get(9) +"'";
										Statement stmt2 = conn.createStatement();
										stmt2.executeUpdate(query2);
									}
									
									JOptionPane.showMessageDialog(null, validimi7);
								}
								
								frmPerKthim.borxhi = Double.parseDouble(lblTotali2.getText().replaceAll("[€\\s]", ""));
								frmPerKthim obj = new frmPerKthim();
								obj.setVisible(true);
								obj.setLocationRelativeTo(null);
								
								Document doc = new Document();
								//D:\Shitjet
								PdfWriter.getInstance(doc, new FileOutputStream("D:\\Shitjet\\Fatura"+data2+koha3+".pdf"));
								
								conn = connectionClass.connectDb();
								String sqlBl = "select b.emri, b.mbiemri, q.qyteti, s.shteti  from tblBleresit b, tblshtetet s, tblqytetet q where b.adreseId = q.id and q.sid = s.id and nrPersonal = '"+id_e_klientit+"'";
								pst = conn.prepareStatement(sqlBl);
								res = pst.executeQuery();
								String em = "";
								String qytet = "";
								while(res.next())
								{
									em = res.getString("emri")+" "+res.getString("mbiemri");
									qytet = res.getString("qyteti")+", "+res.getString("shteti");
								}
								pst.close();
								
								doc.open();
								
								com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
								
								com.itextpdf.text.Font font4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, BaseColor.BLACK);
								com.itextpdf.text.Font font0 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
								com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
								com.itextpdf.text.Image imde = com.itextpdf.text.Image.getInstance(frmShitja.class.getResource("/imgs/logo1.png"));
								Paragraph  pr24  =  new  Paragraph("                                                                                                                                                                                                             ",font);
								Paragraph pr28 = new Paragraph();
								imde.scaleToFit(250f, 100f);
								pr28.add(imde);
								float[] columnWidths1 = {500f, 320f};
								com.itextpdf.text.pdf.PdfPTable tabela3 = new PdfPTable(columnWidths1);
								
								com.itextpdf.text.pdf.PdfPCell c10 = new com.itextpdf.text.pdf.PdfPCell(imde);
								c10.setBorderWidth(0);
								com.itextpdf.text.pdf.PdfPCell c11 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Nr. llog. TEB:  2024000017855732",font4));
								c11.setBorderWidth(0);
								c10.setHorizontalAlignment(Element.ALIGN_LEFT);
								c11.setHorizontalAlignment(Element.ALIGN_RIGHT);
								c10.setRowspan(5);
								com.itextpdf.text.pdf.PdfPCell c13 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("   ",font4));
								c13.setBorderWidth(0);
								c13.setHorizontalAlignment(Element.ALIGN_RIGHT);
								com.itextpdf.text.pdf.PdfPCell c14 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(" Koha:       " + koha2,font4));
								c14.setBorderWidth(0);
								c14.setHorizontalAlignment(Element.ALIGN_RIGHT);
								com.itextpdf.text.pdf.PdfPCell c15 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(" Data:   " + data3,font4));
								c15.setBorderWidth(0);
								c15.setHorizontalAlignment(Element.ALIGN_RIGHT);
								
								tabela3.addCell(c10);
								tabela3.addCell(c14);
								tabela3.addCell(c15);
								tabela3.addCell(c13);
								tabela3.addCell(c11);
								doc.add(tabela3);
								doc.add(pr24);
//								Paragraph pr9 = new Paragraph("                                                                                                                                                                                                                                               Koha: " + koha2,font4);
//								Paragraph pr10 = new Paragraph("                                                                                                                                                                                                                                               Data: " + data3,font4);
//								doc.add(pr9);
//								doc.add(pr10);
								doc.add(pr24);
								Paragraph  pr20  =  new  Paragraph("Tel:       044/489-014                                                                                                                                                                                                             Klienti: "+em,font4);
								Paragraph  pr21  =  new  Paragraph("Adresa: St. Jasharajve                                                                                                                                                                                                           Adresa:  "+qytet,font4);
								Paragraph  pr22  =  new  Paragraph("Qyteti: Drenas, Kosovë",font4);
								Paragraph  pr23  =  new  Paragraph("                                                                                                                                                                                                             ",font4);
								doc.add(pr20);
								doc.add(pr21);
								doc.add(pr22);
								doc.add(pr23);
								doc.add(pr24);
								Paragraph  pr0  =  new  Paragraph("                                                              FATURA",font0);
								Paragraph  pr1  =  new  Paragraph();
								if(Gjuhesia.gjuha=="alb")
									pr1  =  new  Paragraph("Nr    Emertimi                       Nj Matese             Sasia               Cmimi               Zbritje                   Totali pa Zbritje                      Shuma e zbritur                     Totali         ",font);
								else
									pr1  =  new  Paragraph("No    Description                    Meas. unit            Amount              Price               Disc.                     Total without Disc.                    Disc. amount                        Total          ",font);
								Paragraph pr2 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",font);
								//specify column widths
								float[] columnWidths = {30f, 190f, 80f, 100f,100f,80f,120f,120,120f};
								com.itextpdf.text.pdf.PdfPTable tabela = new PdfPTable(columnWidths);
								tabela.setTotalWidth(PageSize.A4.getWidth()-70);
								tabela.setLockedWidth(true);
								Paragraph pg = new Paragraph();
								
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
//								pg.add(tabela);
								com.itextpdf.text.pdf.PdfPTable tabela1 = new PdfPTable(columnWidths);
								tabela1.setTotalWidth(PageSize.A4.getWidth()-70);
								tabela1.setLockedWidth(true);
								doc.add(pr0);
								doc.add(pr2);
								doc.add(tabela);
								doc.add(pr2);
								
								Paragraph pg1 = new Paragraph();
								for(i=0;i<values.size();i++)
								{
									//Paragraph pr3 = new Paragraph(values.get(i).get(0)+"    "+values.get(i).get(1)+"                    "+values.get(i).get(2)+"                  "+df.format(Double.parseDouble(values.get(i).get(3)))+"                   "+df.format(Double.parseDouble(values.get(i).get(4)))+"€                  "+df.format(Double.parseDouble(values.get(i).get(5)))+"%                  "+df.format(Double.parseDouble(values.get(i).get(6)))+"€                   "+df.format(Double.parseDouble(values.get(i).get(7)))+"€                      "+df.format(Double.parseDouble(values.get(i).get(8)))+"€",font);
									//doc.add(pr3);
									
									com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(values.get(i).get(0),font));
									c1.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(values.get(i).get(1),font));
									c2.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(values.get(i).get(2),font));
									c3.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(3))),font));
									c4.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(4)))+" €",font));
									c5.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(5)))+" %",font));
									c6.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(6)))+" €",font));
									c7.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(7)))+" €",font));
									c8.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(8)))+" €",font));
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
								}
//								pg1.add(tabela1);
								doc.add(tabela1);
								doc.add(pr2);
								Paragraph pr4 = new Paragraph("                                                                                                                                                                                                        "+lblTotaliPaTVSH1.getText() + ":   " + lblTotaliPaTVSH2.getText(),font);
								Paragraph pr7 = new Paragraph("                                                                                                                                                                                                        "+lblTotaliZbritur1.getText() + ":      " + lblTotaliZbritur2.getText(),font);
								Paragraph pr8 = new Paragraph("                                                                                                                                                                                                        "+lblTotali1.getText() + ":                    " + lblTotali2.getText(),font);
								doc.add(pr4);
								doc.add(pr7);
								doc.add(pr8);
								//doc.add(pr2);
//								doc.add(pr2);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);

								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								Paragraph pr25 = new Paragraph("     Faturoi:                                                                                                                                                 Pranoi:",font1);
								doc.add(pr25);
								doc.add(pr24);
								doc.add(pr24);
								Paragraph pr26 = new Paragraph("     ____________________                                                                                                                      _______________________",font1);
								doc.add(pr26);
								doc.close();
								
								//D:\Shitjet
								Desktop.getDesktop().open(new File("D:\\Shitjet\\Fatura"+data2+koha3+".pdf"));
								count++;
								values.clear();
								
								txtBarkodi.setText("");
								txtSasia.setText("");
								lblMoney.requestFocus();
								
								if(Gjuhesia.gjuha.equals("alb"))
								{
									lblBarkodi.setText("Barkodi:");
									lblSasia.setText("Sasia:");
								}
								else
								{
									lblBarkodi.setText("Barcode:");
									lblSasia.setText("Amount:");
								}
								numri = 0;
								shumatotale = 0;
								ShumaTotaliPaTVSH = 0;
								ShumaZbritjes = 0;
								PerqindejaZbritjes = 0;
								map.clear();
								DefaultTableModel model = (DefaultTableModel)table.getModel();
								model.getDataVector().removeAllElements();
								model.fireTableDataChanged();
								lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
								lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
								
								lblTotaliPaTVSH2.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
								lblTotaliZbritur2.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
							}
						}
					}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnPerfundoBlerjen.setBounds(220, 910, 198, 32);
		contentPane.add(btnPerfundoBlerjen);
		rdbtnKlientIZakonshem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				rdbtnKlientIZakonshem.doClick();
			}
		});
		rdbtnKlientIZakonshem.setForeground(new Color(0, 0, 51));
		rdbtnKlientIZakonshem.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnKlientIZakonshem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB)
				rdbtnKlientIZakonshem.setSelected(true);
				txtID_klientit.setEnabled(false);
				btnKerkoKlientin.setVisible(false);
			}
		});
		
		rdbtnKlientIZakonshem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnKlientIZakonshem.setBackground(new Color(255, 255, 255));
		rdbtnKlientIZakonshem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtID_klientit.setEnabled(false);
				btnKerkoKlientin.setVisible(false);
			}
		});
		
		grupiRadioButonave.add(rdbtnKlientIZakonshem);
		rdbtnKlientIZakonshem.setSelected(true);
		rdbtnKlientIZakonshem.setBounds(10, 821, 186, 23);
		contentPane.add(rdbtnKlientIZakonshem);
		rdbtnKlientIRregullt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				rdbtnKlientIRregullt.doClick();
			}
		});
		rdbtnKlientIRregullt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnKlientIRregullt.setSelected(true);
				txtID_klientit.setEnabled(true);
				txtID_klientit.requestFocus();
				btnKerkoKlientin.setVisible(true);
			}
		});
		rdbtnKlientIRregullt.setForeground(new Color(0, 0, 51));
		rdbtnKlientIRregullt.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtnKlientIRregullt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB)
				{
					rdbtnKlientIRregullt.setSelected(true);
					txtID_klientit.setEnabled(true);
					txtID_klientit.requestFocus();
					btnKerkoKlientin.setVisible(true);
				}
			}
		});
		rdbtnKlientIRregullt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnKlientIRregullt.setBackground(new Color(255, 255, 255));
		
		grupiRadioButonave.add(rdbtnKlientIRregullt);
		rdbtnKlientIRregullt.setBounds(10, 851, 186, 23);
		contentPane.add(rdbtnKlientIRregullt);
		
		txtID_klientit = new JTextField();
		txtID_klientit.setBorder(null);
		txtID_klientit.setOpaque(false);
		txtID_klientit.setFont(new Font("Arial", Font.BOLD, 14));
		txtID_klientit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnPerfundoBlerjen.doClick();
				}
			}
		});
		txtID_klientit.setEnabled(false);
		txtID_klientit.setBounds(151, 881, 172, 22);
		contentPane.add(txtID_klientit);
		txtID_klientit.setColumns(10);
		btnFshijArtikullin.setBackground(new Color(153, 204, 255));
		btnFshijArtikullin.setForeground(new Color(255, 255, 255));
		btnFshijArtikullin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnFshijArtikullin.doClick();
			}
		});
		btnFshijArtikullin.setFont(new Font("Arial", Font.BOLD, 14));
		btnFshijArtikullin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnFshijArtikullin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				shumatotale = 0;
				ShumaTotaliPaTVSH = 0;
				ShumaZbritjes = 0;
				//prej qitu
				int vleraselektuarInteger;
				String vleraselektuarString;
				
				int gjatesiaEselektimit = table.getSelectedRows().length;
				if(gjatesiaEselektimit>0)
				{
					for(i=0; i<gjatesiaEselektimit; i++ )
					{
						vleraselektuarString =  String.valueOf(table.getValueAt(table.getSelectedRow(), 0));    //metoda table.getValueAt nuk e lejonte qe te shendrrohej direkt ne Integer, andaj se pari ne String e pastaj ne Integer
						vleraselektuarInteger = Integer.parseInt(vleraselektuarString);
					    rreshtat.add(vleraselektuarInteger);
					    model.removeRow(table.getSelectedRow());
					}

					for (i = rreshtat.size()-1; i>=0; i--) //duhet ne reverse sepse indexat bien poshte cdo here
					{
						k = rreshtat.get(i);
						values.remove(k-1);
					}
					
					for (i = 0 ; i< values.size(); i++)
					{
						model.setValueAt(i+1, i, 0);
						values.get(i).set(0, String.valueOf(i+1));
					}
					
					NdryshoSasineOseZbritjen();
					
					numri = values.size();
					sasiaDinamike = 0;
					map.clear();
					
					//deri qitu
					for (i=0; i<numri ; i++)
					{
						//kem nderru prej 15 ne 9 dhe prej 5 ne 3
						if (map.containsKey(values.get(i).get(9)))
						{
							sasiaDinamike = map.get(values.get(i).get(9));
							sasiaDinamike = sasiaDinamike + Double.parseDouble(values.get(i).get(3));
							map.put(values.get(i).get(9), sasiaDinamike);
						}
						else
						{
							sasiaDinamike = Double.parseDouble(values.get(i).get(3));
							map.put(values.get(i).get(9), sasiaDinamike);   
						}
	
					}
					rreshtat.clear();
				}
				else
				{
					JOptionPane.showMessageDialog(null,validimi8);
				}
			}

		});
		
		btnFshijArtikullin.setBounds(183, 416, 135, 32);
		contentPane.add(btnFshijArtikullin);
		btnAnglisht.setOpaque(false);
		btnAnglisht.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnAnglisht.doClick();
			}
		});
		btnAnglisht.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAnglisht.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnglisht.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/eng.png")));

		btnAnglisht.setBounds(1222, 34, 25, 25);
		contentPane.add(btnAnglisht);
		lblMoney.setBorder(new LineBorder(new Color(0, 0, 51), 5, true));
		lblMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lblMoney.setText("0.00 €");
		
		lblMoney.setBackground(new Color(255, 255, 255));
		lblMoney.setOpaque(true);
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 99));
		lblMoney.setForeground(new Color(0, 0, 51));
		lblMoney.setBounds(0, 164, 1259, 151);
		contentPane.add(lblMoney);
		lblIdEKlientit.setFont(new Font("Arial", Font.BOLD, 14));
		lblIdEKlientit.setForeground(new Color(0, 0, 51));
		
		lblIdEKlientit.setBounds(20, 881, 119, 22);
		contentPane.add(lblIdEKlientit);
		lblSasia.setForeground(new Color(0, 0, 51));
		lblSasia.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblSasia.setBounds(13, 382, 65, 20);
		contentPane.add(lblSasia);
		lblBarkodi.setForeground(new Color(0, 0, 51));
		lblBarkodi.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblBarkodi.setBounds(13, 330, 85, 14);
		contentPane.add(lblBarkodi);
		lblTotaliPaTVSH1.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotaliPaTVSH1.setForeground(new Color(0, 0, 51));
		lblTotaliPaTVSH1.setHorizontalAlignment(SwingConstants.RIGHT);

		lblTotaliPaTVSH1.setOpaque(true);
		lblTotaliPaTVSH1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTotaliPaTVSH1.setBackground(new Color(255, 255, 255));
		lblTotaliPaTVSH1.setBounds(973, 825, 128, 24);
		contentPane.add(lblTotaliPaTVSH1);
		lblTotali1.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotali1.setForeground(new Color(0, 0, 51));
		lblTotali1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblTotali1.setBackground(new Color(255, 255, 255));
		lblTotali1.setOpaque(true);
		lblTotali1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTotali1.setBounds(973, 896, 128, 24);
		contentPane.add(lblTotali1);
		lblTotaliZbritur1.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotaliZbritur1.setForeground(new Color(0, 0, 51));
		lblTotaliZbritur1.setHorizontalAlignment(SwingConstants.RIGHT);

		lblTotaliZbritur1.setBackground(new Color(255, 255, 255));
		lblTotaliZbritur1.setOpaque(true);
		lblTotaliZbritur1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTotaliZbritur1.setBounds(973, 862, 128, 24);
		contentPane.add(lblTotaliZbritur1);
		
		lblTotaliPaTVSH2.setOpaque(true);
		lblTotaliPaTVSH2.setBackground(new Color(0, 0, 51));
		lblTotaliPaTVSH2.setBounds(1113, 825, 123, 24);
		contentPane.add(lblTotaliPaTVSH2);
		
		lblTotaliZbritur2.setOpaque(true);
		lblTotaliZbritur2.setBackground(new Color(0, 0, 51));
		lblTotaliZbritur2.setBounds(1113, 862, 123, 24);
		contentPane.add(lblTotaliZbritur2);
		
		lblTotali2.setOpaque(true);
		lblTotali2.setBackground(new Color(0, 0, 51));
		lblTotali2.setBounds(1113, 897, 123, 24);
		contentPane.add(lblTotali2);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
		alignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
		alignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		table.getColumnModel().getColumn(1).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(2).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(3).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(4).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(5).setCellRenderer(alignCenter);
		table.getColumnModel().getColumn(6).setCellRenderer(alignRight);
		table.getColumnModel().getColumn(7).setCellRenderer(alignRight);
		table.getColumnModel().getColumn(8).setCellRenderer(alignRight);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(170);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		btnKerkoProduktin.setFont(new Font("Arial", Font.BOLD, 14));
		btnKerkoProduktin.setForeground(new Color(255, 255, 255));
		btnKerkoProduktin.setBackground(new Color(153, 204, 255));
		btnKerkoProduktin.setText("Kerko produktin");
		btnKerkoProduktin.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/KerkoProduktin1.png")));
		btnKerkoProduktin.setSelectedIcon(new ImageIcon(frmShitja.class.getResource("/imgs/KerkoProduktin1.png")));
		
		btnKerkoProduktin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnKerkoProduktin.doClick();
			}
		});
		btnKerkoProduktin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


		btnAnglisht.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha = "eng";
				NdryshoGjuhen();
			}
		});
		
		btnKerkoProduktin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					frmKerkoProduktin  ObjKerkoProduktin = new frmKerkoProduktin();
					ObjKerkoProduktin.setVisible(true);
					ObjKerkoProduktin.setLocationRelativeTo(null);
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		btnKerkoProduktin.setBounds(286, 323, 218, 32);
		contentPane.add(btnKerkoProduktin);
		btnShqip.setOpaque(false);
		btnShqip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
					btnShqip.doClick();
			}
		});
		btnShqip.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShqip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/alb.png")));
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha = "alb";
				NdryshoGjuhen();
			}
		});
		btnShqip.setBounds(1194, 34, 25, 25);
		
		contentPane.add(btnShqip);
		btnKtheProduktin.setSelectedIcon(new ImageIcon(frmShitja.class.getResource("/imgs/KtheProduktin.png")));
		btnKtheProduktin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnKtheProduktin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmKtheProduktin objKtheProduktin = new frmKtheProduktin();
				objKtheProduktin.setVisible(true);
				objKtheProduktin.setLocationRelativeTo(null);
			}
		});
		btnKtheProduktin.setForeground(new Color(255, 255, 255));
		btnKtheProduktin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnKtheProduktin.doClick();
			}
		});
		btnKtheProduktin.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnKtheProduktin.setBackground(new Color(153, 204, 255));
		btnKtheProduktin.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/KtheProduktin.png")));
		btnKtheProduktin.setBounds(1066, 416, 181, 32);
		contentPane.add(btnKtheProduktin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 345, 261, 2);
		contentPane.add(separator);
		separator_1.setBounds(10, 401, 86, 2);
		
		contentPane.add(separator_1);
		btnKerkoKlientin.setFont(new Font("Arial", Font.BOLD, 14));
		btnKerkoKlientin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmKlientet objKerkoKlientet = new frmKlientet();
				objKerkoKlientet.setVisible(true);
				objKerkoKlientet.setLocationRelativeTo(null);
			}
		});
		btnKerkoKlientin.setForeground(new Color(255, 255, 255));
		btnKerkoKlientin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnKerkoKlientin.doClick();
			}
		});
		btnKerkoKlientin.setVisible(false);
		btnKerkoKlientin.setBackground(new Color(153, 204, 255));
		
		btnKerkoKlientin.setBounds(11, 910, 197, 32);
		contentPane.add(btnKerkoKlientin);
		lblPerdoruesi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try
				{
					frmLlogZbritjen objL = new frmLlogZbritjen();
					objL.setVisible(true);
					objL.setLocationRelativeTo(null);
				}
				catch(Exception ex)
				{
					
				}
			}
		});
		lblPerdoruesi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerdoruesi.setForeground(new Color(255, 255, 255));
		
		lblPerdoruesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblPerdoruesi.setBounds(1025, 60, 107, 24);
		contentPane.add(lblPerdoruesi);
		lblShitesi.setForeground(new Color(255, 255, 255));
		
		lblShitesi.setFont(new Font("Arial", Font.BOLD, 14));
		lblShitesi.setBounds(1143, 60, 116, 24);
		lblShitesi.setText(Useri.getEmri() + " "+Useri.getMbiemri());
		contentPane.add(lblShitesi);
		//label.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/C_Users_hp_AppData_Local_Packages_Microsoft.SkypeApp_kzf8qxf38zg5c_LocalState_4a328a62-3b36-4195-9d6f-46d4ff4f0a25.png")));
		label.setBounds(10, -284, 376, 115);
		
		contentPane.add(label);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1922, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("File");
		menu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					menu.doClick();
			}
		});
		menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(menu);
		mntmExit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					mntmExit.doClick();
			}
		});
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		mntmPerqindja = new JMenuItem("Perqindja");
		mntmPerqindja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					frmLlogZbritjen obj = new frmLlogZbritjen();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
				catch(Exception ex)
				{
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPerfundoBlerjen.doClick();
				
			}
		});
		mntmPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		menu.add(mntmPrint);
		
		JMenuItem mntmPrint_1 = new JMenuItem("Print2");
		mntmPrint_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					x=true;
					if(values.size()==0)
					{
						JOptionPane.showMessageDialog(null,validimi5);
					}
					else
					{
						NdryshoSasineOseZbritjen();
						if(z==false)
						{
							JOptionPane.showMessageDialog(null,validimi1);
						}
						else
						{	
							z= true;
							Connection conn = connectionClass.connectDb();
//							for (i=0; i<values.size();i++)
//							{
//								//e kem ndrru 15 me 9
//								String querySasia = "select sasia from tblRegjistrimimallit where barkodi = '"+String.valueOf(values.get(i).get(9)) +"'";
//								Statement stmtSasia = conn.createStatement();
//								ResultSet rsSasia = stmtSasia.executeQuery(querySasia);
//								while(rsSasia.next())
//								{
//									//e kem zevendsu 15 me 9
//									if(map.get(values.get(i).get(9))>rsSasia.getDouble("sasia"))
//									{
//										t = false;
//									}
//								}
//							}
							if(t == false)
							{
								JOptionPane.showMessageDialog(null,validimi4);
								txtSasia.requestFocus();
							}
							else
							{
								SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
								SimpleDateFormat formatter3 = new SimpleDateFormat("dd/MM/yyyy");
								Date data1 = new Date();
								String data2 = formatter1.format(data1);
								String data3 = formatter3.format(data1);
								
	
								SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
								SimpleDateFormat formatter4 = new SimpleDateFormat("HHmm");
								Date koha1 = new Date();
								String koha2 = formatter2.format(koha1);
								String koha3 = formatter4.format(koha1);
								
								
								
								
								id_e_klientit = "1";
								for (i=0; i<numri; i++)
								{	
									String query1 = "INSERT INTO malliShitur (barkodiProduktit, sasia, cmimiShitjesMeTVSH,zbritja, kohaShitjes, dtShitjes, staffId,bleresId) " 
													+ "VALUES ('" + values.get(i).get(9) + "','" + values.get(i).get(3) + "','" 
													+values.get(i).get(4) + "','"+values.get(i).get(5) +"','"+koha2 +"','"  +data2 + "','"+ Useri.getNrPersonal() +"','"+id_e_klientit+"')";
									Statement stmt1 = conn.createStatement();
									stmt1.executeUpdate(query1);
											
									String query2 = "UPDATE tblRegjistrimiMallit SET sasia = sasia-" +values.get(i).get(3)+ " WHERE barkodi = '"+values.get(i).get(9) +"'";
									Statement stmt2 = conn.createStatement();
									stmt2.executeUpdate(query2);
								}
									
								JOptionPane.showMessageDialog(null, validimi7);
								
								Document doc = new Document();
								//D:\Shitjet
								PdfWriter.getInstance(doc, new FileOutputStream("D:\\Shitjet\\Fatura"+data2+koha3+".pdf"));
								
								conn = connectionClass.connectDb();
								String sqlBl = "select b.emri, b.mbiemri, q.qyteti, s.shteti  from tblBleresit b, tblshtetet s, tblqytetet q where b.adreseId = q.id and q.sid = s.id and nrPersonal = '"+id_e_klientit+"'";
								pst = conn.prepareStatement(sqlBl);
								res = pst.executeQuery();
								String em = "";
								String qytet = "";
								while(res.next())
								{
									em = res.getString("emri")+" "+res.getString("mbiemri");
									qytet = res.getString("qyteti")+", "+res.getString("shteti");
								}
								pst.close();
								
								doc.open();
								
								com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
								
								com.itextpdf.text.Font font4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, BaseColor.BLACK);
								com.itextpdf.text.Font font0 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
								com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
								com.itextpdf.text.Image imde = com.itextpdf.text.Image.getInstance(frmShitja.class.getResource("/imgs/logo1.png"));
								Paragraph  pr24  =  new  Paragraph("                                                                                                                                                                                                             ",font);
								Paragraph pr28 = new Paragraph();
								imde.scaleToFit(250f, 100f);
								pr28.add(imde);
								float[] columnWidths1 = {500f, 320f};
								com.itextpdf.text.pdf.PdfPTable tabela3 = new PdfPTable(columnWidths1);
								
								com.itextpdf.text.pdf.PdfPCell c10 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("  ",font4));
								c10.setBorderWidth(0);
								
								c10.setHorizontalAlignment(Element.ALIGN_LEFT);
								
								c10.setRowspan(5);
								com.itextpdf.text.pdf.PdfPCell c13 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("   ",font4));
								c13.setBorderWidth(0);
								c13.setHorizontalAlignment(Element.ALIGN_RIGHT);
								com.itextpdf.text.pdf.PdfPCell c14 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(" Koha:       " + koha2,font4));
								c14.setBorderWidth(0);
								c14.setHorizontalAlignment(Element.ALIGN_RIGHT);
								com.itextpdf.text.pdf.PdfPCell c15 = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Data:   " + data3+" ",font4));
								c15.setBorderWidth(0);
								c15.setHorizontalAlignment(Element.ALIGN_RIGHT);
								
								tabela3.addCell(c10);
								tabela3.addCell(c14);
								tabela3.addCell(c15);
								tabela3.addCell(c13);
								doc.add(tabela3);
								Paragraph  pr23  =  new  Paragraph("                                                                                                                                                                                                             ",font4);
								doc.add(pr23);
								
								
								Paragraph  pr0  =  new  Paragraph("                                                              FATURA",font0);
								Paragraph  pr1  =  new  Paragraph();
								if(Gjuhesia.gjuha=="alb")
									pr1  =  new  Paragraph("Nr    Emertimi                       Nj Matese             Sasia               Cmimi               Zbritje                   Totali pa Zbritje                      Shuma e zbritur                     Totali         ",font);
								else
									pr1  =  new  Paragraph("No    Description                    Meas. unit            Amount              Price               Disc.                     Total without Disc.                    Disc. amount                        Total          ",font);
								Paragraph pr2 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",font);
								//specify column widths
								float[] columnWidths = {30f, 190f, 80f, 100f,100f,80f,120f,120,120f};
								com.itextpdf.text.pdf.PdfPTable tabela = new PdfPTable(columnWidths);
								tabela.setTotalWidth(PageSize.A4.getWidth()-70);
								tabela.setLockedWidth(true);
								Paragraph pg = new Paragraph();
								
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
//								pg.add(tabela);
								com.itextpdf.text.pdf.PdfPTable tabela1 = new PdfPTable(columnWidths);
								tabela1.setTotalWidth(PageSize.A4.getWidth()-70);
								tabela1.setLockedWidth(true);
								doc.add(pr0);
								doc.add(pr23);
								doc.add(pr23);

								doc.add(pr23);
								doc.add(pr2);
								doc.add(tabela);
								doc.add(pr2);
								
								Paragraph pg1 = new Paragraph();
								for(i=0;i<values.size();i++)
								{
									//Paragraph pr3 = new Paragraph(values.get(i).get(0)+"    "+values.get(i).get(1)+"                    "+values.get(i).get(2)+"                  "+df.format(Double.parseDouble(values.get(i).get(3)))+"                   "+df.format(Double.parseDouble(values.get(i).get(4)))+"€                  "+df.format(Double.parseDouble(values.get(i).get(5)))+"%                  "+df.format(Double.parseDouble(values.get(i).get(6)))+"€                   "+df.format(Double.parseDouble(values.get(i).get(7)))+"€                      "+df.format(Double.parseDouble(values.get(i).get(8)))+"€",font);
									//doc.add(pr3);
									
									com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(values.get(i).get(0),font));
									c1.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(values.get(i).get(1),font));
									c2.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(values.get(i).get(2),font));
									c3.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(3))),font));
									c4.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(4)))+" €",font));
									c5.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c6 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(5)))+" %",font));
									c6.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c7 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(6)))+" €",font));
									c7.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c8 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(7)))+" €",font));
									c8.setBorderWidth(0);
									com.itextpdf.text.pdf.PdfPCell c9 = new com.itextpdf.text.pdf.PdfPCell(new Phrase(df.format(Double.parseDouble(values.get(i).get(8)))+" €",font));
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
								}
//								pg1.add(tabela1);
								doc.add(tabela1);
								doc.add(pr2);
								Paragraph pr4 = new Paragraph("                                                                                                                                                                                                        "+lblTotaliPaTVSH1.getText() + ":   " + lblTotaliPaTVSH2.getText(),font);
								Paragraph pr7 = new Paragraph("                                                                                                                                                                                                        "+lblTotaliZbritur1.getText() + ":      " + lblTotaliZbritur2.getText(),font);
								Paragraph pr8 = new Paragraph("                                                                                                                                                                                                        "+lblTotali1.getText() + ":                    " + lblTotali2.getText(),font);
								doc.add(pr4);
								doc.add(pr7);
								doc.add(pr8);
								//doc.add(pr2);
//								doc.add(pr2);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);

								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr24);
								doc.add(pr23);
								doc.add(pr23);
								Paragraph pr25 = new Paragraph("     Faturoi:                                                                                                                                                 Pranoi:",font1);
								doc.add(pr25);
								doc.add(pr24);
								doc.add(pr24);
								Paragraph pr26 = new Paragraph("     ____________________                                                                                                                      _______________________",font1);
								doc.add(pr26);
								doc.close();
								//D:\Shitjet
								Desktop.getDesktop().open(new File("D:\\Shitjet\\Fatura"+data2+koha3+".pdf"));
								count++;
								values.clear();
								
								txtBarkodi.setText("");
								txtSasia.setText("");
								lblMoney.requestFocus();
								
								if(Gjuhesia.gjuha.equals("alb"))
								{
									lblBarkodi.setText("Barkodi:");
									lblSasia.setText("Sasia:");
								}
								else
								{
									lblBarkodi.setText("Barcode:");
									lblSasia.setText("Amount:");
								}
								numri = 0;
								shumatotale = 0;
								ShumaTotaliPaTVSH = 0;
								ShumaZbritjes = 0;
								PerqindejaZbritjes = 0;
								map.clear();
								DefaultTableModel model = (DefaultTableModel)table.getModel();
								model.getDataVector().removeAllElements();
								model.fireTableDataChanged();
								lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
								lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
								
								lblTotaliPaTVSH2.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
								lblTotaliZbritur2.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
							}
						}
					}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		mntmPrint_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		menu.add(mntmPrint_1);
		mntmPerqindja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		menu.add(mntmPerqindja);
		
		mntmKthehuPrapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					conn = connectionClass.connectDb();
					String sql = "select p.pershkrimi from tblstafi s, tblpozita p where s.poziteid = p.id and s.nrPersonal = '"+Useri.getNrPersonal()+"'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					String pozita = "";
					while(res.next())
					{
						pozita = res.getString("pershkrimi");
					}
					pst.close();
					if(pozita.equals("Pronar") || pozita.equals("Menaxher"))
					{
						frmMenu  ObjMenu = new frmMenu();
						ObjMenu.setVisible(true);
						ObjMenu.setLocationRelativeTo(null);
						dispose();
					}
					else
					{
						if(Gjuhesia.gjuha.equals("alb"))
							JOptionPane.showMessageDialog(null, "Nuk mund te ju qaseni ne sherbimet e tjera te sistemit!");
						else
							JOptionPane.showMessageDialog(null, "You can not use other system services");
					}
					
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		mntmKthehuPrapa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		menu.add(mntmKthehuPrapa);
		
		JMenuItem mntmKerkoProduktin = new JMenuItem("Kerko produktin");
		mntmKerkoProduktin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmKerkoProduktin obj = new frmKerkoProduktin();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmKerkoProduktin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		menu.add(mntmKerkoProduktin);

		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mntmExit.setActionCommand("Exit");
		menu.add(mntmExit);
		mntmLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					conn = connectionClass.connectDb();
					String sql = "select p.pershkrimi from tblstafi s, tblpozita p where s.poziteid = p.id and s.nrPersonal = '"+Useri.getNrPersonal()+"'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					String pozita = "";
					while(res.next())
					{
						pozita = res.getString("pershkrimi");
					}
					pst.close();
					if(pozita.equals("Punetor"))
					{
						Useri.setEmri("");
						Useri.setMbiemri("");
						Useri.setNrPersonal("");
						frmLogin  ObjFrmLogin = new frmLogin();
						ObjFrmLogin.setVisible(true);
						
						dispose();
					}
					else
						dispose();
					
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		
		menu.add(mntmLogOut);
		
		
		try 
		{ 
			imgPerfundoBlerjen = ImageIO.read(frmShitja.class.getResource("/imgs/paguaj.png"));
			imgKerkoProduktin = ImageIO.read(frmShitja.class.getResource("/imgs/KerkoProduktin1.png"));
			imgShto = ImageIO.read(frmShitja.class.getResource("/imgs/add-shopping-cart.png"));
			imgFshijArtikullin = ImageIO.read(frmShitja.class.getResource("/imgs/deleteIcon.png"));
		}
		catch (Exception ex)
		{
		    JOptionPane.showMessageDialog(null,ex.getMessage());
		}
		
		Image dimgPerfundoBlerjen = imgPerfundoBlerjen.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iamgePerfundoBlerjen = new ImageIcon(dimgPerfundoBlerjen);
		btnPerfundoBlerjen.setIcon(iamgePerfundoBlerjen);
		
		Image dimgKerkoProduktin = imgKerkoProduktin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iamgeKerkoProduktin = new ImageIcon(dimgKerkoProduktin);
		btnKerkoProduktin.setIcon(iamgeKerkoProduktin);
		btnKerkoKlientin.setIcon(iamgeKerkoProduktin);
		
		Image dimgShto = imgShto.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iamgeShto = new ImageIcon(dimgShto);
		btnShto.setIcon(iamgeShto);
		
		Image dimgFshij = imgFshijArtikullin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imageFshij = new ImageIcon(dimgFshij);
		btnFshijArtikullin.setIcon(imageFshij);
		
		JLabel lblIkona = new JLabel("");
		lblIkona.setBackground(new Color(153, 204, 255));
		lblIkona.setOpaque(true);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(frmShitja.class.getResource("/imgs/logo1.png"));
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(null, e.getMessage());
		}
		Image dimg = img.getScaledInstance(446, 151,Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblIkona.setIcon(imageIcon);
		lblIkona.setBounds(0, 13, 1922, 151);
		contentPane.add(lblIkona);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(151, 901, 172, 2);
		contentPane.add(separator_2);
		
		NdryshoGjuhen();

	}
	private void NdryshoGjuhen()
		{
		if(Gjuhesia.gjuha=="eng")
		{
			
			validimi1 = "There are not allowed to give the amount negative numbers or values outside the interval (1,100) at the discount.";
			validimi2 = "This product doesn't exist.";
			validimi3 = "You must give the amount of the product.";
			validimi4 = "There is not enough amount.";
			validimi5 = "You must sell something.";
			validimi6 ="This buyer doesn't exist.";
			validimi7 = "THE SALE COMPLETED SUCCESFULLY!";
			validimi8 = "You haven't selected any row.";
			

			if(txtBarkodi.getText().length()==0)
			{
				lblBarkodi.setText("Barcode:");
			}
			else
			{
				lblBarkodi.setText("");
			}
			if(txtSasia.getText().length()==0)
			{
				lblSasia.setText("Amount:");
			}
			else
			{
				lblSasia.setText("");
			}
			
			table.getColumnModel().getColumn(0).setHeaderValue("No.");
			table.getColumnModel().getColumn(1).setHeaderValue("Description");
			table.getColumnModel().getColumn(2).setHeaderValue("Unit");
			table.getColumnModel().getColumn(3).setHeaderValue("Amount");
			table.getColumnModel().getColumn(4).setHeaderValue("Price");
			table.getColumnModel().getColumn(5).setHeaderValue("Disc.");
			table.getColumnModel().getColumn(6).setHeaderValue("Total without Disc.");
			table.getColumnModel().getColumn(7).setHeaderValue("Disc. amount");
			table.getColumnModel().getColumn(8).setHeaderValue("Total");
			
			scrollPane.setViewportView(table);

			rdbtnKlientIZakonshem.setText("Ordinary costumer");
			rdbtnKlientIRregullt.setText("Regular costumer");
			btnPerfundoBlerjen.setText("Complete the sale");
			btnKerkoProduktin.setText("Search the product");
			btnShto.setText("ADD");
			btnFshijArtikullin.setText("DELETE");
			btnKtheProduktin.setText("Return products");
			btnKerkoKlientin.setText("Search the client");
			
			lblIdEKlientit.setText("Client's ID:");
			lblTotaliPaTVSH1.setText("Total without Disc.");
			lblTotaliZbritur1.setText("Total discount");
			lblTotali1.setText("Total");
			lblPerdoruesi.setText("User:");
			
			mntmExit.setText("Exit");
			mntmPerqindja.setText("Discount");
			mntmKthehuPrapa.setText("Back");
			mntmLogOut.setText("Log Out");
		}
		else
		{			
			validimi1 = "Nuk lejohen vlera negative tek sasia apo vlera jashtw intervalit (1,100) tek zbritja.";
			validimi2 = "Ky produkt nuk ekziston.";
			validimi3 = "Ju duhet ta jepni sasine e produktit.";
			validimi4 = "Nuk ka sasi te mjaftueshme.";
			validimi5 = "Ju duhet te shisni dicka.";
			validimi6 ="Ky bleres nuk ekziston.";
			validimi7 = "BLERJA PERFUNDOI ME SUKSES!";
			validimi8 = "Nuk e keni selektuar asnje rresht";
			
			

			
			if(txtBarkodi.getText().length()==0)
			{
				lblBarkodi.setText("Barkodi:");
			}
			else
			{
				lblBarkodi.setText("");
			}
			if(txtSasia.getText().length()==0)
			{
				lblSasia.setText("Sasia:");
			}
			else
			{
				lblSasia.setText("");
			}
			
			table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			table.getColumnModel().getColumn(1).setHeaderValue("Emertimi");
			table.getColumnModel().getColumn(2).setHeaderValue("Nj Matese");
			table.getColumnModel().getColumn(3).setHeaderValue("Sasia");
			table.getColumnModel().getColumn(4).setHeaderValue("Cmimi");
			table.getColumnModel().getColumn(5).setHeaderValue("Zbritje");
			table.getColumnModel().getColumn(6).setHeaderValue("Totali pa zbritje");
			table.getColumnModel().getColumn(7).setHeaderValue("Shuma e zbritur");
			table.getColumnModel().getColumn(8).setHeaderValue("Totali");
			
			scrollPane.setViewportView(table);
			rdbtnKlientIZakonshem.setText("Klient i zakonshem");
			rdbtnKlientIRregullt.setText("Klient i rregullt");
			btnPerfundoBlerjen.setText("Perfundo blerjen");
			btnKerkoProduktin.setText("Kerko produktin");
			btnShto.setText("SHTO");
			btnFshijArtikullin.setText("FSHIJ");
			btnKtheProduktin.setText("Kthe produkte");	
			btnKerkoKlientin.setText("Kerko klientin");
			lblIdEKlientit.setText("ID e klientit:");
			lblTotaliPaTVSH1.setText("Totali pa Zbritje");
			lblTotaliZbritur1.setText("Totali i zbritur");
			lblTotali1.setText("Totali");
			lblPerdoruesi.setText("Perdoruesi:");
			
			mntmExit.setText("Mbylle");
			

			mntmKthehuPrapa.setText("Kthehu Prapa");
			mntmLogOut.setText("Dil");
			mntmPerqindja.setText("Perqindja");
			
		}
	}
}