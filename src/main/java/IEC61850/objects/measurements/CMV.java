package IEC61850.objects.measurements;

import lombok.Data;

@Data
public class CMV {

    private Vector cVal = new Vector();
    private Vector instCVal = new Vector();

}
