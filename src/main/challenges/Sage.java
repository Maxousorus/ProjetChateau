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

    private final String[] possible_names = {
            "Merlin l'Enchanteur",
            "TicTac",
            "Canard Boiteux",
            "Forms",
            "Couecouette",
            "Krystofer"

    };

    private final String[] possible_questions= {
            "Combien font 5*9 ?",
            "Quel est le gagnant de la coupe du monde de football en 2018 ?",
            "Dans quelle équipe joue BENZEMA ?",
            "Quelle est la meilleur note de josias ?",
            "Pain au chocolat ou chocolatine ?",
            "En quelle année le nouveau bâtiment du CNAM est censé ouvrir ?"

    };
    private final String[][] possible_answers = {
            {"45", "54", "71", "69"},
            {"France", "Belgique", "Croatie", "Allemagne"},
            {"Real Madrid", "Paris-Saint-Germain" , "L'Olympique de Marseille", "FC Barcelone"},
            {"9"},
            {"Pain au chocolat", "Chocolatine"},
            {"2022", "2021", "2024", "1415 AV-JC"}
    };

    private String name;
    private String question;
    private String[] answers;
    private String goodAnswer;



    /**
     * Instantiates a new Sage.
     */
    public Sage(){
        super();
        this.name = possible_names[Utils.randomInt(0,possible_names.length-1)];
        int random = Utils.randomInt(0,possible_questions.length-1);
        this.question = possible_questions[random];
        this.answers = possible_answers[random];
        this.goodAnswer = answers[0];
    }

    /**
     * Get question string [ ].
     *
     * @return the string [ ]
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Get answer string [ ].
     *
     * @return the string [ ]
     */
    public String[] getAnswers(){
        return answers;
    }

    /**
     * Question sage int.
     *
     * @return the int
     * @throws IOException the io exception
     */

    public int questionSage() throws IOException {
        int reponse;
        String[] mixedAnswers = mixAnswers(answers);
        int index_goodAnswer = -1;
        for(int i = 0; i < mixedAnswers.length; i++){
            if(mixedAnswers[i].equals(goodAnswer)){
                index_goodAnswer = i;
            }
        }
        Menu choixanswer = new Menu(question, answers);
        reponse = choixanswer.choose();
        if(reponse == index_goodAnswer){
            return 1;
        }
        return -1;
    }

    private String[] mixAnswers(String[] answers){
        String[] mixedAnswers = new String[answers.length];
        for(int i = 0; i < answers.length; i++){
            int random = Utils.randomInt(0,answers.length-1);
            mixedAnswers[i] = answers[random];
            answers[random] = answers[i];
        }
        return mixedAnswers;
    }
}
