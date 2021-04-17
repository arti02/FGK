package fgk.zad1.obj;

import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.scene.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjReader {
    public static World readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        World world = new World(1600, 800, 1);
        List<Vector3> vList  = new ArrayList<>();
        List<Vector3> vnList = new ArrayList<>();
        List<Vector3[]> fList  = new ArrayList<>();
        List<Vector2> vtList = new ArrayList<>();
        while (myReader.hasNextLine()) {
            //Cube
            //stożek_stożek.002
            //Nazwa danego elementu jest w arrayOfString[1]

            String string = myReader.nextLine();
            String[] arrayOfString = string.split(" ");

         //   System.out.println(string);
            if (arrayOfString[0].equals("o")) {
                vList = new ArrayList<>();
                while (true) {
                    String v = myReader.nextLine();
                    String[] actualStringArray = v.split(" ");

                    //Vertex
                    if (actualStringArray[0].equals("v")&& actualStringArray.length==4) {

                        Vector3 vertex = new Vector3(Float.parseFloat(actualStringArray[1])
                                , Float.parseFloat(actualStringArray[2])
                                , Float.parseFloat(actualStringArray[3]));
                        vList.add(vertex);

                    }
                    //VN - Vertex of normals
                    else if (actualStringArray[0].equals("vn") ){
                        Vector3 vertex = new Vector3(Float.parseFloat(actualStringArray[1])
                                , Float.parseFloat(actualStringArray[2])
                                , Float.parseFloat(actualStringArray[3]));
                        vnList.add(vertex);
                        System.out.println(vertex.toString());


                    }
                    //f - walls XDD
                    else if (actualStringArray[0].equals("f") ) {
                        Vector3[] arrayOfVector3 = new Vector3[3];
                        String[] arrayString;
                        for (int i = 1; i < actualStringArray.length; i++)
                        {
                            arrayOfString = actualStringArray[i].split("/");
                            arrayOfVector3[i-1] = new Vector3(Float.parseFloat( arrayOfString[1])
                                    , Float.parseFloat( arrayOfString[2])
                                    , Float.parseFloat( arrayOfString[3]));
                            System.out.println(arrayOfVector3[i-1].toString());
                        }
                        fList.add(arrayOfVector3);

                    }
                    //vt - texture
                    else if (actualStringArray[0].equals("vt") )
                    {
                        Vector2 vt = new Vector2(Float.parseFloat( actualStringArray[1])
                                , Float.parseFloat( actualStringArray[2]));
                        vtList.add(vt);
                    }
                    else {
                        ///Here create objects and set the stage
                        vList.clear();
                        vtList.clear();
                        vnList.clear();
                        fList.clear();
                        break;
                    }
                    }
                }

                //     System.out.println(actualStringArray.length);




        }
        return null;
    }
}
