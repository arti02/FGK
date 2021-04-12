package fgk.zad1.cameras;

import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.main.Driver;
import fgk.zad1.utilitis.Image;

public class OrthogonalCamera extends Camera {

    /**Implemnetacja promienia dla kamery ortogonalnej
     *
     * @param vector
     * @return
     */
    public Ray createRay(Vector2 vector) {
         double SizeOfPixel= Driver.world.viewPlane.sizeOfPixel;

        return new Ray(new Vector3((float) (SizeOfPixel * vector.x), (float) (SizeOfPixel * vector.y), 100),
                new Vector3(0, 0, -1));
    }
}

