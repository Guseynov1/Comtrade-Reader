package IEC61850.objects.measurements;

import IEC61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class ACD {
    private Attribute<Boolean> general = new Attribute<>(false);
    private Attribute<Boolean> phsA = new Attribute<>(false);
    private Attribute<Boolean> phsB = new Attribute<>(false);
    private Attribute<Boolean> phsC = new Attribute<>(false);
    private Attribute<Boolean> neut = new Attribute<>(false);

}
