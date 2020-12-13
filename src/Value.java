import acm.graphics.GLabel;

public class Value extends GLabel {
    private double value;
    private String preValueText = "";

    public Value(String preValueText,double value) {
        super(preValueText);
        this.value = value;
        this.preValueText = preValueText;
    }

    public Value(String preValueText, double value, double v, double v1) {
        super(preValueText,v,v1);
        this.value = value;
        this.preValueText = preValueText;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        this.setLabel(preValueText + value);
    }

    public void addToValue(double add){
        setValue(value + add);
    }

    public String getPreValueText(){
        return preValueText;
    }

    public void setPreValueText(String preValueText){
        this.preValueText = preValueText;
    }
}
