package fgk.zad1.basics;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    public Mesh() {
        list=new ArrayList();
    }

    private List<Triangle> list;

    public List<Triangle> getList() {
        return list;
    }

    public void setList(List<Triangle> list) {
        this.list = list;
    }
}
