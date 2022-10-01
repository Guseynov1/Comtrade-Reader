package IEC61850.objects.statusControl.status;

import IEC61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class INS {
    private Attribute<Integer> stVal = new Attribute<>(0);

}
