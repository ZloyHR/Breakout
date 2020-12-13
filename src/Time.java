public class Time extends Value{

    /**Constructor
     *
     * @param preValueText Text that will be written before time
     * @param value Time
     */
    public Time(String preValueText,double value) {
        super(preValueText,value);
        setValue(value);
    }

    /**Constructor
     *
     * @param preValueText Text that will be written before time
     * @param value Time
     * @param v Start x coordinate
     * @param v1 Start y coordinate
     */
    public Time(String preValueText,double value, double v, double v1) {
        super(preValueText,value,v,v1);
        setValue(value);
    }

    @Override
    /**Sets time*/
    public void setValue(double value) {
        super.setValue(value);
        int sec = (int)(value / 1000);
        int min = sec / 60;
        sec -= min * 60;
        setLabel(getPreValueText() + getTwoDigit(min) + ":" + getTwoDigit(sec));
    }

    /**
     * Transform double into 2-digit string
     * @param x Double that need to be transformed
     * @return Result
     */
    public String getTwoDigit(double x){
        int v = (int)x;
        if(v < 10)return "0" + v;
        return "" + v;
    }
}
