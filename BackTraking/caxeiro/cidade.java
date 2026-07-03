package BackTraking.caxeiro;

public class cidade {
    private String nome;
    private int[] cidadesPróximas;

    public cidade(String nome, int [] cidadesPróximas){
        this.nome = nome;
        this.cidadesPróximas = cidadesPróximas;
    }

    public int getDistancia(int indxex){
        return cidadesPróximas[indxex];
    }

    public int [] getDistancias(){
        return cidadesPróximas;
    }

    public String getNme(){
        return this.nome;
    }



}
