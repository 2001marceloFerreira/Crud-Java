package sistemaVendas;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.sql.ResultSet;
	import java.util.Scanner;

	public class SistemaVendas {
		private Connection con;
		private Statement stmt;
	public SistemaVendas(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("encontramos seu driver!");
			}
		catch (ClassNotFoundException e){
			System.out.println("Nao encontramos seu driver!!" + e);
			System.out.println("Error: "+ e.getMessage());
		}
		
		String url = "jdbc:mysql://localhost:3306/sistemaVendas";
		String user = "root";
		String password = "marcelo";
		
		try{
		con=DriverManager.getConnection(url,user,password);
		stmt = con.createStatement();
		}
		catch(SQLException e){
		System.out.println("Error: "+ e.getMessage());
		}
		
	}	
	
	private void inserir(){
		try{
		stmt.executeUpdate
		("INSERT INTO vendas VALUES (6,'roupa','g','nike')");
		}
		catch(SQLException e){ 
			System.out.println("Error: "+ e.getMessage());
		}
		}
	
	/*private void inserir(int i, String tip, String tam, String mar){
		try{
		stmt.executeUpdate("INSERT INTO vendas VALUES ("+i+",'"+tip+"','"+tam+"','"+mar+"')");
		}catch(SQLException e){
		System.out.println("Erro: "+ e.getMessage());
		}
		}*/
	
	private void listar(){
		try{
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM vendas");
		while ( rs.next() ) 
		{ int id = rs.getInt("id");
		String tipo = rs.getString("tipo");
		String tamanho = rs.getString("tamanho");
		String marca = rs.getString("marca");
		
		System.out.println(id + "\t" + tipo + "\t" +
		tamanho + "\t" + marca);
		}
		}catch(SQLException e){
		System.out.println("Erro: "+ e.getMessage());
		}
		}
	
	private void alterar(String tam, int i){
		try{
		stmt.executeUpdate("UPDATE vendas SET tamanho = '"+tam+"' WHERE id="+i+"");
		}catch(SQLException e){
		System.out.println("Erro: "+ e.getMessage());
		}
		}
	
	private void apagar(int i){
		try{
		stmt.executeUpdate("DELETE FROM vendas WHERE id="+i+"");
		}catch(SQLException e){
		System.out.println("Erro: "+ e.getMessage());
		}
		}
	
	public static void main(String[] args) {
		SistemaVendas sistemaVendas = new SistemaVendas();
		Scanner leia = new Scanner(System.in);
		int id, alterarID, opcao, excluir;
		String tipo, tamanho, marca, alterarTam;

		System.out.println(" 1-inserir\n "
						  + "2-alterar\n "
						  + "3-consultar\n "
						  + "4-excluir");

		System.out.println("digite sua opção: ");
		 opcao = leia.nextInt();

		switch(opcao){
		case 1:
		sistemaVendas.inserir();
		break;
		case 2:
			System.out.println("digite o id para alterar o tamanho: ");
			alterarID = leia.nextInt();
			System.out.println("digite o novo tamanho: ");
			alterarTam = leia.next();
			sistemaVendas.alterar(alterarTam, alterarID);
		break;	
		case 3:
			sistemaVendas.listar();
		break;
		case 4:
			System.out.println("digite o id que deseja excluir: ");
			excluir = leia.nextInt();
			sistemaVendas.apagar(excluir);
		break;	
		}
		
	}

}
