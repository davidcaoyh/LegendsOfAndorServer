package LoAServer;

enum CreatureType {
    GOR, SKRAL, TROLL, WARDRAKS, SKRAL_BOSS
}


public class Creature {
    private int strength;
    private int willpower;
    private int goldReward;
    private int willpowerReward;
    private CreatureType creatureType;
    private Item medicinalHerb;

    public Creature() {}

    public Creature(CreatureType creatureType) {
        this.creatureType = creatureType;
        if (creatureType == CreatureType.GOR) {
            strength = 2;
            willpower = 4;
            goldReward = 2;
            willpowerReward = 2;
        } else if (creatureType == CreatureType.SKRAL) {
            strength = 6;
            willpower = 6;
            goldReward = 4;
            willpowerReward = 4;
        } else if (creatureType == CreatureType.TROLL) {
            strength = 14;
            willpower = 12;
            goldReward = 6;
            willpowerReward = 6;
        } else { // wardraks
            strength = 10;
            willpower = 7;
            goldReward = 6;
            willpowerReward = 6;
        }
    }

    public Creature(boolean difficult, int totalPlayers) {
        creatureType = CreatureType.SKRAL_BOSS;
        if (difficult) {
            if (totalPlayers == 2) {
                strength = 20;
            } else if (totalPlayers == 3) {
                strength = 30;
            } else { // == 4
                strength = 40;
            }
        } else {
            if (totalPlayers == 2) {
                strength = 10;
            } else if (totalPlayers == 3) {
                strength = 20;
            } else { // == 4
                strength = 30;
            }
        }
        willpower = 6;
        goldReward = 6;
        willpowerReward = 6;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public int getWillpowerReward() {
        return willpowerReward;
    }

    public void setWillpowerReward(int willpowerReward) {
        this.willpowerReward = willpowerReward;
    }

    public CreatureType getCreatureType() {
        return creatureType;
    }

    public void setCreatureType(CreatureType creatureType) {
        this.creatureType = creatureType;
    }

    public Item getMedicinalHerb() {
        return medicinalHerb;
    }

    public void setMedicinalHerb(Item medicinalHerb) {
        this.medicinalHerb = medicinalHerb;
    }
}
