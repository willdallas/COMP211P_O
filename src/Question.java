import java.io.File;
import java.util.ArrayList;

class Question {

    private String question;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;
    private ArrayList<String> allAnswers;

    Question(String questionInput, ArrayList<String> answersInput) {
        question = questionInput;
        allAnswers = answersInput;
        correctAnswer = answersInput.get(answersInput.size() - 1);

        answersInput.remove(answersInput.size() - 1);
        incorrectAnswers = answersInput;
    }

    String getQuestion() {
        return question;
    }

    ArrayList<String> getRandomAnswerArray() {

        ArrayList<String> nonRandomAnswerArray = allAnswers;
        ArrayList<String> randomAnswerArray = new ArrayList<String>();
        int randomInt;

        while (nonRandomAnswerArray.size() != 0) {
            randomInt = MiscFunctions.randomIntBetweenNumbers(0, nonRandomAnswerArray.size());
            randomAnswerArray.add(nonRandomAnswerArray.get(randomInt));
            nonRandomAnswerArray.remove(randomInt);
        }

        return randomAnswerArray;
    }

    public String toStringRandomized() {
        String questionString;
        ArrayList<String> randomAnswerArray = getRandomAnswerArray();

        questionString = ("\tWord: " + getQuestion() + "\n");
        for (int i = 0; i < randomAnswerArray.size(); i++) {
            questionString += ("\n\t(" + (i + 1) + ")  " + randomAnswerArray.get(i));
        }

        return questionString;
    }

    public String toString() {
        String questionString;

        questionString = ("\tWord: " + this.getQuestion());
        questionString += ("\n\n\t\tWrong answers: " + incorrectAnswers.get(0));
        questionString += (", " + incorrectAnswers.get(1));
        questionString += (", " + incorrectAnswers.get(2));
        questionString += ("\n\t\tCorrect answer: " + correctAnswer);

        return questionString;
    }


}
