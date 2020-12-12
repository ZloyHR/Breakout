public class Time extends Value{
    public Time(String preValueText) {
        super(preValueText);
    }

    public Time(String preValueText, double v, double v1) {
        super(preValueText, v, v1);
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
        int sec = (int)(value / 1000);
        int min = sec / 60;
        sec -= min * 60;
        setLabel(getPreValueText() + getTwoDigit(min) + ":" + getTwoDigit(sec));
    }

    private String getTwoDigit(double x){
        int v = (int)x;
        if(v < 10)return "0" + v;
        return "" + v;
    }
}
