
/**
 * Classe Item - Permet de créer un item, un item est fabriqué par son nom,sa description et son poids.
 *
 * @author Kuch
 * @version 7.26.1
 */
public class Item
{
    /**
     * La description de l'item
     */
    private String aItemDescription;
    
    /**
     * Le poids de l'item
     */
    private int aItemPoids;
    
    /**
     * Le nom de l'item
     */
    private String aItemNom;
    /**
     * Constructeur naturel d'un Item.
     * 
     * @param pItemNom Nom de l'item.
     * @param pItemDescription Description de l'item.
     * @param pItemPoids Poids de l'item.
     */
    public Item(final String pItemNom,final String pItemDescription,final int pItemPoids)
    {
        this.aItemDescription = pItemDescription;
        this.aItemPoids = pItemPoids;
        this.aItemNom=pItemNom;
    }//item(.)
    
    /**
     * Permet d'accéder au nom de l'item.
     * @return envoie le nom de l'item.
     */
    public String getItemName()
    {
        return this.aItemNom;
    }//getItemNom()
    
    /**
     * Permet d'accéder à la description de l'item.
     * @return envoie la description de l'item.
     */
    public String getItemDescription()
    {
        return this.aItemDescription;
    }//getItemDescription()
    
    /**
     * Permet d'accéder au poids de l'item.
     * @return envoie le poids de l'item.
     */
    public int getItemWeight()
    {
        return this.aItemPoids;
    }//getItemPoids()
    
    /**
     * Permet d'accéder à toutes les informations de l'item.
     * @return envoie le nom,le poids et la description l'item.
     */
    public String getItemLongDescription()
    {
        return this.aItemNom+" : "+this.aItemDescription+"    Weight :"+this.aItemPoids;
    }
    
}
