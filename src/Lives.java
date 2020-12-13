public class Lives extends Value {

    /**Costructor
     *
     * @param preValueText Text that will be written before amount of lives
     * @param value number of lives
     */
    public Lives(String preValueText,double value) {
        super(preValueText,value);
        setValue(value);
    }

    /**
     *
     * @param preValueText Text that will be written before amount of lives
     * @param value number of lives
     * @param v start x coordinate
     * @param v1 start y coordinate
     */
    public Lives(String preValueText,double value, double v, double v1) {
        super(preValueText, value, v, v1);
        setValue(value);
    }

    /**
     * Returns true if player is live
     * @return
     */
    public boolean isLive(){
        return getValue() > 0;
    }

    @Override
    /**
     * Sets value and chage what will be displayed
     */
    public void setValue(double value) {
        super.setValue(value);
        setLabel(getPreValueText() + (int)value);
    }
}
