
package estoquesystem;

import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author borge
 *
 * ESTA CLASSE E RESPONSAVEL POR ABRIR A CONEXAO COM O BANCO DE DADOS
 * OBS:E NECESSARIO ADICIONAR A BIBLIOTECA mysql-connector-java-5.1.47-bin AO SEU PROJETO
 */

public class OpenConnection {
    private Connection con;
    private String user="root";
    private String pass="toor123";
    private String url="jdbc:mysql://localhost:8088/estoque_controle?useSSL=false";

    public OpenConnection(){
    try{
    con=DriverManager.getConnection(url,user,pass);
    }catch(Exception e){}

    }

    public Connection getConnection(){

    return con;
    }


}
