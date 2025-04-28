

/**
 * Classe Game - Permet de créer une partie et son interface graphique.
 * @author Kuch 
 */
public class Game
{
    /**
     * Attribut permettant de créer l'interface.
     */
    private UserInterface aGui;
    
    /**
     * Attribut permettant d'avoir la moteur du jeu.
     */
    private GameEngine aEngine;
    /**
     * Créez le jeu et initialisez sa carte interne. Créez l'interface et créez un lien vers celle-ci.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }//Game()
} // Game
