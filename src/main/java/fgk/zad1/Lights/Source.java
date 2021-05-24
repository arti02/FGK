package fgk.zad1.Lights;

import fgk.zad1.utilitis.Lightintencity;

import java.awt.*;

public abstract class Source {
    Lightintencity lightintencity;
    Color color;
    public abstract void emiteLight();

    public abstract Lightintencity getLightintencity();

    public void setLightintencity(Lightintencity lightintencity) {
        this.lightintencity = lightintencity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
