package devinette.vues;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public  class MaConnexion{

static String url="jdbc:mysql://localhost:3307/db_jeu?autoReconnect=true&useSSL=true";
private static String utilisateur="root";
private static String motDePasse="";
private static Connection connexion=null;
private MaConnexion() {
	try {
		Class.forName("com.mysql.jdbc.Driver" );
		connexion = DriverManager.getConnection(url,utilisateur,motDePasse);
		
	}catch(Exception e){
		e.printStackTrace();	
	}}
	public static Connection getConnection() {
		if (connexion==null) {
			new MaConnexion();
		}
		return connexion;
	}
	public static void stop() {
		if(connexion!=null) {
			try { 
				connexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



