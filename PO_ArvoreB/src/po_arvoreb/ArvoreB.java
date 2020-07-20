package po_arvoreb;

/**
 *
 * @author Murilo Barros
 */
public class ArvoreB implements Tamanho{
    private No raiz;

    public ArvoreB() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void inserir(int info, int posArq) {
        No folha, pai;
        int pos;
        if (raiz == null) {
            raiz = new No(info, posArq);
        } else {
            folha = localizarFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejarIns(pos);
            folha.setTl(folha.getTl() + 1);
            folha.setVetInfo(pos, info);
            folha.setVetPos(pos, posArq);
            if (folha.getTl() > 2 * M) {
                pai = localizarPai(folha, info);
                split(folha, pai);
            }
        }
    }

    public No localizarPai(No no, int info) {
        No aux = raiz;
        No pai = aux;
        int i;

        while (no != aux) {
            i = 0;
            while (i < aux.getTl() && info > aux.getVetInfo(i)) {
                i++;
            }
            pai = aux;
            aux = aux.getVetLig(i);
        }

        return pai;
    }

    public No buscarInfo(int info) {
        No no = raiz;
        int i;
        boolean achou = false;
        while (no != null && !achou) {
            i = 0;
            while (i < no.getTl() && info > no.getVetInfo(i)) {
                i++;
            }

            if (i < no.getTl() && info == no.getVetInfo(i)) {
                achou = true;
            } else {
                no = no.getVetLig(i);
            }
        }
        return no;
    }

    public No buscarSubE(No no) {
        while (no.getVetLig(0) != null) {
            no = no.getVetLig(no.getTl());
        }
        return no;
    }

    public No buscarSubD(No no) {
        while (no.getVetLig(0) != null) {
            no = no.getVetLig(0);
        }
        return no;
    }

    public void fusao(No folha, No pai, No irmaE, No irmaD, int posP) {
        if (irmaE != null) {
            if (irmaE.getTl() > M) { 
               
                folha.remanejarIns(0);
                folha.setVetInfo(0, pai.getVetInfo(posP - 1));
                folha.setVetPos(0, pai.getVetPos(posP - 1));
                folha.setTl(folha.getTl() + 1);

              
                pai.setVetInfo(posP - 1, irmaE.getVetInfo(irmaE.getTl() - 1));
                pai.setVetPos(posP - 1, irmaE.getVetPos(irmaE.getTl() - 1));

              
                folha.setVetLig(0, irmaE.getVetLig(irmaE.getTl()));
                irmaE.setTl(irmaE.getTl() - 1);

            } else {
                irmaE.setVetInfo(irmaE.getTl(), pai.getVetInfo(posP - 1));
                irmaE.setVetPos(irmaE.getTl(), pai.getVetPos(posP - 1));
                irmaE.setTl(irmaE.getTl() + 1);
                pai.remanejarEx(posP - 1);
                pai.setTl(pai.getTl() - 1);
                irmaE.setVetInfo(irmaE.getTl(), folha.getVetInfo(0));
                irmaE.setVetPos(irmaE.getTl(), folha.getVetPos(0));
                irmaE.setTl(irmaE.getTl() + 1);
                irmaE.setVetLig(3, folha.getVetLig(0));
                irmaE.setVetLig(4, folha.getVetLig(1));
                pai.setVetLig(posP - 1, irmaE);
            }
        } else if (irmaD != null) {
            if (irmaD.getTl() > M) { 
                
                folha.remanejarIns(0);
                folha.setVetInfo(0, pai.getVetInfo(posP + 1));
                folha.setVetPos(0, pai.getVetPos(posP + 1));
                folha.setTl(folha.getTl() + 1);

               
                pai.setVetInfo(posP + 1, irmaD.getVetInfo(0));
                pai.setVetPos(posP + 1, irmaD.getVetPos(0));

               
                folha.setVetLig(folha.getTl() - 1, irmaD.getVetLig(0));
                irmaD.setTl(irmaD.getTl() - 1);
                
            } else {
                folha.setVetInfo(folha.getTl(), pai.getVetInfo(posP));
                folha.setVetPos(folha.getTl(), pai.getVetPos(posP));
                folha.setTl(folha.getTl() + 1);
                for (int i = 0; i < irmaD.getTl(); i++) {
                    folha.setVetInfo(folha.getTl(), irmaD.getVetInfo(i));
                    folha.setVetPos(folha.getTl(), irmaD.getVetPos(i));
                    folha.setVetLig(folha.getTl(), irmaD.getVetLig(i));
                    folha.setTl(folha.getTl() + 1);
                }
                folha.setVetLig(folha.getTl(), irmaD.getVetLig(irmaD.getTl()));
                pai.remanejarEx(posP);
                pai.setTl(pai.getTl() - 1);
                pai.setVetLig(posP, folha);
            }
        }

        if (pai.getTl() == 0) {
            raiz = folha;
        } else if (pai.getTl() < M && pai != raiz) {
            folha = pai;
            pai = localizarPai(folha, folha.getVetInfo(0));
            posP = pai.procurarPosicao(folha.getVetInfo(0));
            irmaE = irmaD = null;
            if (posP > 0) {
                irmaE = pai.getVetLig(posP - 1);
            }
            if (posP < pai.getTl()) {
                irmaD = pai.getVetLig(posP + 1);
            }
            fusao(folha, pai, irmaE, irmaD, posP);
        }
    }

