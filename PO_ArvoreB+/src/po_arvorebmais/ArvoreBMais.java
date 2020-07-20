package po_arvorebmais;

/**
 *
 * @author Murilo Barros
 */
public class ArvoreBMais implements Tamanho {
     private No raiz;

    public ArvoreBMais() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void In_ordem(No raiz) {
        int i;
        if (raiz != null) {
            for (i = 0; i < raiz.getTl(); i++) {
                In_ordem(raiz.getVLig(i));
                System.out.println(raiz.getVInfo(i));
            }
            In_ordem(raiz.getVLig(i));
        }
    }

    public void insere(int info) {
        int pos, paipos;
        No Folha, Pai, IrmaE = null, IrmaD = null;
        if (raiz == null) {
            raiz = new No(info);
        } else {
            Folha = localizarFolha(info);
            pos = Folha.procurarPosicao(info);
            Folha.remanejarIns(pos);
            Folha.setVInfo(pos, info);
            Folha.setTl(Folha.getTl() + 1);
            
            if (Folha.getTl() == N) {
                Pai = localizarPai(Folha, info);
                paipos = Pai.procurarPosicao(info);
                if (paipos > 0) {
                    IrmaE = Pai.getVLig(paipos - 1);
                }
                if (paipos < Pai.getTl()) {
                    IrmaD = Pai.getVLig(paipos + 1);
                }
                split(Folha, Pai, IrmaE, IrmaD);
            }
        }
    }


    public void split(No Folha, No Pai, No IrmaE, No IrmaD) {
        int distri=0, pos=0;
        No cx1, cx2;
        cx1 = new No(); cx2 = new No();
       

        if (Folha.getVLig(0) == null) {
            distri = Math.round((float) (N - 1) / 2);
            for (int i = 0; i < distri; i++) 
            {
                cx1.setVInfo(i, Folha.getVInfo(i));
                cx1.setVLig(i, Folha.getVLig(i));
            }
            cx1.setVLig(distri, Folha.getVLig(distri));
            cx1.setTl(distri);
            for (int i = distri; i < Folha.getTl(); i++) 
            {
                cx2.setVInfo(i - distri, Folha.getVInfo(i));
                cx2.setVLig(i - distri, Folha.getVLig(i));
            }
            cx2.setVLig(Folha.getTl() - distri, Folha.getVLig(Folha.getTl()));
            cx2.setTl(Folha.getTl() - distri);
            cx1.setProx(cx2);
            cx2.setAnt(cx1);
            if (Folha == Pai)
            {
                Folha.setVInfo(0, cx2.getVInfo(0));
                Folha.setTl(1);
                Folha.setVLig(0, cx1);
                Folha.setVLig(1, cx2);
            } 
            else 
            {
                pos = Pai.procurarPosicao(cx2.getVInfo(0));
                Pai.remanejarIns(pos);
                Pai.setVInfo(pos, cx2.getVInfo(0));
                Pai.setTl(Pai.getTl() + 1);
                Pai.setVLig(pos, cx1);
                Pai.setVLig(pos + 1, cx2);
                if (IrmaE != null) 
                {
                    IrmaE.setProx(cx1);
                    cx1.setAnt(IrmaE);
                }
                if (IrmaD != null) 
                {
                    IrmaD.setAnt(cx2);
                    cx2.setProx(IrmaD);
                }
            }
        } 
        else 
        { 
            distri = (int) (N / 2) - 1;
            for (int i = 0; i < distri; i++) 
            {
                cx1.setVInfo(i, Folha.getVInfo(i));
                cx1.setVLig(i, Folha.getVLig(i));
            }
            cx1.setVLig(distri, Folha.getVLig(distri));
            cx1.setTl(distri);
            for (int i = distri; i < Folha.getTl() - 1; i++)
            {
                cx2.setVInfo(i - distri, Folha.getVInfo(i + 1));
                cx2.setVLig(i - distri, Folha.getVLig(i + 1));
            }
            cx2.setVLig(Folha.getTl() - (distri + 1), Folha.getVLig(Folha.getTl()));
            cx2.setTl(Folha.getTl() - (distri + 1));

            Folha.setVInfo(0, Folha.getVInfo(distri));
            Folha.setTl(1);
            Folha.setVLig(0, cx1);
            Folha.setVLig(1, cx2);
        }
        if (Pai.getTl() == N) 
        {
            Folha = Pai;
            Pai = localizarPai(Folha, Folha.getVInfo(0));
            pos = Pai.procurarPosicao(Folha.getVInfo(N - 1));
            if (pos > 0) 
                IrmaE = Pai.getVLig(pos - 1);
            
            if (pos < Pai.getTl()) 
                IrmaD = Pai.getVLig(pos + 1);
            
            split(Folha, Pai, IrmaE, IrmaD);
        }
    }

    private No localizarPai(No folha, int info) {
        No aux = raiz;
        No pai = aux;

        while (aux != folha) {
            int i = 0;
            while (i < aux.getTl() && info > aux.getVInfo(i)) {
                i++;
            }
            pai = aux;
            aux = aux.getVLig(i);
        }
        return pai;
    }

    private No localizarFolha(int info) {
        No no = raiz;

        while (no.getVLig(0) != null) {
            int i = 0;
            while (i < no.getTl() && info > no.getVInfo(i)) {
                i++;
            }
            no = no.getVLig(i);
        }
        return no;
    }
    
    
}