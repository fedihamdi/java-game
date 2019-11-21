
package devinette.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import net.proteanit.sql.DbUtils;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;


public class VueMaVue extends JFrame {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JPanel onglet1,onglet2,onglet3 ;

	private JMenuBar barreMenus;
	private JMenu partie, joueur, couleurs ;
	private JMenuItem nouvelle, recherche, quitter, consulter,bleu,rose,mauve;
	protected JTabbedPane onglets;
	private JPanel pNord1;
	
	private BorderLayout border1;
	private JPanel pCentre, pEst;
	
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JFormattedTextField t4;
	private JComboBox<String> c;
	private JButton b1;
	private Component inscrit;
	private Component pseudo;
	private Component nom;
	
	private JTextField texte1;
	private JTextField texte2;
	private JLabel datnais;
	private JFormattedTextField date;
	private JLabel sexe;
	private Component prenom;
	private JComboBox<String> c1;
	private JTextField texte3;
	private GridBagConstraints g;
	private GridBagConstraints e;
	private JButton b3;
	private JButton b4;
	private JPanel pNord2;
	private JPanel pCentre2;
	private JLabel image;
	
	
	private BorderLayout border2;
	private JPanel pSouth;
	private JButton b2;
	private JButton prec;
	private JButton suiv;
	private GridBagConstraints n;
	
	private JLabel txt1;
	private JPanel pCenter3;
	private JLabel txt2;
	private JLabel txt3;
	private JLabel txt4;
	private JLabel txt5;
	private JTextField txt6;
	
	private JButton bu;
	private GridBagConstraints l;
	
	public static String message="nombre d'essais restants ";
	Connection connexion=MaConnexion.getConnection();
	ResultSet resultat=null;
	private Statement statement;
	String stm="SELECT * FROM joueur";
	private JLabel txt7;
	private int nbessaijoueur=8;
	public int nbrjoueur;
	Random rand = new Random();
	int nbrrand= rand.nextInt(31);

