package estoquesystem;

import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

/**
 *
 * @author borges
 */
public class DBOperations {

    OpenConnection op = new OpenConnection();

    private static Map<String, Integer> map = new HashMap();
    private static final Map<String, Integer> mapFornecedor = new HashMap();
    private static final Map<String, Integer> mapTipo = new HashMap();
    public static List<Produto> produtoList = new ArrayList<Produto>();

    public List populateComboBoxMarca() {
        List list = new ArrayList();
        JComboBox v = new JComboBox();

        int i = 0;
        try {
            ResultSet rs = null;
            String sql = "SELECT * FROM marca";
            Connection con = op.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                map.put(rs.getString("nome_marca"), rs.getInt("id_marca"));
                list.add(rs.getString("nome_marca"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro" + e.getErrorCode(), "Erro", HEIGHT);
        }
        return list;
    }

    public Map<String, Integer> getMapMarca() {
        return map;
    }

    public List populateComboBoxForne() {
        List list = new ArrayList();
        try {
            ResultSet rs = null;
            String sql = "SELECT * FROM fornecedor";
            Connection con = op.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                mapFornecedor.put(rs.getString("nome_fornecedor"), rs.getInt("id_fornecedor"));
                list.add(rs.getString("nome_fornecedor"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Map<String, Integer> getMapFornecedor() {
        return mapFornecedor;

    }

    public List populateComboBoxTipo() {
        List list = new ArrayList();
        try {
           
            ResultSet rs = null;
            String sql = "SELECT * FROM tipo_produto";
            Connection con = op.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                mapTipo.put(rs.getString("tipo"), rs.getInt("id_tipo"));
                list.add(rs.getString("tipo"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Map<String, Integer> getTipoMap() {
        return mapTipo;
    }
    
  public List<Produto> populateTableEstoque(String filtro){
  
      try {
          String sql=null;
           ResultSet rs = null;  
           Connection con = op.getConnection();
           if(filtro.equals("produto")){
           filtro="Mouse";
           }
           sql = "select * from produto inner join tipo_produto on produto.fk_id_tipo=tipo_produto.id_tipo inner join fornecedor on produto.fk_id_fornecedor=fornecedor.id_fornecedor inner join marca on produto.fk_id_marca=marca.id_marca where tipo= ? order by nome_produ ;";
           PreparedStatement stm = con.prepareStatement(sql);  
           stm.setString(1,filtro);
           rs = stm.executeQuery();
             produtoList.clear();
            while (rs.next()) {
                produtoList.add(new Produto(rs.getInt("id_produto"),rs.getString("nome_produ"),rs.getString("tipo"),rs.getString("nome_marca"),rs.getString("nome_fornecedor"),String.valueOf(rs.getDouble("preco")),String.valueOf(rs.getInt("quant")),rs.getString("desc_produto") ) );
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
  return produtoList;
  }
    

    public boolean newProduto(String nome, int id_tipo, int id_marca, int id_forne, double valor, int quant, String desc) {
        try {
           // produtoList.add(new Produto(nome, id_tipo, id_marca, id_forne, valor, quant, desc));

            String sql = "insert into produto (nome_produ,fk_id_tipo,fk_id_marca,fk_id_fornecedor,preco,quant,desc_produto) values (?,?,?,?,?,?,?);";
            Connection con = op.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, nome);
            stm.setInt(2, id_tipo);
            stm.setInt(3, id_marca);
            stm.setInt(4, id_forne);
            stm.setDouble(5, valor);
            stm.setInt(6, quant);
            stm.setString(7, desc);
            stm.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public void deleteProduto(int id){
     try {
           // produtoList.add(new Produto(nome, id_tipo, id_marca, id_forne, valor, quant, desc));

            String sql = "delete from produto where id_produto= ?;";
            Connection con = op.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
           
            stm.execute();
            

        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    
    }
    
    public List<Produto> procurar(String filtro,String pesq){
    List<Produto> list=new ArrayList();
     try{
        Connection con=op.getConnection();
        ResultSet rs=null;
        String sql=null;
        PreparedStatement stm=null;
        
        if(filtro.equals("nome")){
        sql="select * from produto inner join tipo_produto on produto.fk_id_tipo=tipo_produto.id_tipo inner join fornecedor on produto.fk_id_fornecedor=fornecedor.id_fornecedor inner join marca on produto.fk_id_marca=marca.id_marca where nome_produ like ?";
        }
        else if(filtro.equals("fornecedor")){
         sql="select * from produto inner join tipo_produto on produto.fk_id_tipo=tipo_produto.id_tipo inner join fornecedor on produto.fk_id_fornecedor=fornecedor.id_fornecedor inner join marca on produto.fk_id_marca=marca.id_marca where nome_fornecedor like ?";
        }
        else if(filtro.equals("marca")){
        sql="select * from produto inner join tipo_produto on produto.fk_id_tipo=tipo_produto.id_tipo inner join fornecedor on produto.fk_id_fornecedor=fornecedor.id_fornecedor inner join marca on produto.fk_id_marca=marca.id_marca where nome_marca like ?";
        }
        stm=con.prepareStatement(sql);
        stm.setString(1, pesq+"%");
       rs=stm.executeQuery();
       produtoList.clear();
       while(rs.next()){
          
       produtoList.add(new Produto(rs.getString("nome_produ"),rs.getString("tipo"),rs.getString("nome_marca"),rs.getString("nome_fornecedor"),String.valueOf(rs.getDouble("preco")),String.valueOf(rs.getInt("quant")),rs.getString("desc_produto") ) );
       
       }
        
    }catch(SQLException e){e.printStackTrace();}
    
     return produtoList;
    }
    
        

}
