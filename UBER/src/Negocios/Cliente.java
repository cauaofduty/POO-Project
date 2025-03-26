package Negocios;
public class Cliente extends Pessoa{
    private String endereco;//ISTO SERA UMA CLASSE! DEPOIS RESOLVER
    
    public Cliente(String nome, String telefone, int IDPessoa){
        super(nome, telefone, IDPessoa);
    }
    public void setEndereco(String endereco){//como o endereco e pra ser posto quando o cliente quiser solicitar alguma entrega
        this.endereco = endereco;
    }
    public String getEndereco(){
        return this.endereco;
    }


}


