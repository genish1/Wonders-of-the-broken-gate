import java.util.HashMap;
import java.util.Set;

/**
 * Classe Room - Classe où toutes les méthodes ou fonctions liées à room sont crées.
 * @author Kuch
 */
public class Room
{
    /**
     * La description de la salle
     */
    private String aDescription;
    
    /**
     * Une liste aillant le nom des salles en clé et les salles en valeur.
     */
    private HashMap<String, Room> aExits;
    
    /**
     * Le nom des images des salles.
     */
    private String aImageName;
    
    /**
     * La liste d'item qu'il y a dans la salle.
     */
    private ItemList aItemList;
    
    /**
     * Une liste aillant la direction de la porte et la porte en valeur.
     */
    private HashMap<String,Door> aDoor;
     /**
      * Crée une pièce décrite par la chaîne "pDescription" et le nom d'une image "pImage"
      * @param pDescription est une chaîne de caractère comme "le salon" ou "l'accueil".
      * @param pImage est une chaine de caractère qui est le nom de l'image représentant la salle.
      */
     public Room(final String pDescription,final String pImage)
    {
         this.aDescription= pDescription;
         this.aExits = new HashMap<String, Room>();
         this.aItemList = new ItemList();
         this.aImageName = pImage;
         this.aDoor = new HashMap<String,Door>();
    }//Room()
     
     /**
      * Permet d'afficher la description de la salle demander.
      * @return la description de la salle demander
      */
      public String getShortDescription()
    {
         return this.aDescription;
    }//getDescription()
    
    /**
     * Renvoie la liste d'item.
     * @return la liste d'item
     */
    public ItemList getItemList()
    {
        return this.aItemList;
    }
    
     /**
      * Définit les sorties de la pièce selectionné. 
      * Chaque direction conduit à une autre pièce ou null(pas de sortie dans cette direction).
      * @param pDirection est la clé permettant de trouver la salle.
      * @param pVoisin est la salle voisine à la salle sélectioné.
      */
     public void setExit(final String pDirection,final Room pVoisin)
    {
         this.aExits.put(pDirection,pVoisin);
    }//setExits()
    
    /**
     * Permet de retirer une salle en tant que sortie.
     * @param pDirection La direction d'où la salle est.
     */
    public void removeExit(final String pDirection)
    {
        this.aExits.remove(pDirection);
    }
     
     /**
      * Permet d'ajouter un item à la salle.
      * @param pNameItem nom de l'item
      * @param pItem l'item
      */
     public void addItem(final String pNameItem,final Item pItem)
    {
         this.aItemList.takeItemList(pNameItem,pItem);
    }//addItem()
     
     /**
      * Permet d'avoir la salle associé à la direction demander.
      * @param pDirection est la clé liée à une salle. Il est une direction(exemple: "sud").
      * @return renvoie la pièce atteinte si nous nous déplaçcons
      * dans la direction "pDirection". S'il n'y a pas de pas de pièce
      * dans cette direction, renvoie une room UNKNOWN qui a comme description "Unknow direction!"
      */
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }//getExit()
     
     /**
         * Renvoie une description des sorties de la
         * pièce, par exemple, "Sorties : nord ouest".
         * @return Une description des sorties possibles.
         */
    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder( "Exits:" );
        for ( String vS : this.aExits.keySet() )
            returnString.append( " " + vS );
        return returnString.toString();
    }//getExitString()
     
    /**
     * Renvoie le contenue de la pièce.
     * Exemple: "No items here" si il y a aucun item liée à la salle, "sword " si il y a sword liée à la pièce.
     * @return La fonction renvoie soit "No items here" soit une liste des items liées à la salle en une phrase.
    */
    private String getItemString()
    {
        return this.aItemList.getItemListString();
    }//getItemString()
    
    /**
     * Renvoie une description détaillée de cette pièce sous forme : 
     *    Vous êtes dans la cuisine.
     *    Sorties : nord ouest
     * @return Une description de la pièce, avec les sorties.
     */
     public String getLongDescription()
     {
         return "You're " + this.aDescription + "\n" + this.getExitString()+"\n"+this.getItemString();
     }//getLongDescription()
    
    /**
     * Renvoie le nom de l'image.
     * @return Le nom de l'image.
     */
    public String getImageName()
    {
        return this.aImageName;
    }//getImageName()
    
    /**
     * Permet d'enlever l'item de la salle.
     * @param pNameItem nom de l'item dans la salle.
     */
    public void removeItem(final String pNameItem)
    {
        this.aItemList.removeItemList(pNameItem);
    }
    
    /**
     * Permet d'avoir l'item dans la liste des items de la salle.
     * @param pName nom de l'item demandé.
     * @return l'item demandé.
     */
    public Item getItem(final String pName)
    {
        return this.aItemList.getItemList(pName);
    }
    
    /**
     * Permet de savoir la salle actuelle a une salle en sortie.
     * @param pRoom La salle où l'on veut vérifier si une salle est une de ces sorties.
     * @return vrai si la salle n'a pas la salle recherché en sortie.
     */
    public boolean isExit(final Room pRoom)
    {
        return !pRoom.aExits.containsValue(this);
    }
    
    /**
     * Permet de créer une porte pour la salle précisé.
     * @param pDirection La direction où est la porte.
     * @param pDoor La porte.
     */
    public void setDoor(final String pDirection,final Door pDoor)
    {
        this.aDoor.put(pDirection,pDoor);
    }
    
    /**
     * Permet d'avoir la porte lié à la salle.
     * @param pDirection La direction de la porte.
     * @return la porte liée à la salle.
     */
    public Door getDoor(final String pDirection)
    {
        return this.aDoor.get(pDirection);
    }//getExit()
    
} // Room
