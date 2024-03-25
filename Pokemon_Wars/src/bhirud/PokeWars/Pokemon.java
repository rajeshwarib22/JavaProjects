package bhirud.PokeWars;

import java.util.Random;

/**
 *
 * @author : Rajeshwari Bhirud
 * Date : 02/29/2024
 * Description the project is about 
 * Pokémon who are mythical creatures, from Earth, 
 * who generally enjoy fighting each other for sport.
 */
public class Pokemon {
    private String name;
    private String creatureType;
    private int creatureValue;
    private int maxHealth;
    private int currentHealth;
    private int attackValue;
    private int defenseValue;
    private int wins;
    private int losses;
    private int draws;
    
    
    // Getter and Setter methods for the class Pokemon attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatureType() {
        return creatureType;
    }

    public void setCreatureType(String creatureType) {
        this.creatureType = creatureType;
    }

    public int getCreatureValue() {
        return creatureValue;
    }

    public void setCreatureValue(int creatureValue) {
        this.creatureValue = getMaxHealth();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }
    
    // Constructors for class pokemon

    public Pokemon() {
        
    }
    
    
    public Pokemon(String name, String creatureType, int creatureValue, int maxHealth, int attackValue, int defenseValue) {
        this.name = name;
        this.creatureType = creatureType;
        this.creatureValue = creatureValue;
        this.maxHealth = maxHealth;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;

    }

    @Override
    public String toString() {
        return "Name : " + name + 
                "\nType : " + creatureType + 
                "\nValue : " + creatureValue + 
                "\nMax Health : " + maxHealth + 
                "\nCurrent Health : " + currentHealth +
                "\nAttack Value : " + attackValue + 
                "\nDefense Value : " + defenseValue + 
                "\nWINS : " + wins + " LOOSES : " + losses + " DRAWS : " + draws;
    }
    
    // Pokemon Class Methods
    public void displayPokemon(){
        System.out.println(toString());
    }
    
    public void fight(Pokemon p2){
        Random random = new Random();
        
        this.currentHealth = this.getMaxHealth();
        p2.currentHealth = this.getMaxHealth();
        System.out.println("\n********************** WAR STARTS ******************************************");
        
        for (int round = 1; round <= 4; round++) {
            // Alternate attacks and defenses
            System.out.println(this.name+" attacks "+p2.name);
            if (round % 2 != 0) {
                // This Pokemon attacks
                int attackDamage = Math.abs(this.attackValue - (int) (p2.defenseValue * (random.nextDouble() + 0.5)));
                p2.currentHealth -= attackDamage;
                System.out.println("\t"+p2.name+" suffers "+ attackDamage +" points of damage");
            } else {
                // Opponent attacks
                int attackDamage = Math.abs(p2.attackValue - (int) (this.defenseValue * (random.nextDouble() + 0.5)));
                this.currentHealth -= attackDamage;
                System.out.println("\t"+this.name+" suffers "+ attackDamage +" points of damage");
            }
        }   
            
        // to check who won , lost or if its a draw
        if (this.currentHealth > p2.currentHealth) {
             System.out.println("" + this.name + " wins**");
            this.wins++;
            p2.losses++;

        }else if (p2.currentHealth > this.currentHealth){
            System.out.println("" + p2.name + " wins**");
            p2.wins++;
            this.losses++;
        }else{
            this.draws++;
            p2.draws++;
        } 
        System.out.println(this.name + " CURRENT HEALTH : " + this.currentHealth);
        System.out.println("WINS : " + this.wins + " LOOSES : " + this.losses + " DRAWS : " + this.draws);

        System.out.println(p2.name+ " CURRENT HEALTH : "+ p2.currentHealth);
        System.out.println("WINS : " + p2.wins + " LOOSES : " + p2.losses + " DRAWS : " + p2.draws);
        
        System.out.println("\n\n********************** WAR ENDS ******************************************");
    }
    
    
    public static void main(String[] args){
        Pokemon p1 = new Pokemon("Charmy", "Charmander", 100, 12, 3, 4);
        Pokemon p2 = new Pokemon("Flare", "Charmeleon", 100, 10, 4, 4);
        
        for(int i=0; i< 3; i++){
            p1.fight(p2);
        }
        
    }
    
}
