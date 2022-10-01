package IEC61850.objects.measurements.settings;

import IEC61850.objects.samples.AnalogValue;
import lombok.Data;

@Data
public class ASG {
    private AnalogValue setVal = new AnalogValue();
    private AnalogValue SetMag = new AnalogValue();

    private AnalogValue SetMagA = new AnalogValue();
    private AnalogValue SetMagB = new AnalogValue();
    private AnalogValue SetMagC = new AnalogValue();

    private AnalogValue minVal = new AnalogValue();
    private AnalogValue maxVal = new AnalogValue();
    private AnalogValue stepSize = new AnalogValue();

}
