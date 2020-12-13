import acm.graphics.GLabel;

public class Value extends GLabel {
    /**Value that will be saved*/
    private double value;
    /**Text before value*/
    private String preValueText = "";

    /**Costructor
     *
     * @param preValueText Text that will be written before value
     * @param value
     */
    public Value(String preValueText,double value) {
        super(preValueText);
        this.value = value;
        this.preValueText = preValueText;
    }

    /**
     *
     * @param preValueText Text that will be written before value
     * @param value
     * @param v start x coordinate
     * @param v1 start y coordinate
     */
    public Value(String preValueText, double value, double v, double v1) {
        super(preValueText,v,v1);
        this.value = value;
        this.preValueText = preValueText;
    }

    /**Returns value*/
    public double getValue() {
        return value;
    }

    /**Sets value*/
    public void setValue(double value) {
        this.value = value;
        this.setLabel(preValueText + value);
    }

    /**Add to value*/
    public void addToValue(double add){
        setValue(value + add);
    }

    /**Returns text before value*/
    public String getPreValueText(){
        return preValueText;
    }

    public void setPreValueText(String preValueText){
        this.preValueText = preValueText;
    }
}
