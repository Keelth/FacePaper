/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepapersampleimpl;

/**
 *
 * @author Marcelo Mandirola, Marcelo Siccardi
 */
public class ArbolAVL extends TArbolBB {

    public ArbolAVL() {
    }

    public void insertar(NodoAVL nodo) {
        NodoAVL raiz = (NodoAVL) super.getRaiz();
        if (raiz != null) {
            super.setRaiz(raiz.insertar(nodo));
        } else {
            super.setRaiz(nodo);
        }
    }

    public void eliminar(Comparable etiqueta) {
        Integer[] contador = {0};
        NodoAVL nodo = (NodoAVL) this.buscar(etiqueta, contador);
        NodoAVL raiz = (NodoAVL) super.getRaiz();
        if (nodo != null) {
            if (raiz != null) {
                NodoAVL[] lexicoanterior = {null};
                super.setRaiz(raiz.eliminar(nodo, lexicoanterior));
                NodoAVL nodo1 = (NodoAVL) this.buscar(etiqueta, contador);
                if (lexicoanterior[0] != null && nodo1 != null) {
                    NodoAVL aux = lexicoanterior[0];
                    raiz = (NodoAVL) super.getRaiz();
                    super.setRaiz(raiz.eliminar(aux, lexicoanterior));
                    raiz = (NodoAVL) super.getRaiz();
                    super.setRaiz(raiz.sustituir(nodo, aux));

                }
            }
        }
    }
    public static Boolean testearAltura(NodoAVL nodis) {
        Boolean iz = true;
        Boolean der = true;
        Boolean balance = true;
        int resul=0;
        if (nodis.getHijoDer() != null) {
            der = der && testearAltura((NodoAVL) nodis.getHijoDer());
        }
        if (nodis.getHijoDer() != null && (nodis.getHijoIzq() != null)) {
            resul = nodis.getHijoDer().obtenerAltura() - nodis.getHijoIzq().obtenerAltura();
            
        }
        if(nodis.getHijoDer()== null && nodis.getHijoIzq()!= null){
            resul=-1-nodis.getHijoIzq().obtenerAltura();
        }
        if(nodis.getHijoDer()!= null && nodis.getHijoIzq()== null){
            resul=1+nodis.getHijoDer().obtenerAltura();
        }
        balance = resul < 2 && resul > -2;
        if (nodis.getHijoIzq() != null) {
            iz = iz && testearAltura((NodoAVL) nodis.getHijoIzq());
        }
        return iz && der && balance;

    }
    

}
