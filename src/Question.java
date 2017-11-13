class Question {

    private String question;
    private String[] answers = new String[4];
    private String oldAnswers = "";

    Question(String questionInput, String answerInput, String incOneInput, String incTwoInput, String incThreeInput) {
        question = questionInput;
        answers[0] = answerInput;
        answers[1] = incOneInput;
        answers[2] = incTwoInput;
        answers[3] = incThreeInput;
    }

    Question(Question questionObject) { // Copy constructor used to create randomized questions for an individual game
        question = questionObject.getQuestion();
        answers[0] = questionObject.getCorrect();
        answers[1] = questionObject.getIncorrect(1);
        answers[2] = questionObject.getIncorrect(2);
        answers[3] = questionObject.getIncorrect(3);
    }

    String getQuestion() {
        return this.question;
    }

    String getCorrect() {
        return this.answers[0];
    }

    String getIncorrect(int num) {
        if (num > 0 && num < 4) {
            return this.answers[num];
        } else {
            return null;
        }
    }

    String getRandomizedNewAnswer() {

        ////Write code here to generate a random answer (without replacement)

        return "";
    }

    public String toString() {

        String questionToString = "";

        questionToString += ("\tQuestion: " + this.getQuestion());
        questionToString += ("\n\t\tWrong answers: " + this.getIncorrect(1));
        questionToString += (", " + this.getIncorrect(2));
        questionToString += (", " + this.getIncorrect(3));
        questionToString += ("\n\t\tCorrect answer: " + this.getCorrect());

        return questionToString;
    }
}
