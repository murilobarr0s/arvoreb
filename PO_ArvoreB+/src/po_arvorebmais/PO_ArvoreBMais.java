package po_arvorebmais;

/**
 *
 * @author Murilo Barros
 */
public class PO_ArvoreBMais {

    public static void main(String[] args) {
           ArvoreBMais aBmais = new ArvoreBMais();

        /*for (int i = 1; i <= 100; i++) {
          aBmais.insere(i);
        }*/
       
        // os valores do exemplo do pdf do material no aprender.... tamanho igual a 4
        aBmais.insere(1);
        aBmais.insere(4);
        aBmais.insere(7);
        aBmais.insere(10);
        aBmais.insere(17);
        aBmais.insere(21);
        aBmais.insere(31);
        aBmais.insere(25);
        aBmais.insere(19);
        aBmais.insere(20);
        aBmais.insere(28);
        aBmais.insere(42);
               
        aBmais.In_ordem(aBmais.getRaiz()); // ok 

    }
    
    
}
