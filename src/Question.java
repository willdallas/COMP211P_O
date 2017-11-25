import java.io.File;
import java.util.ArrayList;

class Question {

    private String question;
    private ArrayList<String> answers = new ArrayList<String>();

    Question(String questionInput, ArrayList<String> answersInput) {
        question = questionInput;
        answers = answersInput;
    }

    String getQuestion() {
        return this.question;
    }

    ArrayList<String> getRandomAnswerArray() {

        ArrayList<String> nonRandomAnswerArray = answers;
        ArrayList<String> randomAnswerArray = new ArrayList<String>();
        int randomInt;

        while (nonRandomAnswerArray.size() != 0) {
            randomInt = MiscFunctions.randomIntBetweenNumbers(0, nonRandomAnswerArray.size());
            randomAnswerArray.add(nonRandomAnswerArray.get(randomInt));
            nonRandomAnswerArray.remove(randomInt);
        }

        return randomAnswerArray;
    }

    String getCorrectAnswer() {
        return answers.get(0);
    }

    String getAnIncorrectAnswer(int num) {
        if (num > 0 && num < 4) {
            return answers.get(num);
        } else {
            return null;
        }
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
        questionString += ("\n\t\tWrong answers: " + this.getAnIncorrectAnswer(1));
        questionString += (", " + this.getAnIncorrectAnswer(2));
        questionString += (", " + this.getAnIncorrectAnswer(3));
        questionString += ("\n\t\tCorrect answer: " + this.getCorrectAnswer());

        return questionString;
    }


}
