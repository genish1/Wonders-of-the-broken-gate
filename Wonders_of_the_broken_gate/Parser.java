
import java.util.StringTokenizer;
/**
 * 
 */
public class Parser 
{
    /**
     * Une liste de commande.
     */
    private CommandWords aValidCommands;  // (voir la classe CommandWords)
    
    /**
     * Créer un nouveau Parser.
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
        // System.in designe le clavier, comme System.out designe l'ecran
    } // Parser()

    /**
     * Permet d'avoir une commande avec le message que le joueur tape au clavier.
     * @param pInputLine Ce que le joueur écrit.
     * @return La commande demandé si elle existe.
     */
    public Command getCommand(final String pInputLine) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // get first word
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // get second word
        else
            vWord2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if ( this.aValidCommands.isCommand( vWord1 ) )
            return new Command( vWord1, vWord2 );
        else
            return new Command( null, vWord2 );
    } // getCommand()
    /**
     * Permet d'avoir la liste des commandes.
     * @return Une String de la liste des commandes.
     */
    public String getCommandString()
    {
        return this.aValidCommands.getCommandList();
    }
} // Parser
