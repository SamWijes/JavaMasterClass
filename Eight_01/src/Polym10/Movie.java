package Polym10;

public class Movie {
    private String title;

    public Movie(String title){
        this.title=title;
    }



    public void watchMovie(){
        String instanceType=getClass().getSimpleName();
        if (instanceType.equals("Movie")){
            System.out.printf("%s is a %s %n",title,instanceType);
        }else {
            System.out.printf("%s is a %s Film %n", title, instanceType);
        }
    }
    public static Movie getMovie(String type, String title) {
        return switch (type.toUpperCase().charAt(0)){
            case 'A' -> new Adventure(title);
            case 'C'->new Comedy(title);
            case 'S'->new SciFiction(title);
            default -> new Movie(title);
        };
    }
}

class Adventure extends Movie{

    public Adventure(String title) {
        super(title);
    }

    @Override
    public void watchMovie() {
        super.watchMovie();
        System.out.printf("..%s%n".repeat(3),"Action","Thriller","fights");
    }


    public void watchAdventure(){
        System.out.println("Watching Adventure");
    }
}

class Comedy extends Movie{

    public Comedy(String title) {
        super(title);
    }

    @Override
    public void watchMovie(){
        super.watchMovie();
        System.out.printf("..%s%n".repeat(3),"Funny","Drama","Family");

    }

    public void watchComedy(){
        System.out.println("Watching Comedy");
    }
}

class SciFiction extends Movie{

    public SciFiction(String title) {
        super(title);
    }

    @Override
    public void watchMovie(){
        super.watchMovie();
        System.out.printf("..%s%n".repeat(3),"Science","Fiction","Family");

    }

    public void watchScifi(){
        System.out.println("Watching Scifi");
    }
}