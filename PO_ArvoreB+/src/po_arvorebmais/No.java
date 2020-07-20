package po_arvorebmais;

/**
 *
 * @author Murilo Barros
 */
public class No implements Tamanho{
    private int vInfo[] = new int[N];
    private No vLig[] = new No[N + 1];
    private int tl;
    private No ant;
    private No prox;

    public No() {
        this.vInfo = new int[(int) N];
        this.vLig = new No[(int) N + 1];
        this.ant = prox = null;
        this.tl = 0;
    }

    public No(int info) {
        this.vInfo[0] = info;
        this.tl = 1;
    }

    public int getVInfo(int pos) {
        return vInfo[pos];
    }

    public void setVInfo(int pos, int info) {
        this.vInfo[pos] = info;
    }

    public No getVLig(int pos) {
        return vLig[pos];
    }

    public void setVLig(int pos, No no) {
        this.vLig[pos] = no;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public void remanejarIns(int pos) {
        this.vLig[tl + 1] = this.vLig[tl];
        for (int i = tl; i > pos; i--) {
            vInfo[i] = vInfo[i - 1];
            vLig[i] = vLig[i - 1];
        }
    }

    public int procurarPosicao(int info) {
        int pos = 0;
        while (pos < tl && info > vInfo[pos]) {
            pos++;
        }
        return pos;
    }
}
