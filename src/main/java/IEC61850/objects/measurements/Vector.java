package IEC61850.objects.measurements;

import IEC61850.objects.samples.AnalogValue;
import lombok.Data;

@Data
public class Vector {
    private AnalogValue mag = new AnalogValue();
    private AnalogValue ang = new AnalogValue();
    private AnalogValue rad = new AnalogValue();
    private AnalogValue ortX = new AnalogValue();
    private AnalogValue ortY = new AnalogValue();

    public void setValue0(float x, float y){
        ortX.getF().setValue(x);
        ortY.getF().setValue(y);
        mag.getF().setValue((float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
        rad.getF().setValue((float) Math.atan2(y, x));
        ang.getF().setValue((float) Math.toDegrees(rad.getF().getValue()));
    }

}
