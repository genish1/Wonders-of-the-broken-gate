import java.util.HashMap;
import java.util.Stack;

/**
 * Permet de stocker toutes les informations concernant le joueur
 *
 * @author KUCH
 */
public class Player
{
    /**
     * Le nom du joueur
     */
    private String aName;
    
    /**
     * Le poids maximal que le joueur peut porter.
     */
    private int aWeightMax;
    
    /**
     * Le poids que le joueur porte.
     */
    private int aCurrentWeight;
    
    /**
     * La salle où le joueur est.
     */
    private Room aCurrentRoom;
    
    /**
     * Une liste avec toutes les salles que le joueur a parcourue.
     */
    private Stack<Room> aPreviousRoom;
    
    /**
     * La liste d'item que le joueur a sur lui.
     */
    private ItemList aItemList;
    
    /**
     * Le nombre d'actions que le joueur peut faire.
     */
    private int aMaxMoves= 40;
    
    /**
     * Le nombre d'actions que le joueur a fait.
     */
    private int aCurrentMoves;
    /**
     * Constructeur naturel, il permet de créer un joueur avec un nom et une salle de départ.
     * @param pName le nom du joueur.
     * @param pCurrentRoom salle de départ du joueur.
     */
    public Player(final String pName,final Room pCurrentRoom)
    {
        this.aName = pName;
        this.aWeightMax = 10;
        this.aCurrentWeight =0;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRoom = new Stack<Room>();
        this.aItemList = new ItemList();
        this.aCurrentMoves = 0;
    }
    
    /**
     * Renvoie le nom du joueur.
     * @return Nom
     */
    public String getName()
    {
    return this.aName;
    }
    
    /**
     * Envoie le poids du joueur.
     * @return Poids du joueur
     */
    public int getCurrentWeight()
    {
    return this.aCurrentWeight;
    }
    
    /**
     * Permet de définir le poids du joueur.
     * @param pW Poids
     */
    public void setCurrentWeight(final int pW)
    {
        this.aCurrentWeight =pW;
    }
    
    /**
     * Renvoie le poids maximal que le joueur peut transporter.
     * @return Poids max 
     */
    public int getMaxWeight()
    {
    return this.aWeightMax;
    }
    
    /**
     * Permet de définir le poids maximal transportable par le joueur.
     * @param pW poids
     */
    public void setMaxWeight(final int pW)
    {
        this.aWeightMax = pW;
    }
    
    /**
     * Renvoie la salle actuelle où le joueur est.
     * @return une salle
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }
    
    /**
     * Renvoie une pile de salle où le joueur est passé.
     * @return une pile de salle
     */
    public Stack<Room> getPreviousRoom()
    {
        return this.aPreviousRoom;
    }
    
    /**
     * Permet de définir la position du joueur actuelle.
     * @param pRoom Une salle.
     */
    public void setCurrentRoom(final Room pRoom)
    {
        this.aCurrentRoom = pRoom;
    }
    
    /**
     * Ajoute une salle à l'historique de salle parcourue par le joueur.
     * @param pRoom salle anciennement parcourue par le joueur.
     */
    public void setPreviousRoom(final Room pRoom)
    {
        this.aPreviousRoom.push(pRoom);
    }
    
    /**
     * Permet d'avoir la dernière salle parcourue par le joueur.
     * @return dernière salle parcourue par le joueur.
     */
    public Room lastPreviousRoom()
    {
        return this.aPreviousRoom.peek();
    }
    
    /**
     * Permet de voir si l'historique de salle parcourue par le joueur est vide.
     * @return vrai si elle est vide sinon non.
     */
    public boolean isEmpty()
    {
        return this.aPreviousRoom.empty();
    }
    
    /**
     * Permet de déplacer le joueur à une autre salle et d'ajouter une salle dans l'historique de parcours.
     * @param pRoom une salle
     */
    public void moveTo(final Room pRoom)
    {
        this.setPreviousRoom(this.aCurrentRoom);
        this.setCurrentRoom(pRoom);
        this.aCurrentMoves+=1;
    }
    
    /**
     * Permet d'avoir un item précis dans l'inventaire du joueur.
     * @param pName Le nom du joueur.
     * @return l'item en question.
     */
    public Item getItemPlayer(final String pName)
    {
        return this.aItemList.getItemList(pName);
    }
    
    /**
     * Permet de prendre l'item.
     * @param pNameItem Nom de l'item
     * @param pItem l'item
     */
    public void takeItemPlayer(final String pNameItem,final Item pItem)
    {
        this.aCurrentWeight += pItem.getItemWeight();
        this.aItemList.takeItemList(pNameItem,pItem);
        this.aCurrentMoves +=1;
    }
    
    /**
     * Similaire à take mais pour déposer.
     * @param pNameItem nom de l'item.
     */
    public void DropItemPlayer(final String pNameItem)
    {
        this.aCurrentWeight -= this.aItemList.getItemList(pNameItem).getItemWeight();
        this.aItemList.removeItemList(pNameItem);
        this.aCurrentMoves+=1;
    }
    
    /**
     * Permet de vérifer si l'item peut être prit par rapport à la place disponible dans l'inventaire.
     * @param pI l'item.
     * @return vrai si l'objet est assez léger sinon non.
     */
    public boolean CanBeTake(final Item pI)
    {
        return (this.aCurrentWeight+ pI.getItemWeight() <= this.aWeightMax );
    }
    
    /**
     * Permet d'afficher l'inventaire du joueur.
     * @return exemple: Nom description poids
     */
    public String PlayerInventory()
    {
        return this.aItemList.getItemListDescriptionString();
    }
    
    /**
     * Permet de savoir si le joueur a le droit de bouger.
     * @return retourne vrai tant que le nombre d'action que le joueur a fait est inférieur au max.
     */
    public boolean CanMove()
    {
        return this.aCurrentMoves < this.aMaxMoves;
    }
    
    /**
     * Permet de connaître le nombre d'actions que le joueur a fait.
     * @return nombre d'actions en cours.
     */
    public int getCurrentMoves()
    {
        return this.aCurrentMoves;
    }
    
    /**
     * Permet de connaître le nombre maximum d'actions que le joueur a.
     * @return nombre d'actions maximum.
     */
    public int getMaxMoves()
    {
        return this.aMaxMoves;
    }
    /**
     * Permet de savoir si la dernière salle dans la pile de back est une des sorties de la salle actuelle.
     * @return vrai si la dernière salle parcourue est une des sorties possibles de la salle actuelle.
     */
    public boolean isTrapdoor()
    {
        return this.lastPreviousRoom().isExit(this.aCurrentRoom);
    }
}
