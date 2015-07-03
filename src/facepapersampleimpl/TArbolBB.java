/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepapersampleimpl;

public class TArbolBB implements IArbolBB {

    private IElementoAB raiz;

    @Override
    public boolean insertar(IElementoAB unElemento) {
        if (getRaiz() == null) {
            setRaiz(unElemento);
            return true;
        } else {
            getRaiz().insertar(unElemento);
            return true;
        }
    }

    @Override
    public boolean insertar(IElementoAB unElemento, Integer[] contador) {
        if (getRaiz() == null) {
            contador[0] += 1;
            setRaiz(unElemento);
            return true;
        } else {
            contador[0] += 1;
            getRaiz().insertar(unElemento, contador);
            return true;
        }
    }

    @Override
    public IElementoAB buscar(Comparable unaEtiqueta, Integer[] contador) {
        if (getRaiz() == null) {
            return null;
        } else {
            return getRaiz().buscar(unaEtiqueta, contador);
        }
    }

    @Override
    public String preOrden() {
        if (getRaiz() == null) {
            return "";
        } else {
            return getRaiz().preOrden();
        }
    }

    @Override
    public String inOrden() {
        if (getRaiz() == null) {
            return "";
        } else {
            return getRaiz().inOrden();
        }
    }

    @Override
    public String postOrden() {
        if (getRaiz() == null) {
            return "nada";
        } else {
            return getRaiz().postOrden();
        }
    }

    @Override
    public int obtenerAltura() {
        return getRaiz().obtenerAltura();
    }

    @Override
    public int obtenerTamanio() {
        if (getRaiz() == null) {
            return 0;
        }
        return getRaiz().obtenerTamanio();
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        Integer[] contador = {0};
        if (getRaiz() == null) {
            return -1;
        } else {
            if (getRaiz().buscar(unaEtiqueta, contador) == null) {
                return -1;
            } else {
                return getRaiz().obtenerNivel(unaEtiqueta);
            }
        }
    }

    @Override
    public int obtenerCantidadHojas() {
        if (getRaiz() == null) {
            return 0;
        } else {
            return getRaiz().obtenerCantidadHojas();
        }
    }

    @Override
    public void listarHojas() {
        if (getRaiz() != null) {
            System.out.println(getRaiz().listarHojas());
        }
    }

    @Override
    public IElementoAB encontrarMinimo() {
        if (getRaiz() == null) {
            return null;
        } else {
            return getRaiz().encontrarMinimo();
        }
    }

    @Override
    public IElementoAB encontrarMaximo() {
        if (getRaiz() == null) {
            return null;
        } else {
            return getRaiz().encontrarMaximo();
        }
    }

    @Override
    public IElementoAB lexicograficamenteAnterior(Comparable unaClave) {
        if (getRaiz() == null) {
            return null;
        } else {
            Integer[] contador = {0};
            IElementoAB elem = getRaiz().buscar(unaClave, contador);
            if (elem == null) {
                return null;
            } else {
                return elem.lexicograficamenteAnterior();
            }
        }
    }

    @Override
    public int obtenerCantNodosNivel(int nivel) {
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean esDeBusqueda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (getRaiz() != null) {
            setRaiz(getRaiz().eliminar(unaEtiqueta));
        }
    }

    @Override
    public void eliminarSiiNivel(Comparable unaEtiqueta, int nivel) {
        if (getRaiz() != null) {
            setRaiz(getRaiz().eliminarSiiNivel(unaEtiqueta, nivel));
        }
    }

    /**
     * Imprime las hojas en orden lexicografico inverso
     *
     * @return
     */
    @Override
    public String hojasLexicoInver() {
        if (getRaiz() != null) {
            return "{" + getRaiz().hojaslexicoInver() + "}";
        }
        return "{}";
    }

    /**
     * @return the raiz
     */
    public IElementoAB getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(IElementoAB raiz) {
        this.raiz = raiz;
    }

    public void completarNodosExternos(int[] vectorBetas) {
        if (raiz!= null) {
            Integer[] contador = new Integer[1];
            contador[0] = 0;
            ((TElementoArbolBB)(raiz)).completarNodosExternos(vectorBetas, contador);
        }
    }
    public long calcularCosto(){
        long[] acum = {0};
        if(raiz != null){
            return ((TElementoArbolBB) raiz).obtenerCosto(acum, 1);
        }
        else{
            return 0;
        }
       
    }

}
