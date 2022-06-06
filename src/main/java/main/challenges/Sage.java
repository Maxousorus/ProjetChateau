package main.challenges;

import main.utils.Utils;
import main.visibles.Menu;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The class Sage class.
 * Is a subclass of the Challenge class.
 */
public class Sage extends Challenge {

    private static final String[] possible_names = {
            "Merlin the Enchanter",
            "TicTac",
            "Dull Duck",
            "Forms",
            "Duduvet",
            "Krystofer",
            "Frederica",
            "Orlando",
            "Marianne",
            "Kaneki",
            "Sophia",
            "Luna",
            "Marcel",
            "Lili",
            "Casper",
            "Bruno",
            "Lara Croft"
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
            "In what year was the first world war?",
            "In which country can we find the Catalonia, the Andalusia and the Castile?",
            "What's the name of the plaqueminier's fruit ?",
            "What series of six films is a boxing champion the star of?",
            "What is the capital of New Zealand?",
            "What is the name of the word processing software developed by Microsoft?",
            "What animal class does the scorpion belong to?",
            "What is the only value to the roulette to be green?",
            "What is the smallest memory unit that can be used on a computer?",
            "How long is the term of the President of the United States?",
            "Which animal is the fastest on land?",
            "How do you measure the depth of the oceans?",
            "In which adventures do we find the characters of Lois and Clark?",
            "What was the name of the mission during which Neil Armstrong made the first steps on the Moon in 1969?",
            "What is the name of the school where the adventures of Harry Potter take place?"
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
            {"1914", "1918", "1939", "1944"},
            {"L'Espagne", "L'Italie", "L'Allemagne", "L'Ecosse"},
            {"Kaki", "Kakou", "Kakao", "Klik"},
            {"Rocky", "Rambo", "Randy", "Ritchie"},
            {"Wellington", "Auckland", "Christchurch", "Hamilton"},
            {"Microsoft Word", "Microsoft Excel", "Microsoft PowerPoint", "Microsoft Access"},
            {"Arachnid", "Mammal", "Reptile", "Insect"},
            {"0", "40", "13", "27"},
            {"Bit", "Byte", "Kilobyte", "GigaByte"},
            {"4yo", "5yo", "6yo", "7yo"},
            {"Cheetah", "Lion", "Tiger", "Elephant"},
            {"With a sonar", "With a compass", "With a GPS", "With a Nokia 3310"},
            {"Superman", "Batman", "Spider-man", "Wonder Woman"},
            {"Apollo 11", "Apollo 13", "Objective Moon", "Apollo 1"},
            {"Poudlard", "Azkaban", "Nurmengard", "CNAM"}
    };

    private final String name;
    private final String question;
    private final String[] answers;
    private final String goodAnswer;


    /**
     * Constructor of the class Sage.
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
     * Get the name of the Sage.
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Ask of the Sage.
     *
     * @return Int index of the answer choose.
     */
    public int questionSage() {
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
