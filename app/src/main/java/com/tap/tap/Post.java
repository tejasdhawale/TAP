package com.tap.tap;

/**
 * Created by R4J35H V415HN4V on 21-03-2017.
 */

public class Post {

    public String questionno;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    //public String question;
    public String question;
    public String op1;
    public String op2;
    public String op3;
    public String op4;
    public String right;
    public String outoff;
    public String testmarks;

    public String getUnattempted() {
        return unattempted;
    }

    public void setUnattempted(String unattempted) {
        this.unattempted = unattempted;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    public String unattempted;
    public String wrong;

    public String getOutoff() {
        return outoff;
    }

    public void setOutoff(String outoff) {
        this.outoff = outoff;
    }

    public String getTestmarks() {
        return testmarks;
    }

    public void setTestmarks(String testmarks) {
        this.testmarks = testmarks;
    }

    public String getQuestionno() {
        return questionno;
    }

    public void setQuestionno(String questionno) {
        this.questionno = questionno;
    }

    /**
     * public String getQuestion() {
     * return question;
     * }
     * <p>
     * public void setQuestion(String question) {
     * this.question = question;
     * }
     **/

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }


}