import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Classe GameEngine- le moteur du jeu Wonders of the Broken Gate.
 *
 * @author Kuch
 * @version 7.26.1
 */
public class GameEngine
{
    /**
     * C'est parser
     */
    private Parser aParser;
    
    /**
     * Permet de créer une interface.
     */
    private UserInterface aGui;
    
    /**
     * Cette attribut permet de créer un joueur et d'avoir ces informations.
     */
    private Player aPlayer;
    
    /**
     * C'est la salle gagnante.
     */
    private Room aWinningRoom =new Room("You're Home, You Won.","image/home.png");
    /**
     * Constructeur par défaut de game,il permet de créer les salles et de lire le clavier.
     */
    public GameEngine()
    {
        this.createRooms();
        this.aParser = new Parser();
    }//Game()
    
    /**
     * Permet de définir le GUI.
     * @param pUserInterface l'interface du joueur.
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }//setGui(.)
    
    /**
     * createRooms a plusieurs fonctionnalités:
     * Il permet de créer des nouvelles rooms et de les relier.
     * Il permet aussi de créer des items liée à une salle precise.
     * Il permet aussi des créers des portes et les liées à des salles.
     */
    private void createRooms()
    {
        // creation des salles
        Room vDepart = new Room("in the spawn room.","image/salle_apparition.png");
        Room vPortail = new Room("in the portal room.","image/portail_casse.png");
        Room vEntreDonjon = new Room("at the entry of the dungeon.","image/entre_donjon.png");
        Room vAccueil1 = new Room("in the main room of the floor -1.","image/etage-1.png");
        Room vArmurerie = new Room("in a room with a lot of equipment","image/armurerie.png");
        Room vSallePorteF = new Room("in a room with a closed door.","image/salle_ferme_monstre.png");
        Room vSallePnj = new Room("in a room with someone else.","image/pnj_potion.png");
        Room vPortail1 = new Room("in a room with a monster to kill.","image/salle_monstre_portail.png");
        Room vAccueil2 = new Room("in the main room of the floor -2.","image/etage-2.png");
        Room vPiege = new Room("in a room with weird things written on the wall.","image/salle_ferme_enigme.png");
        Room vSensUnique = new Room("in a room and suddenly the path behind you closes.","image/salle_sens_unique.png");
        Room vPnjRaccourcis = new Room("in a room with someone interesting.","image/salle_pnj_raccourcis.png");
        Room vAccueil3 = new Room("in the main room of the floor -3.","image/etage-3.png");
        Room vSalleMonstre = new Room("in a room where you have to kill a monster.","image/salle_monstre_bloque.png");
        Room vSortieRaccourcis = new Room("in a room.","image/salle_vers_fin.png");
        Room vPortail2 = new Room("in a room with a part of the portal.","image/salle_portail2.png");
        Room vPortail3 = new Room("in a room with the last part of the portal.","image/bout_de_portail.png");
        
        
        
        //Positionnement des salles
        vDepart.setExit("east",vEntreDonjon);
        vDepart.setExit("north-east",vPortail);
        
        vPortail.setExit("south-west",vDepart);
        vPortail.setExit("south",vEntreDonjon);
        vPortail.setExit("home",aWinningRoom);
        
        vEntreDonjon.setExit("west",vDepart);
        vEntreDonjon.setExit("north",vPortail);
        vEntreDonjon.setExit("down",vAccueil1);
        
        vAccueil1.setExit("up",vEntreDonjon);
        vAccueil1.setExit("north-west",vArmurerie);
        vAccueil1.setExit("west",vSallePorteF);
        vAccueil1.setExit("east",vSallePnj);
        
        vArmurerie.setExit("south-east",vAccueil1);
        vArmurerie.setExit("south-west",vSallePorteF);
        
        vSallePorteF.setExit("north-east",vArmurerie);
        vSallePorteF.setExit("east",vAccueil1);
        vSallePorteF.setExit("south",vPortail3);

        vSallePnj.setExit("west",vAccueil1);
        vSallePnj.setExit("east",vPortail1);
        
        vPortail1.setExit("west",vSallePnj);
        vPortail1.setExit("down",vAccueil2);
        
        vAccueil2.setExit("up",vPortail1);
        vAccueil2.setExit("west",vPiege);
        vAccueil2.setExit("east",vSensUnique);
        vAccueil2.setExit("down",vAccueil3);
        
        vSensUnique.setExit("east",vPnjRaccourcis);
        
        vPnjRaccourcis.setExit("west",vSensUnique);
        
        vAccueil3.setExit("up",vAccueil2);
        vAccueil3.setExit("east",vSalleMonstre);
        
        vSalleMonstre.setExit("west",vAccueil3);
        vSalleMonstre.setExit("south-east",vSortieRaccourcis);
        
        vSortieRaccourcis.setExit("north-west",vSalleMonstre);
        vSortieRaccourcis.setExit("east",vPortail2);
        
        vPortail2.setExit("west",vSortieRaccourcis);
        
        vPortail3.setExit("north",vSallePorteF);
        
        //création des items
        Item vSword= new Item("LuminaraSword","A shimmering sword that glows like starlight, leaving a faint trail of light with every swing.",2);
        Item vBook = new Item("ElderscriptBook","A mysterious book that glows softly, its pages filled with shifting runes that seem to whisper ancient spells.",2);
        Item vSwordUpgrade = new Item("AstralythSword","A radiant sword that glows with the light of distant stars, leaving a trail of shimmering stardust with each swing.",4);
        Item vBookUpgrade = new Item("NexisBook","An ancient, glowing tome with shifting pages that whisper forgotten secrets.",4);
        Item vCookieMagique = new Item("VitalisCookie","A glowing, enchanted cookie that radiates warmth, said to instantly restore the vitality of those in need with its sweet, comforting taste.",1);
        Item vBoutPortail1= new Item("Portal1","First part of the broken portal",4);
        Item vBoutPortail2 = new Item("Portal2","Second part of the broken portal",4);
        Item vBoutPortail3 = new Item("Portal3","Last part of the broken portal",4);
        Beamer vBeamer = new Beamer("Beamer","A gun that can save a room and teleport you back to it.",2);
        Item vKey =new Item("Key","This Key open the door for the last part of the portal !",1);
        //Initialisation des items
        vDepart.addItem(vSword.getItemName(),vSword);
        vDepart.addItem(vBook.getItemName(),vBook);
        
        vPortail.addItem(vBeamer.getItemName(),vBeamer);
        
        vArmurerie.addItem(vSwordUpgrade.getItemName(),vSwordUpgrade);
        vArmurerie.addItem(vBookUpgrade.getItemName(),vBookUpgrade);
        vArmurerie.addItem(vKey.getItemName(),vKey);
        
        vSallePnj.addItem(vCookieMagique.getItemName(),vCookieMagique);
        
        vPortail1.addItem(vBoutPortail1.getItemName(),vBoutPortail1);
        
        vPortail2.addItem(vBoutPortail2.getItemName(),vBoutPortail2);
        
        vPortail3.addItem(vBoutPortail3.getItemName(),vBoutPortail3);
        
        //Initialisation des portes
        Door vPortal = new Door();
        vPortail.setDoor("home",vPortal);
        
        Door vDoor= new Door();
        vSallePorteF.setDoor("south",vDoor);
        //initialisation du lieu de départ pour le joueur.
        this.aPlayer = new Player("William",vDepart);
    }//createRoom()
    
    
    /**
     * Permet de se déplacer de room en room avec l'affichage de la description à chaque room parcourue. Avec l'options d'afficher des messages
     * d'erreur si la commande n'est pas reconnue ou null. Et traite aussi les portes et la salle gagnante.
     * @param pCommand est la commande rentrer par le joueur
     */
    private void goRoom(final Command pCommand)
    {
        Room vNextRoom = null;
        Door vDoor = null;
        
        String vDirection = pCommand.getSecondWord();
        vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
        vDoor =this.aPlayer.getCurrentRoom().getDoor(vDirection);
        
        //Afficher Go where si il y a un premier mot
        if(pCommand.hasSecondWord() == false){
        this.aGui.println("Go where?");
        return;
        }
        //Déterminer le lieu suivant
        
        if (vNextRoom == null) {
        this.aGui.println("There is no door!");
        return;
        }
        if(!this.aPlayer.CanMove())
        {
            this.aGui.println("You have no more action left !! GAME OVER");
            this.endGame();
        }
        else if(vDoor != null)
        {
            if(vNextRoom==aWinningRoom)
            {
                if(vDoor.CanBeOpen())
                {
                    this.aPlayer.moveTo(vNextRoom);
                    this.aGui.println("\n");
                    this.aGui.println("YOUU WOONNN !!");
                    if ( this.aPlayer.getCurrentRoom().getImageName() != null )
                    this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
                    this.endGame();
                    return;
                }
                else
                {
                    if(this.CanOpenPortal())
                    {
                        vDoor.setDoorOpen();
                        this.aGui.println("The portal is now open you can go home.");
                        return;
                    }
                    else{
                        this.aGui.println("The portal is close.You need all the pieces of the portal");
                        return;
                    }
                }
            }
            
            if(vDoor.CanBeOpen())
            {
                this.aGui.println("The door is open.");
                this.aPlayer.moveTo(vNextRoom);
                this.aGui.println("\n");
                this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
                if ( this.aPlayer.getCurrentRoom().getImageName() != null )
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
            }
            else
            {
                if(this.aPlayer.getCurrentRoom().getItemList().containsItem("Key"))
                {
                vDoor.setDoorOpen();
                this.aGui.println("The door is now open you can go.");
                }
                else{
                this.aGui.println("The door is close.Try to drop a key in the room then retry.");
                }
            }
        }
        
        else {
            this.aPlayer.moveTo(vNextRoom);
            this.aGui.println("\n");
            this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
            if ( this.aPlayer.getCurrentRoom().getImageName() != null )
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
        }
    }//goRoom()
    