    public void exclusao(int info) {
        No no, subE, subD, folha, irmaE = null, irmaD = null, pai;
        int pos, posP;

        no = buscarInfo(info);
        if (no != null) {
            pos = no.procurarPosicao(info);
            if (no.getVetLig(0) != null) {
                subE = buscarSubE(no.getVetLig(pos));
                subD = buscarSubD(no.getVetLig(pos + 1));
                if (subE.getTl() > M || subD.getTl() == M) {
                    no.setVetInfo(pos, subE.getVetInfo(subE.getTl() - 1));
                    no.setVetPos(pos, subE.getVetPos(subE.getTl() - 1));
                    subE.setTl(subE.getTl() - 1);
                    folha = subE;
                } else {
                    no.setVetInfo(pos, subD.getVetInfo(0));
                    no.setVetPos(pos, subD.getVetPos(0));
                    pos = 0;
                    folha = subD;
                }

            } else {
                folha = no;
                folha.remanejarEx(pos);
                folha.setTl(folha.getTl() - 1);

                if (folha != raiz && folha.getTl() < M) {
                    pai = localizarPai(folha, info);
                    posP = pai.procurarPosicao(info);
                    if (posP > 0) {
                        irmaE = pai.getVetLig(posP - 1);
                    }
                    if (posP < pai.getTl()) {
                        irmaD = pai.getVetLig(posP + 1);
                    }
                    if (irmaE != null && irmaE.getTl() > M) {
                        folha.remanejarIns(0);
                        folha.setVetInfo(0, pai.getVetInfo(posP - 1));
                        folha.setVetPos(0, pai.getVetPos(posP - 1));
                        folha.setTl(folha.getTl() + 1);
                        pai.setVetInfo(posP - 1, irmaE.getVetInfo(irmaE.getTl() - 1));
                        pai.setVetPos(posP - 1, irmaE.getVetPos(irmaE.getTl() - 1));
                        irmaE.setTl(irmaE.getTl() - 1);
                    } else if (irmaD != null && irmaD.getTl() > M) {
                        folha.setVetInfo(folha.getTl(), pai.getVetInfo(posP));
                        folha.setVetPos(folha.getTl(), pai.getVetPos(posP));
                        folha.setTl(folha.getTl() + 1);
                        pai.setVetInfo(posP, irmaD.getVetInfo(0));
                        pai.setVetPos(posP, irmaD.getVetPos(0));
                        irmaD.remanejarEx(0);
                        irmaD.setTl(irmaD.getTl() - 1);
                    } else {
                        fusao(folha, pai, irmaE, irmaD, posP);
                    }

                }

            }
        }
    }

    public void split(No folha, No pai) {
        No cx1, cx2;
        int pos;
        cx1 = new No();
        cx2 = new No();

        for (int i = 0; i < M; i++) {
            cx1.setVetInfo(i, folha.getVetInfo(i));
            cx1.setVetPos(i, folha.getVetPos(i));
            cx1.setVetLig(i, folha.getVetLig(i));
        }
        cx1.setVetLig(M, folha.getVetLig(M));
        cx1.setTl(M);
        for (int i = M + 1; i < 2 * M + 1; i++) {
            cx2.setVetInfo(i - (M + 1), folha.getVetInfo(i));
            cx2.setVetPos(i - (M + 1), folha.getVetPos(i));
            cx2.setVetLig(i - (M + 1), folha.getVetLig(i));
        }
        cx2.setVetLig(M, folha.getVetLig(2 * M + 1));
        cx2.setTl(M);
        if (pai == folha) {
            folha.setVetInfo(0, folha.getVetInfo(M));
            folha.setVetPos(0, folha.getVetPos(M));
            folha.setVetLig(0, cx1);
            folha.setVetLig(1, cx2);
            folha.setTl(1);
        } else {
            pos = pai.procurarPosicao(folha.getVetInfo(M));
            pai.remanejarIns(pos);
            pai.setTl(pai.getTl() + 1);
            pai.setVetInfo(pos, folha.getVetInfo(M));
            pai.setVetPos(pos, folha.getVetPos(M));
            pai.setVetLig(pos, cx1);
            pai.setVetLig(pos + 1, cx2);
            if (pai.getTl() > 2 * M) {
                folha = pai;
                pai = localizarPai(pai, pai.getVetInfo(pos));
                split(folha, pai);
            }
        }
    }

    public No localizarFolha(int info) {
        No p = raiz;
        int i;
        while (p.getVetLig(0) != null) {
            i = 0;
            while (i < p.getTl() && info > p.getVetInfo(i)) {
                i++;
            }
            p = p.getVetLig(i);
        }
        return p;
    }

    public void In_ordem(No no) {
        if (no != null) {
            for (int i = 0; i < no.getTl(); i++) {
                In_ordem(no.getVetLig(i));
                System.out.println(no.getVetInfo(i));
            }
            In_ordem(no.getVetLig(no.getTl()));
        }
    }
}
