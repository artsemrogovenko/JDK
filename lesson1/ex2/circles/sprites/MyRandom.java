package circles.sprites;

public class MyRandom {    
    public float nextFloat(float min, float max){
        return (float) ((Math.random() * (max - min)) + min);
    }
    public float nextFloat(float max){
        return (float) ((Math.random() * (max - 0.0)) + 0.0);
    }

   
    public int nextInt(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int nextInt(int max){
        return (int) ((Math.random() * (max - 0)) + 0);
    }
    
}
