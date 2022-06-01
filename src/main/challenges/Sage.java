package main.challenges;

import main.utils.Utils;
import main.visibles.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sage class.
 *
 * @author BOUDIER Maxime; BAYEN Maxime; FOURNIER Victor; DOSSA Josias
 */
public class Sage extends Challenge {

    private static final String[] possible_names = {
            "Merlin the Enchanter",
            "TicTac",
            "Dull Duck",
            "Forms",
            "Duduvet",
            "Krystofer"

    };

    private static final String[] possible_questions= {
            "How much is 5*9?",
            "Who is the winner of the 2018 football world cup?",
            "In which team does BENZEMA play?",
            "Pain au chocolat or chocolatine ?",
            "In what year is the new CNAM building due to open?",
            "How many people worked on this dungeon crawler?",
            "What is the name of the first president of the USA?",
            "How many countries are there in the world?",
            "What Jamy says when he is happy?",
            "How many stairs are there in the Eiffel Tower to go at the first level?",
            "In what year was the first world war?"
    };
    private static final String[][] possible_answers = {
            {"45", "54", "71", "69"},
            {"France", "Belgium", "Croatia", "Germany"},
            {"Real Madrid", "Paris-Saint-Germain" , "L'Olympique de Marseille", "FC Barcelone"},
            {"Pain au chocolat", "Chocolatine"},
            {"2022", "2021", "2024", "1415J.C."},
            {"4", "2.5", "3", "5"},
            {"George Washington", "John Adams", "Thomas Jefferson", "James Madison"},
            {"195", "1500", "120", "Approximately 2"},
            {"\"The joy\"", "\"I'm not happy\"", "\"Do you know my little puppy ?\"", "\"I'm your father\""},
            {"328", "215", "784", "512"},
            {"1914", "1918", "1939", "1944"}
    };

    private final String name;
    private final String question;
    private final String[] answers;
    private final String goodAnswer;


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

    public String getName() {
        return name;
    }

    /**
     * Question sage int.
     *
     * @return the int
     * @throws IOException the io exception
     */
    public int questionSage() throws IOException {
        String[] mixedAnswers = mixAnswers(answers);
        int index_goodAnswer = -1;
        for(int i = 0; i < mixedAnswers.length; i++){
            if(mixedAnswers[i].equals(goodAnswer)){
                index_goodAnswer = i;
            }
        }
        Menu questionning = new Menu(question, mixedAnswers);
        if(questionning.choose() == index_goodAnswer){
            return 1;
        }
        return -1;
    }

    private String[] mixAnswers(String[] answers){
        ArrayList<String> answersArray = new ArrayList<>();
        Collections.addAll(answersArray, answers);
        Collections.shuffle(answersArray);
        return answersArray.toArray(new String[answersArray.size()]);
    }
}
