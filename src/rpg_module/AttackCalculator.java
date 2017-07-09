package rpg_module;

/*
Lolita's Revenge
July 08 2017

RPG module for storing potential attacks.
*/

public class AttackCalculator {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Used to determine the function of the class.
     */

    private String name, desc, type;
    private int damage;
    private double health_multiplier, shield_multiplier, armour_multiplier;

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the class
     */

    public AttackCalculator() {}

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Methods that can be accessed from outside of the class
     */

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public String getType() { return type; }
    public void setName(String s) { name = s; }
    public void setDesc(String s) { desc = s; }
    public void setType(String s) { type = s; }

    public int getDamage() { return damage; }
    public void setDamage(int i) { damage = i; }

    public double getHealthMultiplier() { return health_multiplier; }
    public double getShieldMultiplier() { return shield_multiplier; }
    public double getArmourMultiplier() { return armour_multiplier; }
    public void setHealthMultiplier(double d) { health_multiplier = d; }
    public void setShieldMultiplier(double d) { shield_multiplier = d; }
    public void setArmourMultiplier(double d) { armour_multiplier = d; }

    //------------------------------------------------------------------------------------------------------------------

}
