package fgk.zad1.material;

import fgk.zad1.basics.Vector3;

public class Material {
    private Vector3 AmbientKa;
    private  Vector3 SpecularKs;
    private  float Ns;
    private  Vector3 DiffuseKd;
    private float Transparent_d;

    @Override
    public String toString() {
        return "Material{" +
                "AmbientKa=" + AmbientKa +
                ", SpecularKs=" + SpecularKs +
                ", Ns=" + Ns +
                ", DiffuseKd=" + DiffuseKd +
                ", Transparent_d=" + Transparent_d +
                '}';
    }

    public Material(float ns, Vector3 ambientKa, Vector3 specularCs, Vector3 diffuseKd, float transparent_d) {
        Ns = ns;
        AmbientKa = ambientKa;
        SpecularKs = specularCs;
        DiffuseKd = diffuseKd;
        Transparent_d = transparent_d;
    }

    public Material(Vector3 ambientKa, Vector3 specularCs, Vector3 diffuseKd, float transparent_d) {
        AmbientKa = ambientKa;
        SpecularKs = specularCs;
        DiffuseKd = diffuseKd;
        Transparent_d = transparent_d;
    }

    public Vector3 getAmbientKa() {
        return AmbientKa;
    }

    public void setAmbientKa(Vector3 ambientKa) {
        AmbientKa = ambientKa;
    }

    public Vector3 getSpecularCs() {
        return SpecularKs;
    }

    public void setSpecularCs(Vector3 specularCs) {
        SpecularKs = specularCs;
    }

    public float getNs() {
        return Ns;
    }

    public void setNs(float ns) {
        Ns = ns;
    }

    public Vector3 getDiffuseKd() {
        return DiffuseKd;
    }

    public void setDiffuseKd(Vector3 diffuseKd) {
        DiffuseKd = diffuseKd;
    }

    public float getTransparent_d() {
        return Transparent_d;
    }

    public void setTransparent_d(float transparent_d) {
        Transparent_d = transparent_d;
    }


}
