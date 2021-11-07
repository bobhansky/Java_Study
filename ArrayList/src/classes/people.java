package classes;

public class people {
    private int id = 0;

    public people(int i){
        id = i;
    }
    public void shout(){
        System.out.println("people shout!");
    }

    @Override
    public String toString(){
        return "This is a people with id: "+ id +", and \n it can shout!";
    }
}