    /**
     * Une pile stocke l'historique des salles parcourues.
     * Et   back() permet de revenir en arrière à l'aide de la pile.
     * Il y a une exception lorsque la salle actuelle est une trapdoor,on ne peut pas retourner en arrière.
     */
    private void back()
    {
        if(!this.aPlayer.isEmpty())
        {
            if(this.aPlayer.isTrapdoor())
            {
                this.aGui.println("You cannot go back the door is closed");
            }
            else{
            this.aPlayer.setCurrentRoom(this.aPlayer.getPreviousRoom().pop());
            this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() ); 
            if(this.aPlayer.getCurrentRoom().getImageName() != null)
            {
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
            }
            }
        }
        else{
            this.aGui.println("There is no previous room!");
        }
    }//back()
    
    /**
     * Permet d'afficher la description de la salle actuelle avec les sorties possibles.
     * @param pCommand Commande permettant d'avoir le lieu actuel.
     */
    private void printLocationInfo(final Command pCommand)
    {
        look(pCommand);
        if ( this.aPlayer.getCurrentRoom().getImageName() != null )
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
    }//printLocationInfo()
    //Suite du tp3.2
    /**
     * Permet d'afficher un message de bienvenue.
     */
    private void printWelcome()
    {   
        this.aGui.println("");
        this.aGui.println("Welcome to Wonders of the Broken Gate, traveler.\n"+"Cast into an unknown world,your ingenuity is your only hope.\n"
        +"Venture deep into the forgotten dungeon,uncover its secrets,and gather the shards to rebuild the portal.\n"
        +"Your journey home begins now.");
        this.aGui.println("Type 'help' if you need help.\n");
        Command look = new Command("look",null);
        printLocationInfo(look);
    }//printWelcome()
    
    /***
     * Permet d'afficher un message d'aide.
     */
    private void printHelp()
    {
        this.aGui.println("\n");
        this.aGui.println("Vos commandes sont:\n"+this.aParser.getCommandString());
        this.aGui.println(this.aPlayer.getCurrentMoves()+"/"+this.aPlayer.getMaxMoves());
    }//printHelp()
    
    /**
     * Permet de quitter le jeu.
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }//quit()
    
    /**
     * Permet de reconnaitre la commande et l'utiliser.
     * @param pCommand une commande rentrée par le joueur.
     */
    public void interpretCommand(final String pCommand)
    {
        this.aGui.println( "> " + pCommand );
        Command vCommand = this.aParser.getCommand(pCommand);

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "help" ) )
            this.printHelp();
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                this.endGame();
        }
        else if(vCommandWord.equals( "look" ))
        {
            this.look(vCommand);
        }
        else if(vCommandWord.equals( "eat" ))
        {
            this.eat(vCommand);
        }
        else if(vCommandWord.equals("test"))
        {
            this.aGui.println("We are testing");
            this.test(vCommand);
        }
        else if(vCommandWord.equals("take"))
        {
            this.take(vCommand);
        }
        else if(vCommandWord.equals("drop"))
        {
            this.drop(vCommand);
        }
        else if(vCommandWord.equals("inventory"))
        {
            this.inventory();
        }
        else if(vCommandWord.equals("charge"))
        {
            this.charge(vCommand);
        }
        else if(vCommandWord.equals("fire"))
        {
            this.fire(vCommand);
        }
        else if(vCommandWord.equals("back"))
        {
            if ( vCommand.hasSecondWord() )
                {this.aGui.println( "Back where?" );}
            else
                {
                    this.back();
                }
        }
    }//processCommand()
    
    /**
     * Permet d'afficher la description dans la salle où l'on est si la commande rentrait est "look".
     * @param pCommandLook est une commande de l'utilisateur(ex: look,look down)
     */
    private void look(final Command pCommandLook)
    {
        if(pCommandLook.getSecondWord()!=null)
        {
            this.aGui.println("look what");
        }
        else{
            this.aGui.println("\n");
            this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
        }
    }//look(.)
    
    /**
     * Permet de manger le cookie magique qui augmente la taille de son inventaire.
     * @param pCommand action donner par l'utilisateur.
     */
    private void eat(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("Eat what ?");
            return;
        }
        String vItemName = pCommand.getSecondWord();
        Item vItemEat = this.aPlayer.getCurrentRoom().getItem(vItemName);
        if(vItemEat == null)
        {
            this.aGui.println("You can't eat this");
        }
        else if(vItemName.equals("VitalisCookie"))
        {
            this.aPlayer.getCurrentRoom().removeItem(vItemName);
            this.aPlayer.setMaxWeight(this.aPlayer.getMaxWeight() + 5);
            this.aGui.println("You've eaten the "+vItemName+".\n"+"You are stronger,you can now lift: "+this.aPlayer.getMaxWeight());
        }
        else{
            this.aGui.println("You can't eat this");
        }
    }//eat()
    
    /**
     * Permet de tester des actions dans un fichier .txt
     * @param pCommand la commande sera "test nom_du_fichier"
     */
    private void test(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("What do you want to test ?");
            return;
        }
        String vNomFichier = pCommand.getSecondWord();
        if(!vNomFichier.contains(".txt") ) 
        {
            vNomFichier+=".txt";
        }
        try{
            Scanner vScan = new Scanner( new File(vNomFichier) );
            while(vScan.hasNextLine())
            {
                String vLine = vScan.nextLine();
                interpretCommand(vLine);
            }
            }
        catch(final FileNotFoundException pException)
        {
            this.aGui.println("Sorry, the file" + vNomFichier + "not found");
        }
    }
    
    /**
     * Permet de prendre un objet de la salle dans son inventaire.
     * @param pCommand exemple:"take nom_objet"
     */
    private void take(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("What do you want to take ?\n");
            return;
        }
        String vItemName = pCommand.getSecondWord();
        Item vItemToTake = this.aPlayer.getCurrentRoom().getItem(vItemName);
        if(vItemToTake == null)
        {
            this.aGui.println("This item is not here !\n");
        }
        else if(this.aPlayer.CanBeTake(vItemToTake))
        {
            this.aPlayer.takeItemPlayer(vItemName,vItemToTake);
            this.aPlayer.getCurrentRoom().removeItem(vItemName);
            this.aGui.println("You took the item: "+vItemName);
        }
        else{
            this.aGui.println("You're overload, you cannot take more items");
        }
    }
    
    /**
     * Permet de laisser un objet de son inventaire dans la salle actuelle du joueur.
     * @param pCommand exemple:"drop nom_objet"
     */
    private void drop(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("What do you want to drop ?\n");
            return;
        }
        String vItemName = pCommand.getSecondWord();
        Item vItemToDrop = this.aPlayer.getItemPlayer(vItemName);
        if(vItemToDrop == null)
        {
            this.aGui.println("You don't have this item !\n");
        }
        else
        {
            this.aPlayer.getCurrentRoom().addItem(vItemName,vItemToDrop);
            this.aPlayer.DropItemPlayer(vItemName);
            this.aGui.println("You've dropped "+vItemName);
        }
    }
    /**
     * Affiche l'inventaire du joueur.
     */
    public void inventory()
    {
        this.aGui.println(this.aPlayer.PlayerInventory()+"\n"+"Total Weight :" +this.aPlayer.getCurrentWeight() );
    }
    
    /**
     * Permet d'enregistrer la salle dans le beamer.
     * @param pCommand la commande.
     */
    private void charge(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("What do you want to charge ?");
            return;
        }
        
        String vStringBeamer = pCommand.getSecondWord();
        Beamer vBeamer =(Beamer)this.aPlayer.getItemPlayer(vStringBeamer);
        
        if(vBeamer == null)
        {
            this.aGui.println("You dont have the beamer !");
        }
        else{
            vBeamer.charge(this.aPlayer.getCurrentRoom());
            this.aGui.println("The beamer is charged !");
        }
    }//charge(.)
    
    /**
     * Permet de tirer avec le Beamer si il a une charge et une salle sauvgardé.
     * @param pCommand la commande.
     */
    public void fire(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("What do you want to fire ?");
            return;
        }
        
        String vStringBeamer = pCommand.getSecondWord();
        Beamer vBeamer =(Beamer)this.aPlayer.getItemPlayer(vStringBeamer);
        
        if(vBeamer == null)
        {
            this.aGui.println("You dont have the beamer !");
        }
        else{
            if(vBeamer.HasCharge() && vBeamer.isCharge() )
            {
                vBeamer.fire();
                this.aPlayer.setCurrentRoom(vBeamer.getRoomSave());
                this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription());
                if(this.aPlayer.getCurrentRoom().getImageName() != null)
                {
                this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
                }
            }
            else{
                this.aGui.println("You cannot fire because you dont any charge left or you didn't charge the beamer!");
            }
        }
    }
    
    /**
     * Permet de savoir si la salle actuel du joueur contient les 3 bouts de portail.
     * @return vrai si la salle du joueur contient les 3 bouts du portail.
     */
    private boolean CanOpenPortal()
    {
        return this.aPlayer.getCurrentRoom().getItemList().containsItem("Portal1") && this.aPlayer.getCurrentRoom().getItemList().containsItem("Portal2") &&this.aPlayer.getCurrentRoom().getItemList().containsItem("Portal3");
    }
}

