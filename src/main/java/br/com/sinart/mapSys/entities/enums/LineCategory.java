package br.com.sinart.mapSys.entities.enums;

public enum LineCategory {

    INTERMUNICIPAL(1,"INTERMUNICIPAL"),
    INTERESTADUAL(2,"INTERESTADUAL"),
    METROPOLITANA(3,"METROPOLITANA"),
    INTERNACIONAL(4,"INTERNACIONAL");

    private Integer cod;
    private String description;

    LineCategory(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static  LineCategory toEnum(Integer cod){
        if(cod == null){
            return  null;
        }

        for (LineCategory x : LineCategory.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Id inválido: " + cod);
    }
}