	private JTable table;
	public VueMaVue() 
	{   super();
		setTitle("Ma petite application");
		setSize(1150,990);
		setLocation(100,30);
		
		barreMenus = new JMenuBar();
		setJMenuBar(barreMenus);
		
		/* creation menu partie et ses options */
		partie = new JMenu("Partie");
		barreMenus.add(partie);
		nouvelle = new JMenuItem("Nouvelle Partie...");
		quitter = new JMenuItem("Quitter");
		partie.add(nouvelle);
		partie.addSeparator();
		partie.add(quitter);
		
		nouvelle.addActionListener(new ActionNouvelle());
		quitter.addActionListener(new ActionQuitter());
		
		/*Creation menu Joueur et ses options */
		joueur = new JMenu("Joueur");
		barreMenus.add(joueur);
		consulter = new JMenuItem("Consulter joueurs/Classement");
		recherche = new JMenuItem("Recherche");
		joueur.add(recherche);
		joueur.addSeparator();
		joueur.add(consulter);
		consulter.addActionListener(new ActionConsulter());
		recherche.addActionListener(new ActionRecherche());
		/*creation menu couleur et ses options */
		couleurs = new JMenu("Couleur");
		barreMenus.add(couleurs);
		bleu= new JMenuItem("Bleu");
		rose= new JMenuItem("Rose");
		mauve= new JMenuItem("Mauve");
		couleurs.add(bleu);
		
		bleu.addActionListener(new ActionCouleurs());
		couleurs.addSeparator();
		couleurs.add(rose);
		rose.addActionListener(new ActionCouleurs());
		couleurs.addSeparator();
		
		couleurs.add(mauve);
		mauve.addActionListener(new ActionCouleurs());
		
		/*creation des onglets*/
		onglets = new JTabbedPane();
	    onglet1 = new JPanel();
	    onglet2 = new JPanel();
	    onglet3 = new JPanel();
	    
	
	    
	  //Ajout des onglets au menu
	    
        onglets.add("Inscription",onglet1); 
        onglets.add("Consultation et classement",onglet2);
        onglets.add("Jouer ",onglet3);
        add(onglets);
        onglets.setEnabledAt(2, false);
       /* onglets.setEnabledAt(1, false);*/
        
        
        /*l'onglet Inscription*/
        pNord2 = new JPanel();
        pNord2.setBackground(Color.blue);
        border2 = new BorderLayout();
        onglet1.setLayout(border2);
        onglet1.add(pNord2,BorderLayout.NORTH);
        
        image= new JLabel(new ImageIcon("2.PNG"));
		pNord2.add(image);
		pCentre2= new JPanel() ;
		onglet1.add(pCentre2,BorderLayout.CENTER);
		inscrit=new JLabel("inscrivez vous avant de commencer à jouer");
		pCentre2.add(inscrit);
		pseudo=new JLabel("Pseudo");pCentre2.add(pseudo); 
		nom=new JLabel("Nom");pCentre2.add(nom);
		datnais=new JLabel("Date de Naissance ");pCentre2.add(datnais);
		texte1= new JTextField("",15);pCentre2.add(texte1);
		
		 
		texte2= new JTextField("",15);pCentre2.add(texte2);
		
		
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		date= new JFormattedTextField (f.format(new java.util.Date())); 
		date.setPreferredSize(new Dimension (120,20));
		pCentre2.add(date);
	
		
		 
		sexe=new JLabel("Sexe");pCentre2.add(sexe);
		 prenom=new JLabel("Prénom");pCentre2.add(prenom);
		 
		c1=new JComboBox<String>();pCentre2.add(c1);
		c1.addItem("Masculin");
		c1.addItem("Féminin");
		c1.setPreferredSize(new Dimension (100,20));
	
		texte3= new JTextField("",15);pCentre2.add(texte3);
		texte3.setPreferredSize(new Dimension (120,20));
		 
		 getContentPane();
		 pCentre2.setLayout(new GridBagLayout());		
		        g=new GridBagConstraints();
		 		g.insets= new Insets(10,15,10,15);
		 		 g.gridx=1;g.gridy=0;
		 		 pCentre2.add(inscrit,g);
		 		 g.gridx=0;g.gridy=1;
		 		 pCentre2.add(pseudo,g);
		 		 g.gridx=0;g.gridy=2;
		 		 pCentre2.add(nom,g);
		 		 g.gridx=0;g.gridy=3;
		 		 pCentre2.add(datnais,g);
		 		 g.gridx=1;g.gridy=1;
		 		 pCentre2.add(texte1,g);
		 		 g.gridx=1;g.gridy=2;
		 		 pCentre2.add(texte2,g);
		 		 g.gridx=1;g.gridy=3;
		 		 pCentre2.add(date,g);
		 		 g.gridx=2;g.gridy=1;
		 		 pCentre2.add(sexe,g);
		 		 g.gridx=2;g.gridy=2;
		 		 pCentre2.add(prenom,g);
		 		 g.gridx=3;g.gridy=1;
		 		 pCentre2.add(c1,g);
		 		 g.gridx=3;g.gridy=2;
		 		 pCentre2.add(texte3,g);
		 
		
		
		
		  getContentPane();
		   	pSouth=new JPanel();
		    pSouth.setLayout(new GridBagLayout());
		    onglet1.add(pSouth,BorderLayout.SOUTH);
		    e=new GridBagConstraints();
		  
		    e.gridy=0;e.gridx=0;
		    b3= new JButton("Ajouter");
		    pSouth.add(b3,e);
		    e.gridy=0;e.gridx=1;
	   b3.addActionListener(new ActionAjouter());
		    
		    b4= new JButton("Annuler");
	    	pSouth.add(b4,e);
	    	b4.addActionListener(new ActionAnnuler());
        
        /** *  *  *  *        *      * ** */
        /* l'onglet Consultation */
		pNord1= new JPanel() ;
		pNord1.setBackground(Color.BLUE);
		border1 = new BorderLayout();
		onglet2.setLayout(border1);
		onglet2.add(pNord1,BorderLayout.NORTH);
		pCentre= new JPanel() ;
		onglet2.add(pCentre,BorderLayout.CENTER);
		pEst=  new JPanel() ;
		pEst.setBackground(Color.white);
		onglet2.add(pEst,BorderLayout.EAST);
		onglet2.add(pNord1,"North");
		onglet2.add(pCentre,"Center");
		onglet2.add(pEst,"East");
		t1= new JTextField (15);
		pNord1.add(t1);
		
		t2= new JTextField (15);
		pNord1.add(t2);
		
		t3= new JTextField (15);
		pNord1.add(t3);
		 
		
		t4= new JFormattedTextField (new SimpleDateFormat("yyyy-MM-dd")) ; 
		t4.setPreferredSize(new Dimension (120,20));
		pNord1.add(t4);
		c=new JComboBox<String>();
		pNord1.add(c);
		c.addItem("Masculin");
		c.addItem("Féminin");
		b1= new JButton("Modifier");
		pNord1.add(b1);
		b1.addActionListener(new ActionModifier());
		b2= new JButton("Supprimer");
		pNord1.add(b2);
		b2.addActionListener(new ActionSupprimer());
		prec=new JButton("<");
		prec.addActionListener(new ActionPrecedent());
		pNord1.add(prec);
	    
	    
	    suiv=new JButton(">");
	    pNord1.add(suiv);
	    suiv.addActionListener(new ActionSuivant());
	   
	  
	   
	    pNord1.setLayout(new GridBagLayout());
	    		 n=new GridBagConstraints();
	    		 n.insets= new Insets(5,15,7,15);
	    		 n.gridx=0;n.gridy=0;
	    		 pNord1.add(t1,n);
	    		 n.gridx=1;n.gridy=0;
	    		 pNord1.add(t2,n);
	    		 n.gridx=2;n.gridy=0;
	    		 pNord1.add(t3,n);
	    		 n.gridx=3;n.gridy=0;
	    		 pNord1.add(t4,n);
	    		 n.gridx=4;n.gridy=0;
	    		 pNord1.add(c,n);
	    		 n.gridx=5;n.gridy=0;
	    		 pNord1.add(b1,n);
	    		 n.gridx=6;n.gridy=0;
	    		 pNord1.add(b2,n);
	    		 n.gridx=2;n.gridy=2;
	    		 
	    		 pNord1.add(prec,n);
	    		 n.gridx=4;n.gridy=2;
	    		
	    		 pNord1.add(suiv,n);
		onglet2.add(pCentre,"Center");
		
		   
		 BorderLayout w = new BorderLayout();
			  
		  onglet2.setLayout(w);
				
			
		    onglet2.add(pNord1,BorderLayout.NORTH);
			
				
		onglet2.setLayout(w);
		BorderLayout k = new BorderLayout();
		JPanel CenterImg=new JPanel();
		CenterImg.setLayout(k);
		pCentre.add(CenterImg,BorderLayout.NORTH);
		onglet2.add(pCentre,BorderLayout.CENTER);
		pCentre.setLayout(new BorderLayout());
		image= new JLabel(new ImageIcon("1.PNG"));
		pCentre.add(image,BorderLayout.NORTH);
		BorderLayout z = new BorderLayout();
		JPanel CentreTab=new JPanel();
		CentreTab.setLayout(z);
		pCentre.add(CentreTab,BorderLayout.CENTER);

	
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("pseudo");
		model.addColumn("nom");
		model.addColumn("prenom");
		model.addColumn("datenais");
		model.addColumn("sexe");
		model.addColumn("nbressai");
		table = new JTable(model);
		
		try {
			

			String sq = "SELECT pseudo,nom,prenom,datnais,sexe, nbressai FROM joueur ORDER BY nbressai ASC";
			Statement st= connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(sq);
			while (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String datnais = rs.getString("datnais");
				String sexe = rs.getString("sexe");
				String nbressai = rs.getString("nbressai");
				model.addRow(new Object[] { pseudo, nom, prenom, datnais, sexe, nbressai });
				rs.updateRow();
				UpdateTable();

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		JScrollPane scrollpane = new JScrollPane(table);
		pCentre.add(scrollpane,BorderLayout.CENTER);
		
		
		/* Onglet Jouer */
		
		pCenter3= new JPanel() ;
		onglet3.add(pCenter3);
	    BorderLayout d = new BorderLayout();
		  
		onglet3.setLayout(d);
		onglet3.add(pCenter3,BorderLayout.CENTER);
	    pCenter3.setBackground(Color.pink);
	txt1= new JLabel("Jeu de Devinette");
	pCenter3.add(txt1);
	txt2= new JLabel("Bienvenu");
	pCenter3.add(txt2);
	txt3= new JLabel("entrer un nombre entre 0 et 30");
	pCenter3.add(txt3);
	txt4= new JLabel(message);
	pCenter3.add(txt4);
	txt5= new JLabel("8");
	pCenter3.add(txt5);
	txt6= new JTextField(8);
	pCenter3.add(txt6);
	txt7=new JLabel("attention !!");
	txt7.setForeground(Color.white);
	pCenter3.add(txt7);
	bu= new JButton ("Valider");
	pCenter3.add(bu);
	bu.addActionListener(new ActionListener() {
		
		
		

		public void actionPerformed(ActionEvent e) {
			
		      do {
		         
		    	 
		    		 nbrjoueur = Integer.parseInt(txt6.getText());
		    	 
		    		 
			         
		    	 
		    	 nbessaijoueur--;
		         txt5.setText(Integer.toString(nbessaijoueur));
		    	 if(nbrjoueur < nbrrand)
			        	
		        	{txt7.setText("Trop petit !");}
		        
		            
		         else if(nbrjoueur > nbrrand)
		        	{txt7.setText("Trop grand !");}
		        	
		        txt6.setText("");
		         
		        
		      
		} while((nbrjoueur != nbrrand) && (nbessaijoueur >0));
		      
		     
		     int nbressai= 8-nbessaijoueur;
		      if ((nbessaijoueur == 0) && (nbrjoueur != nbrrand)) { JOptionPane.showMessageDialog(null, "Vous avez perdu, le nombre à dévouvrir est : "+nbrrand+" !");}
		     else {JOptionPane.showMessageDialog(null, "Bravo ! vous avez gagnez au bout de" + nbressai + " essais"); }
		     try {	// connexion = maConnection();
		    	String sql="SELECT * FROM joueur where pseudo ='"+t1.getText()+"'";
		    	   Statement  st = connexion.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
					ResultSet rs = st.executeQuery(sql); 
			        
			        rs.updateInt(8,nbressai);
			        rs.updateRow();
	 				UpdateTable(); 
	 				 
			    	}catch(SQLException ee) {
			    		System.err.println(ee.getMessage());}

		      txt5.setText("8");txt6.setText("");
		         UpdateTable(); 
		         
		   }});
	
	  getContentPane();
	   
	    pCenter3.setLayout(new GridBagLayout());
	    		 l=new GridBagConstraints();
	    		 l.insets= new Insets(20,25,20,25);
	  
	    l.gridx=1;l.gridy=0;
	    pCenter3.add(txt1,l);
	    
	    l.gridx=1;l.gridy=1;
	    pCenter3.add(txt2,l);
	    
	    l.gridx=0;l.gridy=2;
	    pCenter3.add(txt3,l);
	    
	    l.gridx=0;l.gridy=4;
	    pCenter3.add(txt4,l);
	    
	    l.gridx=1;l.gridy=4;
	    pCenter3.add(txt5,l);
	    
	    l.gridx=2;l.gridy=2;
	    pCenter3.add(txt6,l);
	    
	    l.gridx=2;l.gridy=3;
	    pCenter3.add(bu,l);
	    
	    l.gridx=3;l.gridy=2;
	    pCenter3.add(txt7,l);
	    try {
	  		   statement = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				resultat= statement.executeQuery(stm);
				
					if (resultat.next()) {
						t1.setText(resultat.getString(1));
						t2.setText(resultat.getString(2));
						t3.setText(resultat.getString(3));
						t4.setText(resultat.getString(4));
						c.setSelectedItem(resultat.getString(5));
						UpdateTable();
						    
					
					
					}}
	   
					
	catch (SQLException e) {
					
		 System.err.println("Avoir une exception ! ");
		 System.err.println(e.getMessage());
	}
	   
		
		
		setVisible(true);
	}
	
	/*les classes des evenements*/
	
	public class ActionCouleurs implements ActionListener {
		public synchronized void actionPerformed(ActionEvent e) {
			if (e.getSource()== bleu)
			{pNord1.setBackground(Color.blue);
			pCenter3.setBackground(Color.blue);
			pNord2.setBackground(Color.blue);
		} else if(e.getSource()==rose) {
			pNord1.setBackground(Color.pink);
			pCenter3.setBackground(Color.pink);
			pNord2.setBackground(Color.pink);
			
		}else if(e.getSource()== mauve) {
			pNord1.setBackground(Color.magenta);
			pCenter3.setBackground(Color.magenta);
			pNord2.setBackground(Color.magenta);
		}
			}
		}
	
	public class ActionNouvelle implements ActionListener{
		

		public synchronized void actionPerformed(ActionEvent e1) {
		
			String j = "";
			int i=j.length();

			try {
				try {
				j = JOptionPane.showInputDialog(null, "saisissez le pseudo svp", "Super vous voulez jouer",JOptionPane.QUESTION_MESSAGE);
				if(( j==null || j.length()>0)) {i++;}
				}catch(Exception e){
					Logger.getLogger(VueMaVue.class.getName()).log(Level.SEVERE, null, e);
				}
				
		           while(i<=0) {
					 
						j = JOptionPane.showInputDialog(null, "vous n'avez rien saisi, saisissez le pseudo svp", "Erreur",JOptionPane.WARNING_MESSAGE);
						
						if(( j==null || j.length()>0)) {i++;}
					};
					if ((j!=null)) {
						
						
						
			            
						try {
							
							Statement stm=connexion.createStatement();
							ResultSet rs=stm.executeQuery("SELECT * FROM joueur WHERE pseudo='"+j+"'");
							//rs.last();
							//int nbr=rs.getRow();
							//rs.beforeFirst();
							//System.out.println(nbr);
							
							if(rs.next()) {	
							onglets.setEnabledAt(2,true);/*REACTIVER ONGlET JOUER*/
							onglets.setSelectedIndex(2);/*SELECTIONNER ONGLET JOUER*/
							txt2.setText("Bienvenu   " +j);
							
								String pseudo = rs.getString("pseudo");
								String nom = rs.getString("nom");
								String prenom = rs.getString("prenom");
								String datnais = rs.getString("datnais");
								String sexe = rs.getString("sexe");
								t1.setText(pseudo.toString());
								t2.setText(nom.toString());
								t3.setText(prenom.toString());
								t4.setText(datnais.toString());
								c.setSelectedItem(sexe.toString());
								

							
							}
							else {
								JOptionPane.showMessageDialog(null,j+"  Pseudo inexistant !");

							}
						} catch (Exception e) {e.printStackTrace();
							
						}
						};
			} catch (Exception e) {
				e.printStackTrace();

			}UpdateTable();
			
			
			/*ILS NOUS RESTENT LES CONDITIONS EN RELATION AVEC LA BASE*/
			
				
		
		}
	}

	public class ActionQuitter implements ActionListener{
		public synchronized void actionPerformed(ActionEvent e2) {
			int i = -1;
			  i=JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter l'application ?", "Quitter", i );
			  switch(i)
			  {
			  	case 0 :
			    System.exit(0);
			  	case 1 :
		}}}
	public class ActionConsulter implements ActionListener{
		public synchronized void actionPerformed(ActionEvent e4) {
			/*onglets.setEnabledAt(1,false);*/
			onglets.setSelectedIndex(1);
			UpdateTable();
			
		}}
	public class ActionRecherche implements ActionListener{
		 private String x="";
		public synchronized void actionPerformed(ActionEvent e5) {
			/*onglets.setEnabledAt(1,false);*/
			onglets.setSelectedIndex(1);
			int i=-1;
			while(i<0)
			{
			 x =JOptionPane.showInputDialog(null,"Saisissez Pseudo Joueur SVP:","Cherchez un pseudo",JOptionPane.INFORMATION_MESSAGE);
			  if(( x==null || x.length()>0)) {i++;}
			  
			
		}
			try{
				Statement stm=connexion.createStatement();
			
			ResultSet rs=stm.executeQuery("SELECT * FROM joueur WHERE pseudo='"+x+"'");
			
			
			
			if (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String datnais = rs.getString("datnais");
				String sexe = rs.getString("sexe");
				t1.setText(pseudo.toString());
				t2.setText(nom.toString());
				t3.setText(prenom.toString());
				t4.setText(datnais.toString());
				c.setSelectedItem(sexe.toString());
				
				
			}else {
				JOptionPane.showMessageDialog(null,x+"  Pseudo inexistant !");

			}
			
			
			
			rs.beforeFirst();}catch(SQLException ec) {
				JOptionPane.showMessageDialog(null, "Pseudo inexistant","Recherche",JOptionPane.INFORMATION_MESSAGE);
				
			}
			
		//UpdateTable();
		
		}
	
	
	}
	
	public class ActionAnnuler implements ActionListener{
		public synchronized void actionPerformed(ActionEvent e6) {
			
			texte1.setText("");
        	texte2.setText("");
        	texte3.setText(""); 
        	date.setText("");
        	UpdateTable();
			
		}}
	
	public class ActionAjouter implements ActionListener{
		public synchronized void actionPerformed(ActionEvent e7) { 
		 String pseudo = texte1.getText();
		 String nom = texte2.getText();
		 
		 String prenom = texte3.getText();
		 String datenaissance = date.getText();
		 
		 String sexe = (String) c1.getSelectedItem(); 
	
		 
		 
		 if (pseudo.equals("")||nom.equals("")||prenom.equals("")|| datenaissance.equals("")) {
			 JOptionPane.showMessageDialog(null, "un ou plusieurs champs obligatoires sont vides");
			 
		 }
		 /*else :requette sql pour inserer*/
		 else {
			 //java.sql.PreparedStatement st;
			// String querry = "INSERT INTO joueur (pseudo, nom, prenom, datnais, sexe, nbressai)" +" VALUES (?,?,?,?,?,?)";
			 try {
				 
				 Statement r = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 ResultSet upd=r.executeQuery(stm);
				/* st=connexion.prepareStatement(querry);
				 st.setString(1, pseudo);
				 st.setString(2, nom);
				 st.setString(3, prenom);
				 st.setString(4, datenaissance);
				 st.setString(5, sexe);
				 int j = st.executeUpdate();*/
				 
				 upd.moveToInsertRow();
				 upd.updateString(1,pseudo);
				 upd.updateString(2, nom);
				 upd.updateString(3,prenom);
				 upd.updateString(4, datenaissance);
				 upd.updateString(5, sexe);
				 upd.updateInt(6, 8);
				 upd.insertRow();
				 
				 
				 
								 
				
				 if (upd.next()) {
					 JOptionPane.showMessageDialog(null, "joueur ajouté");
					 texte1.setText("");
			        	texte2.setText("");
			        	texte3.setText(""); 
			        	
					 
					 }
				 
				 
				 
			 }catch(SQLException ex) {
				 //Logger.getLogger(VueMaVue.class.getName()).log(Level.SEVERE, null, ex);
				 //System.out.println(ex);
				 JOptionPane.showMessageDialog(null,"Pseudo existe deja");
				 texte1.setText("");
			 }
			 
			 
			
		 }
	
		 onglet2.repaint();       
         onglet2.revalidate();	
         UpdateTable();
			 }
		
		
		 }
	public class ActionPrecedent implements ActionListener {
		public synchronized void actionPerformed(ActionEvent a) {
			UpdateTable();
			try {
					if(resultat.previous()) {
					t1.setText(resultat.getString(1));
					t2.setText(resultat.getString(2));
					t3.setText(resultat.getString(3));
					t4.setText(resultat.getString(4));
					c.setSelectedItem(resultat.getString(5));
					
				 }
				 else {JOptionPane.showMessageDialog(null,"Premier joueur");}
  	  }
				
catch (SQLException e) {
				
	 System.err.println("Avoir une exception ! ");
	 System.err.println(e.getMessage());
			}
			UpdateTable();}
		}
	public class ActionSuivant implements ActionListener {
		public synchronized void actionPerformed(ActionEvent n) {
			UpdateTable();
			try {
	    		
					 if(resultat.next()) {
						t1.setText(resultat.getString(1));
						t2.setText(resultat.getString(2));
						t3.setText(resultat.getString(3));
						t4.setText(resultat.getString(4));
						c.setSelectedItem(resultat.getString(5));
						
					 }
					 else {JOptionPane.showMessageDialog(null,"C'est le dernier joueur !!");}
	    	  }
					
	catch (SQLException e) {
					
		 System.err.println("Avoir une exception! ");
		 System.err.println(e.getMessage());
				}
			UpdateTable();
			}
		}
		 
		 
	public class ActionModifier implements ActionListener {
		public synchronized void actionPerformed(ActionEvent a) {	
			if (t2.getText().equals("") || t3.getText().equals("")) {JOptionPane.showMessageDialog(null, "Un des champs est vide","échec",JOptionPane.INFORMATION_MESSAGE);}
			else {
			try
			  {
			    
			    java.sql.PreparedStatement ps = connexion.prepareStatement(
"UPDATE joueur SET nom = ? , prenom = ? , datnais = ?, sexe = ? WHERE pseudo = '"+ t1.getText() + "'");
		    JOptionPane.showMessageDialog(null, "Etes-vous sur de modifier le joueur ? " , "",JOptionPane.INFORMATION_MESSAGE);

				
			    ps.setString(1,t2.getText());
			    ps.setString(2,t3.getText());
			    ps.setString(3,t4.getText());
			    ps.setString(4, c.getSelectedItem().toString());
			    
			 
			    ps.executeUpdate();
			    JOptionPane.showMessageDialog(null, "Modification effectuée",
			    		
                      "Opération validée",JOptionPane.INFORMATION_MESSAGE);

			    ps.close();
				
						   resultat.updateString(2, t2.getText());
						   resultat.updateString(3, t3.getText());
						   resultat.updateString(4, t4.getText());
						   resultat.updateString(5, c.getSelectedItem().toString());
						   System.out.println("Modification effectuée");
						   resultat.updateRow();
						   UpdateTable();
						
			  }
			  catch (SQLException e)
			  {
			   
				  System.err.println("Avoir une exception ! ");
					 System.err.println(e.getMessage());
					 }
			}
			UpdateTable();}
		}
	
			 
			 public class ActionSupprimer implements ActionListener {
				 public synchronized void actionPerformed(ActionEvent a ) {
					 int j = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce joueur ? ", null, JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                   	  
					 try {
						if(j==0) {
						resultat.deleteRow();
						UpdateTable();}
					} catch (Exception e) {
						
					}
			UpdateTable();		 
			 }}
			 public void UpdateTable() {
					
					
					String requete ="Select * from joueur ORDER BY nbressai ASC" ;
					try {
						Statement prepared = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs = prepared.executeQuery(requete);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						

						
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				 
			
				
			 
		 
			
		}
	
	

			
	

	
	

