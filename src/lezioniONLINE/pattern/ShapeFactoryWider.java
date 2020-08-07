package pattern;

import org.jetbrains.annotations.NotNull;

public class ShapeFactoryWider  extends  ShapeFactory{

    private final double amp;

    public ShapeFactoryWider(double amp){
        this.amp = amp;
    }
    /**
     * BRUTTTISSIMA IMPLEMENTAZIONE POCO SICURA, MA MOLTO POCO SICURA
     * @param name nome dell'oggetto che vorresti ritoranre
     * @param data insieme di parametri che servono ad instanziare l'oggetto
     * @return ritorno l'oggetto legato al nome che ho scritto (se non trovato raise exception)
     */
    @Override
    @NotNull
    public Shape create(String name, double... data) throws Exception {
        if (name.toLowerCase().equals("rectangle"))
            return new Rectangle(data[0] * amp,data[1] * amp);
        if (name.toLowerCase().equals("circle"))
            return new Circle(data[0] * amp);
        else
            throw new Exception("Invalid shape:" + name);
    }
}
