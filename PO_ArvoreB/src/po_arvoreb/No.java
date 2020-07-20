package po_arvoreb;

/**
 *
 * @author Murilo Barros
 */
public class No implements Tamanho {
    private int vetInfo[];
    private No vetLig[];
    private int vetPos[];
    private int tl;

    public No() {
        vetInfo = new int[M*2+1];
        vetLig = new No[M*2+2];
        vetPos = new int[M*2+1];
    }

    public No(int info, int posArq) {
        this();
        vetInfo[0] = info;
        vetPos[0] = posArq;
        tl = 1;
    }

    public int getVetInfo(int pos) {
        return vetInfo[pos];
    }

    public void setVetInfo(int pos, int info) {
        this.vetInfo[pos] = info;
    }

    public No getVetLig(int pos) {
        return vetLig[pos];
    }

    public void setVetLig(int pos, No lig) {
        this.vetLig[pos] = lig;
    }

    public int getVetPos(int pos) {
        return vetPos[pos];
    }

    public void setVetPos(int pos, int posArq) {
        this.vetPos[pos] = posArq;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }
    
    public int procurarPosicao(int info)
    {
        int i=0;
        while(i<tl && info > vetInfo[i])
            i++;
        
        return i;
    }
    public void remanejarIns(int pos) {
        vetLig[tl + 1] = vetLig[tl];
        for (int i = tl; i > pos; i--) {
            vetInfo[i] = vetInfo[i - 1];
            vetPos[i] = vetPos[i - 1];
            vetLig[i] = vetLig[i - 1];
        }
    }

    public void remanejarEx(int pos) {
        for (int i = pos; i < tl - 1; i++) {
            vetInfo[i] = vetInfo[i + 1];
            vetPos[i] = vetPos[i + 1];
            vetLig[i] = vetLig[i + 1];
        }
        vetLig[tl - 1] = vetLig[tl];
    }
}
