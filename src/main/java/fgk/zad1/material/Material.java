package fgk.zad1.material;

public class Material {
    private float AmbientCoe;
    private  float SpecularCoe;
    private  float TranssmitioNCoe;
    private  float DiffuseCoe;
    private float Roughness;

    public Material(float ambientCoe, float specularCoe, float transsmitioNCoe, float diffuseCoe, float roughness) {
        AmbientCoe = ambientCoe;
        SpecularCoe = specularCoe;
        TranssmitioNCoe = transsmitioNCoe;
        DiffuseCoe = diffuseCoe;
        Roughness = roughness;
    }

    public float getAmbientCoe() {
        return AmbientCoe;
    }

    public void setAmbientCoe(float ambientCoe) {
        AmbientCoe = ambientCoe;
    }

    public float getSpecularCoe() {
        return SpecularCoe;
    }

    public void setSpecularCoe(float specularCoe) {
        SpecularCoe = specularCoe;
    }

    public float getTranssmitioNCoe() {
        return TranssmitioNCoe;
    }

    public void setTranssmitioNCoe(float transsmitioNCoe) {
        TranssmitioNCoe = transsmitioNCoe;
    }

    public float getDiffuseCoe() {
        return DiffuseCoe;
    }

    public void setDiffuseCoe(float diffuseCoe) {
        DiffuseCoe = diffuseCoe;
    }

    public float getRoughness() {
        return Roughness;
    }

    public void setRoughness(float roughness) {
        Roughness = roughness;
    }
}
