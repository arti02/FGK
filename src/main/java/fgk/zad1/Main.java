package fgk.zad1;

import fgk.zad1.Basics.Ray;
import fgk.zad1.Basics.Sphere;
import fgk.zad1.Basics.Surface;
import fgk.zad1.Basics.Vector3;

public class Main {
    public static void main(String[] args) {
        /**Definiowanie sfer, promieni i powierzchni
         *
         */
        Sphere S = new Sphere(new Vector3(0,0,0), 10);
        Ray R1= new Ray(new Vector3(0,0,-20), new Vector3(0,0,1));
        Ray R2 = new Ray(new Vector3(0,0,-20), new Vector3(0,1,0));
        Ray R3 = new Ray(new Vector3(0,20,10),new Vector3(0,-1,0));
        Surface P = new Surface(new Vector3(0,1,1), 0);

        /**Sprawdzanie czy promienie przecinają dane obiekty
         *
         */
        Vector3[] vec1=  S.checkSection(R1);
        Vector3[] vec2=  S.checkSection(R2);
        Vector3[] vec3=  S.checkSection(R3);
        Vector3 vec4 = P.checkSection(R2);

        /**Informowanie o  wyniku próby przecięcia danych obiektów
         *
         */
        if(vec1!=null)
        {
           System.out.println("R1 przecina się w :");
           for(int i =0;i < vec1.length;i++)
           {
               System.out.println(vec1[i].toString());

           }
       }
        else
        {
           System.out.println("R1 nie przecina się");
       }
        if(vec2!=null)
        {
            System.out.println("R2 przecina się w :");
            for(int i =0;i < vec2.length;i++)
            {
                System.out.println(vec2[i].toString());
            }
        }
        else
        {
            System.out.println("R2 nie przecina się");
        }
        if(vec3!=null)
        {
            System.out.println("R3 przecina się w :");
            for(int i =0;i < vec3.length;i++)
            {
                System.out.println(vec3[i].toString());
            }
        }
        else
        {
            System.out.println("R2 nie przecina się");
        }
        if(vec4!=null) {
            System.out.println("S jest przecinane przez R2:");
            System.out.println(vec4.toString());
        }
        else
        {
            System.out.println("S nie jest przecinane przez R2 ");
        }
    }
}
