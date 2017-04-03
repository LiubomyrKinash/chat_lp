public class Parser {
    
    public String[] parsForComand(String line) {
        String[] outMas;
        String[] parsMas = line.split(" ", 2);

        switch (parsMas[0]) {
            case "echo":
                outMas = line.split(" ", 2);                
                break;

            case "msg":
                outMas = line.split(" ", 3); 
                break;

            default:
                outMas = line.split(" ");
                break;
        }
        return outMas;
    }
    
}


