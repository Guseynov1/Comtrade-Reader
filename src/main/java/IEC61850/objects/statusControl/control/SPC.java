package IEC61850.objects.statusControl.control;

import IEC61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class SPC {
    private Attribute<Boolean> stVal = new Attribute<>(false);
    private Attribute<Boolean> ctlVal = new Attribute<>(false);

}
