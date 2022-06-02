package main.options;

/**
 * The type Statistics.
 */
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

    /**
     * Add enemy killed.
     */
    public void addEnemyKilled() {
        this.enemyKilled++;
    }

    /**
     * Add death.
     */
    public void addDeath() {
        this.death++;
    }

    /**
     * Add room visited.
     */
    public void addRoomVisited() {
        this.roomVisited++;
    }

    /**
     * Add sage question solved.
     */
    public void addSageQuestionSolved() {
        this.sageQuestionSolved++;
    }

    /**
     * Add damage dealt.
     *
     * @param damage the damage
     */
    public void addDamageDealt(int damage) {
        this.damageDealt += damage;
    }

    /**
     * Add damage taken.
     *
     * @param damage the damage
     */
    public void addDamageTaken(int damage) {
        this.damageTaken += damage;
    }

    /**
     * Add boss killed.
     */
    public void addBossKilled() {
        this.bossKilled++;
    }

    /**
     * Add weapons taken.
     */
    public void addWeaponsTaken() {
        this.weaponsTaken++;
    }

    /**
     * Add potion found.
     */
    public void addPotionFound() {
        this.potionFound++;
    }

    /**
     * Add trap found.
     */
    public void addTrapFound() {
        this.trapFound++;
    }

    /**
     * Gets enemy killed.
     *
     * @return the enemy killed
     */
    public int getEnemyKilled() {
        return enemyKilled;
    }

    /**
     * Gets death.
     *
     * @return the death
     */
    public int getDeath() {
        return death;
    }

    /**
     * Gets room visited.
     *
     * @return the room visited
     */
    public int getRoomVisited() {
        return roomVisited;
    }

    /**
     * Gets sage question solved.
     *
     * @return the sage question solved
     */
    public int getSageQuestionSolved() {
        return sageQuestionSolved;
    }

    /**
     * Gets damage dealt.
     *
     * @return the damage dealt
     */
    public int getDamageDealt() {
        return damageDealt;
    }

    /**
     * Gets damage taken.
     *
     * @return the damage taken
     */
    public int getDamageTaken() {
        return damageTaken;
    }

    /**
     * Gets boss killed.
     *
     * @return the boss killed
     */
    public int getBossKilled() {
        return bossKilled;
    }

    /**
     * Gets weapons taken.
     *
     * @return the weapons taken
     */
    public int getWeaponsTaken() {
        return weaponsTaken;
    }

    /**
     * Gets potion found.
     *
     * @return the potion found
     */
    public int getPotionFound() {
        return potionFound;
    }

    /**
     * Gets trap found.
     *
     * @return the trap found
     */
    public int getTrapFound() {
        return trapFound;
    }

    /**
     * Sets enemy killed.
     *
     * @param enemyKilled the enemy killed
     */
    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    /**
     * Sets death.
     *
     * @param death the death
     */
    public void setDeath(int death) {
        this.death = death;
    }

    /**
     * Sets room visited.
     *
     * @param roomVisited the room visited
     */
    public void setRoomVisited(int roomVisited) {
        this.roomVisited = roomVisited;
    }

    /**
     * Sets sage question solved.
     *
     * @param sageQuestionSolved the sage question solved
     */
    public void setSageQuestionSolved(int sageQuestionSolved) {
        this.sageQuestionSolved = sageQuestionSolved;
    }

    /**
     * Sets damage dealt.
     *
     * @param damageDealt the damage dealt
     */
    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }

    /**
     * Sets damage taken.
     *
     * @param damageTaken the damage taken
     */
    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    /**
     * Sets boss killed.
     *
     * @param bossKilled the boss killed
     */
    public void setBossKilled(int bossKilled) {
        this.bossKilled = bossKilled;
    }

    /**
     * Sets weapons taken.
     *
     * @param weaponFound the weapon found
     */
    public void setWeaponsTaken(int weaponFound) {
        this.weaponsTaken = weaponFound;
    }

    /**
     * Sets potion found.
     *
     * @param potionFound the potion found
     */
    public void setPotionFound(int potionFound) {
        this.potionFound = potionFound;
    }

    /**
     * Sets trap found.
     *
     * @param trapFound the trap found
     */
    public void setTrapFound(int trapFound) {
        this.trapFound = trapFound;
    }

    /**
     * Gets statistics.
     *
     * @return the statistics
     */
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
