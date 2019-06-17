package estoquesystem;

/**
 *
 * @author borge
 */
public class Produto {
    private int id_produto;
    private String nome_produto;
    private String marca_produto;
    private String fornece_produto;
    private String quant_produto;
    private String preco;
    private String tipo;
    private String desc;

    public Produto(int id_produto,String nome, String id_tipo, String id_marca, String id_forne, String valor, String quant,String desc) {
        nome_produto = nome;
        marca_produto = id_marca;
        fornece_produto = id_forne;
        quant_produto = quant;
        tipo = id_tipo;
        this.id_produto=id_produto;
    }
    //SEM O ID
  public Produto(String nome, String id_tipo, String id_marca, String id_forne, String valor, String quant,String desc) {
        nome_produto = nome;
        marca_produto = id_marca;
        fornece_produto = id_forne;
        quant_produto = quant;
        tipo = id_tipo;
        
    }
    public Produto() {

    }

    public int getId(){
    return id_produto;
    }
    
    public String getNome() {
        return nome_produto;
    }

    public void setNome(String nome) {
        nome_produto = nome;

    }

    public String getMarca() {
        return marca_produto;
    }

    public void setMarca(String marca) {
        marca_produto = marca;

    }

    public String getFornecedor() {
        return fornece_produto;
    }

    public void setFornecedor(String forne) {
        fornece_produto = forne;

    }

    public String getQuantidade() {
        return quant_produto;
    }

    public void setQuantidade(String q) {
        quant_produto = q;

    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String q) {
        preco = q;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String q) {
        tipo = q;

    }

}
