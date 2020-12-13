public class Time extends Value{
    public Time(String preValueText,double value) {
        super(preValueText,value);
        setValue(value);
    }

    public Time(String preValueText,double value, double v, double v1) {
        super(preValueText,value,v,v1);
        setValue(value);
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
        int sec = (int)(value / 1000);
        int min = sec / 60;
        sec -= min * 60;
        setLabel(getPreValueText() + getTwoDigit(min) + ":" + getTwoDigit(sec));
    }

    public String getTwoDigit(double x){
        int v = (int)x;
        if(v < 10)return "0" + v;
        return "" + v;
    }
}
