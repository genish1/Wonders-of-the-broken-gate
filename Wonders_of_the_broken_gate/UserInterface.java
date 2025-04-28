//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.*;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
/**
 * Cette classe ajoute une interface graphique simple avec
 * une zone d'entrée de texte,une zone de sortie de texte et une zone optionnel pour une image.
 * 
 * @author Kuch
 */
public class UserInterface implements ActionListener
{
    /**
     * Attribut permettant d'avoir la moteur du jeu.
     */
    private GameEngine aEngine;
    
    /**
     * Cette attribut est la fenêtre où nos images,textes et boutons apparaîtront
     */
    private JFrame     aMyFrame;
    
    /**
     * Cette attribut est la partie de la fenêtre où le joueur pourra écrire.
     */
    private JTextField aEntryField;
    
    /**
     * Cette attribut est le texte qui apparaîtra sur la fenêtre du jeu.
     */
    private JTextArea  aLog;
    
    /**
     * Cette attribut permet de mettre des images.
     */
    private JLabel     aImage;
    
    /**
     * Cette attribut permet d'ajouter un bouton help.
     */
    private JButton    aBoutonHelp;
    /**
     * Construit l'interface pour l'utilisateur UserInterface. En paramètre,un Game Engine
     * (un objet traitant et exécutant les commandes de jeu) est nécessaire.
     * 
     * @param pGameEngine L'objet gameEngine implémentant la logique du jeu.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Permet d'afficher un texte dans la zone de sortie de texte.
     * @param pText Le texte que l'on veut afficher.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Permet d'afficher un texte dans la zone de sortie de texte, suivi d'un saut à la ligne.
     *@param pText Le texte que l'on veut afficher à la ligne.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Affiche une image à l'aide de son chemin.
     * @param pImageName Le nom de l'image.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Active ou désactive la saisie dans le champ de saisie de texte.
     * @param pOnOff booleen   
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Configurer l'interface utilisateur graphique.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Wonders of the Broken Gate" ); // change the title !
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        this.aBoutonHelp = new JButton("help");
        this.aImage = new JLabel();

        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add(this.aBoutonHelp,BorderLayout.LINE_END);
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        this.aBoutonHelp.setBackground(Color.RED);
        this.aBoutonHelp.setForeground(Color.WHITE);
        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        this.aBoutonHelp.addActionListener(this);
        // to end program when window is closed
        this.aMyFrame.addWindowListener(
        new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if("help".equals(pE.getActionCommand()))
        {
            this.aEngine.interpretCommand("help");
        }
        else{this.processCommand(); // never suppress this line
        }
    } // actionPerformed(.)

    /**
     * Une commande a été saisie dans le champ de saisie.  
     * Lis la commande puis la traite.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
