package fgk.zad1.scene;

public class ViewPlane {
    /**Rozmiar pixela
     */
    public double sizeOfPixel;
    /**Szerokosc obrazu
     */
    public int width;
    /**Wysokosc obrazu
     *
     */
    public int heigth;

    /**Konstruktor obrazu
     * @param width
     * @param heigth
     * @param sizeOfPixel
     */
    public ViewPlane( int width, int heigth,double sizeOfPixel) {
        this.sizeOfPixel = sizeOfPixel;
        this.width = width;
        this.heigth = heigth;
    }



}
