
/**
 *Beamer est un item qui permet de se téléporter à l'endroit qu'on a sauvgardé.
 *
 *@author KUCH
 * 
 */
public class Beamer extends Item
{
    /**
     * Le nombre de charge que Beamer a.
     */
    private int aCharge;
    
    /**
     * La salle sauvgardé.
     */
    private Room aRoomSave;
    
    /**
     * Permet de créer le beamer.
     * @param pNom le nom de l'item donc Beamer.
     * @param pDescription la description de Beamer.
     * @param pPoids le poids de Beamer.
     */
    public Beamer(final String pNom,final String pDescription,final int pPoids)
    {
        super(pNom,pDescription,pPoids);
        Room aRoomSave;
        this.aCharge =1;
    }
    
    /**
     * Permet de savoir si il reste une charge.
     * @return vrai si il en reste une.
     */
    public boolean HasCharge()
    {
        return !(this.aCharge==0);
    }
    
    /**
     * Permet de savoir si on a sauvgardé la salle.
     * @return vrai si oui.
     */
    public boolean isCharge()
    {
        return !(this.aRoomSave==null);
    }
    
    /**
     * Permet de sauvgarder la salle.
     * @param pRoom La salle qu'on veut enregistrer.
     */
    public void charge(final Room pRoom)
    {
        this.aRoomSave = pRoom;
    }
    
    /**
     * Permet de tirer et donc de vider le chargeur.
     */
    public void fire()
    {
        this.aCharge -=1;
    }
    
    /**
     * Permet de savoir la salle qu'on a enregistré.
     * @return La salle enregistré.
     */
    public Room getRoomSave()
    {
        return this.aRoomSave;
    }
}