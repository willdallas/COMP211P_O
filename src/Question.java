import java.util.ArrayList;

class Question {

    private String question;
    private String correctAnswer;
    private ArrayList<String> allAnswers;

    Question(String questionInput, ArrayList<String> answersInput) {
        question = questionInput;
        allAnswers = new ArrayList<String>(answersInput);
        correctAnswer = answersInput.get(answersInput.size() - 1);
    }

    String getQuestion() {
        return question;
    }

    String getCorrectAnswer() {
        return correctAnswer;
    }

    ArrayList<String> getRandomAnswerArray() {
        ArrayList<String> nonRandomAnswersArray = new ArrayList<String>(allAnswers);
        ArrayList<String> randomAnswerArray = new ArrayList<String>();
        int randomInt;

        while (!nonRandomAnswersArray.isEmpty()) {  // Randomly selects entry from nonRandomAnswersArray to add to randomAnswerArray
            randomInt = MiscFunctions.randomIntBetweenNumbers(0, nonRandomAnswersArray.size() - 1);
            randomAnswerArray.add(nonRandomAnswersArray.get(randomInt));
            nonRandomAnswersArray.remove(randomInt);
        }
        return randomAnswerArray;
    }

    public String toString() {
        String questionString;

        questionString = ("\tWord: " + question);
        questionString += ("\n\n\t\tWrong answers: " + allAnswers.get(0));
        questionString += (", " + allAnswers.get(1));
        questionString += (", " + allAnswers.get(2));
        questionString += ("\n\t\tCorrect answer: " + correctAnswer);

        return questionString;
    }
}
