import java.util.ArrayList;

class Question {

    private String question;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;
    private ArrayList<String> allAnswers;

    Question(String questionInput, ArrayList<String> answersInput) {
        question = questionInput;
        allAnswers = new ArrayList<String>(answersInput);
        correctAnswer = answersInput.get(answersInput.size() - 1);

        answersInput.remove(answersInput.size() - 1);
        incorrectAnswers = new ArrayList<String>(answersInput);
    }

    String getQuestion() {
        return question;
    }

    String getCorrectAnswer() {
        return correctAnswer;
    }

    ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    private ArrayList<String> getRandomAnswerArray() {
        ArrayList<String> allAnswersArray = new ArrayList<String>(allAnswers);
        ArrayList<String> randomAnswerArray = new ArrayList<String>();
        int randomInt;

        while (!allAnswersArray.isEmpty()){
            randomInt = MiscFunctions.randomIntBetweenNumbers(0, allAnswersArray.size() - 1);
            randomAnswerArray.add(allAnswersArray.get(randomInt));
            allAnswersArray.remove(randomInt);
        }
        return randomAnswerArray;
    }

    String toStringRandomized() {
        String questionString;
        ArrayList randomAnswerArray = getRandomAnswerArray();

        questionString = ("\tWord: " + question + "\n");
        for (int i = 0; i < randomAnswerArray.size(); i++) {
            questionString += ("\n\t(" + (i + 1) + ")  " + randomAnswerArray.get(i));
        }

        return questionString;
    }

    public String toString() {
        String questionString;

        questionString = ("\tWord: " + question);
        questionString += ("\n\n\t\tWrong answers: " + incorrectAnswers.get(0));
        questionString += (", " + incorrectAnswers.get(1));
        questionString += (", " + incorrectAnswers.get(2));
        questionString += ("\n\t\tCorrect answer: " + correctAnswer);

        return questionString;
    }


}
