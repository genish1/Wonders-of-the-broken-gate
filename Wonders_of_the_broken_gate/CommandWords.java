
/**
 * La classe CommandWords permet de stocker toutes les commandes, d'avoir la liste des commandes et de vérifier si une commande est dans la liste.
 */
public class CommandWords
{
    /**
     * Une liste contenant les commandes valident.
     */
    private final String[] aValidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.aValidCommands = new String[12];
        this.aValidCommands[0] = "go";
        this.aValidCommands[1] = "help";
        this.aValidCommands[2] = "quit";
        this.aValidCommands[3] = "look";
        this.aValidCommands[4] = "eat";
        this.aValidCommands[5] = "back";
        this.aValidCommands[6] = "test";
        this.aValidCommands[7] = "take";
        this.aValidCommands[8] = "drop";
        this.aValidCommands[9] = "inventory";
        this.aValidCommands[10] = "charge";
        this.aValidCommands[11] = "fire";
    } // CommandWords()

    /**
     * Check whether a given String is a valid command word. 
     * @param pString La commande a vérifier.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands :
        return false;
    } // isCommand()
    
    /**
     * Permet d'avoir toutes les commandes dans une chaine de caractère.
     * @return la chaine de caractère.
     */
    public String getCommandList()
    {
        StringBuilder vCommands = new StringBuilder();
        for(String vCommand : aValidCommands){
            vCommands.append(vCommand+" ");
        }
        return vCommands.toString();
    }
} // CommandWords
