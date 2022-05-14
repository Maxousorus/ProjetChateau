package main.challenges;

/**
 * Sage class.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Sage extends Challenge {

    private String[] question = {
            "Combien font 5*9 ?",
            "Je te donne 15 euros, je t'en prends 3, ensuite tu payes les impots, combien te reste-t-il ?",
            "Dans quelle équipe joue BENZEMA ?",
            "Quelle est la meilleur note de josias ?",
            "Pain au chocolat ou chocolatine ?"

    };
    private String[] answer = {
            "45",
            "0",
            "Real Madrid",
            "9",
            "Pain au chocolat"
    };

    public Sage(){
        super();
        this.question = question;
        this.answer = answer;
    }
}
