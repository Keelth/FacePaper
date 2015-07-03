/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepapersampleimpl;

public class TElementoArbolBB implements IElementoAB {

    private TElementoArbolBB izq;
    private TElementoArbolBB der;
    private Comparable etiqueta;
    private int frecuencia ;
 
    
    public TElementoArbolBB(Comparable etiqueta) {
        this.etiqueta = etiqueta;
    }
     public TElementoArbolBB(Comparable etiqueta,int frecuencia) {
        this.etiqueta = etiqueta;
        this.frecuencia=frecuencia;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public IElementoAB getHijoIzq() {
        return izq;
    }

    @Override
    public IElementoAB getHijoDer() {
        return der;
    }

    @Override
    public void setHijoIzq(IElementoAB elemento) {
        this.izq = (TElementoArbolBB) elemento;
    }

    @Override
    public void setHijoDer(IElementoAB elemento) {
        this.der = (TElementoArbolBB) elemento;
    }

    @Override
    public IElementoAB buscar(Comparable unaEtiqueta, Integer[] contador) {
        contador[0]++;
        if (etiqueta.compareTo(unaEtiqueta) == 0) {
            return this;
        } else {
            if (etiqueta.compareTo(unaEtiqueta) < 0) {
                if (der == null) {
                    return null;
                }
                return der.buscar(unaEtiqueta, contador);
            } else {
                if (izq == null) {
                    return null;
                }
                return izq.buscar(unaEtiqueta, contador);
            }
        }
    }

    @Override
    public boolean insertar(IElementoAB elemento, Integer[] contador) {
        contador[0]++;
        if (etiqueta.compareTo(elemento.getEtiqueta()) == 0) {
            return false;
        } else {
            if (etiqueta.compareTo(elemento.getEtiqueta()) < 0) {
                if (this.der != null) {
                    this.der.insertar(elemento, contador);
                } else {
                    this.der = (TElementoArbolBB) elemento;
                    return true;
                }
            }
            if (etiqueta.compareTo(elemento.getEtiqueta()) > 0) {
                if (this.izq != null) {
                    this.izq.insertar(elemento, contador);
                } else {
                    this.izq = (TElementoArbolBB) elemento;
                    return true;
                }

            }
            return true;
        }
    }

    @Override
    public boolean insertar(IElementoAB elemento) {
        if (etiqueta.compareTo(elemento.getEtiqueta()) == 0) {
            return false;
        } else {
            if (etiqueta.compareTo(elemento.getEtiqueta()) < 0) {
                if (this.der != null) {
                    this.der.insertar(elemento);
                } else {
                    this.der = (TElementoArbolBB) elemento;
                    return true;
                }
            }
            if (etiqueta.compareTo(elemento.getEtiqueta()) > 0) {
                if (this.izq != null) {
                    this.izq.insertar(elemento);
                } else {
                    this.izq = (TElementoArbolBB) elemento;
                    return true;
                }

            }
            return true;
        }
    }

    @Override
    public String preOrden() {
        if (izq == null && der == null) {
            return etiqueta.toString();
        }
        if (izq == null) {
            return etiqueta +"-"+ der.preOrden();
        }
        if (der == null) {
            return etiqueta +"-"+ izq.preOrden();
        }
        return etiqueta +"-"+ izq.preOrden() +"-"+ der.preOrden();
    }

    @Override
    public String inOrden() {
        if (der == null && izq == null) {
            return etiqueta.toString();
        }
        if (der == null) {
            return izq.inOrden()+"-" + etiqueta;
        }
        if (izq == null) {
            return etiqueta + "-"+der.inOrden();
        }
        return izq.inOrden() +"-"+ etiqueta +"-"+ der.inOrden();
    }

    @Override
    public String postOrden() {
        if (der == null && izq == null) {
            return etiqueta.toString();
        }
        if (der == null) {
            return izq.postOrden() + etiqueta;
        }
        if (izq == null) {
            return der.postOrden() + etiqueta;
        }
        return izq.postOrden() + der.postOrden() + etiqueta;
    }

    @Override
    public int obtenerAltura() {
        if (izq == null && der == null) {
            return 0;
        }
        if (der == null) {
            return this.izq.obtenerAltura() + 1;
        }
        if (izq == null) {
            return this.der.obtenerAltura() + 1;

        } else {
            if (this.der.obtenerAltura() + 1 < this.izq.obtenerAltura() + 1) {
                return this.izq.obtenerAltura() + 1;
            }
            if (this.der.obtenerAltura() + 1 > this.izq.obtenerAltura() + 1) {
                return this.der.obtenerAltura() + 1;
            }
            return this.der.obtenerAltura() + 1;
        }
    }

    @Override
    public int obtenerTamanio() {

        if (this.izq == null && this.der == null) {
            return 1;
        }
        if (this.der == null) {
            return 1 + izq.obtenerTamanio();
        }
        if (this.izq == null) {
            return 1 + der.obtenerTamanio();
        }
        return 1 + this.der.obtenerTamanio() + this.izq.obtenerTamanio();

    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(etiqueta) < 0) {
            return 1 + izq.obtenerNivel(unaEtiqueta);
        }
        if (unaEtiqueta.compareTo(etiqueta) > 0) {
            return 1 + der.obtenerNivel(unaEtiqueta);
        }
        return 0;
    }

