import acm.graphics.GLabel;

public class Score extends Value {

    /**Constructor
     *
     * @param preValueText Text that will be written before score
     * @param value Score
     */
    public Score(String preValueText,double value) {
        super(preValueText,value);
        setLabel(preValueText + value);
    }

    /**Constructor
     *
     * @param preValueText Text that will be written before score
     * @param value Score
     * @param v Start x coordinate
     * @param v1 Start y coordinate
     */
    public Score(String preValueText, double value, double v, double v1) {
        super(preValueText, value, v, v1);
    }
}
