/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepapersampleimpl;

/**
 *
 * @author Usuario
 */
public class TArbolBinarioOptimo extends TArbolBB {

    private int[][] W;
    private int[][] P;
    private int[][] R;

    private void crearMatrices(int cantElem) {
        W = new int[cantElem + 1][cantElem + 1];
        P = new int[cantElem + 1][cantElem + 1];
        R = new int[cantElem + 1][cantElem + 1];

    }

    public TArbolBinarioOptimo(int cantElem) {
        super();
        crearMatrices(cantElem);
    }

    public void encontrarOptimo(int cantElem, int[] frecExito, int[] frecNoExito) {
        int i, j, k, kraiz, h;
        int min, pesosSubArboles;
        //Paso 1 h=0 , wii=bii=pii
        for (i = 0; i <= cantElem; i++) {
            W[i][i] = frecNoExito[i];
            P[i][i] = frecNoExito[i];
        }
        //Paso 2 wij = wi(j-1) +aj+bj
        for (j = 1; j <= cantElem; j++) {
            for (i = 0; i <= j - 1; i++) {
                W[i][j] = getW()[i][j - 1] + frecExito[j] + frecNoExito[j];
            }
        }
        //Paso 3 h=1 pij=Wij+pii+pjj
        for (i = 0; i < cantElem; i++) {
            j = i + 1;
            P[i][j] = getW()[i][j] + getP()[i][i] + getP()[j][j];
            R[i][j] = j;
        }
        kraiz = 0;
        for (h = 2; h < cantElem + 1; h++) {
            for (i = 0; i < cantElem - h + 1; i++) {
                j = i + h;
                min = Integer.MAX_VALUE;
                for (k = i + 1; k <= j; k++) {
                    int resultado = P[i][k - 1] + P[k][j];
                    if (min > resultado) {
                        kraiz = k;
                        min = resultado;
                    }
                }
                P[i][j] = min + W[i][j];
                R[i][j] = kraiz;
            }
        }

    }

    public void armarÁrbol(TElementoArbolBB[] claves, int[][] R, int i, int j) {
        int unaRaiz;
        if (i < j) {
            unaRaiz = R[i][j];
            super.insertar(claves[unaRaiz]);
            armarÁrbol(claves, R, i, unaRaiz - 1);
            armarÁrbol(claves, R, unaRaiz, j);
        }
    }

    /**
     * @return the W
     */
    public int[][] getW() {
        return W;
    }

    /**
     * @return the P
     */
    public int[][] getP() {
        return P;
    }

    /**
     * @return the R
     */
    public int[][] getR() {
        return R;
    }

}
