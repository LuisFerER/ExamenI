package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

/**
 * Trabajo realizado para el examen de la materia "Proyecto de Desarrollo de
 * Videojuegos.
 * Doctor Cristian Eduardo Boyain y Goytia Luna
 * @author Luis Fernando Escobedo Romero
 * Matricula: 38192891
 */
public class Main extends SimpleApplication {

    // Declaramos los nodos para cada planeta, teniendo en cuenta que el nodo
    // del sol sera el principal y el que tendra la geometria del sol y los nodos
    // de los demas planetas.
    Node nodo_sol = new Node("nodo_sol");
    Node nodo_mercurio = new Node("nodo_mercurio");
    Node nodo_venus = new Node("nodo_venus");
    Node nodo_tierra = new Node("nodo_tierra");
    Node nodo_marte = new Node("nodo_marte");
    
    // Declaramos los Spatial para cada planeta, con estos se podra rotar en su
    // propio eje cada planeta.
    Spatial sp_sol=null;
    Spatial sp_mercurio=null;
    Spatial sp_venus=null;
    Spatial sp_tierra=null;
    Spatial sp_marte=null;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Hacemos que la camara se pueda mover a mayor velocidad para apreciar
        // mejor el sistema solar.
        flyCam.setMoveSpeed(100f); //Determinamos que la camara se mueva a una mayor velocidad
        // Colocamos la camara en un lugar en donde se aprecien los planetas.
        cam.setLocation(new Vector3f(0,0,400));
        
        // A continuacion, declaramos cada esfera de los planetas y creamos sus respectivos
        // material y geometrias, ademas de poner textura a cada planeta.
        Sphere sol = new Sphere(100,100,18);
        Geometry sol_geom = new Geometry("sol",sol);
        Material sol_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sol_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/sol.jpg"));
        sol_geom.setMaterial(sol_mat);
        
        Sphere mercurio = new Sphere(100,100,4);
        Geometry mercurio_geom = new Geometry("mercurio", mercurio);
        Material mercurio_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mercurio_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/mercurio.jpg"));
        mercurio_geom.setMaterial(mercurio_mat);
        // Movemos un poco a mercurio para que no este cerca del sol.
        mercurio_geom.setLocalTranslation(50,0,0);
        
        Sphere venus = new Sphere(100,100,8);
        Geometry venus_geom = new Geometry("venus", venus);
        Material venus_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        venus_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/venus.jpg"));
        venus_geom.setMaterial(venus_mat);
        // Movemos a venus para tener sepaparicion entre planetas.
        venus_geom.setLocalTranslation(100,0,0);
        
        Sphere tierra = new Sphere(100,100,10);
        Geometry tierra_geom = new Geometry("tierra", tierra);
        Material tierra_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        tierra_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/tierra.jpg"));
        tierra_geom.setMaterial(tierra_mat);
        // Movemos a la tierra para tener sepaparicion entre planetas.
        tierra_geom.setLocalTranslation(150,0,0);
        
        Sphere marte = new Sphere(100,100,5);
        Geometry marte_geom = new Geometry("marte", marte);
        Material marte_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        marte_mat.setTexture("ColorMap", assetManager.loadTexture("Textures/marte.jpg"));
        marte_geom.setMaterial(marte_mat);
        // Movemos a marte para tener sepaparicion entre planetas.
        marte_geom.setLocalTranslation(200,0,0);
        
        // rotamos cada planeta para lucirlos a como estamos acostumbrados a ver a los planetas
        // (sus polos arriba y abajo)
        sol_geom.rotate(-FastMath.HALF_PI,0,0);
        mercurio_geom.rotate(-FastMath.HALF_PI,0,0);
        venus_geom.rotate(-FastMath.HALF_PI,0,0);
        tierra_geom.rotate(-FastMath.HALF_PI,0,0);
        marte_geom.rotate(-FastMath.HALF_PI,0,0);

        // Dentro del rootNode agregamos como hijo al nodo del sol.
        rootNode.attachChild(nodo_sol);
        // Agregamos la geometria del sol a su nodo correspondiente.
        nodo_sol.attachChild(sol_geom);
        // Agregamos los nodos de los planetas al nodo del sol.
        nodo_sol.attachChild(nodo_mercurio);
        nodo_sol.attachChild(nodo_venus);
        nodo_sol.attachChild(nodo_tierra);
        nodo_sol.attachChild(nodo_marte);
        // Agregamos cada geometria de los planetas a sus respectivos nodos.
        nodo_mercurio.attachChild(mercurio_geom);
        nodo_venus.attachChild(venus_geom);
        nodo_tierra.attachChild(tierra_geom);
        nodo_marte.attachChild(marte_geom);
    }

    /**
     * Aqui se asigna cada elemento para que se puedan visualizar en la escena
     * Teniendo en cuenta que se deben crear Spatial y manipular los nodos.
     * @param tpf 
     */
    @Override
    public void simpleUpdate(float tpf) {
        // verificamos que cada Spatial haya sido creado, si no, se asigna para poder
        // manipular mejor y asi rotar en su propio eje a cada planeta. 
        // La asignacion que se hace es, al Spatial estamos definiendo cada geometria de cada
        // Planeta para su manipulacion.
        if(sp_sol==null)
            sp_sol=nodo_sol.getChild("sol");
        if(sp_mercurio==null)
            sp_mercurio=nodo_sol.getChild("mercurio");
        if(sp_venus==null)
            sp_venus=nodo_sol.getChild("venus");
        if(sp_tierra==null)
            sp_tierra=nodo_sol.getChild("tierra");
        if(sp_marte==null)
            sp_marte=nodo_sol.getChild("marte");
        
        // Rotamos en su propio eje a cada planeta
        sp_sol.rotate(0, 0, 2.8f*tpf);
        sp_mercurio.rotate(0, 0, 2.5f*tpf);
        sp_venus.rotate(0, 0, 2*tpf);
        sp_tierra.rotate(0, 0, tpf);
        sp_marte.rotate(0, 0, 0.0009f);
        
        // A cada nodo se le asigna una rotacion, para tener el efecto de translasion
        // de cada planeta al rededor del sol, importante saber que se debe rotar el NODO
        // de cada planeta.
        nodo_mercurio.rotate(0, 0.002f, 0);
        nodo_venus.rotate(0,0.0015f,0);
        nodo_tierra.rotate(0,0.00012f,0);
        nodo_marte.rotate(0,0.0001f,0);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