    @Override
    public int obtenerCantidadHojas() {
        if (der == null && izq == null) {
            return 1;
        }
        if (der == null) {
            return izq.obtenerCantidadHojas();
        }
        if (izq == null) {
            return der.obtenerCantidadHojas();
        }
        return izq.obtenerCantidadHojas() + der.obtenerCantidadHojas();
    }

    @Override
    public String listarHojas() {
        if (der == null && izq == null) {
            return etiqueta.toString();
        }
        if (der == null) {
            return izq.listarHojas();
        }
        if (izq == null) {
            return der.listarHojas();
        }
        return izq.listarHojas() + der.listarHojas();
    }

    @Override
    public IElementoAB encontrarMinimo() {
        if (izq != null) {
            return izq.encontrarMinimo();
        }
        return this;
    }

    @Override
    public IElementoAB encontrarMaximo() {
        if (der != null) {
            return der.encontrarMaximo();
        }
        return this;
    }

    @Override
    public IElementoAB lexicograficamenteAnterior() {
        if (izq == null) {
            return null;
        } else {
            return izq.encontrarMaximo();
        }
    }

    @Override
    public IElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (izq != null) {
                izq = (TElementoArbolBB) izq.eliminar(unaEtiqueta);
            }
            return this;
        }
        if (unaEtiqueta.compareTo(etiqueta) > 0) {
            if (der != null) {
                der = (TElementoArbolBB) der.eliminar(unaEtiqueta);
            }
            return this;
        }
        return quitarNodo();
    }

    @Override
    public IElementoAB quitarNodo() {
        if (izq == null) {
            return der;
        }
        if (der == null) {
            return izq;
        }
        IElementoAB elHijo = izq;
        IElementoAB elPadre = this;
        while (elHijo.getHijoDer() != null) {
            elPadre = elHijo;
            elHijo = elHijo.getHijoDer();
        }
        if (elPadre != this) {
            elPadre.setHijoDer(elHijo.getHijoIzq());
            elHijo.setHijoIzq(izq);
        }
        elHijo.setHijoDer(der);
        return elHijo;
    }

    @Override
    public IElementoAB eliminarSiiNivel(Comparable unaEtiqueta, int nivel) {
        if (nivel == 0 && unaEtiqueta.equals(this.etiqueta)) {
            return quitarNodo();
        }
        if (unaEtiqueta.compareTo(this.etiqueta) > 0) {
            if (der != null) {
                der = (TElementoArbolBB) der.eliminarSiiNivel(unaEtiqueta, nivel - 1);
            }
            return this;
        }
        if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
            if (izq != null) {
                izq = (TElementoArbolBB) izq.eliminarSiiNivel(unaEtiqueta, nivel - 1);
            }
            return this;
        }
        return this;
    }

    /**
     * Imprime las hojas en orden lexicografico inverso
     *
     * @return
     */
    @Override
    public String hojaslexicoInver() {
        String a = "";
        String b = "";
        if (izq == null && der == null) {
            return etiqueta.toString();
        }
        if (der != null) {
            b = der.hojaslexicoInver();
        }
        if (izq != null) {
            a = izq.hojaslexicoInver();
        }
        if (a != "" && b != "") {
            return b + "-" + a;
        }
        return b + a;
    }

    public void completarNodosExternos(int[] vector_betas, Integer[] contador) {
        if (izq != null) {
            izq.completarNodosExternos(vector_betas, contador);
        } else {
            TElementoArbolBB elemento = new TElementoArbolBB("hoja");
            elemento.setFrecuencia(vector_betas[contador[0]]);
            izq= (TElementoArbolBB) elemento;
            contador[0]++;
        }
        if (der != null) {
            der.completarNodosExternos(vector_betas, contador);
        } else {
            TElementoArbolBB elemento = new TElementoArbolBB("hoja");
            elemento.setFrecuencia(vector_betas[contador[0]]);
            der = (TElementoArbolBB) elemento;
            contador[0]++;
        }
    }

    /**
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    public long obtenerCosto(long[] acum,int nivel){
        acum[0]+= nivel*frecuencia;
        if(izq != null){
            izq.obtenerCosto(acum, nivel+1);
        }
        if(der != null){
            der.obtenerCosto(acum, nivel+1);
        }
        return acum[0];
        
    }
    
    @Override
    public void actualizarFrecuencia(int unNumero)
    {
        
    }
    

}
