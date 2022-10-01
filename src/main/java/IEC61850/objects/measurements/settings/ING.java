package IEC61850.objects.measurements.settings;

import IEC61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class ING {
    private Integer SetVal;
    private Attribute<Integer> minVal = new Attribute<>(0);
    private Attribute<Integer> maxVal = new Attribute<>(0);
    private Attribute<Integer> stepSize = new Attribute<>(0);

}
