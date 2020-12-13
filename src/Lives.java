public class Lives extends Value {

    public Lives(String preValueText,double value) {
        super(preValueText,value);
        setValue(value);
    }

    public Lives(String preValueText,double value, double v, double v1) {
        super(preValueText, value, v, v1);
        setValue(value);
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
