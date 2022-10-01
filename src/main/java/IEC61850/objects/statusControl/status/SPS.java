package IEC61850.objects.statusControl.status;

import IEC61850.objects.samples.Attribute;
import lombok.Data;


@Data
public class SPS {
    private Attribute<Boolean> stVal = new Attribute<>(false);

}


