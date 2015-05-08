/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepapersampleimpl;

import facebook4j.Picture;
import facebook4j.Post;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.edu.ucu.p2.adt.impl.*;
import uy.edu.ucu.p2.adt.interfaces.*;
import uy.edu.ucu.p2.facebook.api.FacePaperImpl;
import uy.edu.ucu.p2.facebook.api.FacepaperConnector;
import uy.edu.ucu.p2.facebook.api.IFacePaper;
import uy.edu.ucu.p2.facebook.api.exceptions.FacePaperException;
import uy.edu.ucu.p2.facebook.server.Command;

/**
 *
 * @author diego
 */
public class MyFacePaper implements IFacePaper {

    private FacepaperConnector facepaperConnector = new FacePaperImpl();
    private boolean conectado = false;

    private void conectado() {
        this.conectado = true;
    }

    @Override
    public FacepaperConnector getFacepaperConnector() {
        return this.facepaperConnector;
    }

    /**
     *
     * @return obtiene del conector las noticias (getHome) y cargar la lista de
     * NodoPost
     */
    @Override
    public ILista<INodoPost> obtenerNoticias() throws FacePaperException {
        Post[] posts = this.getFacepaperConnector().getHome(10);
        ILista<INodoPost> result = new Lista<INodoPost>();

        for (Post p : posts) {
            int likes;
            likes = p.getLikes().size();
            if (p.getDescription() == null) {
                if (p.getLink() == null) {
                    NodoPost nodo = new NodoPost(p.getId(), p.getCreatedTime(), likes, new Autor(p.getFrom().getId(), p.getFrom().getName()), p.getDescription());
                    result.insertar(nodo);
                } else {
                    NodoPost nodo = new NodoPost(p.getId(), p.getCreatedTime(), likes, new Autor(p.getFrom().getId(), p.getFrom().getName()), (p.getPicture().toString()));
                    result.insertar(nodo);
                }
            } else {
                NodoPost nodo = new NodoPost(p.getId(), p.getCreatedTime(), likes, new Autor(p.getFrom().getId(), p.getFrom().getName()), p.getDescription());
                result.insertar(nodo);
            }
        }
        return result;
    }

    /**
     *
     * @return Un String con todas las noticias
     * @throws FacePaperException
     */
    public String muro() throws FacePaperException {
        String salida = "";
        String separador = "---------------------------------------------";
        try {
            ILista<INodoPost> lis = obtenerNoticias();
            INodoPost actual = lis.getPrimero();
            while (actual != null) {
                salida += actual.getAutor() + "\n" + actual.getTexto() + "\n" + "Cantidad de 'Me Gusta': " + actual.getCantidadLikes() + "\n" + actual.getFecha() + "\n" + separador + "\n";
                actual = (INodoPost) actual.getSiguiente();
            }
        } catch (FacePaperException ex) {
            Logger.getLogger(MyFacePaper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    /**
     *
     * @return Un String[] con cada Autor
     * @throws FacePaperException
     */
    public String autores() throws FacePaperException {
        String salida = "";
        String separador = ",";
        try {
            ILista<INodoPost> lis = obtenerNoticias();
            INodoPost actual = lis.getPrimero();
            while (actual != null) {
                salida += actual.getAutor() + separador;
                actual = (INodoPost) actual.getSiguiente();
            }
        } catch (FacePaperException ex) {
            Logger.getLogger(MyFacePaper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    /**
     *
     * @return Un String con todos Mis posts
     * @throws FacePaperException
     */
    public String perfil() throws FacePaperException {
        String salida = "";
        String separador = "---------------------------------------------";
        try {
            ILista<INodoPost> lis = obtenerMuro();
            INodoPost actual = lis.getPrimero();
            while (actual != null) {
                salida += actual.getAutor() + "\n" + actual.getTexto() + "\n" + "Cantidad de 'Me Gusta': " + actual.getCantidadLikes() + "\n" + actual.getFecha() + "\n" + separador + "\n";
                actual = (INodoPost) actual.getSiguiente();
            }

        } catch (FacePaperException ex) {
            Logger.getLogger(MyFacePaper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    /**
     *
     * @return obtiene del conector tus Posts (getFeed) y cargar la lista de
     * NodoPost
     */
    @Override
    public ILista<INodoPost> obtenerMuro() throws FacePaperException {
        Post[] posts = this.getFacepaperConnector().getFeed(123);
        ILista<INodoPost> result = new Lista<INodoPost>();
        for (Post p : posts) {
            int likes;
            likes = p.getLikes().size();
            NodoPost nodo = new NodoPost(p.getId(), p.getCreatedTime(), likes, new Autor(p.getFrom().getId(), p.getFrom().getName()), p.getDescription());
            result.insertar(nodo);
        }
        return result;

    }

    /**
     *
     * @return obtiene del conector tus posts, de tus posts los Me Gusta
     * (getLikes), los suma y retorna tal suma
     */
    @Override
    public int calcularMeGustaUltimaSemana() throws FacePaperException {
        int cont = 0;
        try {
            ILista<INodoPost> lis = obtenerMuro();
            Date hoy = new Date();
            INodoPost actual = lis.getPrimero();
            while (actual != null) {
                if (((hoy.getTime() - actual.getFecha().getTime()) / (24 * 60 * 60 * 1000)) <= 7) {
                    cont += actual.getCantidadLikes();
                }
                actual = (INodoPost) actual.getSiguiente();
            }
        } catch (FacePaperException ex) {
            Logger.getLogger(MyFacePaper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cont;
    }

    public String filtrarAutor(String unAutor) throws FacePaperException {
        String postsAutor = "";
        String separador = "--------------";
        try {
            ILista<INodoPost> lis = obtenerNoticias();
            INodoPost actual = lis.getPrimero();
            while (actual != null) {
                if (actual.getAutor().equals(unAutor)) {
                    postsAutor += actual.getTexto() + "\n" + "Cantidad de 'Me Gusta': " + actual.getCantidadLikes() + "\n" + actual.getFecha() + "\n" + separador + "\n";
                }
                actual = (INodoPost) actual.getSiguiente();
            }
        } catch (FacePaperException ex) {
            Logger.getLogger(MyFacePaper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return postsAutor;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    @Override
    public void conectar(Command cmnd) throws FacePaperException {
        facepaperConnector.conectar(cmnd);
    }

}
