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
 * @author Alvaro
 */
public class AutorFacebook extends Autor{

    public AutorFacebook(Comparable clave, String nombre) {
        super(clave, nombre);
    }

    @Override
    public int obtenerCantidadPosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obtenerCantidadPalabrasPublicadas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
