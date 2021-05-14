package fgk.zad1.obj;

import fgk.zad1.basics.Mesh;
import fgk.zad1.basics.Triangle;
import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.material.Material;
import fgk.zad1.scene.World;
import fgk.zad1.utilitis.Lightintencity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Parser plikow OBJ
 */
public class ObjReader {
    /**
     * Listy wspoczynnikow
     */
    private static List<Vector3> vList = new ArrayList<>();
    private static List<Vector3> vnList = new ArrayList<>();
    private static List<Vector3[]> fList = new ArrayList<>();
    private static List<Vector2> vtList = new ArrayList<>();
    private static Vector3 AmbientKa;
    private static Vector3 SpecularKs;
    private static float Ns;
    private static Vector3 DiffuseKd;
    private static float Transparent_d;

    /**
     * Metoda odczytywania i zapisywania wartosci z pliku OBJ
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Mesh readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {

            String string = myReader.nextLine();
            String[] arrayOfString = string.split(" ");

            if (arrayOfString[0].equals("v") && arrayOfString.length == 4) {

                Vector3 vertex = new Vector3(Float.parseFloat(arrayOfString[1])
                        , Float.parseFloat(arrayOfString[2])
                        , Float.parseFloat(arrayOfString[3]));
                vList.add(vertex);
            } else if (arrayOfString[0].equals("vt")) {
                Vector2 vt = new Vector2(Float.parseFloat(arrayOfString[1]),
                        Float.parseFloat(arrayOfString[2]));
                vtList.add(vt);
            } else if (arrayOfString[0].equals("vn")) {
                Vector3 vertex = new Vector3(Float.parseFloat(arrayOfString[1])
                        , Float.parseFloat(arrayOfString[2])
                        , Float.parseFloat(arrayOfString[3]));
                vnList.add(vertex);

            } else if (arrayOfString[0].equals("f")) {
                Vector3[] arrayOfVector3 = new Vector3[arrayOfString.length - 1];
                for (int i = 1; i < arrayOfString.length; i++) {
                    String[] split = arrayOfString[i].split("/");
                    arrayOfVector3[i - 1] = new Vector3(Float.parseFloat(split[0])
                            , Float.parseFloat(split[1])
                            , Float.parseFloat(split[2]));
                }
                fList.add(arrayOfVector3);
            }
        }
        //---------------
        String[] ar = fileName.split("");
        StringBuilder newfilename = new StringBuilder();
        for (int i = 0; i < ar.length; i++) {
            if (i == ar.length - 3) {
                newfilename.append("m");
            } else if (i == ar.length - 2) {
                newfilename.append("t");
            } else if (i == ar.length - 1) {
                newfilename.append("l");
            } else {
                newfilename.append(ar[i]);
            }
        }
        String s = newfilename.toString();
        File file1 = new File(s);
        myReader = new Scanner(file1);
        while (myReader.hasNextLine()) {

            String string = myReader.nextLine();
            String[] arrayOfString = string.split(" ");

            if (arrayOfString[0].equals("Ns") && arrayOfString.length == 2) {
                Ns = Float.parseFloat(arrayOfString[1]);
            } else if (arrayOfString[0].equals("Ka") && arrayOfString.length == 4) {
                AmbientKa = new Vector3(Float.parseFloat(arrayOfString[1]),
                        Float.parseFloat(arrayOfString[2]), Float.parseFloat(arrayOfString[3]));
            } else if (arrayOfString[0].equals("Kd") && arrayOfString.length == 4) {
                DiffuseKd = new Vector3(Float.parseFloat(arrayOfString[1]),
                        Float.parseFloat(arrayOfString[2]), Float.parseFloat(arrayOfString[3]));
            } else if (arrayOfString[0].equals("Ks") && arrayOfString.length == 4) {
                SpecularKs = new Vector3(Float.parseFloat(arrayOfString[1]),
                        Float.parseFloat(arrayOfString[2]), Float.parseFloat(arrayOfString[3]));
            } else if (arrayOfString[0].equals("d") && arrayOfString.length == 2) {
                Transparent_d = Float.parseFloat(arrayOfString[1]);
            }
        }
        /**rysowanie figur
         *
         */
        Mesh mesh = new Mesh();
        List<Triangle> triangles = new ArrayList<>();
        List<Vector3> tempVectors = new ArrayList<>();
        for (Vector3[] vector3s : fList) {
            for (Vector3 vector3 : vector3s) {
                float vIndex = vector3.getX();
                float vnIndex = vector3.getZ();
                Vector3 v = new Vector3(vList.get((int) vIndex - 1));
                Vector3 vn = new Vector3(vnList.get((int) vnIndex - 1));
                //System.out.println(v);
                tempVectors.add(v);
//                if (tempVectors.size()==3&&vector3s.length==3){
//                    triangles.add(new Triangle(new Vector3(tempVectors.get(0)),new Vector3(tempVectors.get(1)),
//                            new Vector3(tempVectors.get(2)),new Vector3(vn), new Lightintencity(1f,0f,0f)));
//                    tempVectors.clear();
//                }
                if (tempVectors.size() == vector3s.length) {
                    for (int i = 0; i < vector3s.length - 2; i++) {
                        triangles.add(new Triangle(new Vector3(tempVectors.get(0)), new Vector3(tempVectors.get(1 + i)),
                                new Vector3(tempVectors.get(2 + i)), new Vector3(vn), new Lightintencity(1f, 0f, 0f),new Material(Ns,AmbientKa,
                                SpecularKs,DiffuseKd,Transparent_d)));

                    }
                    tempVectors.clear();
                }

            }
        }
        mesh.setList(triangles);
        return mesh;

    }
}
