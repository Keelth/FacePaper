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
public class NodoAVL extends TElementoArbolBB {

    private int balance;
    private int altura;
    private int frecuencia;

    public NodoAVL(Comparable etiqueta) {
        super(etiqueta);
    }

    public NodoAVL insertar(NodoAVL insercion) {
        if (insercion.getEtiqueta().compareTo(super.getEtiqueta()) < 0) {
            NodoAVL izq = (NodoAVL) super.getHijoIzq();
            if (izq != null) {
                super.setHijoIzq(izq.insertar(insercion));
            } else {
                super.setHijoIzq(insercion);
            }
        }
        if (insercion.getEtiqueta().compareTo(super.getEtiqueta()) > 0) {
            NodoAVL der = (NodoAVL) super.getHijoDer();
            if (der != null) {
                super.setHijoDer(der.insertar(insercion));
            } else {
                super.setHijoDer(insercion);
            }

        }
        actualizarBalance();
        return balancear(this);
    }

    public NodoAVL(int balance, Comparable etiqueta) {
        super(etiqueta);
        this.balance = balance;
    }

    public void actualizarBalance() {
        NodoAVL izq = (NodoAVL) super.getHijoIzq();
        NodoAVL der = (NodoAVL) super.getHijoDer();
        if (der == null && izq == null) {
            altura = 0;
            return;
        }
        if (der == null) {
            altura = 1 + izq.altura;
            balance = -altura;
            return;
        }
        if (izq == null) {
            altura = 1 + der.altura;
            balance = altura;
            return;
        }
        altura = 1 + Math.max(der.altura, izq.altura);
        balance = der.altura - izq.altura;
    }

    public NodoAVL rotacionLL(NodoAVL k2) {
        NodoAVL k1 = (NodoAVL) k2.getHijoIzq();
        k2.setHijoIzq(k1.getHijoDer());
        k1.setHijoDer(k2);
        if (k2.getHijoIzq() != null) {
            ((NodoAVL) k2.getHijoIzq()).actualizarBalance();
        }
        if (k1.getHijoDer() != null) {
            ((NodoAVL) k1.getHijoDer()).actualizarBalance();
        }
        k1.actualizarBalance();
        return k1;
    }

    public NodoAVL rotacionRR(NodoAVL k1) {
        NodoAVL k2 = (NodoAVL) k1.getHijoDer();
        k1.setHijoDer(k2.getHijoIzq());
        k2.setHijoIzq(k1);
        if (k2.getHijoIzq() != null) {
            ((NodoAVL) (k2.getHijoIzq())).actualizarBalance();
        }
        if (k1.getHijoDer() != null) {
            ((NodoAVL) (k1.getHijoDer())).actualizarBalance();
        }
        k2.actualizarBalance();
        return k2;
    }

    public NodoAVL rotacionLR(NodoAVL k3) {
        k3.setHijoIzq(rotacionRR((NodoAVL) (k3.getHijoIzq())));
        if (k3.getHijoIzq() != null) {
            ((NodoAVL) (k3.getHijoIzq())).actualizarBalance();
        }
        k3.actualizarBalance();
        return rotacionLL(k3);
    }

    public NodoAVL rotacionRL(NodoAVL k1) {
        k1.setHijoDer(rotacionLL((NodoAVL) (k1.getHijoDer())));
        if (k1.getHijoDer() != null) {
            ((NodoAVL) (k1.getHijoDer())).actualizarBalance();
        }
        k1.actualizarBalance();
        return rotacionRR(k1);
    }

    public NodoAVL balancear(NodoAVL k1) {
        if (k1 != null) {
            NodoAVL der = (NodoAVL) k1.getHijoDer();
            NodoAVL izq = (NodoAVL) k1.getHijoIzq();
            if (der != null) {
                if (k1.balance == 2 && der.balance >= 0) {
                    return rotacionRR(k1);
                }
                if (k1.balance == 2 && der.balance == -1) {
                    return rotacionRL(k1);
                }
            }
            if (izq != null) {
                if (k1.balance == -2 && izq.balance == 1) {
                    return rotacionLR(k1);
                }
                if (k1.balance == -2 && izq.balance <= 0) {
                    return rotacionLL(k1);
                }
            }
        }
        return k1;

    }

    public NodoAVL sustituir(NodoAVL k1, NodoAVL k2) {
        if (k1.getEtiqueta().compareTo(getEtiqueta()) < 0) {
            if (getHijoIzq() != null) {
                setHijoIzq(((NodoAVL) getHijoIzq()).sustituir(k1, k2));
            }
            return this;
        }
        if (k1.getEtiqueta().compareTo(getEtiqueta()) > 0) {
            if (getHijoDer() != null) {
                setHijoDer(((NodoAVL) getHijoDer()).sustituir(k1, k2));
            }
            return this;
        }
        k2.setHijoIzq(k1.getHijoIzq());
        k2.setHijoDer(k1.getHijoDer());
        k2.altura=k1.altura;
        k2.balance=k1.balance;
        return k2;
    }

    public NodoAVL eliminar(NodoAVL k1, NodoAVL[] lexicoanterior) {
        if (k1.getEtiqueta().compareTo(getEtiqueta()) < 0) {
            if (getHijoIzq() != null) {
                setHijoIzq(((NodoAVL) getHijoIzq()).eliminar(k1, lexicoanterior));
            }
            actualizarBalance();
            return balancear(this);
        }
        if (k1.getEtiqueta().compareTo(getEtiqueta()) > 0) {
            if (getHijoDer() != null) {
                setHijoDer(((NodoAVL) getHijoDer()).eliminar(k1, lexicoanterior));
            }
            actualizarBalance();

            return balancear(this);
        }
        lexicoanterior[0] = (NodoAVL) this.lexicograficamenteAnterior();
        actualizarBalance();
        return balancear((NodoAVL) quitarNodo());
    }

    public IElementoAB quitarNodo() {
        if (getHijoIzq() == null) {
            return getHijoDer();
        }
        if (getHijoDer() == null) {
            return getHijoIzq();
        }
        return this;

    }
    
    public void actualizarFrecuencia(int unNumero)
    {
        this.frecuencia += unNumero;
    }
    
    public int getFrecuencia()
    {
        return this.frecuencia;
    }
}
