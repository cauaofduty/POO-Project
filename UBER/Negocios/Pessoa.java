package Negocios;
public abstract class Pessoa {
    protected String nome;
    protected String telefone;
    protected int IDPessoa;

        public Pessoa(String nome,String telefone,int IDPessoa){//atributos default para pessoa
            this.nome = nome;
            this.telefone = telefone;
            this.IDPessoa = IDPessoa;
        }
        //getters default abaixo
        public String getNome(){
            return this.nome;
        }

        public String getTelefone(){
            return this.telefone;
        }
        
        public int getIDPessoa(){
            return this.IDPessoa;
        }
    /* Classe	O que usar?	Justificativa
Pessoa	Classe Abstrata	Cliente e Motorista compartilham atributos, mas têm lógica diferente.
FormaDePagamento	Interface	Cada tipo de pagamento tem implementação própria.
Local	Classe Normal	Apenas representa um endereço, sem diferenças na lógica.
Viagem	Classe Abstrata	Viagens compartilham estrutura, mas cálculos podem ser diferentes.
Veiculo	Classe Abstrata	Compartilham atributos, mas têm lógica de tarifa diferente. */
}
