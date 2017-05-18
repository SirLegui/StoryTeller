package storyteller.librerias;

import storyteller.Estructura.ArbolAVL;

public class Album {
    private ArbolAVL tree;
    private String nombre;
    private String local_path;

    public Album(ArbolAVL tree, String nombre, String local_path) {
        this.tree = tree;
        this.nombre = nombre;
        this.local_path = local_path;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocal_path() {
        return local_path;
    }

    public ArbolAVL getTree() {
        return tree;
    }

    public void setLocal_path(String local_path) {
        this.local_path = local_path;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTree(ArbolAVL tree) {
        this.tree = tree;
    }
    
    
    
}
