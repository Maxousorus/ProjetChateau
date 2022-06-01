package main.options;

public abstract class Statistics {

    private int enemyKilled;
    private int death;
    private int roomVisited;
    private int sageQuestionSolved;
    private int damageDealt;
    private int damageTaken;
    private int bossKilled;
    private int weaponsTaken;
    private int potionFound;
    private int trapFound;

    /**
     * Instantiates a new Statistics.
     */
    public Statistics() {
        this.enemyKilled = 0;
        this.death = 0;
        this.roomVisited = 0;
        this.sageQuestionSolved = 0;
        this.damageDealt = 0;
        this.damageTaken = 0;
        this.bossKilled = 0;
        this.weaponsTaken = 0;
        this.potionFound = 0;
        this.trapFound = 0;
    }

    public void addEnemyKilled() {
        this.enemyKilled++;
    }

    public void addDeath() {
        this.death++;
    }

    public void addRoomVisited() {
        this.roomVisited++;
    }

    public void addSageQuestionSolved() {
        this.sageQuestionSolved++;
    }

    public void addDamageDealt(int damage) {
        this.damageDealt += damage;
    }

    public void addDamageTaken(int damage) {
        this.damageTaken += damage;
    }

    public void addBossKilled() {
        this.bossKilled++;
    }

    public void addWeaponsTaken() {
        this.weaponsTaken++;
    }

    public void addPotionFound() {
        this.potionFound++;
    }

    public void addTrapFound() {
        this.trapFound++;
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public int getDeath() {
        return death;
    }

    public int getRoomVisited() {
        return roomVisited;
    }

    public int getSageQuestionSolved() {
        return sageQuestionSolved;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public int getBossKilled() {
        return bossKilled;
    }

    public int getWeaponsTaken() {
        return weaponsTaken;
    }

    public int getPotionFound() {
        return potionFound;
    }

    public int getTrapFound() {
        return trapFound;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public void setRoomVisited(int roomVisited) {
        this.roomVisited = roomVisited;
    }

    public void setSageQuestionSolved(int sageQuestionSolved) {
        this.sageQuestionSolved = sageQuestionSolved;
    }

    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }

    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    public void setBossKilled(int bossKilled) {
        this.bossKilled = bossKilled;
    }

    public void setWeaponsTaken(int weaponFound) {
        this.weaponsTaken = weaponFound;
    }

    public void setPotionFound(int potionFound) {
        this.potionFound = potionFound;
    }

    public void setTrapFound(int trapFound) {
        this.trapFound = trapFound;
    }

    public String getStatistics() {
        return  Parameters.COLOR_1 + "Enemy killed : " + Parameters.SELECTED_COLOR + this.enemyKilled + "\n"
                + Parameters.COLOR_2 + "Death : " + Parameters.SELECTED_COLOR + this.death + "\n"
                + Parameters.COLOR_1 + "Room visited : " + Parameters.SELECTED_COLOR + this.roomVisited + "\n"
                + Parameters.COLOR_2 + "Sage question solved : " + Parameters.SELECTED_COLOR + this.sageQuestionSolved + "\n"
                + Parameters.COLOR_1 + "Damage dealt : " + Parameters.SELECTED_COLOR + this.damageDealt + "\n"
                + Parameters.COLOR_2 + "Damage taken : " + Parameters.SELECTED_COLOR + this.damageTaken + "\n"
                + Parameters.COLOR_1 + "Boss killed : " + Parameters.SELECTED_COLOR + this.bossKilled + "\n"
                + Parameters.COLOR_2 + "Weapon found : " + Parameters.SELECTED_COLOR + this.weaponsTaken + "\n"
                + Parameters.COLOR_1 + "Potion found : " + Parameters.SELECTED_COLOR + this.potionFound + "\n"
                + Parameters.COLOR_2 + "Trap found : " + Parameters.SELECTED_COLOR + this.trapFound + "\n";
    }
}
