import java.util.HashMap;
import java.util.Set;

/**
 * Créer une liste d'item et permet toutes sortes de manipulations dessus.
 *
 * @author KUCH
 */
public class ItemList
{
    /**
     * Une liste aillant comme clé le nom d'un item et comme valeur l'item
     */
    private HashMap<String,Item> aInventory;
    
    /**
     * Contructeur par défaut, il permet de créer la liste d'item.
     */
    public ItemList()
    {
        this.aInventory = new HashMap<String,Item>();
    }
    
    /**
     * Afficher la liste d'item.
     * @return une phrase avec la liste d'items dedans.
     */
    public String getItemListString()
    {
        String vReturnString = "Inventory : ";
        Set<String> vKeys = this.aInventory.keySet();
        for(String vI: vKeys)
        {
            Item vItem = this.aInventory.get(vI);
            vReturnString += vItem.getItemName()+" ";
        }
        return vReturnString;
    }
    
    /**
     * Similaire à getItemListString mais avec plus d'informations.
     * @return pareil sauf avec la description et le poids en plus.
     */
    public String getItemListDescriptionString()
    {
        String vReturnString = "Inventory : \n";
        Set<String> vKeys = this.aInventory.keySet();
        for(String vI: vKeys)
        {
            Item vItem = this.aInventory.get(vI);
            vReturnString += vItem.getItemLongDescription()+"\n";
        }
        return vReturnString;
    }
    
    /**
     * Ajoute un item à la liste.
     * @param pN nom de l'item
     * @param pI l'item
     */
    public void takeItemList(final String pN,final Item pI)
    {
        this.aInventory.put(pN,pI);
    }
    
    /**
     * Opposé à take il supprime un item de la liste.
     * @param pN nom de l'item
     */
    public void removeItemList(final String pN)
    {
        this.aInventory.remove(pN);
    }
    
    /**
     * Permet d'accéder à un item de la liste.
     * @param pN nom de l'item
     * @return envoie l'item demandé.
     */
    public Item getItemList(final String pN)
    {
        return this.aInventory.get(pN);
    }
    
    /**
     * Permet de savoir si un item est dans la liste.
     * @param pItem l'item demandé.
     * @return vrai si l'item est dans la liste.
     */
    public boolean containsItem(final String pItem)
    {
        return this.aInventory.containsKey(pItem);
    }
}
