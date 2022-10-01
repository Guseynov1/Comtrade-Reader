package IEC61850.nodes.measurements.filter;

import IEC61850.objects.measurements.Vector;
import IEC61850.objects.samples.SAV;

public interface Filter {

    void process(SAV sav, Vector vector);
}
