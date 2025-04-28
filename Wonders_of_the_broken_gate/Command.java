
/**
 * Classe Command - Une commande utilisant 1 ou 2 mots.
 *
 * @author Kuch
 */
public class Command
{
    /**
     * La premier mot de la commande.
     */
    private String aCommandWord;
    
    /**
     * Le deuxième mot de la commande.
     */
    private String aSecondWord;
    
    /**
     * Constructeur naturel de Command
     * 
     * @param pCommandWord le premier mot tapait par l'utilisateur.
     * @param pSecondWord le deuxième mot tapait par l'utilisateur.
     */
    public Command(final String pCommandWord,final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command(.)
    
    /**
     * Permet d'accéder au premier mot de la commande.
     * @return Le premier mot de la commande.
     */
    public String getCommandWord()
    {return this.aCommandWord;}//getCommandWord()
    
    /**
     * Permet d'accéder au deuxième mot de la commande.
     * @return Le deuxième mot de la commande.
     */
    public String getSecondWord()
    {return this.aSecondWord;}//getSecondWord()
    
    /**
     * Permet de vérifier si la commande contient un deuxième mot
     * 
     * @return Renvoie vrai si la command a un deuxième mot sinon renvoie faux.
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    }//hasSecondWord()
    
    /**
     * Vérifie si la premier de la commande fait partie des commandes connues.
     * 
     * @return Renvoie vrai si la premier mot fait partie des commandes connues sinon faux.
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    }//isUnkwown()
} // Command
