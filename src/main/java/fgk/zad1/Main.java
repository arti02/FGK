package fgk.zad1;

import fgk.zad1.Basics.*;
import fgk.zad1.Cameras.OrthogonalCamera;
import fgk.zad1.Cameras.PerspectivalCamera;
import fgk.zad1.Utilitis.ColorM;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        /**Definiowanie sfer, promieni i powierzchni
         *
         */
        Sphere S = new Sphere(new Vector3(0,0,20), 10,new ColorM(1f,0f,0f));

        Ray R1= new Ray(new Vector3(0,0,-20), new Vector3(0,0,1));
        Ray R2 = new Ray(new Vector3(0,0,-20), new Vector3(0,1,0));
        Ray R3 = new Ray(new Vector3(0,20,10),new Vector3(0,-1,0));
        Surface P = new Surface(new Vector3(0,1,1), 0);



//        /**Sprawdzanie czy promienie przecinają dane obiekty
//         *
//         */
//        Vector3[] vec1=  S.checkSection(R1);
//        Vector3[] vec2=  S.checkSection(R2);
//        Vector3[] vec3=  S.checkSection(R3);
//        Vector3 vec4 = P.checkSection(R2);

        /** Ortogonalny rzut
         *
         */
        LightIntensity lightIntensity=new LightIntensity(0,1,0);
        Image img=new Image(lightIntensity,640,480);
        OrthogonalCamera or=new OrthogonalCamera(img);
        or.ortogonalShoot(img, S);

        /**Prespektywiczny rzut
         *

         */
//        Vector3 eye=new Vector3(0,0,600);
//        Vector3 lookat= new Vector3(0,0,20);
//        LightIntensity lightIntensity=new LightIntensity(0,1,0);
//        Image img=new Image(lightIntensity,640,480);
//        PerspectivalCamera perspectivalCamera=new PerspectivalCamera(img,eye,lookat,60);
//        perspectivalCamera.ortogonalShoot(img,S);
//        /**Informowanie o  wyniku próby przecięcia danych obiektów
//         *
//         */
//        if(vec1!=null)
//        {
//           System.out.println("R1 przecina się w :");
//           for(int i =0;i < vec1.length;i++)
//           {
//               System.out.println(vec1[i].toString());
//
//           }
//       }
//        else
//        {
//           System.out.println("R1 nie przecina się");
//       }
//        if(vec2!=null)
//        {
//            System.out.println("R2 przecina się w :");
//            for(int i =0;i < vec2.length;i++)
//            {
//                System.out.println(vec2[i].toString());
//            }
//        }
//        else
//        {
//            System.out.println("R2 nie przecina się");
//        }
//        if(vec3!=null)
//        {
//            System.out.println("R3 przecina się w :");
//            for(int i =0;i < vec3.length;i++)
//            {
//                System.out.println(vec3[i].toString());
//            }
//        }
//        else
//        {
//            System.out.println("R2 nie przecina się");
//        }
//        if(vec4!=null) {
//            System.out.println("S jest przecinane przez R2:");
//            System.out.println(vec4.toString());
//        }
//        else
//        {
//            System.out.println("S nie jest przecinane przez R2 ");
//        }
   }
}
