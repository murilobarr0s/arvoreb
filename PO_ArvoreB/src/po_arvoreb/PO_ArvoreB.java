package po_arvoreb;

/**
 *
 * @author Murilo Barros
 */
public class PO_ArvoreB {

    public static void main(String[] args) {
       ArvoreB aB = new ArvoreB();
        
        for (int i = 1; i <= 1000; i++) {
            aB.inserir(i, 0);
        }
        
        aB.In_ordem(aB.getRaiz()); // exibindo...
        //
        System.out.println(" ------- APÓS EXCLUSÃO DO 250 E 500 -------");
        //excluindo os dois elementos p/ teste
        
        aB.exclusao(250);
        aB.exclusao(500);
        
        //exibindo novamente...
        aB.In_ordem(aB.getRaiz()); 
        
    }
    
}
