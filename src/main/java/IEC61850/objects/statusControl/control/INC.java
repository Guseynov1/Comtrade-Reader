package IEC61850.objects.statusControl.control;

import IEC61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class INC {
    private Attribute<Integer> stVal = new Attribute<>(0);
    private Attribute<Integer> ctlVal = new Attribute<>(0);

}
