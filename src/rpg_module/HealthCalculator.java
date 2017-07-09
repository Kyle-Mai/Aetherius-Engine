package rpg_module;

/*
Lolita's Revenge
July 08 2017

Simple calculator for RPG-style health systems.
*/

public class HealthCalculator {

    /*------------------------------------------------------------------------------------------------------------------
     Variables.
     Used to determine the function of the class.
     */

    private int health_total, shields_total, armour_total;
    private int health, shields, armour;
    private double h_recovery = 6.5, s_recovery = 13.0, a_recovery = 4.0;
    private boolean health_regen = false;
    private boolean shield_regen = true;
    private boolean armour_regen = false;

    /*------------------------------------------------------------------------------------------------------------------
     Constructors.
     Used to construct instances of the class
     */

    public HealthCalculator() {}

    /*------------------------------------------------------------------------------------------------------------------
     Accessible methods.
     Methods that can be accessed from outside of the class
     */

    public void setHealth(int i) { health = i; }
    public void setShields(int i) { shields = i; }
    public void setArmour(int i) { armour = i; }
    public int getHealth() { return health; }
    public int getShields() { return shields; }
    public int getArmour() { return armour; }

    public void setTotalHealth(int i) { health_total = i; }
    public void setTotalShields(int i) { shields_total = i; }
    public void setTotalArmour(int i) { armour_total = i; }
    public int getTotalHealth() { return health_total; }
    public int getTotalShields() { return shields_total; }
    public int getTotalArmour() { return armour_total; }

    public void setHealthRecovery(double d) { h_recovery = d; }
    public void setShieldRecovery(double d) { s_recovery = d; }
    public void setArmourRecovery(double d) { a_recovery = d; }
    public double getHealthRecovery() { return h_recovery; }
    public double getShieldRecovery() { return s_recovery; }
    public double getArmourRecovery() { return a_recovery; }

    public void setHealthRegen(boolean b) { health_regen = b; }
    public void setShieldRegen(boolean b) { shield_regen = b; }
    public void setArmourRegen(boolean b) { armour_regen = b; }
    public boolean isHealthRegen() { return health_regen; }
    public boolean isShieldRegen() { return shield_regen; }
    public boolean isArmourRegen() { return armour_regen; }

    public void regen_health() { regen_health(1.0); }
    public void regen_health(double multiplier) { health += ((health_total / 100) * h_recovery) * multiplier; }

    public void regen_shields() { regen_shields(1.0); }
    public void regen_shields(double multiplier) { shields += ((shields_total / 100) * s_recovery) * multiplier; }

    public void regen_armour() { regen_armour(1.0); }
    public void regen_armour(double multiplier) { armour += ((armour_total / 100) * a_recovery) * multiplier; }

    //------------------------------------------------------------------------------------------------------------------

}
