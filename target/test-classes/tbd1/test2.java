public class test2 {
    int counter;
    String name;
    Double sul;
    public test2(String name,Double sul){
        this.name=name;
        this.sul=sul;
        this.counter=0;
    }
    public String returnname (String name){
        counter ++;
        return name;

    }
    public int getCounter(){
        return counter;
    }
    public Double getSul(){
        return sul;
    }
}
