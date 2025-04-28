
/**
 * Cette classe Door permet de créer une porte et de la manipuler
 *
 *@author KUCH
 */
public class Door
{
    
    /**
     * Permet de savoir si la porte est fermée.
     */
    private boolean aLock;
    
    /**
     * La porte ne peut être ouverte que par cette item.
     */
    private Item aItem;
    
    /**
     * Créer le porte,et l'item permettant de l'ouvrir et l'initialise fermée.
     */
    public Door()
    {
        this.aLock = true;
        this.aItem = new Item("Key","This Key open the door for the last part of the portal !",1);
    }
    
    /**
     * Ouvre la porte.
     */
    public void setDoorOpen()
    {
        this.aLock=false;
    }
    
    /**
     * Permet de savoir si la porte est fermée.
     * @return vrai si la porte n'est pas fermée
    */
    public boolean CanBeOpen()
    {
        return !(this.aLock);
    }
}
