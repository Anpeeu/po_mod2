package esami;

import Iterator_command.Lambda;

import java.util.*;
import java.util.function.Function;

public class Gennaio31_01_20 {

    public interface Solid extends Comparable<Solid> {
        double area();
        double volume();
        PositionedSolid at(Point origin);

        static <S extends Solid> int compareBy(Function<S, Double> f, S s1, S s2) {
            return Double.compare(f.apply(s1), f.apply(s2));
        }

        static <S extends Solid> Comparator<S> comparatorBy(Function<S, Double> f) {
            return (s1, s2) -> compareBy(f,s1,s2);
        }

        default int compareTo(Solid s) {
            return compareBy((x) -> x.volume(), this, s);
        }
    }

    public interface Polyhedron extends Solid {
        double perimeter();
        @Override
        PositionedPolyhedron at(Point origin);
    }
    public interface PositionedSolid {
        Point origin();
    }
    public interface PositionedPolyhedron extends PositionedSolid, Iterable<Point> {}

    public static class Point{
        public final double x;
        public final double y;
        public final double z;

        public Point(double x, double y, double z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Point move(double dx, double dy, double dz){
            return new Point(this.x+dx, this.y+dy, this.y+dy);
        }
    }

    public static class Cube implements Polyhedron{
        private double side;


        public Cube(double side){
            this.side = side;
        }

        @Override
        public double area() {
            return side * side  * 6;         //Math.pow(side,3) * 6;
        }

        @Override
        public double volume() {
            return side * side * side;  //Math.pow(side,3);
        }

        @Override
        public double perimeter() {
            return side * 4 * 6;
        }

        @Override
        public PositionedPolyhedron at(Point origin) {

            return new PositionedPolyhedron() {
                @Override
                public Point origin() {
                    return origin;
                }

                @Override
                public Iterator<Point> iterator() {
                    ArrayList<Point> res = new ArrayList<Point>();
                    res.add(origin);
                    res.add(origin.move(0,0,side));
                    res.add(origin.move(0,side,0));
                    res.add(origin.move(0,side,side));
                    res.add(origin.move(side,0,0));
                    res.add(origin.move(side,0,side));
                    res.add(origin.move(side,side,0));
                    res.add(origin.move(side,side,side));
                    return res.iterator();
                }
            };
        }

    }
    public static class Sphere implements Solid{
        private double ray;

        public Sphere( double ray){
            this.ray = ray;
        }

        @Override
        public double area() {
            return Math.PI * 4 * Math.pow(ray, 2);
        }

        @Override
        public double volume() {
            return Math.PI * 3/4 * Math.pow(ray, 3);
        }

        @Override
        public PositionedSolid at(Point origin) {
            return () -> origin;
            /*return new PositionedSolid() {
                @Override
                public Point origin() {
                    return origin;
                }
            };*/
        }
    }

    public static void main(String[] args) {

        List<Integer> points = new ArrayList<Integer>(List.of(1,2,3,4,5));



        List<Integer> foca = new ArrayList<>(points);
        foca.add(foca.size(),foca.get(0));
        System.out.println("PARTE 1");
        for (Integer i : foca)
            System.out.println(i);
        System.out.println("PARTE 2");
        for (Integer i : points)
            System.out.println(i);


        Comparator<Sphere> cmpSphere2 = Solid.comparatorBy(Solid::area);
        //caso1
        Function<Sphere, Double> x1 = Solid::area;
        Comparator<Sphere> cmpS1 = Solid.comparatorBy(x1);
        //caso2
        Function<Sphere, Double> x2 = Solid::area;
        Comparator<Sphere> cmpS2 = Solid.comparatorBy(x2);

        /*
        Cube cube1 = new Cube(11.), cube2 = new Cube(23.);
        Sphere sphere1 = new Sphere(12.), sphere2 = new Sphere(35.);
        List<Solid> solids = List.of(cube1, cube2, sphere1, sphere2);
        List<Cube> cubes = new ArrayList<>(List.of(cube1, cube2));
        List<Sphere> spheres = new ArrayList<>(List.of(sphere1, sphere2));
        List<? extends Polyhedron> polys = cubes;

        Collections.sort(cubes);

        Lambda.Function<Solid,Double> f = solid -> solid.area();
        Comparator<Solid> com = (s1, s2) -> Double.compare(f.apply(s1), f.apply(s2));
        Collections.sort(spheres, (o1, o2) -> Double.compare(o1.area(),o2.area()));

        Collections.sort(spheres, Solid.comparatorBy(Sphere::area));
        Collections.sort(cubes, Solid.comparatorBy(Polyhedron::area));

        Comparator<Cube> cmpCube = Solid.comparatorBy(Cube::perimeter);
        Comparator<Solid> cmpSolid = Solid.comparatorBy(Solid::area);
        //Comparator<Sphere> cmpSphere = Solid.comparatorBy(Sphere::perimeter);
        //Comparator<Solid> cmpSolid2 = Solid.comparatorBy( ( Cube c) -> c.area());
        Comparator<Polyhedron> cmpPoly = Solid.comparatorBy(Polyhedron::volume);

        Comparator<Solid> cmpS = Solid.comparatorBy(Solid::area);
        Comparator<Solid> sda = Solid.comparatorBy((Solid s) -> s.area());

        Comparator<Polyhedron> cmpPoly2 = Solid.comparatorBy(Solid::volume);
        Comparator<Cube> cmpCube2 = Solid.comparatorBy(Polyhedron::perimeter);

        Comparator<Sphere> cmpSphere2 = Solid.comparatorBy(Solid::area);
        //caso1
        Function<Sphere, Double> x1 = Solid::area;
        Comparator<Sphere> cmpS1 = Solid.comparatorBy(x1);
        //caso2
        Function<Sphere, Double> x2 = (Solid s) -> s.area();
        Comparator<Sphere> cmpS2 = Solid.comparatorBy(x2);












        //Collections.sort(solids, cmpCube2);
        Collections.sort(cubes, cmpSolid);

        Collections.sort(spheres, cmpSphere2);
        //Collections.sort(solids, cmpPoly2);
        //Collections.sort(cubes, cmpSolid2);
        Collections.sort(cubes, cmpPoly);
        //Collections.sort(spheres, cmpPoly2);
        Collections.sort(polys, cmpSolid);




        Random r = new Random();
        for(Polyhedron poly : polys){
            System.out.println("Next Poly");
            for ( Point p : poly.at(new Point(r.nextDouble() * 10,r.nextDouble() * 10,r.nextDouble() * 10))){
                System.out.println(String.format("Point[x:%f,y:%f,z:%f]", p.x, p.y, p.z));
            }
        }
        */
    }
}
