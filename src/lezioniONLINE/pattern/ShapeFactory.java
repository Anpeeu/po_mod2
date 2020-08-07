package pattern;


import org.jetbrains.annotations.NotNull;

//tutorialspoint.com/design_pattern/
public class ShapeFactory {

    /**
     * BRUTTTISSIMA IMPLEMENTAZIONE POCO SICURA, MA MOLTO POCO SICURA, può e spesso è statico
     * @param name nome dell'oggetto che vorresti ritoranre
     * @param data insieme di parametri che servono ad instanziare l'oggetto
     * @return ritorno l'oggetto legato al nome che ho scritto (se non trovato raise exception)
     */
    @NotNull
    public Shape create(String name, double... data) throws Exception {
        if (name.toLowerCase().equals("rectangle"))
            return new Rectangle(data[0],data[1]);
        if (name.toLowerCase().equals("circle"))
            return new Circle(data[0]);
        else
            throw new Exception("Invalid shape:" + name);
    }

    public interface Shape {
        void draw();
        double area();
        double perimeter();
    }

    static class Rectangle implements Shape{

        private final double length;
        private final double base;

        public Rectangle(double base, double length){
            this.base = base;
            this.length = length;

        }

        @Override
        public void draw() {
            System.out.println(String.format("Circle[%gx%g]", base, length));
        }

        @Override
        public double area() {
            return base * length;
        }

        @Override
        public double perimeter() {
            return (base + length) * 2;
        }
    }

     class Circle implements Shape {
        private double ray;

        public Circle( double ray){
            this.ray = ray;
        }

        @Override
        public void draw(){
            System.out.println(String.format("Circle[ray:%g]",ray));
        }

        @Override
        public double area(){
            return Math.PI * Math.pow(ray, 2);
        }

        @Override
        public double perimeter(){
            return Math.PI * ray * 2;
        }


    }

    public static void main(String[] args) throws Exception {
        ShapeFactory sf = new ShapeFactoryWider(10);
        Shape s = sf.create("rectangle",10,5);
        s.draw();
    }
}
