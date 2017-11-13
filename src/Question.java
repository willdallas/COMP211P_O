class Question {

    private String question;
    private String answer;
    private String incorrectOne;
    private String incorrectTwo;
    private String incorrectThree;

    Question(String questionInput, String answerInput, String incOneInput, String incTwoInput, String incThreeInput) {
        question = questionInput;
        answer = answerInput;
        incorrectOne = incOneInput;
        incorrectTwo = incTwoInput;
        incorrectThree = incThreeInput;
    }

    Question(Question questionObject) { // Copy constructor used to create randomized questions for an individual game
        question = questionObject.getQuestion();
        answer = questionObject.getCorrect();
        incorrectOne = questionObject.getIncorrectOne();
        incorrectTwo = questionObject.getIncorrectTwo();
        incorrectThree = questionObject.getIncorrectThree();
    }

    String getQuestion() {
        return this.question;
    }

    String getCorrect() {
        return this.answer;
    }

    String getIncorrectOne() {
        return this.incorrectOne;
    }

    String getIncorrectTwo() {
        return this.incorrectTwo;
    }

    String getIncorrectThree() {
        return this.incorrectThree;
    }

    String getRandomAnswer() {

        /////

        return "";

    }


}
