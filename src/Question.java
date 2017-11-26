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
        ArrayList<String> nonRandomAnswersArray = new ArrayList<String>(allAnswers);
        ArrayList<String> randomAnswerArray = new ArrayList<String>();
        int randomInt;

        while (!nonRandomAnswersArray.isEmpty()){  // Randomly selects entry from nonRandomAnswersArray to add to randomAnswerArray
            randomInt = MiscFunctions.randomIntBetweenNumbers(0, nonRandomAnswersArray.size() - 1);
            randomAnswerArray.add(nonRandomAnswersArray.get(randomInt));
            nonRandomAnswersArray.remove(randomInt);
        }
        return randomAnswerArray;
    }

    String toStringRandomized() { // Returns string displaying the New Word, and the four potential answers in random order
        String questionString;
        ArrayList randomAnswerArray = getRandomAnswerArray();

        questionString = ("\tNew Word: " + question + "\n");
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
