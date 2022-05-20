package main.challenges;

import main.utils.Utils;
import main.visibles.Menu;

import java.io.IOException;

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

    /**
     * Instantiates a new Sage.
     */
    public Sage(){
        super();
        this.question = question;
        this.answer = answer;
    }

    /**
     * Get question string [ ].
     *
     * @return the string [ ]
     */
    public String[] getQuestion() {
        return question;
    }

    /**
     * Get answer string [ ].
     *
     * @return the string [ ]
     */
    public String[] getAnswer(){
        return answer;
    }

    /**
     * Question sage int.
     *
     * @return the int
     * @throws IOException the io exception
     */
    public int questionSage() throws IOException {
        int random = Utils.randomInt(0,question.length);
        int reponse;
        Menu choixanswer = new Menu(question[random], answer);
        reponse = choixanswer.choose();
        if(random == reponse){
            return 1;
        }
        return -1;
    }
}
