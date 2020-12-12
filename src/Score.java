import acm.graphics.GLabel;

public class Score extends GLabel {
    private double score;
    private String preScoreText;

    public Score(String preScoreText) {
        super(preScoreText);
        this.preScoreText = preScoreText;
    }

    public Score(String preScoreText,double v,double v1) {
        super(preScoreText,v,v1);
        this.preScoreText = preScoreText;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        this.setLabel(preScoreText + score);
    }

    public String getPreScoreText() {
        return preScoreText;
    }

    public void setPreScoreText(String preScoreText) {
        this.preScoreText = preScoreText;
    }
}
