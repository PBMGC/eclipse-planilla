package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class frmPlanilla extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	Thread thHora = new Thread(this);
	JDesktopPane dpPlanilla;
	JPanel pnlMenu, pnlSubMenu;
	JLabel lblSaludo, lblFecha, lblHora, lblUsuario, lblPC, imgFondo;
	JLabel lblMenu01,lblMenu02,lblMenu03,lblMenu04,lblMenu05,lblMenu06,lblMenu07,lblMenu08,lblMenu09,lblMenu10;
	JLabel imgMenu01,imgMenu02,imgMenu03,imgMenu04,imgMenu05,imgMenu06,imgMenu07,imgMenu08,imgMenu09,imgMenu10;
	JLabel lblSubMenu01,lblSubMenu02,lblSubMenu03,lblSubMenu04,lblSubMenu05,lblSubMenu06,lblSubMenu07,lblSubMenu08,lblSubMenu09,lblSubMenu10,lblSubMenu11,lblSubMenu12,lblSubMenu13;	
	JButton btnTrabajadores, btnPlanillas, btnInformes, btnConfiguraciones, btnUtilitarios;

	JLabel aLblMenu[], aLblSubMenu[], aImgMenu[];
	
	String[][] aMenu = {
			{ "PERSONAL","ASIGNACIÓN CONCEPTOS","TABLAS" },
			{ "PROCESAR PLANILLLAS", "CALCULAR 5TA. CATEGORÍA", "GRATIFICACIONES", "VACACIONES", "C.T.S", "LIQUIDACIÓN" }
			};

	String[][][] aSubMenu = {
		{
			{ "Ficha del Personal","Contratos","Derecho de habiente","Control de asistencia","Adelantos y/o asistencia","Prestamos","Pre-Pos Natal / Bonificaciones" },
			{ "Conceptos del trabajado","Conceptos por sede","Aplicar a todos" },
			{ "Iniciar período","Declarantes","Sede","Centro de costos","Cargo","Departamento","Moneda","Conceptos","Comisión AFPs","Régimen pensionario","Turno","Montos por hora","Bancos" }
		},
		{
			{ "Calcular planilla","Reporte planilla","Generación boletas" },
			{  },
			{ "Generar planilla de gratificaciones","Reporte de planilla de gratificaciones","Anular planilla de gratificaciones","Generar boleta de pago de gratificación" }
		},
		{},{},{}
	};

	int MenuPlanilla, Menu, SubMenu;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPlanilla frame = new frmPlanilla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmPlanilla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		Image imageFondo=null, imageMenu=null;
		try {
			BufferedImage bufferedImage = ImageIO.read( new File( getClass().getResource("/gui/img/fondoApp.png" ).getFile() ) );
			imageFondo = bufferedImage.getScaledInstance(1366, 700, Image.SCALE_DEFAULT);
			
			bufferedImage = ImageIO.read( new File( getClass().getResource("/gui/img/logoMenu_1.png" ).getFile() ) );
			imageMenu = bufferedImage.getScaledInstance(230, 50, Image.SCALE_DEFAULT);
		} catch (IOException e) { e.printStackTrace(); }
		
		dpPlanilla= new JDesktopPane();
		dpPlanilla.setBounds(300,50,1065,700);
		dpPlanilla.setVisible(false);
		contentPane.add(dpPlanilla);

		imgFondo = new JLabel();
		imgFondo.setBounds(0,80,1366,700);
		imgFondo.setIcon(new ImageIcon(imageFondo));
		contentPane.add(imgFondo);

		lblSaludo = new JLabel();
		lblSaludo.setBounds(0,50,1366,30);
		lblSaludo.setBackground( new Color(221,222,226));
		contentPane.add(lblSaludo);

		lblFecha = new JLabel();
		lblFecha.setBounds(1100,745,150,30);
		contentPane.add(lblFecha);
		
		lblHora = new JLabel();
		lblHora.setBounds(1250,745,150,30);
		contentPane.add(lblHora);
		
		JPanel pnlApp = new JPanel();
		pnlApp.setBounds(0,0,1366,50);
		pnlApp.setBackground( new Color(90,17,93) );
		pnlApp.setLayout(null);
		contentPane.add(pnlApp);
		
		JLabel imgLogo = new JLabel();
		imgLogo.setBounds(10,3,208,43);
		imgLogo.setIcon(new ImageIcon(getClass().getResource("/gui/img/logo.png")));
		pnlApp.add(imgLogo);
		
		JLabel imgCerrar = new JLabel();
		imgCerrar.setBounds(1320,9,32,32);
		imgCerrar.setIcon(new ImageIcon(getClass().getResource("/gui/img/cerrar.png")));
		pnlApp.add(imgCerrar);

		btnTrabajadores = new JButton("TRABAJADORES");
		btnTrabajadores.setBounds(250,0,150,50);
		btnTrabajadores.setBackground( new Color(90,17,93) );
		btnTrabajadores.setBorderPainted(false);
		btnTrabajadores.setFocusPainted(false);
		btnTrabajadores.setForeground(Color.white);
		pnlApp.add(btnTrabajadores);
		
		btnPlanillas = new JButton("PLANILLAS");
		btnPlanillas.setBounds(400,0,150,50);
		btnPlanillas.setBackground( new Color(90,17,93) );
		btnPlanillas.setBorderPainted(false);
		btnPlanillas.setFocusPainted(false);
		btnPlanillas.setForeground(Color.white);
		pnlApp.add(btnPlanillas);

		btnInformes = new JButton("INFORMES");
		btnInformes.setBounds(550,0,150,50);
		btnInformes.setBackground( new Color(90,17,93) );
		btnInformes.setBorderPainted(false);
		btnInformes.setFocusPainted(false);
		btnInformes.setForeground(Color.white);
		pnlApp.add(btnInformes);
		
		btnConfiguraciones = new JButton("CONFIGURACIONES");
		btnConfiguraciones.setBounds(700,0,150,50);
		btnConfiguraciones.setBackground( new Color(90,17,93) );
		btnConfiguraciones.setBorderPainted(false);
		btnConfiguraciones.setFocusPainted(false);
		btnConfiguraciones.setForeground(Color.white);
		pnlApp.add(btnConfiguraciones);
		
		btnUtilitarios = new JButton("UTILITARIOS");
		btnUtilitarios.setBounds(850,0,150,50);
		btnUtilitarios.setBackground( new Color(90,17,93) );
		btnUtilitarios.setBorderPainted(false);
		btnUtilitarios.setFocusPainted(false);
		btnUtilitarios.setForeground(Color.white);
		pnlApp.add(btnUtilitarios);
		
		pnlMenu = new JPanel();
		pnlMenu.setBounds(0,50,300,700);
		pnlMenu.setBackground( new Color(57, 66, 69) );
		pnlMenu.setLayout(null);
		pnlMenu.setVisible(false);
		contentPane.add(pnlMenu);
		
		JLabel imgMenu = new JLabel();
		imgMenu.setBounds(35,0,300,55);
		imgMenu.setIcon(new ImageIcon(imageMenu));
		pnlMenu.add(imgMenu);
		
		lblMenu01 = new JLabel();
		lblMenu01.setIconTextGap(10);
		lblMenu01.setForeground( Color.white );
		pnlMenu.add(lblMenu01);
		
		lblMenu02 = new JLabel();
		lblMenu02.setIconTextGap(10);
		lblMenu02.setForeground( Color.white );
		pnlMenu.add(lblMenu02);

		lblMenu03 = new JLabel();
		lblMenu03.setIconTextGap(10);
		lblMenu03.setForeground( Color.white );
		pnlMenu.add(lblMenu03);
		
		lblMenu04 = new JLabel();
		lblMenu04.setIconTextGap(10);
		lblMenu04.setForeground( Color.white );
		pnlMenu.add(lblMenu04);
		
		lblMenu05 = new JLabel();
		lblMenu05.setIconTextGap(10);
		lblMenu05.setForeground( Color.white );
		pnlMenu.add(lblMenu05);

		lblMenu06 = new JLabel();
		lblMenu06.setIconTextGap(10);
		lblMenu06.setForeground( Color.white );
		pnlMenu.add(lblMenu06);
		
		lblMenu07 = new JLabel();
		lblMenu07.setIconTextGap(10);
		lblMenu07.setForeground( Color.white );
		pnlMenu.add(lblMenu07);

		lblMenu08 = new JLabel();
		lblMenu08.setIconTextGap(10);
		lblMenu08.setForeground( Color.white );
		pnlMenu.add(lblMenu08);
		
		lblMenu09 = new JLabel();
		lblMenu09.setIconTextGap(10);
		lblMenu09.setForeground( Color.white );
		pnlMenu.add(lblMenu09);

		lblMenu10 = new JLabel();
		lblMenu10.setIconTextGap(10);
		lblMenu10.setForeground( Color.white );
		pnlMenu.add(lblMenu10);
		
		imgMenu01 = new JLabel();
		pnlMenu.add(imgMenu01);
		
		imgMenu02 = new JLabel();
		pnlMenu.add(imgMenu02);

		imgMenu03 = new JLabel();
		pnlMenu.add(imgMenu03);

		imgMenu04 = new JLabel();
		pnlMenu.add(imgMenu04);

		imgMenu05 = new JLabel();
		pnlMenu.add(imgMenu05);

		imgMenu06 = new JLabel();
		pnlMenu.add(imgMenu06);

		imgMenu07 = new JLabel();
		pnlMenu.add(imgMenu07);

		imgMenu08 = new JLabel();
		pnlMenu.add(imgMenu08);

		imgMenu09 = new JLabel();
		pnlMenu.add(imgMenu09);

		imgMenu10 = new JLabel();
		pnlMenu.add(imgMenu10);

		pnlSubMenu = new JPanel();
		pnlSubMenu.setBackground( new Color(57, 66, 69) );
		pnlSubMenu.setLayout(null);
		pnlSubMenu.setVisible(false);
		pnlMenu.add(pnlSubMenu);
				
		lblSubMenu01 = new JLabel();
		lblSubMenu01.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu01);
		
		lblSubMenu02 = new JLabel();
		lblSubMenu02.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu02);

		lblSubMenu03 = new JLabel();
		lblSubMenu03.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu03);

		lblSubMenu04 = new JLabel();
		lblSubMenu04.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu04);

		lblSubMenu05 = new JLabel();
		lblSubMenu05.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu05);

		lblSubMenu06 = new JLabel();
		lblSubMenu06.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu06);

		lblSubMenu07 = new JLabel();
		lblSubMenu07.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu07);

		lblSubMenu08 = new JLabel();
		lblSubMenu08.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu08);

		lblSubMenu09 = new JLabel();
		lblSubMenu09.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu09);

		lblSubMenu10 = new JLabel();
		lblSubMenu10.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu10);

		lblSubMenu11 = new JLabel();
		lblSubMenu11.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu11);

		lblSubMenu12 = new JLabel();
		lblSubMenu12.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu12);
		
		lblSubMenu13 = new JLabel();
		lblSubMenu13.setForeground( Color.white );
		pnlSubMenu.add(lblSubMenu13);


		this.addWindowListener(new WindowAdapter() { @Override public void windowOpened(WindowEvent e) { form_windowsOpened(); } });
		
		imgCerrar.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgCerrar_Clicked(); } });

		btnTrabajadores.addMouseListener(new MouseAdapter() { 
			@Override public void mouseEntered(MouseEvent e) { btnApp_mouseEntered(e); } 
			@Override public void mouseExited(MouseEvent e) { btnApp_mouseExited(e); }	
			@Override public void mouseClicked(MouseEvent e) { btnMenuPlanilla_mouseClicked(1);	} } );
		
		btnPlanillas.addMouseListener(new MouseAdapter() { 
			@Override public void mouseEntered(MouseEvent e) { btnApp_mouseEntered(e); } 
			@Override public void mouseExited(MouseEvent e) { btnApp_mouseExited(e); } 
			@Override public void mouseClicked(MouseEvent e) { btnMenuPlanilla_mouseClicked(2); } } );
		
		btnInformes.addMouseListener(new MouseAdapter() { 
			@Override public void mouseEntered(MouseEvent e) { btnApp_mouseEntered(e); } 
			@Override public void mouseExited(MouseEvent e) { btnApp_mouseExited(e); } 
			@Override public void mouseClicked(MouseEvent e) { btnMenuPlanilla_mouseClicked(3); } });
		
		btnConfiguraciones.addMouseListener(new MouseAdapter() { 
			@Override public void mouseEntered(MouseEvent e) { btnApp_mouseEntered(e); } 
			@Override public void mouseExited(MouseEvent e) { btnApp_mouseExited(e); } 
			@Override public void mouseClicked(MouseEvent e) { btnMenuPlanilla_mouseClicked(4); } });
	
		btnUtilitarios.addMouseListener(new MouseAdapter() { 
			@Override public void mouseEntered(MouseEvent e) { btnApp_mouseEntered(e); } 
			@Override public void mouseExited(MouseEvent e) { btnApp_mouseExited(e); } 
			@Override public void mouseClicked(MouseEvent e) { btnMenuPlanilla_mouseClicked(5); } });

		imgMenu01.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(1); } });
		imgMenu02.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(2); } });
		imgMenu03.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(3); } });
		imgMenu04.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(4); } });
		imgMenu05.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(5); } });
		imgMenu06.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(6); } });
		imgMenu07.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(7); } });
		imgMenu08.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(8); } });
		imgMenu09.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(9); } });
		imgMenu10.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgMenu_mouseClicked(10); } });
		
		
		lblSubMenu01.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);}			
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(1);} });

		lblSubMenu02.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(2);} });


		lblSubMenu03.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(3);} });
		
		lblSubMenu04.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(4);} });

		lblSubMenu05.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(5);} });

				
		lblSubMenu06.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(6);} });

		
		lblSubMenu07.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(7);} });

		
		lblSubMenu08.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(8);} });
		
		lblSubMenu09.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(9);} });
	
		lblSubMenu10.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(10);} });
	
		lblSubMenu11.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(11);} });
		
		lblSubMenu12.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) {lblSubMenu_mouseEntered(e); }
			@Override public void mouseExited(MouseEvent e) {lblSubMenu_mouseExited(e);} 
			@Override public void mouseClicked(MouseEvent e) {lblSubMenu_mouseClicked(12);} });		
		
	}

	protected void form_windowsOpened() {
		aLblMenu = new JLabel[] { lblMenu01,lblMenu02,lblMenu03,lblMenu04,lblMenu05,lblMenu06,lblMenu07,lblMenu08,lblMenu09,lblMenu10 };
		aLblSubMenu = new JLabel[] { lblSubMenu01,lblSubMenu02,lblSubMenu03,lblSubMenu04,lblSubMenu05,lblSubMenu06,lblSubMenu07,lblSubMenu08,lblSubMenu09,lblSubMenu10,lblSubMenu11,lblSubMenu12,lblSubMenu13 };
		aImgMenu = new JLabel[] { imgMenu01,imgMenu02,imgMenu03,imgMenu04,imgMenu05,imgMenu06,imgMenu07,imgMenu08,imgMenu09,imgMenu10 };
		
		lblSaludo.setText("    Hola " + "" + ",Bienvenido al Sistema de Planilla X6");
		lblFecha.setText( "Fecha : " + new SimpleDateFormat("dd/MM/yyy").format( new Date() ) );
		thHora.start();
	}

	protected void btnApp_mouseEntered(MouseEvent e) {
		e.getComponent().setBackground( new Color(134,37,158) );
	}

	protected void btnApp_mouseExited(MouseEvent e) {
		e.getComponent().setBackground( new Color(90,17,93) );
	}

	protected void btnMenuPlanilla_mouseClicked(int menuPlanilla) {
		MenuPlanilla = menuPlanilla - 1;
		lblSaludo.setVisible(false);
		imgFondo.setVisible(false);
		pnlMenu.setVisible(true);
		pnlSubMenu.setVisible(true);
		imgMenu_mouseClicked(1);	
	}

	protected void imgMenu_mouseClicked(int menu) {
		Menu = menu - 1;
		int filasSubMenu = aSubMenu[MenuPlanilla][Menu].length;
		
		for( JLabel lblMenu : aLblMenu ) lblMenu.setVisible(false);		
		for( JLabel imgMenu : aImgMenu ) imgMenu.setVisible(false);
		for( JLabel lblSubMenu : aLblSubMenu ) lblSubMenu.setVisible(false);
		
		pnlSubMenu.setBounds(0, 70 + (Menu + 1)*25 + ( Menu == 0 ? 0 : 10), 300, filasSubMenu * 30 );
		for ( int i=0; i < filasSubMenu; i++ ) {
			aLblSubMenu[i].setBounds(40,10 + i*30,280,25);
			aLblSubMenu[i].setText( aSubMenu[MenuPlanilla][Menu][i] );
			aLblSubMenu[i].setVisible(true);
		}
		
		int altura = pnlSubMenu.getHeight();
		for( int i=0, filaMenu=0;i < aMenu[MenuPlanilla].length; i++ ) {
			filaMenu =80 + i*30 + ( i <= Menu ? 0 : altura );
			aLblMenu[i].setBounds(10,filaMenu,200,25);
			aLblMenu[i].setIcon(new ImageIcon(getClass().getResource("/gui/img/menu" + (MenuPlanilla + 1) + (i + 1) + ".png")));
			aLblMenu[i].setText( aMenu[MenuPlanilla][i] );
			aLblMenu[i].setVisible(true);
			
			aImgMenu[i].setBounds(270,80 + i*30 + ( i <= Menu ? 0 : altura ),20,20);
			aImgMenu[i].setIcon(new ImageIcon(getClass().getResource("/gui/img/" + ( i!=Menu  ? "arriba" : "abajo" ) + ".png")));
			aImgMenu[i].setVisible(true);	
		}
		
	}
	
	
	protected void lblSubMenu_mouseExited(MouseEvent e) {
		e.getComponent().setForeground( Color.WHITE );		
	}

	protected void lblSubMenu_mouseEntered(MouseEvent e) {
		e.getComponent().setForeground( new Color(100,181,246));		
	}

	protected void lblSubMenu_mouseClicked(int subMenu) {		
		SubMenu= subMenu-1;
		JInternalFrame formulario=null;
		dpPlanilla.setVisible(true);
		dpPlanilla.removeAll();
		switch(MenuPlanilla){
			case 0:
				switch (Menu) {
					case 0:break;
					case 1:break;
					case 2:
						switch(subMenu) {
							case 4: formulario = new frmCargo();break;
				}
			}
			
		}
		dpPlanilla.add(formulario);
		formulario.setVisible(true);
	}
	

	protected void imgCerrar_Clicked() {
//		if ( JOptionPane.showConfirmDialog(this, "� Desea salir de la app ?", "Salir", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION )
			System.exit(0);
	}

	@Override
	public void run() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		while(true) {
			lblHora.setText("Hora : " + dateFormat.format( new Date()) );
			try { thHora.sleep(1000);
			} catch (InterruptedException e) { e.printStackTrace();}
		}
			
		
	}


}
