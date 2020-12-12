public class Lives extends Value {

    public Lives(String preValueText) {
        super(preValueText);
    }

    public Lives(String preValueText, double v, double v1) {
        super(preValueText, v, v1);
    }

    public boolean isLive(){
        return getValue() > 0;
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
        setLabel(getPreValueText() + (int)value);
    }
}
