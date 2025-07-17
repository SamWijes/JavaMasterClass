package Polym10;

public class NextMain {
    public static void main(String[] args) {
        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();


        //casting

        Comedy com = (Comedy) Movie.getMovie("c", "bean");
        com.watchMovie();

        //Using Object Reference to access

        Object comedy = Movie.getMovie("c", "Bean");
        Comedy comdyCast =(Comedy) comedy; //cast object
        comdyCast.watchMovie();
        //((Comedy) comedy).watchMovie();  //in one line
        //((Movie) comedy).watchComedy();  // Doesnt work since method in comedy subclass
        //((Comedy) comedy).watchComedy();
        /*As shown above when using object reference must cast for specific parent or child class to
        * access methods*/

//        var airplane =Movie.getMovie("A","Airplane");
//        airplane.watchMovie();
//        var bean =Movie.getMovie("C","Airplane");
//        bean.watchMovie();


        Object unknownObject=Movie.getMovie("C","Bean");

        if (unknownObject.getClass().getSimpleName().equals("Comedy")){
            Comedy c=(Comedy) unknownObject;
            c.watchComedy();
        } else if (unknownObject instanceof Adventure) {
          ( (Adventure) unknownObject).watchAdventure();

        }else if (unknownObject instanceof SciFiction syfy){
            syfy.watchScifi();
        }


    }
}